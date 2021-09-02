package com.example.demo.infraestructure.imageInfraestructure;

import java.util.UUID;

import com.example.demo.domain.imageDomain.Image;
import com.example.demo.domain.imageDomain.ImageRepositoryRedis;

public class ImageRepositoryRedisImp implements ImageRepositoryRedis {

    @Override
    public Image get(UUID id) {
        return new Image();
    }
    
}
