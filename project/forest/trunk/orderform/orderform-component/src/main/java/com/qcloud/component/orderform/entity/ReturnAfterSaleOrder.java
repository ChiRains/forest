package com.qcloud.component.orderform.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.qcloud.component.my.AfterSaleType;
import com.qcloud.component.orderform.QAfterSaleItem;
import com.qcloud.component.orderform.QAfterSaleOrderItem;
import com.qcloud.component.orderform.model.ReturnOrder;

public class ReturnAfterSaleOrder extends AfterSaleOrder {

    private MerchantOrderEntity merchantOrder;

    private ReturnOrder         returnOrder;

    public ReturnAfterSaleOrder(MerchantOrderEntity merchantOrder, ReturnOrder returnOrder) {

        super();
        this.returnOrder = returnOrder;
        this.merchantOrder = merchantOrder;
    }

    public ReturnOrder getReturnOrder() {

        return returnOrder;
    }

    public MerchantOrderEntity getMerchantOrder() {

        return merchantOrder;
    }

    public List<ReturnAfterSaleOrderItem> listReturnItem() {

        List<ReturnAfterSaleOrderItem> list = new ArrayList<ReturnAfterSaleOrderItem>();
        for (AfterSaleOrderItem afterSaleOrderItem : getList()) {
            list.add((ReturnAfterSaleOrderItem) afterSaleOrderItem);
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

        return returnOrder.getId();
    }

    @Override
    public int getState() {

        return returnOrder.getState();
    }

    @Override
    public Date getTime() {

        return returnOrder.getTime();
    }

    @Override
    public long getUserId() {

        return returnOrder.getUserId();
    }

    @Override
    public String getExplain() {

        return returnOrder.getExplain();
    }

    @Override
    public String getReason() {

        return returnOrder.getReason();
    }

    @Override
    public String getAfterSaleOrderNumber() {

        return returnOrder.getReturnNumber();
    }

    @Override
    public double getSum() {

        return returnOrder.getSum();
    }

    @Override
    public AfterSaleType getAfterSaleType() {

        return AfterSaleType.RETURN;
    }

    @Override
    public long getMerchantId() {

        return returnOrder.getMerchantId();
    }

    @Override
    public long getStoreId() {

        return returnOrder.getStoreId();
    }

    @Override
    public void setState(int state) {

        returnOrder.setState(state);
    }

    @Override
    public double getAfterSaleSum() {

        // TODO Auto-generated method stub
        return 0;
    }
}
