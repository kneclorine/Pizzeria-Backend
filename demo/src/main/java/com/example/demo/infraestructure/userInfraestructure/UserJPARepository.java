package com.example.demo.infraestructure.userInfraestructure;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.userDomain.User;
import com.example.demo.domain.userDomain.UserProjection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJPARepository extends JpaRepository<User, UUID>{

    @Query("SELECT u.id as id, u.name as name, u.lastName as lastName, u.email as email FROM User u WHERE (:name is NULL OR u.name LIKE %:name%)")
    List<UserProjection> findByCriteria(@Param("name") String name, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(u)>0 THEN true ELSE false END FROM User u WHERE u.email = :email")
    boolean exists(@Param("email") String email);
}
