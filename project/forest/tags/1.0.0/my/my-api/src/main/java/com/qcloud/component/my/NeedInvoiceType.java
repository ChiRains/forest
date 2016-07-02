package com.qcloud.component.my;


// 需要发票
public enum NeedInvoiceType {
    //
    YES(1, "需要"),
    //
    NO(2, "不需要");

    //
    private final int    key;

    //
    private final String name;

    private NeedInvoiceType(int key, String name) {

        this.key = key;
        this.name = name;
    }

    public int getKey() {

        return key;
    }

    public String getName() {

        return name;
    }

    public static NeedInvoiceType get(int key) {

        for (NeedInvoiceType needInvoiceType : NeedInvoiceType.values()) {
            if (needInvoiceType.getKey() == key) {
                return needInvoiceType;
            }
        }
        return NO;
    }
}
