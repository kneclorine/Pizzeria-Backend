package com.example.demo.domain;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import sdre.domain.Ingredient;
import sdre.domain.Pizza;

@Projection(name = "euro", types = { Pizza.class })
public interface EuroProjection {

  String getName();

  @Value("#{target.price} €")
  String getPrice();

  Set<Ingredient> getIngredients();
}