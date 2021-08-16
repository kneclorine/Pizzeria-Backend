package com.example.demo.application.ingredientApplication;

import java.io.Serializable;
import java.util.UUID;

public class IngredientDTO implements Serializable{
    
    public UUID id;
    public String name;
    public double price;
}
