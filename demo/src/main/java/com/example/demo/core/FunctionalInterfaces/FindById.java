package com.example.demo.core.FunctionalInterfaces;

import java.util.Optional;

public interface FindById<T, ID> {
    public Optional<T> findById(ID id);
}
