package com.example.demo.domain.imageDomain;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository{  
    public void add(Image imageEntity);
    public Optional<Image> get(UUID id);
}
