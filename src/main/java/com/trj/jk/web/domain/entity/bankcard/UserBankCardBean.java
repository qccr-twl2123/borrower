package com.trj.jk.web.domain.entity.bankcard;

import com.trj.jk.web.domain.UserBankCard;

public class UserBankCardBean extends UserBankCard{
	private String mobile;
	private String verifyCode;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
