package com.trj.jk.web.model.dto;

/**
 * 用户地址信息DTO
 * Created by xierongli on 17/8/9.
 */
public class UserAddressDTO {

    /**地址类型: 0默认  1,居住地址  2,公司地址 3，家庭地址 4, 其他*/
    private byte type;
    private String province;
    private String city;
    private String district;
    private String address;

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
