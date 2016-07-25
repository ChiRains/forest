package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.web.handler.MyCouponHandler;
import com.qcloud.component.my.web.vo.MyCouponVO;
import com.qcloud.component.my.web.vo.admin.AdminMyCouponVO;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;

@Component
public class MyCouponHandlerImpl implements MyCouponHandler {

    @Override
    public List<MyCouponVO> toVOList(List<MyCoupon> list) {

        List<MyCouponVO> voList = new ArrayList<MyCouponVO>();
        for (MyCoupon myCoupon : list) {
            voList.add(toVO(myCoupon));
        }
        return voList;
    }

    @Override
    public MyCouponVO toVO(MyCoupon myCoupon) {

        String json = Json.toJson(myCoupon);
        MyCouponVO vo = Json.toObject(json, MyCouponVO.class, true);
        vo.setMerchant(myCoupon.getMerchantId() > 0);
        vo.setValidDateStr(DateUtil.date2String(vo.getValidDate(), DateUtil.DATE_FORMAT_STRING));
        vo.setInvalidDateStr(DateUtil.date2String(vo.getInvalidDate(), DateUtil.DATE_FORMAT_STRING));
        vo.setExpire(DateUtil.before(myCoupon.getValidDate(), new Date()));
        vo.setOrderDateStr(DateUtil.date2String(myCoupon.getOrderDate(), DateUtil.DATE_FORMAT_STRING));
        // 状态
        return vo;
    }

    @Override
    public List<AdminMyCouponVO> toVOList4Admin(List<MyCoupon> list) {

        List<AdminMyCouponVO> voList = new ArrayList<AdminMyCouponVO>();
        for (MyCoupon adminMyCoupon : list) {
            voList.add(toVO4Admin(adminMyCoupon));
        }
        return voList;
    }

    @Override
    public AdminMyCouponVO toVO4Admin(MyCoupon myCoupon) {

        String json = Json.toJson(myCoupon);
        return Json.toObject(json, AdminMyCouponVO.class, true);
    }
}
