package com.qcloud.project.forest.model.query;

import java.util.ArrayList;
import java.util.List;

public class PartsMerchandiseQuery {

    private List<Long> merchandiseIds = new ArrayList<Long>();

    private Long       classifyId;

    public List<Long> getMerchandiseIds() {

        return merchandiseIds;
    }

    public void setMerchandiseIds(List<Long> merchandiseIds) {

        this.merchandiseIds = merchandiseIds;
    }

    public Long getClassifyId() {

        return classifyId;
    }

    public void setClassifyId(Long classifyId) {

        this.classifyId = classifyId;
    }
}
