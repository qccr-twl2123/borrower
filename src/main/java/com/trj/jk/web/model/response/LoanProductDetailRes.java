package com.trj.jk.web.model.response;

import java.util.List;

/**
 * 借款产品详情响应模型
 * Created by xierongli on 17/8/9.
 */
public class LoanProductDetailRes {

    private Integer productId;
    /**产品名称*/
    private String productName;
    /**产品banner图片*/
    private String productUrl;
    /**最高额度文本*/
    private String amountDescribe;
    /**最高额度*/
    private String amount;
    /**产品代码*/
    private String productCode;

    private String productType;
    /**描述*/
    private String description;
    /**简短描述*/
    private String shortDescription;
    /**标签图片*/
    private String labelUrl;


    /**利率*/
    private String interest;
    private String interestDescribe;

    /**放款周期*/
    private String lendingTime;
    private String lendingTimeDescribe;

    /**期限*/
    private String term;
    private String termDescribe;
    /**攻略地址*/
    private String guideUrl;

    /**资料项目*/
    private List<LoanProductItemRes> loanProductItemResList;


    public String getLabelUrl() {
        return labelUrl;
    }

    public void setLabelUrl(String labelUrl) {
        this.labelUrl = labelUrl;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getInterestDescribe() {
        return interestDescribe;
    }

    public void setInterestDescribe(String interestDescribe) {
        this.interestDescribe = interestDescribe;
    }

    public String getLendingTime() {
        return lendingTime;
    }

    public void setLendingTime(String lendingTime) {
        this.lendingTime = lendingTime;
    }

    public String getLendingTimeDescribe() {
        return lendingTimeDescribe;
    }

    public void setLendingTimeDescribe(String lendingTimeDescribe) {
        this.lendingTimeDescribe = lendingTimeDescribe;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTermDescribe() {
        return termDescribe;
    }

    public void setTermDescribe(String termDescribe) {
        this.termDescribe = termDescribe;
    }

    public String getGuideUrl() {
        return guideUrl;
    }

    public void setGuideUrl(String guideUrl) {
        this.guideUrl = guideUrl;
    }

    public List<LoanProductItemRes> getLoanProductItemResList() {
        return loanProductItemResList;
    }

    public void setLoanProductItemResList(List<LoanProductItemRes> loanProductItemResList) {
        this.loanProductItemResList = loanProductItemResList;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
}
