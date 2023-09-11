package com.nds.api.ndsvendas.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nds.api.ndsvendas.dtos.UtilizadorDTO;
import com.nds.api.ndsvendas.enums.EUserGroup;
import com.nds.api.ndsvendas.models.UtilizadorModel;
import com.nds.api.ndsvendas.repositories.UtilizadorRepository;
import com.nds.api.ndsvendas.security.repository.LoginRepository;

@Service
public class UtilizadorService {

	@Autowired UtilizadorRepository _userRepository;
	@Autowired LoginRepository _loginRepository;
	
	@Transactional
    public UtilizadorModel save(UtilizadorModel UtilizadorModel) {
        return _userRepository.save(UtilizadorModel);
    }

    public boolean existsByLogin(String username) {
        return _userRepository.existsByUsername(username);
    }

    public Page<UtilizadorModel> findAll(Pageable pageable) {
        return _userRepository.findAll(pageable);
    }

    
	  public Page<UtilizadorDTO> findAllToDTO(Pageable pageable) {
	   	  
	   Page<UtilizadorModel> entitiess = _userRepository.findAll(pageable); 	    
	   return ToPageObjectDto(entitiess); 	  
	   
	  }
	  public Page<UtilizadorDTO> ToPageObjectDto(Page<UtilizadorModel> objects) {
		    Page<UtilizadorDTO> dtos  = objects.map(this::ConvertToObjectDto);
		    return dtos;
	  }
	
	 private UtilizadorDTO ConvertToObjectDto(Object source) {
		  UtilizadorDTO target = new UtilizadorDTO();
		  BeanUtils.copyProperties(source, target);
		  target.setUserGroup(((UtilizadorModel)source).getUserGroup().enumValue);
		  target.setPassword(null);
		  return target;
	 }
	 
	public Optional<UtilizadorModel> findById(UUID id) {
        var target = _userRepository.findById(id); 
        return  target;
    	
    	
    }
	public Optional<UtilizadorDTO> findToDTOById(UUID id) {
        var target = _userRepository.findById(id);
        Optional<UtilizadorDTO> dtos  = target.map(this::ConvertToObjectDto);
        return  dtos;
    	
    	
    }


    @Transactional
    public void delete(UtilizadorModel UtilizadorModel) {
        _userRepository.delete(UtilizadorModel);
    }

	public boolean confirmPassword(@Valid UtilizadorDTO utilizadorDto) { 
		return utilizadorDto.getPassword().equals(utilizadorDto.getRepeatPassword());
	}
	
	public UtilizadorDTO findByLogin(String username) {
        var source = _userRepository.findByUsername(username);
		return ConvertToObjectDto(source);
    } 
}
