package com.nds.api.ndsvendas.security.service.impl;

import com.nds.api.ndsvendas.security.entities.Login;
import com.nds.api.ndsvendas.security.enums.PerfilEnum;
import com.nds.api.ndsvendas.security.repository.LoginRepository;
import com.nds.api.ndsvendas.security.request.LoginJwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado: " + username));

        return User
                .builder()
                .username(login.getEmail())
                .password(login.getPassword())
                .authorities(mapToGrantedAuthorities(login.getPerfil()))
                .build();
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
        return authorities;
    }

    public UserDetails autenticar(LoginJwtRequest usuarioJwtRequest) throws Exception {
        UserDetails userDetails =  loadUserByUsername(usuarioJwtRequest.getEmail());
        boolean senhaValida = passwordEncoder.matches(usuarioJwtRequest.getPassword(), userDetails.getPassword());

        if(senhaValida){
            return userDetails;
        }
        throw new Exception("Senha Invalida.");
    }
    
    public Login getUserLogado(){
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if ( autenticado == null ) throw new AuthenticationCredentialsNotFoundException("Usuario nao logado");
		
		UserDetails usuarioLogado = (UserDetails) autenticado.getPrincipal();
		return loginRepository.findOneByEmail( usuarioLogado.getUsername() );
	}
}
