package com.trj.jk.web.controller;

import com.alibaba.fastjson.JSON;
import com.trj.jk.web.domain.LoanLimit;
import com.trj.jk.web.service.IThreadTaskService;
import com.trj.jk.web.service.IUserService;
import com.trj.jk.web.service.UserFaceLogService;
import com.trj.jk.web.util.HttpClientUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xierongli on 17/9/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestContract {
    //crm系统域名
    @Value("${app.remote.domain.crm}")
    private String	remoteDomainCrm;
    //crm合同发布url
    @Value("${app.remote.domain.contractPublishUrl}")
    private String contractPublishUrl;
    @Autowired
    private IThreadTaskService threadTaskService;
    @Autowired
    private IUserService userService;
    @Autowired
    private UserFaceLogService userFaceLogService;
    @Value("${app.remote.cloudwalk.idcardScore}")
    private BigDecimal idcardScore = null;

//    id                                  1321                 1322
//    uid                                25603                24922
//    loan_apply_id                      70368                68238
//    loan_receipt_id        JKC92017102700003    JKC92017102700004
//    product_id                             1                    1
//    name                                 黄毅琳                   梁斌
//    mobile                       13960525502          18682822226
//    bank_card_id                       16781                16144
//    use_amount                         20000                19000
//    repay_type                      permonth             permonth
//    term                                   6                   12
//    term_unit                          month                month
//    expect_repay_amount              3673.33              1906.33
//    repay_periods                        NaN                  NaN
//    interest                             1.7                  1.7
//    status                                 1                    1
//    type                                   0                    0
//    tenant                             jkWeb                jkWeb
//    publish_contract_id                  NaN                  NaN
//    is_contract_publish                    0                    0
//    modify_time          2017-10-27 15:02:06  2017-10-27 15:54:24
//    create_time          2017-10-27 15:02:06  2017-10-27 15:54:24
//    cut_charge                           600                  950

    @Test
    public void testUnFinishContract(){
        /**合同数据生成*/
        String crmUrl ="https://crm.tourongjia.com";
        Integer id = 1321;
        Integer uid = 25603;
        Integer loanApplyId = 70368;
        String loanReceiptId = "JKC92017102700003";
        String mobile = "13960525502";
        String name ="黄毅琳";
        Integer bankCardId = 16781;
        BigDecimal useAmount = new BigDecimal("20000");
        BigDecimal expectRepayAmount = new BigDecimal("3673.33" );
        BigDecimal interest = new BigDecimal("1.7");
        String term = "6";


        LoanLimit  loanLimit = new LoanLimit();

        loanLimit.setId(id);
        loanLimit.setUid(uid);
        loanLimit.setLoanApplyId(loanApplyId);
        loanLimit.setLoanReceiptId(loanReceiptId);
        loanLimit.setBankCardId(bankCardId);

        loanLimit.setInterest(interest);
        loanLimit.setMobile(mobile);
        loanLimit.setName(name);
        loanLimit.setUseAmount(useAmount);
        loanLimit.setExpectRepayAmount(expectRepayAmount);

        loanLimit.setStatus(new Integer(1).byteValue());
        loanLimit.setIsContractPublish(new Integer(0).byteValue());
        loanLimit.setRepayType("permonth");
        loanLimit.setProductId(1);

        loanLimit.setTenant("jkWeb");
        loanLimit.setTerm(term);
        loanLimit.setType(new Integer(0).byteValue());
        loanLimit.setTermUnit("month");

        Map<String, String> param = new HashMap<String, String>();
        param.put("jkWebUid", String.valueOf(uid));
        param.put("jkWebLoanLimit", JSON.toJSONString(loanLimit));
        String url = crmUrl + contractPublishUrl;
        String result = userService.doContractPublish(url, param);
        System.out.println(result);

        //执行之后需要更新线上数据
        //update jk_loan_limit set is_contract_publish =1, publish_contract_id = "JK-C920170918000010" where id = 660;

    }


    @Test
    public  void test11(){
        String url = "http://escrowcrm1.tourongjia.com/jkWebService/jkWebService_handleContractPublic.jhtml";
        String body = HttpClientUtils.httpPost(url, null, null, null, null);
        System.out.println(JSON.toJSONString(body));
    }




}
