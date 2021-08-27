package com.example.demo.controller;

import java.util.UUID;

import javax.validation.Valid;

import com.example.demo.application.userApplication.CreateUserDTO;
import com.example.demo.application.userApplication.UpdateUserDTO;
import com.example.demo.application.userApplication.UserApplication;
import com.example.demo.application.userApplication.UserDTO;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserApplication userApplication;

    @Autowired
    public UserController(final UserApplication userApplication){
        this.userApplication = userApplication;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody CreateUserDTO dto){
        UserDTO userDTO = this.userApplication.add(dto);

        return ResponseEntity.status(201).body(userDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,  path = "/{id}")
    public ResponseEntity<?> get(@Valid @PathVariable UUID id) {
        UserDTO userDTO = this.userApplication.get(id);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @Valid @RequestBody UpdateUserDTO dto) {
        UserDTO userDTO = this.userApplication.update(id, dto);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable UUID id) {
        this.userApplication.delete(id);
    }

}