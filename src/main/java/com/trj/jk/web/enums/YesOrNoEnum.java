package com.trj.jk.web.enums;

/**
 * Yes or No
 *
 * @author xierongli
 * @version $$Id: trj-jk-web, v 0.1 2018/5/3 下午2:45 mark1xie Exp $$
 */
public enum YesOrNoEnum {

    NO(0,"no"),
    YES(1,"yes");

    private Integer code;
    private String description;

    YesOrNoEnum(Integer code, String description) {
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
