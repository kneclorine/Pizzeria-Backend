package com.example.demo.application.userApplication;

import javax.validation.Valid;
import javax.validation.groups.Default;

import com.example.demo.domain.userDomain.User;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserService {
    public void createUser(@Valid User user) {
        //TODO: lógica para el servicio del usuario
      }

      @Validated(value = {Default.class, UserFullValidation.class})
      public void createFullClient(@Valid User user) {
         //TODO: lógica para el servicio completo
      }
}
