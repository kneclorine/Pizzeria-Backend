package com.example.demo.application.ingredientApplication;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.example.demo.DemoApplication;
import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientProyection;
import com.example.demo.domain.ingredientDomain.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngredientApplicationImp implements IngredientApplication {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientApplicationImp(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Transactional
    @Override
    public IngredientDTO add(CreateOrUpdateIngredientDTO dto) {

        Ingredient ingredient = IngredientService.createIngredient(dto);
        this.ingredientRepository.add(ingredient);
        
        DemoApplication.logger.info("Ingredient added!!!!");

        return IngredientService.createIngredientDTO(ingredient);
    }

   
}
