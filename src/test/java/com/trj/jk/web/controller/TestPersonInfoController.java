package com.trj.jk.web.controller;

import com.alibaba.fastjson.JSON;
import com.trj.jk.web.controller.version_2.LoanProductV2Controller;
import com.trj.jk.web.controller.version_2.PersonInfoV2Controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xierongli
 * @version : trj-jk-web, v 0.1 2017/4/19 11:25 Exp $$
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPersonInfoController {

    @Autowired
    private PersonInfoController  personInfoController;

    @Autowired
    private PersonInfoV2Controller personInfoV2Controller;

    @Test
    public void getScore(){
        System.out.println(JSON.toJSONString(personInfoController.getScore()));
    }

    @Test
    public void queryLoanApplyContacts(){
        System.out.println(JSON.toJSONString(personInfoV2Controller.queryLoanApplyContacts()));
    }

    @Test
    public void queryUserAddressAndCompany(){
        System.out.println(JSON.toJSONString(personInfoV2Controller.queryUserAddressAndCompany(6532)));
    }

}
