package com.trj.jk.web.util;

import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2017/6/7.
 */
public class OrderUtil {

    public static String getOrderNo(String name,Integer goodsId,Integer uid){
        StringBuffer orderNo=new StringBuffer();
        long date=new Date().getTime()/1000;
        Random random = new Random();
        int r = random.nextInt(10000)+1000;
        orderNo.append(name);
        orderNo.append(date);
        orderNo.append(r);
        orderNo.append(goodsId);
        orderNo.append(uid);
        return orderNo.toString();
    }

}
