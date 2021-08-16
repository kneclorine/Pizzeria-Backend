package com.example.demo.application.userApplication;

import java.io.Serializable;
import java.util.UUID;

public class UserDTO implements Serializable{
    
    public UUID id;
    public String name;
    public String lastName;
    public String email;
    public String password;
}
