package com.qcloud.component.publicservice.model;

import java.util.Date;

public class Message {

    private long   id;

    private long   typeId;

    // 消息接收人
    private long   receiver;

    // 消息标题
    private String title;

    // 消息内容
    private String content;

    // 发送时间
    private Date   time;

    // 是否已读 1未读 2是 3删除
    private int    state;

    // 消息类型
    private int    classify;

    private String url;

    public Message() {

    }

    public Message(long id, long typeId, long receiver, String title, String content, Date time, int state, int classify, String url) {

        super();
        this.id = id;
        this.typeId = typeId;
        this.receiver = receiver;
        this.title = title;
        this.content = content;
        this.time = time;
        this.state = state;
        this.classify = classify;
        this.url = url;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getTypeId() {

        return typeId;
    }

    public void setTypeId(long typeId) {

        this.typeId = typeId;
    }

    public long getReceiver() {

        return receiver;
    }

    public void setReceiver(long receiver) {

        this.receiver = receiver;
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

    public Date getTime() {

        return time;
    }

    public void setTime(Date time) {

        this.time = time;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getClassify() {

        return classify;
    }

    public void setClassify(int classify) {

        this.classify = classify;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }
}
