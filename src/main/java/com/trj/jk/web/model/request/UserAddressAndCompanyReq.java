package com.trj.jk.web.model.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 个人资料响应模型
 * Created by xierongli on 17/8/16.
 */
public class UserAddressAndCompanyReq {


    @NotNull(message = "借款ID不能为空")
    private Integer loanApplyId;

    /**居住地址*/
    @NotBlank(message = "居住地省份不为空")
    private String residentialProvince;
    @NotBlank(message = "居住地城市不为空")
    private String residentialCity;
    @NotBlank(message = "居住地地区不为空")
    private String residentialDistrict;
    @NotBlank(message = "居住地地址不为空")
    private String residentialAddress;

    /**公司地址*/
    @NotBlank(message = "公司地址省份不为空")
    private String corpProvince;
    @NotBlank(message = "公司地址城市不为空")
    private String corpCity;
    @NotBlank(message = "公司地址地区不为空")
    private String corpDistrict;
    @NotBlank(message = "公司地址地址不为空")
    private String corpAddress;

    @NotBlank(message = "公司名称不为空")
    private String corpName;
    @NotBlank(message = "部门不能为空")
    private String department;
    @NotBlank(message = "职位不能为空")
    private String position;

    public Integer getLoanApplyId() {
        return loanApplyId;
    }

    public void setLoanApplyId(Integer loanApplyId) {
        this.loanApplyId = loanApplyId;
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

    public String getCorpProvince() {
        return corpProvince;
    }

    public void setCorpProvince(String corpProvince) {
        this.corpProvince = corpProvince;
    }

    public String getCorpCity() {
        return corpCity;
    }

    public void setCorpCity(String corpCity) {
        this.corpCity = corpCity;
    }

    public String getCorpDistrict() {
        return corpDistrict;
    }

    public void setCorpDistrict(String corpDistrict) {
        this.corpDistrict = corpDistrict;
    }

    public String getCorpAddress() {
        return corpAddress;
    }

    public void setCorpAddress(String corpAddress) {
        this.corpAddress = corpAddress;
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
