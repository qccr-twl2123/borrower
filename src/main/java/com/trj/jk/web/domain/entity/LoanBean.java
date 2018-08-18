package com.trj.jk.web.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LoanBean implements Serializable{

	//额度批复ID
	private Integer loanLimitId;
	
	//产品名称
	private String productName;
	
	//借款金额
	private BigDecimal useAmount; 
	
	private String term;
	
	private String termUnit;
	
	//借款期限
	private String termStr;
	
	private Byte status;
	
	//还款时间
	private String repayDate;
	
	//还款金额
	private BigDecimal repayAmount;
	
	//展示状态
	private Byte showStatus;
	
	//申请时间
	private String applyDate;
	
	//结束时间
	private String finishedDate;

	public Integer getLoanLimitId() {
		return loanLimitId;
	}

	public void setLoanLimitId(Integer loanLimitId) {
		this.loanLimitId = loanLimitId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getUseAmount() {
		return useAmount;
	}

	public void setUseAmount(BigDecimal useAmount) {
		this.useAmount = useAmount;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getTermUnit() {
		return termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	public String getTermStr() {
		return termStr;
	}

	public void setTermStr(String termStr) {
		this.termStr = termStr;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	public BigDecimal getRepayAmount() {
		return repayAmount;
	}

	public void setRepayAmount(BigDecimal repayAmount) {
		this.repayAmount = repayAmount;
	}

	public Byte getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Byte showStatus) {
		this.showStatus = showStatus;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(String finishedDate) {
		this.finishedDate = finishedDate;
	}
	
}
