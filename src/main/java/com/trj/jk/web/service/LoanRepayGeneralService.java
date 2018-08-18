package com.trj.jk.web.service;

import com.trj.jk.web.domain.LoanRepayGeneral;
import com.trj.jk.web.model.response.MyLoanInfoRes;

import java.util.List;
import java.util.Map;

/**
 * Created by xierongli on 17/9/16.
 */
public interface LoanRepayGeneralService {

    List<LoanRepayGeneral> getByLoanApplyId(Integer loanApplyId);
    Map<String,Object> getMyLoanInfo(Integer uid);

}
