package com.nds.api.ndsvendas.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nds.api.ndsvendas.dtos.InstituicaoDTO;
import com.nds.api.ndsvendas.dtos.MotivoIsencaoDTO;
import com.nds.api.ndsvendas.dtos.ParamFiscalDTO;
import com.nds.api.ndsvendas.dtos.RetornoInstituicaoDTO;
import com.nds.api.ndsvendas.models.InstituicaoModel;
import com.nds.api.ndsvendas.models.MotivoIsencaoModel;
import com.nds.api.ndsvendas.models.ParamFiscalModel;
import com.nds.api.ndsvendas.models.ParametrosModel;
import com.nds.api.ndsvendas.models.ProdutoModel;
import com.nds.api.ndsvendas.repositories.InstituicaoRepository;
import com.nds.api.ndsvendas.repositories.MotivoIsencaoRepository;
import com.nds.api.ndsvendas.repositories.ParamFiscalRepository;
import com.nds.api.ndsvendas.repositories.ParametroRepository;

@Service
public class InstituicaoService {

	@Autowired InstituicaoRepository _instituicaoRepository; 
	@Autowired MotivoIsencaoRepository _motivoIseRepository;
	@Autowired ParametroRepository _parmRepository;
	@Autowired ParamFiscalRepository _parmFiscalRepository;
	
	public Optional<InstituicaoModel> findById(UUID id) {
        var target = _instituicaoRepository.findById(id); 
        return  target;    	
    	
    }
 
	public List<MotivoIsencaoModel> getAllMotivos(){
		var listMotivos = _motivoIseRepository.findAll();
		return listMotivos;
	}
	public Page<MotivoIsencaoDTO> AllMotivos(Pageable pageable){
		var listMotivos = _motivoIseRepository.findAll(pageable);
		return ToPageObjectMotivoDto(listMotivos);  
	} 
	public Optional<InstituicaoModel> findByNumTelefone(String numTelefone) {
        var target = _instituicaoRepository.findByNumeroTelefone(numTelefone); 
        return  target;    	
    	
    }
	@Transactional
    public InstituicaoModel save(InstituicaoModel model) {
	 
		return _instituicaoRepository.save(model);
    }
	 
	public boolean existsByNumeroFiscal(String numFiscal) {
		 
		return _instituicaoRepository.existsByNumeroFiscal(numFiscal);
	}
	public boolean existsById(UUID id) {
		 
		return _instituicaoRepository.existsById(id);
	}
	public Page<InstituicaoDTO> findAllInstituicaoToDTO(Pageable pageable) {
		var entitiess = _instituicaoRepository.findAll(pageable);		     
		return ToPageObjectDto(entitiess);   
	} 
	public RetornoInstituicaoDTO getInstituicaoRetorn() {
		
		var returno = new RetornoInstituicaoDTO();
		var instituicaoViews = _instituicaoRepository.findTopByOrderByNumeroFiscalDesc();	
		
		returno.setInstituicaoViews(ConvertToObjectDto(instituicaoViews));
		if(instituicaoViews != null && _parmFiscalRepository.existsByInstituicaoId(instituicaoViews.getId())) {			 
			var parmFiscaisViews =  _parmFiscalRepository.findByInstituicaoId(instituicaoViews.getId());
			returno.setDadosFiscaisViews(ConvertToObjectParamFiscalDto(parmFiscaisViews));
			returno.getDadosFiscaisViews().setInstituicaoId(instituicaoViews.getId());
		}
		
		return returno;
	}
	private Page<InstituicaoDTO> ToPageObjectDto(Page<InstituicaoModel> objects) {
	    Page<InstituicaoDTO> dtos  = objects.map(this::ConvertToObjectDto);
	    return dtos;
    }
	
	private InstituicaoDTO ConvertToObjectDto(Object source) {
  	      var target = new InstituicaoDTO(); 
		  BeanUtils.copyProperties(source, target);    
		  return target;
	}
	
	private ParamFiscalDTO ConvertToObjectParamFiscalDto(Object source) {
	      var target = new ParamFiscalDTO(); 
		  BeanUtils.copyProperties(source, target);  
		  target.setMoeda(((ParamFiscalModel)source).getMoeda().enumValue);	
		  target.setTipo(((ParamFiscalModel)source).getTipo().enumValue);
		  return target;
	}
	private MotivoIsencaoDTO ConvertToObjectMotivoDto(Object source) {
	      var target = new MotivoIsencaoDTO(); 
		  BeanUtils.copyProperties(source, target);  
		  
		  return target;
	}
	private Page<MotivoIsencaoDTO> ToPageObjectMotivoDto(Page<MotivoIsencaoModel> objects) {
	    Page<MotivoIsencaoDTO> dtos  = objects.map(this::ConvertToObjectMotivoDto);
	    return dtos;
    }

	public boolean existsByCae(String numCae) { 
		return _parmFiscalRepository.existsByCae(numCae);
	}

	public Optional<ParamFiscalModel> findDadosFiscalById(UUID id) { 
		return _parmFiscalRepository.findById(id);
	}
	@Transactional
	public ParamFiscalModel saveDadosFiscal(ParamFiscalModel entityModel) {
		var instituicao = _instituicaoRepository.findTopByOrderByNumeroFiscalDesc();
		entityModel.setInstituicao(instituicao);
		return _parmFiscalRepository.save(entityModel);
	}
}
