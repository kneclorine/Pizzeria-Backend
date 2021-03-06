package com.example.demo.controller;

import java.io.IOException;
import java.util.UUID;

import javax.validation.Valid;

import com.example.demo.application.imageApplication.BytesDTO;
import com.example.demo.application.imageApplication.CreateOrUpdateImageDTO;
import com.example.demo.application.imageApplication.ImageApplicationImp;
import com.example.demo.application.imageApplication.ImageDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageApplicationImp imageApplicationImp;

    @Autowired
    public ImageController(ImageApplicationImp imageApplicationImp) {
        this.imageApplicationImp = imageApplicationImp;
    }
    
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> upload(@RequestParam("image") MultipartFile file) throws IOException {

        CreateOrUpdateImageDTO dto = new CreateOrUpdateImageDTO();
        dto.setImage(file.getBytes());

        ImageDTO imageDTO = imageApplicationImp.save(dto);

        return ResponseEntity.status(201).body(imageDTO);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<?> getData(@Valid @PathVariable UUID id) {
        BytesDTO cloudinaryDTO = imageApplicationImp.get(id);
        
        return ResponseEntity.ok(cloudinaryDTO);
    }
}
