package com.qcloud.project.forest.web.vo;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.orderform.web.vo.personal.OrderItemVO;

public class CombinationItemVO {

    private long              unifiedMerchandiseId;

    // 折扣价,成交价
    private double            discount;

    // 数量
    private double            price;

    // 数量
    private int               number;

    // 商品名称
    private String            name;

    // 缩略图
    private String            image;

    private boolean           isAfterSale;

    private boolean           isEvaluation;
    
    private boolean           isAddEvaluation;

    private String            desc;

    // 订单项
    private Long              orderItemId;

    private List<OrderItemVO> orderItemList = new ArrayList<OrderItemVO>();

    public long getUnifiedMerchandiseId() {

        return unifiedMerchandiseId;
    }

    public void setUnifiedMerchandiseId(long unifiedMerchandiseId) {

        this.unifiedMerchandiseId = unifiedMerchandiseId;
    }

    public double getDiscount() {

        return discount;
    }

    public void setDiscount(double discount) {

        this.discount = discount;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
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

    public boolean isAfterSale() {

        return isAfterSale;
    }

    public void setAfterSale(boolean isAfterSale) {

        this.isAfterSale = isAfterSale;
    }

    public boolean isEvaluation() {

        return isEvaluation;
    }

    public void setEvaluation(boolean isEvaluation) {

        this.isEvaluation = isEvaluation;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public Long getOrderItemId() {

        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {

        this.orderItemId = orderItemId;
    }

    
    public boolean isAddEvaluation() {
    
        return isAddEvaluation;
    }

    
    public void setAddEvaluation(boolean isAddEvaluation) {
    
        this.isAddEvaluation = isAddEvaluation;
    }
}
