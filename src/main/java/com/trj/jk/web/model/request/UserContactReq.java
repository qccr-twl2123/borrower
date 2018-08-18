package com.trj.jk.web.model.request;

import com.trj.jk.web.domain.entity.regex.RegexContext;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 用户联系人业务对象
 * Created by xierongli on 17/7/27.
 */
public class UserContactReq {

    @NotBlank(message = "用户名不能为空")
    private String name;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = RegexContext.MOBILE_REGEX,message="手机号格式不合法")
    private String mobile;
    @NotBlank(message = "关系不能为空")
    private String relation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
