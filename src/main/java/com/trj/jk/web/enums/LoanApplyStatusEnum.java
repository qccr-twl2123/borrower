package com.trj.jk.web.enums;

/**
 * 借款申请状态 0:未完成 1:审核中 2:审核成功 3:审核失败
 * Created by xierongli on 17/9/12.
 */
public enum LoanApplyStatusEnum {

    INIT(0,"未完成"),
    RUNNING(1,"审核中"),
    SUCCESS(2,"审核成功"),
    FAILURE(3,"审核失败"),
    IN_REPAY(4,"待还款"),
    CLEAR(5,"已还款"),
    GIVE_UP(6,"已撤回");

    private Integer code;
    private String description;

    LoanApplyStatusEnum(Integer code, String description) {
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
