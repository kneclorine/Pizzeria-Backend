package com.example.demo.application.ingredientApplication;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientProjection;
import com.example.demo.domain.ingredientDomain.IngredientReadRepository;
import com.example.demo.domain.ingredientDomain.IngredientWriteRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientApplicationImp implements IngredientApplication {

    private final IngredientWriteRepository ingredientWriteRepository;
    private final IngredientReadRepository ingredientReadRepository;
    private final ModelMapper modelMapper = new ModelMapper(); //TODO: Preuntar a juancarlos
    private final Logger logger;

    @Autowired
    public IngredientApplicationImp(final IngredientWriteRepository ingredientWriteRepository,
                                    final IngredientReadRepository ingredientReadRepository,
                                    final Logger logger){

        this.ingredientWriteRepository = ingredientWriteRepository;
        this.ingredientReadRepository = ingredientReadRepository;
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

        Ingredient ingredient = this.ingredientReadRepository.findById(id).orElseThrow();

        return this.modelMapper.map(ingredient, IngredientDTO.class);
    }

    @Override
    public void update(UUID id, CreateOrUpdateIngredientDTO dtos) {

        Ingredient ingredient = this.ingredientReadRepository.findById(id).orElseThrow();
        ingredient.setId(id);
        ingredient.setName(dtos.getName());
        ingredient.setPrice(dtos.getPrice());

        // TODO: Validar nombre no duplicado || Select count(*) from ingredients where name = ? || Si devuelve 1 o mas, metemos un throw propio.

        this.ingredientWriteRepository.update(ingredient);
        logger.info("Ingredient"+"updated succesfully."); 
    }

    @Override
    public void delete(UUID id) {

        Ingredient ingredient = this.ingredientReadRepository.findById(id).orElseThrow();
        this.ingredientWriteRepository.delete(ingredient);
        logger.info("Ingredient"+"deleted succesfully."); 
    }

    @Override
    public List<IngredientProjection> getAll(String name, int page, int size) {
        return this.getAll(name, page, size);
    }
}
