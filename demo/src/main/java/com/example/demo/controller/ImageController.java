package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.application.imageApplication.ImageApplicationImp;
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
    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile file) {

        
        try {
            ImageEntity imageEntity = imageApplicationImp.save(file);
            //añadir imageEntity.getId() a image_id de pizza
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(String.format("Archivo subido correctamente: %s, uuid=%s", file.getOriginalFilename(), imageEntity.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(String.format("No se pudo encontrar el archivo: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Optional<ImageEntity> imageEntityOptional = imageApplicationImp.getFile(id);

        if (!imageEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                                 .build();
        }

        ImageEntity imageEntity = imageEntityOptional.get();
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; timestamp=\"" + imageEntity.getTimestamp() + "\"")
                             .contentType(MediaType.valueOf(imageEntity.getContentType()))
                             .body(imageEntity.getData());
    }
}
