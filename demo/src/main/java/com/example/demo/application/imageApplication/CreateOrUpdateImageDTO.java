package com.example.demo.application.imageApplication;

import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
public @Getter @Setter class CreateOrUpdateImageDTO {
    @NotBlank
    public byte[] data;
}
