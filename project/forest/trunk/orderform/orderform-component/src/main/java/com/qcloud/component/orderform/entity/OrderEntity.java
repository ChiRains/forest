package com.qcloud.component.orderform.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.qcloud.component.my.NeedInvoiceType;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.publicdata.EnableType;

public class OrderEntity implements QOrder {

    private List<MerchantOrderEntity> merchantOrderList;

    // private List<AfterSaleOrder> afterSaleList = new ArrayList<AfterSaleOrder>();
    private CollectOrder              collectOrder;

    private int                       userState;

    private String                    userStateStr;

    private boolean                   canRefund;

    private boolean                   canExchange;

    private boolean                   canReturn;

    public OrderEntity(CollectOrder collectOrder) {

        super();
        this.collectOrder = collectOrder;
    }

    @Override
    public List<QMerchantOrder> getMerchantOrderList() {

        List<QMerchantOrder> list = new ArrayList<QMerchantOrder>();
        for (MerchantOrderEntity merchantOrder : merchantOrderList) {
            list.add(merchantOrder);
        }
        return list;
    }

    public List<MerchantOrderEntity> getEntityList() {

        return merchantOrderList;
    }

    public MerchantOrderEntity getMerchantOrder(Long merchantOrderId) {

        for (MerchantOrderEntity merchantOrder : merchantOrderList) {
            if (merchantOrder.getId() == merchantOrderId) {
                return (MerchantOrderEntity) merchantOrder;
            }
        }
        return null;
    }

    public MerchantOrderEntity getMerchantOrderByMerchant(Long merchantId) {

        for (MerchantOrderEntity merchantOrder : merchantOrderList) {
            if (merchantOrder.getMerchantId() == merchantId) {
                return (MerchantOrderEntity) merchantOrder;
            }
        }
        return null;
    }

    public void setMerchantOrderList(List<MerchantOrderEntity> merchantOrderList) {

        this.merchantOrderList = merchantOrderList;
    }

    // public List<AfterSaleOrder> getAfterSaleList() {
    //
    // return afterSaleList;
    // }
    //
    // public void setAfterSaleList(List<AfterSaleOrder> afterSaleList) {
    //
    // this.afterSaleList = afterSaleList;
    // }
    //
    // @Override
    // public List<QAfterSaleOrder> getAfterSaleOrderList() {
    //
    // List<AfterSaleOrder> list = getAfterSaleList();
    // List<QAfterSaleOrder> afterSaleOrderList = new ArrayList<QAfterSaleOrder>();
    // for (AfterSaleOrder afterSaleOrder : list) {
    // afterSaleOrderList.add(afterSaleOrder);
    // }
    // return afterSaleOrderList;
    // }
    @Override
    public String getOrderNumber() {

        return collectOrder.getOrderNumber();
    }

    @Override
    public Date getOrderDate() {

        return collectOrder.getTime();
    }

    @Override
    public double getSum() {

        return collectOrder.getSum();
    }

    @Override
    public String getAddress() {

        return collectOrder.getAddress();
    }

    @Override
    public String getMobile() {

        return collectOrder.getMobile();
    }

    @Override
    public String getConsignee() {

        return collectOrder.getConsignee();
    }

    @Override
    public long getUserId() {

        return collectOrder.getUserId();
    }

    @Override
    public long getId() {

        return collectOrder.getId();
    }

    @Override
    public int getState() {

        return collectOrder.getState();
    }

    public void setState(int state) {

        collectOrder.setState(state);
    }

    @Override
    public int getNeedInvoiceType() {

        return collectOrder.getNeedInvoice();
    }

    @Override
    public int getInvoiceType() {

        return collectOrder.getInvoiceType();
    }

    @Override
    public double getCash() {

        return collectOrder.getCash();
    }

    @Override
    public double getCoupon() {

        return collectOrder.getCoupon();
    }

    public CollectOrder getCollectOrder() {

        return collectOrder;
    }

    public boolean canRefund() {

        // 付款,确认可以申请退款
        return canApplyAfterSale() && canRefund;
    }

    public boolean canReturn() {

        // 签收才可以退换货
        return canApplyAfterSale() && canReturn;
    }

    public boolean canExchange() {

        // 签收才可以退换货
        return canApplyAfterSale() && canExchange;
    }

    @Override
    public double getTotalPrice() {

        double totalPrice = 0.0;
        for (MerchantOrderEntity merchantOrderEntity : merchantOrderList) {
            totalPrice += merchantOrderEntity.getTotalPrice();
        }
        return totalPrice;
    }

    @Override
    public double getTotalPurchase() {

        double totalPurchase = 0.0;
        for (MerchantOrderEntity merchantOrderEntity : merchantOrderList) {
            totalPurchase += merchantOrderEntity.getTotalPurchase();
        }
        return totalPurchase;
    }

    @Override
    public int getNumber() {

        int totalNumber = 0;
        for (MerchantOrderEntity merchantOrderEntity : merchantOrderList) {
            totalNumber += merchantOrderEntity.getNumber();
        }
        return totalNumber;
    }

    @Override
    public double getPreferential() {

        return collectOrder.getPreferential();
    }

    public int getIntegral() {

        return collectOrder.getIntegral();
    }

    public double getConsumption() {

        return collectOrder.getConsumption();
    }

    @Override
    public double getPostage() {

        return collectOrder.getPostage();
    }

    // @Override
    // public String getAfterSaleStateName() {
    //
    // List<QAfterSaleOrder> list = getAfterSaleOrderList();
    // if (CollectionUtils.isEmpty(list)) {
    // return "";
    // }
    // boolean finish = true;
    // for (QAfterSaleOrder qAfterSaleOrder : list) {
    // if (!qAfterSaleOrder.isFinish()) {
    // finish = false;
    // break;
    // }
    // }
    // if (finish) {
    // return "售后完成";
    // } else {
    // return "售后中";
    // }
    // }
    @Override
    public boolean canApplyAfterSale() {

        return collectOrder.getAfterSale() == EnableType.ENABLE.getKey();
    }

    @Override
    public boolean canEvaluation() {

        return collectOrder.getEvaluation() == EnableType.ENABLE.getKey();
    }

    @Override
    public String getInvoice() {

        if (NeedInvoiceType.NO.getKey() == collectOrder.getNeedInvoice()) {
            return "不开发票";
        } else {
            return StringUtils.isEmpty(collectOrder.getInvoiceHead()) ? "个人" : collectOrder.getInvoiceHead();
        }
    }

    @Override
    public int getPaymentMode() {

        return collectOrder.getPaymentMode();
    }

    public int getUserState() {

        return userState;
    }

    public void setUserState(int userState) {

        this.userState = userState;
    }

    public String getUserStateStr() {

        return userStateStr;
    }

    public void setUserStateStr(String userStateStr) {

        this.userStateStr = userStateStr;
    }

    public void setCanRefund(boolean canRefund) {

        this.canRefund = canRefund;
    }

    public void setCanExchange(boolean canExchange) {

        this.canExchange = canExchange;
    }

    public void setCanReturn(boolean canReturn) {

        this.canReturn = canReturn;
    }

    @Override
    public Date getLastUpdateTime() {

        return collectOrder.getLastUpdateTime();
    }
}
