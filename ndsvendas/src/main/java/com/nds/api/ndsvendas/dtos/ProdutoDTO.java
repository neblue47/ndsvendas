package com.nds.api.ndsvendas.dtos;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.nds.api.ndsvendas.enums.EStatuStock;
import com.nds.api.ndsvendas.enums.EUserGroup;

public class ProdutoDTO {

	private UUID id;
	@NotNull
	@NotEmpty
	private String descricaoComercial;	
	
	private String descricaoTecnica;
	
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private Double preco;
	
	private int statuStock;	
	private String statuStockDescription;	
 
	private Double taxa; 	
 
	private Double preco_taxado;

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

	public int getStatuStock() {
		return statuStock;
	}

	public void setStatuStock(int statuStock) {
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

	public String getStatuStockDescription() {
		return  this.statuStock == 1 ? "Disponivel" : this.statuStock == 2 ? "Não Disponivel" : "Todos";
	}

	public void setStatuStockDescription(String statuStockDescription) {
		this.statuStockDescription = statuStockDescription;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	
}
