package com.trj.jk.web.domain.entity.authentication.bi;

public class OperatorJXLBean extends BiParamBase{
	private String personName;
	private String personId;
	private String phone;
	private String contact_tel1;
	private String contact_name1;
	private String contact_type1;
	private String contact_tel2;
	private String contact_name2;
	private String contact_type2;	

	private String contact_tel3;
	private String contact_name3;
	private String contact_type3;	
	private String account;
	private String passwd;
	private String captcha;
	private String type="SUBMIT_CAPTCHA";	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContact_tel1() {
		return contact_tel1;
	}
	public void setContact_tel1(String contact_tel1) {
		this.contact_tel1 = contact_tel1;
	}
	public String getContact_name1() {
		return contact_name1;
	}
	public void setContact_name1(String contact_name1) {
		this.contact_name1 = contact_name1;
	}
	public String getContact_type1() {
		return contact_type1;
	}
	public void setContact_type1(String contact_type1) {
		this.contact_type1 = contact_type1;
	}
	public String getContact_tel2() {
		return contact_tel2;
	}
	public void setContact_tel2(String contact_tel2) {
		this.contact_tel2 = contact_tel2;
	}
	public String getContact_name2() {
		return contact_name2;
	}
	public void setContact_name2(String contact_name2) {
		this.contact_name2 = contact_name2;
	}
	public String getContact_type2() {
		return contact_type2;
	}
	public void setContact_type2(String contact_type2) {
		this.contact_type2 = contact_type2;
	}
	public String getContact_tel3() {
		return contact_tel3;
	}
	public void setContact_tel3(String contact_tel3) {
		this.contact_tel3 = contact_tel3;
	}
	public String getContact_name3() {
		return contact_name3;
	}
	public void setContact_name3(String contact_name3) {
		this.contact_name3 = contact_name3;
	}
	public String getContact_type3() {
		return contact_type3;
	}
	public void setContact_type3(String contact_type3) {
		this.contact_type3 = contact_type3;
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
	
	public OperatorJXLBean(String personName, String personId, String phone,
			String contact_tel1, String contact_name1, String contact_type1,
			String contact_tel2, String contact_name2, String contact_type2,
			String contact_tel3, String contact_name3, String contact_type3,
			String account, String passwd, String captcha) {
		super();
		this.personName = personName;
		this.personId = personId;
		this.phone = phone;
		this.contact_tel1 = contact_tel1;
		this.contact_name1 = contact_name1;
		this.contact_type1 = contact_type1;
		this.contact_tel2 = contact_tel2;
		this.contact_name2 = contact_name2;
		this.contact_type2 = contact_type2;
		this.contact_tel3 = contact_tel3;
		this.contact_name3 = contact_name3;
		this.contact_type3 = contact_type3;
		this.account = account;
		this.passwd = passwd;
		this.captcha = captcha;
	}	

}

