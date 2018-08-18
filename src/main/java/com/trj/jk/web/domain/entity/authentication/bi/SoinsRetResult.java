package com.trj.jk.web.domain.entity.authentication.bi;

public class SoinsRetResult {
    private String success_url;

    private String open_id;

    public String getSuccess_url() {
        return success_url;
    }

    public void setSuccess_url(String success_url) {
        this.success_url = success_url;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    @Override
    public String toString() {
        return "SoinsRetResult [success_url=" + success_url + ", open_id=" + success_url + "]";
    }
}
