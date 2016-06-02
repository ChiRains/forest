package com.qcloud.component.personalcenter.model.key;

public class TypeEnum {

    public static final String USER_ACCOUNT_CODE = "user";

    public static final String USER_MESSAGE_CODE = "user";
    //
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
    // public enum AccountDataType {
    // MOBILE("mobile", "手机"), EMAIL("email", "email");
    //
    // private final String key;
    //
    // private final String name;
    //
    // private AccountDataType(String key, String name) {
    //
    // this.key = key;
    // this.name = name;
    // }
    //
    // public String getKey() {
    //
    // return key;
    // }
    //
    // public String getName() {
    //
    // return name;
    // }
    // }
    public enum AccountType {
        REGISTER(1, "注册用户"), QQ(2, "QQ用户"), WEIXIN(3, "微信用户"), WEIBO(4, "微博用户");

        private final int    key;

        private final String name;

        private AccountType(int key, String name) {

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
    public enum UserStateType {
        // 新增状态,尚未激活
        NEW(1, "新建"),
        // 新增状态,拿到激活码激活,第一次设置用密码激活
        ACTIVATE(2, "激活"),
        // 冻结状态,由于不正当言论,可以冻结一定时间后系统自动解冻
        FROZEN(3, "冻结"),
        // 禁用, 可对用户进行禁用,禁用后不能登录系统,可再由管理员启用,启用后直接进入激活状态
        FORBIDDEN(4, "禁用");

        private final int    key;

        private final String name;

        private UserStateType(int key, String name) {

            this.key = key;
            this.name = name;
        }

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        public static String getDesc(int state) {

            UserStateType[] types = UserStateType.values();
            for (UserStateType userStateType : types) {
                if (userStateType.getKey() == state) {
                    return userStateType.getName();
                }
            }
            return "";
        }
    }
    public enum MembershipCardWarehouseStateType {
        // 新增状态,尚未激活
        NEW(1, "新建"),
        // 激活
        ACTIVATE(2, "激活"),
        // 激活
        LOSS(3, "挂失");

        private final int    key;

        private final String name;

        private MembershipCardWarehouseStateType(int key, String name) {

            this.key = key;
            this.name = name;
        }

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        public static String getDesc(int state) {

            UserStateType[] types = UserStateType.values();
            for (UserStateType userStateType : types) {
                if (userStateType.getKey() == state) {
                    return userStateType.getName();
                }
            }
            return "";
        }
    }
    // public enum UserOrderStateType {
    // //
    // TOPAY(1, "待付款"),
    // //
    // INVALID(2, "已失效"),
    // //
    // CANCEL_ORDER(3, "已取消"),
    // //
    // SHIP(4, "待发货"),
    // //
    // SIGN(5, "待收货"),
    // //
    // EVALUATION(6, "待评价"),
    // //
    // EVALUATED(7, "已评价"),
    // //
    // AFTERSALES(8, "售后"),
    // //
    // SUCCESS(9, "成功");
    //
    // //
    // private final int key;
    //
    // //
    // private final String name;
    //
    // private UserOrderStateType(int key, String name) {
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
    // public static String getNameByState(int state) {
    //
    // for (UserOrderStateType stateType : UserOrderStateType.values()) {
    // if (stateType.getKey() == state) {
    // return stateType.getName();
    // }
    // }
    // return "";
    // }
    // }
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
    // // 发票类型
    // public enum MessageType {
    // //
    // UNREAD(1, "未读"),
    // //
    // READ(2, "已读"),
    // //
    // DELETE(3, "删除");
    //
    // //
    // private final int key;
    //
    // //
    // private final String name;
    //
    // private MessageType(int key, String name) {
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
    // public static MessageType get(int key) {
    //
    // for (MessageType messageType : MessageType.values()) {
    // if (messageType.getKey() == key) {
    // return messageType;
    // }
    // }
    // return UNREAD;
    // }
    // }
    public enum CollectionType {
        //
        MERCHANDISE(1, "收藏商品"),
        //
        MERCHANT(2, "收藏商家");

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
        DELETE(3, "删除");

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

    public final static String EXCEL_TEMPLATE_DIR = "/WEB-INF/excel";
    //
    public enum WealthDetailNote {
        Integral_Buy_Gift(1, "购物送积分"), Integral_Buy_Use(2, "购物抵现"), Integral_Sign_Gift(3, "签到送积分"),
        Commission_Buy_Gift(4, "购物送佣金"),  Commission_Buy_Use(5, "购物抵现"),  Commission_Sign_Gift(6, "签到送佣金"),
        Comsumption_Buy_Gift(7, "购物送消费币"), Comsumption_Buy_Use(8, "购物抵现"), Comsumption_Sign_Gift(9, "签到送消费币");

        private final int    key;

        private final String name;

        private WealthDetailNote(int key, String name) {

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
