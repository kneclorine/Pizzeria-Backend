package com.example.demo.infraestructure.pizzaInfraestructure;

import java.util.UUID;

import com.example.demo.domain.pizzaDomain.Pizza;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;

public interface PizzaJPARepository extends ReactiveCrudRepository<Pizza, UUID> {

  Mono<Pizza> findByName(@Param("name") String name);

  @Query("SELECT CASE WHEN COUNT(p)>0 THEN true ELSE false END FROM Pizza p WHERE p.name = :name")
  Mono<Boolean> existsByName(@Param("name") String name);
}
