package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.web.handler.GiftCouponUserHandler;
import com.qcloud.project.forest.web.vo.GiftCouponUserVO;
import com.qcloud.project.forest.web.vo.admin.AdminGiftCouponUserVO;

@Component
public class GiftCouponUserHandlerImpl implements GiftCouponUserHandler {

    @Autowired
    private FileSDKClient fileSDKClient;

    @Override
    public List<GiftCouponUserVO> toVOList(List<GiftCouponUser> list) {

        List<GiftCouponUserVO> voList = new ArrayList<GiftCouponUserVO>();
        for (GiftCouponUser giftCouponUser : list) {
            voList.add(toVO(giftCouponUser));
        }
        return voList;
    }

    @Override
    public GiftCouponUserVO toVO(GiftCouponUser giftCouponUser) {

        String json = Json.toJson(giftCouponUser);
        GiftCouponUserVO giftCouponUserVO = Json.toObject(json, GiftCouponUserVO.class, true);
        giftCouponUserVO.setValidDate(DateUtil.date2String(giftCouponUser.getValidDate(), "yyyy.MM.dd"));
        giftCouponUserVO.setInValidDate(DateUtil.date2String(giftCouponUser.getInValidDate(), "yyyy.MM.dd"));
        giftCouponUserVO.setImage(fileSDKClient.getFileServerUrl() + giftCouponUserVO.getImage());
        if (new Date().after(giftCouponUser.getInValidDate())) {
            giftCouponUserVO.setState(3);
        }
        return giftCouponUserVO;
    }

    @Override
    public List<AdminGiftCouponUserVO> toVOList4Admin(List<GiftCouponUser> list) {

        List<AdminGiftCouponUserVO> voList = new ArrayList<AdminGiftCouponUserVO>();
        for (GiftCouponUser adminGiftCouponUser : list) {
            voList.add(toVO4Admin(adminGiftCouponUser));
        }
        return voList;
    }

    @Override
    public AdminGiftCouponUserVO toVO4Admin(GiftCouponUser giftCouponUser) {

        String json = Json.toJson(giftCouponUser);
        return Json.toObject(json, AdminGiftCouponUserVO.class, true);
    }
}
