package com.nds.api.ndsvendas.dtos;

import javax.validation.constraints.NotNull;

public class MotivoIsencaoDTO {

	private String taxCode; 
	private String description;
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
