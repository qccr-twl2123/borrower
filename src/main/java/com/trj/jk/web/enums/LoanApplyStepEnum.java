package com.trj.jk.web.enums;

/**
 * 借款申请步骤码枚举
 * Created by xierongli on 17/9/20.
 */
public enum LoanApplyStepEnum {

    INIT(0,"初始化"),
    FILL_CONTACT(1,"联系人信息"),
    FILL_COMPANY(2,"单位信息"),
    FILL_AUTH(3,"认证信息"),
    FILL_FACE_AUTH(4,"人脸是被"),;


    private Integer code;
    private String description;

    LoanApplyStepEnum(Integer code, String description) {
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
