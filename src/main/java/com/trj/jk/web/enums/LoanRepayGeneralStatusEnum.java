package com.trj.jk.web.enums;

/**
 * Created by xierongli on 17/9/11.
 */
public enum LoanRepayGeneralStatusEnum {
    INIT(0,"未开始"),
    RUNNING(1,"正常还款中"),
    DONE(2,"还款结束"),
    EXCEPTION(3,"还款异常");

    private Integer code;
    private String description;


    LoanRepayGeneralStatusEnum(Integer code, String description) {
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
