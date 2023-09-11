package com.nds.api.ndsvendas.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nds.api.ndsvendas.models.ParamFiscalModel;
@Repository
public interface ParamFiscalRepository  extends JpaRepository<ParamFiscalModel, UUID>{

	
	ParamFiscalModel findByInstituicaoId(UUID instituicaoId); 
	 
	boolean existsByCae(String cae);
	
	boolean existsByInstituicaoId(UUID instituicaoId);
}