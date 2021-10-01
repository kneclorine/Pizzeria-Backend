package com.example.demo.application.userApplication;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.userDomain.UserProjection;

public interface UserApplication {

    public UserDTO add(CreateUserDTO dto);
    public UserDTO login(LoginUserDTO dto);
    public UserDTO get(UUID id);
    public UserDTO update(UUID id, UpdateUserDTO dto);
    public void delete(UUID id);
    public List<UserProjection> getAll(String name,  int page, int size);
}