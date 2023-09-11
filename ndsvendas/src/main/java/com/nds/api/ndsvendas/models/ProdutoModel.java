package com.nds.api.ndsvendas.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.nds.api.ndsvendas.enums.EStatuStock;

@Entity
@Table(name="TB_PRODUTO")
public class ProdutoModel implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // , columnDefinition = "VARCHAR(36)"
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id;
	
	@NotNull
	@NotEmpty
	private String descricaoComercial;	
	@NotNull
	@NotEmpty
	private String descricaoTecnica;
	
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private Double preco;
	
	private EStatuStock statuStock;
	
	private Double taxa; 	
	 
	private Double preco_taxado;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescricaoComercial() {
		return descricaoComercial;
	}

	public void setDescricaoComercial(String descricaoComercial) {
		this.descricaoComercial = descricaoComercial;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public EStatuStock getStatuStock() {
		return statuStock;
	}

	public void setStatuStock(EStatuStock statuStock) {
		this.statuStock = statuStock;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public Double getPreco_taxado() {
		return preco_taxado;
	}

	public void setPreco_taxado(Double preco_taxado) {
		this.preco_taxado = preco_taxado;
	}

	public String getDescricaoTecnica() {
		return descricaoTecnica;
	}

	public void setDescricaoTecnica(String descricaoTecnica) {
		this.descricaoTecnica = descricaoTecnica;
	}
	
	
 
}
