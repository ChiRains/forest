package com.qcloud.component.my.web.form;

public class InvoiceForm {

    // 模式 1开发票 1不开发票
    private int    mode;

    // 类型 1普通 1增值税
    private int    type;

    // 抬头
    private String head;

    // 内容
    private String content;

    public int getMode() {

        return mode;
    }

    public void setMode(int mode) {

        this.mode = mode;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public String getHead() {

        return head;
    }

    public void setHead(String head) {

        this.head = head;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }
}
