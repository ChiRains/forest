package com.qcloud.component.warehouse.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QMerchandiseItem;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.warehouse.web.handler.MerchandiseStockHandler;
import com.qcloud.component.warehouse.model.MerchandiseStock;
import com.qcloud.component.warehouse.web.vo.MerchandiseStockVO;
import com.qcloud.component.warehouse.web.vo.admin.AdminMerchandiseStockVO;

@Component
public class MerchandiseStockHandlerImpl implements MerchandiseStockHandler {

    @Autowired
    private CommoditycenterClient commoditycenterClient;

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
            QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(adminMerchandiseStock.getUnifiedMerchandiseId());
            vo.setPrice(qUnifiedMerchandise.getPrice());
            vo.setPurchase(qUnifiedMerchandise.getPurchase());
            vo.setDiscount(qUnifiedMerchandise.getDiscount());
            List<QMerchandiseItem> qMerchandiseItemList = qUnifiedMerchandise.getList();
            vo.setSpecifications(qMerchandiseItemList != null ? qUnifiedMerchandise.getList().get(0).getSpecifications() : "");
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
