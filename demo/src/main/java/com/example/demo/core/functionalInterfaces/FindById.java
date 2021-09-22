package com.example.demo.core.functionalInterfaces;

import reactor.core.publisher.Mono;

public interface FindById<T, ID> {
    public Mono<T> findById(ID id);
}
