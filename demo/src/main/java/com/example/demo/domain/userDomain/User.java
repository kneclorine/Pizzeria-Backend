package com.example.demo.domain.userDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.core.EntityBase;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table("user")
public @NoArgsConstructor @Getter @Setter class User extends EntityBase {

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private Rol rol = Rol.USER;

    @Override
    public String toString() {

        return String.format("User {id: %s, name: %s, lastName: %s, email: %s, rol: %s}", this.getId(), this.getName(),
                this.getLastName(), this.getEmail(), this.getRol());
    }
}
