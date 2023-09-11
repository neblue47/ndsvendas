package com.nds.api.ndsvendas.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.nds.api.ndsvendas.dtos.VendaDTO;
import com.nds.api.ndsvendas.enums.EFormaPagamento;

@Entity
@Table(name="TB_VENDA")
public class VendaModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // , columnDefinition = "VARCHAR(36)"
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id;
	
	@NotNull
	private String numeroVenda;
	
	@ManyToOne 
	private ClienteModel cliente;
	@ManyToOne 
	private UtilizadorModel utilizador;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVenda;	
	
	@NotNull
	private Double totalVenda;	
	
	@NotNull
	private Double totalPago; 
	
	private int isAnulado;
	 
	private Date dataAnuladoVenda;
	
	private String motivoAnuladoVenda;	
	
	private EFormaPagamento formaPagamento;
	
	@OneToMany (cascade = CascadeType.ALL,fetch = FetchType.EAGER) 
	private List<ItemVendaModel> itemVenda  ;

	

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

	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}
	
	public UtilizadorModel getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(UtilizadorModel utilizador) {
		this.utilizador = utilizador;
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

	public int getIsAnulado() {
		return isAnulado;
	}

	public void setIsAnulado(int isAnulado) {
		this.isAnulado = isAnulado;
	}

	public Double getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(Double totalPago) {
		this.totalPago = totalPago;
	}
 

	public List<ItemVendaModel> getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(List<ItemVendaModel> itemVenda) {
		this.itemVenda = itemVenda;
	}
  
	public VendaModel() {
		 
	}

	public VendaModel(VendaDTO modelDTO) {
		this.totalPago = modelDTO.getTotalPago();
		this.totalVenda =  modelDTO.getTotalPago();
		this.dataVenda = modelDTO.getDataVenda();
		 
	}

	public Date getDataAnuladoVenda() {
		return dataAnuladoVenda;
	}

	public void setDataAnuladoVenda(Date dataAnuladoVenda) {
		this.dataAnuladoVenda = dataAnuladoVenda;
	}

	public String getMotivoAnuladoVenda() {
		return motivoAnuladoVenda;
	}

	public void setMotivoAnuladoVenda(String motivoAnuladoVenda) {
		this.motivoAnuladoVenda = motivoAnuladoVenda;
	}

	public EFormaPagamento getFormaPagamento() {
		
		return formaPagamento == null ? EFormaPagamento.ProntoPagamento : formaPagamento;
	}

	public void setFormaPagamento(EFormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	
}
