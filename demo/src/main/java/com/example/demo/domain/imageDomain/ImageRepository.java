package com.example.demo.domain.imageDomain;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository{  
    public void add(ImageEntity imageEntity);
    public Optional<ImageEntity> get(UUID id);
}
