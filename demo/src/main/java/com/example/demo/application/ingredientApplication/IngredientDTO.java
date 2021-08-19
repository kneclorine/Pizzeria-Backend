package com.example.demo.application.ingredientApplication;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class IngredientDTO implements Serializable{
    
    private UUID id;
    private String name;
    private BigDecimal price;

    public UUID getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public BigDecimal getPrice(){
        return this.price;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
