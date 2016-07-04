//package com.qcloud.component.sellercenter.web.handler.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.sellercenter.model.MerchantWealthDetail;
//import com.qcloud.component.sellercenter.web.handler.MerchantWealthDetailHandler;
//import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantWealthDetailVO;
//import com.qcloud.pirates.core.json.Json;
//
//@Component
//public class MerchantWealthDetailHandlerImpl implements MerchantWealthDetailHandler {
//
//    @Override
//    public List<AdminMerchantWealthDetailVO> toVOList4Admin(List<MerchantWealthDetail> list) {
//
//        List<AdminMerchantWealthDetailVO> voList = new ArrayList<AdminMerchantWealthDetailVO>();
//        for (MerchantWealthDetail adminMerchantWealthDetail : list) {
//            voList.add(toVO4Admin(adminMerchantWealthDetail));
//        }
//        return voList;
//    }
//
//    @Override
//    public AdminMerchantWealthDetailVO toVO4Admin(MerchantWealthDetail merchantWealthDetail) {
//
//        String json = Json.toJson(merchantWealthDetail);
//        return Json.toObject(json, AdminMerchantWealthDetailVO.class, true);
//    }
//}
