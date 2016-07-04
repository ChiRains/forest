//package com.qcloud.component.sellercenter.web.handler.impl;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.publicdata.model.Classify;
//import com.qcloud.component.publicdata.util.ClassifyUtils;
//import com.qcloud.component.sellercenter.model.MerchantClassify;
//import com.qcloud.component.sellercenter.web.handler.MerchantClassifyHandler;
//import com.qcloud.component.sellercenter.web.vo.MerchantClassifyVO;
//import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantClassifyVO;
//import com.qcloud.pirates.core.json.Json;
//@Component
//public class MerchantClassifyHandlerImpl implements MerchantClassifyHandler {
//    @Override
//    public List<MerchantClassifyVO> toVOList(List<MerchantClassify> list) {
//        List<MerchantClassifyVO> voList = new ArrayList<MerchantClassifyVO>();
//        for (MerchantClassify merchantClassify : list) {
//            voList.add(toVO(merchantClassify));
//        }
//        return voList;
//    }
//
//    @Override
//    public MerchantClassifyVO toVO(MerchantClassify merchantClassify) {
//        String json = Json.toJson(merchantClassify);
//        return Json.toObject(json, MerchantClassifyVO.class, true);
//    }
//
//    @Override
//    public List<AdminMerchantClassifyVO> toVOList4Admin(List<MerchantClassify> list) {
//        List<AdminMerchantClassifyVO> voList = new ArrayList<AdminMerchantClassifyVO>();
//        for (MerchantClassify adminMerchantClassify : list) {
//            voList.add(toVO4Admin(adminMerchantClassify));
//        }
//        return voList;
//    }
//
//    @Override
//    public AdminMerchantClassifyVO toVO4Admin(MerchantClassify merchantClassify) {
//        String json = Json.toJson(merchantClassify);
//        return Json.toObject(json, AdminMerchantClassifyVO.class, true);
//    }
//
//    @Override
//    public List<AdminMerchantClassifyVO> toVOList4Admin(List<Classify> list, List<Long> keyList) {
//        List<AdminMerchantClassifyVO> voList = new ArrayList<AdminMerchantClassifyVO>();
//        for (Classify classify : list) {
//            AdminMerchantClassifyVO vo = new AdminMerchantClassifyVO();
//            vo.setClassify(classify.getName());
//            vo.setClassifyId(classify.getId());
//            vo.setChecked("");
//            for (Long key : keyList) {
//                if (key.longValue() == classify.getId()) {
//                    vo.setChecked("checked");
//                    break;
//                }
//            }
//            String path = ClassifyUtils.calculationPath(list, classify);
//            vo.setPath(path);
//            voList.add(vo);
//        }
//        return voList;
//    }
//}
