package com.trj.jk.web.service;

import com.trj.jk.web.domain.LoanLimit;

import java.util.List;

/**
 * Created by xierongli on 17/9/16.
 */
public interface LoanLimitService {

    List<LoanLimit> getByLoanApplyId(Integer loanApplyId);
}
