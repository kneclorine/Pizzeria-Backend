package com.example.demo.domain.ingredientDomain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.core.Entities;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
public @NoArgsConstructor @Getter @Setter class Ingredient extends Entities{

    @NotBlank @Size(min=2, max=255)
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull @Digits(integer = 5, fraction = 2) @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal price;
}
