package com.qcloud.component.marketing.exception;

import com.qcloud.pirates.exception.PiratesRuntimeException;

public class MarketingException extends PiratesRuntimeException {

	public MarketingException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MarketingException(String message) {
		super(message);
	}
}
