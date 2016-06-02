package com.qcloud.component.marketing.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.web.handler.CouponItemsHandler;
import com.qcloud.component.marketing.web.vo.CouponItemsVO;
import com.qcloud.component.marketing.web.vo.admin.AdminCouponItemsVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class CouponItemsHandlerImpl implements CouponItemsHandler {

    @Override
    public List<CouponItemsVO> toVOList(List<CouponItems> list) {

        List<CouponItemsVO> voList = new ArrayList<CouponItemsVO>();
        for (CouponItems couponItems : list) {
            voList.add(toVO(couponItems));
        }
        return voList;
    }

    @Override
    public CouponItemsVO toVO(CouponItems couponItems) {

        String json = Json.toJson(couponItems);
        return Json.toObject(json, CouponItemsVO.class, true);
    }

    @Override
    public List<AdminCouponItemsVO> toVOList4Admin(List<CouponItems> list) {

        List<AdminCouponItemsVO> voList = new ArrayList<AdminCouponItemsVO>();
        for (CouponItems adminCouponItems : list) {
            voList.add(toVO4Admin(adminCouponItems));
        }
        return voList;
    }

    @Override
    public AdminCouponItemsVO toVO4Admin(CouponItems couponItems) {

        String json = Json.toJson(couponItems);
        return Json.toObject(json, AdminCouponItemsVO.class, true);
    }
}
