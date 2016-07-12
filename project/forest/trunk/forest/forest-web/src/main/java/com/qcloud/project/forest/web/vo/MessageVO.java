package com.qcloud.project.forest.web.vo;

public class MessageVO {

    private String content;

    private String time;

    private String url;

    public MessageVO() {

        super();
    }

    public MessageVO(String content, String time, String url) {

        super();
        this.content = content;
        this.time = time;
        this.url = url;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {

        this.time = time;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }
}
