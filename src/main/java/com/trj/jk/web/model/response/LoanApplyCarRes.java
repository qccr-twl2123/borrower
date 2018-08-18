package com.trj.jk.web.model.response;

import java.math.BigDecimal;
import java.util.List;

/**
 *借款申请车辆信息
 * Created by xierongli on 17/8/21.
 */
public class LoanApplyCarRes {

    private Integer uid;
    private Integer loanApplyId;
    private String model;
    private String brand;
    private String attachId;
    private BigDecimal nakedBikeAmount;
    private List<String> imgUrls;
    private String soName;
    private Integer salesOrganization;
    private String cityName;
    private String cityCode;

    private String residentialProvince;
    private String residentialCity;
    private String residentialDistrict;
    private String residentialAddress;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getLoanApplyId() {
        return loanApplyId;
    }

    public void setLoanApplyId(Integer loanApplyId) {
        this.loanApplyId = loanApplyId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAttachId() {
        return attachId;
    }

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    public BigDecimal getNakedBikeAmount() {
        return nakedBikeAmount;
    }

    public void setNakedBikeAmount(BigDecimal nakedBikeAmount) {
        this.nakedBikeAmount = nakedBikeAmount;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getSoName() {
        return soName;
    }

    public void setSoName(String soName) {
        this.soName = soName;
    }

    public Integer getSalesOrganization() {
        return salesOrganization;
    }

    public void setSalesOrganization(Integer salesOrganization) {
        this.salesOrganization = salesOrganization;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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
