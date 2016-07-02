package com.qcloud.component.goods.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.goods.QUnifiedMerchandise.MerchandiseType;
import com.qcloud.component.goods.dao.MerchandiseItemCacheDao;
import com.qcloud.component.goods.model.CombinationMerchandise;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.model.MerchandiseMarketing;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.cache.MerchandiseItemCache;
import com.qcloud.component.goods.model.key.TypeEnum.MerchandiseIsIncludePost;
import com.qcloud.component.goods.model.key.TypeEnum.UnifiedMerchandiseType;
import com.qcloud.component.goods.service.CombinationMerchandiseItemService;
import com.qcloud.component.goods.service.CombinationMerchandiseService;
import com.qcloud.component.goods.service.MerchandiseItemCacheService;
import com.qcloud.component.goods.service.MerchandiseItemService;
import com.qcloud.component.goods.service.MerchandiseMarketingService;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class MerchandiseItemCacheServiceImpl implements MerchandiseItemCacheService {

    @Autowired
    private MerchandiseItemCacheDao           merchandiseItemCacheDao;

    @Autowired
    private UnifiedMerchandiseService         unifiedMerchandiseService;

    @Autowired
    private MerchandiseItemService            merchandiseItemService;

    @Autowired
    private MerchandiseService                merchandiseService;

    @Autowired
    private MerchandiseSpecificationsService  merchandiseSpecificationsService;

    @Autowired
    private CombinationMerchandiseService     combinationMerchandiseService;

    @Autowired
    private CombinationMerchandiseItemService combinationMerchandiseItemService;

    @Autowired
    private MerchandiseMarketingService       merchandiseMarketingService;

    @Override
    public List<MerchandiseItemCache> list(final Long unifiedMerchandiseId) {

        List<MerchandiseItemCache> result = new ArrayList<MerchandiseItemCache>();
        List<Long> list = merchandiseItemCacheDao.listMerchandiseItemIds(unifiedMerchandiseId);
        if (CollectionUtils.isEmpty(list)) {
            UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(unifiedMerchandiseId);
            AssertUtil.assertNotNull(unifiedMerchandise, "商品数据不完整,商品不存在:" + unifiedMerchandiseId);
            if (unifiedMerchandise.getType() == UnifiedMerchandiseType.SINGLE.getKey()) { // 单一商品
                // 统一商品
                MerchandiseItem merchandiseItem = merchandiseItemService.getByUnifiedMerchandise(unifiedMerchandiseId);
                AssertUtil.assertNotNull(merchandiseItem, "商品数据不完整,单一商品不存在:" + unifiedMerchandiseId);
                MerchandiseItemCache merchandiseItemCache = get(new MerchandiseItemGetter() {

                    @Override
                    public MerchandiseItem get() {

                        return merchandiseItemService.getByUnifiedMerchandise(unifiedMerchandiseId);
                    }
                }, merchandiseItem.getId(), unifiedMerchandiseId);
                merchandiseItemCache.setDiscount(merchandiseItem.getDiscount());
                merchandiseItemCache.setPurchase(merchandiseItem.getPurchase());
                merchandiseItemCache.setPrice(merchandiseItem.getPrice());
                merchandiseItemCache.setStock(merchandiseItem.getStock());
                merchandiseItemCache.setNumber(1);
                merchandiseItemCache.setGoodEvaluation(merchandiseItem.getGoodEvaluation());
                merchandiseItemCache.setMiddleEvaluation(merchandiseItem.getMiddleEvaluation());
                merchandiseItemCache.setLowEvaluation(merchandiseItem.getLowEvaluation());
                result.add(merchandiseItemCache);
            } else if (unifiedMerchandise.getType() == UnifiedMerchandiseType.COMBINAION.getKey()) {// 组合商品
                CombinationMerchandise combinationMerchandise = combinationMerchandiseService.getByUnifiedMerchandise(unifiedMerchandiseId);
                AssertUtil.assertNotNull(combinationMerchandise, "商品数据不完整,组合商品不存在:" + unifiedMerchandiseId);
                List<CombinationMerchandiseItem> itemList = combinationMerchandiseItemService.listByCombinationMerchandise(combinationMerchandise.getId());
                for (final CombinationMerchandiseItem combinationMerchandiseItem : itemList) {
                    MerchandiseItemCache merchandiseItemCache = get(new MerchandiseItemGetter() {

                        @Override
                        public MerchandiseItem get() {

                            return merchandiseItemService.get(combinationMerchandiseItem.getMerchandiseItemId());
                        }
                    }, combinationMerchandiseItem.getMerchandiseItemId(), unifiedMerchandiseId);
                    //
                    merchandiseItemCache.setNumber(combinationMerchandiseItem.getNum());
                    // merchandiseItemCache.setUnifiedMerchandiseId(unifiedMerchandiseId);
                    result.add(merchandiseItemCache);
                }
            } else if (unifiedMerchandise.getType() == UnifiedMerchandiseType.MARKETING.getKey()) {// 促销商品
                final MerchandiseMarketing merchandiseMarketing = merchandiseMarketingService.getByUnifiedMerchandise(unifiedMerchandiseId);
                AssertUtil.assertNotNull(merchandiseMarketing, "商品数据不完整,促销商品不存在:" + unifiedMerchandiseId);
                MerchandiseItemCache merchandiseItemCache = get(new MerchandiseItemGetter() {

                    @Override
                    public MerchandiseItem get() {

                        return merchandiseItemService.get(merchandiseMarketing.getMerchandiseItemId());
                    }
                }, merchandiseMarketing.getMerchandiseItemId(), unifiedMerchandiseId);
                merchandiseItemCache.setDiscount(merchandiseMarketing.getDiscount());
                merchandiseItemCache.setPurchase(merchandiseMarketing.getPurchase());
                merchandiseItemCache.setPrice(merchandiseMarketing.getPrice());
                merchandiseItemCache.setStock(merchandiseMarketing.getStock());
                merchandiseItemCache.setNumber(1);
                // 价格,库存在另处理
                result.add(merchandiseItemCache);
            }
        } else {
            for (final Long key : list) {
                MerchandiseItemCache merchandiseItemCache = get(new MerchandiseItemGetter() {

                    @Override
                    public MerchandiseItem get() {

                        return merchandiseItemService.get(key);
                    }
                }, key, unifiedMerchandiseId);
                result.add(merchandiseItemCache);
            }
        }
        return result;
    }

    private MerchandiseItemCache get(MerchandiseItemGetter getter, Long itemId, Long unifiedMerchandiseId) {

        MerchandiseItemCache merchandiseItemCache = merchandiseItemCacheDao.get(itemId);
        if (merchandiseItemCache == null) {
            // 统一商品
            merchandiseItemCache = new MerchandiseItemCache();
            // merchandiseItemCache.setUnifiedMerchandiseId(unifiedMerchandiseId);
            // 商品规格,价格,图片引用
            MerchandiseItem merchandiseItem = getter.get();
            AssertUtil.assertNotNull(merchandiseItem, "商品数据不完整,单一商品不存在:" + unifiedMerchandiseId);
            merchandiseItemCache.setId(merchandiseItem.getId());
            merchandiseItemCache.setMerchandiseSpecificationsId(merchandiseItem.getMerchandiseSpecificationsId());
            // 商品基本信息
            Merchandise merchandise = merchandiseService.get(merchandiseItem.getMerchandiseId());
            AssertUtil.assertNotNull(merchandise, "商品数据不完整,商品明细信息不存在:" + merchandiseItem.getMerchandiseId());
            merchandiseItemCache.setWeight(merchandise.getWeight());
            merchandiseItemCache.setMerchandiseId(merchandise.getId());
            merchandiseItemCache.setCode(merchandise.getCode());
            merchandiseItemCache.setImage(merchandise.getImage());
            merchandiseItemCache.setMallClassifyId(merchandise.getMallClassifyId());
            merchandiseItemCache.setMerchantClassifyId(merchandise.getMerchantClassifyId());
            merchandiseItemCache.setMerchantId(merchandise.getMerchantId());
            merchandiseItemCache.setName(merchandise.getName());
            merchandiseItemCache.setUnit(merchandise.getUnit());
            // 价格,库存
            merchandiseItemCache.setDiscount(merchandiseItem.getDiscount());
            merchandiseItemCache.setPurchase(merchandiseItem.getPurchase());
            merchandiseItemCache.setPrice(merchandiseItem.getPrice());
            merchandiseItemCache.setStock(merchandiseItem.getStock());
            // 商品是否包邮
            merchandiseItemCache.setIncludePost(merchandise.getIsIncludePost() == MerchandiseIsIncludePost.YES.getKey());
            merchandiseItemCache.setUnifiedMerchandiseId(merchandiseItem.getUnifiedMerchandiseId());
            // 规格
            MerchandiseSpecifications merchandiseSpecifications = merchandiseSpecificationsService.get(merchandiseItem.getMerchandiseSpecificationsId());
            if (merchandiseSpecifications == null) {
                merchandiseItemCache.setSpecifications("");
            } else {
                StringBuffer sb = new StringBuffer();
                if (merchandiseSpecifications.getAttributeId0() != -1) {
                    sb.append(merchandiseSpecifications.getValue0()).append(" ");
                }
                if (merchandiseSpecifications.getAttributeId1() != -1) {
                    sb.append(merchandiseSpecifications.getValue1()).append(" ");
                }
                if (merchandiseSpecifications.getAttributeId2() != -1) {
                    sb.append(merchandiseSpecifications.getValue2()).append(" ");
                }
                merchandiseItemCache.setSpecifications(sb.toString());
                // TODO state
            }
            boolean result = merchandiseItemCacheDao.add(merchandiseItemCache);
            if (!result) {
                // TODO: 干掉一半,按点击率,然后再加
                merchandiseItemCacheDao.add(merchandiseItemCache);
            }
        }
        return merchandiseItemCache;
    }

    @Override
    public boolean remove(Long unifiedMerchandiseId) {

        return merchandiseItemCacheDao.removeByUnifiedMerchandise(unifiedMerchandiseId);
    }
    private interface MerchandiseItemGetter {

        MerchandiseItem get();
    }
}
