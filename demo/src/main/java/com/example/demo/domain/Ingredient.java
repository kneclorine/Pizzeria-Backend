package com.example.demo.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Ingredient extends Entities{

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

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
