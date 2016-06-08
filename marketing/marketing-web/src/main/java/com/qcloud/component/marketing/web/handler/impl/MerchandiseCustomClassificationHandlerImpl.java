package com.qcloud.component.marketing.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.OutdatedCommoditycenterClient;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.marketing.model.MerchandiseCustomClassification;
import com.qcloud.component.marketing.web.handler.MerchandiseCustomClassificationHandler;
import com.qcloud.component.marketing.web.vo.MerchandiseCustomClassificationVO;
import com.qcloud.component.marketing.web.vo.admin.AdminMerchandiseCustomClassificationVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class MerchandiseCustomClassificationHandlerImpl implements MerchandiseCustomClassificationHandler {

    @Autowired
    private CommoditycenterClient         commoditycenterClient;

    @Autowired
    private OutdatedCommoditycenterClient outdatedCommoditycenterClient;

    @Autowired
    private FileSDKClient                 fileSDKClient;

    @Override
    public List<MerchandiseCustomClassificationVO> toVOList(List<MerchandiseCustomClassification> list) {

        List<MerchandiseCustomClassificationVO> voList = new ArrayList<MerchandiseCustomClassificationVO>();
        for (MerchandiseCustomClassification merchandiseCustomClassification : list) {
            voList.add(toVO(merchandiseCustomClassification));
        }
        return voList;
    }

    @Override
    public MerchandiseCustomClassificationVO toVO(MerchandiseCustomClassification merchandiseCustomClassification) {

        String json = Json.toJson(merchandiseCustomClassification);
        MerchandiseCustomClassificationVO vo = Json.toObject(json, MerchandiseCustomClassificationVO.class, true);
        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseCustomClassification.getUnifiedMerchandiseId());
        vo.setDiscount(unifiedMerchandise.getDiscount());
        vo.setPrice(unifiedMerchandise.getPrice());
        vo.setMerchantId(unifiedMerchandise.getMerchantId());
        vo.setUnifiedMerchandiseId(unifiedMerchandise.getId());
        vo.setMerchandiseId(unifiedMerchandise.getList().get(0).getMerchandiseId());
        vo.setName(unifiedMerchandise.getList().get(0).getName());
        vo.setImage(fileSDKClient.getFileServerUrl() + unifiedMerchandise.getList().get(0).getImage());
        return vo;
    }

    @Override
    public List<AdminMerchandiseCustomClassificationVO> toVOList4Admin(List<MerchandiseCustomClassification> mcList) {

        List<AdminMerchandiseCustomClassificationVO> voList = new ArrayList<AdminMerchandiseCustomClassificationVO>();
        for (MerchandiseCustomClassification mc : mcList) {
            // AdminMerchandiseCustomClassificationVO vo = new AdminMerchandiseCustomClassificationVO();
            // vo.setId(mc.getId());
            // vo.setMerchantId(mc.getMerchantId());
            // vo.setUnifiedMerchandiseId(mc.getUnifiedMerchandiseId());
            // vo.setCustomClassifyId(mc.getCustomClassifyId());
            // vo.setOrderNum(mc.getOrderNum());
            // QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(mc.getUnifiedMerchandiseId());
            // vo.setqUnifiedMerchandise(qUnifiedMerchandise);
            // voList.add(vo);
            AdminMerchandiseCustomClassificationVO vo = new AdminMerchandiseCustomClassificationVO();
            QUnifiedMerchandise qmerchandise = commoditycenterClient.getUnifiedMerchandise(mc.getUnifiedMerchandiseId());
            Merchandise merchandise = outdatedCommoditycenterClient.getMerchandise(qmerchandise.getList().get(0).getMerchandiseId());
            if (merchandise != null) {
                vo.setImage(merchandise.getImage() == null ? "" : merchandise.getImage());
                // vo.setName(merchandise.getName());
            }
            // vo.setName(qmerchandise.getList().get(0).getName());
            vo.setStock(qmerchandise.getStock());
            vo.setId(mc.getId());
            vo.setMerchantId(mc.getMerchantId());
            vo.setUnifiedMerchandiseId(mc.getUnifiedMerchandiseId());
            vo.setCustomClassifyId(mc.getCustomClassifyId());
            vo.setOrderNum(mc.getOrderNum());
            vo.setSpecifications(qmerchandise.getList().get(0).getSpecifications());
            vo.setName(mc.getName());
            vo.setSysCode(mc.getSysCode());
            vo.setqUnifiedMerchandise(qmerchandise);
            voList.add(vo);
            // voList.add(toVO4Admin(mc));
        }
        return voList;
    }

    @Override
    public AdminMerchandiseCustomClassificationVO toVO4Admin(MerchandiseCustomClassification merchandiseCustomClassification) {

        String json = Json.toJson(merchandiseCustomClassification);
        AdminMerchandiseCustomClassificationVO vo = Json.toObject(json, AdminMerchandiseCustomClassificationVO.class, true);
        return vo;
    }
}
