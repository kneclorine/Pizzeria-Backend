package com.example.demo.infraestructure.ingredientInfraestructure;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientProjection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IngredientJPARepository extends JpaRepository<Ingredient, UUID>{
    
    Ingredient findByName(@Param("name") String name);

    @Query("SELECT i FROM Ingredient i WHERE (:name IS NULL OR i.name LIKE :name)")
    List<IngredientProjection> findByCriteria(@Param("name") String name, Pageable pageable);
}
