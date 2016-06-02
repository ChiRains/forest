package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.sellercenter.model.MerchantConsumptionDetail;
import com.qcloud.component.sellercenter.web.handler.MerchantConsumptionDetailHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantConsumptionDetailVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class MerchantConsumptionDetailHandlerImpl implements MerchantConsumptionDetailHandler {

    @Override
    public List<AdminMerchantConsumptionDetailVO> toVOList4Admin(List<MerchantConsumptionDetail> list) {

        List<AdminMerchantConsumptionDetailVO> voList = new ArrayList<AdminMerchantConsumptionDetailVO>();
        for (MerchantConsumptionDetail adminMerchantConsumptionDetail : list) {
            voList.add(toVO4Admin(adminMerchantConsumptionDetail));
        }
        return voList;
    }

    @Override
    public AdminMerchantConsumptionDetailVO toVO4Admin(MerchantConsumptionDetail merchantConsumptionDetail) {

        String json = Json.toJson(merchantConsumptionDetail);
        return Json.toObject(json, AdminMerchantConsumptionDetailVO.class, true);
    }
}
