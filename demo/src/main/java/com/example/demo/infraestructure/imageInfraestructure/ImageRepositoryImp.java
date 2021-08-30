package com.example.demo.infraestructure.imageInfraestructure;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.core.exceptions.InternalServerErrorEnum;
import com.example.demo.core.exceptions.InternalServerErrorException;
import com.example.demo.core.exceptions.NotFoundException;
import com.example.demo.domain.imageDomain.ImageEntity;
import com.example.demo.domain.imageDomain.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepositoryImp implements ImageRepository {

    private final RedisTemplate<String,byte[]> redisTemplate;

    @Autowired
    public ImageRepositoryImp(final RedisTemplate<String,byte[]> redisTemplate){
        
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void add(ImageEntity imageEntity) {
        
        try{
            redisTemplate.opsForValue().set(imageEntity.getId().toString(),imageEntity.getData(),Duration.ofDays(1));
        
        }catch(Exception e){
            throw new InternalServerErrorException(InternalServerErrorEnum.REDIRECT);
        }finally{
            if(!this.redisTemplate.getConnectionFactory().getConnection().isClosed()){
                this.redisTemplate.getConnectionFactory().getConnection().close();
            }
        }
    }

    @Override
    public Optional<ImageEntity> get(UUID id) {
        try{
            byte[] bytes = this.redisTemplate.opsForValue().get(id.toString());
            if(bytes==null){
                return Optional.of(null);
            }
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setId(id);
            imageEntity.setData(bytes);
            return Optional.of(imageEntity);
        }catch(Exception e){
            throw new NotFoundException("Not Found");
        }finally{
            if(!this.redisTemplate.getConnectionFactory().getConnection().isClosed()){
                this.redisTemplate.getConnectionFactory().getConnection().close();
            }
        }
    }
    
}
