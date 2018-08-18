package com.trj.jk.web.enums;

/**
 * 借款额度状态枚举
 * Created by xierongli on 17/9/15.
 */
public enum LoanLimitStatusEnum {


    INIT(1,"募集中"),
    RUNNING(2,"正常还款中"),
    DELAY(3,"已逾期"),
    FINISH(4,"已结束");

    private Integer code;
    private String description;


    LoanLimitStatusEnum(Integer code, String description) {
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
