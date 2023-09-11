package com.nds.api.ndsvendas.security.repository;

import com.nds.api.ndsvendas.security.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginRepository extends JpaRepository<Login, UUID> {
	
	Optional<Login> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	Login findOneByEmail(String username); 
	
}