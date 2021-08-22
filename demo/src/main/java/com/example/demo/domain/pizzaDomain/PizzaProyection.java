package com.example.demo.domain.pizzaDomain;

import java.util.Set;
import java.math.BigDecimal;
import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;

public interface PizzaProyection {
    public UUID getId();
    public String getName();
    public String getUrl();
    public BigDecimal getPrice();
    public Set<Ingredient> getIngredients();
}
