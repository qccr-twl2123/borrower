package com.trj.jk.web.model.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 签章入参
 * Created by xierongli on 17/9/15.
 */
public class SignatureReq {

    @NotBlank(message = "订单号获取失败")
    private String orderNo;
    @NotBlank(message = "验证码获取失败")
    private String verifyCode;
    @NotNull(message = "用款金额不能为空")
    @DecimalMin(value = "1000.00",message = "使用金额需大于1000元")
    private BigDecimal useAmount;

    private String loanUseType;


    @NotNull(message = "砍头息不能为空")
    private BigDecimal cutCharge;
    @NotBlank(message = "用款期限不能为空")
    private String  term;
    @NotNull(message = "总费率不能为空")
    private BigDecimal interest;
    @NotNull(message = "放款银行卡ID不能为空")
    private Integer  bankCardId;
    @NotNull(message = "预计每月还款金额不能为空")
    private BigDecimal expectRepayAmount;

    public String getLoanUseType() {
        return loanUseType;
    }

    public void setLoanUseType(String loanUseType) {
        this.loanUseType = loanUseType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public BigDecimal getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
    }


    public BigDecimal getCutCharge() {
        return cutCharge;
    }

    public void setCutCharge(BigDecimal cutCharge) {
        this.cutCharge = cutCharge;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Integer getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(Integer bankCardId) {
        this.bankCardId = bankCardId;
    }

    public BigDecimal getExpectRepayAmount() {
        return expectRepayAmount;
    }

    public void setExpectRepayAmount(BigDecimal expectRepayAmount) {
        this.expectRepayAmount = expectRepayAmount;
    }
}
