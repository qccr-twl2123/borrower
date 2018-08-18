package com.trj.jk.web.controller.v3;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.trj.jk.web.domain.UserContacts;
import com.trj.jk.web.domain.entity.LoanApplyBean;
import com.trj.jk.web.enums.LoanProductEnum;
import com.trj.jk.web.mapper.LoanApplyMapper;
import com.trj.jk.web.model.request.OpenLoanApplyReq;
import com.trj.jk.web.model.request.OpenUserContactReq;
import com.trj.jk.web.validator.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xierongli on 17/7/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLoanApplyController {

    @Autowired
    private LoanApplyController loanApplyController;

    @Autowired
    private LoanApplyMapper loanApplyMapper;

    @Test
    public void testGetBuyCarInfo() {
        LoanApplyBean loanApply = new LoanApplyBean();
        loanApply.setOrderNo("ODNO0002017081010"+new Random().nextInt());
        loanApply.setProductCode("C12");
        loanApply.setMobile("13429151266");
        loanApply.setIdentityId("430426198609166173");
        loanApply.setName("老五");
        List<UserContacts> userContacts = new ArrayList<UserContacts>();
        UserContacts contacts1 = new UserContacts();
        contacts1.setMobile("13429151111");
        contacts1.setName("同一1");
        contacts1.setRelation("子女");

        UserContacts contacts2 = new UserContacts();
        contacts2.setMobile("13429151112");
        contacts2.setName("同一2");
        contacts2.setRelation("朋友");

        UserContacts contacts3 = new UserContacts();
        contacts3.setMobile("13429151113");
        contacts3.setName("同一3");
        contacts3.setRelation("同事");

        userContacts.add(contacts1);
        userContacts.add(contacts2);
        userContacts.add(contacts3);
        loanApply.setContacts(userContacts);
        String jsonData = new Gson().toJson(loanApply);
        System.out.println("jsonData:" + jsonData);

//        Result<Object> result = loanApplyController.addLoanApply(loanApply,null);
//        System.out.println(JSON.toJSONString(result));
    }



    @Test
    public void changeInvitationCode(){

        System.out.println(JSON.toJSONString(loanApplyController.changeInvitationCode("SQC92017102500005","1234567891",null)));
    }


    @Test
    public void existLoanApply(){
        Integer uid = 76180;
        Assert.isTrue(CollectionUtils.isNotEmpty(loanApplyMapper.getLoanApplyListByProductCode(uid, LoanProductEnum.JIN_JI_DAI.getProductCode())), "您在长富贷平台有在贷金额没有结清，请结清后再来申请！");
        Assert.isTrue(CollectionUtils.isNotEmpty(loanApplyMapper.getLoanApplyListByProductCode(uid, LoanProductEnum.GONG_XIN_DAI.getProductCode())), "您在长富贷平台有在贷金额没有结清，请结清后再来申请！");
        Assert.isTrue(CollectionUtils.isNotEmpty(loanApplyMapper.getLoanApplyListByProductCode(uid, LoanProductEnum.GOU_CHE_BAO.getProductCode())), "您在长富贷平台有在贷金额没有结清，请结清后再来申请！");
    }

}
