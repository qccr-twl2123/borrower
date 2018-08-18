package com.trj.jk.web.enums;

/**
 * 还款计划状态枚举
 * Created by xierongli on 17/9/20.
 */
public enum LoanRepayPlanStatusEnum {
    INIT(0,"未开始"),
    PART(1,"部分还款"),
    SUCCESS(2,"还款成功"),
    RUNNING(3,"还款中");

    private Integer code;
    private String description;

    LoanRepayPlanStatusEnum(Integer code, String description) {
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
