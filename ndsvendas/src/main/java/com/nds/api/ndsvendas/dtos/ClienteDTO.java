package com.nds.api.ndsvendas.dtos;

import java.util.Date;
import java.util.UUID;

public class ClienteDTO {

	private UUID id;
	private UUID clienteId;
	private String nome;
	private String numeroNif; 
	private String numTelefone;
	private String morada;
	private String pais;
	private Double totalVenda;
	private Double totalPago;
	private Double totalDivida;
	private Date ultimaData;
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getClienteId() {
		return clienteId != null ? clienteId : id;
	}
	public void setClienteId(UUID clienteId) {
		this.clienteId = clienteId;
	}
	  
	public String getNome() {
		return nome;
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
	public String getNumTelefone() {
		return numTelefone;
	}
	public void setNumTelefone(String numTelefone) {
		this.numTelefone = numTelefone;
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
	public Double getTotalVenda() {
		return totalVenda;
	}
	public void setTotalVenda(Double totalVenda) {
		this.totalVenda = totalVenda;
	}
	public Double getTotalPago() {
		return totalPago;
	}
	public void setTotalPago(Double totalPago) {
		this.totalPago = totalPago;
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
	
	
}
