package com.trj.jk.web.domain.exception;

public class CheckException extends RuntimeException{
	public CheckException() {
	}

	public CheckException(String message) {
		super(message);
	}

	public CheckException(String message, Throwable cause) {
		super(message, cause);
	}

	public CheckException(Throwable cause) {
		super(cause);
	}
}
