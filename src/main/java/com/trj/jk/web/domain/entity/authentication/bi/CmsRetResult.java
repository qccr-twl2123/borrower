package com.trj.jk.web.domain.entity.authentication.bi;

public class CmsRetResult {
    private String forbidden_code;
    private String forbidden_reason;
    private String permission;

    public String getForbidden_code() {
        return forbidden_code;
    }

    public void setForbidden_code(String forbidden_code) {
        this.forbidden_code = forbidden_code;
    }

    public String getForbidden_reason() {
        return forbidden_reason;
    }

    public void setForbidden_reason(String forbidden_reason) {
        this.forbidden_reason = forbidden_reason;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "CmsRetResult [forbidden_code=" + forbidden_code + ", forbidden_reason=" + forbidden_reason + ", permission=" + permission + "]";
    }
}
