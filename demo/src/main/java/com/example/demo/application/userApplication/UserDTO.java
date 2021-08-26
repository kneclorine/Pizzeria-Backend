package com.example.demo.application.userApplication;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import  com.example.demo.domain.userDomain.Rol;

@RequestMapping("api/v1/users")
public @Getter @Setter @NoArgsConstructor class UserDTO implements Serializable{

    @NotBlank 
    private UUID id;

    @NotBlank @Size(min=2, max = 255)
    private String name;

    @NotBlank @Size(min=2, max = 255)
    private String lastName;

    @NotBlank @Size(min=2, max = 255)
    private String email;

    @NotBlank @Size(min=2, max = 255)
    private String password;

    @Column
    private Rol rol = Rol.ROLE_USER;

}
