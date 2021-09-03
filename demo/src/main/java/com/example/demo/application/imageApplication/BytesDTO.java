package com.example.demo.application.imageApplication;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @NoArgsConstructor @Getter @Setter class BytesDTO {
    private UUID id;
    private byte[] image;
}
