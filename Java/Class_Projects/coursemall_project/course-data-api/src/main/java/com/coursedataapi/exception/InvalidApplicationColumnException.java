package com.coursedataapi.exception;

public class InvalidApplicationColumnException extends RuntimeException {

	public InvalidApplicationColumnException(String message) {

		super(message);
	}

	public InvalidApplicationColumnException(Throwable cause) {

		super(cause);
	}

	public InvalidApplicationColumnException(String message, Throwable cause) {

		super(message, cause);
	}

}
