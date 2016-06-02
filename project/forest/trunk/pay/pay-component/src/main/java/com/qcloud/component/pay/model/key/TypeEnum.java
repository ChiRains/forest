package com.qcloud.component.pay.model.key;

public class TypeEnum {

    public enum PayMethodType {
        //
        WEIXIN("weixinpay", "微信支付"), ALIPAY("alipay", "支付宝"), UNIONPAY("unionpay", "银联"), BEST("bestpay", "翼支付");

        private final String key;

        private final String name;

        private PayMethodType(String key, String name) {

            this.key = key;
            this.name = name;
        }

        public String getKey() {

            return key;
        }

        public String getName() {

            return name;
        }
    }
    public enum PayClientType {
        //
        WEIXIN("weixin", "微信"), WEB("web", "WEB"), APP("app", "APP");

        private final String key;

        private final String name;

        private PayClientType(String key, String name) {

            this.key = key;
            this.name = name;
        }

        public String getKey() {

            return key;
        }

        public String getName() {

            return name;
        }
    }
}
