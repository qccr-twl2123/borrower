package com.trj.jk.web.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ApplyDetailsBean implements Serializable{

	private Integer loanLimitId;
	
	private String applyNo;
	
	private String productName;
	
	private String  loanAmount;
	
	private String term;
	
	private String status;
	
	private  String waitRepayPeriods;
	
	private String repayDate;
	
	private String repayAmount;
	
	private String principal;
	
	private String interest;
	
	private String commissionCharge;
	
	private String penaltyInterestAmount;
	
	private String statusDescribe;

	public Integer getLoanLimitId() {
		return loanLimitId;
	}

	public void setLoanLimitId(Integer loanLimitId) {
		this.loanLimitId = loanLimitId;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWaitRepayPeriods() {
		return waitRepayPeriods;
	}

	public void setWaitRepayPeriods(String waitRepayPeriods) {
		this.waitRepayPeriods = waitRepayPeriods;
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

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getCommissionCharge() {
		return commissionCharge;
	}

	public void setCommissionCharge(String commissionCharge) {
		this.commissionCharge = commissionCharge;
	}

	public String getPenaltyInterestAmount() {
		return penaltyInterestAmount;
	}

	public void setPenaltyInterestAmount(String penaltyInterestAmount) {
		this.penaltyInterestAmount = penaltyInterestAmount;
	}

	public String getStatusDescribe() {
		return statusDescribe;
	}

	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}

	
	
}
