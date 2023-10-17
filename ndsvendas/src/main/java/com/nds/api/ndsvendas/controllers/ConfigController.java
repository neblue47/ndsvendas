package com.nds.api.ndsvendas.controllers;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nds.api.ndsvendas.dtos.InstituicaoDTO;
import com.nds.api.ndsvendas.dtos.MotivoIsencaoDTO;
import com.nds.api.ndsvendas.dtos.ParamFiscalDTO;
import com.nds.api.ndsvendas.dtos.ProdutoDTO;
import com.nds.api.ndsvendas.dtos.RetornoInstituicaoDTO;
import com.nds.api.ndsvendas.dtos.UtilizadorDTO;
import com.nds.api.ndsvendas.enums.EMoeda;
import com.nds.api.ndsvendas.enums.EStatuStock;
import com.nds.api.ndsvendas.enums.ETipoComercializacao;
import com.nds.api.ndsvendas.enums.EUserGroup;
import com.nds.api.ndsvendas.implments.InstituicaoImplements;
import com.nds.api.ndsvendas.implments.ProdutoImplements;
import com.nds.api.ndsvendas.models.InstituicaoModel;
import com.nds.api.ndsvendas.models.MotivoIsencaoModel;
import com.nds.api.ndsvendas.models.ParamFiscalModel;
import com.nds.api.ndsvendas.models.UtilizadorModel;
import com.nds.api.ndsvendas.security.entities.Login;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/configuracoes")
public class ConfigController {

	@Autowired InstituicaoImplements _instistService;
	@Autowired ProdutoImplements _productServices; 
	
	@GetMapping 
    public ResponseEntity<RetornoInstituicaoDTO> getAllCliente(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(_instistService.getInstituicaoRetorn()); 
    }
	@GetMapping("/details")
    public ResponseEntity<RetornoInstituicaoDTO> getDetails(){
        return ResponseEntity.status(HttpStatus.OK).body(_instistService.getInstituicaoRetorn()); 
    }
	@GetMapping("/instDetails")
    public ResponseEntity<InstituicaoDTO> getInstituicaoDetails(){
        return ResponseEntity.status(HttpStatus.OK).body(_instistService.getInstituicaoRetorn().getInstituicaoViews()); 
    }
	@PostMapping
    public ResponseEntity<Object> saveInstituicao(@RequestBody @Valid InstituicaoDTO modelDto){
        if(_instistService.existsByNumeroFiscal(modelDto.getNumeroFiscal())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: NumeroFiscal is already in use!");
        }
         
        var entityModel = new InstituicaoModel();
        BeanUtils.copyProperties(modelDto, entityModel);  
        var newModel = _instistService.save(entityModel);      
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newModel);
    }
	@PutMapping("/{id}")
    public ResponseEntity<Object> updateInstituicao(@PathVariable(value = "id") UUID id,@RequestBody @Valid InstituicaoDTO modelDto){
        Optional<InstituicaoModel> UtilizadorModelOptional = _instistService.findById(id);
        if (!UtilizadorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instituicao not found.");
        }
        var entityModel = new InstituicaoModel();
        BeanUtils.copyProperties(modelDto, entityModel);         
        
        return ResponseEntity.status(HttpStatus.OK).body(_instistService.save(entityModel));
    }
	
	@PostMapping("/dadosFiscal")
    public ResponseEntity<Object> saveDadosFiscal(@RequestBody @Valid ParamFiscalDTO modelDto){
        if(_instistService.existsByCae(modelDto.getCae())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CAE is already in use!");
        }
         
        var entityModel = new ParamFiscalModel();
        BeanUtils.copyProperties(modelDto, entityModel);  
        var newModel = _instistService.saveDadosFiscal(entityModel);      
        entityModel.setMoeda(EMoeda.fromId(modelDto.getMoeda()));
        entityModel.setTipo(ETipoComercializacao.Gerais);
        return ResponseEntity.status(HttpStatus.CREATED).body(newModel);
    }
	@PutMapping("/dadosFiscal/{id}")
    public ResponseEntity<Object> updateDadosFiscal(@PathVariable(value = "id") UUID id,@RequestBody @Valid ParamFiscalDTO modelDto){
        Optional<ParamFiscalModel> paramFiscalModel = _instistService.findDadosFiscalById(id);
        if (!paramFiscalModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ParamFiscalModel not found.");
        }
        var entityModel = new ParamFiscalModel();
        BeanUtils.copyProperties(modelDto, entityModel);         
        entityModel.setMoeda(EMoeda.fromId(modelDto.getMoeda()));
        entityModel.setTipo(ETipoComercializacao.Gerais);
        return ResponseEntity.status(HttpStatus.OK).body(_instistService.saveDadosFiscal(entityModel));
    }
	
	 
	@GetMapping("/regimeMotivos") 
    public ResponseEntity<Page<MotivoIsencaoDTO>> AllMotivosDetails(@PageableDefault(page = 0, size = 10, sort = "taxCode", direction = Sort.Direction.ASC) Pageable pageable){
        
		return ResponseEntity.status(HttpStatus.OK).body(_instistService.AllMotivos(pageable)); 
    }
	 
}
