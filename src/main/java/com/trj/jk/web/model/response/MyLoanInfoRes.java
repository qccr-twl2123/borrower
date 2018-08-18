package com.trj.jk.web.model.response;

import java.math.BigDecimal;

/**
 * 我的借款
 * Created by xierongli on 17/10/25.
 */
public class MyLoanInfoRes {

    /**在贷笔数*/
    private Integer loanQuantity;
    /**本月应还金额*/
    private BigDecimal RepayAmount;
    /**在贷金额*/
    private BigDecimal inLoanAmount;
    /**借款总额*/
    private BigDecimal loanAmount;

    public Integer getLoanQuantity() {
        return loanQuantity;
    }

    public void setLoanQuantity(Integer loanQuantity) {
        this.loanQuantity = loanQuantity;
    }

    public BigDecimal getRepayAmount() {
        return RepayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        RepayAmount = repayAmount;
    }

    public BigDecimal getInLoanAmount() {
        return inLoanAmount;
    }

    public void setInLoanAmount(BigDecimal inLoanAmount) {
        this.inLoanAmount = inLoanAmount;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }
}
