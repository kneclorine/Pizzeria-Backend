package com.example.demo.domain.ingredientDomain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientRepository {

    public void add(Ingredient ingredient);
    public void update(Ingredient ingredient);
    public void delete(Ingredient ingredient);
    public Optional<Ingredient> get(UUID id);
    public List<Ingredient> getAll(String name, BigDecimal price, int page, int size);
}
