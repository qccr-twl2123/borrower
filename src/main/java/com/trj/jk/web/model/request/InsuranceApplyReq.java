package com.trj.jk.web.model.request;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 保险申请入参
 * @author xierongli
 * @version $$Id: trj-jk-web; v 0.1 2018/5/3 下午3:34 mark1xie Exp $$
 */
public class InsuranceApplyReq {
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "投保日期不能为空")
    private String applyDate;
    @NotBlank(message = "平台编号不能为空")
    private String platformApplyId;
    @NotBlank(message = "总保费不能为空")
    private String totalPremium;
    private String productCode;

    @NotBlank(message = "投保人证件类型不能为空")
    private String idType;
    @NotBlank(message = "投保人证件号不能为空")
    private String idNo;
    @NotBlank(message = "投保人姓名不能为空")
    private String name;
    @NotBlank(message = "投保人手机号不能为空")
    private String phone;

    private String insurancePeriodNum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getPlatformApplyId() {
        return platformApplyId;
    }

    public void setPlatformApplyId(String platformApplyId) {
        this.platformApplyId = platformApplyId;
    }

    public String getTotalPremium() {
        return totalPremium;
    }

    public void setTotalPremium(String totalPremium) {
        this.totalPremium = totalPremium;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInsurancePeriodNum() {
        return insurancePeriodNum;
    }

    public void setInsurancePeriodNum(String insurancePeriodNum) {
        this.insurancePeriodNum = insurancePeriodNum;
    }
}
