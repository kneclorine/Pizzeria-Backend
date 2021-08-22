package com.example.demo.application.pizzaApplication;

import java.math.BigDecimal;
import java.util.Set;
import com.example.demo.domain.ingredientDomain.Ingredient;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class CreateOrUpdatePizzaDTO {
    public String name;
    public String url;
    public BigDecimal price;
    public Set<Ingredient> Ingredients;
}
