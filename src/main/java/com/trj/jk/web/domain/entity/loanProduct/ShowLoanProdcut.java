package com.trj.jk.web.domain.entity.loanProduct;


import java.io.Serializable;


public class ShowLoanProdcut  implements Serializable{
	
	private Integer productId;

	private String productName;
	
	private String productType;

	private String productIconUrl;
	
	private String amountDescribe;
	
	private String  amount;
	
	private String firstIllustrationUrl;
	
	private String secondIllustrationUrl;
	
	private String interestDescribe;
	
	private String interest;
	
	private String lendingTimeDescribe;
	
	private String lendingTime;
	
	private String termDescribe;
	
	private String term;
	
	private Byte isOpen;


	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductIconUrl() {
		return productIconUrl;
	}

	public void setProductIconUrl(String productIconUrl) {
		this.productIconUrl = productIconUrl;
	}

	public String getAmountDescribe() {
		return amountDescribe;
	}

	public void setAmountDescribe(String amountDescribe) {
		this.amountDescribe = amountDescribe;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFirstIllustrationUrl() {
		return firstIllustrationUrl;
	}

	public void setFirstIllustrationUrl(String firstIllustrationUrl) {
		this.firstIllustrationUrl = firstIllustrationUrl;
	}

	public String getSecondIllustrationUrl() {
		return secondIllustrationUrl;
	}

	public void setSecondIllustrationUrl(String secondIllustrationUrl) {
		this.secondIllustrationUrl = secondIllustrationUrl;
	}

	public String getInterestDescribe() {
		return interestDescribe;
	}

	public void setInterestDescribe(String interestDescribe) {
		this.interestDescribe = interestDescribe;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getLendingTimeDescribe() {
		return lendingTimeDescribe;
	}

	public void setLendingTimeDescribe(String lendingTimeDescribe) {
		this.lendingTimeDescribe = lendingTimeDescribe;
	}

	public String getLendingTime() {
		return lendingTime;
	}

	public void setLendingTime(String lendingTime) {
		this.lendingTime = lendingTime;
	}

	public String getTermDescribe() {
		return termDescribe;
	}

	public void setTermDescribe(String termDescribe) {
		this.termDescribe = termDescribe;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Byte getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Byte isOpen) {
		this.isOpen = isOpen;
	}
}
