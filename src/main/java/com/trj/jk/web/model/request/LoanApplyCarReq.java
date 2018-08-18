package com.trj.jk.web.model.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 借款申请车辆信息
 * Created by xierongli on 17/7/28.
 */
public class LoanApplyCarReq {

    private Integer uid;

    @NotBlank(message = "订车合同附件ID不为空")
    private String attachId;
    @NotNull(message = "借款ID不能为空")
    private Integer loanApplyId;
    @NotNull(message = "销售机构ID")
    private Integer salesOrganization;
    @NotBlank(message = "品牌不能空")
    private String brand;
    @NotBlank(message = "型号不能空")
    private String model;
    @NotNull(message = "裸车价不能为空")
    private BigDecimal nakedBikeAmount;

    @NotBlank(message = "请输入现居省份")
    private String residentialProvince;
    @NotBlank(message = "请输入现居城市")
    private String residentialCity;
    @NotBlank(message = "请输入现居地区")
    private String residentialDistrict;
    @NotBlank(message = "请输入现居地址")
    private String residentialAddress;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAttachId() {
        return attachId;
    }

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    public Integer getLoanApplyId() {
        return loanApplyId;
    }

    public void setLoanApplyId(Integer loanApplyId) {
        this.loanApplyId = loanApplyId;
    }

    public Integer getSalesOrganization() {
        return salesOrganization;
    }

    public void setSalesOrganization(Integer salesOrganization) {
        this.salesOrganization = salesOrganization;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getNakedBikeAmount() {
        return nakedBikeAmount;
    }

    public void setNakedBikeAmount(BigDecimal nakedBikeAmount) {
        this.nakedBikeAmount = nakedBikeAmount;
    }

    public String getResidentialProvince() {
        return residentialProvince;
    }

    public void setResidentialProvince(String residentialProvince) {
        this.residentialProvince = residentialProvince;
    }

    public String getResidentialCity() {
        return residentialCity;
    }

    public void setResidentialCity(String residentialCity) {
        this.residentialCity = residentialCity;
    }

    public String getResidentialDistrict() {
        return residentialDistrict;
    }

    public void setResidentialDistrict(String residentialDistrict) {
        this.residentialDistrict = residentialDistrict;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }
}
