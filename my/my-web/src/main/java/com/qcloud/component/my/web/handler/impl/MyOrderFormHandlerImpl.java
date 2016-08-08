package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.my.model.MyOrderForm;
import com.qcloud.component.my.web.handler.MyOrderFormHandler;
import com.qcloud.component.my.web.vo.MyMerchantOrderItemVO;
import com.qcloud.component.my.web.vo.MyOrderFormListVO;
import com.qcloud.component.my.web.vo.MyOrderFormMerchandiseVO;
import com.qcloud.component.my.web.vo.MyOrderFormMerchantVO;
import com.qcloud.component.my.web.vo.MyOrderFormSimpleVO;
import com.qcloud.component.my.web.vo.MyOrderItemVO;
import com.qcloud.component.my.web.vo.admin.AdminMyOrderFormVO;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.StringUtil;

@Component
public class MyOrderFormHandlerImpl implements MyOrderFormHandler {

    @Autowired
    private FileSDKClient      fileSDKClient;

    @Autowired
    private OrderformClient    orderformClient;

    @Autowired
    private SellercenterClient sellercenterClient;

    @Override
    public List<AdminMyOrderFormVO> toVOList4Admin(List<MyOrderForm> list) {

        List<AdminMyOrderFormVO> voList = new ArrayList<AdminMyOrderFormVO>();
        for (MyOrderForm adminMyOrderForm : list) {
            voList.add(toVO4Admin(adminMyOrderForm));
        }
        return voList;
    }

    @Override
    public AdminMyOrderFormVO toVO4Admin(MyOrderForm myOrderForm) {

        String json = Json.toJson(myOrderForm);
        return Json.toObject(json, AdminMyOrderFormVO.class, true);
    }

    @Override
    public List<MyOrderFormSimpleVO> toVOList4Simple(List<MyOrderForm> list) {

        List<MyOrderFormSimpleVO> voList = new ArrayList<MyOrderFormSimpleVO>();
        for (MyOrderForm myOrderForm : list) {
            voList.add(toVO4Simple(myOrderForm));
        }
        return voList;
    }

    @Override
    public MyOrderFormSimpleVO toVO4Simple(MyOrderForm myOrderForm) {

        String json = Json.toJson(myOrderForm);
        MyOrderFormSimpleVO vo = Json.toObject(json, MyOrderFormSimpleVO.class, true);
        QOrder order = orderformClient.getOrder(myOrderForm.getOrderId(), myOrderForm.getTime());
        setMyOrderFormSimpleVOAttr(vo, myOrderForm, order);
        return vo;
    }

    @Override
    public List<MyOrderFormMerchandiseVO> toVOList4Merchandise(List<MyOrderForm> list) {

        List<MyOrderFormMerchandiseVO> voList = new ArrayList<MyOrderFormMerchandiseVO>();
        for (MyOrderForm myOrderForm : list) {
            if (isToOrder(myOrderForm.getState())) {
                voList.add(toOrderVO4Merchandise(myOrderForm));
            } else {
                voList.add(toMerchantVO4Merchandise(myOrderForm));
            }
        }
        return voList;
    }

    private boolean isToOrder(int state) {

        int[] states = orderStates();
        for (int i : states) {
            if (i == state) {
                return true;
            }
        }
        return false;
    }

    private int[] orderStates() {

        Xml xml = XmlFactory.get("my-myOrderForm-View-Order-State");
        if (xml == null) {
            return new int[0];
        }
        List<XmlItem> itemList = xml.getItemList();
        int[] states = new int[itemList.size()];
        for (XmlItem item : itemList) {
            int state = Integer.parseInt(item.getAttrMap().get("state"));
            states[itemList.indexOf(item)] = state;
        }
        return states;
    }

    private MyOrderFormMerchandiseVO toMerchantVO4Merchandise(MyOrderForm myOrderForm) {

        String json = Json.toJson(myOrderForm);
        MyOrderFormMerchandiseVO vo = Json.toObject(json, MyOrderFormMerchandiseVO.class, true);
        QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(myOrderForm.getSubOrderId(), myOrderForm.getTime());
        setMyOrderFormSimpleVOAttr(vo, myOrderForm, merchantOrder.getOrder());
        vo.setAfterSale(merchantOrder.getOrder().canApplyAfterSale());
        vo.setEvaluation(merchantOrder.getOrder().canEvaluation());
        AssertUtil.assertNotNull(merchantOrder, "商家不存在." + merchantOrder.getMerchantId());
        QMerchant merchant = sellercenterClient.getMerchant(merchantOrder.getMerchantId());
        vo.setMerchantId(merchant.getId());
        vo.setMerchantName(merchant.getName());
        if (StringUtils.isNotEmpty(merchant.getImage())) {
            vo.setMerchantImage(fileSDKClient.getFileServerUrl() + merchant.getImage());
        } else {
            vo.setImage(StringUtil.nullToEmpty(merchant.getImage()));
        }
        vo.setNumber(merchantOrder.getOrder().getNumber());
        AssertUtil.assertNotNull(merchant, "商家不存在." + merchantOrder.getMerchantId());
        for (QOrderItem orderItem : merchantOrder.getOrderItemList()) {
            MyOrderItemVO myOrderItemVO = new MyOrderItemVO();
            myOrderItemVO.setDiscount(NumberUtil.scale(orderItem.getDiscount(), 2));
            if (StringUtils.isNotEmpty(orderItem.getImage())) {
                myOrderItemVO.setImage(fileSDKClient.getFileServerUrl() + orderItem.getImage());
            } else {
                myOrderItemVO.setImage(StringUtil.nullToEmpty(orderItem.getImage()));
            }
            myOrderItemVO.setMerchantId(merchantOrder.getMerchantId());
            myOrderItemVO.setMerchantName(merchant.getName());
            myOrderItemVO.setNumber(orderItem.getNumber());
            myOrderItemVO.setSpecifications(orderItem.getSpecifications());
            myOrderItemVO.setUnifiedMerchandiseId(orderItem.getUnifiedMerchandiseId());
            myOrderItemVO.setOrderItemId(orderItem.getId());
            myOrderItemVO.setName(orderItem.getName());
            vo.getOrderItemList().add(myOrderItemVO);
        }
        return vo;
    }

    private MyOrderFormMerchandiseVO toOrderVO4Merchandise(MyOrderForm myOrderForm) {

        String json = Json.toJson(myOrderForm);
        MyOrderFormMerchandiseVO vo = Json.toObject(json, MyOrderFormMerchandiseVO.class, true);
        QOrder order = orderformClient.getOrder(myOrderForm.getOrderId(), myOrderForm.getTime());
        setMyOrderFormSimpleVOAttr(vo, myOrderForm, order);
        List<QMerchantOrder> merchantOrderList = order.getMerchantOrderList();
        vo.setAfterSale(order.canApplyAfterSale());
        vo.setEvaluation(order.canEvaluation());
        if (merchantOrderList.size() == 1) {
            QMerchantOrder merchantOrder = merchantOrderList.get(0);
            AssertUtil.assertNotNull(merchantOrder, "商家不存在." + merchantOrder.getMerchantId());
            QMerchant merchant = sellercenterClient.getMerchant(merchantOrderList.get(0).getMerchantId());
            vo.setMerchantId(merchant.getId());
            vo.setMerchantName(merchant.getName());
            if (StringUtils.isNotEmpty(merchant.getImage())) {
                vo.setMerchantImage(fileSDKClient.getFileServerUrl() + merchant.getImage());
            } else {
                vo.setImage(StringUtil.nullToEmpty(merchant.getImage()));
            }
        } else {
            Xml xml = XmlFactory.get("orderform-myorderform-list-merchantname");
            if (xml != null && xml.getItemList().size() > 0) {
                vo.setMerchantName(StringUtil.nullToEmpty(xml.getItemList().get(0).getAttrMap().get("name")));
            } else {
                vo.setMerchantName("");
            }
            vo.setMerchantId(-1L);
            vo.setMerchantImage("");
        }
        vo.setNumber(order.getNumber());
        for (QMerchantOrder merchantOrder : merchantOrderList) {
            QMerchant merchant = sellercenterClient.getMerchant(merchantOrderList.get(0).getMerchantId());
            AssertUtil.assertNotNull(merchant, "商家不存在." + merchantOrder.getMerchantId());
            for (QOrderItem orderItem : merchantOrder.getOrderItemList()) {
                MyOrderItemVO myOrderItemVO = new MyOrderItemVO();
                myOrderItemVO.setDiscount(orderItem.getDiscount());
                if (StringUtils.isNotEmpty(orderItem.getImage())) {
                    myOrderItemVO.setImage(fileSDKClient.getFileServerUrl() + orderItem.getImage());
                } else {
                    myOrderItemVO.setImage(StringUtil.nullToEmpty(orderItem.getImage()));
                }
                myOrderItemVO.setMerchantId(merchantOrder.getMerchantId());
                myOrderItemVO.setMerchantName(merchant.getName());
                myOrderItemVO.setNumber(orderItem.getNumber());
                myOrderItemVO.setSpecifications(orderItem.getSpecifications());
                myOrderItemVO.setUnifiedMerchandiseId(orderItem.getUnifiedMerchandiseId());
                myOrderItemVO.setName(orderItem.getName());
                myOrderItemVO.setOrderItemId(orderItem.getId());
                vo.getOrderItemList().add(myOrderItemVO);
            }
        }
        return vo;
    }

    private void setMyOrderFormSimpleVOAttr(MyOrderFormSimpleVO vo, MyOrderForm myOrderForm, QOrder order) {

        String image = order.getMerchantOrderList().get(0).getOrderItemList().get(0).getImage();
        if (StringUtils.isNotEmpty(image)) {
            vo.setImage(fileSDKClient.getFileServerUrl() + image);
        } else {
            vo.setImage(StringUtil.nullToEmpty(image));
        }
        vo.setOrderNumber(order.getOrderNumber());
        vo.setSum(NumberUtil.scale(order.getSum(), 2));
        vo.setCash(NumberUtil.scale(order.getCash(), 2));
        vo.setPostage(order.getPostage());
        // vo.setAfterSaleStateName(order.getAfterSaleStateName());
        vo.setTimeStr(DateUtil.date2String(myOrderForm.getTime()));
        vo.setState(myOrderForm.getState());
        vo.setStateStr(order.getUserStateStr());
        vo.setSurplus(NumberUtil.scale(order.getPreferential(), 2));
        //
        vo.setPaymentMode(order.getPaymentMode());
        vo.setIntegral(order.getIntegral());
        vo.setConsumption(NumberUtil.scale(order.getConsumption(), 2));
        vo.setConsignee(order.getConsignee());
        vo.setMobile(order.getMobile());
        vo.setSubOrderId(myOrderForm.getSubOrderId());
        vo.setRemind(false);
        if (myOrderForm.getSubOrderId() == -1) {
            QMerchantOrder merchantOrder = order.getMerchantOrderList().get(0);
            vo.setRemind(!merchantOrder.canRemind());
            vo.setDeliveryTimeStr(merchantOrder.getDeliveryTimeStr());
        } else {
            for (QMerchantOrder merchantOrder : order.getMerchantOrderList()) {
                if (merchantOrder.getId() == myOrderForm.getSubOrderId()) {
                    vo.setRemind(!merchantOrder.canRemind());
                    vo.setDeliveryTimeStr(merchantOrder.getDeliveryTimeStr());
                    break;
                }
            }
        }
    }

    @Override
    public List<MyOrderFormMerchantVO> toVOList4Merchant(List<MyOrderForm> list) {

        List<MyOrderFormMerchantVO> voList = new ArrayList<MyOrderFormMerchantVO>();
        for (MyOrderForm myOrderForm : list) {
            voList.add(toVO4Merchant(myOrderForm));
        }
        return voList;
    }

    private MyOrderFormMerchantVO toVO4Merchant(MyOrderForm myOrderForm) {

        String json = Json.toJson(myOrderForm);
        MyOrderFormMerchantVO vo = Json.toObject(json, MyOrderFormMerchantVO.class, true);
        QOrder order = orderformClient.getOrder(myOrderForm.getOrderId(), myOrderForm.getTime());
        setMyOrderFormSimpleVOAttr(vo, myOrderForm, order);
        List<QMerchantOrder> merchantOrderList = order.getMerchantOrderList();
        for (QMerchantOrder merchantOrder : merchantOrderList) {
            QMerchant merchant = sellercenterClient.getMerchant(merchantOrder.getMerchantId());
            AssertUtil.assertNotNull(merchant, "商家不存在." + merchantOrder.getMerchantId());
            MyMerchantOrderItemVO myMerchantOrderItemVO = new MyMerchantOrderItemVO();
            myMerchantOrderItemVO.setMerchantId(merchantOrder.getMerchantId());
            myMerchantOrderItemVO.setMerchantName(merchant.getName());
            myMerchantOrderItemVO.setNumber(merchantOrder.getNumber());
            myMerchantOrderItemVO.setSum(merchantOrder.getSum());
            myMerchantOrderItemVO.setCash(merchantOrder.getCash());
            for (QOrderItem orderItem : merchantOrder.getOrderItemList()) {
                MyOrderItemVO myOrderItemVO = new MyOrderItemVO();
                myOrderItemVO.setDiscount(orderItem.getDiscount());
                if (StringUtils.isNotEmpty(orderItem.getImage())) {
                    myOrderItemVO.setImage(fileSDKClient.getFileServerUrl() + orderItem.getImage());
                } else {
                    myOrderItemVO.setImage(StringUtil.nullToEmpty(orderItem.getImage()));
                }
                myOrderItemVO.setMerchantId(merchant.getId());
                myOrderItemVO.setMerchantName(merchant.getName());
                myOrderItemVO.setNumber(orderItem.getNumber());
                myOrderItemVO.setSpecifications(orderItem.getSpecifications());
                myOrderItemVO.setUnifiedMerchandiseId(orderItem.getUnifiedMerchandiseId());
                myOrderItemVO.setName(orderItem.getName());
                myOrderItemVO.setOrderItemId(orderItem.getId());
                myMerchantOrderItemVO.getOrderItemList().add(myOrderItemVO);
            }
            vo.getMerchantOrderItemList().add(myMerchantOrderItemVO);
        }
        return vo;
    }

    @Override
    public List<MyOrderFormListVO> toVOList4List(List<MyOrderForm> list) {

        List<MyOrderFormListVO> voList = new ArrayList<MyOrderFormListVO>();
        for (MyOrderForm myOrderForm : list) {
            String json = Json.toJson(myOrderForm);
            MyOrderFormListVO vo = Json.toObject(json, MyOrderFormListVO.class, true);
            QOrder order = orderformClient.getOrder(myOrderForm.getOrderId(), myOrderForm.getTime());
            List<String> imageList = new ArrayList<String>();
            int merchandiseNumber = 0;
            for (QMerchantOrder merchantOrder : order.getMerchantOrderList()) {
                for (QOrderItem item : merchantOrder.getOrderItemList()) {
                    imageList.add(fileSDKClient.getFileServerUrl() + item.getImage());
                    merchandiseNumber++;
                }
            }
            vo.setImage(imageList);
            vo.setOrderNumber(order.getOrderNumber());
            vo.setSum(NumberUtil.scale(order.getSum(), 2));
            //
            vo.setOrderDateStr(DateUtil.date2String(myOrderForm.getTime()));
            vo.setTimeStr(DateUtil.date2String(myOrderForm.getTime()));
            vo.setState(myOrderForm.getState());
            vo.setStateStr(order.getUserStateStr());
            //
            vo.setSubOrderId(myOrderForm.getSubOrderId());
            vo.setRemind(false);
            if (myOrderForm.getSubOrderId() == -1) {
                QMerchantOrder merchantOrder = order.getMerchantOrderList().get(0);
                vo.setRemind(!merchantOrder.canRemind());
            } else {
                for (QMerchantOrder merchantOrder : order.getMerchantOrderList()) {
                    if (merchantOrder.getId() == myOrderForm.getSubOrderId()) {
                        vo.setRemind(!merchantOrder.canRemind());
                        break;
                    }
                }
            }
            vo.setAfterSale(order.canApplyAfterSale());
            vo.setEvaluation(order.canEvaluation());
            vo.setAddEvaluation(!order.canEvaluation());
            vo.setCash(order.getCash());
            vo.setMerchandiseNumber(merchandiseNumber);
            voList.add(vo);
        }
        return voList;
    }
}
