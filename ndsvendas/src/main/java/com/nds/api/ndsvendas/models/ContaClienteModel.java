package com.nds.api.ndsvendas.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
@Entity
@Table(name="TB_CONTACLIENTE")
public class ContaClienteModel {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // , columnDefinition = "VARCHAR(36)"
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id;	
	
	@ManyToOne
	private ClienteModel cliente;
	
	@NotNull
	private Double totalDivida; 
	
	@NotNull
	private Double totalCorrente; 
	
	@NotNull
	private Double totalVendas;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaData;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	 

	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public Double getTotalDivida() {
		return totalDivida;
	}

	public void setTotalDivida(Double totalDivida) {
		this.totalDivida = totalDivida;
	}

	public Double getTotalCorrente() {
		return totalCorrente;
	}

	public void setTotalCorrente(Double totalCorrente) {
		this.totalCorrente = totalCorrente;
	}

	public Double getTotalVendas() {
		return totalVendas;
	}

	public void setTotalVendas(Double totalVendas) {
		this.totalVendas = totalVendas;
	}

	public Date getUltimaData() {
		return ultimaData;
	}

	public void setUltimaData(Date ultimaData) {
		this.ultimaData = ultimaData;
	}	
	
	
}
