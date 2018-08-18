package com.trj.jk.web.service;

import com.trj.jk.web.domain.LoanApply;
import com.trj.jk.web.model.request.OrderFinishReq;

/**
 * Created by xierongli on 17/8/10.
 */
public interface LoanApplyService {

    Integer addLoanApply(LoanApply loanApply);

    LoanApply getByOrderNo(Integer uid,String orderNo);

    void orderFinish(OrderFinishReq orderFinishReq,Integer uid);

    void createLoanApply(Integer uid,String orderNo);
}
