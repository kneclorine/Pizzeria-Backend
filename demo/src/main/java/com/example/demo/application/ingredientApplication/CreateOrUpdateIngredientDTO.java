package com.example.demo.application.ingredientApplication;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class CreateOrUpdateIngredientDTO {
    
    public String name;
    public BigDecimal price;
}
