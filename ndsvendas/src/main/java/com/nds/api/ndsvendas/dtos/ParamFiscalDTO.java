package com.nds.api.ndsvendas.dtos;

import java.util.UUID;

public class ParamFiscalDTO {

	private UUID id;
	private int moeda;
	private String moedaDescription;
	private String cae;
	private String taxCode;
	private String motivoIsencao;
	private double taxa;  
	private UUID instituicaoId;	
	private int tipo;
	private String tipoDescription;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public int getMoeda() {
		return moeda;
	}
	public void setMoeda(int moeda) {
		this.moeda = moeda;
	}
	public String getMoedaDescription() {
		return moedaDescription;
	}
	public void setMoedaDescription(String moedaDescription) {
		this.moedaDescription = moedaDescription;
	}
	public String getCae() {
		return cae;
	}
	public void setCae(String cae) {
		this.cae = cae;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getMotivoIsencao() {
		return motivoIsencao;
	}
	public void setMotivoIsencao(String motivoIsencao) {
		this.motivoIsencao = motivoIsencao;
	}
	public double getTaxa() {
		return taxa;
	}
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	public UUID getInstituicaoId() {
		return instituicaoId;
	}
	public void setInstituicaoId(UUID instituicaoId) {
		this.instituicaoId = instituicaoId;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getTipoDescription() {
		return tipoDescription;
	}
	public void setTipoDescription(String tipoDescription) {
		this.tipoDescription = tipoDescription;
	} 
 	
	
  
	 
    
    
}
