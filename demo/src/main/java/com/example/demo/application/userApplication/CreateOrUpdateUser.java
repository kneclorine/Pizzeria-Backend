package com.example.demo.application.userApplication;

import java.util.UUID;

import javax.persistence.Column;

import  com.example.demo.domain.userDomain.Rol;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@RequestMapping("api/v1/users")
public @Getter @Setter class CreateOrUpdateUser {
    public String name;
    public String lastName;
    public String email;
    public String password;

    private Rol rol = Rol.ROLE_USER;

}
