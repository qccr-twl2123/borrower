package com.trj.jk.web.domain.entity.login;

import com.trj.commons.result.Result;

public class AuthResult<T> extends Result<T>{
	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
