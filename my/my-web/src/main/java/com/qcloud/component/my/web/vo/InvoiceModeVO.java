package com.qcloud.component.my.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class InvoiceModeVO {

    // ID
    private long   id;

    // 模式 1开发票 1不开发票
    private int    mode;

    // 类型 1普通 1增值税
    private int    type;

    // 抬头
    private String head;

    // 内容
    private String content;

    public InvoiceModeVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setMode(int mode) {

        this.mode = mode;
    }

    public int getMode() {

        return mode;
    }

    public void setType(int type) {

        this.type = type;
    }

    public int getType() {

        return type;
    }

    public void setHead(String head) {

        this.head = head;
    }

    public String getHead() {

        return head;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getContent() {

        return content;
    }
}
