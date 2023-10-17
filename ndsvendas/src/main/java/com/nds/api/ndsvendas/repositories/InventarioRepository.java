package com.nds.api.ndsvendas.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nds.api.ndsvendas.models.InventarioModel;
import com.nds.api.ndsvendas.models.ProdutoModel;
@Repository
public interface InventarioRepository extends JpaRepository<InventarioModel, UUID>{

	//@Query("SELECT  v from InventarioModel v JOIN v.produto p WHERE v.lote ?1 and p.id = ?2")
	InventarioModel findByLoteAndProduto(String lote, UUID productId);
	
	@Query("SELECT  v from InventarioModel v WHERE v.quantidade > 0")
	Page<InventarioModel>  findAllDisponiveis(Pageable pageable) ;
	
	@Query("SELECT  v from InventarioModel v INNER JOIN v.produto p ON p.id = ?1 and v.lote LIKE ?2")
	Optional<InventarioModel>  findOneProdutoDisponiveis(UUID productId,String lote) ;

	 
		 
	

}
