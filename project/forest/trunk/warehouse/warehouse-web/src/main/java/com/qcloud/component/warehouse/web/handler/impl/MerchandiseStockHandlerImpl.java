package com.qcloud.component.warehouse.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.warehouse.model.MerchandiseStock;
import com.qcloud.component.warehouse.web.handler.MerchandiseStockHandler;
import com.qcloud.component.warehouse.web.vo.MerchandiseStockVO;
import com.qcloud.component.warehouse.web.vo.admin.AdminMerchandiseStockVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class MerchandiseStockHandlerImpl implements MerchandiseStockHandler {

    @Autowired
    private UnifiedMerchandiseService        unifiedMerchandiseService;

    @Autowired
    private MerchandiseSpecificationsService merchandiseSpecificationsService;

    @Override
    public List<MerchandiseStockVO> toVOList(List<MerchandiseStock> list) {

        List<MerchandiseStockVO> voList = new ArrayList<MerchandiseStockVO>();
        for (MerchandiseStock merchandiseStock : list) {
            voList.add(toVO(merchandiseStock));
        }
        return voList;
    }

    @Override
    public MerchandiseStockVO toVO(MerchandiseStock merchandiseStock) {

        String json = Json.toJson(merchandiseStock);
        return Json.toObject(json, MerchandiseStockVO.class, true);
    }

    @Override
    public List<AdminMerchandiseStockVO> toVOList4Admin(List<MerchandiseStock> list) {

        List<AdminMerchandiseStockVO> voList = new ArrayList<AdminMerchandiseStockVO>();
        for (MerchandiseStock adminMerchandiseStock : list) {
            AdminMerchandiseStockVO vo = toVO4Admin(adminMerchandiseStock);
            UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(adminMerchandiseStock.getUnifiedMerchandiseId());
            vo.setPrice(unifiedMerchandise.getPrice());
            vo.setPurchase(unifiedMerchandise.getPurchase());
            vo.setDiscount(unifiedMerchandise.getDiscount());
            List<MerchandiseSpecifications> msList = merchandiseSpecificationsService.listByUnifiedMerchandise(unifiedMerchandise.getId());
            StringBuffer sb = new StringBuffer();
            for (MerchandiseSpecifications merchandiseSpecifications : msList) {
                sb.append(merchandiseSpecifications.getValue()).append(" ");
            }
            vo.setSpecifications(sb.toString());
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public AdminMerchandiseStockVO toVO4Admin(MerchandiseStock merchandiseStock) {

        String json = Json.toJson(merchandiseStock);
        return Json.toObject(json, AdminMerchandiseStockVO.class, true);
    }
}
