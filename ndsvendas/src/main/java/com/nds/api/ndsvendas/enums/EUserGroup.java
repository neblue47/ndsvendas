package com.nds.api.ndsvendas.enums;

public enum EUserGroup {
	Admin(1),Gestor(2),Vendedor(3);
	
	public int enumValue;
	EUserGroup(int value) {
		enumValue = value;
	}
	public static EUserGroup fromId(int id) {
        for (EUserGroup type : values()) {
            if (type.enumValue == id) {
                return type;
            }
        } 
        return null;
    }
}
