package com.example.demo.infraestructure.ingredientInfraestructure;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientProjection;
import com.example.demo.domain.ingredientDomain.IngredientReadRepository;
import com.example.demo.domain.ingredientDomain.IngredientWriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientRepositoryImp implements IngredientWriteRepository, IngredientReadRepository {

    
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
    public Optional<Ingredient> findById(UUID id) {
        return this.ingredientJPARepository.findById(id);
    }

    @Override
    public boolean exists(String name) {
        return this.ingredientJPARepository.exists(name);
    }

    @Override
    public List<IngredientProjection> getAll(String name, int page, int size) {
        return this.ingredientJPARepository.findByCriteria(name,
        PageRequest.of(page, size));
    }

    @Override
    public void update(Ingredient ingredient) {
        this.ingredientJPARepository.save(ingredient);
    }

   @Override
    public void delete(Ingredient ingredient) {
        this.ingredientJPARepository.delete(ingredient);
    }

}
