package com.trj.jk.web.enums;

/**
 * 用户绑卡状态
 * Created by xierongli on 17/9/7.
 */
public enum UserBindCardStatusEnum {
    INIT(new Integer(0).byteValue(),"初始化"),
    APPLY(new Integer(1).byteValue(),"绑卡申请"),
    CONFIRM(new Integer(2).byteValue(),"绑卡确认"),
    SUCCESS(new Integer(3).byteValue(),"绑卡成功");

    private byte status;
    private String description;

    UserBindCardStatusEnum(byte status, String description) {
        this.status = status;
        this.description = description;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
