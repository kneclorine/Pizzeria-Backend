package com.example.demo.application.userApplication;

import java.util.UUID;

public interface UserApplication {

    public UserDTO add(CreateUserDTO dto);
    public UserDTO get(UUID id);
    public UserDTO update(UUID id, UpdateUserDTO dto);
    public void delete(UUID id);
}