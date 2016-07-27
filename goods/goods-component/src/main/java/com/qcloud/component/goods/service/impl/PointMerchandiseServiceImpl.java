package com.qcloud.component.goods.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.service.PointMerchandiseService;
import com.qcloud.pirates.util.AssertUtil;

@Service
public class PointMerchandiseServiceImpl implements PointMerchandiseService {

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Transactional
    @Override
    public boolean createList(List<UnifiedMerchandise> list) {

        for (UnifiedMerchandise merchandise : list) {
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandise.getId());
            AssertUtil.assertNotNull(unifiedMerchandise, "统一商品不存在,添加失败.");
            AssertUtil.assertTrue(unifiedMerchandise.getStock() >= merchandise.getStock(), "原商品库存不足:" + merchandise.getStock());
            AssertUtil.assertTrue(merchandise.getIntegral() > 0, unifiedMerchandise.getName() + ":" + unifiedMerchandise.getSpecifications() + ":积分兑换价必须大于0,不然会亏本哦.");
            AssertUtil.assertTrue(merchandise.getDiscount() >= 0, unifiedMerchandise.getName() + ":" + unifiedMerchandise.getSpecifications() + ":额外现金价必须大于或等于0哦");
            commoditycenterClient.regUnifiedMerchandise(unifiedMerchandise, PointMerchandiseService.unifiedMerchandise_type, "", merchandise.getDiscount(), (int) merchandise.getIntegral(), merchandise.getStock(), merchandise.getActivityId());
            commoditycenterClient.updateOnlineStock(unifiedMerchandise.getId(), merchandise.getStock());
        }
        return true;
    }
}
