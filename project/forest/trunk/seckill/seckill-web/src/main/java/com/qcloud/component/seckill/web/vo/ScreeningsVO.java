package com.qcloud.component.seckill.web.vo;

import java.util.ArrayList;
import java.util.List;

public abstract class ScreeningsVO {

    private long         id;

    private String       beginTimeStr;

    private String       endTimeStr;

    // 1结束 2进行中 3即将开枪
    private int          state;

    private String       nowStr;

    // 轮播图
    private List<String> screeningsSlideList = new ArrayList<String>();

    public ScreeningsVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
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

    public String getNowStr() {

        return nowStr;
    }

    public void setNowStr(String nowStr) {

        this.nowStr = nowStr;
    }

    public List<String> getScreeningsSlideList() {

        return screeningsSlideList;
    }

    public void setScreeningsSlideList(List<String> screeningsSlideList) {

        this.screeningsSlideList = screeningsSlideList;
    }
}
