package com.qcloud.component.personalcenter.exception;
import com.qcloud.pirates.exception.PiratesRuntimeException;
public class PersonalCenterException extends PiratesRuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = -6640944052112269100L;

    public PersonalCenterException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PersonalCenterException(String message) {
        super(message);
    }
}
