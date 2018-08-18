package com.trj.jk.web.domain.entity.user;

import com.trj.jk.web.domain.UserBasic;

public class UserBean extends UserBasic{
	String oldPassword;

	String modifyPassowrdWay;
	
	String name;
	
	String protectQuestionNo;
	
	
	public String getProtectQuestionNo() {
		return protectQuestionNo;
	}

	public void setProtectQuestionNo(String protectQuestionNo) {
		this.protectQuestionNo = protectQuestionNo;
	}

	public String getName() {
		return name;
	}	

	public void setName(String name) {
		this.name = name;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public String getModifyPassowrdWay() {
		return modifyPassowrdWay;
	}

	public void setModifyPassowrdWay(String modifyPassowrdWay) {
		this.modifyPassowrdWay = modifyPassowrdWay;
	}

	@Override
	public String toString() {
		return "UserBean [oldPassword=" + oldPassword + ", modifyPassowrdWay=" + modifyPassowrdWay + ", name=" + name
				+ ", protectQuestionNo=" + protectQuestionNo + "]";
	}	
	
}
