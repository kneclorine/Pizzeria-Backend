package com.example.demo.application.imageApplication;

import java.io.IOException;
import java.util.UUID;

import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.imageDomain.Image;
import com.example.demo.domain.imageDomain.ImageRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;



@Service
public class ImageApplicationImp extends ApplicationBase<Image, UUID> implements ImageApplication{

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

    @Override
    public ImageDTO save(CreateOrUpdateImageDTO dto) throws IOException {

        Image imageEntity = modelMapper.map(dto, Image.class);
        imageEntity.setId(UUID.randomUUID());
        imageEntity.setThisNew(true);

        imageEntity.validate();
        imageRepository.add(imageEntity);
        this.logger.info(serializeObject(imageEntity, "added"));
        
        return modelMapper.map(imageEntity, ImageDTO.class);
    }

    @Override
    public Mono<BytesDTO> get(UUID id) {

        return this.findById(id).flatMap( image -> Mono.just(this.modelMapper.map(image, BytesDTO.class)));
    }

}
