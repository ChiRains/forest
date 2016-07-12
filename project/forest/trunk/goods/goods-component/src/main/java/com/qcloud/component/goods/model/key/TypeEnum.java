package com.qcloud.component.goods.model.key;

public class TypeEnum {

    public enum ClassifySpecificationsUploadImageType {
        YES(1, "是"), NO(2, "否");

        private final int    key;

        private final String name;

        private ClassifySpecificationsUploadImageType(int key, String name) {

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
    public enum MerchandiseStateType {
        INIT(1, "待初始化"), NEW(2, "新增"), AUDITING(3, "待审核"), ONLINE(4, "上线"), OFFLINE(5, "下线");

        private final int    key;

        private final String name;

        private MerchandiseStateType(int key, String name) {

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
    public enum OrderType {
        ASE(1, "升序"), DESC(2, "降序");

        private final int    key;

        private final String name;

        private OrderType(int key, String name) {

            this.key = key;
            this.name = name;
        }

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        public static OrderType get(int type) {

            for (OrderType orderType : OrderType.values()) {
                if (orderType.getKey() == type) {
                    return orderType;
                }
            }
            return ASE;
        }
    }
    public enum QueryType {
        SALES_VOLUME(1, "销量"), DATE(2, "时间"), PRICE(3, "价格"), HOT(4, "热度"), GOODEVALUATION(5, "好评");

        private final int    key;

        private final String name;

        private QueryType(int key, String name) {

            this.key = key;
            this.name = name;
        }

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        public static QueryType get(int type) {

            for (QueryType queryType : QueryType.values()) {
                if (queryType.getKey() == type) {
                    return queryType;
                }
            }
            return SALES_VOLUME;
        }
    }
    public enum QueryItemType {
        M(1, "商品档案"), S(2, "商品规格"), MS(3, "全部");

        private final int    key;

        private final String name;

        private QueryItemType(int key, String name) {

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
    public enum AttrType {
        attr(1, "商品属性"), spec(2, "商品规格");

        private final int    key;

        private final String name;

        private AttrType(int key, String name) {

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
    public enum AttrValueType {
        INPUT(1, "输入框"), SELECT(2, "下拉框"), RADIO(3, "单选框"), CHECKBOX(4, "复选框"), EDITOR(5, "富文本"), TEXTAREA(6, "文本域");

        private final int    key;

        private final String name;

        private AttrValueType(int key, String name) {

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
    public enum StarLevelType {
        ALL(0, "所有"), CP(10, "差评"), ZP(30, "中评"), HP(40, "好评");

        private final int    key;

        private final String name;

        private StarLevelType(int key, String name) {

            this.key = key;
            this.name = name;
        }

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        public static StarLevelType get(int type) {

            StarLevelType t = StarLevelType.ALL;
            switch (type) {
            case 0:
                t = StarLevelType.ALL;
                break;
            case 1:
                t = StarLevelType.HP;
                break;
            case 2:
                t = StarLevelType.ZP;
                break;
            case 3:
                t = StarLevelType.CP;
                break;
            default:
                t = StarLevelType.ALL;
                break;
            }
            return t;
        }
    }
    public enum BrandType {
        Brand(100, "品牌"), BRANDONSALE(110, "品牌特卖");

        private final int    key;

        private final String name;

        private BrandType(int key, String name) {

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
    public enum MerchandiseIsIncludePost {
        YES(1, "是"), NO(2, "否");

        private final int    key;

        private final String name;

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        private MerchandiseIsIncludePost(int key, String name) {

            this.key = key;
            this.name = name;
        }
    }
    public enum VipPaymentType {
        PRICE(1, "价格"), DISCOUNT(2, "折扣");

        private final int    key;

        private final String name;

        public int getKey() {

            return key;
        }

        public String getName() {

            return name;
        }

        private VipPaymentType(int key, String name) {

            this.key = key;
            this.name = name;
        }
    }

    public final static String EXCEL_TEMPLATE_DIR = "/WEB-INF/excel";
}
