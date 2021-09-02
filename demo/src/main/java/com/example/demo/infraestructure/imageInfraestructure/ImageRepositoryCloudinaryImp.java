package com.example.demo.infraestructure.imageInfraestructure;

import com.example.demo.domain.imageDomain.Image;
import com.example.demo.domain.imageDomain.ImageCloudinary;
import com.example.demo.domain.imageDomain.ImageRepositoryCloudinary;

public class ImageRepositoryCloudinaryImp implements ImageRepositoryCloudinary {

    @Override
    public ImageCloudinary add(Image image) {
        return new ImageCloudinary();
    }
    
}
