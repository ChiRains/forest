package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.sellercenter.model.StoreDeliveryRange;
import com.qcloud.component.sellercenter.web.handler.StoreDeliveryRangeHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminStoreDeliveryRangeVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class StoreDeliveryRangeHandlerImpl implements StoreDeliveryRangeHandler {

    @Override
    public List<AdminStoreDeliveryRangeVO> toVOList4Admin(List<StoreDeliveryRange> list) {

        List<AdminStoreDeliveryRangeVO> voList = new ArrayList<AdminStoreDeliveryRangeVO>();
        for (StoreDeliveryRange adminStoreDeliveryRange : list) {
            voList.add(toVO4Admin(adminStoreDeliveryRange));
        }
        return voList;
    }

    @Override
    public AdminStoreDeliveryRangeVO toVO4Admin(StoreDeliveryRange storeDeliveryRange) {

        String json = Json.toJson(storeDeliveryRange);
        return Json.toObject(json, AdminStoreDeliveryRangeVO.class, true);
    }
}
