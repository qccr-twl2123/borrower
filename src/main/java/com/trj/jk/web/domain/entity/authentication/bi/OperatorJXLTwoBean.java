package com.trj.jk.web.domain.entity.authentication.bi;

public class OperatorJXLTwoBean extends BiParamBase{
	private String personId;
	private String account;
	private String passwd;
	private String captcha;

	private String type="SUBMIT_CAPTCHA";
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
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
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	
	public OperatorJXLTwoBean(String personId, String account, String passwd,
			String captcha) {
		super();
		this.personId = personId;
		this.account = account;
		this.passwd = passwd;
		this.captcha = captcha;
	}	
}
