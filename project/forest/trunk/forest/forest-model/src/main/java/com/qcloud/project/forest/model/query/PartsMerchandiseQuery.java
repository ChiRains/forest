package com.qcloud.project.forest.model.query;

import java.util.ArrayList;
import java.util.List;

public class PartsMerchandiseQuery {

    private List<Long> merchandiseIds = new ArrayList<Long>();

    private long       classifyId;

    private int        state;

    public List<Long> getMerchandiseIds() {

        return merchandiseIds;
    }

    public void setMerchandiseIds(List<Long> merchandiseIds) {

        this.merchandiseIds = merchandiseIds;
    }

    public long getClassifyId() {

        return classifyId;
    }

    public void setClassifyId(long classifyId) {

        this.classifyId = classifyId;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }
}
