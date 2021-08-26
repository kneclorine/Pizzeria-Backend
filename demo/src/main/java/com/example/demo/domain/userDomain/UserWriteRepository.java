package com.example.demo.domain.userDomain;

public interface UserWriteRepository {

    public void add(User user);
    public void update(User user); 
    public void delete(User user); 
}
