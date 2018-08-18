package com.trj.jk.web.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xierongli
 * @version $$Id: trj-jk-web, v 0.1 2018/5/3 下午4:06 mark1xie Exp $$
 */
public class RandomUtil {

    private static AtomicInteger tempNum = new AtomicInteger(100);

    /**
     * 获取随机序列
     * @author xierongli
     * @date 2018/5/3 下午4:06
     */
    public static String getSequeue(){
        long num = System.currentTimeMillis();
        tempNum.addAndGet(1);
        if(tempNum.get() == 1000){
            tempNum.set(100);
        }
        return num + "" + tempNum.get();
    }
}
