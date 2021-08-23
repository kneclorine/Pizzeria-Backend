package com.example.demo.application.ingredientApplication;

import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientApplicationImp implements IngredientApplication {

    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public IngredientApplicationImp(final IngredientRepository ingredientRepository, final Logger logger){
        this.ingredientRepository = ingredientRepository;
        this.modelMapper = new ModelMapper();
        this.logger = logger;
    }

    @Override
    public IngredientDTO add(CreateOrUpdateIngredientDTO dto) {

        Ingredient ingredient = modelMapper.map(dto, Ingredient.class);
        ingredient.setId(UUID.randomUUID());
        ingredient.validate();

        this.ingredientRepository.add(ingredient);
        logger.info("Ingredient added succesfully.");

        return modelMapper.map(ingredient, IngredientDTO.class);
    }
}
