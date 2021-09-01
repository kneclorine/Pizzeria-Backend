package com.example.demo.infraestructure.userInfraestructure;

import com.example.demo.domain.userDomain.User;
import com.example.demo.domain.userDomain.UserProjection;
import com.example.demo.domain.userDomain.UserReadRepository;
import com.example.demo.domain.userDomain.UserWriteRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImp implements UserWriteRepository, UserReadRepository{

    private final UserJPARepository userJPARepository;

    @Autowired
    public UserRepositoryImp(final UserJPARepository userJPARepository){
        this.userJPARepository = userJPARepository;
    }   

    @Override
    public void add(User user) {
        this.userJPARepository.save(user);
    }

    @Override
    public Optional<User>findById(UUID id) {
        return this.userJPARepository.findById(id);
    }

    @Override
    public void update(User user) {
        this.userJPARepository.save(user);
    }

    @Override
    public void delete(User user) {
        this.userJPARepository.delete(user);
    }

    @Override
    public List<UserProjection> getAll(String email, int page, int size) {
        return this.userJPARepository.findByCriteria(
            email,
            PageRequest.of(page, size));
    }

    @Override
    public boolean exists(String email) {
        return this.userJPARepository.exists(email);
    }
}
