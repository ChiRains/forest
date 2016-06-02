package com.qcloud.component.brokerage.model.key;

public class TypeEnum {

    public static final int Distribution_Grade_Type = 99;
    public enum UserAllocationType {
        ALLOCATION(1, "已经分配"), NOT_ALLOCATION(2, "尚未分配");

        private final int    key;

        private final String name;

        private UserAllocationType(int key, String name) {

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
    public enum FormulaCalculationRecordStateType {
        START(1, "开始"), FINISH(2, "结束");

        private final int    key;

        private final String name;

        private FormulaCalculationRecordStateType(int key, String name) {

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
    public enum FormulaCalculationResultStateType {
        // allocate
        INIT(1, "计算好"), ALLOCATING(2, "正在分配"), ALLOCATED(3, "已分配");

        private final int    key;

        private final String name;

        private FormulaCalculationResultStateType(int key, String name) {

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
    public enum tradeUserDistributionType {
        YES(1, "参加"), NO(2, "不参加");

        private final int    key;

        private final String name;

        private tradeUserDistributionType(int key, String name) {

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
    public enum DistributionBrokerageStateType {
        // allocate
        TO_AUDIT(1, "计算好"), PASS(2, "通过"), NOT_PASS(3, "不通过");

        private final int    key;

        private final String name;

        private DistributionBrokerageStateType(int key, String name) {

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
