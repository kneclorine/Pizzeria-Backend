package com.example.demo.domain.imageDomain;

import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository{  
    public void add(ImageEntity imageEntity);
    public ImageEntity get();
}
