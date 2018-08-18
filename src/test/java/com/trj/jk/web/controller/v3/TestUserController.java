package com.trj.jk.web.controller.v3;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.trj.commons.result.Result;
import com.trj.commons.result.Results;
import com.trj.jk.web.domain.LoanApply;
import com.trj.jk.web.domain.UserContacts;
import com.trj.jk.web.domain.UserExt;
import com.trj.jk.web.domain.entity.LoanApplyBean;
import com.trj.jk.web.mapper.LoanApplyMapper;
import com.trj.jk.web.model.request.IdentityReq;
import com.trj.jk.web.service.UserBasicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserController {

    @Autowired
    private UserController userController;
    @Autowired
    private LoanApplyMapper loanApplyMapper;
    @Autowired
    private UserBasicService userBasicService;

    @Test
    public void testIdentitySave() {
        UserExt ext = new UserExt();
        ext.setName("");
        ext.setIdentityId("430426198609166173");
        ext.setHeadImageId(4091);
        ext.setIdentityCardFrontImageId(4092);
        ext.setIdentityCardOppositeImageId(4093);
        Assert.notNull(ext.getName(), "姓名信息不能为空！");
        Assert.notNull(ext.getIdentityId(), "身份证号信息不能为空！");
        Assert.notNull(ext.getHeadImageId(), "身份证抠像图片信息不能为空！");
        Assert.notNull(ext.getIdentityCardFrontImageId(), "身份证正面图片信息不能为空！");
        Assert.notNull(ext.getIdentityCardOppositeImageId(), "身份证反面面图片信息不能为空！");
        String jsonData = new Gson().toJson(ext);
        System.out.println("jsonData:" + jsonData);

//        Result<Object> result = userController.identitySave(ext);
//        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void testRegister(){
        String mobile ="135888664132";
        System.out.println(JSON.toJSONString(Results.newSuccessResult(true)));
        System.out.println(JSON.toJSONString(Results.newFailedResult("手机号已注册")));
        //System.out.println(JSON.toJSONString(userController.register(mobile)));

    }
   



}
