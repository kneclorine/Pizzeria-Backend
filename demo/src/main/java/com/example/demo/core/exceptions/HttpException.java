package com.example.demo.core.exceptions;

public class HttpException extends RuntimeException {
    
    private Integer code;

    public HttpException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public Integer getCode(){
        return this.code;
    }
}
