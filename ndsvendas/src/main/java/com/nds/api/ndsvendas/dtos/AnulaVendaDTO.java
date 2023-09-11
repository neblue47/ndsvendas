package com.nds.api.ndsvendas.dtos;

import java.util.Date;
import java.util.UUID;

public class AnulaVendaDTO {

	private UUID id;
	private UUID utilizadorId;	
	private Date data;	
	private String motivo;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getUtilizadorId() {
		return utilizadorId;
	}
	public void setUtilizadorId(UUID utilizadorId) {
		this.utilizadorId = utilizadorId;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	
}
