package com.qcloud.component.orderform.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.service.SubOrderService;
import com.qcloud.component.orderform.web.handler.CollectOrderHandler;
import com.qcloud.component.orderform.web.vo.admin.AdminCollectOrderVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.core.json.Json;

@Component
public class CollectOrderHandlerImpl implements CollectOrderHandler {

    @Autowired
    PersonalcenterClient personalcenterClient;

    @Autowired
    SubOrderService      subOrderService;

    @Autowired
    SellercenterClient   sellercenterClient;

    // @Override
    // public List<CollectOrderVO> toVOList(List<CollectOrder> list){
    // List<CollectOrderVO> voList = new ArrayList<CollectOrderVO>();
    // for (CollectOrder collectOrder : list) {
    // voList.add(toVO(collectOrder));
    // }
    // return voList;
    // }
    //
    // @Override
    // public CollectOrderVO toVO(CollectOrder collectOrder){
    // String json = Json.toJson(collectOrder);
    // return Json.toObject(json, CollectOrderVO.class, true);
    //
    // }
    @Override
    public List<AdminCollectOrderVO> toVOList4Admin(List<CollectOrder> list) {

        List<AdminCollectOrderVO> voList = new ArrayList<AdminCollectOrderVO>();
        for (CollectOrder adminCollectOrder : list) {
            voList.add(toVO4Admin(adminCollectOrder));
        }
        return voList;
    }

    @Override
    public AdminCollectOrderVO toVO4Admin(CollectOrder collectOrder) {

        QUser user = personalcenterClient.getUser(collectOrder.getUserId());
        String json = Json.toJson(collectOrder);
        AdminCollectOrderVO vo = Json.toObject(json, AdminCollectOrderVO.class, true);
        vo.setCustomer(user.getName());
        String codeString = "";
        List<SubOrder> subOrderList = subOrderService.listByCollectOrder(vo.getId(), vo.getTime());
        for (SubOrder subOrder : subOrderList) {
            QMerchant merchant=sellercenterClient.getMerchant(subOrder.getMerchantId());
            codeString = codeString + merchant.getCode()+";";
        }
        vo.setCodeString(codeString);
        return vo;
    }
}
