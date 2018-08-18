package com.trj.jk.web.enums;

/**
 * 借款申请状态枚举
 * 用于51公积金
 * Created by xierongli on 17/9/16.
 */
public enum LoanApplyOpenStateEnum {
    AUDIT_RUNNING(3,"审核中"),
    AUDIT_ERROR(4,"审核失败"),
    AUDIT_SUCCESS(5,"审核成功"),

    FUND_COLLECT(6,"募集中"),
    FUND_RELEASE(7,"放款成功"),
    FUND_SETTLE(9,"已结清");

    private Integer code;
    private String description;

    LoanApplyOpenStateEnum(Integer code, String description) {
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
