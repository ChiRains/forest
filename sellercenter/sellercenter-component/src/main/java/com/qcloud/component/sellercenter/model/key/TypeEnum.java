package com.qcloud.component.sellercenter.model.key;

import com.qcloud.component.publicdata.IntKeyValue;

public class TypeEnum {

    public static final String MERCHANT_MESSAGE_CODE = "merchant";

    public static final String STORE_MESSAGE_CODE    = "store";
    //
    public enum CommodityAuditingType implements IntKeyValue {
        NEED(1, "需要"), UNNEED(2, "不需要");

        private final int    key;

        private final String value;

        private CommodityAuditingType(int key, String value) {

            this.key = key;
            this.value = value;
        }

        public long getKey() {

            return key;
        }

        @Override
        public String getValue() {

            return value;
        }
    }
    public enum DistributionType implements IntKeyValue {
        YES(1, "是"), NO(2, "否");

        private final int    key;

        private final String value;

        private DistributionType(int key, String value) {

            this.key = key;
            this.value = value;
        }

        public long getKey() {

            return key;
        }

        @Override
        public String getValue() {

            return value;
        }
    }
    public enum MerchantQueryType {
        NEW(1, "最新"), HOT(2, "最热"), RECENTLY(3, "最近"), FAVOURABLE(4, "最受欢迎");

        private final int    key;

        private final String value;

        private MerchantQueryType(int key, String value) {

            this.key = key;
            this.value = value;
        }

        public int getKey() {

            return key;
        }

        public String getValue() {

            return value;
        }
    }
    public enum MerchantOrderStateType {
        //
        TOPAY(1, "待付款"),
        //
        INVALID(2, "已失效"),
        //
        CANCEL_ORDER(3, "已取消"),
        //
        CONFIRM(4, "待确认"),
        //
        SHIP(5, "待发货"),
        //
        SIGN(6, "待签收"),
        //
        SIGNED(7, "已签收"),
        //
        SUCCESS(8, "成功"),
        //
        AFTER_SALE(9, "售后"),
        //
        RETURN(10, "退货"),
        //
        EXCHANGE(11, "换货"),
        //
        REFUND(12, "退款");

        //
        private final int    key;

        //
        private final String name;

        private MerchantOrderStateType(int key, String name) {

            this.key = key;
            this.name = name;
        }

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        public static MerchantOrderStateType get(int key) {

            for (MerchantOrderStateType type : MerchantOrderStateType.values()) {
                if (type.getKey() == key) {
                    return type;
                }
            }
            return null;
        }
    }
    //
    public enum StatusType {
        UNDO(0, "未处理"), UNPASS(1, "未通过"), PASS(2, "已通过");

        private final int    key;

        private final String name;

        private StatusType(int key, String name) {

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
    //
    public enum NotifyType {
        No(0, "不需要短信通知"), Yes(1, "需要短信通知");

        private final int    key;

        private final String name;

        private NotifyType(int key, String name) {

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
    //
    public enum DistributeMembershipCardStateType {
        SEND(1, "待发卡"), SENDED(2, "已发卡");

        private final int    key;

        private final String name;

        private DistributeMembershipCardStateType(int key, String name) {

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
    public enum MerchantIsIncludePost {
        YES(1, "是"), NO(2, "否");

        private final int    key;

        private final String name;

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        private MerchantIsIncludePost(int key, String name) {

            this.key = key;
            this.name = name;
        }
    }
    public enum CertifiedType {
        YES(1, "正品"), NO(2, "非正品");

        private final int    key;

        private final String name;

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        private CertifiedType(int key, String name) {

            this.key = key;
            this.name = name;
        }
    }
    public enum SpecialServiceType {
        SPECIAL(1, "特色服务"), NUSPECIAL(2, "无特色服务");

        private final int    key;

        private final String name;

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        private SpecialServiceType(int key, String name) {

            this.key = key;
            this.name = name;
        }
    }
    public enum NoReasonType {
        NOREASON(1, "7天无理由退货"), REASON(2, "没有7天无理由退货");

        private final int    key;

        private final String name;

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        private NoReasonType(int key, String name) {

            this.key = key;
            this.name = name;
        }
    }
    public enum ExternalUrlType {
        NOREASON(1, "商品有外部链接"), REASON(2, "商品没有外部链接");

        private final int    key;

        private final String name;

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        private ExternalUrlType(int key, String name) {

            this.key = key;
            this.name = name;
        }
    }
    public enum MerchantStatelType {
        NO(1, "否"), YES(2, "是");

        private final int    key;

        private final String name;

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        private MerchantStatelType(int key, String name) {

            this.key = key;
            this.name = name;
        }
    }
    public enum SexpressType {
        Free(1, "包邮"), Fixed(2, "固定收费"), Region(3, "区域收费");

        private final int    key;

        private final String name;

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        private SexpressType(int key, String name) {

            this.key = key;
            this.name = name;
        }
    }
}
