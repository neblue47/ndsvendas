package com.nds.api.ndsvendas.dtos;

import java.util.UUID; 
public class InstituicaoDTO {

	private UUID id; 
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
