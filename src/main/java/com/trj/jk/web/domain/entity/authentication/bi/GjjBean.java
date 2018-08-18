package com.trj.jk.web.domain.entity.authentication.bi;

public class GjjBean extends BiParamBase{
	private String personName;
	private String personId;
	private String areaCode;
	private String account;
	private String passwd;
	private String phone;
	private String sort;
	
	public GjjBean(String personName, String personId, String areaCode,
			String account, String password, String phone,String sort) {
		super();
		this.personName = personName;
		this.personId = personId;
		this.areaCode = areaCode;
		this.account = account;
		this.passwd = password;
		this.phone = phone;
		this.sort = sort;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPassword(String password) {
		this.passwd = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
