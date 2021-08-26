package com.example.demo.application.userApplication;

import javax.validation.Valid;

import com.example.demo.domain.userDomain.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.modelmapper.ModelMapper;

@Service
@Validated
public class UserService {
    public static User createUser(@Valid CreateOrUpdateUser userDTO) {
        
      ModelMapper modelMapper = new ModelMapper();
      User user = modelMapper.map(userDTO, User.class);

      modelMapper.validate();
      return user;
        
    }

    public static UserDTO createDTO(@Valid User user) {
        
      ModelMapper modelMapper = new ModelMapper();
      UserDTO userDTO = modelMapper.map(user, UserDTO.class);

      modelMapper.validate();
      return userDTO;
    }
  }

      

