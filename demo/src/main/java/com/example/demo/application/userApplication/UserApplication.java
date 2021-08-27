package com.example.demo.application.userApplication;

import java.util.UUID;

public interface UserApplication {

    public UserDTO add(CreateOrUpdateUserDTO dto);
    public UserDTO get(UUID id);
    public UserDTO update(UUID id, CreateOrUpdateUserDTO dto);
    public void delete(UUID id);
}