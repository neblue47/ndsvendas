package com.nds.api.ndsvendas.enums;

public enum EStatuStock {
	Disponivel(1),Indisponivel(2),Todos(0);
	
	public int enumValue;
	EStatuStock(int value) {
		enumValue = value;
	}
	public static EStatuStock fromId(int id) {
        for (EStatuStock type : values()) {
            if (type.enumValue == id) {
                return type;
            }
        } 
        return null;
    }
}
