package com.qcloud.component.orderform;

import java.util.Date;
import java.util.List;

public interface QOrder {

    List<QMerchantOrder> getMerchantOrderList();

    // List<QAfterSaleOrder> getAfterSaleOrderList();
    String getOrderNumber();

    Date getOrderDate();

    String getAddress();

    String getMobile();

    String getConsignee();

    String getInvoice();

    long getUserId();

    long getId();

    int getState();

    int getNeedInvoiceType();

    int getInvoiceType();

    double getTotalPrice();

    double getSum();

    double getTotalPurchase();

    double getCash();

    int getNumber();

    double getPreferential();

    double getCoupon();

    int getPaymentMode();

    int getIntegral();

    double getConsumption();

    double getPostage();

    // String getAfterSaleStateName();
    boolean canApplyAfterSale();

    boolean canEvaluation();

    int getUserState();

    String getUserStateStr();

    Date getLastUpdateTime();
}
