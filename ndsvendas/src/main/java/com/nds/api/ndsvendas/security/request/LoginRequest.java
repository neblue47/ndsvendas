package com.nds.api.ndsvendas.security.request;

import com.nds.api.ndsvendas.security.enums.PerfilEnum;
 

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import lombok.*;
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class LoginRequest {

	@NotEmpty(message = "Campo Email é Obrigatório.")
	private String email;

	@NotEmpty(message = "Campo Password é Obrigatório.")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private PerfilEnum perfil;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	
}