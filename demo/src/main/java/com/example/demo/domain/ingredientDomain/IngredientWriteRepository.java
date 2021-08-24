package com.example.demo.domain.ingredientDomain;

public interface IngredientWriteRepository {

    public void add(Ingredient ingredient);
    public void update(Ingredient ingredient);
    public void delete(Ingredient ingredient);
}
