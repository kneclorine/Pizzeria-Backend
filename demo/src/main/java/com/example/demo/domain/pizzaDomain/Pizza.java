package com.example.demo.domain.pizzaDomain;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.core.Entities;
import com.example.demo.domain.ingredientDomain.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public @NoArgsConstructor @AllArgsConstructor @Getter @Setter class Pizza extends Entities{

    @NotNull @Size(min = 2, max = 255)
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull @Size(max = 255)
    @Column(nullable = false)
    private String url;

    @NotNull
    @Column(nullable = false, precision = 5, scale = 2) @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;

    @Transient
    private final Set<Ingredient> ingredients = new HashSet<Ingredient>();
}
