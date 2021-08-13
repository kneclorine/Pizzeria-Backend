package com.example.demo.infraestructure.ingredientInfraestructure;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.repositoryDomain.Repository;

public class IngredientRepositoryImp implements Repository<Ingredient> {

    @Override
    public void add(Ingredient ingredient) {
    }

    @Override
    public void update(Ingredient ingredient) {
    }

    @Override
    public void delete(Ingredient ingredient) {
    }

    @Override
    public Ingredient get(UUID id) {
        return null;
    }

    @Override
    public List<Ingredient> getAll() {
        return null;
    }
}
