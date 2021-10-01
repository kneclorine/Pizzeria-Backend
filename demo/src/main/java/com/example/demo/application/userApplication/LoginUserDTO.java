package com.example.demo.application.userApplication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Validated
public @NoArgsConstructor @Getter @Setter class LoginUserDTO {


    @NotBlank @Email
    private String email;

    @NotBlank
    private String password;

}
