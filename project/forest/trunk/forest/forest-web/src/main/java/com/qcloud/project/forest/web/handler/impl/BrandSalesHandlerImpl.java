package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.web.handler.BrandSalesHandler;
import com.qcloud.project.forest.web.vo.BrandSalesVO;
import com.qcloud.project.forest.web.vo.admin.AdminBrandSalesVO;

@Component
public class BrandSalesHandlerImpl implements BrandSalesHandler {

    @Override
    public List<BrandSalesVO> toVOList(List<UnifiedMerchandise> list) {

        List<BrandSalesVO> voList = new ArrayList<BrandSalesVO>();
        for (UnifiedMerchandise unifiedMerchandise : list) {
            voList.add(toVO(unifiedMerchandise));
        }
        return voList;
    }

    @Override
    public BrandSalesVO toVO(UnifiedMerchandise UnifiedMerchandise) {

        String json = Json.toJson(UnifiedMerchandise);
        return Json.toObject(json, BrandSalesVO.class, true);
    }

    @Override
    public List<AdminBrandSalesVO> toVOList4Admin(List<UnifiedMerchandise> list) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AdminBrandSalesVO toVO4Admin(UnifiedMerchandise unifiedMerchandise) {

        // TODO Auto-generated method stub
        return null;
    }
}
