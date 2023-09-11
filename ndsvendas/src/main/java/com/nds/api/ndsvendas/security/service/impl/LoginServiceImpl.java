package com.nds.api.ndsvendas.security.service.impl;

import com.nds.api.ndsvendas.security.entities.Login;
import com.nds.api.ndsvendas.security.repository.LoginRepository;
import com.nds.api.ndsvendas.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Optional<Login> findByEmail(String email) {

        return loginRepository.findByEmail(email);
    }

    @Override
    public Login save(Login usuario) {

        return loginRepository.save(usuario);
    }
    
    public boolean existsByLogin(String email) {
        return loginRepository.existsByEmail(email);
    }
}
