package com.nds.api.ndsvendas.repositories;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nds.api.ndsvendas.models.VendaModel; 
@Repository
public interface VendaRepository extends JpaRepository<VendaModel, UUID>{

	@Query("SELECT  v from VendaModel v WHERE v.dataVenda between ?1 and ?2 ")
	Page<VendaModel> findAllByDataVenda(Date vendaData,Date vendaDataFm, Pageable page); 
	
	@Query("SELECT  v from VendaModel v WHERE v.dataVenda between ?1 and ?2 and v.isAnulado = ?3")
	Page<VendaModel> findAllByDataVendaFlag(Date vendaData,Date vendaDataFm, int isAnulado, Pageable page); 
}
	 
