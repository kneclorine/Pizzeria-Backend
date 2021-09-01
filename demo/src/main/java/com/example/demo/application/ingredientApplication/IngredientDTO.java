package com.example.demo.application.ingredientApplication;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class IngredientDTO{
    
    @NotBlank
    private UUID id;
    
    private String name;

     private BigDecimal price;
}
