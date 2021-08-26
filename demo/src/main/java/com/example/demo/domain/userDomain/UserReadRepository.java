package com.example.demo.domain.userDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserReadRepository {
  public Optional<User> findById(UUID id);

  public List<UserProjection> getAll(String name, String lastName, int page, int size); 
}
