package com.trj.jk.web.controller;

import com.alibaba.fastjson.JSON;
import com.trj.commons.result.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * Created by xierongli on 17/7/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAppController {

    @Autowired
    private  AppController appController;

    @Test
    public void show21(){
        Result<Map<String, Object>> result = appController.getVersion("1", "1.0.6");
        System.out.println(JSON.toJSONString(result));
    }
}
