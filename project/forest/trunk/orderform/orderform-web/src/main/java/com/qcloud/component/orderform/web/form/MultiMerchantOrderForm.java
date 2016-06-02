package com.qcloud.component.orderform.web.form;

import java.util.List;

/**
 * 多商家下订单
 * 
 * @author zoro
 *
 */
public class MultiMerchantOrderForm {

    // 收货人信息
    private Long                    consigneeId;

    // 发票ID
    private Long                    invoiceId;

    // 邮费
    private List<OrderExpress>      expressList;

    // 使用优惠劵
    private List<OrderCoupon>       myCouponList;

    //
    private List<Explain>           explainList;

    //
    private List<MerchandiseDetail> merchandiseList;

    public Long getConsigneeId() {

        return consigneeId;
    }

    public void setConsigneeId(Long consigneeId) {

        this.consigneeId = consigneeId;
    }

    public Long getInvoiceId() {

        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {

        this.invoiceId = invoiceId;
    }

    public List<OrderCoupon> getMyCouponList() {

        return myCouponList;
    }

    public void setMyCouponList(List<OrderCoupon> myCouponList) {

        this.myCouponList = myCouponList;
    }

    public List<Explain> getExplainList() {

        return explainList;
    }

    public void setExplainList(List<Explain> explainList) {

        this.explainList = explainList;
    }

    public List<MerchandiseDetail> getMerchandiseList() {

        return merchandiseList;
    }

    public void setMerchandiseList(List<MerchandiseDetail> merchandiseList) {

        this.merchandiseList = merchandiseList;
    }

    public List<OrderExpress> getExpressList() {

        return expressList;
    }

    public void setExpressList(List<OrderExpress> expressList) {

        this.expressList = expressList;
    }
}
