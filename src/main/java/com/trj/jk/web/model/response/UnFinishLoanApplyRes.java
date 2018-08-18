package com.trj.jk.web.model.response;

import java.util.Date;

/**
 * 未完成借款申请DTO
 * Created by xierongli on 17/7/27.
 */
public class UnFinishLoanApplyRes {

    /**借款号ID(jk_loan_apply)*/
    private Integer id;
    private Integer productId;
    private String  productName;
    private Byte status;

    /**标志 0 否 1 最新*/
    private Integer isLast;
    /**步骤代码*/
    private String stepCode;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getIsLast() {
        return isLast;
    }

    public void setIsLast(Integer isLast) {
        this.isLast = isLast;
    }

    public String getStepCode() {
        return stepCode;
    }

    public void setStepCode(String stepCode) {
        this.stepCode = stepCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
