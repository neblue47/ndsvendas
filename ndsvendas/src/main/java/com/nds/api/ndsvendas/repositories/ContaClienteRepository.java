package com.nds.api.ndsvendas.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nds.api.ndsvendas.models.ClienteModel;
import com.nds.api.ndsvendas.models.ContaClienteModel;
@Repository
public interface ContaClienteRepository extends JpaRepository<ContaClienteModel, UUID>{
	
	@Query("SELECT u FROM ContaClienteModel u WHERE u.cliente = ?1")
	Optional<ContaClienteModel> findByCliente(ClienteModel cliente);
	
	boolean existsByClienteId(UUID clienteId);

}
