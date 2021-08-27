package com.example.demo.domain.userDomain;

import java.util.List;

public interface UserReadRepository {

    public List<UserProjection> getAll(String name, int page, int size);   
}
