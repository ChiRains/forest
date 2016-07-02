package com.qcloud.component.pay.exception;

import com.qcloud.pirates.exception.PiratesRuntimeException;

public class PayException extends PiratesRuntimeException {

	public PayException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public PayException(String message) {
		super(message);
	}
}
