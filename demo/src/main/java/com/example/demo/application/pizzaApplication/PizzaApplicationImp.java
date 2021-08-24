package com.example.demo.application.pizzaApplication;

import java.util.UUID;


import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.pizzaDomain.PizzaReadRepository;
import com.example.demo.domain.pizzaDomain.PizzaWriteRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaApplicationImp implements PizzaApplication{

    private final PizzaWriteRepository pizzaWriteRepository;
    private final PizzaReadRepository pizzaReadRepository;
    private final ModelMapper modelMapper = new ModelMapper();;
    private final Logger logger;

    @Autowired
    public PizzaApplicationImp(final PizzaWriteRepository pizzaWriteRepository,
                                final PizzaReadRepository pizzaReadRepository,
                                final Logger logger){
        this.pizzaWriteRepository = pizzaWriteRepository;
        this.pizzaReadRepository = pizzaReadRepository;
        this.logger = logger;

    }

    @Override
    public PizzaDTO add(CreateOrUpdatePizzaDTO dto) {

        Pizza pizza = modelMapper.map(dto, Pizza.class);
        pizza.setId(UUID.randomUUID());
        pizza.validate();

        
        this.pizzaWriteRepository.add(pizza);
        logger.info("pizza added succesfully");

        return modelMapper.map(pizza, PizzaDTO.class);
    }
    
}
