package com.qcloud.component.commoditycenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.commoditycenter.model.MonthHotSale;
import com.qcloud.component.commoditycenter.service.MerchandiseVipDiscountService;
import com.qcloud.component.commoditycenter.web.handler.MonthHotSaleHandler;
import com.qcloud.component.commoditycenter.web.vo.MonthHotSaleVO;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class MonthHotSaleHandlerImpl implements MonthHotSaleHandler {

    @Autowired
    private CommoditycenterClient         commoditycenterClient;

    @Autowired
    private FileSDKClient                 fileSDKClient;

    @Autowired
    private MerchandiseVipDiscountService merchandiseVipDiscountService;

    @Override
    public List<MonthHotSaleVO> toVOList(List<MonthHotSale> list) {

        List<MonthHotSaleVO> voList = new ArrayList<MonthHotSaleVO>();
        for (MonthHotSale monthHotSale : list) {
            voList.add(toVO(monthHotSale));
        }
        return voList;
    }

    @Override
    public MonthHotSaleVO toVO(MonthHotSale monthHotSale) {

        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(monthHotSale.getUnifiedMerchandiseId());
        AssertUtil.assertNotNull(unifiedMerchandise, "统一商品不存在." + monthHotSale.getUnifiedMerchandiseId());
        MonthHotSaleVO vo = new MonthHotSaleVO();
        vo.setImage(fileSDKClient.getFileServerUrl() + unifiedMerchandise.getImage());
        vo.setName(unifiedMerchandise.getName());
        vo.setUnifiedMerchandiseId(monthHotSale.getUnifiedMerchandiseId());
        vo.setDiscount(unifiedMerchandise.getDiscount());
        // vip 价格
        double minVip = merchandiseVipDiscountService.statMin(unifiedMerchandise.getList().get(0).getId());
        double maxVip = merchandiseVipDiscountService.statMax(unifiedMerchandise.getList().get(0).getId());
        vo.setMinVipDiscount(minVip);
        vo.setMaxVipDiscount(maxVip);
        return vo;
    }
}
