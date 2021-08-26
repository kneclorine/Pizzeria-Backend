package com.example.demo.controller;

import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.example.demo.application.userApplication.userApplication;
import com.example.demo.application.userApplication.CreateOrUpdateUser;
import com.example.demo.application.userApplication.UserDTO;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/v1/users")
@RestController
public class UserController {
    private final userApplication userApplication;

    @Autowired
    public UserController(final userApplication userApplication) {
        this.userApplication = userApplication;
    }

   @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody final CreateOrUpdateUser dto) {
        UserDTO userDTO = this.userApplication.add(dto);

        return ResponseEntity.status(201).body(userDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,  path = "/{id}") 
    public ResponseEntity<?> get(@PathVariable UUID id) {
        UserDTO userDTO = this.userApplication.get(id);

        return ResponseEntity.ok(userDTO);
    } 

    @Transactional
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody CreateOrUpdateUser dto) {
        this.userApplication.update(id, dto);
        return ResponseEntity.ok(dto);
    }  
    

}