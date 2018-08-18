package com.trj.jk.web.domain.entity.personInfo.certfication;

public class CertficationInfo {
	private String key;
	private String name;
	private String field;
	//是否必填0：不必填，1：必填
	private Byte isFill;
	private String url;
	private String result;	
	//通过的状态：0：不必填通过，1:必填通过 ，2：二选一通过
	private Byte passStatus;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Byte getIsFill() {
		return isFill;
	}
	public void setIsFill(Byte isFill) {
		this.isFill = isFill;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Byte getPassStatus() {
		return passStatus;
	}
	public void setPassStatus(Byte passStatus) {
		this.passStatus = passStatus;
	}
	@Override
	public String toString() {
		return "CertficationInfo [key=" + key + ", name=" + name + ", field=" + field + ", isFill=" + isFill + ", url="
				+ url + ", result=" + result + ", passStatus=" + passStatus + "]";
	}
	
}
