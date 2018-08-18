package com.trj.jk.web.controller;

import com.trj.jk.web.controller.interestfree.IdentityController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xierongli on 17/9/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestIdentityController {


    @Autowired
    private IdentityController identityController;

    @Test
    public void testGetVerifyCode(){
        identityController.getVerifyCode(765, "", (byte)3, 515);
    }
}
