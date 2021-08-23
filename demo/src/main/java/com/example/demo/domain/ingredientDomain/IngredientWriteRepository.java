package com.example.demo.domain.ingredientDomain;

import java.util.UUID;
import java.util.Optional;

public interface IngredientWriteRepository {

    public void add(Ingredient ingredient);
    public Optional<Ingredient> findById(UUID id);
}
