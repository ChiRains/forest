package com.qcloud.component.marketing.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.marketing.web.handler.CouponHandler;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.web.vo.CouponVO;
import com.qcloud.component.marketing.web.vo.admin.AdminCouponVO;

@Component
public class CouponHandlerImpl implements CouponHandler {

    @Autowired
    private FileSDKClient fileSDKClient;

    @Override
    public List<CouponVO> toVOList(List<Coupon> list) {

        List<CouponVO> voList = new ArrayList<CouponVO>();
        for (Coupon coupon : list) {
            voList.add(toVO(coupon));
        }
        return voList;
    }

    @Override
    public CouponVO toVO(Coupon coupon) {

        String json = Json.toJson(coupon);
        CouponVO vo = Json.toObject(json, CouponVO.class, true);
        vo.setImage(fileSDKClient.getFileServerUrl() + coupon.getImage());
        vo.setStartDateStr(DateUtil.date2String(coupon.getStartDate(), DateUtil.DATE_FORMAT_STRING));
        vo.setEndDateStr(DateUtil.date2String(coupon.getEndDate(), DateUtil.DATE_FORMAT_STRING));
        vo.setValidDateStr(DateUtil.date2String(coupon.getValidDate(), DateUtil.DATE_FORMAT_STRING));
        return vo;
    }

    @Override
    public List<AdminCouponVO> toVOList4Admin(List<Coupon> list) {

        List<AdminCouponVO> voList = new ArrayList<AdminCouponVO>();
        for (Coupon adminCoupon : list) {
            voList.add(toVO4Admin(adminCoupon));
        }
        return voList;
    }

    @Override
    public AdminCouponVO toVO4Admin(Coupon coupon) {

        String json = Json.toJson(coupon);
        AdminCouponVO vo = Json.toObject(json, AdminCouponVO.class, true);
        vo.setImageUid(fileSDKClient.urlToUid(vo.getImage()));
        return vo;
    }
}
