package com.trj.jk.web.model.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户提款展示模型
 * @author xierongli
 * @version $$Id: trj-jk-web, v 0.1 2018/4/28 下午4:40 mark1xie Exp $$
 */
public class CashOutRes {
    /**授信金额*/
    private BigDecimal creditAmount;
    /**贷款期限*/
    private List<Integer> loanTermList;
    /**保险*/
    private List<LoanLimitInsuranceRes> loanLimitInsuranceResList;
    /**借款用途*/
    private List<String> loanUseTypeList;
    /**月利率*/
    private String monthRate;

    public String getMonthRate() {
        return monthRate;
    }

    public void setMonthRate(String monthRate) {
        this.monthRate = monthRate;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public List<Integer> getLoanTermList() {
        return loanTermList;
    }

    public void setLoanTermList(List<Integer> loanTermList) {
        this.loanTermList = loanTermList;
    }

    public List<LoanLimitInsuranceRes> getLoanLimitInsuranceResList() {
        return loanLimitInsuranceResList;
    }

    public void setLoanLimitInsuranceResList(List<LoanLimitInsuranceRes> loanLimitInsuranceResList) {
        this.loanLimitInsuranceResList = loanLimitInsuranceResList;
    }

    public List<String> getLoanUseTypeList() {
        return loanUseTypeList;
    }

    public void setLoanUseTypeList(List<String> loanUseTypeList) {
        this.loanUseTypeList = loanUseTypeList;
    }
}
