package com.trj.jk.web.model.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by xierongli on 17/9/28.
 */
public class MonthlyRepayReq {

    @NotNull(message = "借款金额不能为空")
    private BigDecimal loanAmount;
    @NotBlank(message = "借款期限不能为空")
    private String term;
    @NotBlank(message = "还款方式不能为空")
    private String repayType;

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }
}
