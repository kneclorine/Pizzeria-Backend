package com.example.demo.core.configurationBeans;

import org.hibernate.type.SerializationException;
import org.springframework.data.redis.serializer.RedisSerializer;

import lombok.NoArgsConstructor;

public @NoArgsConstructor class ByteSeriallizer implements RedisSerializer<byte[]>{
    @Override
    public byte[] serialize(byte[] t) throws SerializationException {
        return t;
    }
 
    @Override
    public byte[] deserialize(byte[] bytes) throws SerializationException {
        return bytes;
    }
}
