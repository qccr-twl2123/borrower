package com.trj.jk.web.domain.entity;

import java.io.Serializable;

public class MobileValidateCode implements Serializable{

	private int uid;
	
	private int smsId;
	
	private String code;
	
	private String mobile;
	
	private long validTime;
	
	private byte status;
	
	private String miNo;

	private long modifyTime;
	
	private long createTime;
	
	private int creator;
	
	private int updator;
	
	private int version;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getSmsId() {
		return smsId;
	}

	public void setSmsId(int smsId) {
		this.smsId = smsId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public long getValidTime() {
		return validTime;
	}

	public void setValidTime(long validTime) {
		this.validTime = validTime;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getMiNo() {
		return miNo;
	}

	public void setMiNo(String miNo) {
		this.miNo = miNo;
	}

	public long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public int getUpdator() {
		return updator;
	}

	public void setUpdator(int updator) {
		this.updator = updator;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
