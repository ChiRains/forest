package com.qcloud.component.commoditycenter.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QMerchandiseEvaluation;
import com.qcloud.component.commoditycenter.QMerchandiseItem;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise.MerchandiseType;
import com.qcloud.component.commoditycenter.entity.MerchandiseEvaluationEntity;
import com.qcloud.component.commoditycenter.entity.MerchandiseItemEntity;
import com.qcloud.component.commoditycenter.entity.UnifiedMerchandiseEntity;
import com.qcloud.component.commoditycenter.exception.CommoditycenterException;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.model.MerchandiseDealRecord;
import com.qcloud.component.commoditycenter.model.MerchandiseEvaluation;
import com.qcloud.component.commoditycenter.model.MerchandiseImage;
import com.qcloud.component.commoditycenter.model.MerchandiseItem;
import com.qcloud.component.commoditycenter.model.MerchandiseSpecifications;
import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscount;
import com.qcloud.component.commoditycenter.model.MonthHotSale;
import com.qcloud.component.commoditycenter.model.cache.MerchandiseItemCache;
import com.qcloud.component.commoditycenter.model.cache.UnifiedMerchandiseCache;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.OrderType;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.QueryType;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.UnifiedMerchandiseType;
import com.qcloud.component.commoditycenter.model.query.MerchandiseItemQuery;
import com.qcloud.component.commoditycenter.service.CombinationMerchandiseService;
import com.qcloud.component.commoditycenter.service.MerchandiseDealRecordService;
import com.qcloud.component.commoditycenter.service.MerchandiseEvaluationService;
import com.qcloud.component.commoditycenter.service.MerchandiseImageService;
import com.qcloud.component.commoditycenter.service.MerchandiseItemCacheService;
import com.qcloud.component.commoditycenter.service.MerchandiseItemService;
import com.qcloud.component.commoditycenter.service.MerchandiseMarketingService;
import com.qcloud.component.commoditycenter.service.MerchandiseService;
import com.qcloud.component.commoditycenter.service.MerchandiseSpecificationsService;
import com.qcloud.component.commoditycenter.service.MerchandiseVipDiscountService;
import com.qcloud.component.commoditycenter.service.MonthHotSaleService;
import com.qcloud.component.commoditycenter.service.UnifiedMerchandiseCacheService;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.core.reflect.BeanUtils;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class CommoditycenterClientImpl implements CommoditycenterClient {

    @Autowired
    private UnifiedMerchandiseCacheService   unifiedMerchandiseCacheService;

    @Autowired
    private MerchandiseItemCacheService      merchandiseItemCacheService;

    @Autowired
    private MerchandiseItemService           merchandiseItemService;

    @Autowired
    private MerchandiseMarketingService      merchandiseMarketingService;

    @Autowired
    private CombinationMerchandiseService    combinationMerchandiseService;

    @Autowired
    private MerchandiseDealRecordService     merchandiseDealRecordService;

    @Autowired
    private MerchandiseSpecificationsService merchandiseSpecificationsService;

    @Autowired
    private MerchandiseImageService          merchandiseImageService;

    @Autowired
    private MerchandiseEvaluationService     merchandiseEvaluationService;

    @Autowired
    private MerchandiseService               merchandiseService;

    @Autowired
    private MerchandiseVipDiscountService    merchandiseVipDiscountService;

    @Autowired
    private FileSDKClient                    fileSDKClient;

    @Autowired
    private MonthHotSaleService              monthHotSaleService;

    @Autowired
    private PublicdataClient                 publicdataClient;

    @Override
    public QUnifiedMerchandise getUnifiedMerchandise(long unifiedMerchandiseId) {

        // 组装统一商品对象,供其他组件与前端接口使用
        UnifiedMerchandiseCache unifiedMerchandiseCache = unifiedMerchandiseCacheService.get(unifiedMerchandiseId);
        if (unifiedMerchandiseCache == null) {
            return null;
        }
        UnifiedMerchandiseEntity um = new UnifiedMerchandiseEntity();
        um.setMerchantId(unifiedMerchandiseCache.getMerchantId());
        um.setDiscount(unifiedMerchandiseCache.getDiscount());
        um.setId(unifiedMerchandiseCache.getId());
        um.setPrice(unifiedMerchandiseCache.getPrice());
        um.setPurchase(unifiedMerchandiseCache.getPurchase());
        um.setStock(unifiedMerchandiseCache.getStock());
        um.setName(unifiedMerchandiseCache.getName());
        um.setImage(unifiedMerchandiseCache.getImage());
        um.setType(getMerchandiseType(unifiedMerchandiseCache.getType()));
        um.setSence(unifiedMerchandiseCache.getSence());
        um.setIncludePost(unifiedMerchandiseCache.getIsIncludePost());
        //
        double weight = 0.0;
        List<MerchandiseItemCache> list = merchandiseItemCacheService.list(unifiedMerchandiseId);
        List<QMerchandiseItem> miList = new ArrayList<QMerchandiseItem>();
        for (MerchandiseItemCache merchandiseItemCache : list) {
            MerchandiseItemEntity mi = new MerchandiseItemEntity();
            mi.setId(merchandiseItemCache.getId());
            mi.setMerchandiseId(merchandiseItemCache.getMerchandiseId());
            mi.setCode(merchandiseItemCache.getCode());
            mi.setImage(merchandiseItemCache.getImage());
            mi.setMerchantClassifyId(merchandiseItemCache.getMerchantClassifyId());
            mi.setMerchantId(merchandiseItemCache.getMerchantId());
            mi.setName(merchandiseItemCache.getName());
            mi.setSpecifications(merchandiseItemCache.getSpecifications());
            mi.setSalesVolume(merchandiseItemCache.getSalesVolume());
            mi.setMallClassifyId(merchandiseItemCache.getMallClassifyId());
            mi.setUnifiedMerchandiseId(merchandiseItemCache.getUnifiedMerchandiseId());
            mi.setNumber(merchandiseItemCache.getNumber());
            mi.setUnit(merchandiseItemCache.getUnit());
            mi.setGoodEvaluation(merchandiseItemCache.getGoodEvaluation());
            mi.setMiddleEvaluation(merchandiseItemCache.getMiddleEvaluation());
            mi.setLowEvaluation(merchandiseItemCache.getLowEvaluation());
            miList.add(mi);
            weight += merchandiseItemCache.getWeight() * merchandiseItemCache.getNumber();
        }
        um.setList(miList);
        um.setWeight(weight);
        return um;
    }

    @Override
    public boolean updateOnlineStock(long unifiedMerchandiseId, int stock) {

        QUnifiedMerchandise unifiedMerchandise = getUnifiedMerchandise(unifiedMerchandiseId);
        // 更新库存,只能针对单一商品
        if (MerchandiseType.SINGLE.equals(unifiedMerchandise.getType())) {
            MerchandiseItem merchandiseItem = merchandiseItemService.get(unifiedMerchandise.getList().get(0).getId());
            int totalStock = merchandiseItem.getStock() + stock;
            if (totalStock < 0) {
                throw new CommoditycenterException("调整库存后库存不大于等于零,调整失败." + unifiedMerchandiseId);
            }
            merchandiseItem.setStock(totalStock);
            // merchandiseItem.setDiscount(discount);
            // merchandiseItem.setPurchase(purchase);
            // merchandiseItem.setPrice(price);
            return merchandiseItemService.update(merchandiseItem);
        }
        return false;
    }

    @Override
    public boolean lockOnlineStock(long unifiedMerchandiseId, int stock) {

        QUnifiedMerchandise unifiedMerchandise = getUnifiedMerchandise(unifiedMerchandiseId);
        int totalStock = unifiedMerchandise.getStock() + stock;
        if (totalStock < 0) {
            throw new CommoditycenterException("锁定库存失败,库存不足." + unifiedMerchandise.getId() + " " + unifiedMerchandise.getStock() + " " + stock);
        }
        if (MerchandiseType.SINGLE.equals(unifiedMerchandise.getType())) {
            Long itemId = unifiedMerchandise.getList().get(0).getId();
            return merchandiseItemService.lockStock(itemId, stock);
        } else if (MerchandiseType.COMBINATION.equals(unifiedMerchandise.getType())) {
            return combinationMerchandiseService.lockStock(unifiedMerchandiseId, stock);
        } else if (MerchandiseType.MARKETING.equals(unifiedMerchandise.getType())) {
            return merchandiseMarketingService.lockStock(unifiedMerchandiseId, stock);
        } else {
            return false;
        }
    }

    private MerchandiseType getMerchandiseType(int type) {

        if (UnifiedMerchandiseType.SINGLE.getKey() == type) {
            return MerchandiseType.SINGLE;
        } else if (UnifiedMerchandiseType.COMBINAION.getKey() == type) {
            return MerchandiseType.COMBINATION;
        } else if (UnifiedMerchandiseType.MARKETING.getKey() == type) {
            return MerchandiseType.MARKETING;
        }
        return null;
    }

    @Override
    public boolean addSalesVolume(long unifiedMerchandiseId, int number) {

        QUnifiedMerchandise unifiedMerchandise = getUnifiedMerchandise(unifiedMerchandiseId);
        if (MerchandiseType.SINGLE.equals(unifiedMerchandise.getType())) {
            return merchandiseItemService.updateSalesVolume(unifiedMerchandise.getList().get(0).getId(), number);
        }
        // 其他暂不记录
        return true;
    }

    @Override
    public void incrementMerchandiseDealRecoed(Long userId, Long unifiedMerchandiseId, int number, String specifications, Long[] orderIds) {

        AssertUtil.assertNotNull(orderIds, "订单ID不能为空.");
        AssertUtil.assertTrue(orderIds.length == 4, "订单ID数组长度不正确.");
        QUnifiedMerchandise unifiedMerchandise = getUnifiedMerchandise(unifiedMerchandiseId);
        MerchandiseType merchandiseType = unifiedMerchandise.getType();
        if (MerchandiseType.SINGLE.equals(merchandiseType) || MerchandiseType.MARKETING.equals(merchandiseType)) {
            MerchandiseDealRecord merchandiseDealRecord = new MerchandiseDealRecord();
            merchandiseDealRecord.setMerchandiseId(unifiedMerchandise.getList().get(0).getMerchandiseId());
            merchandiseDealRecord.setSpecifications(specifications);
            merchandiseDealRecord.setUserId(userId);
            merchandiseDealRecord.setTime(new Date());
            merchandiseDealRecord.setNumber(number);
            merchandiseDealRecord.setOrderId(orderIds[0]);
            merchandiseDealRecord.setSubOrderId(orderIds[1]);
            merchandiseDealRecord.setOrderItemId(orderIds[2]);
            merchandiseDealRecord.setOrderItemDetailId(orderIds[3]);
            merchandiseDealRecordService.add(merchandiseDealRecord);
        } else {
            List<QMerchandiseItem> list = unifiedMerchandise.getList();
            for (QMerchandiseItem merchandiseItem : list) {
                MerchandiseDealRecord merchandiseDealRecord = new MerchandiseDealRecord();
                merchandiseDealRecord.setMerchandiseId(merchandiseItem.getMerchandiseId());
                merchandiseDealRecord.setSpecifications(specifications);
                merchandiseDealRecord.setUserId(userId);
                merchandiseDealRecord.setTime(new Date());
                merchandiseDealRecord.setNumber(number);
                merchandiseDealRecord.setOrderId(orderIds[0]);
                merchandiseDealRecord.setSubOrderId(orderIds[1]);
                merchandiseDealRecord.setOrderItemId(orderIds[2]);
                merchandiseDealRecord.setOrderItemDetailId(orderIds[3]);
                merchandiseDealRecordService.add(merchandiseDealRecord);
            }
        }
        if (MerchandiseType.SINGLE.equals(merchandiseType)) {
            Calendar calendar = Calendar.getInstance();
            MonthHotSale monthHotSale = monthHotSaleService.getByUnifiedMerchandise(unifiedMerchandiseId, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
            if (monthHotSale != null) {
                monthHotSale.setNumber(monthHotSale.getNumber() + number);
                monthHotSaleService.update(monthHotSale);
            } else {
                long mallClassifyId = unifiedMerchandise.getList().get(0).getMallClassifyId();
                long merchantClassifyId = unifiedMerchandise.getList().get(0).getMerchantClassifyId();
                monthHotSale = new MonthHotSale();
                monthHotSale.setMallClassifyId(mallClassifyId);
                monthHotSale.setMerchantClassifyId(merchantClassifyId);
                if (mallClassifyId > 0) {
                    Classify mallClassify = publicdataClient.getClassify(mallClassifyId);
                    monthHotSale.setMallBsid(mallClassify == null ? "" : mallClassify.getBsid());
                }
                if (merchantClassifyId > 0) {
                    Classify merchantClassify = publicdataClient.getClassify(merchantClassifyId);
                    monthHotSale.setMerchantBsid(merchantClassify == null ? "" : merchantClassify.getBsid());
                }
                monthHotSale.setMonth(calendar.get(Calendar.MONTH));
                monthHotSale.setYear(calendar.get(Calendar.YEAR));
                monthHotSale.setNumber(number);
                monthHotSale.setUnifiedMerchandiseId(unifiedMerchandiseId);
                monthHotSaleService.add(monthHotSale);
            }
        }
    }

    @Override
    public List<String> listMerchandiseImage(long unifiedMerchandiseId) {

        QUnifiedMerchandise unifiedMerchandise = getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertTrue(!MerchandiseType.COMBINATION.equals(unifiedMerchandise.getType()), "组合商品图不在这个接口提供." + unifiedMerchandiseId);
        MerchandiseItem merchandiseItem = merchandiseItemService.get(unifiedMerchandise.getList().get(0).getId());
        AssertUtil.assertNotNull(merchandiseItem, "单一商品不存在." + unifiedMerchandiseId);
        // 规格
        MerchandiseSpecifications merchandiseSpecifications = merchandiseSpecificationsService.get(merchandiseItem.getMerchandiseSpecificationsId());
        List<String> imageStrList = new ArrayList<String>();
        if (merchandiseSpecifications != null) {
            if (merchandiseSpecifications.getAttributeId0() > 0) {
                List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandiseItem.getMerchandiseId(), merchandiseSpecifications.getAttributeId0(), merchandiseSpecifications.getValue0());
                for (MerchandiseImage merchandiseImage : imageList) {
                    if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
                        String[] strs = merchandiseImage.getImage().split(",");
                        for (int index = 0; index < strs.length; index++) {
                            imageStrList.add(fileSDKClient.uidToUrl(strs[index]));
                        }
                    }
                }
            }
            if (merchandiseSpecifications.getAttributeId1() > 0) {
                List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandiseItem.getMerchandiseId(), merchandiseSpecifications.getAttributeId1(), merchandiseSpecifications.getValue1());
                for (MerchandiseImage merchandiseImage : imageList) {
                    if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
                        String[] strs = merchandiseImage.getImage().split(",");
                        for (int index = 0; index < strs.length; index++) {
                            imageStrList.add(fileSDKClient.uidToUrl(strs[index]));
                        }
                    }
                }
            }
            if (merchandiseSpecifications.getAttributeId2() > 0) {
                List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandiseItem.getMerchandiseId(), merchandiseSpecifications.getAttributeId2(), merchandiseSpecifications.getValue2());
                for (MerchandiseImage merchandiseImage : imageList) {
                    if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
                        String[] strs = merchandiseImage.getImage().split(",");
                        for (int index = 0; index < strs.length; index++) {
                            imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
                        }
                    }
                }
            }
        }
        // 为空则取默认图
        if (CollectionUtils.isEmpty(imageStrList)) {
            List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandiseItem.getMerchandiseId(), -1L, "-1");
            for (MerchandiseImage merchandiseImage : imageList) {
                if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
                    String[] strs = merchandiseImage.getImage().split(",");
                    for (int index = 0; index < strs.length; index++) {
                        imageStrList.add(fileSDKClient.uidToUrl(strs[index]));
                    }
                }
            }
        }
        return imageStrList;
    }

    @Override
    public QMerchandiseEvaluation getMerchandiseEvaluation(long evaluationId, long merchandiseId) {

        MerchandiseEvaluation merchandiseEvaluation = merchandiseEvaluationService.get(evaluationId, merchandiseId);
        Merchandise merchandise = merchandiseService.get(merchandiseId);
        return new MerchandiseEvaluationEntity(merchandiseEvaluation, merchandise);
    }

    @Override
    public List<QUnifiedMerchandise> randomUnifiedMerchandise(long merchantId, int number) {

        MerchandiseItemQuery merchandiseItemQuery = new MerchandiseItemQuery();
        merchandiseItemQuery.setQueryType(QueryType.HOT);
        merchandiseItemQuery.setOrderType(OrderType.DESC);
        merchandiseItemQuery.setQueryItemType(QueryItemType.M);
        if (merchantId != -1L) {
            merchandiseItemQuery.setMerchantId(merchantId);
        }
        Page<MerchandiseItem> page = merchandiseItemService.page(BeanUtils.transBean2Map(merchandiseItemQuery), 0, number);
        List<MerchandiseItem> merchandiseItemList = page.getData();
        List<QUnifiedMerchandise> list = new ArrayList<QUnifiedMerchandise>();
        for (MerchandiseItem merchandiseItem : merchandiseItemList) {
            list.add(getUnifiedMerchandise(merchandiseItem.getUnifiedMerchandiseId()));
        }
        return list;
    }

    @Override
    public boolean setVipDiscount(long userId, long unifiedMerchandiseId, double price) {

        MerchandiseItem merchandiseItem = merchandiseItemService.getByUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(merchandiseItem, "商品信息不存在." + unifiedMerchandiseId);
        MerchandiseVipDiscount merchandiseVipDiscount = merchandiseVipDiscountService.get(userId, merchandiseItem.getId());
        if (merchandiseVipDiscount == null) {
            merchandiseVipDiscount = new MerchandiseVipDiscount();
            merchandiseVipDiscount.setPrice(price);
            merchandiseVipDiscount.setMerchandiseItemId(merchandiseItem.getId());
            merchandiseVipDiscount.setUserId(userId);
            return merchandiseVipDiscountService.add(merchandiseVipDiscount);
        } else {
            merchandiseVipDiscount.setPrice(price);
            return merchandiseVipDiscountService.update(merchandiseVipDiscount);
        }
    }

    @Override
    public Double getVipDiscount(long userId, long unifiedMerchandiseId) {

        MerchandiseItem merchandiseItem = merchandiseItemService.getByUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(merchandiseItem, "商品信息不存在." + unifiedMerchandiseId);
        MerchandiseVipDiscount merchandiseVipDiscount = merchandiseVipDiscountService.get(userId, merchandiseItem.getId());
        if (merchandiseVipDiscount == null || merchandiseVipDiscount.getPrice() <= 0) {
            return merchandiseItem.getDiscount();
            // return 0.0;
        } else {
            // 这里也是,暂时不处理折扣
            return merchandiseVipDiscount.getPrice();
        }
    }

    @Override
    public Double statMinVipDiscount(Long unifiedMerchandiseId) {

        MerchandiseItem merchandiseItem = merchandiseItemService.getByUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(merchandiseItem, "商品信息不存在." + unifiedMerchandiseId);
        Double min = merchandiseVipDiscountService.statMin(merchandiseItem.getId());
        return min == null ? 0 : min;
    }

    @Override
    public Double statMaxVipDiscount(Long unifiedMerchandiseId) {

        MerchandiseItem merchandiseItem = merchandiseItemService.getByUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(merchandiseItem, "商品信息不存在." + unifiedMerchandiseId);
        Double max = merchandiseVipDiscountService.statMax(merchandiseItem.getId());
        return max == null ? 0 : max;
    }

    @Override
    public boolean clearUserVipDiscount(long userId) {

        return merchandiseVipDiscountService.deleteByUser(userId);
    }
}