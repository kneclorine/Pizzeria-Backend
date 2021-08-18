package com.example.demo.application.ingredientApplication;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.example.demo.domain.ingredientDomain.IngredientProyection;

public interface IngredientApplication {
    
    public IngredientDTO add(CreateOrUpdateIngredientDTO dto);
    public IngredientDTO get(UUID id);
    public void update(UUID id, CreateOrUpdateIngredientDTO dto);
    public void delete(UUID id);
    public List<IngredientProyection> getAll(String name, BigDecimal price, int page, int size);
}
