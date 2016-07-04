//package com.qcloud.component.sellercenter.web.handler.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.sellercenter.model.MerchantPay;
//import com.qcloud.component.sellercenter.web.handler.MerchantPayHandler;
//import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantPayVO;
//import com.qcloud.pirates.core.json.Json;
//
//@Component
//public class MerchantPayHandlerImpl implements MerchantPayHandler {
//
//    @Override
//    public List<AdminMerchantPayVO> toVOList4Admin(List<MerchantPay> list) {
//
//        List<AdminMerchantPayVO> voList = new ArrayList<AdminMerchantPayVO>();
//        for (MerchantPay adminMerchantPay : list) {
//            voList.add(toVO4Admin(adminMerchantPay));
//        }
//        return voList;
//    }
//
//    @Override
//    public AdminMerchantPayVO toVO4Admin(MerchantPay merchantPay) {
//
//        String json = Json.toJson(merchantPay);
//        return Json.toObject(json, AdminMerchantPayVO.class, true);
//    }
//}
