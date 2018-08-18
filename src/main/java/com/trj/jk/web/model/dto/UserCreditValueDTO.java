package com.trj.jk.web.model.dto;


import java.util.List;

/**
 * 长富分DTO
 * @author xierongli
 * @version : trj-jk-web, v 0.1 2017/4/18 14:10 Exp $$
 */
public class UserCreditValueDTO {
    /**用户ID*/
    private Integer uid;

    /**长富分值*/
    private Integer total;
    /**等级描述*/
    private String level;

    /**信用等级描述*/
    private List<CreditLevelDTO> creditLevelDTOs;

    /**长富分子祥明细*/
    List<UserCreditItemValueDTO> userCreditItemValueDTOList;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<UserCreditItemValueDTO> getUserCreditItemValueDTOList() {
        return userCreditItemValueDTOList;
    }

    public void setUserCreditItemValueDTOList(List<UserCreditItemValueDTO> userCreditItemValueDTOList) {
        this.userCreditItemValueDTOList = userCreditItemValueDTOList;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<CreditLevelDTO> getCreditLevelDTOs() {
        return creditLevelDTOs;
    }

    public void setCreditLevelDTOs(List<CreditLevelDTO> creditLevelDTOs) {
        this.creditLevelDTOs = creditLevelDTOs;
    }
}
