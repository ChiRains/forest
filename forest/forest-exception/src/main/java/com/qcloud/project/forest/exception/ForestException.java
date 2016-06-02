package com.qcloud.project.forest.exception;

import com.qcloud.pirates.exception.PiratesRuntimeException;

public class ForestException extends PiratesRuntimeException {

	public ForestException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ForestException(String message) {
		super(message);
	}
}
