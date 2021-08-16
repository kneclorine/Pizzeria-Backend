package com.example.demo.domain;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PizzaRepository extends PagingAndSortingRepository<Pizza, Long> {

  Pizza findByName(@Param("name") String name);

  @Query("SELECT p FROM Pizza p WHERE (:name IS NULL OR p.name LIKE :name) OR (:price IS NULL OR p.price >= :price)")
  List<Pizza> findByCriteria(@Param("name") String name, @Param("price") BigDecimal price);
}
