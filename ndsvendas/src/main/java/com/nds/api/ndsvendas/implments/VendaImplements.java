package com.nds.api.ndsvendas.implments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nds.api.ndsvendas.dtos.ItemVendaDTO;
import com.nds.api.ndsvendas.dtos.ProdutoDTO;
import com.nds.api.ndsvendas.dtos.VendaDTO;
import com.nds.api.ndsvendas.dtos.VendaFilterDTO;
import com.nds.api.ndsvendas.enums.ENumVenda;
import com.nds.api.ndsvendas.models.ContaClienteModel;
import com.nds.api.ndsvendas.models.ItemVendaModel;
import com.nds.api.ndsvendas.models.ProdutoModel;
import com.nds.api.ndsvendas.models.VendaModel;
import com.nds.api.ndsvendas.repositories.ClienteRepository;
import com.nds.api.ndsvendas.repositories.ProdutoRepository;
import com.nds.api.ndsvendas.repositories.VendaRepository;

@Service
public class VendaImplements {

	@Autowired VendaRepository _vendaRepository;
	@Autowired ProdutoRepository _productRepository;
	@Autowired ClienteRepository _clientRepository;
	@Autowired ClienteImplements _clientServices;
	
	
	@Transactional
    public VendaModel save(VendaModel model) {
		var numVenda = novoNumVenda();
		model.setNumeroVenda(numVenda); 
		return _vendaRepository.save(model);
    }
	@Transactional
	public void UpContaCliente(ContaClienteModel model) {
		_clientServices.saveConta(model);
	}
	public Page<VendaDTO> findAllToDTO(Pageable pageable) {
		  
		  var entitiess = _vendaRepository.findAll(pageable);		     
		  return ToPageObjectDto(entitiess);   
	   	  
	  }
	public Optional<VendaModel> findById(UUID id) {
        return _vendaRepository.findById(id);
    }
	public Page<VendaDTO> findAllByFilterToDTO(VendaFilterDTO filter,Pageable pageable) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); 
		Page<VendaModel> entitiess = null ;
		try {
			Date vendaData = formatter.parse(filter.getDataInicial());
			Date vendaDataFm = formatter.parse(filter.getDataFim());
			vendaDataFm.setHours(23);
			vendaDataFm.setMinutes(59);
			if(filter.getEnumeradoVenda() == ENumVenda.Todas)
				entitiess = _vendaRepository.findAllByDataVenda(vendaData, vendaDataFm,pageable);
			else
			{
				var isAnulado = filter.getEnumVenda() ==  1 ? 1 : 0;
				entitiess = _vendaRepository.findAllByDataVendaFlag( vendaData, vendaDataFm,isAnulado,pageable);
			}
			 
		} catch (ParseException e) { 
			e.printStackTrace();
		}
	   	return ToPageObjectDto(entitiess); 
	  }
	private String novoNumVenda ()
	{
		Object ultimoCod = _vendaRepository.count();		
		long ultimoCod2 = ultimoCod != null ? Long.parseLong(ultimoCod.toString())+1 : 1;
		String numVenda = "NDSV" + StringUtils.leftPad(Long.toString(ultimoCod2), 6, "0");
		return numVenda;
	}
	
	 public Page<VendaDTO> ToPageObjectDto(Page<VendaModel> objects) {
		    Page<VendaDTO> dtos  = objects.map(this::ConvertToObjectDto);
		    return dtos;
	 }
	 
	 private VendaDTO ConvertToObjectDto(Object source) {
   	  	  var target = new VendaDTO();
		  BeanUtils.copyProperties(source, target); 
		  target.setFormaPagamento(((VendaModel) source).getFormaPagamento().enumValue);
		  var listsource = ((VendaModel) source).getItemVenda();
		  var itensVenda =  ToListObjectDto(listsource) ;
		  target.setClienteData(((VendaModel) source).getCliente());
		  target.setVendaItens(itensVenda);
		  return target;
	}
	  
	 private List<ItemVendaDTO> ToListObjectDto(List<ItemVendaModel> objects) {
		 	List<ItemVendaDTO> dtos  = new ArrayList<ItemVendaDTO>();
		 	for (var itemsoure : objects) {
				var target = new ItemVendaDTO();
				BeanUtils.copyProperties(itemsoure, target);
				target.setItemId(itemsoure.getId());
				target.setItemProductId(itemsoure.getProduto().getId());
				target.setItemLote(itemsoure.getLote());
				target.setSubtotal(itemsoure.getSubtotal());
				target.setPreco(itemsoure.getProduto().getPreco_taxado());
				target.setQuantidade(itemsoure.getQuantidade());
				target.setDescricaoItem(itemsoure.getProduto().getDescricaoComercial());
				dtos.add(target);
			}
		    return dtos;
	 }

	public Object anularVenda(VendaModel vendaModel) {
		return _vendaRepository.save(vendaModel);
	} 
	public ItemVendaModel GetItemVendaModel(ItemVendaDTO itemDTO, String produtoId) {
		
		var modelItem = new ItemVendaModel();		 
		var produto = _productRepository.findOneProduct(produtoId).get();		 
		BeanUtils.copyProperties(itemDTO, modelItem); 
		modelItem.setProduto(produto);
		modelItem.setTaxa(produto.getTaxa());
		modelItem.setSubtotal(itemDTO.getSubtotal()); 
		return modelItem;
	}
}
