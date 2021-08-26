package com.example.demo.application.userApplication;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @NoArgsConstructor @Getter @Setter class UserDTO {

    private UUID id;

    private String name;

    private String lastName;
    
    private String email;

    private String password;
}
