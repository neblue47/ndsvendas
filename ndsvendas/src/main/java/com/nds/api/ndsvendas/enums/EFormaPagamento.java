package com.nds.api.ndsvendas.enums;

public enum EFormaPagamento {

	ProntoPagamento(1),CreditoPagamento(2);
	
	public int enumValue;
	EFormaPagamento(int value) {
		enumValue = value;
	}
	public static EFormaPagamento fromId(int id) {
        for (EFormaPagamento type : values()) {
            if (type.enumValue == id) {
                return type;
            }
        } 
        return null;
    }
}
