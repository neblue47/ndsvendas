package com.nds.api.ndsvendas.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nds.api.ndsvendas.models.ClienteModel;
import com.nds.api.ndsvendas.models.ContaClienteModel;
import com.nds.api.ndsvendas.models.ProdutoModel; 
@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID>{

	boolean existsByDescricaoComercial(String username);

	 
	@Query("SELECT p FROM ProdutoModel p WHERE p.id LIKE ?1")
	Optional<ProdutoModel> findOneProduct(UUID produtoId);
	
	@Query("SELECT p FROM ProdutoModel p WHERE p.id LIKE ?1")
	Optional<ProdutoModel> findOneProduct(String produtoId);
}
