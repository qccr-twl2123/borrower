package com.trj.jk.web.domain.entity.authentication.bi;

public class BiRetResult {
	private String website;
	
	private String token;

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "BiRetResult [website=" + website + ", token=" + token + "]";
	}
}
