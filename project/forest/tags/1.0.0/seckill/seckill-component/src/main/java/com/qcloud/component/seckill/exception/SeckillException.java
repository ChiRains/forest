package com.qcloud.component.seckill.exception;

import com.qcloud.pirates.exception.PiratesRuntimeException;

public class SeckillException extends PiratesRuntimeException {

	public SeckillException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SeckillException(String message) {
		super(message);
	}
}
