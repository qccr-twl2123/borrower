package com.trj.jk.web.enums;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 银行代码枚举
 *
 * @author xierongli
 * @version $$Id: trj-jk-web, v 0.1 2018/5/8 上午9:43 mark1xie Exp $$
 */
public enum BankCodeEnum {

    CCB("建设银行"),
    BOC("中国银行"),
    CITIC("中信银行"),
    CEB("光大银行"),
    CIB("兴业银行"),
    SPDB("浦发银行"),
    ICBC("工商银行"),
    SPAB("平安银行"),
    GDB("广发银行"),
    BOCO("交通银行");

    private String desc;

    BankCodeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public static List<String> getBankList(){
        List<String> bankList = Lists.newArrayList();
        for(BankCodeEnum bankCodeEnum : BankCodeEnum.values()){
            bankList.add(bankCodeEnum.getDesc());
        }
        return bankList;
    }
}
