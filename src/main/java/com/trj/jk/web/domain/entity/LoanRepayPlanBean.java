package com.trj.jk.web.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LoanRepayPlanBean implements Serializable{
    
    private Integer id;
    
    private Integer uid;

    private Integer repayInfoId;

    private String applyLoanId;
    
    private Byte status;

    private String repayDate;

    private BigDecimal amount;

    private BigDecimal repayedAmount;
    
    private Integer showstatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getRepayInfoId() {
		return repayInfoId;
	}

	public void setRepayInfoId(Integer repayInfoId) {
		this.repayInfoId = repayInfoId;
	}

	public String getApplyLoanId() {
		return applyLoanId;
	}

	public void setApplyLoanId(String applyLoanId) {
		this.applyLoanId = applyLoanId;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRepayedAmount() {
		return repayedAmount;
	}

	public void setRepayedAmount(BigDecimal repayedAmount) {
		this.repayedAmount = repayedAmount;
	}

	public Integer getShowstatus() {
		return showstatus;
	}

	public void setShowstatus(Integer showstatus) {
		this.showstatus = showstatus;
	}

}