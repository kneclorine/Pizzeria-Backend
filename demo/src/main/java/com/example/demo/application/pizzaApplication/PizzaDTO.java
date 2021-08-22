package com.example.demo.application.pizzaApplication;

import java.math.BigDecimal;
import java.util.Set;
import com.example.demo.domain.ingredientDomain.Ingredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class PizzaDTO {
    
    private String name;
    private String url;
    private BigDecimal price;
    private Set<Ingredient> Ingredients;

}
