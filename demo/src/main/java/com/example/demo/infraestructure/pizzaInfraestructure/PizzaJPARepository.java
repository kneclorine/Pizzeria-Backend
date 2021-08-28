package com.example.demo.infraestructure.pizzaInfraestructure;

import java.util.UUID;

import com.example.demo.domain.pizzaDomain.Pizza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PizzaJPARepository extends JpaRepository<Pizza, UUID> {

  Pizza findByName(@Param("name") String name);

  @Query("SELECT CASE WHEN COUNT(p)>0 THEN true ELSE false END FROM Pizza p WHERE p.name = :name")
    boolean exists(@Param("name") String name);
}
