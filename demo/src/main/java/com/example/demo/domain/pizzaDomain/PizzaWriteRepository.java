package com.example.demo.domain.pizzaDomain;

import java.util.UUID;

import com.example.demo.core.functionalInterfaces.ExistsByField;
import com.example.demo.core.functionalInterfaces.FindById;

public interface PizzaWriteRepository extends FindById<Pizza, UUID>, ExistsByField{

    public void add(Pizza pizza); 
}
