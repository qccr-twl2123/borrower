package com.trj.jk.web.model.request;

import com.trj.jk.web.domain.entity.regex.RegexContext;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 绑定银行卡申请Model
 * Created by xierongli on 17/9/7.
 */
public class BindBankCardApplyReq {

    private Integer uid;

    private String bankBranch;
    @NotBlank(message = "持卡人姓名不能为空")
    private String cardHolder;
    @NotBlank(message = "银行卡号不能为空")
    private String cardId;


    @NotBlank(message = "证件号不能为空")
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
