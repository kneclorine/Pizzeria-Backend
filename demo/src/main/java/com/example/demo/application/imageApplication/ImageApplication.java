package com.example.demo.application.imageApplication;

import java.util.UUID;


public interface ImageApplication {
    public ImageDTO save(CreateOrUpdateImageDTO dto);
    public CloudinaryDTO getFile(UUID id);
}
