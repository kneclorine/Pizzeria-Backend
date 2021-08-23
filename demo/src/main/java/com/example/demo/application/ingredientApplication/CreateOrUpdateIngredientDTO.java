package com.example.demo.application.ingredientApplication;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class CreateOrUpdateIngredientDTO {
    
    @NotNull @Size(min=2, max=255)
    private String name;

    @NotNull @Digits(integer = 5, fraction = 2)
    private BigDecimal price;
}
