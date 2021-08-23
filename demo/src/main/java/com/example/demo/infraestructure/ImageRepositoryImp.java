package com.example.demo.infraestructure;

import java.io.File;
import java.io.FileOutputStream;
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
public class ImageRepositoryImp {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageRepositoryImp(ImageRepository imageRepository) {
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

    public File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
