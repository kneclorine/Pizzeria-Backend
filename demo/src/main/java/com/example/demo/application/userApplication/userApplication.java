package com.example.demo.application.userApplication;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.Entities;
import com.example.demo.domain.userDomain.User;
import com.example.demo.domain.userDomain.UserProjection;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/users")
public interface userApplication {
    public UserDTO add(CreateOrUpdateUser dto);

    public void update(UUID id, CreateOrUpdateUser dtos);

    public UserDTO get(UUID id);

    
}
