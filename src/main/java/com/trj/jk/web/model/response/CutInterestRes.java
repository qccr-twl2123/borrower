package com.trj.jk.web.model.response;


import java.util.List;

/**
 * Created by xierongli on 17/9/13.
 */
public class CutInterestRes {

    /**贷款总金额*/
    private String loanAmount;
    /**砍头息金额*/
    private String cutAmount;
    /**可用金额*/
    private String limitAmount;
    /**利率*/
    private String interest;
    /**期限*/
    private Integer term;

    /**还款总金额(本息和)*/
    private String totalAmount;
    /**每月还款详情*/
    private List<MonthRepaymentRes> termList;

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getCutAmount() {
        return cutAmount;
    }

    public void setCutAmount(String cutAmount) {
        this.cutAmount = cutAmount;
    }

    public String getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(String limitAmount) {
        this.limitAmount = limitAmount;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<MonthRepaymentRes> getTermList() {
        return termList;
    }

    public void setTermList(List<MonthRepaymentRes> termList) {
        this.termList = termList;
    }
}
