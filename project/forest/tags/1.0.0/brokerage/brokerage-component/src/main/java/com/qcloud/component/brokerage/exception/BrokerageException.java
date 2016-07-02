package com.qcloud.component.brokerage.exception;

import com.qcloud.pirates.exception.PiratesRuntimeException;

public class BrokerageException extends PiratesRuntimeException {

	public BrokerageException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BrokerageException(String message) {
		super(message);
	}
}
