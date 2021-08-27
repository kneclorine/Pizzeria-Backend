package com.example.demo.application.userApplication;

import java.util.UUID;

import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.userDomain.User;
import com.example.demo.domain.userDomain.UserWriteRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationImp extends ApplicationBase<User, UUID> implements UserApplication {

    private final UserWriteRepository userWriteRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public UserApplicationImp(final UserWriteRepository userWriteRepository, final ModelMapper modelMapper,
            final Logger logger) {

        super((id) -> userWriteRepository.findById(id));

        this.userWriteRepository = userWriteRepository;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public UserDTO add(CreateOrUpdateUserDTO dto) {

        User user = modelMapper.map(dto, User.class);
        user.setId(UUID.randomUUID());
        user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        user.validate("email", user.getEmail(), (email) -> this.userWriteRepository.exists(email));

        this.userWriteRepository.add(user);
        logger.info(this.serializeObject(user, "added"));

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO get(UUID id) {

        User user = this.findById(id);
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO update(UUID id, CreateOrUpdateUserDTO dto) {

        User userToUpdate = this.findById(id);
        User userUpdated = modelMapper.map(dto, User.class);
        userUpdated.setId(id);

        if (userToUpdate.getEmail().equals(dto.getEmail())) {

            userUpdated.validate();
        } else {

            userUpdated.validate("email", userUpdated.getEmail(), (email) -> this.userWriteRepository.exists(email));
        }

        if (BCrypt.checkpw(dto.getPassword(), userToUpdate.getPassword())) {

            userUpdated.setPassword(userToUpdate.getPassword());            
        }else {

            userUpdated.setPassword(BCrypt.hashpw(userUpdated.getPassword(), BCrypt.gensalt()));
        }
        
        this.userWriteRepository.update(userUpdated);
        logger.info(this.serializeObject(userUpdated, "updated"));

        return modelMapper.map(userUpdated, UserDTO.class);
    }

    @Override
    public void delete(UUID id) {

        User user = this.findById(id);
        this.userWriteRepository.delete(user);
        logger.info(this.serializeObject(user, "deleted"));
    }

    private String serializeObject(User user, String messege) {

        return String.format("User {id: %s, name: %s, lastName: %s, email: %s} %s succesfully.", user.getId(),
                user.getName(), user.getLastName(), user.getEmail(), messege);
    }
}
