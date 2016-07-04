package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.goods.web.handler.UnifiedMerchandiseHandler;
import com.qcloud.component.goods.web.vo.UnifiedMerchandiseVO;
import com.qcloud.component.goods.web.vo.admin.AdminUnifiedMerchandiseVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.core.json.Json;

@Component
public class UnifiedMerchandiseHandlerImpl implements UnifiedMerchandiseHandler {

    @Autowired
    private MerchandiseSpecificationsService merchandiseSpecificationsService;

    @Autowired
    private PublicdataClient                 publicdataClient;

    @Override
    public List<UnifiedMerchandiseVO> toVOList(List<UnifiedMerchandise> list) {

        List<UnifiedMerchandiseVO> voList = new ArrayList<UnifiedMerchandiseVO>();
        for (UnifiedMerchandise unifiedMerchandise : list) {
            voList.add(toVO(unifiedMerchandise));
        }
        return voList;
    }

    @Override
    public UnifiedMerchandiseVO toVO(UnifiedMerchandise unifiedMerchandise) {

        String json = Json.toJson(unifiedMerchandise);
        return Json.toObject(json, UnifiedMerchandiseVO.class, true);
    }

    @Override
    public List<AdminUnifiedMerchandiseVO> toVOList4Admin(List<UnifiedMerchandise> list) {

        List<AdminUnifiedMerchandiseVO> voList = new ArrayList<AdminUnifiedMerchandiseVO>();
        for (UnifiedMerchandise adminUnifiedMerchandise : list) {
            voList.add(toVO4Admin(adminUnifiedMerchandise));
        }
        return voList;
    }

    @Override
    public AdminUnifiedMerchandiseVO toVO4Admin(UnifiedMerchandise unifiedMerchandise) {

        String json = Json.toJson(unifiedMerchandise);
        AdminUnifiedMerchandiseVO merchandiseVO = Json.toObject(json, AdminUnifiedMerchandiseVO.class, true);
        List<MerchandiseSpecifications> specList = merchandiseSpecificationsService.listByUnifiedMerchandise(merchandiseVO.getId());
        // 规格
        String specifications = "";
        for (MerchandiseSpecifications merchandiseSpecifications : specList) {
            specifications += merchandiseSpecifications.getValue() + " ";
        }
        merchandiseVO.setSpecifications(specifications);
        //
        Classify mallClassify = publicdataClient.getClassify(merchandiseVO.getMallClassifyId());
        merchandiseVO.setMallClassifyStr(mallClassify == null ? "默认" : mallClassify.getName());
        Classify merchantClassify = publicdataClient.getClassify(merchandiseVO.getMerchantClassifyId());
        merchandiseVO.setMerchantClassifyStr(merchantClassify == null ? "默认" : merchantClassify.getName());
        return merchandiseVO;
    }
}
