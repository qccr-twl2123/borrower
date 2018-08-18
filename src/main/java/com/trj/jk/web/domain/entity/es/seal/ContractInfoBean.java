package com.trj.jk.web.domain.entity.es.seal;

import java.io.Serializable;

public class ContractInfoBean implements Serializable{
	
	private String contractId;

	private String name;

	private String amount;
	
	private String guaranteeYear;
	
	private String guaranteeMonth;
	
	private String guaranteeDay;
	
	private String signatureYear;
	
	private String signatureMonth;
	
	private String signatureDay;

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getGuaranteeYear() {
		return guaranteeYear;
	}

	public void setGuaranteeYear(String guaranteeYear) {
		this.guaranteeYear = guaranteeYear;
	}

	public String getGuaranteeMonth() {
		return guaranteeMonth;
	}

	public void setGuaranteeMonth(String guaranteeMonth) {
		this.guaranteeMonth = guaranteeMonth;
	}

	public String getGuaranteeDay() {
		return guaranteeDay;
	}

	public void setGuaranteeDay(String guaranteeDay) {
		this.guaranteeDay = guaranteeDay;
	}

	public String getSignatureYear() {
		return signatureYear;
	}

	public void setSignatureYear(String signatureYear) {
		this.signatureYear = signatureYear;
	}

	public String getSignatureMonth() {
		return signatureMonth;
	}

	public void setSignatureMonth(String signatureMonth) {
		this.signatureMonth = signatureMonth;
	}

	public String getSignatureDay() {
		return signatureDay;
	}

	public void setSignatureDay(String signatureDay) {
		this.signatureDay = signatureDay;
	}
	
	
}
