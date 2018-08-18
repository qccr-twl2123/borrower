package com.trj.jk.web.enums;

/**
 * 用户地址类型
 * Created by xierongli on 17/8/9.
 */
public enum UserAddressEnum {
    MOREN(0,"默认"),
    RESIDENCE(1,"居住地"),
    COMPANY(2,"公司"),
    HOME(3,"家庭"),
    OTHER(4,"其他"),;

    private Integer code;
    private String description;


    UserAddressEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
