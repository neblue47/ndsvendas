package com.nds.api.ndsvendas.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.nds.api.ndsvendas.enums.EUnidade;

@Entity
@Table(name="TB_INVENTARIO")
public class InventarioModel implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // , columnDefinition = "VARCHAR(36)"
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id; 	
	@ManyToOne // (cascade = CascadeType.ALL)
	private ProdutoModel produto; 
	private EUnidade unidade;  
	private double valor;
	private int quantidade;
	private String lote;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrada; 
	@ManyToOne 
	private UtilizadorModel utilizador;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public ProdutoModel getProduto() {
		return produto;
	}
	public void setProduto(ProdutoModel produto) {
		this.produto = produto;
	}
	public EUnidade getUnidade() {
		return unidade;
	}
	public void setUnidade(EUnidade unidade) {
		this.unidade = unidade;
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
	public UtilizadorModel getUtilizador() {
		return utilizador;
	}
	public void setUtilizador(UtilizadorModel utilizador) {
		this.utilizador = utilizador;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	} 
	
	
}
