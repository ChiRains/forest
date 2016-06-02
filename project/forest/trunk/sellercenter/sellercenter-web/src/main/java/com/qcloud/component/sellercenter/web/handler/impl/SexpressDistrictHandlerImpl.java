package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.sellercenter.model.SexpressDistrict;
import com.qcloud.component.sellercenter.web.handler.SexpressDistrictHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminSexpressDistrictVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class SexpressDistrictHandlerImpl implements SexpressDistrictHandler {

    @Override
    public List<AdminSexpressDistrictVO> toVOList4Admin(List<SexpressDistrict> list) {

        List<AdminSexpressDistrictVO> voList = new ArrayList<AdminSexpressDistrictVO>();
        for (SexpressDistrict adminSexpressDistrict : list) {
            voList.add(toVO4Admin(adminSexpressDistrict));
        }
        return voList;
    }

    @Override
    public AdminSexpressDistrictVO toVO4Admin(SexpressDistrict sexpressDistrict) {

        String json = Json.toJson(sexpressDistrict);
        return Json.toObject(json, AdminSexpressDistrictVO.class, true);
    }
}
