package com.trj.jk.web.model.result;

/**
 * Created by xierongli on 17/10/20.
 */
public class SocialResult {

    private String retmessage;
    private Integer retcode;
    private String areaList;

    public String getRetmessage() {
        return retmessage;
    }

    public void setRetmessage(String retmessage) {
        this.retmessage = retmessage;
    }

    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    public String getAreaList() {
        return areaList;
    }

    public void setAreaList(String areaList) {
        this.areaList = areaList;
    }
}
