package com.nds.api.ndsvendas.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
@Table(name="TB_INSTITUICAO")
public class InstituicaoModel {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // , columnDefinition = "VARCHAR(36)"
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id;
	
	@NotNull
	@NotEmpty
	private String designacaoFormal; 
	private String designacaoComercial;
	private String numeroFiscal;
	private String numeroTelefone; 
	private String endereco;
	private String missaoVisao;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getDesignacaoFormal() {
		return designacaoFormal;
	}
	public void setDesignacaoFormal(String designacaoFormal) {
		this.designacaoFormal = designacaoFormal;
	}
	public String getDesignacaoComercial() {
		return designacaoComercial;
	}
	public void setDesignacaoComercial(String designacaoComercial) {
		this.designacaoComercial = designacaoComercial;
	}
	public String getNumeroFiscal() {
		return numeroFiscal;
	}
	public void setNumeroFiscal(String numeroFiscal) {
		this.numeroFiscal = numeroFiscal;
	}
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getMissaoVisao() {
		return missaoVisao;
	}
	public void setMissaoVisao(String missaoVisao) {
		this.missaoVisao = missaoVisao;
	}
	
	
	
}
