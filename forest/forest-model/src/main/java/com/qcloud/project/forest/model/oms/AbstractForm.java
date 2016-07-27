package com.qcloud.project.forest.model.oms;

public abstract class AbstractForm {

    public String  method;

    public String  timestamp;

    private String format;

    private String app_id;

    private String sign;

    private String secret;

    public String getMethod() {

        return method;
    }

    public void setMethod(String method) {

        this.method = method;
    }

    public String getFormat() {

        return format;
    }

    public void setFormat(String format) {

        this.format = format;
    }

    public String getApp_id() {

        return app_id;
    }

    public void setApp_id(String app_id) {

        this.app_id = app_id;
    }

    public String getSign() {

        return sign;
    }

    public void setSign(String sign) {

        this.sign = sign;
    }

    public String getSecret() {

        return secret;
    }

    public void setSecret(String secret) {

        this.secret = secret;
    }

    public String getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(String timestamp) {

        this.timestamp = timestamp;
    }
}
