package com.qcloud.component.orderform.web.handler.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.my.AfterSaleType;
import com.qcloud.component.orderform.QAfterSaleDetail;
import com.qcloud.component.orderform.QAfterSaleItem;
import com.qcloud.component.orderform.QAfterSaleOrder;
import com.qcloud.component.orderform.QAfterSaleOrderItem;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.orderform.QOrderItemDetail;
import com.qcloud.component.orderform.engine.AfterSaleSelecterService;
import com.qcloud.component.orderform.entity.AfterSaleOrder;
import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrder;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.web.handler.AfterSaleHandler;
import com.qcloud.component.orderform.web.vo.AfterSaleInfoDetailsVO;
import com.qcloud.component.orderform.web.vo.AfterSaleInfoMerchandiseVO;
import com.qcloud.component.orderform.web.vo.AfterSaleInfoMessage;
import com.qcloud.component.orderform.web.vo.AfterSaleOrderItemVO;
import com.qcloud.component.orderform.web.vo.personal.AfterSaleItemVO;
import com.qcloud.component.orderform.web.vo.personal.AfterSaleVO;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;

@Component
public class AfterSaleHandlerImpl implements AfterSaleHandler {

    @Autowired
    private SellercenterClient sellercenterClient;

    @Autowired
    private FileSDKClient      fileSDKClient;

    @Autowired
    private ParameterClient    parameterClient;

    @Override
    public List<AfterSaleVO> toVOList(List<QAfterSaleOrder> list) {

        List<AfterSaleVO> voList = new ArrayList<AfterSaleVO>();
        for (QAfterSaleOrder afterSaleOrder : list) {
            voList.add(toVO(afterSaleOrder));
        }
        return voList;
    }

    @Override
    public AfterSaleVO toVO(QAfterSaleOrder afterSaleOrder) {

        AfterSaleVO afterSaleVO = new AfterSaleVO();
        //
        afterSaleVO.setAfterSaleOrderNumber(afterSaleOrder.getAfterSaleOrderNumber());
        afterSaleVO.setExplain(afterSaleOrder.getExplain());
        afterSaleVO.setReason(afterSaleOrder.getReason());
        afterSaleVO.setState(afterSaleOrder.getState());
        afterSaleVO.setSum(afterSaleOrder.getSum());
        afterSaleVO.setTimeStr(DateUtil.date2String(afterSaleOrder.getTime()));
        afterSaleVO.setType(afterSaleOrder.getAfterSaleType().ordinal() + 1);
        QMerchant merchant = sellercenterClient.getMerchant(afterSaleOrder.getMerchantId());
        afterSaleVO.setMerchantName(merchant == null ? "" : merchant.getName());
        afterSaleVO.setItemList(toItemVOList(afterSaleOrder.listItem()));
        afterSaleVO.setId(afterSaleOrder.getAfterSaleId());
        if (afterSaleOrder instanceof ExchangeAfterSaleOrder) {
            ExchangeAfterSaleOrder exOrder = (ExchangeAfterSaleOrder) afterSaleOrder;
            afterSaleVO.setLogisticsCompany(exOrder.getExchangeOrder().getMerchantLogisticsCompany());
            afterSaleVO.setLogisticsNumber(exOrder.getExchangeOrder().getMerchantLogisticsNumber());
        } else {
            afterSaleVO.setLogisticsCompany("");
            afterSaleVO.setLogisticsNumber("");
        }
        return afterSaleVO;
    }

    private List<AfterSaleItemVO> toItemVOList(List<QAfterSaleOrderItem> list) {

        List<AfterSaleItemVO> voList = new ArrayList<AfterSaleItemVO>();
        for (QAfterSaleOrderItem afterSaleOrderItem : list) {
            voList.add(toItemVO(afterSaleOrderItem));
        }
        return voList;
    }

    private AfterSaleItemVO toItemVO(QAfterSaleOrderItem orderItem) {

        if (orderItem instanceof QAfterSaleItem) {
            QAfterSaleItem item = (QAfterSaleItem) orderItem;
            QOrderItem detail = item.getOrderItem();
            AfterSaleItemVO vo = new AfterSaleItemVO();
            vo.setExplain(item.getExplain());
            vo.setReason(item.getReason());
            vo.setImage(fileSDKClient.getFileServerUrl() + detail.getImage());
            vo.setName(detail.getName());
            vo.setNumber(item.getNumber());
            vo.setSpecifications(detail.getSpecifications());
            return vo;
        } else if (orderItem instanceof QAfterSaleDetail) {
            QAfterSaleDetail item = (QAfterSaleDetail) orderItem;
            QOrderItemDetail detail = item.getOrderItemDetail();
            AfterSaleItemVO vo = new AfterSaleItemVO();
            vo.setExplain(item.getExplain());
            vo.setReason(item.getReason());
            vo.setImage(fileSDKClient.getFileServerUrl() + detail.getImage());
            vo.setName(detail.getName());
            vo.setNumber(item.getNumber());
            vo.setSpecifications(detail.getSpecifications());
            return vo;
        }
        AssertUtil.assertTrue(false, "程序出错." + orderItem);
        return null;
    }

    @Autowired
    AfterSaleSelecterService afterSaleSelecterService;

    @Override
    public List<AfterSaleOrderItemVO> toVOList(MerchantOrderEntity merchantOrderEntity) {

        List<AfterSaleOrderItemVO> voList = new ArrayList<AfterSaleOrderItemVO>();
        List<QOrderItem> list = merchantOrderEntity.getOrderItemList();
        List<AfterSaleOrder> afterSaleList = afterSaleSelecterService.listAfterSaleOrder(merchantOrderEntity);
        for (QOrderItem qOrderItem : list) {
            AfterSaleOrderItemVO afterSaleOrderItemVO = getAfterSaleOrderItem(qOrderItem, afterSaleList);
            if (afterSaleOrderItemVO != null) {
                voList.add(afterSaleOrderItemVO);
            }
        }
        return voList;
    }

    private AfterSaleOrderItemVO getAfterSaleOrderItem(QOrderItem qOrderItem, List<AfterSaleOrder> afterSaleList) {

        AfterSaleOrderItemVO afterSaleOrderItemVO = null;
        int number = 0;
        for (AfterSaleOrder afterSaleOrder : afterSaleList) {
            if (AfterSaleType.EXCHANGE.getKey() == afterSaleOrder.getAfterSaleType().getKey() || AfterSaleType.RETURN.getKey() == afterSaleOrder.getAfterSaleType().getKey()) {
                List<QAfterSaleDetail> list = afterSaleOrder.listItem();
                for (QAfterSaleDetail afterSaleOrderItem : list) {
                    if (afterSaleOrderItem.getOrderItemDetail().getOrderItem().getId() == qOrderItem.getId()) {
                        number += qOrderItem.getNumber();
                        break;
                    }
                }
            } else {
                List<QAfterSaleItem> list = afterSaleOrder.listItem();
                for (QAfterSaleItem afterSaleOrderItem : list) {
                    if (afterSaleOrderItem.getOrderItem().getId() == qOrderItem.getId()) {
                        number += qOrderItem.getNumber();
                        break;
                    }
                }
            }
        }
        if (number < qOrderItem.getNumber()) {
            afterSaleOrderItemVO = new AfterSaleOrderItemVO();
            afterSaleOrderItemVO.setName(qOrderItem.getName());
            afterSaleOrderItemVO.setImage(fileSDKClient.getFileServerUrl() + qOrderItem.getImage());
            afterSaleOrderItemVO.setDiscount(qOrderItem.getDiscount());
            afterSaleOrderItemVO.setNumber(qOrderItem.getNumber() - number);
            afterSaleOrderItemVO.setOrderItemId(qOrderItem.getId());
        }
        return afterSaleOrderItemVO;
    }

    @Override
    public AfterSaleInfoDetailsVO toDetailsVO(QAfterSaleOrder afterSaleOrder) {

        AfterSaleInfoDetailsVO afterSaleInfo = new AfterSaleInfoDetailsVO();
        int number = 0;
        if (AfterSaleType.EXCHANGE.getKey() == afterSaleOrder.getAfterSaleType().getKey() || AfterSaleType.RETURN.getKey() == afterSaleOrder.getAfterSaleType().getKey()) {
            List<QAfterSaleDetail> list = afterSaleOrder.listItem();
            for (QAfterSaleDetail detail : list) {
                AfterSaleInfoMerchandiseVO myAfterSaleMerchandiseVO = new AfterSaleInfoMerchandiseVO();
                myAfterSaleMerchandiseVO.setDiscount(detail.getOrderItemDetail().getDiscount());
                myAfterSaleMerchandiseVO.setImage(fileSDKClient.getFileServerUrl() + detail.getOrderItemDetail().getImage());
                myAfterSaleMerchandiseVO.setName(detail.getOrderItemDetail().getName());
                myAfterSaleMerchandiseVO.setNumber(detail.getNumber());
                myAfterSaleMerchandiseVO.setSpecifications(detail.getOrderItemDetail().getSpecifications());
                afterSaleInfo.getList().add(myAfterSaleMerchandiseVO);
                number += detail.getNumber();
            }
        } else {
            List<QAfterSaleItem> list = afterSaleOrder.listItem();
            for (QAfterSaleItem item : list) {
                AfterSaleInfoMerchandiseVO myAfterSaleMerchandiseVO = new AfterSaleInfoMerchandiseVO();
                myAfterSaleMerchandiseVO.setDiscount(item.getOrderItem().getDiscount());
                myAfterSaleMerchandiseVO.setImage(fileSDKClient.getFileServerUrl() + item.getOrderItem().getImage());
                myAfterSaleMerchandiseVO.setName(item.getOrderItem().getName());
                myAfterSaleMerchandiseVO.setNumber(item.getNumber());
                myAfterSaleMerchandiseVO.setSpecifications(item.getOrderItem().getSpecifications());
                afterSaleInfo.getList().add(myAfterSaleMerchandiseVO);
                number += item.getNumber();
            }
        }
        afterSaleInfo.setMessageList(listMessage(afterSaleOrder.getState()));
        afterSaleInfo.setNumber(number);
        afterSaleInfo.setReason(afterSaleOrder.getReason());
        afterSaleInfo.setState(afterSaleOrder.getState());
        afterSaleInfo.setStateStr(afterSaleOrder.getUserStateStr());
        afterSaleInfo.setImage(afterSaleOrder.getAfterSaleImage());
        afterSaleInfo.setAfterSaleSum(afterSaleOrder.getAfterSaleSum());
        afterSaleInfo.setAfterSaleType(afterSaleOrder.getAfterSaleType().getKey());
        afterSaleInfo.setExplain(afterSaleOrder.getExplain());
        afterSaleInfo.setAfterSaleId(afterSaleOrder.getAfterSaleId());
        return afterSaleInfo;
    }

    public List<AfterSaleInfoMessage> listMessage(int afterSaleState) {

        List<AfterSaleInfoMessage> messageList = new ArrayList<AfterSaleInfoMessage>();
        Xml xml = XmlFactory.get("order-refund-state-message-config");
        if (xml != null) {
            List<XmlItem> itemList = xml.getItemList();
            for (XmlItem xmlItem : itemList) {
                int state = Integer.parseInt(String.valueOf(xmlItem.getAttrMap().get("state")));
                String stateDesc = xmlItem.getText();
                String code = xmlItem.getAttrMap().get("code");
                String message = String.valueOf(parameterClient.get(code));
                if (state <= afterSaleState) {
                    AfterSaleInfoMessage afterSaleInfoMessage = new AfterSaleInfoMessage();
                    afterSaleInfoMessage.setState(state);
                    afterSaleInfoMessage.setMessage(message);
                    afterSaleInfoMessage.setStateDesc(stateDesc);
                    messageList.add(afterSaleInfoMessage);
                }
            }
        }
        Collections.sort(messageList, new Comparator<AfterSaleInfoMessage>() {

            @Override
            public int compare(AfterSaleInfoMessage o1, AfterSaleInfoMessage o2) {

                return String.valueOf(o2.getState()).compareTo(String.valueOf(o1.getState()));
            }
        });
        return messageList;
    }
}
