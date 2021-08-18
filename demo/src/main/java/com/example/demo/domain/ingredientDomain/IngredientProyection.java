package com.example.demo.domain.ingredientDomain;

import java.math.BigDecimal;
import java.util.UUID;

public interface IngredientProyection {
    public UUID getId();
    public String getName();
    public BigDecimal getPrice();
}
