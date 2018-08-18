package com.trj.jk.web.domain.exception;

public class InvokeException extends RuntimeException{
	public InvokeException() {
	}

	public InvokeException(String message) {
		super(message);
	}

	public InvokeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvokeException(Throwable cause) {
		super(cause);
	}
}
