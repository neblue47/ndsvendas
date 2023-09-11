package com.nds.api.ndsvendas.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.nds.api.ndsvendas.enums.EMoeda;
import com.nds.api.ndsvendas.enums.ETipoComercializacao;
@Entity
@Table(name="TB_PARAMFISCAL")
public class ParamFiscalModel implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // , columnDefinition = "VARCHAR(36)"
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id;
	private EMoeda moeda;
	private String cae;
	private String taxCode;
	private String motivoIsencao;
	private double taxa;
	private ETipoComercializacao tipo;
	@OneToOne(fetch = FetchType.EAGER)
	private InstituicaoModel instituicao;
	
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getCae() {
		return cae;
	}
	public void setCae(String cae) {
		this.cae = cae;
	}
	public EMoeda getMoeda() {
		return moeda;
	}
	public void setMoeda(EMoeda moeda) {
		this.moeda = moeda;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getMotivoIsencao() {
		return motivoIsencao;
	}
	public void setMotivoIsencao(String motivoIsencao) {
		this.motivoIsencao = motivoIsencao;
	}
	public ETipoComercializacao getTipo() {
		return tipo;
	}
	public void setTipo(ETipoComercializacao tipo) {
		this.tipo = tipo;
	}
	public InstituicaoModel getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(InstituicaoModel instituicao) {
		this.instituicao = instituicao;
	}
	public double getTaxa() {
		return taxa;
	}
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	
	
}
