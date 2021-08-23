package com.example.demo.application.ingredientApplication;

import java.util.UUID;


public interface IngredientApplication {
    
    public IngredientDTO add(CreateOrUpdateIngredientDTO dto);
    public IngredientDTO get(UUID id);
}
