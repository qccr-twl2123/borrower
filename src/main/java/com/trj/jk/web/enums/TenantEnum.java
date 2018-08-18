package com.trj.jk.web.enums;

/**
 * 平台枚举
 * Created by xierongli on 17/10/18.
 */
public enum TenantEnum {

    CHANG_FU_DAI("jkWeb"),DAI_LA("dl");

    private String description;

    TenantEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
