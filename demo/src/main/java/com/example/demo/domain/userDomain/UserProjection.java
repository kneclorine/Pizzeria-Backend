package com.example.demo.domain.userDomain;

import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.rest.core.config.Projection;

    @Projection(name = "user", types = {User.class })
    public interface UserProjection {
    
    @NotBlank
    public UUID getId();

    @NotBlank @Size(min=2, max=255)
    public String getName();
    
    @NotBlank @Size(min=2, max=255)
    public String getLastName();

    @NotBlank @Size(min=2, max=255)
    String getEmail();

    Set<User> getUsers();

}


