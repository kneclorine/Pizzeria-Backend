package com.example.demo.application.pizzaApplication;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class PizzaDTO {

    private UUID id;
    
    private String name;

    private BigDecimal price;

    private Set<Ingredient> Ingredients = new HashSet<Ingredient>();
}
