package com.trj.jk.web.model.request;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 砍头息计算入参
 * Created by xierongli on 17/9/12.
 */
public class CutInterestReq {


    @NotNull(message = "还款期数获取为空")
    private Integer term;
    @NotNull(message = "贷款金额获取为空")
    private String amount;
    @NotBlank(message = "利率获取为空")
    private String interest;


    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
