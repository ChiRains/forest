package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.project.forest.model.IntegralOrder;
import com.qcloud.project.forest.model.key.TypeEnum.IntegralOrderStateType;
import com.qcloud.project.forest.web.handler.IntegralOrderHandler;
import com.qcloud.project.forest.web.vo.IntegralOrderListVO;
import com.qcloud.project.forest.web.vo.IntegralOrderVO;
import com.qcloud.project.forest.web.vo.admin.AdminIntegralOrderVO;

@Component
public class IntegralOrderHandlerImpl implements IntegralOrderHandler {

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @Autowired
    private FileSDKClient        fileSDKClient;

    @Override
    public List<IntegralOrderVO> toVOList(List<IntegralOrder> list) {

        List<IntegralOrderVO> voList = new ArrayList<IntegralOrderVO>();
        for (IntegralOrder integralOrder : list) {
            voList.add(toVO(integralOrder));
        }
        return voList;
    }

    @Override
    public IntegralOrderVO toVO(IntegralOrder integralOrder) {

        String json = Json.toJson(integralOrder);
        IntegralOrderVO orderVO = Json.toObject(json, IntegralOrderVO.class, true);
        orderVO.setOrderDateStr(DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
        orderVO.setStateStr(IntegralOrderStateType.get(orderVO.getState()));
        orderVO.setImage(fileSDKClient.getFileServerUrl() + orderVO.getImage());
        orderVO.setOrderId(integralOrder.getId());
        orderVO.setCanRemind(integralOrder.getRemind() == 0);
        return orderVO;
    }

    @Override
    public List<AdminIntegralOrderVO> toVOList4Admin(List<IntegralOrder> list) {

        List<AdminIntegralOrderVO> voList = new ArrayList<AdminIntegralOrderVO>();
        for (IntegralOrder adminIntegralOrder : list) {
            voList.add(toVO4Admin(adminIntegralOrder));
        }
        return voList;
    }

    @Override
    public AdminIntegralOrderVO toVO4Admin(IntegralOrder integralOrder) {

        String json = Json.toJson(integralOrder);
        AdminIntegralOrderVO adminIntegralOrderVO = Json.toObject(json, AdminIntegralOrderVO.class, true);
        QUser user = personalcenterClient.getUser(adminIntegralOrderVO.getUserId());
        adminIntegralOrderVO.setUserName(user.getNickname());
        return adminIntegralOrderVO;
    }

    @Override
    public List<IntegralOrderListVO> toListVOList(List<IntegralOrder> list) {

        List<IntegralOrderListVO> voList = new ArrayList<IntegralOrderListVO>();
        for (IntegralOrder order : list) {
            String json = Json.toJson(order);
            IntegralOrderListVO orderVO = Json.toObject(json, IntegralOrderListVO.class, true);
            orderVO.setOrderId(order.getId());
            orderVO.setImage(fileSDKClient.getFileServerUrl() + orderVO.getImage());
            orderVO.setStateStr(IntegralOrderStateType.get(orderVO.getState()));
            orderVO.setCanRemind(order.getRemind() == 0);
            voList.add(orderVO);
        }
        return voList;
    }
}
