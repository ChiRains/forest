package com.qcloud.component.my.exception;

import com.qcloud.pirates.exception.PiratesRuntimeException;

public class MyException extends PiratesRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -3547031750063458851L;

    public MyException(String msg, Throwable cause) {

        super(msg, cause);
    }

    public MyException(String message) {

        super(message);
    }
}
