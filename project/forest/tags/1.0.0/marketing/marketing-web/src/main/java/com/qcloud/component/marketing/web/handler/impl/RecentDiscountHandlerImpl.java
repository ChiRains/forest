package com.qcloud.component.marketing.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.service.MerchandiseEvaluationService;
import com.qcloud.component.marketing.model.RecentDiscount;
import com.qcloud.component.marketing.web.handler.RecentDiscountHandler;
import com.qcloud.component.marketing.web.vo.RecentDiscountVO;
import com.qcloud.component.marketing.web.vo.admin.AdminRecentDiscountVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class RecentDiscountHandlerImpl implements RecentDiscountHandler {

    @Autowired
    private FileSDKClient         fileSDKClient;

    @Autowired
    private CommoditycenterClient commoditycenterClient;
    
    @Autowired
    private MerchandiseEvaluationService merchandiseEvaluationService;
    

    @Override
    public List<RecentDiscountVO> toVOList(List<RecentDiscount> list) {

        List<RecentDiscountVO> voList = new ArrayList<RecentDiscountVO>();
        for (RecentDiscount recentDiscount : list) {
            voList.add(toVO(recentDiscount));
        }
        return voList;
    }

    @Override
    public RecentDiscountVO toVO(RecentDiscount recentDiscount) {

        String json = Json.toJson(recentDiscount);
        RecentDiscountVO vo = Json.toObject(json, RecentDiscountVO.class, true);
        vo.setImage(fileSDKClient.getFileServerUrl() + vo.getImage());
        return vo;
    }

    @Override
    public List<AdminRecentDiscountVO> toVOList4Admin(List<RecentDiscount> list) {

        List<AdminRecentDiscountVO> voList = new ArrayList<AdminRecentDiscountVO>();
        for (RecentDiscount adminRecentDiscount : list) {
            voList.add(toVO4Admin(adminRecentDiscount));
        }
        return voList;
    }

    @Override
    public AdminRecentDiscountVO toVO4Admin(RecentDiscount recentDiscount) {

        String json = Json.toJson(recentDiscount);
        AdminRecentDiscountVO vo = Json.toObject(json, AdminRecentDiscountVO.class, true);
        vo.setImageUid(fileSDKClient.urlToUid(vo.getImage()));
        return vo;
    }
}
