package com.qcloud.project.forest.outside.entity;

public abstract class AbstractResult {

    public String  exceptionMsg;

    public Object  message;

    private String result;

    public String getExceptionMsg() {

        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {

        this.exceptionMsg = exceptionMsg;
    }

    public String getResult() {

        return result;
    }

    public void setResult(String result) {

        this.result = result;
    }

    public Object getMessage() {

        return message;
    }

    public void setMessage(Object message) {

        this.message = message;
    }
}
