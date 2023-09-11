package com.nds.api.ndsvendas.security.entities;

import com.nds.api.ndsvendas.models.ClienteModel;
import com.nds.api.ndsvendas.models.UtilizadorModel;
import com.nds.api.ndsvendas.security.enums.PerfilEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.Type;
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="TB_LOGIN")
public class Login {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // , columnDefinition = "VARCHAR(36)"
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING) 
	@Column(name = "perfil", nullable = false)
	private PerfilEnum perfil;

	@OneToOne 
	private UtilizadorModel Utilizador;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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