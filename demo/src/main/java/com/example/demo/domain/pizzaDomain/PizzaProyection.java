package com.example.demo.domain.pizzaDomain;

import java.util.Set;
import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.domain.ingredientDomain.Ingredient;

public interface PizzaProyection {

    @NotBlank
    public UUID getId();

    @NotBlank @Size(min=2, max=255)
    public String getName();

    @NotBlank @Size(min=2, max=255)
    public String getUrl();

    @NotNull @Digits(integer = 5, fraction = 2) @DecimalMin(value = "0.0", inclusive = false)
    public BigDecimal getPrice();


    public Set<Ingredient> getIngredients();
}
