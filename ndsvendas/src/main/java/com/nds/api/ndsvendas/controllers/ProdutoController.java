package com.nds.api.ndsvendas.controllers;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nds.api.ndsvendas.dtos.ProdutoDTO;
import com.nds.api.ndsvendas.enums.EStatuStock;
import com.nds.api.ndsvendas.implments.ProdutoImplements;
import com.nds.api.ndsvendas.models.ProdutoModel;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired ProdutoImplements _productServices; 
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMINISTRADOR', 'OPERADOR', 'CONSULTA')")
    public ResponseEntity<Object> saveProduto(@RequestBody @Valid ProdutoDTO produtoDto){
        if(_productServices.existsByDescricaoComercial(produtoDto.getDescricaoComercial())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Product is already in saved!");
        } 
        var model = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto, model); 
        model.setStatuStock(EStatuStock.fromId(produtoDto.getStatuStock()));
        return ResponseEntity.status(HttpStatus.CREATED).body(_productServices.save(model));
    }

	 
    @GetMapping 
    public ResponseEntity<Page<ProdutoDTO>> getAllProduto(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(_productServices.findAllToDTO(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduto(@PathVariable(value = "id") UUID id){
        Optional<ProdutoDTO> ProdutoModelOptional = _productServices.findToDTOById(id);
        if (!ProdutoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable(value = "id") UUID id){
        Optional<ProdutoModel> ProdutoModelOptional = _productServices.findById(id);
        if (!ProdutoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        _productServices.delete(ProdutoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduto(@PathVariable(value = "id") UUID id,@RequestBody @Valid ProdutoDTO produtoDto){
        Optional<ProdutoModel> ProdutoModelOptional = _productServices.findById(id);
        if (!ProdutoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var ProdutoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto, ProdutoModel);
        ProdutoModel.setId(ProdutoModelOptional.get().getId());
        ProdutoModel.setStatuStock(EStatuStock.fromId(produtoDto.getStatuStock()));
        return ResponseEntity.status(HttpStatus.OK).body(_productServices.save(ProdutoModel));
    }
}
