package com.example.demo.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.application.imageApplication.ImageApplicationImp;
import com.example.demo.application.imageApplication.ImageDTO;
import com.example.demo.domain.imageDomain.ImageEntity;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private final ImageApplicationImp imageApplicationImp;

    @Autowired
    public ImageController(ImageApplicationImp imageApplicationImp) {
        this.imageApplicationImp = imageApplicationImp;
    }

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile file) throws IOException {

        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setData(file.getBytes());
        ImageEntity imageEntity = imageApplicationImp.save(imageDTO);

        return ResponseEntity.status(HttpStatus.OK)
                                .body(String.format("Archivo subido correctamente: %s, uuid=%s", file.getOriginalFilename(), imageEntity.getId()));
    
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) {
        Optional<ImageEntity> imageEntityOptional = imageApplicationImp.getFile(id);

        ImageEntity imageEntity = imageEntityOptional.get();
        return ResponseEntity.ok()
                             .body(imageEntity.getData());
    }
}
