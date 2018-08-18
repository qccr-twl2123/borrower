package com.trj.jk.web.model.request;

import com.trj.jk.web.domain.entity.regex.RegexContext;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 用户职业信息业务对象
 * Created by xierongli on 17/7/28.
 */
public class UserProfessionReq {

    /**用户职业表主键ID (jk_user_profession)*/
    private Integer id;
    @NotNull(message = "借款ID不能为空")
    private Integer loanApplyId;
    @NotBlank(message = "步骤码不能为空")
    private String stepCode;

    private Integer uid;

    @NotBlank(message = "公司名称不能为空")
    private String corpName;
    @NotBlank(message = "所属行业不能为空")
    private String industry;
    @NotBlank(message = "公司所属省份不能为空")
    private String corpProvince;
    @Pattern(regexp = RegexContext.EMAIL_REGEX,message = "邮箱地址格式错误")
    private String corpEmail;

    @NotBlank(message = "公司所属城市不能为空")
    private String corpCity;
    @NotBlank(message = "所属地区不能为空")
    private String corpDistrict;
    @NotBlank(message = "公司地址不能为空")
    private String corpAddress;
    @NotBlank(message = "所属部门不能为空")
    private String department;

    @NotBlank(message = "所属部门不能为空")
    private String position;
    @NotBlank(message = "所属部门不能为空")
    private String corpTel;
    @NotBlank(message = "所属部门不能为空")
    private String salaryDay;
    @NotBlank(message = "所属部门不能为空")
    private String salaryScope;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStepCode() {
        return stepCode;
    }

    public void setStepCode(String stepCode) {
        this.stepCode = stepCode;
    }

    public Integer getLoanApplyId() {
        return loanApplyId;
    }

    public void setLoanApplyId(Integer loanApplyId) {
        this.loanApplyId = loanApplyId;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCorpProvince() {
        return corpProvince;
    }

    public void setCorpProvince(String corpProvince) {
        this.corpProvince = corpProvince;
    }

    public String getCorpEmail() {
        return corpEmail;
    }

    public void setCorpEmail(String corpEmail) {
        this.corpEmail = corpEmail;
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

    public String getCorpTel() {
        return corpTel;
    }

    public void setCorpTel(String corpTel) {
        this.corpTel = corpTel;
    }

    public String getSalaryDay() {
        return salaryDay;
    }

    public void setSalaryDay(String salaryDay) {
        this.salaryDay = salaryDay;
    }

    public String getSalaryScope() {
        return salaryScope;
    }

    public void setSalaryScope(String salaryScope) {
        this.salaryScope = salaryScope;
    }
}
