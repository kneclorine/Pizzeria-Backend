package com.example.demo.domain.imageDomain;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository{  
    public String add(ImageEntity imageEntity);
    public Optional<ImageEntity> get(UUID id);
}
