package com.example.demo.application.pizzaApplication;

import java.util.UUID;

public interface PizzaApplication {
    
    public PizzaDTO add(CreateOrUpdatePizzaDTO dto);
    public PizzaDTO get(UUID id);
}
