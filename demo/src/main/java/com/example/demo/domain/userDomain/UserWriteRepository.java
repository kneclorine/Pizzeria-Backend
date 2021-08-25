package com.example.demo.domain.userDomain;

import com.example.demo.core.functionalInterfaces.ExistsByField;

public interface UserWriteRepository extends ExistsByField{

    public void add(User user);
}
