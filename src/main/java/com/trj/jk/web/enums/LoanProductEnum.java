package com.trj.jk.web.enums;

/**
 * 借款产品信息枚举
 * Created by xierongli on 17/7/24.
 */
public enum LoanProductEnum {
    GONG_XIN_DAI("工薪贷","C9"),
    JIN_JI_DAI("金鸡贷","C12"),
    GOU_CHE_BAO("购车宝","C1");

    private String productName;
    private String  productCode;


    LoanProductEnum(String productName, String productCode) {
        this.productName = productName;
        this.productCode = productCode;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }



}
