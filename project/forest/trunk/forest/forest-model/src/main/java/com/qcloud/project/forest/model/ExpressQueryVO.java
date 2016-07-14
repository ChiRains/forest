package com.qcloud.project.forest.model;

public class ExpressQueryVO {

    private String time;

    private String context;

    private String location;

    public ExpressQueryVO() {

    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {

        this.time = time;
    }

    public String getContext() {

        return context;
    }

    public void setContext(String context) {

        this.context = context;
    }

    public String getLocation() {

        return location;
    }

    public ExpressQueryVO(String time, String context, String location) {

        super();
        this.time = time;
        this.context = context;
        this.location = location;
    }

    public void setLocation(String location) {

        this.location = location;
    }
}
