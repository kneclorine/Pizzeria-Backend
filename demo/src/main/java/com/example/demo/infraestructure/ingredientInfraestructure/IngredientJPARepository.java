package com.example.demo.infraestructure.ingredientInfraestructure;

import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientProjection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IngredientJPARepository extends ReactiveCrudRepository<Ingredient, UUID>{

    @Query("SELECT i.id as id, i.name as name, i.price as price FROM Ingredient i WHERE (:name is NULL OR name LIKE %:name%)")
    Flux<IngredientProjection> findByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(i)>0 THEN true ELSE false END FROM Ingredient i WHERE i.name = :name")
    Mono<Boolean> existsByName(@Param("name") String name);
}
