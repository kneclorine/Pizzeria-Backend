package com.example.demo.domain.ingredientDomain;

import com.example.demo.core.functionalInterfaces.ExistsByField;
import com.example.demo.core.functionalInterfaces.FindById;

import java.util.UUID;

public interface IngredientWriteRepository extends FindById<Ingredient, UUID>, ExistsByField {

    public void add(Ingredient ingredient);
    public void update(Ingredient ingredient);
    public void delete(Ingredient ingredient);
}
