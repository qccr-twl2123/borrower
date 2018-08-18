package com.trj.jk.web.service.impl;

import com.trj.jk.web.domain.LoanLimit;
import com.trj.jk.web.domain.LoanLimitCriteria;
import com.trj.jk.web.mapper.LoanLimitMapper;
import com.trj.jk.web.service.LoanLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xierongli on 17/9/16.
 */
@Service
public class LoanLimitServiceImpl implements LoanLimitService {

    @Autowired
    private LoanLimitMapper loanLimitMapper;

    @Override
    public List<LoanLimit> getByLoanApplyId(Integer loanApplyId) {
        LoanLimitCriteria loanLimitCriteria = new LoanLimitCriteria();
        loanLimitCriteria.createCriteria().andLoanApplyIdEqualTo(loanApplyId);
        return loanLimitMapper.selectByCriteria(loanLimitCriteria);
    }
}
