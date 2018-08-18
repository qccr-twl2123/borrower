package com.trj.jk.web.domain.entity.authentication.bi;

public class CreditcardBean extends BiParamBase{

	private String personName;
	private String personId;
	private String email;

	private String passwd;
	private String phone;	
	private String loginType;
	private String identity;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public CreditcardBean(String personName, String personId, String email,
			String passwd, String phone, String loginType,
			String identity) {
		super();
		this.personName = personName;
		this.personId = personId;
		this.email = email;
		this.passwd = passwd;
		this.phone = phone;
		this.loginType = loginType;
		this.identity = identity;
	}		
}
