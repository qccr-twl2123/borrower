package com.trj.jk.web.model.request;

import com.trj.jk.web.domain.entity.regex.RegexContext;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 外部注册请求模型
 * Created by xierongli on 17/8/2.
 */
public class OutRegisterReq {

    @NotBlank(message = "token 不能为空")
    private String token;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = RegexContext.MOBILE_REGEX,message = "手机号格式不正确")
    private String mobile;

    private String channel;
    private String serialNumber;
    private String subjectId;
    @NotBlank(message = "手机验证码不能为空")
    @Length(min = 6,max = 6,message = "手机验证码必须是6位数字")
    private String mobileCheckPwd;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getMobileCheckPwd() {
        return mobileCheckPwd;
    }

    public void setMobileCheckPwd(String mobileCheckPwd) {
        this.mobileCheckPwd = mobileCheckPwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
