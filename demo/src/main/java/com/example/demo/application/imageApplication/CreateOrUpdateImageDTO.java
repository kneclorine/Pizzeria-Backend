package com.example.demo.application.imageApplication;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class CreateOrUpdateImageDTO {
    @Size(min=1)
    public byte[] data;
}
