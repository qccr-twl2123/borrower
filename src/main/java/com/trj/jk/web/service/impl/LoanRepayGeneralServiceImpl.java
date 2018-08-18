package com.trj.jk.web.service.impl;

import com.google.common.collect.Maps;
import com.trj.jk.web.domain.LoanRepayGeneral;
import com.trj.jk.web.domain.LoanRepayGeneralCriteria;
import com.trj.jk.web.mapper.LoanRepayGeneralMapper;
import com.trj.jk.web.mapper.LoanRepayPlanMapper;
import com.trj.jk.web.model.response.MyLoanInfoRes;
import com.trj.jk.web.service.LoanRepayGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xierongli on 17/9/16.
 */
@Service
public class LoanRepayGeneralServiceImpl implements LoanRepayGeneralService {

    @Autowired
    private LoanRepayGeneralMapper loanRepayGeneralMapper;
    @Autowired
    private LoanRepayPlanMapper loanRepayPlanMapper;

    @Override
    public List<LoanRepayGeneral> getByLoanApplyId(Integer loanApplyId) {
        LoanRepayGeneralCriteria loanRepayGeneralCriteria = new LoanRepayGeneralCriteria();
        loanRepayGeneralCriteria.createCriteria().andApplyLoanIdEqualTo(loanApplyId);
        return loanRepayGeneralMapper.selectByCriteria(loanRepayGeneralCriteria);
    }
    @Override
    public Map<String,Object> getMyLoanInfo(Integer uid) {
        Map<String,Object> map = Maps.newHashMap();
        MyLoanInfoRes myLoanInfoRes = loanRepayGeneralMapper.getMyLoanInfo(uid);
        map.put("loanQuantity",myLoanInfoRes.getLoanQuantity());
        map.put("loanAmount",myLoanInfoRes.getLoanAmount());

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("uid", uid);
        //查询罚金
        BigDecimal penaltyInterestAmount = loanRepayGeneralMapper.getPenaltyInterestAmount(uid);

        //本月应还
        BigDecimal repayAmount = loanRepayPlanMapper.getMounthRepayAmount(paramMap);
        map.put("RepayAmount", repayAmount.add(penaltyInterestAmount));
        //在贷金额
        BigDecimal inLoanAmount = loanRepayPlanMapper.getInLoanAmountByUid(uid);
        map.put("inLoanAmount", inLoanAmount.add(penaltyInterestAmount));
        return map;
    }
}
