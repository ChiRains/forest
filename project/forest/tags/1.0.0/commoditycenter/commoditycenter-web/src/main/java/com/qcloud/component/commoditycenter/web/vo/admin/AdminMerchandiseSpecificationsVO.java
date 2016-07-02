package com.qcloud.component.commoditycenter.web.vo.admin;
import java.util.Date;
import java.math.BigDecimal;
public class AdminMerchandiseSpecificationsVO {
    private long   id;
    private long   merchandiseId;
    private long   attributeId0;
    private String attribute0Str;
    // 值
    private String value0;
    private long   attributeId1;
    private String attribute1Str;
    // 值
    private String value1;
    private long   attributeId2;
    private String attribute2Str;
    // 值
    private String value2;
    // 库存
    private int    state;
    
    private double purchase;
    
    private double discount;
    
    private double price;
    
    private int stock;
    

    public AdminMerchandiseSpecificationsVO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(long merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public long getAttributeId0() {
        return attributeId0;
    }

    public void setAttributeId0(long attributeId0) {
        this.attributeId0 = attributeId0;
    }

    public String getAttribute0Str() {
        return attribute0Str;
    }

    public void setAttribute0Str(String attribute0Str) {
        this.attribute0Str = attribute0Str;
    }

    public String getValue0() {
        return value0;
    }

    public void setValue0(String value0) {
        this.value0 = value0;
    }

    public long getAttributeId1() {
        return attributeId1;
    }

    public void setAttributeId1(long attributeId1) {
        this.attributeId1 = attributeId1;
    }

    public String getAttribute1Str() {
        return attribute1Str;
    }

    public void setAttribute1Str(String attribute1Str) {
        this.attribute1Str = attribute1Str;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public long getAttributeId2() {
        return attributeId2;
    }

    public void setAttributeId2(long attributeId2) {
        this.attributeId2 = attributeId2;
    }

    public String getAttribute2Str() {
        return attribute2Str;
    }

    public void setAttribute2Str(String attribute2Str) {
        this.attribute2Str = attribute2Str;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    
    public double getPurchase() {
    
        return purchase;
    }

    
    public void setPurchase(double purchase) {
    
        this.purchase = purchase;
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

    
    public int getStock() {
    
        return stock;
    }

    
    public void setStock(int stock) {
    
        this.stock = stock;
    }
}
