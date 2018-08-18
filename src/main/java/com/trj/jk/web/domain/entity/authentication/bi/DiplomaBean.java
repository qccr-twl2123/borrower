package com.trj.jk.web.domain.entity.authentication.bi;

public class DiplomaBean extends BiParamBase{
	private String personName;
	private String personId;
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
	public DiplomaBean(String personName, String personId) {
		super();
		this.personName = personName;
		this.personId = personId;
	}
	
}
