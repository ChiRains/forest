package com.qcloud.component.orderform.web.form;

import java.util.List;
import com.qcloud.component.piratesship.web.form.ListForm;

public class OrderForm {

    private String                  explain;

    // 收货人信息
    private Long                    consigneeId;

    // 发票ID
    private Long                    invoiceId;

    // 配送
    private Long                    deliveryId;

    // 物流
    private String                  expressCode;

    // 我的优惠劵
    private ListForm                myCouponList;

    private List<MerchandiseDetail> merchandiseList;

    public List<MerchandiseDetail> getMerchandiseList() {

        return merchandiseList;
    }

    public void setMerchandiseList(List<MerchandiseDetail> merchandiseList) {

        this.merchandiseList = merchandiseList;
    }

    public Long getConsigneeId() {

        return consigneeId;
    }

    public void setConsigneeId(Long consigneeId) {

        this.consigneeId = consigneeId;
    }

    public String getExplain() {

        return explain;
    }

    public void setExplain(String explain) {

        this.explain = explain;
    }

    public Long getInvoiceId() {

        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {

        this.invoiceId = invoiceId;
    }

    public Long getDeliveryId() {

        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {

        this.deliveryId = deliveryId;
    }

    public String getExpressCode() {

        return expressCode;
    }

    public void setExpressCode(String expressCode) {

        this.expressCode = expressCode;
    }

    public ListForm getMyCouponList() {

        return myCouponList;
    }

    public void setMyCouponList(ListForm myCouponList) {

        this.myCouponList = myCouponList;
    }
}
