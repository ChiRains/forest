package com.qcloud.component.goods.exception;

import com.qcloud.pirates.exception.PiratesRuntimeException;

public class CommoditycenterException extends PiratesRuntimeException {

	/**
     * 
     */
    private static final long serialVersionUID = 2314627057682340030L;

    public CommoditycenterException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CommoditycenterException(String message) {
		super(message);
	}
}
