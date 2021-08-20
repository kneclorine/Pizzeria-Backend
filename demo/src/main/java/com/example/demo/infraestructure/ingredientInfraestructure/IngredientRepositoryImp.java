package com.example.demo.infraestructure.ingredientInfraestructure;

import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
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
}
