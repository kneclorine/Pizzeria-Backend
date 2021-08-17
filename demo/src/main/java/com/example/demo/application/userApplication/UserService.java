package com.example.demo.application.userApplication;
@Service
@Validated
public class UserService {
    public void createUser(@Valid User user) {
        //TODO: lógica para el servicio del usuario
      }

      @Validated(values = {Default.class, UserFullValidation.class})
      public void createFullClient(@Valid User user) {
         //TODO: lógica para el servicio completo
      }
}
