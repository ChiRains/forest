package com.qcloud.component.sellercenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.model.key.TypeEnum.MerchantOrderStateType;
import com.qcloud.component.sellercenter.web.handler.MerchantOrderFormHandler;
import com.qcloud.component.sellercenter.web.vo.MerchantOrderFormVO;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantOrderFormVO;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;

@Component
public class MerchantOrderFormHandlerImpl implements MerchantOrderFormHandler {

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @Autowired
    private SellercenterClient   sellercenterClient;

    @Autowired(required = false)
    private OrderformClient      orderformClient;

    @Autowired
    private FileSDKClient        fileSDKClient;

    @Override
    public List<MerchantOrderFormVO> toVOList(List<MerchantOrderForm> list) {

        List<MerchantOrderFormVO> voList = new ArrayList<MerchantOrderFormVO>();
        for (MerchantOrderForm merchantOrderForm : list) {
            voList.add(toVO(merchantOrderForm));
        }
        return voList;
    }

    @Override
    public MerchantOrderFormVO toVO(MerchantOrderForm merchantOrderForm) {

        String json = Json.toJson(merchantOrderForm);
        MerchantOrderFormVO vo = Json.toObject(json, MerchantOrderFormVO.class, true);
        AssertUtil.assertNotNull(orderformClient, "订单接口尚未实例化.");
        QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(merchantOrderForm.getSubOrderId(), merchantOrderForm.getTime());
        vo.setOrderNumber(merchantOrder.getOrder().getOrderNumber());
        String stateStr = "";
        if (merchantOrderForm.getState() == MerchantOrderStateType.TOPAY.getKey()) {
            stateStr = "待付款";
        } else if (merchantOrderForm.getState() == MerchantOrderStateType.TO_PACKING.getKey()) {
            stateStr = "待配货";
        } else if (merchantOrderForm.getState() == MerchantOrderStateType.SHIP.getKey()) {
            stateStr = "待发货";
        }
        vo.setStateStr(stateStr);
        vo.setSum(merchantOrder.getSum());
        vo.setExplain(merchantOrder.getExplain());
        vo.setImage(fileSDKClient.getFileServerUrl() + merchantOrder.getOrderItemList().get(0).getImage());
        vo.setDateStr(DateUtil.date2String(merchantOrder.getOrder().getOrderDate(), DateUtil.DATE_FORMAT_STRING));
        return vo;
    }

    @Override
    public List<AdminMerchantOrderFormVO> toVOList4Admin(List<MerchantOrderForm> list) {

        List<AdminMerchantOrderFormVO> voList = new ArrayList<AdminMerchantOrderFormVO>();
        for (MerchantOrderForm adminMerchantOrderForm : list) {
            voList.add(toVO4Admin(adminMerchantOrderForm));
        }
        return voList;
    }

    @Override
    public AdminMerchantOrderFormVO toVO4Admin(MerchantOrderForm merchantOrderForm) {

        String json = Json.toJson(merchantOrderForm);
        AdminMerchantOrderFormVO vo = Json.toObject(json, AdminMerchantOrderFormVO.class, true);
        AssertUtil.assertNotNull(orderformClient, "订单接口尚未实例化.");
        QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(merchantOrderForm.getSubOrderId(), merchantOrderForm.getTime());
        // MerchantOrderFormModel merchantOrderFormModel = merchantOrderFormGetter.get();
        vo.setOrderNumber(merchantOrder.getOrder().getOrderNumber());
        vo.setSubOrderNumber(merchantOrder.getOrderNumber());
        vo.setUserId(merchantOrder.getOrder().getUserId());
        vo.setSum(merchantOrder.getSum());
        vo.setAddress(merchantOrder.getOrder().getAddress());
        vo.setMobile(merchantOrder.getOrder().getMobile());
        vo.setDeliveryTimeStr(merchantOrder.getDeliveryTimeStr());
        vo.setConsignee(merchantOrder.getOrder().getConsignee());
        vo.setExplain(merchantOrder.getExplain());
        vo.setStoreId(merchantOrder.getStoreId());
        QUser user = personalcenterClient.getUser(merchantOrder.getOrder().getUserId());
        vo.setUserName(user.getName());
        vo.setNickname(user.getNickname());
        vo.setPickupAddressStr(merchantOrder.getPickupAddressStr());
        vo.setStoreName(sellercenterClient.getStore(merchantOrder.getStoreId()).getName());
        vo.setNeedInvoiceType(merchantOrder.getOrder().getNeedInvoiceType());
        vo.setInvoiceType(merchantOrder.getOrder().getInvoiceType());
        for (MerchantOrderStateType stateType : MerchantOrderStateType.values()) {
            if (stateType.getKey() == merchantOrderForm.getState()) {
                vo.setStateStr(stateType.getName());
                break;
            }
        }
        vo.setState(merchantOrderForm.getState());
        vo.setCash(merchantOrder.getCash());
        return vo;
    }
}
