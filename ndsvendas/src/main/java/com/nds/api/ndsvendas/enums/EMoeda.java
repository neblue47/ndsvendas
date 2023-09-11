package com.nds.api.ndsvendas.enums;

public enum EMoeda {

	Kz(1),	USD(2); 
	
	public int enumValue;
	
	EMoeda(int value) {
		enumValue = value;
	}
	public static EMoeda fromId(int id) {
        for (EMoeda type : values()) {
            if (type.enumValue == id) {
                return type;
            }
        } 
        return null;
    }
}
