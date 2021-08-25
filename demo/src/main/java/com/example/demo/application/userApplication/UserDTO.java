package com.example.demo.application.userApplication;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @NoArgsConstructor @Getter @Setter class UserDTO {

    @NotBlank
    private UUID id;

    @NotBlank @Size(min=2, max=255)
    private String name;
    
    @NotBlank @Size(min=2, max=255)
    private String lastName;
    
    @NotBlank @Email @Size(min=2, max=255)
    private String email;

    @NotBlank @Size(min=8, max=255)
    private String password;
}
