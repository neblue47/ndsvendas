package com.nds.api.ndsvendas.implments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.nds.api.ndsvendas.dtos.InventarioDTO;
import com.nds.api.ndsvendas.dtos.ProdutoDTO;
import com.nds.api.ndsvendas.dtos.VendaDTO;
import com.nds.api.ndsvendas.models.InventarioModel;
import com.nds.api.ndsvendas.models.ProdutoModel;
import com.nds.api.ndsvendas.models.UtilizadorModel;
import com.nds.api.ndsvendas.models.VendaModel;
import com.nds.api.ndsvendas.repositories.InventarioRepository;
import com.nds.api.ndsvendas.repositories.ProdutoRepository;

@Service
public class InventarioImplement {

	@Autowired InventarioRepository _repository;
	@Autowired ProdutoRepository _productRepository;
	 
	
	public Page<InventarioDTO> findAllToDTO(Pageable pageable) {
		  var entitiess = _repository.findAll(pageable);		     
		  return ToPageObjectDto(entitiess);   
	   	  
	}
	
	
  
	public Page<InventarioDTO> ToPageObjectDto(Page<InventarioModel> objects) {
	    Page<InventarioDTO> dtos  = objects.map(this::ConvertToObjectDto);
	    return dtos;
	}

	
	public InventarioModel findByByLoteAndProductId(String lote, UUID productId) {
		var itemModel = _repository.findByLoteAndProduto(lote,productId);
		return itemModel;
	}

	//@Query("SELECT  v from InventarioModel v JOIN v.produto p WHERE v.lote ?1 and p.id = ?2")
	public boolean existsByLoteAndProduct(String lote, UUID productId) {
		var itemModel = _repository.findByLoteAndProduto(lote,productId);
		    
		return itemModel != null;
	}
	@Transactional
	 public InventarioModel save(InventarioModel entity) {
		
		return _repository.save(entity);
	}
	@Transactional
	 public List<InventarioModel> save(List<InventarioModel> entities) {
		List<InventarioModel> result = new ArrayList<InventarioModel>();
		for (InventarioModel entity : entities) {
			result.add(_repository.save(entity));
	    }
		return result;
	}
	public Page<InventarioDTO> findAllDisponiveis(Pageable pageable) {
		  var entitiess = _repository.findAllDisponiveis(pageable);		     
		  return ToPageObjectDto(entitiess);   
	   	  
	}
	public Optional<InventarioDTO> findToDTOByProductIdAndLote(UUID productId,String itemLote) {
    	var target = _repository.findOneProdutoDisponiveis(productId, itemLote);
        Optional<InventarioDTO> dtos  = target.map(this::ConvertToObjectDto);
        return  dtos;
    }
	public Optional<InventarioDTO> getOneToDTOByProductIdAndLote(UUID itemId) {
    	var target = _repository.findById(itemId);
        Optional<InventarioDTO> dtos  = target.map(this::ConvertToObjectDto);
        return  dtos;
    }
	public void atualizarStock(VendaModel model) {
		
		for (var entity : model.getItemVenda()) {
			var itemVendido = _repository.findByLoteAndProduto(entity.getLote(),entity.getProduto().getId());
			var quantidade = itemVendido.getQuantidade() - entity.getQuantidade();
			itemVendido.setQuantidade(quantidade);
			_repository.save(itemVendido);
	    }
	 
		
	}
	 
	private InventarioDTO ConvertToObjectDto(Object source) {
	  	  var target = new InventarioDTO();
			  BeanUtils.copyProperties(source, target);
			  target.setUnidade((((InventarioModel)source).getUnidade().enumValue));
			  target.setProductId(((InventarioModel)source).getProduto().getId());
			  target.setProductDescricao(((InventarioModel)source).getProduto().getDescricaoComercial());
			  target.setInventarioId(((InventarioModel)source).getId());
			  target.setUtilizadorName(((InventarioModel)source).getUtilizador().getFullName());
			  return target;
		}
	public ProdutoModel itemProduto (UUID productId,String itemLote) 
	{
		var itemProdutos = _repository.findByLoteAndProduto(itemLote,productId);
		return itemProdutos.getProduto();
	}
}
