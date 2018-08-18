package com.trj.jk.web.model.dto;

/**
 * @author xierongli
 * @version $$Id: trj-jk-web, v 0.1 2018/5/4 上午11:14 mark1xie Exp $$
 */

public class InsuranceApplyDTO {

    private String platformApplyId;
    private String productCode;
    private String insuranceNum;
    private String resp_code;
    private String totalPremium;
    private String insurancePeriodNum;
    private String applyTime;
    private String resp_msg;
    private Integer applyStatus;

    public String getPlatformApplyId() {
        return platformApplyId;
    }

    public void setPlatformApplyId(String platformApplyId) {
        this.platformApplyId = platformApplyId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getInsuranceNum() {
        return insuranceNum;
    }

    public void setInsuranceNum(String insuranceNum) {
        this.insuranceNum = insuranceNum;
    }

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getTotalPremium() {
        return totalPremium;
    }

    public void setTotalPremium(String totalPremium) {
        this.totalPremium = totalPremium;
    }

    public String getInsurancePeriodNum() {
        return insurancePeriodNum;
    }

    public void setInsurancePeriodNum(String insurancePeriodNum) {
        this.insurancePeriodNum = insurancePeriodNum;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getResp_msg() {
        return resp_msg;
    }

    public void setResp_msg(String resp_msg) {
        this.resp_msg = resp_msg;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }
}
