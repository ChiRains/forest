package com.qcloud.component.commoditycenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.commoditycenter.model.MerchandiseBrowsingHistory;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseBrowsingHistoryHandler;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseBrowsingHistoryVO;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;

@Component
public class MerchandiseBrowsingHistoryHandlerImpl implements MerchandiseBrowsingHistoryHandler {

    @Autowired
    CommoditycenterClient commoditycenterClient;

    @Autowired
    FileSDKClient         fileSDKClient;

    @Override
    public List<MerchandiseBrowsingHistoryVO> toVOList(List<MerchandiseBrowsingHistory> list) {

        List<MerchandiseBrowsingHistoryVO> voList = new ArrayList<MerchandiseBrowsingHistoryVO>();
        for (MerchandiseBrowsingHistory merchandiseBrowsingHistory : list) {
            voList.add(toVO(merchandiseBrowsingHistory));
        }
        return voList;
    }

    @Override
    public MerchandiseBrowsingHistoryVO toVO(MerchandiseBrowsingHistory merchandiseBrowsingHistory) {

        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseBrowsingHistory.getUnifiedMerchandiseId());
        AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在." + merchandiseBrowsingHistory.getUnifiedMerchandiseId());
        MerchandiseBrowsingHistoryVO vo = new MerchandiseBrowsingHistoryVO();
        vo.setBrowsingTimeStr(DateUtil.date2String(merchandiseBrowsingHistory.getBrowsingTime()));
        String clientType = "";
        switch (merchandiseBrowsingHistory.getClientType()) {
        case 1:
            clientType = "微信";
            break;
        case 2:
            clientType = "Android";
            break;
        case 3:
            clientType = "IOS";
            break;
        case 4:
            clientType = "pc";
            break;
        }
        vo.setClientType(clientType);
        vo.setDiscount(unifiedMerchandise.getDiscount());
        vo.setImage(fileSDKClient.getFileServerUrl() + unifiedMerchandise.getImage());
        vo.setName(unifiedMerchandise.getName());
        vo.setPrice(unifiedMerchandise.getPrice());
        vo.setUnifiedMerchandiseId(merchandiseBrowsingHistory.getUnifiedMerchandiseId());
        vo.setSpecifications(unifiedMerchandise.getSpecifications());
        return vo;
    }
}
