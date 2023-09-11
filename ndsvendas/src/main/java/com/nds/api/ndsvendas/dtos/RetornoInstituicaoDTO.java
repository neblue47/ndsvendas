package com.nds.api.ndsvendas.dtos;

public class RetornoInstituicaoDTO {

	 
	private InstituicaoDTO instituicaoViews; 
	private ParamFiscalDTO dadosFiscaisViews;

	public InstituicaoDTO getInstituicaoViews() {
		return instituicaoViews;
	}

	public void setInstituicaoViews(InstituicaoDTO instituicaoViews) {
		this.instituicaoViews = instituicaoViews;
	}

	public ParamFiscalDTO getDadosFiscaisViews() {
		return dadosFiscaisViews;
	}

	public void setDadosFiscaisViews(ParamFiscalDTO dadosFiscaisViews) {
		this.dadosFiscaisViews = dadosFiscaisViews;
	} 

	 
	
	
}
