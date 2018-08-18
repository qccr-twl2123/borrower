package com.trj.jk.web.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class RepayRecordBean implements Serializable{
	
	private Integer recordId;

	private String orderNO;
	private String LoanNo;

	private String productType;
	
	private BigDecimal repayAmount;
	
	private String repayDate;
	

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getLoanNo() {
		return LoanNo;
	}

	public void setLoanNo(String loanNo) {
		LoanNo = loanNo;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public BigDecimal getRepayAmount() {
		return repayAmount;
	}

	public void setRepayAmount(BigDecimal repayAmount) {
		this.repayAmount = repayAmount;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	public String getOrderNO() {
		return orderNO;
	}

	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}
}
