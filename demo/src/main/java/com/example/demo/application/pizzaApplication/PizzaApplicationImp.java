package com.example.demo.application.pizzaApplication;

import java.util.UUID;

import com.example.demo.application.ingredientApplication.IngredientApplicationImp;
import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.pizzaDomain.PizzaWriteRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaApplicationImp extends ApplicationBase<Pizza, UUID> implements PizzaApplication {

    private final PizzaWriteRepository pizzaWriteRepository;
    private final IngredientApplicationImp ingredientApplicationImp;
    private final ModelMapper modelMapper = new ModelMapper();
    private final Logger logger;

    @Autowired
    public PizzaApplicationImp(final PizzaWriteRepository pizzaWriteRepository,
            final IngredientApplicationImp ingredientApplicationImp, final Logger logger) {

        super((id) -> pizzaWriteRepository.findById(id));

        this.pizzaWriteRepository = pizzaWriteRepository;
        this.ingredientApplicationImp = ingredientApplicationImp;
        this.logger = logger;
    }

    @Override
    public PizzaDTO add(CreateOrUpdatePizzaDTO dto) {

        Pizza pizza = this.modelMapper.map(dto, Pizza.class);
        pizza.setId(UUID.randomUUID());
        for (UUID ingredientId : dto.getIngredients()) {

            Ingredient ingredient = this.modelMapper.map(ingredientApplicationImp.get(ingredientId), Ingredient.class);
            pizza.addIngredient(ingredient);
        }
        pizza.setPrice(pizza.calculatePrice());

        pizza.validate("name", pizza.getName(), (name) -> this.pizzaWriteRepository.exists(name));

        this.pizzaWriteRepository.add(pizza);
        logger.info(this.serializeObject(pizza, "added"));

        return this.modelMapper.map(pizza, PizzaDTO.class);
    }

    @Override
    public PizzaDTO get(UUID id) {
        
        Pizza pizza = this.findById(id);
        return this.modelMapper.map(pizza, PizzaDTO.class);
    }
}