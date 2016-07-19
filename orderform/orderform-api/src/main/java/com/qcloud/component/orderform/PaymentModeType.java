package com.qcloud.component.orderform;

public enum PaymentModeType {
    //
    // ONLINE_ALIPAY(101, "支付宝"),
    // //
    // ONLINE_TENPAY(102, "财富通"),
    // //
    // ONLINE_MICROCHANNELPAY(103, "微信支付"),
    // //
    // ONLINE_BANKINGPAY(104, "网银支付"),
    // //
    // OFFLINE_COD(105, "货到付款"),
    // //
    // commission(106, "系统货币"),
    // // integral
    // INTEGRAL(108, "积分"),
    // // consumptionCurrency
    // CONSUMPTION_CURRENCY(109, "兑兑券");
    LIMBS_PAY(101, "翼支付"), WEIXIN_PAY(102, "微信支付"), ONLINE_ALIPAY(103, "支付宝"), UNION_PAY(104, "银联支付");

    //
    private final int    key;

    //
    private final String name;

    private PaymentModeType(int key, String name) {

        this.key = key;
        this.name = name;
    }

    public int getKey() {

        return key;
    }

    public String getName() {

        return name;
    }

    public static PaymentModeType get(int key) {

        PaymentModeType[] types = PaymentModeType.values();
        for (PaymentModeType type : types) {
            if (type.getKey() == key) {
                return type;
            }
        }
        return PaymentModeType.ONLINE_ALIPAY;
    }
}
