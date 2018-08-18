package com.trj.jk.web.domain.entity;

import java.io.Serializable;
import java.util.List;

public class RepayDetailsBean implements Serializable{

	private String label;
	
	private List<RepayDetailsInfoBean> list;


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<RepayDetailsInfoBean> getList() {
		return list;
	}

	public void setList(List<RepayDetailsInfoBean> list) {
		this.list = list;
	}

	
}
