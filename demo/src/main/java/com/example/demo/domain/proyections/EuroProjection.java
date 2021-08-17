package com.example.demo.domain.proyections;

import java.util.Set;

import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.pizzaDomain.Pizza;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "euro", types = { Pizza.class })
public interface EuroProjection {

  String getName();

  @Value("#{target.price} €")
  String getPrice();

  Set<Ingredient> getIngredients();
}