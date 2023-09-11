package com.nds.api.ndsvendas.security.controller;

import com.nds.api.ndsvendas.security.request.LoginJwtRequest;
import com.nds.api.ndsvendas.security.response.LoginJwtResponse;
import com.nds.api.ndsvendas.security.service.LoginService;
import com.nds.api.ndsvendas.security.service.impl.UserDetailsServiceImpl;
import com.nds.api.ndsvendas.security.utils.JwtUtil;
import com.nds.api.ndsvendas.services.UtilizadorService;

import lombok.RequiredArgsConstructor; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
 
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor 
public class LoginController {
 
 
  

    @Autowired
    private  UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired 
    private  UtilizadorService _userServices;

    @Autowired
    private JwtUtil jwtUtil;
 
 
    @PostMapping("/autenticacao")
    @ResponseStatus(HttpStatus.OK)
    public LoginJwtResponse getAutenticacao(@RequestBody LoginJwtRequest usuarioJwtRequest){
        try{
            UserDetails userDetails = userDetailsServiceImpl.autenticar(usuarioJwtRequest);
            var userLogado= _userServices.findByLogin(userDetails.getUsername());
            String token = jwtUtil.obterToken(usuarioJwtRequest);
            
            return  LoginJwtResponse.builder()  
                    .email(userDetails.getUsername())
                    .token(token)
                    .firstLogin(false)
                    .userLogado(userLogado) 
                    .build();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }



}
