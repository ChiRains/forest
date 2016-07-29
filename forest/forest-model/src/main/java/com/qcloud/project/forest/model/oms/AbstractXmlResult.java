package com.qcloud.project.forest.model.oms;

public abstract class AbstractXmlResult {

    public int    code;

    public String msg;

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {

        this.msg = msg;
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {

        this.code = code;
    }
}
