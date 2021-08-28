package com.example.demo.domain.imageDomain;

import javax.persistence.Column;

public class Image {

    @Column(name = "image")
    public byte[] data;
}
