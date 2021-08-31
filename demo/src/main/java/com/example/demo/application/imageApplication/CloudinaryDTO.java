package com.example.demo.application.imageApplication;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @NoArgsConstructor @Getter @Setter class CloudinaryDTO {
    @NotBlank
    private String Url;
}
