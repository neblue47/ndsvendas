package com.nds.api.ndsvendas.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nds.api.ndsvendas.models.InstituicaoModel;
import com.nds.api.ndsvendas.models.ParametrosModel;
@Repository
public interface ParametroRepository extends JpaRepository<ParametrosModel, UUID>{

	ParametrosModel findByInstituicaoId(UUID instituicaoId); 
	 
	boolean existsByCae(String cae);
	boolean existsByInstituicaoId(UUID instituicaoId);
}
