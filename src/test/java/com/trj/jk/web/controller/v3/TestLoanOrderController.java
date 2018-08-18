package com.trj.jk.web.controller.v3;


import com.alibaba.fastjson.JSON;
import com.trj.jk.web.convert.ObjectConvert;
import com.trj.jk.web.domain.LoanLimit;
import com.trj.jk.web.domain.entity.page.PageBean;
import com.trj.jk.web.mapper.LoanLimitMapper;
import com.trj.jk.web.model.request.CutInterestReq;
import com.trj.jk.web.model.request.OrderFinishReq;
import com.trj.jk.web.model.request.SignatureReq;
import com.trj.jk.web.validator.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by xierongli on 17/7/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLoanOrderController {

    @Autowired
    private LoanOrderController loanOrderController;

    @Autowired
    private LoanLimitMapper loanLimitMapper;

    @Test
    public void queryLoanUseTypeList(){
        System.out.println(JSON.toJSONString(loanOrderController.queryInsuranceContractTpl()));
    }

    @Test
    public void testOrderFinish() {
        String json ="{\"orderNo\":\"261656830\",\"productCode\":\"C12\",\"mobile\":\"13957194562\",\"identityId\":\"330922199309183525\",\"name\":\"刘思雨\",\"corpName\":\"杭州投融谱华互联网金融服务有限公司\",\"contacts\":[{\"name\":\"周碧红\",\"mobile\":\"13587092928\",\"relation\":\"父母\"},{\"name\":\"邬小青\",\"mobile\":\"18868811935\",\"relation\":\"同事\"}]}";
        OrderFinishReq orderFinishReq = JSON.parseObject(json,OrderFinishReq.class);
        loanOrderController.finish(orderFinishReq);
    }

    @Test
    public void testGetLimitAuditList() {
        PageBean pageBean = new PageBean();
        pageBean.setLimit(10);
        pageBean.setPage(1);
        System.out.println(JSON.toJSONString(loanOrderController.getLimitAuditList(pageBean,"C12")));
    }

    @Test
    public void testTryDoRepayAmount() {
        CutInterestReq cutInterestReq = new CutInterestReq();
        cutInterestReq.setAmount("1000");
        cutInterestReq.setInterest("0.1");
        cutInterestReq.setTerm(6);
        System.out.println(JSON.toJSONString(loanOrderController.tryDoRepayAmount(cutInterestReq)));
    }

    @Test
    public void getRepayPlans() {
        System.out.println(JSON.toJSONString(loanOrderController.getRepayPlans("361704266",null)));

    }

    @Test
    public void testDoSignature() {
        SignatureReq signatureReq = new SignatureReq();
        signatureReq.setBankCardId(577);
        signatureReq.setCutCharge(new BigDecimal(10));
        signatureReq.setExpectRepayAmount(new BigDecimal(900));
        signatureReq.setInterest(new BigDecimal(0.022));

        signatureReq.setOrderNo("2616565621");
        signatureReq.setTerm("12");
        signatureReq.setUseAmount(new BigDecimal(1000));
        signatureReq.setVerifyCode("1234");
        System.out.println(JSON.toJSONString(loanOrderController.doSignature(signatureReq)));

    }
}
