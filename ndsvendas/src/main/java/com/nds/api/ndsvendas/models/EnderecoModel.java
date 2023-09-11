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
@Table(name="TB_ENDERECO")
public class EnderecoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id;
	@Column(nullable = false, length = 250)
	private String morada;
	@Column(nullable = false, length = 50)
	private String cidade;
	
	private String caixaPostal;
	@Column(nullable = false, length = 250)
	private String pais;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getMorada() {
		return morada;
	}
	public void setMorada(String morada) {
		this.morada = morada;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCaixaPostal() {
		return caixaPostal;
	}
	public void setCaixaPostal(String caixaPostal) {
		this.caixaPostal = caixaPostal;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	
}
