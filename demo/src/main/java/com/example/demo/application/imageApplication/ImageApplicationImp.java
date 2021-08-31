package com.example.demo.application.imageApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import com.example.demo.domain.imageDomain.ImageEntity;
import com.example.demo.domain.imageDomain.ImageRepository;
import com.example.demo.core.ApplicationBase;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImageApplicationImp extends ApplicationBase<ImageEntity, UUID> implements ImageApplication{

    private final ImageRepository imageRepository;
    private final Logger logger;
    private final ModelMapper modelMapper;

    @Autowired
    public ImageApplicationImp(final ImageRepository imageRepository,
                                final ModelMapper modelMapper,
                                final Logger logger) {
        super((id) -> imageRepository.get(id));
        this.imageRepository = imageRepository;
        this.logger = logger;
        this.modelMapper = modelMapper;
    }

    public ImageDTO save(CreateOrUpdateImageDTO dto) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(UUID.randomUUID());
        imageEntity.setData(dto.getData());
        imageEntity.validate();
        imageRepository.add(imageEntity);
        this.logger.info(serializeObject(imageEntity, "added"));

        return modelMapper.map(imageEntity, ImageDTO.class);
    }

    public CloudinaryDTO getFile(UUID id) {
        CloudinaryDTO cloudinaryDTO = modelMapper.map(this.findById(id),CloudinaryDTO.class);
        cloudinaryDTO.getUrl();
        return cloudinaryDTO;
    }

    private String serializeObject(ImageEntity image, String msg){
        return String.format("Image {id: %s, data: %s} has been %s succesfully.",
                            image.getId(), image.getData(),
                            msg);
    }

    public File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
