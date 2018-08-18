package com.trj.jk.web.controller;

import com.alibaba.fastjson.JSON;
import com.trj.jk.web.model.request.MonthlyRepayReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by xierongli on 17/9/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLoanInfoController {

    @Autowired
    private  LoanInfoController loanInfoController;

    @Test
    public void  testGetMonthlyRepay(){
        BigDecimal loanAmount = new BigDecimal(40000);
        String term = "6个月";
        String repayType ="endDate";
        MonthlyRepayReq monthlyRepayReq = new MonthlyRepayReq();
        monthlyRepayReq.setTerm(term);
        monthlyRepayReq.setRepayType(repayType);
        monthlyRepayReq.setLoanAmount(loanAmount);

        System.out.println(JSON.toJSONString(loanInfoController.getMonthlyRepay(monthlyRepayReq)));

    }

    @Test
    public void testGetMyLoansInfo(){
        System.out.println(JSON.toJSONString(loanInfoController.getMyLoansInfo()));

    }
}
