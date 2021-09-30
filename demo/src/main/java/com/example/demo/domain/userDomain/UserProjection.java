package com.example.demo.domain.userDomain;

import java.util.UUID;

public interface UserProjection {
    
    public UUID getId();
    
    public String getName();

    public String getLastName();

    public String getEmail();

    public String getPassword();
}
