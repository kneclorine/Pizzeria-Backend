package com.example.demo.domain.ingredientDomain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.core.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public @NoArgsConstructor @Getter @Setter class Ingredient extends Entities{

    @NotBlank @Size(min=2, max=255)
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank
    @Column(nullable = false, precision = 5, scale = 2)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;
}
