package com.qcloud.project.forest.model.oms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesOrderOfflineEntity {

    // 订单交易号
    public String                        tradeId;

    // 联系人
    public String                        consignee;

    // 店铺编码
    public String                        shopCode;

    // 店铺名称
    public String                        shopName;

    // 付款时间
    public Date                          payDate;

    // 买家邮箱
    public String                        buyer_email;

    // 买家昵称
    public String                        buyer_nick;

    // 支付总金额
    public double                        payAmount;

    // 会员代码
    public String                        memberCode;

    // 会员名称
    public String                        memberName;

    // 数量
    public double                        quantity;

    // 收货人手机号
    public String                        consigneeMobile;

    // 收货人电话
    public String                        consigneeTelephone;

    // 收货人地址
    public String                        consigneeAddress;

    // 收货人省
    public String                        consigneeProvince;

    // 收货人市
    public String                        consigneeCity;

    // 收货人区
    public String                        consigneeCounty;

    // 商品详细
    public List<SalesOrderLineEntity>    productDetails = new ArrayList<SalesOrderLineEntity>();

    // 支付明细(非必填)
    public List<SalesOrderPayEntity>     payDetais      = new ArrayList<SalesOrderPayEntity>();

    // 发票明细(非必填)
    public List<SalesOrderInvoiceEntity> invoiceDetails = new ArrayList<SalesOrderInvoiceEntity>();

    // 是否需要发票
    public boolean                       needInvoice;

    // 买家备注(非必填)
    public String                        buyerMemo;

    public String getTradeId() {

        return tradeId;
    }

    public void setTradeId(String tradeId) {

        this.tradeId = tradeId;
    }

    public String getConsignee() {

        return consignee;
    }

    public void setConsignee(String consignee) {

        this.consignee = consignee;
    }

    public String getShopCode() {

        return shopCode;
    }

    public void setShopCode(String shopCode) {

        this.shopCode = shopCode;
    }

    public String getShopName() {

        return shopName;
    }

    public void setShopName(String shopName) {

        this.shopName = shopName;
    }

    public Date getPayDate() {

        return payDate;
    }

    public void setPayDate(Date payDate) {

        this.payDate = payDate;
    }

    public String getBuyer_email() {

        return buyer_email;
    }

    public void setBuyer_email(String buyer_email) {

        this.buyer_email = buyer_email;
    }

    public String getBuyer_nick() {

        return buyer_nick;
    }

    public void setBuyer_nick(String buyer_nick) {

        this.buyer_nick = buyer_nick;
    }

    public double getPayAmount() {

        return payAmount;
    }

    public void setPayAmount(double payAmount) {

        this.payAmount = payAmount;
    }

    public String getMemberCode() {

        return memberCode;
    }

    public void setMemberCode(String memberCode) {

        this.memberCode = memberCode;
    }

    public String getMemberName() {

        return memberName;
    }

    public void setMemberName(String memberName) {

        this.memberName = memberName;
    }

    public double getQuantity() {

        return quantity;
    }

    public void setQuantity(double quantity) {

        this.quantity = quantity;
    }

    public String getConsigneeMobile() {

        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {

        this.consigneeMobile = consigneeMobile;
    }

    public String getConsigneeTelephone() {

        return consigneeTelephone;
    }

    public void setConsigneeTelephone(String consigneeTelephone) {

        this.consigneeTelephone = consigneeTelephone;
    }

    public String getConsigneeAddress() {

        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {

        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeCity() {

        return consigneeCity;
    }

    public void setConsigneeCity(String consigneeCity) {

        this.consigneeCity = consigneeCity;
    }

    public String getConsigneeCounty() {

        return consigneeCounty;
    }

    public void setConsigneeCounty(String consigneeCounty) {

        this.consigneeCounty = consigneeCounty;
    }

    public List<SalesOrderLineEntity> getProductDetails() {

        return productDetails;
    }

    public void setProductDetails(List<SalesOrderLineEntity> productDetails) {

        this.productDetails = productDetails;
    }

    public List<SalesOrderPayEntity> getPayDetais() {

        return payDetais;
    }

    public void setPayDetais(List<SalesOrderPayEntity> payDetais) {

        this.payDetais = payDetais;
    }

    public List<SalesOrderInvoiceEntity> getInvoiceDetails() {

        return invoiceDetails;
    }

    public void setInvoiceDetails(List<SalesOrderInvoiceEntity> invoiceDetails) {

        this.invoiceDetails = invoiceDetails;
    }

    public boolean isNeedInvoice() {

        return needInvoice;
    }

    public void setNeedInvoice(boolean needInvoice) {

        this.needInvoice = needInvoice;
    }

    public String getBuyerMemo() {

        return buyerMemo;
    }

    public void setBuyerMemo(String buyerMemo) {

        this.buyerMemo = buyerMemo;
    }

    public String getConsigneeProvince() {

        return consigneeProvince;
    }

    public void setConsigneeProvince(String consigneeProvince) {

        this.consigneeProvince = consigneeProvince;
    }
}
