package com.example.demo.infraestructure.userInfraestructure;

import java.util.UUID;

import com.example.demo.domain.userDomain.User;
import com.example.demo.domain.userDomain.UserProjection;
import com.example.demo.domain.userDomain.UserReadRepository;
import com.example.demo.domain.userDomain.UserWriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryImp implements UserWriteRepository, UserReadRepository{

    private final UserJPARepository userJPARepository;

    @Autowired
    public UserRepositoryImp(final UserJPARepository userJPARepository){
        this.userJPARepository = userJPARepository;
    }   

    @Override
    public Mono<User> add(User user) {
        return this.userJPARepository.save(user);
    }

    @Override
    public Mono<User>findById(UUID id) {
        return this.userJPARepository.findById(id);
    }

    @Override
    public Mono<User> update(User user) {
        return this.userJPARepository.save(user);
    }

    @Override
    public Mono<Void> delete(User user) {
        return this.userJPARepository.delete(user);
    }

    @Override
    public Flux<UserProjection> getAll(String email, int page, int size) {
        return this.userJPARepository.findByEmail(
            email,
            PageRequest.of(page, size));
    }

    @Override
    public Mono<Boolean> exists(String email) {
        return this.userJPARepository.existsByEmail(email);
    }
}
