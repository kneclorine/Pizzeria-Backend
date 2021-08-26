package com.example.demo.domain.userDomain;

import com.example.demo.core.functionalInterfaces.ExistsByField;
import com.example.demo.core.functionalInterfaces.FindById;

import java.util.UUID;

public interface UserWriteRepository extends FindById<User, UUID>,ExistsByField{

    public void add(User user);
    public void update(User user);
}
