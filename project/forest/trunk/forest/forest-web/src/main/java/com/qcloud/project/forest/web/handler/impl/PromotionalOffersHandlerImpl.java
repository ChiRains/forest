package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.model.PromotionalOffers;
import com.qcloud.project.forest.web.handler.PromotionalOffersHandler;
import com.qcloud.project.forest.web.vo.PromotionalOffersVO;
import com.qcloud.project.forest.web.vo.admin.AdminPromotionalOffersVO;

@Component
public class PromotionalOffersHandlerImpl implements PromotionalOffersHandler {

    @Override
    public List<PromotionalOffersVO> toVOList(List<UnifiedMerchandise> list) {

        List<PromotionalOffersVO> voList = new ArrayList<PromotionalOffersVO>();
        for (UnifiedMerchandise unifiedMerchandise : list) {
            voList.add(toVO(unifiedMerchandise));
        }
        return voList;
    }

    @Override
    public PromotionalOffersVO toVO(UnifiedMerchandise UnifiedMerchandise) {

        String json = Json.toJson(UnifiedMerchandise);
        return Json.toObject(json, PromotionalOffersVO.class, true);
    }

    @Override
    public List<AdminPromotionalOffersVO> toVOList4Admin(List<PromotionalOffers> list) {

        List<AdminPromotionalOffersVO> voList = new ArrayList<AdminPromotionalOffersVO>();
        for (PromotionalOffers adminPromotionalOffers : list) {
            voList.add(toVO4Admin(adminPromotionalOffers));
        }
        return voList;
    }

    @Override
    public AdminPromotionalOffersVO toVO4Admin(PromotionalOffers promotionalOffers) {

        String json = Json.toJson(promotionalOffers);
        return Json.toObject(json, AdminPromotionalOffersVO.class, true);
    }
}
