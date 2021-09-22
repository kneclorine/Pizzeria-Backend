package com.example.demo.application.pizzaApplication;

import java.util.UUID;

import reactor.core.publisher.Mono;

public interface PizzaApplication {
    
    public Mono<PizzaDTO> add(CreateOrUpdatePizzaDTO dto);
    public Mono<PizzaDTO> get(UUID id);
}
