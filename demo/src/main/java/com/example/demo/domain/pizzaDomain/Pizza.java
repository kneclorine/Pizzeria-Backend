package com.example.demo.domain.pizzaDomain;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.example.demo.core.EntityBase;
import com.example.demo.domain.imageDomain.Image;
import com.example.demo.domain.ingredientDomain.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public @NoArgsConstructor @AllArgsConstructor @Getter @Setter class Pizza extends EntityBase{

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @Transient
    private BigDecimal price;

    @Embedded
    private Image image;

    @ManyToMany @JoinTable(name = "pizzaIngredient", joinColumns = @JoinColumn(name="ingredient_id"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private final Set<Ingredient> ingredients = new HashSet<Ingredient>();

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
    }
}
