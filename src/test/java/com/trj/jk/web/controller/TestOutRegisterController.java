package com.trj.jk.web.controller;

import com.alibaba.fastjson.JSON;
import com.trj.jk.web.model.request.OutRegisterReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xierongli on 17/8/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestOutRegisterController {

    @Autowired
    private OutRegisterController outRegisterController;

    @Value("${app.upload.url}")
    private String				uploadImgUrl;

    @Test
    public void getVerificationCode(){
        String mobile = "15355099653";
        System.out.println(JSON.toJSONString(outRegisterController.getVerificationCode(null, null)));
    }



//    @Test
//    public void checkVerificationCode(){
//        String token="d8a3568b-3aa9-471f-a6ec-a9084d99650d";
//        String code ="Y7LL";
//        String mobile = "";
//        System.out.println(JSON.toJSONString(outRegisterController.checkVerificationCode(code,token)));
//    }

    @Test
    public void getMobileVerificationCode(){
        String token="cb869105-dc2c-446f-a704-d5d993c28d17";
        String mobile = "15355099653";
        System.out.println(JSON.toJSONString(outRegisterController.getMobileVerificationCode(token,mobile)));
    }

    @Test
    public void register(){
        String token="cb869105-dc2c-446f-a704-d5d993c28d17";
        String mobile = "15355098693";
        OutRegisterReq outRegisterReq = new OutRegisterReq();
        outRegisterReq.setChannel("cps");
        outRegisterReq.setMobile(mobile);
        outRegisterReq.setMobileCheckPwd("814787");
        outRegisterReq.setPassword("343232");
        outRegisterReq.setSerialNumber("366");
        outRegisterReq.setToken(token);
        outRegisterReq.setSubjectId("136600001");

        System.out.println(JSON.toJSONString(outRegisterController.register(outRegisterReq)));
    }




}
