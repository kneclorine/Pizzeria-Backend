package com.example.demo.domain.pizzaDomain;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.core.EntityBase;
import com.example.demo.domain.ingredientDomain.Ingredient;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table("pizza")
public @NoArgsConstructor @AllArgsConstructor @Getter @Setter class Pizza extends EntityBase{

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull @Digits(integer = 3, fraction = 2) @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal price;

    @NotNull
    private UUID image;

    @ManyToMany @JoinTable(name = "pizzaIngredient", joinColumns = @JoinColumn(name="ingredient_id"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private final Set<Ingredient> ingredients = new HashSet<Ingredient>();

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
    }

    public BigDecimal calculatePrice(){

        BigDecimal totalPrice = new BigDecimal(0.00);
        BigDecimal profit = new BigDecimal(1.20);

        for(Ingredient ingredient : ingredients){
            totalPrice = totalPrice.add(ingredient.getPrice());
        }
        totalPrice = totalPrice.multiply(profit);
        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_EVEN);

        return totalPrice;
    }

    @Override
    public String toString() {
        return String.format("Pizza {id: %s, name: %s, price: %s, with ingredients:[%s]}", this.getId(), this.getName(), this.getPrice(), this.getIngredients().toString());
    }

    @Override
    public boolean isNew() {
        return this.isThisNew();
    }
}
