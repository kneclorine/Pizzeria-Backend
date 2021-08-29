package com.example.demo.core.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ForbiddenException extends HttpException {
    
    private final Map<String, String> map = new HashMap<String, String>();

     public ForbiddenException(){
         this("Forbidden");
     } 
     public ForbiddenException(String message){
         super(403, message);
     }
     public Map<String,String> getExceptions(){
        return this.map;
    }

    public void addException(String key, String message){
        this.map.put(key, message);
    }
}
