package com.nds.api.ndsvendas.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nds.api.ndsvendas.models.ClienteModel;
import com.nds.api.ndsvendas.models.InstituicaoModel;

@Repository
public interface InstituicaoRepository extends JpaRepository<InstituicaoModel, UUID>{

	Optional<InstituicaoModel> findByNumeroTelefone(String numTelefone);

	boolean existsByNumeroFiscal(String numFiscal);
	
	InstituicaoModel findTopByOrderByNumeroFiscalDesc();

}
