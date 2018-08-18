package com.trj.jk.web.model.dto;

import java.io.Serializable;

/**
 * 存管开户返回结果bean
 * @author -ming-
 *
 */

@SuppressWarnings("serial")
public class EscrowOpenDTO implements Serializable {
	
	/**
	 * 是否存管
	 */
	private boolean isEscrow;
	
	/**
	 * 多多理财页面跳转url
	 */
	private String ddUrl;
	
	public boolean isEscrow() {
		return isEscrow;
	}

	public void setEscrow(boolean isEscrow) {
		this.isEscrow = isEscrow;
	}

	public String getDdUrl() {
		return ddUrl;
	}

	public void setDdUrl(String ddUrl) {
		this.ddUrl = ddUrl;
	}

	@Override
	public String toString() {
		return "EscrowOpenDTO [isEscrow=" + isEscrow + ", ddUrl=" + ddUrl + "]";
	}
}
