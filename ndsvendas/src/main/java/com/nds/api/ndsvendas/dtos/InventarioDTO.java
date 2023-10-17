package com.nds.api.ndsvendas.dtos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.nds.api.ndsvendas.enums.EUnidade;
import com.nds.api.ndsvendas.enums.EUserGroup;

public class InventarioDTO {

	private UUID inventarioId; 	 
	private UUID productId; 
	private String productDescricao;
	private int unidade; 
	private int quantidade;
	private String unidadeDescription;
	private double valor;
	private String lote;
	private Date dataEntrada; 
	private String dataEntradaString;
	private UUID utilizadorId;
	private String utilizadorName;
	public UUID getInventarioId() {
		return inventarioId;
	}
	public void setInventarioId(UUID inventarioId) {
		this.inventarioId = inventarioId;
	}
	public UUID getProductId() {
		return productId;
	}
	public void setProductId(UUID productId) {
		this.productId = productId;
	}
	public String getProductDescricao() {
		return productDescricao;
	}
	public void setProductDescricao(String productDescricao) {
		this.productDescricao = productDescricao;
	}
	public int getUnidade() {
		return unidade;
	}
	public void setUnidade(int unidade) {
		this.unidade = unidade;
	}
	public String getUnidadeDescription() {
		return EUnidade.fromId(unidade).name().toUpperCase();
	}
	public void setUnidadeDescription(String unidadeDescription) {
		this.unidadeDescription = unidadeDescription;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
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
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getDataEntradaString() {
		var date = this.dataEntrada.getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        dataEntradaString = dateFormat.format(date);  
         
		return dataEntradaString;
	}
		
}


