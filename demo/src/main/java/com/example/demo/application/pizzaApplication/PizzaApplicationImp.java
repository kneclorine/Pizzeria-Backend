package com.example.demo.application.pizzaApplication;

import java.util.UUID;

import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientWriteRepository;
import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.pizzaDomain.PizzaWriteRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaApplicationImp extends ApplicationBase<Pizza, UUID> implements PizzaApplication{

    private final PizzaWriteRepository pizzaWriteRepository;
    private final IngredientWriteRepository ingredientWriteRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final Logger logger;

    @Autowired
    public PizzaApplicationImp(final PizzaWriteRepository pizzaWriteRepository,
                                final IngredientWriteRepository ingredientWriteRepository,
                                final Logger logger){
                                
        super((id) -> pizzaWriteRepository.findById(id));

        this.pizzaWriteRepository = pizzaWriteRepository;
        this.ingredientWriteRepository = ingredientWriteRepository;
        this.logger = logger;
    }

    @Override
    public PizzaDTO add(CreateOrUpdatePizzaDTO dto) {

        //TODO: Implementar la validacion tnato en la pizza como en ingredeinte
        Pizza pizza = this.modelMapper.map(dto, Pizza.class);
        pizza.setId(UUID.randomUUID());
        for(UUID ingredientId : dto.getIngredients()){

            Ingredient ingredient = this.ingredientWriteRepository.findById(ingredientId).orElseThrow();
            pizza.addIngredient(ingredient);
        }

        this.pizzaWriteRepository.add(pizza);
        logger.info(this.serializeObject(pizza, "added"));
        return this.modelMapper.map(pizza, PizzaDTO.class);
    }
}
