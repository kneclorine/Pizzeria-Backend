package com.example.demo.infraestructure.pizzaInfraestructure;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.example.demo.domain.pizzaDomain.Pizza;
import com.example.demo.domain.pizzaDomain.PizzaProyection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PizzaJPARepository extends JpaRepository<Pizza, UUID> {

  Pizza findByName(@Param("name") String name);

  @Query("SELECT p FROM Pizza p WHERE (:name IS NULL OR p.name LIKE :name) OR (:price IS NULL OR p.price >= :price)")
  List<PizzaProyection> findByCriteria(@Param("name") String name, @Param("price") BigDecimal price, Pageable pageable);
}
