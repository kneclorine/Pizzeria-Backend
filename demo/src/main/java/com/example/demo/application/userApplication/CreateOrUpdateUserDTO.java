package com.example.demo.application.userApplication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Validated
public @NoArgsConstructor @Getter @Setter class CreateOrUpdateUserDTO {

    @NotBlank @Size(min=2, max=255)
    private String name;
    
    @NotBlank @Size(min=2, max=255)
    private String lastName;
    
    @NotBlank @Email @Size(min=2, max=255)
    private String email;

    @NotBlank @Size(min=8, max=255)
    private String password;
}
