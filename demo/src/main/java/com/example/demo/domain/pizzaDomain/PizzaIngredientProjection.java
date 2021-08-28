package com.example.demo.domain.pizzaDomain;

import java.util.Set;
import java.util.UUID;

public interface PizzaIngredientProjection {
    
    public UUID getId();

    public String getName();

    public Image getImage();

    public Set<Ingredient> getIngredients();

    public interface Image {

        public String getPublic_id();
    }

    public interface Ingredient {

        public UUID getId();

        public String getName();
    }
}
