package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.sellercenter.model.MerchantWealth;
import com.qcloud.component.sellercenter.web.handler.MerchantWealthHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantWealthVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class MerchantWealthHandlerImpl implements MerchantWealthHandler {

    @Override
    public List<AdminMerchantWealthVO> toVOList4Admin(List<MerchantWealth> list) {

        List<AdminMerchantWealthVO> voList = new ArrayList<AdminMerchantWealthVO>();
        for (MerchantWealth adminMerchantWealth : list) {
            voList.add(toVO4Admin(adminMerchantWealth));
        }
        return voList;
    }

    @Override
    public AdminMerchantWealthVO toVO4Admin(MerchantWealth merchantWealth) {

        String json = Json.toJson(merchantWealth);
        return Json.toObject(json, AdminMerchantWealthVO.class, true);
    }
}
