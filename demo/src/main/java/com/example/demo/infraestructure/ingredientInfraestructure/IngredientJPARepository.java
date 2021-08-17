package com.example.demo.infraestructure.ingredientInfraestructure;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IngredientJPARepository extends CrudRepository<Ingredient, UUID>{
    
    Ingredient findByName(@Param("name") String name);

    @Query("SELECT i FROM Ingredient i WHERE (:name IS NULL OR i.name LIKE :name) OR (:price IS NULL OR i.price >= :price)")
    List<Ingredient> findByCriteria(@Param("name") String name, @Param("price") BigDecimal price, Pageable pageable);
}
