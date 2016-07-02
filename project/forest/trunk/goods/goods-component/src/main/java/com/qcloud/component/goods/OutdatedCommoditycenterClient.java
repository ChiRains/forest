//package com.qcloud.component.goods;
//
//import java.util.List;
//import com.qcloud.component.goods.model.Merchandise;
//import com.qcloud.component.goods.model.MerchandiseEvaluation;
//import com.qcloud.component.goods.model.UnifiedMerchandise;
//import com.qcloud.component.goods.model.query.MerchandiseQuery;
//import com.qcloud.pirates.data.Page;
//
//@Deprecated
//public interface OutdatedCommoditycenterClient {
//
////    public Page<MerchandiseItem> merchandiseItemPage(MerchandiseItemQuery query, int start, int count);
//
//    public Page<Merchandise> merchandisePage(MerchandiseQuery query, int start, int count);
//
//    public List<Long> merchandiseIdList(MerchandiseQuery query);
//
//    public Merchandise getMerchandise(long merchandiseId);
//
////    public MerchandiseItem getMerchandiseItem(long merchandiseItemId);
// 
//    public Long returnUnifiedMerchandiseId(UnifiedMerchandise merchandise);
//
////    public boolean addMerchandisMarketing(MerchandiseMarketing merchandiseMarketing);
//
////    public Page<MerchandiseMarketing> merchandiseMarketingList(MerchandiseMarketingQuery query, int start, int count);
//
//    public boolean deleteMerchandiseOfMarketing(Long id);
//
//    public boolean setEnable(Long id, int enable);
//
//    // 通过商家id获取商家商品
////    public List<MerchandiseItem> merchandiseListByMerchantId(long merchantId);
//
//    public List<Merchandise> getMerchandiseList(long merchantId);
//
//    public boolean offline(Long id);
//
//    public int count4DeleteClassify(Long mallClassifyId);
//
//    public MerchandiseEvaluation getMerchandiseEvaluation(Long evaluationId, Long merchandiseId);
//}