package com.example.demo.application.imageApplication;

import java.io.IOException;
import java.util.UUID;

import reactor.core.publisher.Mono;


public interface ImageApplication {
    public ImageDTO save(CreateOrUpdateImageDTO dto) throws IOException;
    public Mono<BytesDTO> get(UUID id);
}
