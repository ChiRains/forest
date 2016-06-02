package com.qcloud.component.orderform.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.orderform.web.handler.ReturnOrderHandler;
import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.web.vo.ReturnOrderVO;
import com.qcloud.component.orderform.web.vo.admin.AdminReturnOrderVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;

@Component
public class ReturnOrderHandlerImpl implements ReturnOrderHandler {

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @Override
    public List<ReturnOrderVO> toVOList(List<ReturnOrder> list) {

        List<ReturnOrderVO> voList = new ArrayList<ReturnOrderVO>();
        for (ReturnOrder returnOrder : list) {
            voList.add(toVO(returnOrder));
        }
        return voList;
    }

    @Override
    public ReturnOrderVO toVO(ReturnOrder returnOrder) {

        String json = Json.toJson(returnOrder);
        return Json.toObject(json, ReturnOrderVO.class, true);
    }

    @Override
    public List<AdminReturnOrderVO> toVOList4Admin(List<ReturnOrder> list) {

        List<AdminReturnOrderVO> voList = new ArrayList<AdminReturnOrderVO>();
        for (ReturnOrder adminReturnOrder : list) {
            voList.add(toVO4Admin(adminReturnOrder));
        }
        return voList;
    }

    @Override
    public AdminReturnOrderVO toVO4Admin(ReturnOrder returnOrder) {

        String json = Json.toJson(returnOrder);
        AdminReturnOrderVO vo = Json.toObject(json, AdminReturnOrderVO.class, true);
        QUser qUser = personalcenterClient.getUser(vo.getUserId());
        vo.setUserName(qUser != null ? qUser.getMobile() : "");
        return vo;
    }
}
