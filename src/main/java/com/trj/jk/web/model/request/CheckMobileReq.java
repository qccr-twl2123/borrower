package com.trj.jk.web.model.request;

import com.trj.jk.web.domain.entity.regex.RegexContext;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by xierongli on 17/10/16.
 */
public class CheckMobileReq {
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = RegexContext.MOBILE_REGEX,message = "手机号格式不正确")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
