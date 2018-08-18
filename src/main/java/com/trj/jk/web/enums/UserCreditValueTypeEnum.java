package com.trj.jk.web.enums;

/**
 * 长富分类型枚举
 * @author xierongli
 * @version : trj-jk-web, v 0.1 2017/4/18 14:35 Exp $$
 */
public enum UserCreditValueTypeEnum {

    PERSON_INFO(1,300,"身份信息"),
    CREDIT_HISTORY(2,400,"历史信用"),
    MY_TASK(3,200,"我的任务");

    private Integer type;
    /**总分*/
    private Integer standard;
    private String description;

    UserCreditValueTypeEnum(Integer type, Integer standard, String description) {
        this.type = type;
        this.standard = standard;
        this.description = description;
    }

    public static String getDescByType(Integer type){
        if(type == null){return "";}
        for(UserCreditValueTypeEnum userCreditValueTypeEnum : UserCreditValueTypeEnum.values()){
            if(type.equals(userCreditValueTypeEnum.getType())){
                return userCreditValueTypeEnum.getDescription();
            }
        }
        return "";
    }

    public static Integer getStandardByType(Integer type){
        if(type == null){return 0;}
        for(UserCreditValueTypeEnum userCreditValueTypeEnum : UserCreditValueTypeEnum.values()){
            if(type.equals(userCreditValueTypeEnum.getType())){
                return userCreditValueTypeEnum.getStandard();
            }
        }
        return 0;
    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }

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
}
