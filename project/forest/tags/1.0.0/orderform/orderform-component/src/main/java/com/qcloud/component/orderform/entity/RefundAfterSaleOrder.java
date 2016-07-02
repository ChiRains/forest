package com.qcloud.component.orderform.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.qcloud.component.my.AfterSaleType;
import com.qcloud.component.orderform.QAfterSaleItem;
import com.qcloud.component.orderform.model.RefundOrder;

public class RefundAfterSaleOrder extends AfterSaleOrder {

    private MerchantOrderEntity merchantOrder;

    private RefundOrder         refundOrder;

    public RefundAfterSaleOrder(MerchantOrderEntity merchantOrder, RefundOrder refundOrder) {

        super();
        this.refundOrder = refundOrder;
        this.merchantOrder = merchantOrder;
    }

    public RefundOrder getRefundOrder() {

        return refundOrder;
    }

    public MerchantOrderEntity getMerchantOrder() {

        return merchantOrder;
    }

    public List<RefundAfterSaleOrderItem> listRefundItem() {

        List<RefundAfterSaleOrderItem> list = new ArrayList<RefundAfterSaleOrderItem>();
        for (AfterSaleOrderItem afterSaleOrderItem : getList()) {
            list.add((RefundAfterSaleOrderItem) afterSaleOrderItem);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QAfterSaleItem> listItem() {

        List<QAfterSaleItem> list = new ArrayList<QAfterSaleItem>();
        for (AfterSaleOrderItem afterSaleOrderItem : getList()) {
            list.add((QAfterSaleItem) afterSaleOrderItem);
        }
        return list;
    }

    @Override
    public long getAfterSaleId() {

        return refundOrder.getId();
    }

    @Override
    public int getState() {

        return refundOrder.getState();
    }

    @Override
    public Date getTime() {

        return refundOrder.getTime();
    }

    @Override
    public long getUserId() {

        return refundOrder.getUserId();
    }

    @Override
    public String getExplain() {

        return refundOrder.getExplain();
    }

    @Override
    public String getReason() {

        return refundOrder.getReason();
    }

    @Override
    public String getAfterSaleOrderNumber() {

        return refundOrder.getRefundNumber();
    }

    @Override
    public double getSum() {

        return refundOrder.getSum();
    }

    @Override
    public AfterSaleType getAfterSaleType() {

        return AfterSaleType.REFUND;
    }

    @Override
    public long getMerchantId() {

        return refundOrder.getMerchantId();
    }

    @Override
    public long getStoreId() {

        return refundOrder.getStoreId();
    }

    @Override
    public void setState(int state) {

        refundOrder.setState(state);
    }
}
