package com.example.demo.application.ingredientApplication;

import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientWriteRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientApplicationImp implements IngredientApplication {

    private final IngredientWriteRepository ingredientWriteRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public IngredientApplicationImp(final IngredientWriteRepository ingredientWriteRepository,
                                    final Logger logger){

        this.ingredientWriteRepository = ingredientWriteRepository;
        this.modelMapper = new ModelMapper(); // No insertar aqui, preuntar a juancarlos
        this.logger = logger;
    }

    @Override
    public IngredientDTO add(CreateOrUpdateIngredientDTO dto) {

        Ingredient ingredient = modelMapper.map(dto, Ingredient.class);
        ingredient.setId(UUID.randomUUID());
        // TODO: Validar nombre no duplicado || Select count(*) from ingredients where name = ? || Si devuelve 1 o mas, metemos un throw propio.
        
        ingredient.validate();

        this.ingredientWriteRepository.add(ingredient);
        logger.info("Ingredient"+"added succesfully."); // Paso datos del ingrediente en formato json

        return modelMapper.map(ingredient, IngredientDTO.class);
    }

    @Override
    public IngredientDTO get(UUID id) {

        Ingredient ingredient = this.ingredientWriteRepository.findById(id).orElseThrow();

        return this.modelMapper.map(ingredient, IngredientDTO.class);
    }
}
