package com.example.demo.application.pizzaApplication;

import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;

import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.pizzaDomain.PizzaRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class PizzaApplicationImp implements PizzaApplication{

    private final PizzaRepository pizzaRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public PizzaApplicationImp(final PizzaRepository pizzaRepository, final Logger logger){
        this.pizzaRepository = pizzaRepository;
        this.modelMapper = new ModelMapper();
        this.logger = logger;
    }

    @Override
    public PizzaDTO add(CreateOrUpdatePizzaDTO dto) {

        Pizza pizza = modelMapper.map(dto, Pizza.class);
        pizza.setId(UUID.randomUUID());
        pizza.validate();
        
        this.pizzaRepository.add(pizza);
        logger.info("pizza added succesfully");

        return modelMapper.map(pizza, PizzaDTO.class);
    }
    
}
