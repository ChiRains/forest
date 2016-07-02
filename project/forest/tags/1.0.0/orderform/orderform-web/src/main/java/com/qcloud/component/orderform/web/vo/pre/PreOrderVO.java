package com.qcloud.component.orderform.web.vo.pre;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.orderform.web.vo.OrderCouponVO;
import com.qcloud.component.orderform.web.vo.OrderExpressVO;

public class PreOrderVO extends AbstractPrepareOrderVO {

    private List<PreOrderItemVO> preOrderItemList = new ArrayList<PreOrderItemVO>();

    // 邮费
    private double               postage;

    // 当前选中的
    private OrderExpressVO       express;

    //
    private List<OrderExpressVO> expressList      = new ArrayList<OrderExpressVO>();

    //
    private List<OrderCouponVO>  couponList       = new ArrayList<OrderCouponVO>();

    public List<PreOrderItemVO> getPreOrderItemList() {

        return preOrderItemList;
    }

    public void setPreOrderItemList(List<PreOrderItemVO> preOrderItemList) {

        this.preOrderItemList = preOrderItemList;
    }

    public double getPostage() {

        return postage;
    }

    public void setPostage(double postage) {

        this.postage = postage;
    }

    public OrderExpressVO getExpress() {

        return express;
    }

    public void setExpress(OrderExpressVO express) {

        this.express = express;
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
}
