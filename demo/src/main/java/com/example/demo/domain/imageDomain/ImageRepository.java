package com.example.demo.domain.imageDomain;

import java.util.UUID;

import reactor.core.publisher.Mono;

public interface ImageRepository{  
    public void add(Image imageEntity);
    public Mono<Image> get(UUID id);
}
