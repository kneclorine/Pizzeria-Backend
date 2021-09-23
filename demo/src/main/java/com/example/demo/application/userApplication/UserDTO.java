package com.example.demo.application.userApplication;

import java.util.UUID;

import com.example.demo.domain.userDomain.Rol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @NoArgsConstructor @Getter @Setter class UserDTO {

    private UUID id;

    private String name;

    private String lastName;
    
    private String email;

    private Rol rol;

    private String type;

    private String token;
}
