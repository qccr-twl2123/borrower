package com.trj.jk.web.enums;

/**
 * 用户展示借款申请状态枚举
 * Created by xierongli on 17/9/12.
 */
public enum UserLoanShowStatusEnum {
    RETRY(0,"重新申请"),
    NO_PASS(1,"审批未通过"),
    DETAIL(2,"查看详情"),
    APPLY_LOAN(3,"申请放款"),

    AUDIT_RUNNING(4,"审批中"),
    DELAY(5,"已逾期"),
    REPAYMENT(6,"正常还款"),
    SETTLE(7,"已结清"),
    RAISE_MONEY(8,"募集中"),;

    private Integer code;
    private String description;


    UserLoanShowStatusEnum(Integer code, String description) {
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
