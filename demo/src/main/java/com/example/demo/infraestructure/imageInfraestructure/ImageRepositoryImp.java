package com.example.demo.infraestructure.imageInfraestructure;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.core.configurationBeans.CloudinaryConfiguration;
import com.example.demo.core.exceptions.InternalServerErrorEnum;
import com.example.demo.core.exceptions.InternalServerErrorException;
import com.example.demo.domain.imageDomain.Image;
import com.example.demo.domain.imageDomain.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepositoryImp implements ImageRepository {

    private final RedisTemplate<String,byte[]> redisTemplate;
    private final Cloudinary cloudinary = CloudinaryConfiguration.buildConnection();

    @Autowired
    public ImageRepositoryImp(final RedisTemplate<String,byte[]> redisTemplate){
        
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void add(Image imageEntity) {
        
        try{
            redisTemplate.opsForValue().set(imageEntity.getId().toString(),imageEntity.getImage(),Duration.ofDays(1));
            cloudinary.uploader().upload(imageEntity.getImage(), ObjectUtils.asMap("public_id",imageEntity.getId().toString()));
        }catch(Exception e){
            throw new InternalServerErrorException(InternalServerErrorEnum.REDIRECT);
        }finally{
            if(!this.redisTemplate.getConnectionFactory().getConnection().isClosed()){
                this.redisTemplate.getConnectionFactory().getConnection().close();
            }
        }
    }

    @Override
    public Optional<Image> get(UUID id) {
        try{
            byte[] bytes = this.redisTemplate.opsForValue().get(id.toString());
            if(bytes==null){
                return Optional.of(null);
            }
            Image image = new Image();
            image.setImage(bytes);
            image.setId(id);

            return Optional.of(image);
        }catch(Exception e){
            throw new InternalServerErrorException(InternalServerErrorEnum.REDIRECT);
        }finally{
            if(!this.redisTemplate.getConnectionFactory().getConnection().isClosed()){
                this.redisTemplate.getConnectionFactory().getConnection().close();
            }
        }
    }
}
