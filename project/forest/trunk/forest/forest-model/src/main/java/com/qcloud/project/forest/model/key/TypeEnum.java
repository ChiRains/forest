package com.qcloud.project.forest.model.key;

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
        STATION("station", "站内消息"), LOGISTICS("logistics", "物流消息"), COUPONS("coupons", "优惠券消息"), PAYMENT("payment", "付款提醒"), MEDICATION("medication", "用药提醒");

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
    public enum ClassifyType {
        ARTICLE(60, "资讯分类");

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
        Wait_Receive(1, "待接单"), Wait_Sort(2, "待分拣"), Wait_Send(3, "待配送"), Wait_Sign(4, "待签收"), /* Wait_User_Sure(5, "待用户确认"), */Finished(5, "已完成"), Cancel(6, "取消");

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

        public static String getValue(int key) {

            ForestOrderState orderState[] = ForestOrderState.values();
            for (ForestOrderState type : orderState) {
                if (type.getKey() == key) {
                    return type.getName();
                }
            }
            return "";
        }
    }
}
