package com.example.demo.application.imageApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.imageDomain.ImageEntity;
import com.example.demo.infraestructure.imageInfraestructure.ImageRepositoryImp;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImageApplicationImp implements ImageApplication{

    private final ImageRepositoryImp imageRepositoryImp;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public ImageApplicationImp(final ImageRepositoryImp imageRepositoryImp,
                                final ModelMapper modelMapper,
                                final Logger logger) {
        this.imageRepositoryImp = imageRepositoryImp;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    public ImageEntity save(ImageDTO dto) {
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
