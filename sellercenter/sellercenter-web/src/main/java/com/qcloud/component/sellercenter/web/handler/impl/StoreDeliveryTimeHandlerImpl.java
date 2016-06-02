package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.sellercenter.model.StoreDeliveryTime;
import com.qcloud.component.sellercenter.web.handler.StoreDeliveryTimeHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminStoreDeliveryTimeVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class StoreDeliveryTimeHandlerImpl implements StoreDeliveryTimeHandler {

    @Override
    public List<AdminStoreDeliveryTimeVO> toVOList4Admin(List<StoreDeliveryTime> list) {

        List<AdminStoreDeliveryTimeVO> voList = new ArrayList<AdminStoreDeliveryTimeVO>();
        for (StoreDeliveryTime adminStoreDeliveryTime : list) {
            voList.add(toVO4Admin(adminStoreDeliveryTime));
        }
        return voList;
    }

    @Override
    public AdminStoreDeliveryTimeVO toVO4Admin(StoreDeliveryTime storeDeliveryTime) {

        String json = Json.toJson(storeDeliveryTime);
        return Json.toObject(json, AdminStoreDeliveryTimeVO.class, true);
    }
}
