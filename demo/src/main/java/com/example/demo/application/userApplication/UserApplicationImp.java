package com.example.demo.application.userApplication;

import java.util.UUID;

import com.example.demo.domain.userDomain.User;
import com.example.demo.domain.userDomain.UserWriteRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationImp implements UserApplication{

    private final UserWriteRepository userWriteRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public UserApplicationImp(final UserWriteRepository userWriteRepository,
                              final ModelMapper modelMapper,
                              final Logger logger){

        this.userWriteRepository = userWriteRepository;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public UserDTO add(CreateOrUpdateUserDTO dto) {

        User user = modelMapper.map(dto, User.class);
        user.setId(UUID.randomUUID());
        user.validate("email", user.getEmail(), (email) -> this.userWriteRepository.exists(email));

        this.userWriteRepository.add(user);
        logger.info(this.serializeObject(user, "added"));

        return modelMapper.map(user, UserDTO.class);
    }

    private String serializeObject(User user, String messege){
        StringBuilder stringBuilder = new StringBuilder("Ingredient {id: ").append(user.getId())
                                                                           .append(", name: ")
                                                                           .append(user.getName())
                                                                           .append(", lastName: ")
                                                                           .append(user.getLastName())
                                                                           .append(", email: ")
                                                                           .append(user.getEmail())
                                                                           .append(", password: ")
                                                                           .append(user.getPassword())
                                                                           .append("} ")
                                                                           .append(messege)
                                                                           .append(" succesfully.");
        return stringBuilder.toString();
    }
}