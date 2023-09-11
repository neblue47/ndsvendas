package com.nds.api.ndsvendas.dtos;

import java.util.UUID;

public class ItemVendaDTO {
	private UUID itemId;
	private double subtotal;	
	private double preco;	
	private int quantidade;
	private String descricaoItem;
	
	public UUID getItemId() {
		return itemId;
	}
	public void setItemId(UUID itemId) {
		this.itemId = itemId;
	}
	 
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getDescricaoItem() {
		return descricaoItem;
	}
	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}
}
