package com.trj.jk.web.domain.entity.authentication.bi;

import java.util.List;

public class CmsResult {
    private String retmessage;
    private Integer retcode;
    private CmsRetResult retresult;

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

    public CmsRetResult getRetresult() {
        return retresult;
    }

    public void setRetresult(CmsRetResult retresult) {
        this.retresult = retresult;
    }

    @Override
    public String toString() {
        return "CmsResult [retmessage=" + retmessage + ", retcode=" + retcode + ", retresult=" + retresult + "]";
    }

}
