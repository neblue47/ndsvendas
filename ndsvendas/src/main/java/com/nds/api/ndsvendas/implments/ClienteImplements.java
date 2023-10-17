package com.nds.api.ndsvendas.implments;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nds.api.ndsvendas.dtos.ClienteDTO;
import com.nds.api.ndsvendas.dtos.ContaClienteDTO;
import com.nds.api.ndsvendas.dtos.ProdutoDTO;
import com.nds.api.ndsvendas.models.ClienteModel;
import com.nds.api.ndsvendas.models.ContaClienteModel;
import com.nds.api.ndsvendas.models.ProdutoModel;
import com.nds.api.ndsvendas.repositories.ClienteRepository;
import com.nds.api.ndsvendas.repositories.ContaClienteRepository;

@Service
public class ClienteImplements {

	@Autowired ClienteRepository _clientRepository;
	@Autowired ContaClienteRepository _contClientRepository;
	
	public Optional<ClienteModel> findById(UUID id) {
        var target = _clientRepository.findById(id); 
        return  target;    	
    	
    }

	public Optional<ContaClienteModel> findContaByCliente(ClienteModel cliente) {
        var target = _contClientRepository.findByCliente(cliente); 
        return  target;    	
    	
    }
	public Optional<ClienteModel> findByNumTelefone(String numTelefone) {
        var target = _clientRepository.findByNumTelefone(numTelefone); 
        return  target;    	
    	
    }
	@Transactional
    public ClienteModel save(ClienteModel model) {
	 
		return _clientRepository.save(model);
    }
	@Transactional
    public ContaClienteModel saveConta(ContaClienteModel model) {
	 
		return _contClientRepository.save(model);
    }
	public boolean existsByNumTelefone(String numTelefone) {
		 
		return _clientRepository.existsByNumTelefone(numTelefone);
	}
	public boolean existsByContaClienteId(UUID clienteId) {
		 
		return _contClientRepository.existsByClienteId(clienteId);
	}
	public Page<ContaClienteDTO> findAllContaClienteToDTO(Pageable pageable) {
		var entitiess = _contClientRepository.findAll(pageable);		     
		return ToPageObjectDto(entitiess);   
	} 
	
	private Page<ContaClienteDTO> ToPageObjectDto(Page<ContaClienteModel> objects) {
	    Page<ContaClienteDTO> dtos  = objects.map(this::ConvertToObjectDto);
	    return dtos;
    }
	
	private ContaClienteDTO ConvertToObjectDto(Object source) {
  	      var target = new ContaClienteDTO();
  	      var targetCliente = new ClienteDTO();
  	      var sourceCliente = ((ContaClienteModel)source).getCliente();
		  BeanUtils.copyProperties(source, target);  
		  BeanUtils.copyProperties(sourceCliente, targetCliente);
		  ((ContaClienteDTO)target).setCliente(targetCliente);
		  return target;
	}
}
