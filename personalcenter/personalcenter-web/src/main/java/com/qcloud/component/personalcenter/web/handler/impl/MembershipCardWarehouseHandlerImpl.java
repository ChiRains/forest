package com.qcloud.component.personalcenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.personalcenter.web.handler.MembershipCardWarehouseHandler;
import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.key.TypeEnum.MembershipCardWarehouseStateType;
import com.qcloud.component.personalcenter.web.vo.MembershipCardWarehouseVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMembershipCardWarehouseVO;

@Component
public class MembershipCardWarehouseHandlerImpl implements MembershipCardWarehouseHandler {

    @Override
    public List<MembershipCardWarehouseVO> toVOList(List<MembershipCardWarehouse> list) {

        List<MembershipCardWarehouseVO> voList = new ArrayList<MembershipCardWarehouseVO>();
        for (MembershipCardWarehouse membershipCardWarehouse : list) {
            voList.add(toVO(membershipCardWarehouse));
        }
        return voList;
    }

    @Override
    public MembershipCardWarehouseVO toVO(MembershipCardWarehouse membershipCardWarehouse) {

        String json = Json.toJson(membershipCardWarehouse);
        return Json.toObject(json, MembershipCardWarehouseVO.class, true);
    }

    @Override
    public List<AdminMembershipCardWarehouseVO> toVOList4Admin(List<MembershipCardWarehouse> list) {

        List<AdminMembershipCardWarehouseVO> voList = new ArrayList<AdminMembershipCardWarehouseVO>();
        for (MembershipCardWarehouse adminMembershipCardWarehouse : list) {
            voList.add(toVO4Admin(adminMembershipCardWarehouse));
        }
        return voList;
    }

    @Override
    public AdminMembershipCardWarehouseVO toVO4Admin(MembershipCardWarehouse membershipCardWarehouse) {

        String json = Json.toJson(membershipCardWarehouse);
        AdminMembershipCardWarehouseVO vo = Json.toObject(json, AdminMembershipCardWarehouseVO.class, true);
        if (membershipCardWarehouse.getState() == MembershipCardWarehouseStateType.NEW.getKey()) {
            vo.setStateStr(MembershipCardWarehouseStateType.NEW.getName());
        } else {
            vo.setStateStr(MembershipCardWarehouseStateType.ACTIVATE.getName());
        }
        return vo;
    }
}
