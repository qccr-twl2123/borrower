package com.trj.jk.web.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class RepayPlanInfoBean implements Serializable{

	private String periodNumber;
	
	private String repayDate;
	
	private String repayAmount;
	
	private Byte status;
	
	private String statusDescribe;
	
	private String repayedAmount;

	public String getPeriodNumber() {
		return periodNumber;
	}

	public void setPeriodNumber(String periodNumber) {
		this.periodNumber = periodNumber;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	public String getRepayAmount() {
		return repayAmount;
	}

	public void setRepayAmount(String repayAmount) {
		this.repayAmount = repayAmount;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getStatusDescribe() {
		return statusDescribe;
	}

	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}

	public String getRepayedAmount() {
		return repayedAmount;
	}

	public void setRepayedAmount(String repayedAmount) {
		this.repayedAmount = repayedAmount;
	}
	
}
