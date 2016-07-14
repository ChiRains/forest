package com.qcloud.component.orderform.web.vo.pre;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.orderform.web.vo.OrderCouponVO;
import com.qcloud.component.orderform.web.vo.OrderExpressVO;

public class PreMerchantVO {

    private long                        merchantId;

    private String                      merchantName;

    private String                      image;

    private int                         number;

    private double                      sum;

    private double                      cash;

    // 邮费
    private double                      postage;

    // 当前选中的
    private OrderExpressVO              express;

    private List<PreOrderCombinationVO> combinationItemList = new ArrayList<PreOrderCombinationVO>();

    //
    private List<PreOrderItemVO>        preOrderItemList    = new ArrayList<PreOrderItemVO>();

    //
    private List<OrderExpressVO>        expressList         = new ArrayList<OrderExpressVO>();

    //
    private List<OrderCouponVO>         couponList          = new ArrayList<OrderCouponVO>();

    //
    private List<String>                imageList           = new ArrayList<String>();

    public long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(long merchantId) {

        this.merchantId = merchantId;
    }

    public String getMerchantName() {

        return merchantName;
    }

    public void setMerchantName(String merchantName) {

        this.merchantName = merchantName;
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

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public double getSum() {

        return sum;
    }

    public void setSum(double sum) {

        this.sum = sum;
    }

    public double getPostage() {

        return postage;
    }

    public void setPostage(double postage) {

        this.postage = postage;
    }

    public double getCash() {

        return cash;
    }

    public void setCash(double cash) {

        this.cash = cash;
    }

    public List<OrderExpressVO> getExpressList() {

        return expressList;
    }

    public void setExpressList(List<OrderExpressVO> expressList) {

        this.expressList = expressList;
    }

    public List<OrderCouponVO> getCouponList() {

        return couponList;
    }

    public void setCouponList(List<OrderCouponVO> couponList) {

        this.couponList = couponList;
    }

    public OrderExpressVO getExpress() {

        return express;
    }

    public void setExpress(OrderExpressVO express) {

        this.express = express;
    }

    public List<PreOrderCombinationVO> getCombinationItemList() {

        return combinationItemList;
    }

    public void setCombinationItemList(List<PreOrderCombinationVO> combinationItemList) {

        this.combinationItemList = combinationItemList;
    }

    public List<String> getImageList() {

        return imageList;
    }

    public void setImageList(List<String> imageList) {

        this.imageList = imageList;
    }
}
