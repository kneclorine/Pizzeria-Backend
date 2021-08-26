package com.example.demo.application.imageApplication;

import java.util.Optional;

import com.example.demo.domain.imageDomain.ImageEntity;

public interface ImageApplication {
    public ImageEntity save(ImageDTO dto);
    public Optional<ImageEntity> getFile(String id);
}
