package com.qcloud.component.my.model.key;

public class TypeEnum {

    public static final String USER_ACCOUNT_CODE = "user";

    public static final String USER_MESSAGE_CODE = "user";
    //
    public enum ConsigneeType {
        DEFAULT(1, "默认"), NOTDEFAULT(2, "非默认");

        private final int    key;

        private final String name;

        private ConsigneeType(int key, String name) {

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
    public enum CollectionType {
        //
        MERCHANDISE(1, "收藏商品"),
        //
        MERCHANT(2, "收藏商家"),
        //
        STORE(3, "收藏店铺");

        //
        private final int    key;

        //
        private final String name;

        private CollectionType(int key, String name) {

            this.key = key;
            this.name = name;
        }

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        public static CollectionType get(int key) {

            for (CollectionType collectionType : CollectionType.values()) {
                if (collectionType.getKey() == key) {
                    return collectionType;
                }
            }
            return MERCHANDISE;
        }
    }
    public enum WithdrawCashStateType {
        //
        APPLY(1, "申请"),
        //
        AUDITING(2, "审核通过"),
        //
        COMPLETE(3, "完成"), NOTAUDITING(4, "审核不通过"), FAILED(5, "失败");

        //
        private final int    key;

        //
        private final String name;

        private WithdrawCashStateType(int key, String name) {

            this.key = key;
            this.name = name;
        }

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        public static WithdrawCashStateType get(int key) {

            for (WithdrawCashStateType withdrawCashStateType : WithdrawCashStateType.values()) {
                if (withdrawCashStateType.getKey() == key) {
                    return withdrawCashStateType;
                }
            }
            return APPLY;
        }
    }
    public enum CouponStateType {
        //
        NOTUSE(1, "未使用"),
        //
        USE(2, "使用"),
        //
        Invalid(3, "已过期"),
        //
        DELETE(4, "删除");

        //
        private final int    key;

        //
        private final String name;

        private CouponStateType(int key, String name) {

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
    public enum InvoiceModeDefault {
        DEFAULT(1, "默认"), NOTDEFAULT(2, "非默认");

        private final int    key;

        private final String name;

        private InvoiceModeDefault(int key, String name) {

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
    // public final static String EXCEL_TEMPLATE_DIR = "/WEB-INF/excel";
}
