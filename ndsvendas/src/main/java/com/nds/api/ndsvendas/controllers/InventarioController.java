package com.nds.api.ndsvendas.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nds.api.ndsvendas.dtos.InventarioDTO;
import com.nds.api.ndsvendas.dtos.InventarioPackDTO;
import com.nds.api.ndsvendas.dtos.ProdutoDTO;
import com.nds.api.ndsvendas.enums.EUnidade;
import com.nds.api.ndsvendas.implments.InventarioImplement;
import com.nds.api.ndsvendas.implments.ProdutoImplements;
import com.nds.api.ndsvendas.implments.UtilizadorImplements;
import com.nds.api.ndsvendas.models.InventarioModel;
import com.nds.api.ndsvendas.models.ProdutoModel; 

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/inventarios")
public class InventarioController {

	@Autowired ProdutoImplements _productServiceImpl;
	@Autowired  UtilizadorImplements _userServiceImpl;
	@Autowired InventarioImplement _invetImplement;
	
	@GetMapping 
    public ResponseEntity<Page<InventarioDTO>> getAllProductInv(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(_invetImplement.findAllToDTO(pageable));
    }
	@GetMapping("/produtosDisponiveis") 
    public ResponseEntity<Page<InventarioDTO>> getAllDisponiveis(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(_invetImplement.findAllDisponiveis(pageable));
    }
	
	@GetMapping("/produtosDisponiveis/{id}")
    public ResponseEntity<Object> getOneProduto(@PathVariable(value = "productId") UUID productId,@PathVariable(value = "itemLote") String itemLote){
        Optional<InventarioDTO> ProdutoModelOptional = _invetImplement.findToDTOByProductIdAndLote(productId, itemLote);
        if (!ProdutoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoModelOptional.get());
    }
	@GetMapping("/produtoSelecionado/{itemId}") 
    public ResponseEntity<Object> produtoSelecionado(@PathVariable(value = "itemId") UUID itemId){
        Optional<InventarioDTO> ProdutoModelOptional = _invetImplement.getOneToDTOByProductIdAndLote(itemId);
        if (!ProdutoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoModelOptional.get());
    }
	@PostMapping
	public ResponseEntity<Object> saveProdutoInv(@RequestBody @Valid InventarioPackDTO entityDTO){
       
    	var model = ToInventarioModel(entityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(_invetImplement.save(model));        
        
    }
	
	
	private ProdutoModel ToProdutoModel(InventarioDTO modelDTO) {
		var produto = _productServiceImpl.findById(modelDTO.getProductId()).get();
		return produto;
	}
	private List<InventarioModel> ToInventarioModel(InventarioPackDTO modelDTO) {
		var itemVenda = new ArrayList<InventarioModel>();
		var userGuy = _userServiceImpl.findById(modelDTO.getUtilizadorId()).get();
		for(var item : modelDTO.getItemEntradaArray()) {
			var modelItem = new InventarioModel(); 
			BeanUtils.copyProperties(item, modelItem);   
			modelItem.setUnidade(EUnidade.fromId(item.getUnidade()));
			modelItem.setUtilizador(userGuy);
			modelItem.setProduto(ToProdutoModel(item));
			itemVenda.add(modelItem);
		}
		return itemVenda;
		 
	}
	 
}
