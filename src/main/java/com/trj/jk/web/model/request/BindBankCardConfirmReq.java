package com.trj.jk.web.model.request;

import com.trj.jk.web.domain.entity.regex.RegexContext;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 绑定银行卡确认Model
 * Created by xierongli on 17/9/8.
 */
public class BindBankCardConfirmReq {

    private Integer uid;
    @NotNull(message = "银行卡号主键不为空")
    private Integer id;

    @NotBlank(message = "验证码不能为空")
    private String verifyCode;
    @NotBlank(message = "银行机构不能为空")
    private String bankBranch;
    @NotBlank(message = "持卡人姓名不能为空")
    private String cardHolder;
    @NotBlank(message = "银行卡号不能为空")
    private String cardId;
    @NotBlank(message = "绑单号不能为空")
    private String bindNo;


    @NotBlank(message = "证件号不能唯恐")
    private String identityCode;
    @NotBlank(message = "银行预留手机号码不能为空")
    private String mediaId;
    @NotBlank(message="手机号不能为空")
    @Pattern(regexp = RegexContext.MOBILE_REGEX,message = "手机号格式不正确")
    private String mobile;
    @NotBlank(message = "身份不为空")
    private String province;
    @NotBlank(message="城市不能为空")
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBindNo() {
        return bindNo;
    }

    public void setBindNo(String bindNo) {
        this.bindNo = bindNo;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
}
