package com.nds.api.ndsvendas.implments;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nds.api.ndsvendas.dtos.ProdutoDTO;
import com.nds.api.ndsvendas.dtos.UtilizadorDTO;
import com.nds.api.ndsvendas.models.ProdutoModel;
import com.nds.api.ndsvendas.models.UtilizadorModel;
import com.nds.api.ndsvendas.repositories.ProdutoRepository; 
@Service
public class ProdutoImplements {

	@Autowired ProdutoRepository _productRepository;
	
	@Transactional
    public ProdutoModel save(ProdutoModel model) {
		//model.getPreco() * model.getTaxa()
		return _productRepository.save(model);
    }

    public boolean existsByDescricaoComercial(String descricaoComercial) {
        return _productRepository.existsByDescricaoComercial(descricaoComercial);
    }

    public Page<ProdutoModel> findAll(Pageable pageable) {
        return _productRepository.findAll(pageable);
    }

     
	  public Page<ProdutoDTO> findAllToDTO(Pageable pageable) {
	  
		  var entitiess = _productRepository.findAll(pageable);		     
		  return ToPageObjectDto(entitiess);   
	   	  
	  }
	 
	 
    public Optional<ProdutoModel> findById(UUID id) {
        return _productRepository.findOneProduct(id);
    }
    public Optional<ProdutoModel> findById(String id) {
        return _productRepository.findOneProduct(id);
    }
    public Optional<ProdutoDTO> findToDTOById(UUID id) {
    	var target = _productRepository.findOneProduct(id);
        Optional<ProdutoDTO> dtos  = target.map(this::ConvertToObjectDto);
        return  dtos;
    }

    @Transactional
    public void delete(ProdutoModel model) {
    	_productRepository.delete(model);
    }
    
    private ProdutoDTO ConvertToObjectDto(Object source) {
    	  var target = new ProdutoDTO();
		  BeanUtils.copyProperties(source, target);
		  target.setStatuStock((((ProdutoModel)source).getStatuStock().enumValue));
		  return target;
	}
    
    public Page<ProdutoDTO> ToPageObjectDto(Page<ProdutoModel> objects) {
	    Page<ProdutoDTO> dtos  = objects.map(this::ConvertToObjectDto);
	    return dtos;
    }
}
