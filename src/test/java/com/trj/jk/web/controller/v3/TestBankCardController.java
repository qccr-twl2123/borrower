package com.trj.jk.web.controller.v3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.trj.commons.result.Result;
import com.trj.jk.web.domain.BankBranch;
import com.trj.jk.web.domain.UserContacts;
import com.trj.jk.web.domain.entity.LoanApplyBean;
import com.trj.jk.web.model.request.BindBankCardApplyReq;
import com.trj.jk.web.model.request.BindBankCardConfirmReq;
import com.trj.jk.web.model.request.OpenLoanApplyReq;
import com.trj.jk.web.model.request.OpenUserContactReq;
import com.trj.jk.web.model.response.BankConfRes;
import com.trj.jk.web.util.HttpClientUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * Created by xierongli on 17/7/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBankCardController {

    @Autowired
    private BankCardController bankCardController;

    @Test
    public void testToBind() {
//        bankBranch = "中国建设银行总行(不受理个人业务)";
//        cardHolder = "张冰冰";
//        cardId = 123665589666444;
//        city = "北京市";
//        identityCode = 412825199202026752;
//        mediaId = 18458891234;
//        mobile = 18458891231;
//        province = "北京市";
            String json = "{\"mediaType\":\"MOBILE\",\"verifyCode\":\"123456\",\"bindNo\":\"123456\",\"cardId\":\"6217002920132397127\",\"mediaId\":\"18281922132\",\"mobile\":\"18281922132\",\"cardHolder\":\"常岳\",\"identityCode\":\"130302199210303915\",\"province\":\"浙江省\",\"city\":\"杭州市\",\"bankBranch\":\"中国建设银行总行\"}\n";
//        {"mediaType":"MOBILE","cardId":"6217002920132397127","mediaId":"18281922132","mobile":"18281922132","cardHolder":"常岳","identityCode":"130302199210303915","province":"浙江省","city":"杭州市","bankBranch":"中国建设银行总行"}
//        bankCardController.bindBankCardApply(new Gson().fromJson(json, BindBankCardApplyReq.class));
//        bankCardController.bindConfirm(new Gson().fromJson(json, BindBankCardConfirmReq.class));

        Map data = new Gson().fromJson(json, HashMap.class);
        Map<String, String> reqHeadMap = new HashMap<String, String>();
        reqHeadMap.put("Content-Type", "application/x-www-form-urlencoded");
        reqHeadMap.put("Authorization", "Bearer 9fe79c6c-1be9-4974-90a6-80686cb90acc");
        String body=  HttpClientUtils.httpPost("https://testjkweb.tourongjia.com/v3/card/toBind",null,data,reqHeadMap,null);
        System.out.println(body);
    }

    @Test
    public void testBindConfirm(){
        String json ="{\"cardId\":\"6217002920118085126\",\"mediaId\":\"18907485436\",\"mobile\":\"18907485436\",\"cardHolder\":\"常岳\",\"identityCode\":\"130302199210303915\",\"province\":\"湖南省\",\"city\":\"长沙市\",\"bankBranch\":\"中国建设银行总行\",\"bindNo\":\"2147483647\",\"verifyCode\":\"1234\"}";
        BindBankCardConfirmReq bindBankCardConfirmReq = JSON.parseObject(json,BindBankCardConfirmReq.class);
        System.out.println(JSON.toJSONString(bindBankCardConfirmReq));
        System.out.println(JSON.toJSONString(bankCardController.bindConfirm(bindBankCardConfirmReq)));

    }

    @Test
    public void testBindBankCardApply(){
        String json ="";
        BindBankCardApplyReq bindBankCardApplyReq = JSON.parseObject(json,BindBankCardApplyReq.class);
        System.out.println(JSON.toJSONString(bankCardController.bindBankCardApply(bindBankCardApplyReq)));
    }

    @Test
    public void testGetBankConfList(){
        System.out.println(JSON.toJSONString(bankCardController.getBankConfList()));
    }

    @Test
    public void testGetBankBranchesByCityCode(){
        Result<List<BankBranch>> result = bankCardController.getBankBranchesByCityCode("1301","001");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testBankBranch(){
        System.out.println(JSON.toJSONString(bankCardController.bankBranch()));
    }









}
