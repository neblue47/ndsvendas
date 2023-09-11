package com.nds.api.ndsvendas.enums;

public enum ETipoComercializacao {
	Gerais(1),	Farmaceuticos(2),	Servicos(3);
	
	public int enumValue;
	ETipoComercializacao(int value) {
		enumValue = value;
	}
	public static ETipoComercializacao fromId(int id) {
        for (ETipoComercializacao type : values()) {
            if (type.enumValue == id) {
                return type;
            }
        } 
        return null;
    }
}
