package com.example.demo.domain.ingredientDomain;

import com.example.demo.core.FunctionalInterfaces.ExistsByField;
import com.example.demo.core.FunctionalInterfaces.FindById;

import java.util.Optional;
import java.util.UUID;

public interface IngredientWriteRepository extends FindById<Ingredient, UUID>, ExistsByField {
    public void add(Ingredient ingredient);
    public void update(Ingredient ingredient);
    public void delete(Ingredient ingredient);
    
}
