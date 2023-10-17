package com.nds.api.ndsvendas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nds.api.ndsvendas.dtos.ClienteDTO;
import com.nds.api.ndsvendas.dtos.ContaClienteDTO;
import com.nds.api.ndsvendas.implments.ClienteImplements;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired ClienteImplements _clientServices;
	
	  @GetMapping 
	    public ResponseEntity<Page<ContaClienteDTO>> getAllCliente(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
	        return ResponseEntity.status(HttpStatus.OK).body(_clientServices.findAllContaClienteToDTO(pageable)); 
	    }
}
