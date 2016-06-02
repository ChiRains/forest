package com.qcloud.component.warehouse.exception;

import com.qcloud.pirates.exception.PiratesRuntimeException;

public class WarehouseException extends PiratesRuntimeException {

	public WarehouseException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public WarehouseException(String message) {
		super(message);
	}
}
