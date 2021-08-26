package com.example.demo.application.imageApplication;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class CreateOrUpdateImageDTO {
    @NotNull @Size(min=1)
    private byte[] data;
}
