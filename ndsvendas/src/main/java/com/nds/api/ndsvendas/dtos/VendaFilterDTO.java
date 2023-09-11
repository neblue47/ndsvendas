package com.nds.api.ndsvendas.dtos;

import java.util.Date;

import com.nds.api.ndsvendas.enums.ENumVenda;

public class VendaFilterDTO {
	private String dataInicial;	
	private String dataFim;	
	private int enumVenda;
	private ENumVenda enumeradoVenda;
	
	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	public int getEnumVenda() {
		return enumVenda;
	}
	public void setEnumVenda(int enumVenda) {
		this.enumVenda = enumVenda;
	}
	public ENumVenda getEnumeradoVenda() {
		return enumeradoVenda;
	}
	public void setEnumeradoVenda(ENumVenda enumeradoVenda) {
		this.enumeradoVenda = enumeradoVenda;
	}
	 
	
	
}
