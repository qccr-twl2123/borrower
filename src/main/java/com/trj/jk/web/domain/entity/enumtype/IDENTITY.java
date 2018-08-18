package com.trj.jk.web.domain.entity.enumtype;

public enum IDENTITY {
	IDENTITY_CARD(1,"IDENTITY_CARD");
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	int value;
	String description;
	
	IDENTITY(int val, String des){
		this.value = val;
		this.description = des;
	}
}
