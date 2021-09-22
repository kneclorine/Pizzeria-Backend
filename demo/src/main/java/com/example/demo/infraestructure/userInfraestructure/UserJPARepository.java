package com.example.demo.infraestructure.userInfraestructure;

import java.util.UUID;

import com.example.demo.domain.userDomain.User;
import com.example.demo.domain.userDomain.UserProjection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserJPARepository extends ReactiveCrudRepository<User, UUID>{

    @Query("SELECT u.id as id, u.name as name, u.lastName as lastName, u.email as email FROM User u WHERE (:email is NULL OR u.email LIKE %:email%)")
    Flux<UserProjection> findByEmail(@Param("email") String email, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(u)>0 THEN true ELSE false END FROM User u WHERE u.email = :email")
    Mono<Boolean> existsByEmail(@Param("email") String email);
}
