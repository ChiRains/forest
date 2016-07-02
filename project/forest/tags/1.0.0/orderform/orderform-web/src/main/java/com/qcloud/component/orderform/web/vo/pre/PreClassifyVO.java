package com.qcloud.component.orderform.web.vo.pre;

import java.util.ArrayList;
import java.util.List;

public class PreClassifyVO {

    private long                 classifyId;

    private String               classifyName;

    private String               image;

    private List<PreOrderItemVO> preOrderItemList = new ArrayList<PreOrderItemVO>();

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

    public List<PreOrderItemVO> getPreOrderItemList() {

        return preOrderItemList;
    }

    public void setPreOrderItemList(List<PreOrderItemVO> preOrderItemList) {

        this.preOrderItemList = preOrderItemList;
    }
}
