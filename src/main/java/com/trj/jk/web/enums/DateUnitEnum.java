package com.trj.jk.web.enums;

/**
 * 日期单位枚举
 * Created by xierongli on 17/9/15.
 */
public enum DateUnitEnum {
    DAY("day","天"),
    MONTH("month","月"),
    YEAR("year","年");

    private String english;
    private String chinese;

    DateUnitEnum(String english, String chinese) {
        this.english = english;
        this.chinese = chinese;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
}
