package com.nds.api.ndsvendas.enums;

public enum ENumVenda {
	Anulada(1),NaoAnulada(2),Todas(0);
	
	public int enumValue;
	ENumVenda(int value) {
		enumValue = value;
	}
	public static ENumVenda fromId(int id) {
        for (ENumVenda type : values()) {
            if (type.enumValue == id) {
                return type;
            }
        } 
        return null;
    }
}
