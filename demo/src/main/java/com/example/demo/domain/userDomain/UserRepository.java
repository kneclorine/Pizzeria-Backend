package com.example.demo.domain.userDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
  public void add(User user);
  public void update(User user);
  public void delete(User user);
  public Optional<User> get(UUID id);
  public List<User> getAll(String name, String lastName, int page, int size);
}
