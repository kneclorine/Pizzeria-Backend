package com.example.demo.application.pizzaApplication;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class PizzaDTO {

    @NotNull
    private UUID id;
    
    @NotNull @Size(min=2, max=255)
    private String name;

    @NotNull @Size(min=2, max=255)
    private String url;

    @NotNull @Digits(integer = 5, fraction = 2)
    private BigDecimal price;

    @NotNull
    private Set<Ingredient> Ingredients;

}
