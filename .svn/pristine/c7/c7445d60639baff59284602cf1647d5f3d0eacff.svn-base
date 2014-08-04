package org.hy.foundation.common.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	
	@SuppressWarnings("unused")
	private Exception exception;
	@SuppressWarnings("unused")
	private String message;
	@SuppressWarnings("unused")
	private String code;
	
	public CustomException() {
		super(null, null);
	}

	public CustomException(Exception exception, String message) {
		super(exception);
		this.exception = exception;
		this.message = message;
	}

	public CustomException(Exception exception, String message, String code) {
		super(exception);
		this.exception = exception;
		this.message = message;
		this.code = code;
	}
}
