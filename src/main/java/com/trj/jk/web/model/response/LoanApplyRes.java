package com.trj.jk.web.model.response;

/**
 * 借款申请响应模型
 * Created by xierongli on 17/8/14.
 */
public class LoanApplyRes {


    private Integer loanApplyId;
    private Integer productId;

    public Integer getLoanApplyId() {
        return loanApplyId;
    }

    public void setLoanApplyId(Integer loanApplyId) {
        this.loanApplyId = loanApplyId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
