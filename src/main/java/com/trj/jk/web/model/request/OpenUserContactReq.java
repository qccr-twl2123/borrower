package com.trj.jk.web.model.request;

import com.trj.jk.web.domain.entity.regex.RegexContext;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by xierongli on 17/9/8.
 */
public class OpenUserContactReq {


    @NotBlank(message = "联系人姓名不为空")
    private String name;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = RegexContext.MOBILE_REGEX,message="手机号格式不合法")
    private String mobile;

    @NotBlank(message = "关系不为空")
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
