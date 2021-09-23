package com.example.demo.domain.ingredientDomain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.core.EntityBase;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table("ingredient")
public @NoArgsConstructor @Getter @Setter class Ingredient extends EntityBase{

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull @Digits(integer = 3, fraction = 2) @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal price;

    @Override
    public String toString() {

        return String.format("Ingredient {id: %s, name: %s, price: %s}", this.getId(), this.getName(), this.getPrice());
    }

    @Override
    public boolean isNew() {
        // TODO Auto-generated method stub
        return false;
    }
}