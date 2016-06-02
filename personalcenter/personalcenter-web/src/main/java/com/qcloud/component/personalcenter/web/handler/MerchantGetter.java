//package com.qcloud.component.personalcenter.web.handler;
//
//import java.util.List;
//
//public interface MerchantGetter {
//
//    ShoppingCartMerchant get4ShoppingCart(Long merchantId);
//
//    CollectionMerchant get4Collection(Long merchantId);
//    //
//    public static interface ShoppingCartMerchant {
//
//        Long getMerchantId();
//
//        String getName();
//
//        // 图片
//        String getImage();
//
//        // 图片
//        String setImage(String image);
//    }
//    public static interface CollectionMerchant {
//
//        Long getMerchantId();
//
//        String getName();
//
//        // 图片
//        String getImage();
//
//        // 图片
//        String setImage(String image);
//
//        Long getClassifyId();
//
//        List<MerchantHotMerchandise> getHotMerchandises(int number);
//    }
//    public static interface MerchantHotMerchandise {
//
//        Long getUnifiedMerchandiseId();
//
//        Long getMerchandiseId();
//
//        String getImage();
//
//        // 图片
//        String setImage(String image);
//
//        double getDiscount();
//
//        double getPrice();
//    }
//}
