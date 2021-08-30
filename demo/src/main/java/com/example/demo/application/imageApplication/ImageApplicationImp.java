package com.example.demo.application.imageApplication;

import java.util.UUID;

import com.example.demo.domain.imageDomain.ImageEntity;
import com.example.demo.domain.imageDomain.ImageRepository;
import com.example.demo.core.ApplicationBase;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        
        imageRepository.add(imageEntity);
        logger.info("Image added succesfully.");

        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(imageEntity.getId());
        return imageDTO;
    }

    public CloudinaryDTO getFile(UUID id) {
        return modelMapper.map(this.findById(id),CloudinaryDTO.class);
    }

}
