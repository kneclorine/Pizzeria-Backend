package com.example.demo.application.ingredientApplication;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class IngredientDTO{
    
    @NotNull
    private UUID id;
    
    @NotNull @Size(min=2, max=255)
    private String name;

    @NotNull @Digits(integer = 5, fraction = 2)
    private BigDecimal price;
}
