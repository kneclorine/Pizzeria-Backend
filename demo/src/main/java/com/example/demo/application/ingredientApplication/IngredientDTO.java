package com.example.demo.application.ingredientApplication;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class IngredientDTO {
    
    public UUID id;
    public String name;
    public BigDecimal price;
}
