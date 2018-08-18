package com.trj.jk.web.domain.entity.personInfo;

import java.io.Serializable;
import java.util.List;

import com.trj.jk.web.domain.UserContacts;
import com.trj.jk.web.domain.UserExt;
import com.trj.jk.web.domain.UserProfession;

public class BasicallyInfo implements Serializable{
	
	/**
	 * 身份信息
	 */
	private UserExt userExt;
	
	/**
	 * 工作信息
	 */
	private List<UserProfession> userProfessions;
	
	/**
	 * 联系人信息
	 */
	private List<UserContacts> userContacts;


	/**
	 * 驾驶证信息
	 */
	private DriveLicence driverLicence;
	
	public UserExt getUserExt() {
		return userExt;
	}

	public void setUserExt(UserExt userExt) {
		this.userExt = userExt;
	}

	public List<UserProfession> getUserProfessions() {
		return userProfessions;
	}

	public void setUserProfessions(List<UserProfession> userProfessions) {
		this.userProfessions = userProfessions;
	}

	public List<UserContacts> getUserContacts() {
		return userContacts;
	}

	public void setUserContacts(List<UserContacts> userContacts) {
		this.userContacts = userContacts;
	}

	public DriveLicence getDriverLicence() {
		return driverLicence;
	}

	public void setDriverLicence(DriveLicence driverLicence) {
		this.driverLicence = driverLicence;
	}
	
}
