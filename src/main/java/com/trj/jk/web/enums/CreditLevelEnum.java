package com.trj.jk.web.enums;

import com.google.common.collect.Lists;
import com.trj.jk.web.model.dto.CreditLevelDTO;

import java.util.List;

/**
 * 信用等级
 * @author xierongli
 * @version : trj-jk-web, v 0.1 2017/4/19 10:03 Exp $$
 */
public enum CreditLevelEnum {
    GOOD(1,"信用值一般","一般", 0, 200),
    BETTER(2,"信用值正常","正常", 200, 500),
    BEST(3,"信用值良好","良好", 500, 750),
    EXCELLENT(4,"信用值优秀","优秀", 750, 900);

    private Integer code;
    private String level;
    private String shortLevelDesc;
    private Integer min;
    private Integer max;

    CreditLevelEnum(Integer code, String level, String shortLevelDesc, Integer min, Integer max) {
        this.code = code;
        this.level = level;
        this.shortLevelDesc = shortLevelDesc;
        this.min = min;
        this.max = max;
    }

    public static  String getLevelByValue(Integer value){
        if(value == null ){return "信用值一般";}
        for(CreditLevelEnum creditLevelEnum : CreditLevelEnum.values()){
            if(value > creditLevelEnum.getMin() && value <= creditLevelEnum.getMax()){
                return creditLevelEnum.getLevel();
            }
        }
        return "信用值一般";
    }

    public static List<CreditLevelDTO> getCreditLevels(){
        List<CreditLevelDTO> creditLevelDTOs = Lists.newArrayList();
        for(CreditLevelEnum creditLevelEnum : CreditLevelEnum.values()){
            CreditLevelDTO creditLevelDTO = new CreditLevelDTO();
            creditLevelDTO.setLevel(creditLevelEnum.getLevel());
            creditLevelDTO.setShortDesc(creditLevelEnum.getShortLevelDesc());
            creditLevelDTO.setMax(creditLevelEnum.getMax());
            creditLevelDTO.setMin(creditLevelEnum.getMin());
            creditLevelDTOs.add(creditLevelDTO);
        }
        return creditLevelDTOs;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getShortLevelDesc() {
        return shortLevelDesc;
    }

    public void setShortLevelDesc(String shortLevelDesc) {
        this.shortLevelDesc = shortLevelDesc;
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
