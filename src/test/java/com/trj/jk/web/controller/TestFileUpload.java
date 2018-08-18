package com.trj.jk.web.controller;

import com.trj.jk.web.util.HttpClientUtils;
import com.xiaoleilu.hutool.io.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * Created by xierongli on 17/8/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFileUpload {

    @Value("${app.upload.url}")
    private String uploadImgUrl;

    @Resource
    private RedisTemplate<String, Long> redisTemplate;

    @Test
    public void testUploadFile() {
        File file = FileUtil.file("static/images/icon/group-l.png");
        InputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String data = HttpClientUtils.uploadFile(in,file.getName(),"https://uatjkimg.tourongjia.com/fileUpload");
        System.out.println(data);
    }

    @Test
    public void test(){
//        String key ="SQC201909093";
//        long number = redisTemplate.boundHashOps(key).increment(key, 1);
//        long number = redisTemplate.boundValueOps(key).increment(1);
//        System.out.println(number);
        System.out.println(String.valueOf(null));
    }
}
