package com.example.demo.infraestructure.userInfraestructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.userDomain.User;
import com.example.demo.domain.userDomain.UserProjection;
import com.example.demo.domain.userDomain.UserReadRepository;
import com.example.demo.domain.userDomain.UserWriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImp implements UserWriteRepository, UserReadRepository {

    private UserJPARepository userJPARepository;

    @Autowired
    public UserRepositoryImp(UserJPARepository userJPARepository){
        this.userJPARepository = userJPARepository;
    }

    @Override
    public void add(User user) {
        this.userJPARepository.save(user);
    }

    @Override 
    public Optional<User> findById(UUID id){
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
    public List<UserProjection> getAll(String name, String lastName, int page, int size) {
        return this.userJPARepository.findByCriteria(
            name, lastName,
            PageRequest.of(page, size, Sort.by("name").descending())
            );
    }  
}
