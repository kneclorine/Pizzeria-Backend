package com.example.demo.domain.userDomain;

import java.util.UUID;

import com.example.demo.core.functionalInterfaces.ExistsByField;
import com.example.demo.core.functionalInterfaces.FindById;

import reactor.core.publisher.Mono;

public interface UserWriteRepository extends FindById<User, UUID>,ExistsByField{

    public Mono<User> add(User user);
    public Mono<User> update(User user);
    public Mono<Void> delete(User user);
}
