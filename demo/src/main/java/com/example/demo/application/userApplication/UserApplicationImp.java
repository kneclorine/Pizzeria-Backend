package com.example.demo.application.userApplication;

import java.util.UUID;

import com.example.demo.core.ApplicationBase;
import com.example.demo.domain.userDomain.User;
import com.example.demo.domain.userDomain.UserProjection;
import com.example.demo.domain.userDomain.UserReadRepository;
import com.example.demo.domain.userDomain.UserWriteRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserApplicationImp extends ApplicationBase<User, UUID> implements UserApplication {

    private final UserWriteRepository userWriteRepository;
    private final UserReadRepository userReadRepository;
    private final ModelMapper modelMapper;
    private final Logger logger;

    @Autowired
    public UserApplicationImp(final UserWriteRepository userWriteRepository, UserReadRepository userReadRepository, final ModelMapper modelMapper,
            final Logger logger) {

        super((id) -> userWriteRepository.findById(id));

        this.userWriteRepository = userWriteRepository;
        this.userReadRepository = userReadRepository;
        this.modelMapper = modelMapper;
        this.logger = logger;
    }

    @Override
    public Mono<UserDTO> add(CreateUserDTO dto) {

        User user = modelMapper.map(dto, User.class);
        user.setId(UUID.randomUUID());
        user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        user.validate("email", user.getEmail(), (email) -> this.userWriteRepository.exists(email));

        this.userWriteRepository.add(user);
        logger.info(this.serializeObject(user, "added"));

        return Mono.just(modelMapper.map(user, UserDTO.class));
    }

    @Override
    public Mono<UserDTO> get(UUID id) {

        User user = this.findById(id).block();
        return Mono.just(this.modelMapper.map(user, UserDTO.class));
    }

    @Override
    public Mono<UserDTO> update(UUID id, UpdateUserDTO dto) {

        User user = this.findById(id).block();
        User userUpdated = this.modelMapper.map(dto, User.class);
        userUpdated.setId(user.getId());
        userUpdated.setEmail(user.getEmail());
        userUpdated.setRol(user.getRol());
    
        if(BCrypt.checkpw(dto.getNewPassword(), user.getPassword()) && BCrypt.checkpw(userUpdated.getPassword(), user.getPassword())) {
            userUpdated.setPassword(user.getPassword());
        } else {
            userUpdated.setPassword(BCrypt.hashpw(dto.getNewPassword(), BCrypt.gensalt()));
        }
        userUpdated.validate();
        
        this.userWriteRepository.update(userUpdated);
        logger.info(this.serializeObject(userUpdated, "updated"));

        return Mono.just(modelMapper.map(userUpdated, UserDTO.class));
    }

    @Override
    public void delete(UUID id) {

        User user = this.findById(id).block();
        this.userWriteRepository.delete(user);
        logger.info(this.serializeObject(user, "deleted"));
    }

    @Override
    public Flux<UserProjection> getAll(String email, int page, int size) {
        return this.userReadRepository.getAll(email, page, size);
    }
}
