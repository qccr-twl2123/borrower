package com.trj.jk.web.controller;

import com.alibaba.fastjson.JSON;
import com.trj.jk.web.controller.version_2.LoanApplyV2Controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xierongli on 17/8/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLoanApplyV2Controller {

    @Autowired
    private LoanApplyV2Controller loanApplyV2Controller;


    @Test
    public void testAddLoanApply(){
//        System.out.println(JSON.toJSONString(loanApplyV2Controller.addLoanApply(3,"")));

    }
}
