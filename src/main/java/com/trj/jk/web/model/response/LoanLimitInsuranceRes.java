package com.trj.jk.web.model.response;

import java.math.BigDecimal;

/**
 * @author xierongli
 * @version $$Id: trj-jk-web, v 0.1 2018/5/2 下午3:25 mark1xie Exp $$
 */
public class LoanLimitInsuranceRes {
    private Integer term;
    private BigDecimal amount;

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
