//package com.qcloud.component.mall.web.eximpl;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.commoditycenter.CommoditycenterClient;
//import com.qcloud.component.commoditycenter.OutdatedCommoditycenterClient;
//import com.qcloud.component.commoditycenter.model.Merchandise;
//import com.qcloud.component.commoditycenter.model.MerchandiseItem;
//import com.qcloud.component.commoditycenter.model.key.TypeEnum.OrderType;
//import com.qcloud.component.commoditycenter.model.key.TypeEnum.QueryItemType;
//import com.qcloud.component.commoditycenter.model.key.TypeEnum.QueryType;
//import com.qcloud.component.commoditycenter.model.query.MerchandiseItemQuery;
//import com.qcloud.component.personalcenter.web.handler.MerchantGetter;
//import com.qcloud.component.sellercenter.QMerchant;
//import com.qcloud.component.sellercenter.SellercenterClient;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.util.AssertUtil;
//
//@Component
//public class MerchantGetterImpl implements MerchantGetter {
//
//    @Autowired
//    SellercenterClient    sellercenterClient;
//
//    @Autowired
//    CommoditycenterClient commoditycenterClient;
//    
//    @Autowired
//    OutdatedCommoditycenterClient outdatedCommoditycenterClient;
//  
//    @Override
//    public ShoppingCartMerchant get4ShoppingCart(Long merchantId) {
//
//        final QMerchant merchant = sellercenterClient.getMerchant(merchantId);
//        return new ShoppingCartMerchant() {
//
//            private String image;
//
//            @Override
//            public Long getMerchantId() {
//
//                return merchant.getId();
//            }
//
//            @Override
//            public String getName() {
//
//                return merchant.getName();
//            }
//
//            @Override
//            public String getImage() {
//
//                if (StringUtils.isEmpty(image)) {
//                    return merchant.getImage();
//                }
//                return image;
//            }
//
//            @Override
//            public String setImage(String image) {
//
//                return this.image = image;
//            }
//        };
//    }
//
//    @Override
//    public CollectionMerchant get4Collection(Long merchantId) {
//
//        final QMerchant merchant = sellercenterClient.getMerchant(merchantId);
//        AssertUtil.assertNotNull(merchant, "商家不存在." + merchantId);
//        return new CollectionMerchant() {
//
//            private String image;
//
//            @Override
//            public Long getMerchantId() {
//
//                return merchant.getId();
//            }
//
//            @Override
//            public String getName() {
//
//                return merchant.getName();
//            }
//
//            @Override
//            public String getImage() {
//
//                if (StringUtils.isEmpty(image)) {
//                    return merchant.getImage();
//                }
//                return image;
//            }
//
//            @Override
//            public String setImage(String image) {
//
//                return this.image = image;
//            }
//
//            @Override
//            public Long getClassifyId() {
//
//                return merchant.getClassifyId();
//            }
//
//            @Override
//            public List<MerchantHotMerchandise> getHotMerchandises(int number) {
//
//                MerchandiseItemQuery merchandiseItemQuery = new MerchandiseItemQuery();
//                merchandiseItemQuery.setQueryType(QueryType.HOT);
//                merchandiseItemQuery.setOrderType(OrderType.DESC);
//                merchandiseItemQuery.setQueryItemType(QueryItemType.MS);
//                merchandiseItemQuery.setMerchantId(merchant.getId());
//                Page<MerchandiseItem> page = outdatedCommoditycenterClient.merchandiseItemPage(merchandiseItemQuery, 0, number);
//                List<MerchantHotMerchandise> merchantHotMerchandiseList = new ArrayList<MerchantHotMerchandise>();
//                List<MerchandiseItem> merchandiseItemList = page.getData();
//                for (final MerchandiseItem merchandiseItem : merchandiseItemList) {
//                    final Merchandise merchandise = outdatedCommoditycenterClient.getMerchandise(merchandiseItem.getMerchandiseId());
//                    merchantHotMerchandiseList.add(new MerchantHotMerchandise() {
//
//                        private String image;
//
//                        @Override
//                        public Long getMerchandiseId() {
//
//                            return merchandiseItem.getMerchandiseId();
//                        }
//
//                        @Override
//                        public String getImage() {
//
//                            if (StringUtils.isEmpty(image)) {
//                                return merchandise.getImage();
//                            }
//                            return image;
//                        }
//
//                        @Override
//                        public String setImage(String image) {
//
//                            return this.image = image;
//                        }
//
//                        @Override
//                        public double getDiscount() {
//
//                            return merchandiseItem.getDiscount();
//                        }
//
//                        @Override
//                        public double getPrice() {
//
//                            return merchandiseItem.getPrice();
//                        }
//
//                        @Override
//                        public Long getUnifiedMerchandiseId() {
//
//                            return merchandiseItem.getUnifiedMerchandiseId();
//                        }
//                    });
//                }
//                return merchantHotMerchandiseList;
//            }
//        };
//    }
//}
