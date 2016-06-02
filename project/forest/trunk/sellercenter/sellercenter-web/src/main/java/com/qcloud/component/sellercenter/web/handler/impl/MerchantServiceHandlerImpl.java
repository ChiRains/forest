package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.sellercenter.model.MerchantService;
import com.qcloud.component.sellercenter.web.handler.MerchantServiceHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantServiceVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class MerchantServiceHandlerImpl implements MerchantServiceHandler {

    @Override
    public List<AdminMerchantServiceVO> toVOList4Admin(List<MerchantService> list) {

        List<AdminMerchantServiceVO> voList = new ArrayList<AdminMerchantServiceVO>();
        for (MerchantService adminMerchantService : list) {
            voList.add(toVO4Admin(adminMerchantService));
        }
        return voList;
    }

    @Override
    public AdminMerchantServiceVO toVO4Admin(MerchantService merchantService) {

        String json = Json.toJson(merchantService);
        return Json.toObject(json, AdminMerchantServiceVO.class, true);
    }
}
