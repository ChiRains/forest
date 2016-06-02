package com.qcloud.component.warehouse.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.warehouse.service.StockStateService;
import com.qcloud.component.warehouse.web.handler.MerchandiseStockStateHandler;
import com.qcloud.component.warehouse.web.handler.StockStateHandler;
import com.qcloud.component.warehouse.model.MerchandiseStockState;
import com.qcloud.component.warehouse.model.StockState;
import com.qcloud.component.warehouse.web.vo.MerchandiseStockStateVO;
import com.qcloud.component.warehouse.web.vo.admin.AdminMerchandiseStockStateVO;

@Component
public class MerchandiseStockStateHandlerImpl implements MerchandiseStockStateHandler {

    @Autowired
    private StockStateService     stockStateService;

    @Autowired
    private StockStateHandler     stockStateHandler;

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Override
    public List<MerchandiseStockStateVO> toVOList(List<MerchandiseStockState> list) {

        List<MerchandiseStockStateVO> voList = new ArrayList<MerchandiseStockStateVO>();
        for (MerchandiseStockState merchandiseStockState : list) {
            voList.add(toVO(merchandiseStockState));
        }
        return voList;
    }

    @Override
    public MerchandiseStockStateVO toVO(MerchandiseStockState merchandiseStockState) {

        String json = Json.toJson(merchandiseStockState);
        return Json.toObject(json, MerchandiseStockStateVO.class, true);
    }

    @Override
    public List<AdminMerchandiseStockStateVO> toVOList4Admin(List<MerchandiseStockState> list) {

        List<AdminMerchandiseStockStateVO> voList = new ArrayList<AdminMerchandiseStockStateVO>();
        if (list == null) {
            list = new ArrayList<MerchandiseStockState>();
        }
        for (MerchandiseStockState mss : list) {
            AdminMerchandiseStockStateVO vo = toVO4Admin(mss);
            StockState stockState = stockStateService.get(mss.getStockStateId());
            vo.setAdminStockStateVO(stockStateHandler.toVO4Admin(stockState));
            vo.setqUnifiedMerchandise(commoditycenterClient.getUnifiedMerchandise(mss.getUnifiedMerchandiseId()));
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public AdminMerchandiseStockStateVO toVO4Admin(MerchandiseStockState merchandiseStockState) {

        String json = Json.toJson(merchandiseStockState);
        return Json.toObject(json, AdminMerchandiseStockStateVO.class, true);
    }
}
