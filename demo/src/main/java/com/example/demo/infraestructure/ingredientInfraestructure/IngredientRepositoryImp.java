package com.example.demo.infraestructure.ingredientInfraestructure;

import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.ingredientDomain.Ingredient;
import com.example.demo.domain.ingredientDomain.IngredientWriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientRepositoryImp implements IngredientWriteRepository {
    
    private final IngredientJPARepository ingredientJPARepository;

    @Autowired
    public IngredientRepositoryImp(IngredientJPARepository ingredientJPARepository){
        this.ingredientJPARepository = ingredientJPARepository;
    }

// TODO: Se capturan excepciones en cada metodo.

    @Override
    public void add(Ingredient ingredient) {
        this.ingredientJPARepository.save(ingredient);
    }

    @Override
    public Optional<Ingredient> findById(UUID id) {
        return this.ingredientJPARepository.findById(id);
    }
}
