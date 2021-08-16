package com.example.demo.application.pizzaApplication;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.example.demo.application.ingredientApplication.IngredientDTO;

public class PizzaDTO implements Serializable{
    
    public UUID id;
    public String name;
    public double price;
    public Set<IngredientDTO> ingredients = new HashSet<IngredientDTO>();
}
