package com.trj.jk.web.model.request;

import com.trj.jk.web.domain.entity.regex.RegexContext;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * 借款申请业务对象
 * Created by xierongli on 17/7/27.
 */
public class LoanApplyReq {

    @NotBlank(message="借款申请产品不能为空",groups = Integer.class)
    private Integer productId;
    @NotBlank(message="省份信息不可以为空")
    private String province;
    @NotBlank(message="城市不能为空")
    private String city;
    @NotBlank(message="区域信息不可以为空")
    private String district;

    @NotBlank(message="名字不能为空")
    private String name;
    @NotBlank(message="手机号不能为空")
    @Pattern(regexp = RegexContext.MOBILE_REGEX,message="手机号格式不合法")
    private String mobile;
    @NotBlank(message="身份信息不能为空")
    private String identityId;
    @NotBlank(message="借款申请金额不能为空",groups=BigDecimal.class)
    private BigDecimal amount;
    @NotBlank(message="借款步骤码不能为空")
    private String stepCode;


    public String getStepCode() {
        return stepCode;
    }

    public void setStepCode(String stepCode) {
        this.stepCode = stepCode;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

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

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
