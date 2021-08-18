package com.example.demo.application;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.imageDomain.ImageEntity;
import com.example.demo.domain.imageDomain.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageEntity save(MultipartFile file) throws IOException {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(UUID.randomUUID().toString());
        imageEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        imageEntity.setContentType(file.getContentType());
        imageEntity.setData(file.getBytes());
        imageEntity.setSize(file.getSize());

        return imageRepository.save(imageEntity);
    }

    public Optional<ImageEntity> getFile(String id) {
        return imageRepository.findById(id);
    }
}
