package com.example.demo.controller;

import java.io.File;
import java.util.Map;

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

import com.example.demo.infraestructure.ImageRepositoryImp;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.application.CloudinaryConfiguration;
import com.example.demo.domain.imageDomain.ImageEntity;

@RestController
@RequestMapping("images")
public class ImageController {
    private final ImageRepositoryImp imageRepositoryImp;
    private Cloudinary cloudinary = CloudinaryConfiguration.buildConnection();

    @Autowired
    public ImageController(ImageRepositoryImp imageRepositoryImp) {
        this.imageRepositoryImp = imageRepositoryImp;
    }

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile file) {
        try {
            ImageEntity imageEntity = imageRepositoryImp.save(file);
            
            
            File fileSave = imageRepositoryImp.convert(file);
            
            Map result = cloudinary.uploader().upload(fileSave, ObjectUtils.emptyMap());
            imageEntity.setId((String)result.get("public_id"));
            fileSave.delete();

            return ResponseEntity.status(HttpStatus.OK)
                                 .body(String.format("Archivo subido correctamente: %s, uuid=%s", file.getOriginalFilename(), imageEntity.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(String.format("No se pudo encontrar el archivo: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getFile(@PathVariable String id) {
        String format = "png";
        Transformation transformation = new Transformation().crop("fill");
        
        String cloudUrl = cloudinary.url().secure(true).format(format)
                .transformation(transformation)
                .publicId(id)
                .generate();
        System.out.println(cloudUrl);

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setUrl(cloudUrl);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(String.format(imageEntity.getUrl()));
    }
}
