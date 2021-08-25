package com.example.demo.domain.ingredientDomain;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

public interface IngredientReadRepository {
    public Optional<Ingredient> findById(UUID id);
    
    public List<IngredientProjection> getAll(String name, int page, int size);
}
