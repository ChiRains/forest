//package com.qcloud.component.mall.web.eximpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.commoditycenter.CommoditycenterClient;
//import com.qcloud.component.commoditycenter.OutdatedCommoditycenterClient;
//import com.qcloud.component.commoditycenter.model.Merchandise;
//import com.qcloud.component.sellercenter.web.handler.MerchandiseGetter;
//
//@Component
//public class MerchandiseGetterImpl implements MerchandiseGetter {
//
//    @Autowired
//    OutdatedCommoditycenterClient outdatedCommoditycenterClient;
//
//    @Override
//    public MerchandiseModel get(Long id) {
//
//        final Merchandise merchandise = outdatedCommoditycenterClient.getMerchandise(id);
//        return new MerchandiseModel() {
//
//            @Override
//            public String getName() {
//
//                return merchandise.getName();
//            }
//        };
//    }
//}
