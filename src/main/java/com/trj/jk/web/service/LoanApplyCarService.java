package com.trj.jk.web.service;

import com.trj.jk.web.model.request.LoanApplyCarReq;
import com.trj.jk.web.model.response.LoanApplyCarRes;

/**
 * Created by xierongli on 17/8/21.
 */
public interface LoanApplyCarService {

    int saveOrUpdate(LoanApplyCarReq loanApplyCarReq);

    LoanApplyCarRes getLoanApplyInfo(Integer uid,Integer loanApplyId);
}
