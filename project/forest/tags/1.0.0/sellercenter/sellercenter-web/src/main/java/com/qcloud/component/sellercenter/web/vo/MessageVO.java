package com.qcloud.component.sellercenter.web.vo;

public class MessageVO {

    private Long    id;

    // 消息标题
    private String  title;

    // 消息内容
    private String  content;

    // 发送时间
    private String  time;

    private boolean read;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
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

    public boolean isRead() {

        return read;
    }

    public void setRead(boolean read) {

        this.read = read;
    }
}
