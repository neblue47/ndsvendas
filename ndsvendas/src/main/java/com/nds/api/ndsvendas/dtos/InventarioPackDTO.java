package com.nds.api.ndsvendas.dtos;

import java.util.List;
import java.util.UUID;

public class InventarioPackDTO {
	
	private UUID utilizadorId; 
	private List<InventarioDTO>  itemEntradaArray;
	
	public UUID getUtilizadorId() {
		return utilizadorId;
	}
	public void setUtilizadorId(UUID utilizadorId) {
		this.utilizadorId = utilizadorId;
	}
	public List<InventarioDTO> getItemEntradaArray() {
		return itemEntradaArray;
	}
	public void setItemEntradaArray(List<InventarioDTO> itemEntradaArray) {
		this.itemEntradaArray = itemEntradaArray;
	}
	 	
	
}
