package com.example.demo.domain.imageDomain;

import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.Setter;

@RedisHash("Image")
public @Getter @Setter class ImageEntity {
    private String id;
    private String name;
    private String contentType;
    private Long size;
    private byte[] data;
}
