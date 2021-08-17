package com.example.demo.application.ingredientApplication;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class IngredientDTO implements Serializable{
    
    public UUID id;
    public String name;
    public BigDecimal price;
}
