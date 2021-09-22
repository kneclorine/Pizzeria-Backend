package com.example.demo.core;

import com.example.demo.core.exceptions.NotFoundException;
import com.example.demo.core.functionalInterfaces.FindById;

import reactor.core.publisher.Mono;

public abstract class ApplicationBase<T, ID> {

    private FindById<T, ID> getById;

    protected Mono<T> findById(ID id){
        
        return this.getById.findById(id).switchIfEmpty(Mono.error(new NotFoundException()));
    }

    protected ApplicationBase(FindById<T, ID> getById){
        this.getById = getById;
    }

    protected String serializeObject(T entity, String messege) {

        return String.format("%s %s succesfully.", entity.toString(), messege);
    }
}
