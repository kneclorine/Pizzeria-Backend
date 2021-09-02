package com.example.demo.application.userApplication;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.domain.userDomain.Rol;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Validated
public @NoArgsConstructor @Getter @Setter class UpdateUserDTO {

    @NotBlank
    private String name;
    
    @NotBlank
    private String lastName;

    @NotBlank
    private String password;

    @NotBlank
    private String newPassword;

    @NotNull
    private Rol rol = Rol.USER;
}