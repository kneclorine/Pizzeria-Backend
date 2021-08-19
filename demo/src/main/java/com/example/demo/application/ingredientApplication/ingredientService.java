package com.example.demo.application.ingredientApplication;

import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.DemoApplication;

import org.modelmapper.ModelMapper;

public class IngredientService {

    public static Ingredient createIngredient(CreateOrUpdateIngredientDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Ingredient ingredient = modelMapper.map(dto, Ingredient.class);

        DemoApplication.logger.info("Ingredient Mapped from IngredientDTO.");

        return ingredient;
    }

    public static IngredientDTO createIngredientDTO(Ingredient ingredient){

        ModelMapper modelMapper = new ModelMapper();
        IngredientDTO ingredientDTO = modelMapper.map(ingredient, IngredientDTO.class);

        DemoApplication.logger.info("IngredientDTO Mapped from Ingredient.");

        return ingredientDTO;
    }

}
