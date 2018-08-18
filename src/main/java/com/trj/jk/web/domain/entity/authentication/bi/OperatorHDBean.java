package com.trj.jk.web.domain.entity.authentication.bi;

public class OperatorHDBean extends BiParamBase{
	public OperatorHDBean(String personName, String personId, String phone) {
		super();
		this.personName = personName;
		this.personId = personId;
		this.phone = phone;
	}
	private String personName;
	private String personId;
	private String phone;
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
	
}
