package com.trj.jk.web.domain.entity;

public class BuyCarInfoBean {

	private String title;
	
	private byte status;
	
	private byte isFill;
	
	private String statusStr;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public byte getIsFill() {
		return isFill;
	}

	public void setIsFill(byte isFill) {
		this.isFill = isFill;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	@Override
	public String toString() {
		return "BuyCarInfoBean [title=" + title + ", status=" + status + ", isFill=" + isFill + ", statusStr="
				+ statusStr + "]";
	}
	
	
}
