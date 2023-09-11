package com.nds.api.ndsvendas.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import org.hibernate.annotations.Type;
@Entity
@Table(name="TB_IMPOSTO")
public class ImpostosModel {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // , columnDefinition = "VARCHAR(36)"
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id;
	
	@NotNull
	@Column(length = 3)
	private String taxType;
	private String taxCountryRegion;
	
	@NotNull
	@Column(length = 3)	
	private String taxCode;
	
	@NotNull
	private String description;
	
	private Date taxExpirationDate;
	
	@Column(precision=10, scale=2)
	private Double taxPercentage;
	
	@Column(precision=10, scale=2)
	private Double taxAmount;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	public String getTaxCountryRegion() {
		return taxCountryRegion;
	}
	public void setTaxCountryRegion(String taxCountryRegion) {
		this.taxCountryRegion = taxCountryRegion;
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
	public Date getTaxExpirationDate() {
		return taxExpirationDate;
	}
	public void setTaxExpirationDate(Date taxExpirationDate) {
		this.taxExpirationDate = taxExpirationDate;
	}
	public Double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public Double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	
}
