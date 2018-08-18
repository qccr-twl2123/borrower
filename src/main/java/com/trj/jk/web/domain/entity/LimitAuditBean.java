package com.trj.jk.web.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.trj.jk.web.domain.Code;

public class LimitAuditBean implements Serializable{

	private Integer applyId;
	private Integer uid;
	private Byte status;
	/**借款状态*/
	private Byte showStatus;
	/**借款状态文本*/
	private String showStatusText;

	/**产品数据*/
	private Integer productId;
	private String productCode;
	private Integer productType;
	private String productName;

	/**申请数据*/
	private BigDecimal applyAmount;
	private String applyTime;
	private String vailDate;
	private Integer loanNumber;
	private BigDecimal loanAmount;


	/**审核数据*/
	private String auditTime;
	private BigDecimal auditAmount;
	private String repayType;
	private BigDecimal restLimit;

	private BigDecimal amountLimitCalculate1;
	private BigDecimal amountLimitCalculate2;
	private String term1;
	private List<String> term1List;
	private String term2;
	private List<String> term2List;

	private String termUnit;
	private BigDecimal interest;
	private Integer rateType;

	private List<Code> repayTypeList;
	private Map<String,Object> curTermRate;

	public String getShowStatusText() {
		return showStatusText;
	}

	public void setShowStatusText(String showStatusText) {
		this.showStatusText = showStatusText;
	}

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public BigDecimal getAuditAmount() {
		return auditAmount;
	}

	public void setAuditAmount(BigDecimal auditAmount) {
		this.auditAmount = auditAmount;
	}

	public BigDecimal getRestLimit() {
		return restLimit;
	}

	public void setRestLimit(BigDecimal restLimit) {
		this.restLimit = restLimit;
	}

	public String getVailDate() {
		return vailDate;
	}

	public void setVailDate(String vailDate) {
		this.vailDate = vailDate;
	}

	public Integer getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(Integer loanNumber) {
		this.loanNumber = loanNumber;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Byte getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Byte showStatus) {
		this.showStatus = showStatus;
	}

	public BigDecimal getAmountLimitCalculate1() {
		return amountLimitCalculate1;
	}

	public void setAmountLimitCalculate1(BigDecimal amountLimitCalculate1) {
		this.amountLimitCalculate1 = amountLimitCalculate1;
	}

	public BigDecimal getAmountLimitCalculate2() {
		return amountLimitCalculate2;
	}

	public void setAmountLimitCalculate2(BigDecimal amountLimitCalculate2) {
		this.amountLimitCalculate2 = amountLimitCalculate2;
	}

	public String getTerm1() {
		return term1;
	}

	public void setTerm1(String term1) {
		this.term1 = term1;
	}

	public String getTerm2() {
		return term2;
	}

	public void setTerm2(String term2) {
		this.term2 = term2;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Integer getRateType() {
		return rateType;
	}

	public void setRateType(Integer rateType) {
		this.rateType = rateType;
	}

	public List<String> getTerm1List() {
		return term1List;
	}

	public void setTerm1List(List<String> term1List) {
		this.term1List = term1List;
	}

	public List<String> getTerm2List() {
		return term2List;
	}

	public void setTerm2List(List<String> term2List) {
		this.term2List = term2List;
	}

	public String getTermUnit() {
		return termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	public List<Code> getRepayTypeList() {
		return repayTypeList;
	}

	public void setRepayTypeList(List<Code> repayTypeList) {
		this.repayTypeList = repayTypeList;
	}

	public Map<String, Object> getCurTermRate() {
		return curTermRate;
	}

	public void setCurTermRate(Map<String, Object> curTermRate) {
		this.curTermRate = curTermRate;
	}
}
