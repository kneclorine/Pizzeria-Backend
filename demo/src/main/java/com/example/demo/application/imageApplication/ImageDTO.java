package com.example.demo.application.imageApplication;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @NoArgsConstructor @Getter @Setter class ImageDTO {
    public UUID id;
    public String cloudId;
}
