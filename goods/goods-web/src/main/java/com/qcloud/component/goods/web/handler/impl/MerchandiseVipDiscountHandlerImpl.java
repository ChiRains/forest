package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.model.MerchandiseVipDiscount;
import com.qcloud.component.goods.service.MerchandiseItemService;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.web.handler.MerchandiseVipDiscountHandler;
import com.qcloud.component.goods.web.vo.MerchandiseVipDiscountVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseVipDiscountVO;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;

@Component
public class MerchandiseVipDiscountHandlerImpl implements MerchandiseVipDiscountHandler {

    @Autowired
    private MerchandiseItemService merchandiseItemService;

    @Autowired
    private MerchandiseService     merchandiseService;

    @Override
    public List<MerchandiseVipDiscountVO> toVOList(List<MerchandiseVipDiscount> list) {

        List<MerchandiseVipDiscountVO> voList = new ArrayList<MerchandiseVipDiscountVO>();
        for (MerchandiseVipDiscount merchandiseVipDiscount : list) {
            voList.add(toVO(merchandiseVipDiscount));
        }
        return voList;
    }

    @Override
    public MerchandiseVipDiscountVO toVO(MerchandiseVipDiscount merchandiseVipDiscount) {

        MerchandiseItem merchandiseItem = merchandiseItemService.get(merchandiseVipDiscount.getMerchandiseItemId());
        AssertUtil.assertNotNull(merchandiseItem, "商品单品不存在." + merchandiseVipDiscount.getMerchandiseItemId());
        Merchandise merchandise = merchandiseService.get(merchandiseItem.getMerchandiseId());
        merchandiseVipDiscount.getMerchandiseItemId();
        MerchandiseVipDiscountVO merchandiseVipDiscountVO = new MerchandiseVipDiscountVO();
        merchandiseVipDiscountVO.setDiscount(merchandiseItem.getDiscount());
        merchandiseVipDiscountVO.setVipDiscount(merchandiseVipDiscount.getPrice());
        merchandiseVipDiscountVO.setName(merchandiseItem.getName());
        merchandiseVipDiscountVO.setUnit(merchandise.getUnit());
        if (merchandiseVipDiscountVO.getDiscount() > 0) {
            merchandiseVipDiscountVO.setDiscountRate(NumberUtil.scale(merchandiseVipDiscountVO.getVipDiscount() / merchandiseVipDiscountVO.getDiscount(), 2));
        } else {
            merchandiseVipDiscountVO.setDiscountRate(1);
        }
        return merchandiseVipDiscountVO;
    }

    @Override
    public List<AdminMerchandiseVipDiscountVO> toVOList4Admin(List<MerchandiseVipDiscount> list) {

        List<AdminMerchandiseVipDiscountVO> voList = new ArrayList<AdminMerchandiseVipDiscountVO>();
        for (MerchandiseVipDiscount adminMerchandiseVipDiscount : list) {
            voList.add(toVO4Admin(adminMerchandiseVipDiscount));
        }
        return voList;
    }

    @Override
    public AdminMerchandiseVipDiscountVO toVO4Admin(MerchandiseVipDiscount merchandiseVipDiscount) {

        String json = Json.toJson(merchandiseVipDiscount);
        return Json.toObject(json, AdminMerchandiseVipDiscountVO.class, true);
    }
}
