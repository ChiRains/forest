//package com.qcloud.component.mall.web.eximpl;
//
//import java.util.Date;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.commoditycenter.OutdatedCommoditycenterClient;
//import com.qcloud.component.commoditycenter.model.MerchandiseEvaluation;
//import com.qcloud.component.sellercenter.web.handler.MerchandiseEvaluationGetter;
//import com.qcloud.pirates.util.AssertUtil;
//
//@Component
//public class MerchandiseEvaluationGetterImpl implements MerchandiseEvaluationGetter {
//
//    @Autowired
//    OutdatedCommoditycenterClient outdatedCommoditycenterClient;
//
//    @Override
//    public MerchandiseEvaluation4Seller get(long evaluationId, long merchandiseId) {
//
//        final MerchandiseEvaluation merchandiseEvaluation = outdatedCommoditycenterClient.getMerchandiseEvaluation(evaluationId, merchandiseId);
//        AssertUtil.assertNotNull(merchandiseEvaluation, "商品评价不存在.");
//        MerchandiseEvaluation4Seller merchandiseEvaluation4Seller = new MerchandiseEvaluation4Seller() {
//
//            @Override
//            public long getId() {
//
//                return merchandiseEvaluation.getId();
//            }
//
//            @Override
//            public String getContent() {
//
//                return merchandiseEvaluation.getContent();
//            }
//
//            @Override
//            public int getStar() {
//
//                return merchandiseEvaluation.getStar();
//            }
//
//            @Override
//            public Date getTime() {
//
//                return merchandiseEvaluation.getTime();
//            }
//
//            @Override
//            public int getStatus() {
//
//                return merchandiseEvaluation.getStatus();
//            }
//
//            @Override
//            public String getSpecifications() {
//
//                return merchandiseEvaluation.getSpecifications();
//            }
//
//            @Override
//            public long getUserId() {
//
//                return merchandiseEvaluation.getUserId();
//            }
//        };
//        return merchandiseEvaluation4Seller;
//    }
//}
