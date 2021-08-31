package com.example.demo.infraestructure.imageInfraestructure;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.core.configurationBeans.CloudinaryConfiguration;
import com.example.demo.core.exceptions.InternalServerErrorEnum;
import com.example.demo.core.exceptions.InternalServerErrorException;
import com.example.demo.domain.imageDomain.ImageEntity;
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
            ImageEntity image = new ImageEntity();
            InputStream inputStream = new ByteArrayInputStream(bytes);
            File file = new File("output.png");
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            file.delete();
            String cloudUrl= cloudinary.url().secure(true).publicId((String) result.get("public_id")).generate();
            image.setUrl(cloudUrl);

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
