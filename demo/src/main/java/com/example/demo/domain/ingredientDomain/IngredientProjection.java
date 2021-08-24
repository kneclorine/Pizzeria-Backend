package com.example.demo.domain.ingredientDomain;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public interface IngredientProjection {

    @NotBlank
    public UUID getId();

    @NotBlank @Size(min=2, max=255)
    public String getName();

    @NotNull @Digits(integer = 5, fraction = 2) @DecimalMin(value = "0.0", inclusive = false)
    public BigDecimal getPrice();
}
