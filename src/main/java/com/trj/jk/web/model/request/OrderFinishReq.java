package com.trj.jk.web.model.request;

import com.trj.jk.web.domain.entity.regex.RegexContext;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 *  订单完成请求参数
 * Created by xierongli on 17/10/18.
 */
public class OrderFinishReq {

    @NotBlank(message = "产品代码不能为空")
    private String productCode;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = RegexContext.MOBILE_REGEX, message = "手机号格式不合法")
    private String mobile;
    @NotBlank(message = "外部订单号不为空")
    private String orderNo;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "身份证号不能为空")
    @Size(min = 15, max = 18, message = "身份证号码长度不合法")
    private String identityId;

    private String corpName;

    @Valid
    private List<OpenUserContactReq> contacts;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

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

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public List<OpenUserContactReq> getContacts() {
        return contacts;
    }

    public void setContacts(List<OpenUserContactReq> contacts) {
        this.contacts = contacts;
    }
}
