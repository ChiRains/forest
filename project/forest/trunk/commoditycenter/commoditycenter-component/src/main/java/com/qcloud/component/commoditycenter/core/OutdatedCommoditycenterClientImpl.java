package com.qcloud.component.commoditycenter.core;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.commoditycenter.OutdatedCommoditycenterClient;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.model.MerchandiseEvaluation;
import com.qcloud.component.commoditycenter.model.MerchandiseItem;
import com.qcloud.component.commoditycenter.model.MerchandiseMarketing;
import com.qcloud.component.commoditycenter.model.UnifiedMerchandise;
import com.qcloud.component.commoditycenter.model.query.MerchandiseItemQuery;
import com.qcloud.component.commoditycenter.model.query.MerchandiseMarketingQuery;
import com.qcloud.component.commoditycenter.model.query.MerchandiseQuery;
import com.qcloud.component.commoditycenter.service.MerchandiseEvaluationService;
import com.qcloud.component.commoditycenter.service.MerchandiseItemService;
import com.qcloud.component.commoditycenter.service.MerchandiseMarketingService;
import com.qcloud.component.commoditycenter.service.MerchandiseService;
import com.qcloud.component.commoditycenter.service.UnifiedMerchandiseService;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class OutdatedCommoditycenterClientImpl implements OutdatedCommoditycenterClient {

    @Autowired
    private MerchandiseItemService       merchandiseItemService;

    @Autowired
    private MerchandiseService           merchandiseService;

    @Autowired
    private MerchandiseMarketingService  merchandiseMarketingService;

    @Autowired
    private UnifiedMerchandiseService    unifiedMerchandiseService;

    @Autowired
    private MerchandiseEvaluationService merchandiseEvaluationService;

    @Override
    public Page<MerchandiseItem> merchandiseItemPage(MerchandiseItemQuery query, int start, int count) {

        return merchandiseItemService.page(BeanUtils.transBean2Map(query), start, count);
    }

    @Override
    public Page<Merchandise> merchandisePage(MerchandiseQuery query, int start, int count) {

        return merchandiseService.page(BeanUtils.transBean2Map(query), start, count);
    }

    @Override
    public List<Long> merchandiseIdList(MerchandiseQuery query) {

        List<Long> ids = new ArrayList<Long>();
        List<Merchandise> list = merchandiseService.merchandiseList(BeanUtils.transBean2Map(query));
        for (Merchandise merchandise : list) {
            ids.add(merchandise.getId());
        }
        return ids;
    }

    @Override
    public Merchandise getMerchandise(long merchandiseId) {

        return merchandiseService.get(merchandiseId);
    }

//    private MerchandiseType getMerchandiseType(int type) {
//
//        if (UnifiedMerchandiseType.SINGLE.getKey() == type) {
//            return MerchandiseType.SINGLE;
//        } else if (UnifiedMerchandiseType.COMBINAION.getKey() == type) {
//            return MerchandiseType.COMBINATION;
//        } else if (UnifiedMerchandiseType.MARKETING.getKey() == type) {
//            return MerchandiseType.MARKETING;
//        }
//        return null;
//    }

    @Override
    public MerchandiseItem getMerchandiseItem(long merchandiseItemId) {

        return merchandiseItemService.get(merchandiseItemId);
    }

//    @Override
//    public long getSalesVolume(long merchandiseId) {
//
//        List<MerchandiseItem> list = merchandiseItemService.listByMerchandise(merchandiseId);
//        long salesVolume = 0;
//        for (MerchandiseItem merchandiseItem : list) {
//            salesVolume += getSalesVolume(merchandiseItem);
//        }
//        return salesVolume;
//    }
//
//    private long getSalesVolume(MerchandiseItem merchandiseItem) {
//
//        if (merchandiseItem == null) {
//            return 0;
//        } else {
//            long salesVolume = merchandiseItem.getSalesVolume() > merchandiseItem.getVirtualSalesVolume() ? merchandiseItem.getSalesVolume() : merchandiseItem.getVirtualSalesVolume();
//            return salesVolume;
//        }
//    }

    // 添加活动商品
    @Override
    public boolean addMerchandisMarketing(MerchandiseMarketing merchandiseMarketing) {

        AssertUtil.assertNotNull(merchandiseMarketing, "添加失败");
        return merchandiseMarketingService.add(merchandiseMarketing);
    }

    @Override
    public Page<MerchandiseMarketing> merchandiseMarketingList(MerchandiseMarketingQuery query, int start, int count) {

        return merchandiseMarketingService.page(query, start, count);
    }

    @Override
    public boolean deleteMerchandiseOfMarketing(Long id) {

        AssertUtil.assertNotNull(id, "id不能为空");
        return merchandiseMarketingService.delete(id);
    }

    @Override
    public Long returnUnifiedMerchandiseId(UnifiedMerchandise merchandise) {

        return unifiedMerchandiseService.addThenRetrunId(merchandise);
    }

    @Override
    public boolean setEnable(Long id, int enable) {

        return merchandiseMarketingService.setEnable(id, enable);
    }

    @Override
    public List<MerchandiseItem> merchandiseListByMerchantId(long merchantId) {

        return merchandiseItemService.merchandiseListByMerchantId(merchantId);
    }

    @Override
    public boolean offline(Long id) {

        return merchandiseService.offline(id);
    }

    @Override
    public int count4DeleteClassify(Long mallClassifyId) {

        return merchandiseService.count4DeleteClassify(mallClassifyId);
    }

    @Override
    public List<Merchandise> getMerchandiseList(long merchantId) {

        return merchandiseService.getMerchandiseList(merchantId);
    }

    @Override
    public MerchandiseEvaluation getMerchandiseEvaluation(Long evaluationId, Long merchandiseId) {

        return merchandiseEvaluationService.get(evaluationId, merchandiseId);
    }
}
