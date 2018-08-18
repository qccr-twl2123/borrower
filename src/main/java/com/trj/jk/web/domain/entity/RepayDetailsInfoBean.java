package com.trj.jk.web.domain.entity;

import java.io.Serializable;

public class RepayDetailsInfoBean implements Serializable{

	private String title;
	
	private String info;

	public RepayDetailsInfoBean() {
		super();
	}

	public RepayDetailsInfoBean(String title, String info) {
		super();
		this.title = title;
		this.info = info;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
