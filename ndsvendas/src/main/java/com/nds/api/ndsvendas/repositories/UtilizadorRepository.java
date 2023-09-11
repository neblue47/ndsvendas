package com.nds.api.ndsvendas.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nds.api.ndsvendas.models.UtilizadorModel;

 
@Repository
public interface UtilizadorRepository extends JpaRepository<UtilizadorModel, UUID> {

	boolean existsByUsername(String username);

	UtilizadorModel findByUsername(String username); 
}


