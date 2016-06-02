//package com.qcloud.project.forest.web.eximpl;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.commoditycenter.CommoditycenterClient;
//import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
//import com.qcloud.component.commoditycenter.QUnifiedMerchandise.MerchandiseType;
//import com.qcloud.component.personalcenter.web.handler.UnifiedMerchandiseGetter;
//import com.qcloud.project.forest.exception.ForestException;
//
//@Component
//public class UnifiedMerchandiseGetterImpl implements UnifiedMerchandiseGetter {
//
//    @Autowired
//    private CommoditycenterClient commoditycenterClient;
//
//    @Override
//    public ShoppingCartUnifiedMerchandise get4ShoppingCart(Long unifiedMerchandiseId) {
//
//        final QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
//        return new ShoppingCartUnifiedMerchandise() {
//
//            @Override
//            public long getMerchantId() {
//
//                return unifiedMerchandise.getMerchantId();
//            }
//
//            @Override
//            public long getMerchantClassifyId() {
//
//                if (unifiedMerchandise.getList().size() > 0) {
//                    return unifiedMerchandise.getList().get(0).getMerchantClassifyId();
//                } else {
//                    return 0L;
//                }
//            }
//
//            @Override
//            public String getName() {
//
//                return unifiedMerchandise.getName();
//            }
//
//            @Override
//            public String getImage() {
//
//                return unifiedMerchandise.getImage();
//            }
//
//            @Override
//            public double getDiscount() {
//
//                return unifiedMerchandise.getDiscount();
//            }
//
//            @Override
//            public double getPrice() {
//
//                return unifiedMerchandise.getPrice();
//            }
//
//            @Override
//            public String getSpecifications() {
//
//                if (unifiedMerchandise.getList().size() > 0) {
//                    return unifiedMerchandise.getList().get(0).getSpecifications();
//                } else {
//                    return null;
//                }
//            }
//
//            @Override
//            public int getStock() {
//
//                return unifiedMerchandise.getStock();
//            }
//
//            @Override
//            public int getMerchandiseType() {
//
//                MerchandiseType merchandiseType = unifiedMerchandise.getType();
//                if (MerchandiseType.SINGLE.equals(merchandiseType)) {
//                    return 1;
//                } else if (MerchandiseType.COMBINATION.equals(merchandiseType)) {
//                    return 2;
//                } else if (MerchandiseType.MARKETING.equals(merchandiseType)) {
//                    return 3;
//                }
//                throw new ForestException("不支持的商品类型." + merchandiseType);
//            }
//        };
//    }
//
//    @Override
//    public CollectionUnifiedMerchandise get4Collection(Long unifiedMerchandiseId) {
//
//        final QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
//        return new CollectionUnifiedMerchandise() {
//
//            private String image;
//
//            @Override
//            public String getName() {
//
//                return unifiedMerchandise.getName();
//            }
//
//            @Override
//            public String getImage() {
//
//                if (StringUtils.isEmpty(image)) {
//                    return unifiedMerchandise.getImage();
//                }
//                return image;
//            }
//
//            @Override
//            public double getDiscount() {
//
//                return unifiedMerchandise.getDiscount();
//            }
//
//            @Override
//            public double getPrice() {
//
//                return unifiedMerchandise.getPrice();
//            }
//
//            @Override
//            public long getSalesVolume() {
//
//                if (unifiedMerchandise.getList().size() > 0) {
//                    return unifiedMerchandise.getList().get(0).getSalesVolume();
//                } else {
//                    return 0L;
//                }
//            }
//
//            @Override
//            public String getSpecifications() {
//
//                if (unifiedMerchandise.getList().size() > 0) {
//                    return unifiedMerchandise.getList().get(0).getSpecifications();
//                } else {
//                    return null;
//                }
//            }
//
//            @Override
//            public String setImage(String image) {
//
//                return this.image = image;
//            }
//
//            @Override
//            public Long getMallClassifyId() {
//
//                if (unifiedMerchandise.getList().size() > 0) {
//                    return unifiedMerchandise.getList().get(0).getMallClassifyId();
//                } else {
//                    return null;
//                }
//            }
//        };
//    }
//}
