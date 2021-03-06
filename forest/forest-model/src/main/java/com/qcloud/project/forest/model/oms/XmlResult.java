package com.qcloud.project.forest.model.oms;

public class XmlResult {

    public int    code;

    public String msg;

    public XmlResult() {

        this.code = 0;
        this.msg = "成功";
    }

    public XmlResult(int code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {

        this.code = code;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {

        this.msg = msg;
    }
}
