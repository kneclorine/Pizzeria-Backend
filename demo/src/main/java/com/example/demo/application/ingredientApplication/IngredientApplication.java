package com.example.demo.application.ingredientApplication;

import java.util.UUID;

import com.example.demo.domain.ingredientDomain.IngredientProjection;

import java.util.List;


public interface IngredientApplication {
    
    public IngredientDTO add(CreateOrUpdateIngredientDTO dto);
    public IngredientDTO get(UUID id);
    public IngredientDTO update(UUID id, CreateOrUpdateIngredientDTO dto);
    public void delete(UUID id);
    public List<IngredientProjection> getAll(String name,  int page, int size);
}
