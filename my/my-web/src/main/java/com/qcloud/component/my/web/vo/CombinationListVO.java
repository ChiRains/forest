package com.qcloud.component.my.web.vo;

import java.util.ArrayList;
import java.util.List;

public class CombinationListVO {

    private long                   unifiedMerchandiseId;

    private String                 name;

    private String                 desc;

    private double                 sum;

    private int                    number;

    private int                    stock;

    private String                 group;

    private int                    type            = 1;

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

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public int getStock() {

        return stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public String getGroup() {

        return group;
    }

    public void setGroup(String group) {

        this.group = group;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }
}
