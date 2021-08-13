package com.example.demo.domain;

import java.util.UUID;

public abstract class Entity {
    
    private UUID id;

    public void generateID(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public boolean validate(UUID id){
        if(id != null){
            try {
                UUID.fromString(id.toString());
                return true;
             } catch (Exception ex) {
                return false;
             }
        }
        
        return false;
    }

    @Override
    public boolean equals (Object obj) {

        if (!(obj instanceof Entity)){
            return false;
        }

        Entity tmpEntity = (Entity) obj;

        return this.getId().equals(tmpEntity.getId());
    }

    @Override
    public int hashCode(){
        return this.getId().hashCode();
    }
}
