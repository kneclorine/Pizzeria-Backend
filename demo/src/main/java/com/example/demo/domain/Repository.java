package com.example.demo.domain;

import java.util.List;
import java.util.UUID;

public interface Repository <T>{
    public void add(T type);
    public void update(T type);
    public void delete(T type);
    public T get(UUID id);
    public List<T> getAll();
}
