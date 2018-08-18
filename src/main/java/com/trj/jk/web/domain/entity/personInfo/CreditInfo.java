package com.trj.jk.web.domain.entity.personInfo;

import java.io.Serializable;

public class CreditInfo implements Serializable{
	
	/**
	 * 信用卡信息
	 */
	private Byte creditCard;
	/**
	 * 征信信息
	 */
	private Byte CreditInformation;
	public Byte getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(Byte creditCard) {
		this.creditCard = creditCard;
	}
	public Byte getCreditInformation() {
		return CreditInformation;
	}
	public void setCreditInformation(Byte creditInformation) {
		CreditInformation = creditInformation;
	}
	
	
}
