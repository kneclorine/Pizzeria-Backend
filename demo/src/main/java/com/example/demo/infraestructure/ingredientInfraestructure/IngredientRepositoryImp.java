package com.example.demo.infraestructure.ingredientInfraestructure;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class IngredientRepositoryImp implements IngredientRepository {

    
    private final IngredientJPARepository ingredientJPARepository;

    @Autowired
    public IngredientRepositoryImp(IngredientJPARepository ingredientJPARepository){
        this.ingredientJPARepository = ingredientJPARepository;
    }

    @Override
    public void add(Ingredient ingredient) {
        this.ingredientJPARepository.save(ingredient);
    }

    @Override
    public void update(Ingredient ingredient) {
        this.ingredientJPARepository.save(ingredient);
    }

    @Override
    public void delete(Ingredient ingredient) {
        this.ingredientJPARepository.delete(ingredient);
    }

    @Override
    public Optional<Ingredient> get(UUID id) {
        return this.ingredientJPARepository.findById(id);
    }

    @Override
    public List<Ingredient> getAll(String name, BigDecimal price, int page, int size) {
        return this.ingredientJPARepository.findByCriteria(
            name, price,
            PageRequest.of(page, size, Sort.by("name").descending())
        );
    }
}
