package com.trj.jk.web.model.dto;

/**
 * 用户长富分子项
 * @author xierongli
 * @version : trj-jk-web, v 0.1 2017/4/18 14:33 Exp $$
 */
public class UserCreditItemValueDTO {
    /**类型*/
    private Integer type;
    /**描述*/
    private String description;
    /**分值*/
    private Integer value;
    /**标准值*/
    private Integer standard;
    /**比例*/
    private Double  ratio;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}
