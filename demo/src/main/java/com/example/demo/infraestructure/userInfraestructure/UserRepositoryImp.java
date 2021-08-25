package com.example.demo.infraestructure.userInfraestructure;

import com.example.demo.domain.userDomain.User;
import com.example.demo.domain.userDomain.UserWriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImp implements UserWriteRepository{

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
    public boolean exists(String email) {
        return this.userJPARepository.exists(email);
    }
}
