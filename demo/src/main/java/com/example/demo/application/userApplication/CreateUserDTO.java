package com.example.demo.application.userApplication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.domain.userDomain.Rol;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Validated
public @NoArgsConstructor @Getter @Setter class CreateUserDTO {

    @NotBlank
    private String name;
    
    @NotBlank
    private String lastName;
    
    @NotBlank @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private Rol rol = Rol.USER;
}
