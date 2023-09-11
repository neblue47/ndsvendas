package com.nds.api.ndsvendas.security.service;

import com.nds.api.ndsvendas.security.entities.Login;

import java.util.Optional;

public interface LoginService {

    Optional<Login> findByEmail(String email);

    Login save(Login login);
    
    boolean existsByLogin(String email);
    
}
