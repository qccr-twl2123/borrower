package com.trj.jk.web.model.request;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 身份证认证入参
 * Created by xierongli on 17/8/9.
 */
public class IdentityReq {


    @NotBlank(message = "用户姓名不能为空")
    private String name;
    @NotBlank(message = "身份证号不能为空")
    @Size(min = 15,max = 18,message = "身份证号码长度不合法")
    private String identityId;

    private String identityAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "身份证到期日期不能为空")
    @Future(message = "身份证已过期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }


    public String getIdentityAddress() {
        return identityAddress;
    }

    public void setIdentityAddress(String identityAddress) {
        this.identityAddress = identityAddress;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
