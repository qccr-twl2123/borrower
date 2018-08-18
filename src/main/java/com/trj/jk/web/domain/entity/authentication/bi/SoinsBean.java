package com.trj.jk.web.domain.entity.authentication.bi;

public class SoinsBean extends BiParamBase{
	public SoinsBean(){

	}
	public SoinsBean(String personName, String personId, String areaCode,
			String account, String password, String phone, String website,
			String type, String sort, String email) {
		super();
		this.personName = personName;
		this.personId = personId;
		this.areaCode = areaCode;
		this.account = account;
		this.passwd = password;
		this.phone = phone;
		this.website = website;
		this.type = type;
		this.sort = sort;
		this.email = email;
	}

	public SoinsBean(String personName, String personId, String phone) {
		super();
		this.personName = personName;
		this.personId = personId;
		this.phone = phone;
	}

	private String personName;
	private String personId;
	private String areaCode;
	private String account;
	private String passwd;
	private String phone;
	private String website;
	private String type;
	private String sort;
	private String email;
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
	public void setPassword(String passwd) {
		this.passwd = passwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
