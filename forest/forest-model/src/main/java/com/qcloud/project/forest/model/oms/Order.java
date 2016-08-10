package com.qcloud.project.forest.model.oms;

import java.util.List;

public class Order {

    // 订单id
    private String        tid;

    // 订单状态
    private String        status;

    // 订单最后修改时间(北京时间)
    private String        modified;

    // 订单创建时间(北京时间)
    private String        created;

    // 快递方式
    private String        shipping_type;

    // 运费
    private double        post_fee;

    // 收货人姓名
    private String        receiver_name;

    // 收货省份
    private String        receiver_state;

    // 收货市
    private String        receiver_city;

    // 收货地区
    private String        receiver_district;

    // 收货人地址
    private String        receiver_address;

    // 邮编
    private String        receiver_zip;

    // 收货人手机
    private String        receiver_mobile;

    // 收货人电话
    private String        receiver_phone;

    // 会员编码
    private String        buyer_nick;

    // 会员名称
    private String        buyer_name;

    // 是否开票
    private String        is_tax;

    // 开票类型
    private String        invoice_type;

    // 开票抬头
    private String        invoice_title;

    // 支付方式
    private String        pay_type;

    // 实际支付金额
    private double        real_pay;

    // 商品总金额
    private double        total_fee;

    // 用户备注
    private String        buyer_message;

    // 买家货到付款服务费
    private double        buyer_cod_fee;

    // 积分支付
    private double        point_fee;

    // 优惠券支付
    private double        coupon_pay;

    // 付款时间
    private String        paytime;

    // 卖家备注
    private String        seller_memo;

    // 商品列表
    private List<Item>    itemlist;

    // 支付方式
    private List<Payment> payments;

    public String getTid() {

        return tid;
    }

    public void setTid(String tid) {

        this.tid = tid;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public String getModified() {

        return modified;
    }

    public void setModified(String modified) {

        this.modified = modified;
    }

    public String getCreated() {

        return created;
    }

    public void setCreated(String created) {

        this.created = created;
    }

    public String getShipping_type() {

        return shipping_type;
    }

    public void setShipping_type(String shipping_type) {

        this.shipping_type = shipping_type;
    }

    public String getReceiver_name() {

        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {

        this.receiver_name = receiver_name;
    }

    public String getReceiver_state() {

        return receiver_state;
    }

    public void setReceiver_state(String receiver_state) {

        this.receiver_state = receiver_state;
    }

    public String getReceiver_city() {

        return receiver_city;
    }

    public void setReceiver_city(String receiver_city) {

        this.receiver_city = receiver_city;
    }

    public String getReceiver_district() {

        return receiver_district;
    }

    public void setReceiver_district(String receiver_district) {

        this.receiver_district = receiver_district;
    }

    public String getReceiver_address() {

        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {

        this.receiver_address = receiver_address;
    }

    public String getReceiver_zip() {

        return receiver_zip;
    }

    public void setReceiver_zip(String receiver_zip) {

        this.receiver_zip = receiver_zip;
    }

    public String getReceiver_mobile() {

        return receiver_mobile;
    }

    public void setReceiver_mobile(String receiver_mobile) {

        this.receiver_mobile = receiver_mobile;
    }

    public String getReceiver_phone() {

        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {

        this.receiver_phone = receiver_phone;
    }

    public String getBuyer_nick() {

        return buyer_nick;
    }

    public void setBuyer_nick(String buyer_nick) {

        this.buyer_nick = buyer_nick;
    }

    public String getBuyer_name() {

        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {

        this.buyer_name = buyer_name;
    }

    public String getIs_tax() {

        return is_tax;
    }

    public void setIs_tax(String is_tax) {

        this.is_tax = is_tax;
    }

    public String getInvoice_type() {

        return invoice_type;
    }

    public void setInvoice_type(String invoice_type) {

        this.invoice_type = invoice_type;
    }

    public String getInvoice_title() {

        return invoice_title;
    }

    public void setInvoice_title(String invoice_title) {

        this.invoice_title = invoice_title;
    }

    public String getPay_type() {

        return pay_type;
    }

    public void setPay_type(String pay_type) {

        this.pay_type = pay_type;
    }

    public String getBuyer_message() {

        return buyer_message;
    }

    public void setBuyer_message(String buyer_message) {

        this.buyer_message = buyer_message;
    }

    public String getPaytime() {

        return paytime;
    }

    public void setPaytime(String paytime) {

        this.paytime = paytime;
    }

    public String getSeller_memo() {

        return seller_memo;
    }

    public void setSeller_memo(String seller_memo) {

        this.seller_memo = seller_memo;
    }

    public List<Item> getItemlist() {

        return itemlist;
    }

    public void setItemlist(List<Item> itemlist) {

        this.itemlist = itemlist;
    }

    public double getPost_fee() {

        return post_fee;
    }

    public void setPost_fee(double post_fee) {

        this.post_fee = post_fee;
    }

    public double getReal_pay() {

        return real_pay;
    }

    public void setReal_pay(double real_pay) {

        this.real_pay = real_pay;
    }

    public double getTotal_fee() {

        return total_fee;
    }

    public void setTotal_fee(double total_fee) {

        this.total_fee = total_fee;
    }

    public double getPoint_fee() {

        return point_fee;
    }

    public void setPoint_fee(double point_fee) {

        this.point_fee = point_fee;
    }

    public double getBuyer_cod_fee() {

        return buyer_cod_fee;
    }

    public void setBuyer_cod_fee(double buyer_cod_fee) {

        this.buyer_cod_fee = buyer_cod_fee;
    }

    public double getCoupon_pay() {

        return coupon_pay;
    }

    public void setCoupon_pay(double coupon_pay) {

        this.coupon_pay = coupon_pay;
    }

    public List<Payment> getPayments() {

        return payments;
    }

    public void setPayments(List<Payment> payments) {

        this.payments = payments;
    }
}
