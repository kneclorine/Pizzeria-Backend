package com.example.demo.domain.imageDomain;

import java.util.UUID;

public interface ImageRepositoryRedis {
    
    public Image get(UUID id);
}
