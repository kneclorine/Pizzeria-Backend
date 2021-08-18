package com.example.demo.application.ingredientApplication;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientProyection;
import com.example.demo.domain.ingredientDomain.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class IngredientApplicationImp implements IngredientApplication {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientApplicationImp(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientDTO add(CreateOrUpdateIngredientDTO dto) {

        Ingredient ingredient = IngredientService.createIngredient(dto);
        this.ingredientRepository.add(ingredient);

        return IngredientService.createIngredientDTO(ingredient);
    }

    @Override
    public IngredientDTO get(UUID id) {

        Ingredient ingredient = this.ingredientRepository.get(id).orElseThrow();

        return IngredientService.createIngredientDTO(ingredient);
    }

    @Override
    public void update(UUID id, CreateOrUpdateIngredientDTO dto) {

        Ingredient ingredient = this.ingredientRepository.get(id).orElseThrow();
        ingredient.setName(dto.name);
        ingredient.setPrice(dto.price);
        this.ingredientRepository.update(ingredient);
    }

    @Override
    public void delete(UUID id) {

        Ingredient ingredient = this.ingredientRepository.get(id).orElseThrow();
        this.ingredientRepository.delete(ingredient);
    }

    @Override
    public List<IngredientProyection> getAll(String name, BigDecimal price, int page, int size) {
        return this.ingredientRepository.getAll(name, price, page, size);
    }   
}
