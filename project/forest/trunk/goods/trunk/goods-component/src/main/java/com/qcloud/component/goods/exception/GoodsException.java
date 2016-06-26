package com.qcloud.component.goods.exception;

import com.qcloud.pirates.exception.PiratesRuntimeException;

public class GoodsException extends PiratesRuntimeException {

	public GoodsException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public GoodsException(String message) {
		super(message);
	}
}
