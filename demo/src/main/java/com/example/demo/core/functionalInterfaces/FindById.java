package com.example.demo.core.functionalInterfaces;

import java.util.Optional;

public interface FindById<T, ID> {
    public Optional<T> findById(ID id);
}
