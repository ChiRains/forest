package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.my.model.DeliveryMode;
import com.qcloud.component.my.web.handler.DeliveryModeHandler;
import com.qcloud.component.my.web.vo.DeliveryModeVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class DeliveryModeHandlerImpl implements DeliveryModeHandler {

    @Override
    public List<DeliveryModeVO> toVOList(List<DeliveryMode> list) {

        List<DeliveryModeVO> voList = new ArrayList<DeliveryModeVO>();
        for (DeliveryMode deliveryMode : list) {
            voList.add(toVO(deliveryMode));
        }
        return voList;
    }

    @Override
    public DeliveryModeVO toVO(DeliveryMode deliveryMode) {

        String json = Json.toJson(deliveryMode);
        return Json.toObject(json, DeliveryModeVO.class, true);
    }
}
