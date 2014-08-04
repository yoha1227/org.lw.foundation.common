package org.hy.foundation.common.exception;

import javax.ws.rs.core.Response.Status;

public class RestRuntimeException extends RuntimeException {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	private String reason;
    private Status status;
    private int errorCode;

	public RestRuntimeException() {
		super(null,null);
	}

    public RestRuntimeException(String message, String reason, Status status, int errorCode) {
        super(message);
        this.reason = reason;
        this.status = status;
        this.errorCode = errorCode;
    }


}
