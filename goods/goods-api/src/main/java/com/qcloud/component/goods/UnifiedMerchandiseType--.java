//package com.qcloud.component.goods;
//
//public enum UnifiedMerchandiseType {
//    SINGLE(1, "单一商品"), COMBINATION(2, "组合商品"), MARKETING(3, "促销、活动商品");
//
//    private final int    key;
//
//    private final String name;
//
//    private UnifiedMerchandiseType(int key, String name) {
//
//        this.key = key;
//        this.name = name;
//    }
//
//    public int getKey() {
//
//        return key;
//    }
//
//    public String getName() {
//
//        return name;
//    }
//
//    public static UnifiedMerchandiseType get(int type) {
//
//        for (UnifiedMerchandiseType umt : UnifiedMerchandiseType.values()) {
//            if (umt.getKey() == type) {
//                return umt;
//            }
//        }
//        return null;
//    }
//}
