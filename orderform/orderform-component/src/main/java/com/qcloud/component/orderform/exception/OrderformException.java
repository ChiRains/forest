package com.qcloud.component.orderform.exception;

import com.qcloud.pirates.exception.PiratesRuntimeException;

public class OrderformException extends PiratesRuntimeException {

	public OrderformException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public OrderformException(String message) {
		super(message);
	}
}
