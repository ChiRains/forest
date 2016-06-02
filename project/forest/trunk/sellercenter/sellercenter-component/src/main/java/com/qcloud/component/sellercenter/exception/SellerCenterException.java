package com.qcloud.component.sellercenter.exception;

import com.qcloud.pirates.exception.PiratesRuntimeException;

public class SellerCenterException extends PiratesRuntimeException {

	public SellerCenterException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SellerCenterException(String message) {
		super(message);
	}
}
