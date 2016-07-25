package com.qcloud.project.forest.model.key;

import com.qcloud.pirates.exception.PiratesRuntimeException;

public class TypeEnum {

    public enum EnableType {
        ENABLE(1, "启用"), DISABLE(0, "禁用");

        private final int    key;

        private final String name;

        private EnableType(int key, String name) {

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
    public enum StateType {
        UNTREATED(0, "未审核"), TREATED(1, "已处理"), NOTPASS(2, "未通过");

        private final int    key;

        private final String name;

        private StateType(int key, String name) {

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
    public enum AnalysisresultType {
        BLOOB(1, "血压分析"), BIM(2, "BIM计算");

        private final int    key;

        private final String name;

        private AnalysisresultType(int key, String name) {

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
    public enum state {
        UNTREATED(0, "未处理"), TREATED(1, "已处理"), NOTPASS(2, "未通过");

        private final int    key;

        private final String name;

        private state(int key, String name) {

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
    public enum PeriodType {
        DAY(1, "每日"), WEEK(2, "每周"), MONTH(3, "每月");

        private final int    key;

        private final String name;

        private PeriodType(int key, String name) {

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
    public enum MessageType {
        USER("user", "用户信息"), STATION("station", "站内消息"), LOGISTICS("logistics", "物流消息"), COUPONS("coupons", "优惠券消息"), PAYMENT("payment", "付款提醒"), MEDICATIONREMINDERS("medicationReminders", "用药提醒"), MERCHANT("merchant", "商家信息");

        private final String key;

        private final String name;

        private MessageType(String key, String name) {

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
    public enum MessageClassify {
        MERCHANTPRIVATEMESSAGE(1, "商家站内消息"), USERPRIVATEMESSAGE(1, "用户站内消息"), LOGISTICS(2, "物流消息"), COUPONS(3, "优惠券消息"), PAYMENT(4, "付款提醒"), MEDICATIONREMINDERS(5, "药物提醒"), ;

        private final int    key;

        private final String name;

        private MessageClassify(int key, String name) {

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
    public enum ClassifyType {
        ARTICLE(60, "资讯分类"), SALESPROMOTION(70, "活动商品"), FEEDBACK(80, "反馈类型"),
        //
        PROMOTIONALOFFERS(90, "促销优惠"), BRANDONSALEBRAND(110, "品牌特卖品牌"),
        //
        BRANDONSALECLASSIFY(120, "品牌特卖品牌类别"), HOMEPAGESALECLASSIFY(130, "主页商品特卖类别"),
        //
        IntegralMerchandiseRange(300, "积分商品区间");

        private final int    key;

        private final String name;

        private ClassifyType(int key, String name) {

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
    public enum GifeCouponStateType {
        Can_Use(1, "未使用"), Used(2, "已使用");

        private final int    key;

        private final String name;

        private GifeCouponStateType(int key, String name) {

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
    public enum ForestOrderState {
        Normal_To_Pay(10, "待付款"), Normal_To_Ship(20, "待配货"), Normal_To_Send(30, "待发货"), Normal_To_Receive(40, "待签收"), Normal_Trade_Success(50, "已完成"), Cancel(60, "已关闭"), After_Sale(70, "退款中"), Closed(80, "已取消");

        private final int    key;

        private final String name;

        private ForestOrderState(int key, String name) {

            this.key = key;
            this.name = name;
        }

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        public static String get(int key) {

            ForestOrderState orderState[] = ForestOrderState.values();
            for (ForestOrderState type : orderState) {
                if (type.getKey() == key) {
                    return type.getName();
                }
            }
            return "";
        }
    }
    public enum ConfigCodeType {
        GIFTCOUPONUSERULE("giftCouponUseRule", "赠品券使用规则");

        private final String key;

        private final String name;

        private ConfigCodeType(String key, String name) {

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
    public enum ConfigType {
        GIFTCOUPONUSERULE(1, "赠品券使用规则");

        private final int    key;

        private final String name;

        private ConfigType(int key, String name) {

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
    public enum IntegralOrderStateType {
        TO_PAY(10, "待付款"), TO_SHIP(20, "待发货"), TO_SIGN(30, "待签收"), FINISHED(40, "已完成");

        private final int    key;

        private final String name;

        private IntegralOrderStateType(int key, String name) {

            this.key = key;
            this.name = name;
        }

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        public static String get(int key) {

            IntegralOrderStateType[] typeList = IntegralOrderStateType.values();
            for (IntegralOrderStateType type : typeList) {
                if (type.getKey() == key) {
                    return type.getName();
                }
            }
            throw new PiratesRuntimeException("积分订单状态不存在.");
        }
    }
}
