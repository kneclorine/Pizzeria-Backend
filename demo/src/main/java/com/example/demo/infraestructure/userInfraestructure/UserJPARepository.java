package com.example.demo.infraestructure.userInfraestructure;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.userDomain.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserJPARepository extends CrudRepository<User, UUID>{

    User findByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE (:name IS NULL OR u.name LIKE :name) AND (:lastName IS NULL OR u.lastName LIKE :lastName)")
    List<User> findByCriteria(@Param("name") String name, @Param("lastName") String lastName, Pageable pageable);
}
