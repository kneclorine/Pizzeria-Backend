package com.example.demo.application.ingredientApplication;

import com.example.demo.domain.ingredientDomain.Ingredient;

import org.modelmapper.ModelMapper;

public class IngredientService {

    public static Ingredient createIngredient(CreateOrUpdateIngredientDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Ingredient ingredient = modelMapper.map(dto, Ingredient.class);
        modelMapper.validate();

        return ingredient;
    }

    public static IngredientDTO createIngredientDTO(Ingredient ingredient){

        ModelMapper modelMapper = new ModelMapper();
        IngredientDTO ingredientDTO = modelMapper.map(ingredient, IngredientDTO.class);
        modelMapper.validate();

        return ingredientDTO;
    }

}
