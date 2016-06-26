package com.qcloud.component.goods.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.goods.dao.UnifiedMerchandiseCacheDao;
import com.qcloud.component.goods.model.CombinationMerchandise;
import com.qcloud.component.goods.model.MerchandiseMarketing;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.cache.MerchandiseItemCache;
import com.qcloud.component.goods.model.cache.UnifiedMerchandiseCache;
import com.qcloud.component.goods.model.key.TypeEnum.UnifiedMerchandiseType;
import com.qcloud.component.goods.service.CombinationMerchandiseService;
import com.qcloud.component.goods.service.MerchandiseItemCacheService;
import com.qcloud.component.goods.service.MerchandiseMarketingService;
import com.qcloud.component.goods.service.UnifiedMerchandiseCacheService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class UnifiedMerchandiseCacheServiceServiveImpl implements UnifiedMerchandiseCacheService {

    @Autowired
    private UnifiedMerchandiseCacheDao    unifiedMerchandiseCacheDao;

    @Autowired
    private UnifiedMerchandiseService     unifiedMerchandiseService;

    @Autowired
    private MerchandiseItemCacheService   merchandiseItemCacheService;

    @Autowired
    private CombinationMerchandiseService combinationMerchandiseService;

    @Autowired
    private MerchandiseMarketingService   merchandiseMarketingService;

    @Override
    public UnifiedMerchandiseCache get(Long unifiedMerchandiseId) {

        UnifiedMerchandiseCache unifiedMerchandiseCache = unifiedMerchandiseCacheDao.get(unifiedMerchandiseId);
        if (unifiedMerchandiseCache == null) {
            unifiedMerchandiseCache = new UnifiedMerchandiseCache();
            unifiedMerchandiseCache.setId(unifiedMerchandiseId);
            UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(unifiedMerchandiseId);
            AssertUtil.assertNotNull(unifiedMerchandise, "商品数据不完整,商品不存在:" + unifiedMerchandiseId);
            unifiedMerchandiseCache.setType(unifiedMerchandise.getType());
            unifiedMerchandiseCache.setMerchantId(unifiedMerchandise.getMerchantId());
            if (unifiedMerchandise.getType() == UnifiedMerchandiseType.SINGLE.getKey()) { // 单一商品
                List<MerchandiseItemCache> itemList = merchandiseItemCacheService.list(unifiedMerchandiseId);
                AssertUtil.assertNotEmpty(itemList, "商品数据不完整,单一商品不存在:" + unifiedMerchandiseId);
                MerchandiseItemCache merchandiseItemCache = itemList.get(0);
                unifiedMerchandiseCache.setDiscount(merchandiseItemCache.getDiscount());
                unifiedMerchandiseCache.setPurchase(merchandiseItemCache.getPurchase());
                unifiedMerchandiseCache.setPrice(merchandiseItemCache.getPrice());
                unifiedMerchandiseCache.setName(merchandiseItemCache.getName());
                unifiedMerchandiseCache.setImage(merchandiseItemCache.getImage());
                unifiedMerchandiseCache.setStock(merchandiseItemCache.getStock());
                unifiedMerchandiseCache.setIncludePost(merchandiseItemCache.getIsIncludePost());
                unifiedMerchandiseCache.setSence(-1);
            } else if (unifiedMerchandise.getType() == UnifiedMerchandiseType.COMBINAION.getKey()) {// 组合
                CombinationMerchandise combinationMerchandise = combinationMerchandiseService.getByUnifiedMerchandise(unifiedMerchandiseId);
                AssertUtil.assertNotNull(combinationMerchandise, "商品数据不完整,组合商品不存在:" + unifiedMerchandiseId);
                unifiedMerchandiseCache.setDiscount(combinationMerchandise.getDiscount());
                unifiedMerchandiseCache.setPurchase(combinationMerchandise.getPurchase());
                unifiedMerchandiseCache.setPrice(combinationMerchandise.getPrice());
                unifiedMerchandiseCache.setName(combinationMerchandise.getName());
                unifiedMerchandiseCache.setImage(combinationMerchandise.getImage());
                unifiedMerchandiseCache.setStock(combinationMerchandise.getStock());
                // 组合都包邮. 不包邮的再加字段设置
                unifiedMerchandiseCache.setIncludePost(true);
                // 组合是不是包邮
                unifiedMerchandiseCache.setSence(-2);
            } else if (unifiedMerchandise.getType() == UnifiedMerchandiseType.MARKETING.getKey()) {
                List<MerchandiseItemCache> itemList = merchandiseItemCacheService.list(unifiedMerchandiseId);
                AssertUtil.assertNotEmpty(itemList, "商品数据不完整,单一商品不存在:" + unifiedMerchandiseId);
                MerchandiseItemCache merchandiseItemCache = itemList.get(0);
                unifiedMerchandiseCache.setDiscount(merchandiseItemCache.getDiscount());
                unifiedMerchandiseCache.setPurchase(merchandiseItemCache.getPurchase());
                unifiedMerchandiseCache.setPrice(merchandiseItemCache.getPrice());
                unifiedMerchandiseCache.setName(merchandiseItemCache.getName());
                unifiedMerchandiseCache.setImage(merchandiseItemCache.getImage());
                unifiedMerchandiseCache.setStock(merchandiseItemCache.getStock());
                MerchandiseMarketing merchandiseMarketing = merchandiseMarketingService.getByUnifiedMerchandise(unifiedMerchandiseId);
                unifiedMerchandiseCache.setSence(merchandiseMarketing.getSence());
                // TODO
                // unifiedMerchandiseCache.setIncludePost(merchandiseMarketing.getIsIncludePost());
                unifiedMerchandiseCache.setIncludePost(merchandiseItemCache.getIsIncludePost());
            }
            unifiedMerchandiseCacheDao.add(unifiedMerchandiseCache);
        }
        return unifiedMerchandiseCache;
    }

    @Override
    public boolean remove(Long unifiedMerchandiseId) {

        return unifiedMerchandiseCacheDao.remove(unifiedMerchandiseId);
    }
}
