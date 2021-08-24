package com.example.demo.application.pizzaApplication;

import java.math.BigDecimal;
import java.util.Set;
import com.example.demo.domain.ingredientDomain.Ingredient;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Validated
public @Getter @Setter class CreateOrUpdatePizzaDTO {

    @NotNull @Size(min=2, max=255)
    public String name;

    @NotNull @Size(min=2, max=255)
    public String url;

    @NotNull @Digits(integer = 5, fraction = 2) @DecimalMin(value = "0.0", inclusive = false)
    public BigDecimal price;

    @NotNull
    public Set<Ingredient> Ingredients;
}
