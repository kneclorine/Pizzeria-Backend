package com.example.demo.domain.ingredientDomain;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, UUID> {
}
