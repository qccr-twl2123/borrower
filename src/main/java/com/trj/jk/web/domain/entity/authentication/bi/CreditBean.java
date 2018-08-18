package com.trj.jk.web.domain.entity.authentication.bi;

public class CreditBean extends BiParamBase{
	private String personName;
	private String personId;
	private String captcha;
	private String account;
	private String passwd;
	private String phone;	
	public CreditBean(String personName, String personId, String captcha,
			String account, String password, String phone) {
		super();
		this.personName = personName;
		this.personId = personId;
		this.captcha = captcha;
		this.account = account;
		this.passwd = password;
		this.phone = phone;
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
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
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
	public void setPasswd(String password) {
		this.passwd = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
		
}
