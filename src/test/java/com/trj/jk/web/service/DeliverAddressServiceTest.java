package com.trj.jk.web.service;

import com.alibaba.fastjson.JSON;
import com.trj.jk.web.domain.DeliverAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by xierongli on 17/6/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeliverAddressServiceTest {/*

    @Autowired
    private IDeliverAddressService deliverAddressService;

    @Test
    public void insert(){
        DeliverAddress deliverAddress = new DeliverAddress();
        deliverAddress.setUid(1);
        deliverAddress.setAddress("mamammsmada");
        deliverAddress.setGeocodeId(1);
        deliverAddress.setMobile("17899990000");
        deliverAddress.setName("mark");
        deliverAddress.setType((byte)0);
        deliverAddress.setCreatePerson("system");
        deliverAddress.setCreateTime(new Date());
        deliverAddress.setUpdatePerson("system");
        deliverAddress.setModifyTime(new Date());
        int num = deliverAddressService.insert(deliverAddress);
        System.out.println(num);

    }

    @Test
    public void queryForList(){
        Integer uid  = 1;
        List<DeliverAddress> deliverAddressList = deliverAddressService.queryForList(uid);
        System.out.println(JSON.toJSONString(deliverAddressList));
    }*/
}
