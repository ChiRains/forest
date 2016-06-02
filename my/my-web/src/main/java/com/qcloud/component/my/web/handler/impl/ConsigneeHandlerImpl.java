package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.my.model.Consignee;
import com.qcloud.component.my.web.handler.ConsigneeHandler;
import com.qcloud.component.my.web.vo.ConsigneeVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class ConsigneeHandlerImpl implements ConsigneeHandler {

    @Override
    public List<ConsigneeVO> toVOList(List<Consignee> list) {

        List<ConsigneeVO> voList = new ArrayList<ConsigneeVO>();
        for (Consignee consignee : list) {
            voList.add(toVO(consignee));
        }
        return voList;
    }

    @Override
    public ConsigneeVO toVO(Consignee consignee) {

        String json = Json.toJson(consignee);
        ConsigneeVO vo = Json.toObject(json, ConsigneeVO.class, true);
        // vo.setAddress(StringUtil.nullToEmpty(consignee.getProvince()) + StringUtil.nullToEmpty(consignee.getCity()) + StringUtil.nullToEmpty(consignee.getDistrict()) + StringUtil.nullToEmpty(consignee.getAddress()));
        return vo;
    }
}
