package com.example.demo.domain.userDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.core.EntityBase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public @NoArgsConstructor @Getter @Setter class User extends EntityBase{

    @NotBlank @Size(min=2, max=255)
    @Column(nullable = false)
    private String name;

    @NotBlank @Size(min=2, max=255)
    @Column(nullable = false)
    private String lastName;

    
    @NotBlank @Email @Size(min=2, max=255)
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank @Size(min=8, max=255)
    @Column(nullable = false)
    private String password;
}
