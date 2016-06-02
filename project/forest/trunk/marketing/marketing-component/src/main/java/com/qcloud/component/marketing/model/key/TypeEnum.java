package com.qcloud.component.marketing.model.key;

public class TypeEnum {

    public enum Enable {
        ENABLE(1, "启用"), DISABLE(2, "禁用");

        private final int    key;

        private final String name;

        private Enable(int key, String name) {

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
    public enum SenceType {
        LIMIT(1, "限时折扣"), FULL(2, "满减送"), DOUBLE(3, "双倍积分"), SECKILL(4, "秒杀"), SPECIAL(5, "特价"), POINTEXCHANGE(6, "积分兑换"), CURRENCYEXCHANGE(7, "消费币兑换"),GROUPBUYS(8,"团购");

        private final int    key;

        private final String name;

        private SenceType(int key, String name) {

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
    public enum CurrencyType {
        RMB(1, "人民币"), POINT(2, "积分"), CURRENCY(3, "消费币");

        private final int    key;

        private final String name;

        private CurrencyType(int key, String name) {

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
    public enum OnOrOffLine {
        ONLINE(1, "线上"), OFFLINE(2, "线下"), BOTH(3, "线上+线下");

        private final int    key;

        private final String name;

        private OnOrOffLine(int key, String name) {

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
    
    public enum PlatformCoupon {
        PLATFORM(-1, "线上");

        private final int    key;

        private final String name;

        private PlatformCoupon(int key, String name) {

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
}
