package com.trj.jk.web.model.response;

import com.trj.jk.web.domain.entity.thirdparty.ErrorResult;

/**
 * 绑定银行卡返回模型
 * Created by xierongli on 17/9/7.
 */
public class BindBankCardApplyRes {


    private Integer userBankCardId;
    private Integer userId;
    private String bindNo;
    private ErrorResult result;

    public Integer getUserBankCardId() {
        return userBankCardId;
    }

    public void setUserBankCardId(Integer userBankCardId) {
        this.userBankCardId = userBankCardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBindNo() {
        return bindNo;
    }

    public void setBindNo(String bindNo) {
        this.bindNo = bindNo;
    }

    public ErrorResult getResult() {
        return result;
    }

    public void setResult(ErrorResult result) {
        this.result = result;
    }
}
