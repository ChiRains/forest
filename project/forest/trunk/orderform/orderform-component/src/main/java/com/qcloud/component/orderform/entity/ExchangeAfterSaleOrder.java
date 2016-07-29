package com.qcloud.component.orderform.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.qcloud.component.my.AfterSaleType;
import com.qcloud.component.orderform.QAfterSaleDetail;
import com.qcloud.component.orderform.QAfterSaleOrderItem;
import com.qcloud.component.orderform.model.ExchangeOrder;

public class ExchangeAfterSaleOrder extends AfterSaleOrder {

    private MerchantOrderEntity merchantOrder;

    private ExchangeOrder       exchangeOrder;

    public ExchangeAfterSaleOrder(MerchantOrderEntity merchantOrder, ExchangeOrder exchangeOrder) {

        super();
        this.exchangeOrder = exchangeOrder;
        this.merchantOrder = merchantOrder;
    }

    public ExchangeOrder getExchangeOrder() {

        return exchangeOrder;
    }

    public List<ExchangeAfterSaleOrderItem> listExchangeItem() {

        List<ExchangeAfterSaleOrderItem> list = new ArrayList<ExchangeAfterSaleOrderItem>();
        for (AfterSaleOrderItem afterSaleOrderItem : getList()) {
            list.add((ExchangeAfterSaleOrderItem) afterSaleOrderItem);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QAfterSaleDetail> listItem() {

        List<QAfterSaleDetail> list = new ArrayList<QAfterSaleDetail>();
        for (AfterSaleOrderItem afterSaleOrderItem : getList()) {
            list.add((QAfterSaleDetail) afterSaleOrderItem);
        }
        return list;
    }

    @Override
    public MerchantOrderEntity getMerchantOrder() {

        return merchantOrder;
    }

    @Override
    public long getAfterSaleId() {

        return exchangeOrder.getId();
    }

    @Override
    public int getState() {

        return exchangeOrder.getState();
    }

    @Override
    public Date getTime() {

        return exchangeOrder.getTime();
    }

    @Override
    public long getUserId() {

        return exchangeOrder.getUserId();
    }

    @Override
    public String getExplain() {

        return exchangeOrder.getExplain();
    }

    @Override
    public String getReason() {

        return exchangeOrder.getReason();
    }

    @Override
    public String getAfterSaleOrderNumber() {

        return exchangeOrder.getExchangeNumber();
    }

    @Override
    public double getSum() {

        return 0.0;
    }

    @Override
    public AfterSaleType getAfterSaleType() {

        return AfterSaleType.EXCHANGE;
    }

    @Override
    public long getMerchantId() {

        return exchangeOrder.getMerchantId();
    }

    @Override
    public long getStoreId() {

        return exchangeOrder.getStoreId();
    }

    @Override
    public void setState(int state) {

        exchangeOrder.setState(state);
    }


    @Override
    public double getAfterSaleSum() {

        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getAfterSaleImage() {

        // TODO Auto-generated method stub
        return "";
    }
}
