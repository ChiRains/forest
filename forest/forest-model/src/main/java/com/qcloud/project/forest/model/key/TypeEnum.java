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
	
	public enum StateType{
		UNTREATED(0,"未审核"),TREATED(1,"已处理"),NOTPASS(2,"未通过");
		private final int key;
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
	public enum ClassifyType{
		ARTICLE(60,"资讯分类");
		private final int key;
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

}
