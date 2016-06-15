package com.qcloud.component.my.web.vo;

import java.util.ArrayList;
import java.util.List;

public class CombinationListVO {

    private long                   unifiedMerchandiseId;

    private String                 name;

    private String                 desc;

    private double                 sum;

    private List<MyShoppingCartVO> merchandiseList = new ArrayList<MyShoppingCartVO>();

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public double getSum() {

        return sum;
    }

    public void setSum(double sum) {

        this.sum = sum;
    }

    public List<MyShoppingCartVO> getMerchandiseList() {

        return merchandiseList;
    }

    public void setMerchandiseList(List<MyShoppingCartVO> merchandiseList) {

        this.merchandiseList = merchandiseList;
    }

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }
}
