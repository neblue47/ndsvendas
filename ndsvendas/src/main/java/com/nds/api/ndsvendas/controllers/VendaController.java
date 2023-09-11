package com.nds.api.ndsvendas.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nds.api.ndsvendas.dtos.VendaDTO;
import com.nds.api.ndsvendas.enums.EFormaPagamento;
import com.nds.api.ndsvendas.models.ClienteModel;
import com.nds.api.ndsvendas.models.ContaClienteModel;
import com.nds.api.ndsvendas.models.ItemVendaModel;
import com.nds.api.ndsvendas.models.UtilizadorModel;
import com.nds.api.ndsvendas.models.VendaModel;
import com.nds.api.ndsvendas.services.ClienteService;
import com.nds.api.ndsvendas.services.ProdutoService;
import com.nds.api.ndsvendas.services.UtilizadorService;
import com.nds.api.ndsvendas.services.VendaService;
 
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/vendas")
public class VendaController {

	@Autowired VendaService _saleServices;
	@Autowired ProdutoService _productServices;
	@Autowired ClienteService _clientServices; 
	@Autowired  UtilizadorService _userServiceImpl;
	
	@PostMapping
    public ResponseEntity<Object> saveVenda(@RequestBody @Valid VendaDTO modelDTO){
          
        var model = ToVendaModel(modelDTO); 
        var conta = SetUpContaCliente(modelDTO); 
        var bodyContent = _saleServices.save(model);
        _saleServices.UpContaCliente(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(bodyContent);
    }
	
	@GetMapping("/clienteByTelefone/{numTelefone}")
    public ResponseEntity<Object> getOneCliente(@PathVariable(value = "numTelefone") String numTelefone){
        Optional<ClienteModel> ClienteModelOptional = _clientServices.findByNumTelefone(numTelefone);
        if (!ClienteModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ClienteModelOptional.get());
    }
	
	@PostMapping("/savecliente")
    public ResponseEntity<Object> saveCliente(@RequestBody @Valid ClienteModel model){
		if(_clientServices.existsByNumTelefone(model.getNumTelefone())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Telefone is already in saved!");
        } 
        return ResponseEntity.status(HttpStatus.CREATED).body(_clientServices.save(model));
    }
	
	@PutMapping("/anularVenda/{id}")
    public ResponseEntity<Object> anularVenda(@PathVariable(value = "id") UUID id,@RequestBody String motivo){
        Optional<VendaModel> VendaModelOptional = _saleServices.findById(id);
        if (!VendaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda not found.");
        }
        
        Date date = new Date();  
        var vendaModel = VendaModelOptional.get();
        vendaModel.setIsAnulado(1);
        vendaModel.setMotivoAnuladoVenda(motivo);
        vendaModel.setDataAnuladoVenda(date);
        return ResponseEntity.status(HttpStatus.OK).body(_saleServices.anularVenda(vendaModel));
    }
	
	private VendaModel ToVendaModel(VendaDTO modelDTO) {
		var model = new VendaModel(); 
		model.setTotalPago(modelDTO.getTotalPago());
		model.setTotalVenda(modelDTO.getTotalVenda());
		model.setDataVenda( modelDTO.getDataVenda());
		model.setDataVenda( modelDTO.getDataVenda());
		model.setFormaPagamento(EFormaPagamento.fromId(modelDTO.getFormaPagamento()));
		model.setItemVenda(ToItemVendaModel(modelDTO));
		model.setCliente(ToClienteModel(modelDTO)); 
		model.setUtilizador(ToUtilizadorModel(modelDTO)); 

		
		return model;
	}
	private ContaClienteModel SetUpContaCliente(VendaDTO modelDTO) {
	 var cliente = ToClienteModel(modelDTO);
	 var conta = new ContaClienteModel();  
	 var totalDividas = 0d;
	 var totalVendas = 0d;
	 if(_clientServices.existsByContaClienteId(cliente.getId())) {
		 conta = _clientServices.findContaByCliente(cliente).get();
		 if(modelDTO.getFormaPagamento() == EFormaPagamento.CreditoPagamento.enumValue) {
			  totalDividas = conta.getTotalDivida() > 0 ? conta.getTotalDivida() + modelDTO.getTotalDivida() : modelDTO.getTotalDivida();
			  totalVendas  = conta.getTotalVendas() > 0 ? conta.getTotalVendas() + modelDTO.getTotalVenda() : modelDTO.getTotalVenda(); 
		 }
		 else {
			  totalDividas = conta.getTotalDivida() > 0 ? conta.getTotalDivida() + 0 : 0;
			  totalVendas  = conta.getTotalVendas() > 0 ? conta.getTotalVendas() + modelDTO.getTotalVenda() : modelDTO.getTotalVenda();
		 }
		 conta.setTotalDivida(totalDividas);  
		 conta.setTotalVendas(totalVendas); 
		 conta.setUltimaData(modelDTO.getDataVenda());
	 }
	 else {
		 conta = new ContaClienteModel();
		 conta.setTotalDivida(modelDTO.getTotalDivida());  
		 conta.setTotalVendas(modelDTO.getTotalVenda());
		 conta.setCliente(cliente);
		 conta.setTotalCorrente(0D);
		 conta.setUltimaData(modelDTO.getDataVenda());
	 }
	 return conta;
	}
	 
	private ClienteModel ToClienteModel(VendaDTO modelDTO) {
		var cliente =  new ClienteModel();
		if(modelDTO.getClienteId() != null) { 
			cliente = _clientServices.findById(modelDTO.getClienteId()).get() ;
		}
		else {
			cliente.setNome(modelDTO.getNomeCliente());
			cliente.setNumTelefone(modelDTO.getNumTelefone());
			cliente.setNumeroNif(modelDTO.getNumeroNif());
			cliente.setMorada(modelDTO.getMorada());
			cliente.setPais("Angola");
			
			if(_clientServices.existsByNumTelefone(modelDTO.getNumTelefone())){
				var tempcliente = _clientServices.findByNumTelefone(modelDTO.getNumTelefone()).get();
				if(cliente.equals(tempcliente))
				   return tempcliente;
				
				throw new RuntimeException("Numero de Telefone existe na base");
	           }
			
			cliente = _clientServices.save(cliente)  ;
		}
		return cliente;
	}
	private UtilizadorModel ToUtilizadorModel(VendaDTO modelDTO) {
		 
		 var user = _userServiceImpl.findById(modelDTO.getUtilizadorId()).get();
		return user;
	}
	private List<ItemVendaModel> ToItemVendaModel(VendaDTO modelDTO) {
		var itemVenda = new ArrayList<ItemVendaModel>();
		for(var item : modelDTO.getVendaItens()) {
			var modelItem = new ItemVendaModel();
			var produto = _productServices.findById(item.getItemId()).get();
			BeanUtils.copyProperties(item, modelItem); 
			modelItem.setProduto(produto);
			modelItem.setTaxa(produto.getTaxa());
			modelItem.setSubtotal(item.getSubtotal()); 
			itemVenda.add(modelItem);
		}
		return itemVenda;
		 
	}
}
