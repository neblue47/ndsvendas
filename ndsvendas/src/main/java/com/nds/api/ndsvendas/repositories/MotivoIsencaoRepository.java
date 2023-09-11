package com.nds.api.ndsvendas.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nds.api.ndsvendas.models.MotivoIsencaoModel;
@Repository
public interface MotivoIsencaoRepository extends JpaRepository<MotivoIsencaoModel, String>{

	//Optional<MotivoIsencaoModel> findByTaxCode(String taxCode); 
	List<MotivoIsencaoModel> findByTaxCode(String taxCode);
}
