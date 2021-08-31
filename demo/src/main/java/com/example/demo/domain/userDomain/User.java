package com.example.demo.domain.userDomain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size; 
import javax.validation.constraints.NotBlank;

import com.example.demo.core.EntityBase;
import com.example.demo.domain.commentDomain.Comment;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
public @NoArgsConstructor @Getter @Setter class User extends EntityBase{


    @NotNull @Size(min=2, max=255)
    @Column(nullable = false)
    private String name;

    @NotNull @Size(min=2, max=255)
    @Column(nullable = false)
    private String lastName;

    @NotNull @Size(min=2, max=255)
    @Column(nullable = false)
    private String email;

    @NotNull @Size(min=2, max=255)
    @Column(nullable = false)
    private String password;

    @Column
    private Rol rol = Rol.ROLE_USER;


    /*@NotNull @Size(min=2, max=255)
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = new HashSet<>(); */

    public User(String name, String lastName, String email, String password, Rol rol){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rol = rol;
    } 


}
