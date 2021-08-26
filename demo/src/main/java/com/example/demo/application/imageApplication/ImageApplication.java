package com.example.demo.application.imageApplication;

import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.imageDomain.ImageEntity;

public interface ImageApplication {
    public ImageEntity save(ImageDTO dto);
    public Optional<ImageEntity> getFile(UUID id);
}
