package com.nds.api.ndsvendas.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
@Table(name="TB_ITEMVENDA")
public class ItemVendaModel implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // , columnDefinition = "VARCHAR(36)"
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id;	
	@NotNull
	private Integer quantidade;
	@NotNull
	private Double subtotal;
	
	private Double taxa;

	@ManyToOne // (cascade = CascadeType.ALL)
	private ProdutoModel produto;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public ProdutoModel getProduto() {
		return produto;
	}

	public void setProduto(ProdutoModel produto) {
		this.produto = produto;
	}
	 
	 
	
	
}
