package com.example.demo.infraestructure.imageInfraestructure;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.example.demo.domain.imageDomain.ImageEntity;
import com.example.demo.domain.imageDomain.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepositoryImp implements ImageRepository {

    private final RedisTemplate<UUID,byte[]> redisTemplate;

    @Autowired
    public ImageRepositoryImp(final RedisTemplate<UUID, byte[]> redisTemplate){
        
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void add(ImageEntity imageEntity) {
        
        redisTemplate.opsForValue().set(imageEntity.getId(),imageEntity.getData(),10,TimeUnit.SECONDS);        
        
    }

    @Override
    public Optional<ImageEntity> get(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
