package com.trj.jk.web.controller;

import com.alibaba.fastjson.JSON;
import com.trj.jk.web.domain.UserCertfication;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Created by mark1xie on 17/5/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAuthicationController {


    @Autowired
    private AuthenticationController authenticationController;


    @Test
    public void testLivingBodyAuth(){
        List<MultipartFile> fileList = Lists.newArrayList();
        System.out.println(JSON.toJSONString(authenticationController.livingBodyAuth(fileList)));
    }

    @Test
    public void testConfirm(){
        UserCertfication userCertfication = new UserCertfication();
        userCertfication.setType(new Integer(8).byteValue());
        userCertfication.setCertficationStatus(new Integer(0).byteValue());
        Integer certficationId = 88;
        Byte confirmType = new Integer(0).byteValue();
        System.out.println(JSON.toJSONString(authenticationController.confirm(userCertfication,certficationId, confirmType)));
    }

    @Test
    public void testSocialCity(){
        String searchKey ="";
        System.out.println(JSON.toJSONString(authenticationController.socialCity(searchKey)));

    }

    @Test
    public void testSocialAuthentication(){
        String soinsBean ="{\"name\":\"刘丹\",\"cell_phone_num\":\"15867788606\",\"id_card_num\":\"512528197410090019\",\"password\":\"134024\",\"website\":\"si_yibin\",\"sort\":\"005167100000-1304\",\"type\":\"auth_auto\"}\n";
        System.out.println(JSON.toJSONString(authenticationController.socialAuthentication(soinsBean)));


    }



}
