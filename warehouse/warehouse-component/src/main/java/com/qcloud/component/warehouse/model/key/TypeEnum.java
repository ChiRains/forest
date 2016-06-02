package com.qcloud.component.warehouse.model.key;

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
    public enum StockStateType {
        APPLY(1, "申请"), CONFIRM(2, "确认"), SIGN(3, "签收");

        private final int    key;

        private final String name;

        private StockStateType(int key, String name) {

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
