package com.trj.jk.web.enums;

import com.google.common.collect.Lists;
import com.trj.jk.web.model.response.LoanProductItemRes;

import java.util.List;

/**
 * 借款产品所需资料枚举
 * Created by xierongli on 17/8/10.
 */
public enum DocumentItemEnum {

    GONGJIJIN("C9","公积金或社保","/images/icon/gongjijin-s.png"),
    YANGHANG("C9","央行征信(简版)","/images/icon/zhengxin-s.png"),
    JIASHIZHENG("C1","驾驶证","/images/icon/jiashizheng-s.png"),
    DINGCHE_HETONG("C1","订车合同","/images/icon/hetong-s.png"),
    ZICHANZHENMING("C13","资产证明或收入证明","/images/icon/hetong-s.png"),
    YANGHANGZX("C13","央行征信","/images/icon/zhengxin-s.png");

    private String productCode;
    private String name;
    private String url;

    DocumentItemEnum(String productCode, String name, String url) {
        this.productCode = productCode;
        this.name = name;
        this.url = url;
    }

    public static List<LoanProductItemRes> getByProductCode(String productCode,String downloadUrl){
        List<LoanProductItemRes> loanProductItemResList = Lists.newArrayList();
        for(DocumentItemEnum documentItem : DocumentItemEnum.values()){
            LoanProductItemRes loanProductItemRes = new LoanProductItemRes();
            if(documentItem.getProductCode().equals(productCode)){
                loanProductItemRes.setItemName(documentItem.getName());
                loanProductItemRes.setItemUrl(downloadUrl+documentItem.getUrl());
            }else{
                continue;
            }
            loanProductItemResList.add(loanProductItemRes);
        }
        return loanProductItemResList;
    }
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
