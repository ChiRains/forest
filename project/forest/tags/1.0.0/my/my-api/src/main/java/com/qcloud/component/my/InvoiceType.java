package com.qcloud.component.my;

// 发票类型
public enum InvoiceType {
    //
    COMMON(1, "普通发票"),
    //
    VALUEADDEDTAX(2, "增值税");

    //
    private final int    key;

    //
    private final String name;

    private InvoiceType(int key, String name) {

        this.key = key;
        this.name = name;
    }

    public int getKey() {

        return key;
    }

    public String getName() {

        return name;
    }

    public static InvoiceType get(int key) {

        for (InvoiceType invoiceType : InvoiceType.values()) {
            if (invoiceType.getKey() == key) {
                return invoiceType;
            }
        }
        return COMMON;
    }
}