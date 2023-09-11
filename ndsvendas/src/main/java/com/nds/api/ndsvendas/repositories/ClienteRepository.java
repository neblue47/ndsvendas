package com.nds.api.ndsvendas.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nds.api.ndsvendas.models.ClienteModel; 
@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, UUID>{

	Optional<ClienteModel> findByNumTelefone(String numTelefone);

	boolean existsByNumTelefone(String numTelefone);

}
