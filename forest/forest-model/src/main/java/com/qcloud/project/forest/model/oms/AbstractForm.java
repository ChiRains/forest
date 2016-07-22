package com.qcloud.project.forest.model.oms;

import java.util.Date;

public abstract class AbstractForm {

    public String  method;

    public Date    timestamp;

    private String format;

    public String getMethod() {

        return method;
    }

    public void setMethod(String method) {

        this.method = method;
    }

    public Date getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(Date timestamp) {

        this.timestamp = timestamp;
    }

    public String getFormat() {

        return format;
    }

    public void setFormat(String format) {

        this.format = format;
    }
}
