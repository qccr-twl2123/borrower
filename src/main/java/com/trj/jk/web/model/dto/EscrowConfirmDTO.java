package com.trj.jk.web.model.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 存管开户回调传入数据bean
 * @author -ming-
 *
 */
@SuppressWarnings("serial")
public class EscrowConfirmDTO implements Serializable {
	
	/**
	 * 状态码
	 */
	@NotNull(message="状态码不可以为空")
	private Integer code;
	
	/**
	 * 消息
	 */
	private String msg;
	/**
	 * 姓名
	 */
	@NotBlank(message = "姓名不能为空")
	private String name;
	
	/**
	 * 手机号
	 */
	@NotBlank(message = "手机号码不能为空")
	private String mobile;
	
	/**
	 * 身份证号
	 */
	@NotBlank(message = "身份证不能为空")
	private String identityId;
	
	/**
	 * 存管银行账号
	 */
	private String bankCardNo;

	/**
	 * 平台标识
	 */
	@NotBlank(message = "平台标识不能为空")
	private String tenant;
	
	/**
	 * 签名标识
	 */
	@NotBlank(message = "签名不能为空")
	private String sign;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "EscrowConfirmDTO [code=" + code + ", msg=" + msg + ", name=" + name + ", mobile=" + mobile
				+ ", identityId=" + identityId + ", bankCardNo=" + bankCardNo + ", tenant=" + tenant + ", sign=" + sign
				+ "]";
	}
	
}
