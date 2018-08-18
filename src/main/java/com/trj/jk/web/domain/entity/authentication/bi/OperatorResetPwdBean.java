package com.trj.jk.web.domain.entity.authentication.bi;

import java.io.Serializable;

/**
 * 修改运营商服务密码调用bi借口bean
 *
 */
public class OperatorResetPwdBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9092298813662929599L;
	
	//姓名
	private String name;
	
	//手机号
	private String mobile;
	
	//省份证号
	private String identityId;
	
	//重置的密码
	private String password;
	
	//手机验证码
	private String verifyCode;
	
	//bi回传
	private String website;
	
	//bi回传
	private String token;
	
	//类型
	private String type;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OperatorResetPwdBean [name=" + name + ", mobile=" + mobile + ", identityId=" + identityId
				+ ", password=" + password + ", verifyCode=" + verifyCode + ", website=" + website + ", token=" + token
				+ ", type=" + type + "]";
	}
	
	
}
