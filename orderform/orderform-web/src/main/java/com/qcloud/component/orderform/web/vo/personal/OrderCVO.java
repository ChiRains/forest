package com.qcloud.component.orderform.web.vo.personal;

import java.util.ArrayList;
import java.util.List;

public class OrderCVO extends AbstractOrderVO {

    private String                explain;

    private List<OrderClassifyVO> classifyList = new ArrayList<OrderClassifyVO>();

    public List<OrderClassifyVO> getClassifyList() {

        return classifyList;
    }

    public void setClassifyList(List<OrderClassifyVO> classifyList) {

        this.classifyList = classifyList;
    }

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }
}
