package com.example.demo.application.imageApplication;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import com.example.demo.domain.imageDomain.ImageEntity;
import com.example.demo.domain.imageDomain.ImageRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.core.ApplicationBase;
import com.example.demo.core.configurationBeans.CloudinaryConfiguration;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ImageApplicationImp extends ApplicationBase<ImageEntity, UUID> implements ImageApplication{

    private final ImageRepository imageRepository;
    private final Logger logger;
    private final ModelMapper modelMapper;
    private final Cloudinary cloudinary = CloudinaryConfiguration.buildConnection();

    @Autowired
    public ImageApplicationImp(final ImageRepository imageRepository,
                                final ModelMapper modelMapper,
                                final Logger logger) {
        super((id) -> imageRepository.get(id));
        this.imageRepository = imageRepository;
        this.logger = logger;
        this.modelMapper = modelMapper;
    }

    public ImageDTO save(CreateOrUpdateImageDTO dto) throws IOException {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(UUID.randomUUID());
        imageEntity.setData(dto.getData());
        imageEntity.validate();
        imageRepository.add(imageEntity);
        Map result = cloudinary.uploader().upload(imageEntity.getData(), ObjectUtils.emptyMap());
        ImageDTO imageDTO = modelMapper.map(imageEntity, ImageDTO.class);
        imageDTO.setCloudId((String) result.get("public_id"));
        this.logger.info(serializeObject(imageEntity, "added"));
        return imageDTO;
    }

    public CloudinaryDTO getFile(UUID id) {
        return modelMapper.map(this.findById(id),CloudinaryDTO.class);
    }

}
