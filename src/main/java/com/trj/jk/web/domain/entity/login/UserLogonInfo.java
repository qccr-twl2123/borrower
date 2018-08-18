package com.trj.jk.web.domain.entity.login;


public class UserLogonInfo {
	private static final long serialVersionUID = -6420863261986626239L;
	private Integer userId;//用户id

	private Long logonTime;//上次操作时间
	private String logonIP;//上次登录ip

	private String theme="default";//主题
//	private String isForced; //是否需要强制修改默认密码

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public String getTheme() {
		return theme;
	}

	public Long getLogonTime() {
		return logonTime;
	}
	public void setLogonTime(Long logonTime) {
		this.logonTime = logonTime;
	}
	public String getLogonIP() {
		return logonIP;
	}
	public void setLogonIP(String logonIP) {
		this.logonIP = logonIP;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}



}
