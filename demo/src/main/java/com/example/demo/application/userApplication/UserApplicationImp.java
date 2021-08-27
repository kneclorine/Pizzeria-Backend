package com.example.demo.application.userApplication;

import java.util.List;
import java.util.UUID;

import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.userDomain.User;
import com.example.demo.domain.userDomain.UserProjection;
import com.example.demo.domain.userDomain.UserReadRepository;
import com.example.demo.domain.userDomain.UserWriteRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationImp extends ApplicationBase<User, UUID> implements UserApplication {

    private final UserWriteRepository userWriteRepository;
    private final UserReadRepository userReadRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public UserApplicationImp(final UserWriteRepository userWriteRepository, UserReadRepository userReadRepository, final ModelMapper modelMapper,
            final Logger logger) {

        super((id) -> userWriteRepository.findById(id));

        this.userWriteRepository = userWriteRepository;
        this.userReadRepository = userReadRepository;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public UserDTO add(CreateUserDTO dto) {

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
    public UserDTO update(UUID id, UpdateUserDTO dto) {

        User user = this.findById(id);
        User userUpdated = this.modelMapper.map(dto, User.class);
        userUpdated.setId(user.getId());
        userUpdated.setEmail(user.getEmail());
    
        if(BCrypt.checkpw(userUpdated.getPassword(), user.getPassword())) {
            userUpdated.setPassword(user.getPassword());
        } else {
            userUpdated.setPassword(BCrypt.hashpw(userUpdated.getPassword(), BCrypt.gensalt()));
        }
        userUpdated.validate();
        
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

    @Override
    public List<UserProjection> getAll(String name, int page, int size) {
        return this.userReadRepository.getAll(name, page, size);
    }
}
