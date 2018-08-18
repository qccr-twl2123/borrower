package com.trj.jk.web.domain.exception;

public class LoginException extends RuntimeException {
	public LoginException() {
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginException(Throwable cause) {
		super(cause);
	}
}
