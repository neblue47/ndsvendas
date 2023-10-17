package com.nds.api.ndsvendas.enums;

public enum EUnidade {

	Unitario(1),Caixa(2),Saqueta(3);
	
	public int enumValue;
	EUnidade(int value) {
		enumValue = value;
	}
	public static EUnidade fromId(int id) {
        for (EUnidade type : values()) {
            if (type.enumValue == id) {
                return type;
            }
        } 
        return null;
    }
}
