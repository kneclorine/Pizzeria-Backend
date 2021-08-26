package com.example.demo.application.userApplication;

import java.util.List;
import java.util.UUID;


import javax.validation.Valid;
import com.example.demo.domain.userDomain.User;
import com.example.demo.domain.userDomain.UserReadRepository;
import com.example.demo.domain.userDomain.UserWriteRepository;
import com.example.demo.domain.userDomain.UserProjection;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class userApplicationImp implements userApplication {

    private final UserWriteRepository userWriteRepository;
    private final UserReadRepository userReadRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final Logger logger;

    @Autowired
    public userApplicationImp(final UserWriteRepository userWriteRepository, 
                              final UserReadRepository userReadRepository,
                              final Logger logger){

        this.userWriteRepository = userWriteRepository;
        this.userReadRepository = userReadRepository;
        this.logger = logger;
    }

    @Override
    public UserDTO add(@Valid CreateOrUpdateUser dto) {

        User user = modelMapper.map(dto, User.class);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())); 
        user.setId(UUID.randomUUID());
        
        user.validate();
        
        this.userWriteRepository.add(user); 
        logger.info("User"+"added succesfully.");

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO get(UUID id) {
        User user = this.userReadRepository.findById(id).orElseThrow();

        System.out.println(user.getPassword());

        //BCrypt.checkpw(psw, user.getPassword());

        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void update(UUID id, CreateOrUpdateUser dtos) {
        User user = this.userReadRepository.findById(id).orElseThrow();
        user.setId(id);
        user.setName(dtos.name);
        user.setLastName(dtos.lastName);
        user.setEmail(dtos.email);

        this.userWriteRepository.update(user);
        logger.info("User"+"updated succesfully.");
        
    }

    
    
}
