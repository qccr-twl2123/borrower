package com.trj.jk.web.model.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 *
 * 借款地址信息 入参
 * Created by xierongli on 17/8/9.
 */
public class LoanApplyAddressReq {

    /**借款ID*/
    @NotNull(message = "借款ID 不为空")
    private Integer loanApplyId;
    /**地址信息*/
    @NotBlank(message = "地址信息不能为空")
    private String addressInfo;
    /**公司名称*/
    @NotBlank(message = "公司名称不为空")
    private String corpName;
    /**部门*/
    private String department;

    /**职位*/
    private String position;

    public Integer getLoanApplyId() {
        return loanApplyId;
    }

    public void setLoanApplyId(Integer loanApplyId) {
        this.loanApplyId = loanApplyId;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
