package com.qcloud.component.orderform.model.key;

public class TypeEnum {

    public enum DiscountType {
        //
        COUPON(1, "优惠劵");

        //
        private final int    key;

        //
        private final String name;

        private DiscountType(int key, String name) {

            this.key = key;
            this.name = name;
        }

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }
    }
    public enum RecordStateTimeType {// 1总单 2子单 3订单项 4订单明细 5退货单 6退货明细 7换货单 8换货明细
        //
        COLLECT(1, "总单"),
        //
        SUB(2, "子单"),
        //
        ITEM(3, "订单项"),
        //
        DETAIL(4, "订单明细"),
        //
        REFUND(5, "退款单"),
        //
        RETURN(6, "退货单"),
        //
        EXCAHANGE(7, "换货单");

        //
        private final int    key;

        //
        private final String name;

        private RecordStateTimeType(int key, String name) {

            this.key = key;
            this.name = name;
        }

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }
    }
    // // 送货方式
    // public enum DeliveryModeType {
    // //
    // LOGISTICS(1, "物流"),
    // //
    // PICKUP(2, "门店自提"),
    // //
    // DELIVERY(3, "送货上门");
    // //
    // private final int key;
    //
    // //
    // private final String name;
    //
    // private DeliveryModeType(int key, String name) {
    //
    // this.key = key;
    // this.name = name;
    // }
    //
    // public int getKey() {
    //
    // return key;
    // }
    //
    // public String getName() {
    //
    // return name;
    // }
    //
    // public static DeliveryModeType get(int key) {
    //
    // for (DeliveryModeType deliveryModeType : DeliveryModeType.values()) {
    // if (deliveryModeType.getKey() == key) {
    // return deliveryModeType;
    // }
    // }
    // return LOGISTICS;
    // }
    // }
    // // 需要发票
    // public enum NeedInvoiceType {
    // //
    // YES(1, "需要"),
    // //
    // NO(2, "不需要");
    //
    // //
    // private final int key;
    //
    // //
    // private final String name;
    //
    // private NeedInvoiceType(int key, String name) {
    //
    // this.key = key;
    // this.name = name;
    // }
    //
    // public int getKey() {
    //
    // return key;
    // }
    //
    // public String getName() {
    //
    // return name;
    // }
    //
    // public static NeedInvoiceType get(int key) {
    //
    // for (NeedInvoiceType needInvoiceType : NeedInvoiceType.values()) {
    // if (needInvoiceType.getKey() == key) {
    // return needInvoiceType;
    // }
    // }
    // return NO;
    // }
    // }
    // // 发票类型
    // public enum InvoiceType {
    // //
    // COMMON(1, "普通发票"),
    // //
    // VALUEADDEDTAX(2, "增值税");
    //
    // //
    // private final int key;
    //
    // //
    // private final String name;
    //
    // private InvoiceType(int key, String name) {
    //
    // this.key = key;
    // this.name = name;
    // }
    //
    // public int getKey() {
    //
    // return key;
    // }
    //
    // public String getName() {
    //
    // return name;
    // }
    //
    // public static InvoiceType get(int key) {
    //
    // for (InvoiceType invoiceType : InvoiceType.values()) {
    // if (invoiceType.getKey() == key) {
    // return invoiceType;
    // }
    // }
    // return COMMON;
    // }
    // }
}
