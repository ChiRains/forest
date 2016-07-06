package com.qcloud.component.goods.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QMerchandiseEvaluation;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.goods.entity.MerchandiseEvaluationEntity;
import com.qcloud.component.goods.entity.UnifiedMerchandiseEntity;
import com.qcloud.component.goods.exception.CommoditycenterException;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.MerchandiseDealRecord;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.MerchandiseVipDiscount;
import com.qcloud.component.goods.model.MonthHotSale;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.MerchandiseStateType;
import com.qcloud.component.goods.model.key.TypeEnum.OrderType;
import com.qcloud.component.goods.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.goods.model.key.TypeEnum.QueryType;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
import com.qcloud.component.goods.service.CombinationMerchandiseItemService;
import com.qcloud.component.goods.service.MerchandiseDealRecordService;
import com.qcloud.component.goods.service.MerchandiseEvaluationService;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.goods.service.MerchandiseVipDiscountService;
import com.qcloud.component.goods.service.MonthHotSaleService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class CommoditycenterClientImpl implements CommoditycenterClient {

    @Autowired
    private UnifiedMerchandiseService         unifiedMerchandiseService;

    @Autowired
    private MerchandiseService                merchandiseService;

    @Autowired
    private MerchandiseSpecificationsService  merchandiseSpecificationsService;

    @Autowired
    private CombinationMerchandiseItemService combinationMerchandiseItemService;

    @Autowired
    private MerchandiseEvaluationService      merchandiseEvaluationService;

    @Autowired
    private MerchandiseVipDiscountService     merchandiseVipDiscountService;

    @Autowired
    private PublicdataClient                  publicdataClient;

    @Autowired
    private MerchandiseDealRecordService      merchandiseDealRecordService;

    @Autowired
    private MonthHotSaleService               monthHotSaleService;

    @Override
    public UnifiedMerchandiseEntity getUnifiedMerchandise(long unifiedMerchandiseId) {

        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(unifiedMerchandiseId);
        if (unifiedMerchandise == null) {
            return null;
        }
        if (unifiedMerchandise.getType() == UnifiedMerchandiseType.COMBINATION.getKey()) {
            List<CombinationMerchandiseItem> list = combinationMerchandiseItemService.listByCombinationMerchandise(unifiedMerchandise.getId());
            UnifiedMerchandiseEntity unifiedMerchandiseEntity = new UnifiedMerchandiseEntity(unifiedMerchandise, null);
            unifiedMerchandiseEntity.setNumber(1);
            unifiedMerchandiseEntity.setSpecifications("组合套餐");
            unifiedMerchandiseEntity.setType(UnifiedMerchandiseType.COMBINATION);
            List<QUnifiedMerchandise> relaList = new ArrayList<QUnifiedMerchandise>();
            for (CombinationMerchandiseItem combinationMerchandiseItem : list) {
                UnifiedMerchandiseEntity item = getUnifiedMerchandise(combinationMerchandiseItem.getRelaUnifiedMerchandiseId());
                AssertUtil.assertNotNull(item, "组合套餐关联商品记录不存在." + combinationMerchandiseItem.getRelaUnifiedMerchandiseId());
                AssertUtil.assertTrue(UnifiedMerchandiseType.SINGLE.equals(item.getType()), "组合套餐商品项只能是单品." + combinationMerchandiseItem.getRelaUnifiedMerchandiseId());
                item.setNumber(combinationMerchandiseItem.getNumber());
                item.setDiscount(combinationMerchandiseItem.getDiscount());
                relaList.add(item);
            }
            unifiedMerchandiseEntity.setList(relaList);
            return unifiedMerchandiseEntity;
        } else {
            Merchandise merchandise = merchandiseService.get(unifiedMerchandise.getMerchandiseId());
            UnifiedMerchandiseEntity unifiedMerchandiseEntity = new UnifiedMerchandiseEntity(unifiedMerchandise, merchandise);
            unifiedMerchandiseEntity.setNumber(1);
            unifiedMerchandiseEntity.setList(new ArrayList<QUnifiedMerchandise>());
            List<MerchandiseSpecifications> spList = merchandiseSpecificationsService.listByUnifiedMerchandise(unifiedMerchandise.getRelaUnifiedMerchandiseId());
            StringBuffer sb = new StringBuffer();
            for (MerchandiseSpecifications merchandiseSpecifications : spList) {
                sb.append(merchandiseSpecifications.getValue()).append(" ");
            }
            unifiedMerchandiseEntity.setSpecifications(sb.toString());
            unifiedMerchandiseEntity.setType(UnifiedMerchandiseType.Factory.get(unifiedMerchandise.getType()));
            return unifiedMerchandiseEntity;
        }
    }

    @Override
    public QMerchandiseEvaluation getMerchandiseEvaluation(long evaluationId, long merchandiseId) {

        MerchandiseEvaluation merchandiseEvaluation = merchandiseEvaluationService.get(evaluationId, merchandiseId);
        Merchandise merchandise = merchandiseService.get(merchandiseId);
        return new MerchandiseEvaluationEntity(merchandiseEvaluation, merchandise);
    }

    @Override
    public boolean updateOnlineStock(long unifiedMerchandiseId, int stock) {

        QUnifiedMerchandise unifiedMerchandise = getUnifiedMerchandise(unifiedMerchandiseId);
        // 更新库存,只能针对单一商品
        if (UnifiedMerchandiseType.SINGLE.equals(unifiedMerchandise.getType())) {
            int totalStock = unifiedMerchandise.getStock() + stock;
            if (totalStock < 0) {
                throw new CommoditycenterException("调整库存后库存不大于等于零,调整失败." + unifiedMerchandiseId);
            }
            return lockOnlineStock(unifiedMerchandiseId, 0 - stock);
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
        return unifiedMerchandiseService.lockStock(unifiedMerchandiseId, totalStock);
    }

    @Override
    public boolean addSalesVolume(long unifiedMerchandiseId, int number) {

        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "统一商品不存在." + unifiedMerchandiseId);
        return unifiedMerchandiseService.updateSalesVolume(unifiedMerchandiseId, number);
    }

    @Override
    public void incrementMerchandiseDealRecoed(long userId, long unifiedMerchandiseId, int number, String specifications, long[] orderIds) {

        AssertUtil.assertNotNull(orderIds, "订单ID不能为空.");
        AssertUtil.assertTrue(orderIds.length == 4, "订单ID数组长度不正确.");
        QUnifiedMerchandise unifiedMerchandise = getUnifiedMerchandise(unifiedMerchandiseId);
        UnifiedMerchandiseType merchandiseType = unifiedMerchandise.getType();
        if (UnifiedMerchandiseType.COMBINATION.equals(merchandiseType)) {
            List<QUnifiedMerchandise> list = unifiedMerchandise.getList();
            for (QUnifiedMerchandise merchandiseItem : list) {
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
        } else {
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
        }
        if (UnifiedMerchandiseType.SINGLE.equals(merchandiseType)) {
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
    public List<QUnifiedMerchandise> randomUnifiedMerchandise(long merchantId, int number) {

        UnifiedMerchandiseQuery unifiedMerchandiseQuery = new UnifiedMerchandiseQuery();
        unifiedMerchandiseQuery.setQueryType(QueryType.HOT);
        unifiedMerchandiseQuery.setOrderType(OrderType.DESC);
        unifiedMerchandiseQuery.setQueryItemType(QueryItemType.M);
        if (merchantId != -1L) {
            unifiedMerchandiseQuery.setMerchantId(merchantId);
        }
        Page<UnifiedMerchandise> page = unifiedMerchandiseService.page(unifiedMerchandiseQuery, 0, number);
        List<UnifiedMerchandise> merchandiseList = page.getData();
        List<QUnifiedMerchandise> list = new ArrayList<QUnifiedMerchandise>();
        for (UnifiedMerchandise unifiedMerchandise : merchandiseList) {
            list.add(getUnifiedMerchandise(unifiedMerchandise.getId()));
        }
        return list;
    }

    @Override
    public boolean setVipDiscount(long userId, long unifiedMerchandiseId, double discount) {

        MerchandiseVipDiscount merchandiseVipDiscount = merchandiseVipDiscountService.get(userId, unifiedMerchandiseId);
        if (merchandiseVipDiscount == null) {
            merchandiseVipDiscount = new MerchandiseVipDiscount();
            merchandiseVipDiscount.setPrice(discount);
            merchandiseVipDiscount.setMerchandiseItemId(unifiedMerchandiseId);
            merchandiseVipDiscount.setUserId(userId);
            return merchandiseVipDiscountService.add(merchandiseVipDiscount);
        } else {
            merchandiseVipDiscount.setPrice(discount);
            return merchandiseVipDiscountService.update(merchandiseVipDiscount);
        }
    }

    @Override
    public double getVipDiscount(long userId, long unifiedMerchandiseId) {

        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "统一商品信息不存在." + unifiedMerchandiseId);
        MerchandiseVipDiscount merchandiseVipDiscount = merchandiseVipDiscountService.get(userId, unifiedMerchandiseId);
        if (merchandiseVipDiscount == null || merchandiseVipDiscount.getPrice() <= 0) {
            return unifiedMerchandise.getDiscount();
            // return 0.0;
        } else {
            // 这里也是,暂时不处理折扣
            return merchandiseVipDiscount.getPrice();
        }
    }

    @Override
    public double statMinVipDiscount(long unifiedMerchandiseId) {

        Double min = merchandiseVipDiscountService.statMin(unifiedMerchandiseId);
        return min == null ? 0 : min;
    }

    @Override
    public double statMaxVipDiscount(long unifiedMerchandiseId) {

        Double max = merchandiseVipDiscountService.statMax(unifiedMerchandiseId);
        return max == null ? 0 : max;
    }

    @Override
    public boolean clearUserVipDiscount(long userId) {

        return merchandiseVipDiscountService.deleteByUser(userId);
    }

    @Override
    public long regUnifiedMerchandise(long unifiedMerchandiseId, int type, String image, double discount, int integral, int stock, long activityId) {

        UnifiedMerchandise rela = unifiedMerchandiseService.get(unifiedMerchandiseId);
        UnifiedMerchandise unifiedMerchandise = new UnifiedMerchandise();
        unifiedMerchandise.setActivityId(activityId);
        unifiedMerchandise.setBrandId(rela.getBrandId());
        unifiedMerchandise.setCanUseCoupon(EnableType.DISABLE.getKey());
        unifiedMerchandise.setClickRate(0);
        unifiedMerchandise.setCode(rela.getCode());
        unifiedMerchandise.setDiscount(discount);
        unifiedMerchandise.setGoodEvaluation(0);
        unifiedMerchandise.setImage(StringUtils.isEmpty(image) ? rela.getImage() : image);
        unifiedMerchandise.setIntegral(integral);
        unifiedMerchandise.setKeywords(rela.getKeywords());
        unifiedMerchandise.setLowEvaluation(0);
        unifiedMerchandise.setMallClassifyBsid(rela.getMallClassifyBsid());
        unifiedMerchandise.setMallClassifyId(rela.getMallClassifyId());
        unifiedMerchandise.setMerchandiseId(rela.getMerchandiseId());
        unifiedMerchandise.setMerchantClassifyBsid(rela.getMerchantClassifyBsid());
        unifiedMerchandise.setMerchantClassifyId(rela.getMerchantClassifyId());
        unifiedMerchandise.setMiddleEvaluation(0);
        unifiedMerchandise.setName(rela.getName());
        unifiedMerchandise.setOrder(0);
        unifiedMerchandise.setPrice(rela.getPrice());
        unifiedMerchandise.setPurchase(rela.getPurchase());
        unifiedMerchandise.setRecordTime(new Date());
        unifiedMerchandise.setRelaUnifiedMerchandiseId(rela.getId());
        unifiedMerchandise.setSalesVolume(0);
        unifiedMerchandise.setState(MerchandiseStateType.ONLINE.getKey());
        unifiedMerchandise.setStock(stock);
        unifiedMerchandise.setType(type);
        unifiedMerchandise.setUpdateTime(new Date());
        unifiedMerchandise.setVirtualSalesVolume(0);
        unifiedMerchandise.setMerchantId(rela.getMerchantId());
        unifiedMerchandiseService.add(unifiedMerchandise);
        return unifiedMerchandise.getId();
    }

    @Override
    public long regUnifiedMerchandise(QUnifiedMerchandise um, int type, String image, double discount, int integral, int stock, long activityId) {

        return regUnifiedMerchandise(um.getId(), type, image, discount, integral, stock, activityId);
    }
}
