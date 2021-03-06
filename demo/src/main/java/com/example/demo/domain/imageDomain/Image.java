package com.example.demo.domain.imageDomain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import com.example.demo.core.EntityBase;

import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RedisHash("Image")
@Embeddable
@Entity
public @NoArgsConstructor @Getter @Setter class Image extends EntityBase{

    @NotEmpty
    private byte[] image;
}
