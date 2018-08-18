package com.trj.jk.web.model.response;

/**
 * 地址信息and 公司信息
 * Created by xierongli on 17/8/16.
 */
public class UserAddressAndCompanyRes {

    /**居住地址*/
    private String residentialProvince;
    private String residentialCity;
    private String residentialDistrict;
    private String residentialAddress;

    /**公司地址*/
    private String corpProvince;
    private String corpCity;
    private String corpDistrict;
    private String corpAddress;

    private String corpName;
    private String department;
    private String position;

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
