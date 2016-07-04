//package com.qcloud.component.sellercenter;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import com.qcloud.component.publicdata.KeyValueVO;
//import com.qcloud.component.publicdata.model.Classify;
//import com.qcloud.component.sellercenter.model.MerchantEvaluation;
//import com.qcloud.component.sellercenter.model.StoreMember;
//import com.qcloud.component.sellercenter.model.query.MerchantQuery;
//import com.qcloud.component.sellercenter.model.query.StoreMemberQuery;
//import com.qcloud.pirates.data.Page;
//
//@Deprecated
//public interface OutdatedSellercenterClient {
//
//    List<QMerchant> listMerchant(long memberId);
//
//    List<Classify> listMerchantClassify(long merchantId);
//
//    List<Classify> listMerchantMerchandiseClassify(long merchantId);
//    
//    List<Classify> listMerchantSpecClassify(long merchantId);
//
//    MerchantEvaluation getMerchantEvaluation(long evaluationId, long merchantId);
//
//    boolean deleteMerchantEvaluation(long evaluationId, long merchantId);
//  
//    StoreMember getMemberStore(StoreMemberQuery query);
//
//    // boolean sendMsgToMerchant(long merchantId, SellerMessageType type, String title, String content);
//    //
//    // boolean sendMsgToStore(long storeId, SellerMessageType type, String title, String content);
//    boolean sendSmsToMerchant(long merchantId, String content);
//
//    // boolean sendSmsToMerchant(long merchantId, String code, Map<String, String> map);
//    boolean sendSmsToStore(long storeId, String content);
//
//    // boolean sendSmsToStore(long storeId, String code, Map<String, String> map);
//    Page<QMerchant> list4select(MerchantQuery query, int start, int count);
//}