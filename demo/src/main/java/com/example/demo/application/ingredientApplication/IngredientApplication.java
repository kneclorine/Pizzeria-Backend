package com.example.demo.application.ingredientApplication;

import java.util.UUID;

import com.example.demo.domain.ingredientDomain.IngredientProjection;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IngredientApplication {
    
    public Mono<IngredientDTO> add(CreateOrUpdateIngredientDTO dto);
    public Mono<IngredientDTO> get(UUID id);
    public Mono<IngredientDTO> update(UUID id, CreateOrUpdateIngredientDTO dto);
    public Mono<IngredientDTO> delete(UUID id);
    public Flux<IngredientProjection> getAll(String name,  int page, int size);
}
