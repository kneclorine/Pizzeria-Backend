package com.example.demo.domain.ingredientDomain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.domain.Entities;
import com.example.demo.domain.PizzaIngredient;

@Entity
public class Ingredient extends Entities{

    @NotNull @Size(min=2, max=255)
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false, precision = 5, scale = 2)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;
    
    @OneToMany(mappedBy = "ingredient")
    private Set<PizzaIngredient> pizzaIngredients = new HashSet<PizzaIngredient>();

    public Ingredient() {
    }

    public Ingredient(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public BigDecimal getPrice(){
        return this.price;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }
}
