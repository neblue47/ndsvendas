package com.nds.api.ndsvendas.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nds.api.ndsvendas.models.ProdutoModel; 
@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID>{

	boolean existsByDescricaoComercial(String username); 
}
