package com.nds.api.ndsvendas.models;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
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
@Table(name="TB_PARAMETRO")
public class ParametrosModel {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // , columnDefinition = "VARCHAR(36)"
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id;
	
	private String cae;
	@OneToOne(fetch = FetchType.EAGER)
	private ImpostosModel imposto;
	
	@OneToOne(fetch = FetchType.EAGER)
	private InstituicaoModel instituicao;
	
	private String regime;
	
	private String motivo_isencao;
	
    @ElementCollection
    @Enumerated
	private List<ETipoComercializacao> tipo;
    @Enumerated
    private EMoeda moeda;
	
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
	public ImpostosModel getImposto() {
		return imposto;
	}
	public void setImposto(ImpostosModel imposto) {
		this.imposto = imposto;
	}
	public String getRegime() {
		return regime;
	}
	public void setRegime(String regime) {
		this.regime = regime;
	}
	public String getMotivo_isencao() {
		return motivo_isencao;
	}
	public void setMotivo_isencao(String motivo_isencao) {
		this.motivo_isencao = motivo_isencao;
	}
	public List<ETipoComercializacao> getTipo() {
		return tipo;
	}
	public void setTipo(List<ETipoComercializacao> tipo) {
		this.tipo = tipo;
	}
	public EMoeda getMoeda() {
		return moeda;
	}
	public void setMoeda(EMoeda moeda) {
		this.moeda = moeda;
	}
	public InstituicaoModel getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(InstituicaoModel instituicao) {
		this.instituicao = instituicao;
	}
    
    
}
