package com.example.demo.domain;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, UUID> {

  @RestResource(exported = false)
  List<User> findByName(@Param("name") String name);
}
