package com.nds.api.ndsvendas.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
@Entity
@Table(name="TB_MOTIVOISENCAO")
public class MotivoIsencaoModel implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // , columnDefinition = "VARCHAR(36)"
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id;
	
	@NotNull
	@Column(length = 4)	
	private String taxCode;
	
	@NotNull
	private String description;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
