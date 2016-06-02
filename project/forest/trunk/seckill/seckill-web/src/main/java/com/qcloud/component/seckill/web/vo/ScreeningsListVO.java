package com.qcloud.component.seckill.web.vo;

public class ScreeningsListVO {

    //
    private long    id;

    private String  beginTimeStr;

    private String  endTimeStr;

    // 1结束 2进行中 3即将开枪
    private int     state;

    private String  stateStr;

    private boolean current;

    private String  title;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getBeginTimeStr() {

        return beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr) {

        this.beginTimeStr = beginTimeStr;
    }

    public String getEndTimeStr() {

        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {

        this.endTimeStr = endTimeStr;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public String getStateStr() {

        return stateStr;
    }

    public void setStateStr(String stateStr) {

        this.stateStr = stateStr;
    }

    public boolean isCurrent() {

        return current;
    }

    public void setCurrent(boolean current) {

        this.current = current;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }
}
