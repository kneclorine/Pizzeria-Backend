package com.example.demo.core;

import com.example.demo.core.exceptions.NotFoundException;
import com.example.demo.core.functionalInterfaces.FindById;

public abstract class ApplicationBase<T, ID> {

    private FindById<T, ID> getById;

    protected T findById(ID id){
        T t = this.getById.findById(id).orElseThrow(()->{
            throw new NotFoundException();
        });
        return t;
    }

    protected ApplicationBase(FindById<T, ID> getById){
        this.getById = getById;
    }

    protected String serializeObject(T entity, String messege) {

        return String.format("%s %s succesfully.", entity.toString(), messege);
    }
}
