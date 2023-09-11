package com.nds.api.ndsvendas.dtos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ContaClienteDTO {

	private UUID id;
	private Double totalVendas;
	private Double totalCorrente;
	private Double totalDivida;
	private Date ultimaData;
	private String ultimaDataString;
	private ClienteDTO cliente;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
 
	public Double getTotalVendas() {
		return totalVendas;
	}
	public void setTotalVendas(Double totalVendas) {
		this.totalVendas = totalVendas;
	}
	public Double getTotalCorrente() {
		return totalCorrente;
	}
	public void setTotalCorrente(Double totalCorrente) {
		this.totalCorrente = totalCorrente;
	}
	public Double getTotalDivida() {
		return totalDivida;
	}
	public void setTotalDivida(Double totalDivida) {
		this.totalDivida = totalDivida;
	}
	public Date getUltimaData() {
		return ultimaData;
	}
	public void setUltimaData(Date ultimaData) {
		this.ultimaData = ultimaData;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	
	public String getUltimaDataString() {
		var date = this.ultimaData.getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        ultimaDataString = dateFormat.format(date);  
         
		return ultimaDataString;
	}
}
