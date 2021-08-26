package com.example.demo.application.imageApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.imageDomain.ImageEntity;
import com.example.demo.infraestructure.imageInfraestructure.ImageRepositoryImp;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImageApplicationImp implements ImageApplication{

    private final ImageRepositoryImp imageRepositoryImp;
    private final Logger logger;

    @Autowired
    public ImageApplicationImp(final ImageRepositoryImp imageRepositoryImp,final Logger logger) {
        this.imageRepositoryImp = imageRepositoryImp;
        this.logger = logger;
    }

    public ImageEntity save(ImageDTO dto) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(UUID.randomUUID().toString());
        imageEntity.setData(dto.getData());
        
        imageRepositoryImp.add(imageEntity);
        logger.info("Image added succesfully.");
        return null;
    }

    public Optional<ImageEntity> getFile(String id) {
        return imageRepositoryImp.findById(id);
    }

    public File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
