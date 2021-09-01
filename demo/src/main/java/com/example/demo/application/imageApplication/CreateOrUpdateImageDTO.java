package com.example.demo.application.imageApplication;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class CreateOrUpdateImageDTO {
    @NotBlank
    public byte[] data;
}
