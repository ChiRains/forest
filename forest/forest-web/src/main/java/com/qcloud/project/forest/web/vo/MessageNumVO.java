package com.qcloud.project.forest.web.vo;

public class MessageNumVO {

    private int type;

    private int num;

    public MessageNumVO() {

        super();
    }

    public MessageNumVO(int type, int num) {

        super();
        this.type = type;
        this.num = num;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public int getNum() {

        return num;
    }

    public void setNum(int num) {

        this.num = num;
    }
}
