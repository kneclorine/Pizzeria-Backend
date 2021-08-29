package com.example.demo.application.imageApplication;

import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.imageDomain.ImageEntity;
import com.example.demo.infraestructure.imageInfraestructure.ImageRepositoryImp;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImageApplicationImp implements ImageApplication{

    private final ImageRepositoryImp imageRepositoryImp;
    private final Logger logger;

    @Autowired
    public ImageApplicationImp(final ImageRepositoryImp imageRepositoryImp,
                                final Logger logger) {
        this.imageRepositoryImp = imageRepositoryImp;
        this.logger = logger;
    }

    public ImageEntity save(CreateOrUpdateImageDTO dto) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(UUID.randomUUID());
        imageEntity.setData(dto.getData());
        
        imageRepositoryImp.add(imageEntity);
        logger.info("Image added succesfully.");
        return imageEntity;
    }

    public Optional<ImageEntity> getFile(UUID id) {
        return imageRepositoryImp.get(id);
    }

}
