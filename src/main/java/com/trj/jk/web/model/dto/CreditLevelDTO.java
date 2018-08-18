package com.trj.jk.web.model.dto;

/**
 * 信用等级描述信息
 * @author xierongli
 * @version : trj-jk-web, v 0.1 2017/4/20 16:01 Exp $$
 */
public class CreditLevelDTO {



    private String level;
    private String shortDesc;

    private Integer min;
    private Integer max;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
