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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nds.api.ndsvendas.dtos.UtilizadorDTO;
import com.nds.api.ndsvendas.enums.EUserGroup;
import com.nds.api.ndsvendas.models.UtilizadorModel;
import com.nds.api.ndsvendas.security.entities.Login;
import com.nds.api.ndsvendas.security.enums.PerfilEnum;
import com.nds.api.ndsvendas.security.service.LoginService;
import com.nds.api.ndsvendas.security.service.impl.UserDetailsServiceImpl;
import com.nds.api.ndsvendas.services.UtilizadorService;
 
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/utilizadores")
public class UtilizadorController {

	@Autowired PasswordEncoder passwordEncoder  ;
	
	@Autowired UtilizadorService _userServices;
	@Autowired LoginService _loginServices;
	@Autowired UserDetailsServiceImpl _userAuth;
	 
	
	@PostMapping
    public ResponseEntity<Object> saveUtilizador(@RequestBody @Valid UtilizadorDTO utilizadorDto){
        if(_userServices.existsByLogin(utilizadorDto.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Username is already in use!");
        }
        if(!_userServices.confirmPassword(utilizadorDto)){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not Acceptable: Passwords is not equals!");
        }
        var utilizadorModel = new UtilizadorModel();
        BeanUtils.copyProperties(utilizadorDto, utilizadorModel); 
        utilizadorModel.setUserGroup(EUserGroup.fromId(utilizadorDto.getUserGroup()));
        var newUtilizador = _userServices.save(utilizadorModel);
        
        
        if(!_loginServices.existsByLogin(utilizadorDto.getUsername())) {
        	var loginModel = new Login();
            loginModel.setEmail(utilizadorDto.getUsername());
            loginModel.setPassword(passwordEncoder.encode(utilizadorDto.getPassword()));
            loginModel.setPerfil(ConvertoPerfilEnum(utilizadorModel.getUserGroup()));
            _loginServices.save(loginModel);
        }
        
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newUtilizador);
    }
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    public ResponseEntity<Page<UtilizadorDTO>> getAllUtilizador(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(_userServices.findAllToDTO(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUtilizador(@PathVariable(value = "id") UUID id){
        Optional<UtilizadorDTO> UtilizadorModelOptional = _userServices.findToDTOById(id);
        if (!UtilizadorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(UtilizadorModelOptional.get());
    }
    @GetMapping("/utilizadorDetail")
    public ResponseEntity<UtilizadorDTO> getOneUtilizadorDetail(){
    	var username = _userAuth.getUserLogado().getEmail();
    	UtilizadorDTO UtilizadorModelOptional = _userServices.findByLogin(username);
         
        return ResponseEntity.status(HttpStatus.OK).body(UtilizadorModelOptional);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUtilizador(@PathVariable(value = "id") UUID id){
        Optional<UtilizadorModel> UtilizadorModelOptional = _userServices.findById(id);
        if (!UtilizadorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        _userServices.delete(UtilizadorModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUtilizador(@PathVariable(value = "id") UUID id,@RequestBody @Valid UtilizadorDTO UtilizadorDto){
        Optional<UtilizadorModel> UtilizadorModelOptional = _userServices.findById(id);
        if (!UtilizadorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        var UtilizadorModel = new UtilizadorModel();
        BeanUtils.copyProperties(UtilizadorDto, UtilizadorModel); 
        UtilizadorModel.setUserGroup(EUserGroup.fromId(UtilizadorDto.getUserGroup()));
        
        if(!_loginServices.existsByLogin(UtilizadorDto.getUsername())) {
        	var loginModel = new Login();
            loginModel.setEmail(UtilizadorDto.getUsername());
            loginModel.setPassword(passwordEncoder.encode(UtilizadorDto.getPassword()));
            loginModel.setPerfil(ConvertoPerfilEnum(UtilizadorModel.getUserGroup()));
            _loginServices.save(loginModel);
        }
        
        
        return ResponseEntity.status(HttpStatus.OK).body(_userServices.save(UtilizadorModel));
    }
    
    private PerfilEnum ConvertoPerfilEnum (EUserGroup group) {
		switch(group) {
		case Admin :
		return PerfilEnum.ROLE_ADMINISTRADOR;
		case Gestor :
			return PerfilEnum.ROLE_GESTOR;
		case Vendedor :
			return PerfilEnum.ROLE_OPERADOR; 
		default: 
			return PerfilEnum.ROLE_CONSULTA;
		}
	}
     
}
