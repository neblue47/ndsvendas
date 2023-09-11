package com.nds.api.ndsvendas.dtos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.ManyToOne;

import com.nds.api.ndsvendas.models.ClienteModel;
import com.nds.api.ndsvendas.models.UtilizadorModel;

public class VendaDTO {

	private UUID id;	
	private String numeroVenda;
	private Date dataVenda;	
	private String dataVendaString;
	private Double totalVenda;
	private Double totalPago;
	private Double totalDivida;
	private Double currentDivida;
	private int isAnulado;
	
	private UUID clienteId;
	private String nomeCliente;
	private String numeroNif; 
	private String numTelefone;
	private String morada;
	private String pais;
	private int formaPagamento;
	
 
	private UUID utilizadorId;
	private String utilizadorName;
	
	private List<ItemVendaDTO> vendaItens ;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNumeroVenda() {
		return numeroVenda;
	}

	public void setNumeroVenda(String numeroVenda) {
		this.numeroVenda = numeroVenda;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
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

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroNif() {
		return numeroNif;
	}

	public void setNumeroNif(String numeroNif) {
		this.numeroNif = numeroNif;
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
	
	 

	public String getNumTelefone() {
		return numTelefone;
	}

	public void setNumTelefone(String numTelefone) {
		this.numTelefone = numTelefone;
	}

	public List<ItemVendaDTO> getVendaItens() {
		return vendaItens;
	}

	public void setVendaItens(List<ItemVendaDTO> vendaItens) {
		this.vendaItens = vendaItens;
	}

	public UUID getClienteId() {
		return clienteId;
	}

	public void setClienteId(UUID clienteId) {
		this.clienteId = clienteId;
	}
	
	public int getIsAnulado() {
		return isAnulado;
	}

	public void setIsAnulado(int isAnulado) {
		this.isAnulado = isAnulado;
	}

	public void setClienteData(ClienteModel cliente) {
		this.clienteId = cliente.getId();
		this.nomeCliente = cliente.getNome();
		this.numeroNif = cliente.getNumeroNif();
		this.numTelefone = cliente.getNumTelefone();
		this.morada = cliente.getMorada();
		this.pais = cliente.getPais();
		
	}

	public String getDataVendaString() {
		var date = this.dataVenda.getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        dataVendaString = dateFormat.format(date);  
         
		return dataVendaString;
	}

	public int getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(int formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Double getTotalDivida() {
		return totalDivida ;
	}

	public void setTotalDivida(Double totalDivida) {
		this.totalDivida = totalDivida;
	}

	public Double getCurrentDivida() {
		return formaPagamento == 2 ? totalVenda - totalPago : 0;
	}

	public UUID getUtilizadorId() {
		return utilizadorId;
	}

	public void setUtilizadorId(UUID utilizadorId) {
		this.utilizadorId = utilizadorId;
	}

	public String getUtilizadorName() {
		return utilizadorName;
	}

	public void setUtilizadorName(String utilizadorName) {
		this.utilizadorName = utilizadorName;
	}
	public void setUtilizadorData(UtilizadorModel utilizador) {
		this.utilizadorId = utilizador.getId();
		this.utilizadorName = utilizador.getFullName(); 		
	}
	
	
}
 

 
