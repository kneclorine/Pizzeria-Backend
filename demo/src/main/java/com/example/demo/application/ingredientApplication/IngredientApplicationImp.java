package com.example.demo.application.ingredientApplication;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.example.demo.DemoApplication;
import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientProyection;
import com.example.demo.domain.ingredientDomain.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class IngredientApplicationImp implements IngredientApplication {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientApplicationImp(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientDTO add(@Valid CreateOrUpdateIngredientDTO dto) {

        Ingredient ingredient = IngredientService.createIngredient(dto);
        this.ingredientRepository.add(ingredient);
        
        DemoApplication.logger.info("Ingredient added!!!!");

        return IngredientService.createIngredientDTO(ingredient);
    }

    @Override
    public IngredientDTO get(@Valid UUID id) {

        Ingredient ingredient = this.ingredientRepository.get(id).orElseThrow();
        DemoApplication.logger.info("Ingredient founded!!!!");

        return IngredientService.createIngredientDTO(ingredient);
    }

    @Override
    public void update(@Valid UUID id, @Valid CreateOrUpdateIngredientDTO dto) {

        Ingredient ingredient = this.ingredientRepository.get(id).orElseThrow();
        ingredient.setName(dto.getName());
        ingredient.setPrice(dto.getPrice());
        this.ingredientRepository.update(ingredient);
        DemoApplication.logger.info("Ingredient updated!!!!");
    }

    @Override
    public void delete(@Valid UUID id) {

        Ingredient ingredient = this.ingredientRepository.get(id).orElseThrow();
        this.ingredientRepository.delete(ingredient);
        
        DemoApplication.logger.info("Ingredient deleted!!!!");
    }

    @Override
    public List<IngredientProyection> getAll(@Valid String name, @Valid BigDecimal price, @Valid int page, @Valid int size) {
        
        DemoApplication.logger.info("Ingredients founded!!!!");
        return this.ingredientRepository.getAll(name, price, page, size);
    }   
}
