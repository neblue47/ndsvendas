package com.nds.api.ndsvendas.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="TB_CLIENTE")
public class ClienteModel implements Serializable{
 
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // , columnDefinition = "VARCHAR(36)"
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id;	
	@Column(nullable = false, length = 250) 
	private String nome;	
	@Column(nullable = false, unique = true, length = 20)
	private String numeroNif; 
	private String numTelefone;
	private boolean autoFacturacao;
	
	
	@Column(nullable = false, length = 250)
	private String morada;  
	 
	@Column(nullable = false, length = 250)
	private String pais;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	 

	public String getNumTelefone() {
		return numTelefone;
	}

	public void setNumTelefone(String numTelefone) {
		this.numTelefone = numTelefone;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroNif() {
		return numeroNif;
	}

	public void setNumeroNif(String numeroNif) {
		this.numeroNif = numeroNif;
	}

	public boolean isAutoFacturacao() {
		return autoFacturacao;
	}

	public void setAutoFacturacao(boolean autoFacturacao) {
		this.autoFacturacao = autoFacturacao;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (autoFacturacao ? 1231 : 1237);
		result = prime * result + ((morada == null) ? 0 : morada.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numTelefone == null) ? 0 : numTelefone.hashCode());
		result = prime * result + ((numeroNif == null) ? 0 : numeroNif.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteModel other = (ClienteModel) obj;
		if (autoFacturacao != other.autoFacturacao)
			return false;
		if (morada == null) {
			if (other.morada != null)
				return false;
		} else if (!morada.equals(other.morada))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numTelefone == null) {
			if (other.numTelefone != null)
				return false;
		} else if (!numTelefone.equals(other.numTelefone))
			return false;
		if (numeroNif == null) {
			if (other.numeroNif != null)
				return false;
		} else if (!numeroNif.equals(other.numeroNif))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}
	
	 

	
}
