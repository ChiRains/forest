package com.qcloud.component.orderform.web.vo.personal;

import java.util.ArrayList;
import java.util.List;

public class OrderClassifyVO {

    private long              classifyId;

    private String            classifyName;

    private String            image;

    private List<OrderItemVO> orderItemList = new ArrayList<OrderItemVO>();

    public long getClassifyId() {

        return classifyId;
    }

    public void setClassifyId(long classifyId) {

        this.classifyId = classifyId;
    }

    public String getClassifyName() {

        return classifyName;
    }

    public void setClassifyName(String classifyName) {

        this.classifyName = classifyName;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public List<OrderItemVO> getOrderItemList() {

        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemVO> orderItemList) {

        this.orderItemList = orderItemList;
    }
}
