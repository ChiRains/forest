package com.qcloud.component.orderform.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.qcloud.component.orderform.model.OrderState;
import com.qcloud.component.orderform.model.OrderStateDefination;
import com.qcloud.component.orderform.service.OrderConfigService;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;

@Service
public class OrderConfigServiceImpl implements OrderConfigService {

    //
    private OrderStateDefination normalState;

    //
    private OrderStateDefination refundState;

    //
    private OrderStateDefination returnState;

    //
    private OrderStateDefination exchangeState;

    @PostConstruct
    public void init() {

        normalState = init("orderform-normal-state", "orderform-normal-state-chain");
        refundState = init("orderform-refund-state", "orderform-refund-state-chain");
        returnState = init("orderform-return-state", "orderform-return-state-chain");
        exchangeState = init("orderform-exchange-state", "orderform-exchange-state-chain");
        initAfterSale(normalState, "orderform-normal-state-to-after-sale");
    }

    public OrderStateDefination init(String stateXmlCode, String chainCode) {

        Xml stateXml = XmlFactory.get(stateXmlCode);
        List<OrderState> stateList;
        if (stateXml == null) {
            stateList = new ArrayList<OrderState>(0);
        } else {
            stateList = new ArrayList<OrderState>();
            List<XmlItem> list = stateXml.getItemList();
            for (XmlItem xmlItem : list) {
                String orderStateStr = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("orderState")).trim();
                String merchantOrderStateStr = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("merchantOrderState")).trim();
                String userOrderStateStr = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("userOrderState")).trim();
                String orderStateDesc = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("orderStateDesc")).trim();
                String merchantOrderStateDesc = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("merchantOrderStateDesc")).trim();
                String userOrderStateDesc = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("userOrderStateDesc")).trim();
                String init = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("init")).trim();
                String finish = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("finish")).trim();
                OrderState orderState = new OrderState();
                orderState.setOrderState(Integer.parseInt(orderStateStr));
                orderState.setMerchantOrderState(Integer.parseInt(merchantOrderStateStr));
                orderState.setPersonalOrderState(Integer.parseInt(userOrderStateStr));
                orderState.setDesc(StringUtil.nullToEmpty(xmlItem.getText()).trim());
                orderState.setOrderStateDesc(orderStateDesc);
                orderState.setMerchantOrderStateDesc(merchantOrderStateDesc);
                orderState.setPersonalOrderStateDesc(userOrderStateDesc);
                orderState.setInit(Boolean.valueOf(init));
                orderState.setFinish(Boolean.valueOf(finish));
                stateList.add(orderState);
            }
        }
        OrderStateDefination def = new OrderStateDefination();
        def.setStateList(stateList);
        //
        List<int[]> chain;
        Xml xml = XmlFactory.get(chainCode);
        if (xml == null) {
            chain = new ArrayList<int[]>(0);
        } else {
            chain = new ArrayList<int[]>();
            List<XmlItem> list = xml.getItemList();
            for (XmlItem xmlItem : list) {
                String chainStr = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("chain")).trim();
                Xml chainXml = XmlFactory.get(chainStr);
                AssertUtil.assertNotNull(chainXml, "订单状态链未定义" + chainStr);
                List<XmlItem> chainList = chainXml.getItemList();
                int[] stateArray = new int[chainList.size()];
                for (XmlItem chainXmlItem : chainList) {
                    String orderStateStr = StringUtil.nullToEmpty(chainXmlItem.getAttrMap().get("orderState")).trim();
                    OrderState orderState = def.get(Integer.parseInt(orderStateStr));
                    AssertUtil.assertNotNull(orderState, "未定义 " + chainCode + " " + orderStateStr + " 状态");
                    stateArray[chainList.indexOf(chainXmlItem)] = orderState.getOrderState();
                }
                chain.add(stateArray);
            }
        }
        def.setChain(chain);
        return def;
    }

    private void initAfterSale(OrderStateDefination normalState, String xmlCode) {

        Xml xml = XmlFactory.get(xmlCode);
        if (xml != null) {
            List<XmlItem> itemList = xml.getItemList();
            for (XmlItem xmlItem : itemList) {
                String orderStateStr = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("orderState")).trim();
                String refundStr = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("refund")).trim();
                String returnStr = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("return")).trim();
                String exchangeStr = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("exchange")).trim();
                OrderState orderState = normalState.get(Integer.parseInt(orderStateStr));
                AssertUtil.assertNotNull(orderState, "订单状态未定义" + orderStateStr);
                orderState.setCanRefund(Boolean.valueOf(refundStr));
                orderState.setCanReturn(Boolean.valueOf(returnStr));
                orderState.setCanExchange(Boolean.valueOf(exchangeStr));
            }
        }
    }

    @Override
    public List<int[]> normalChain() {

        return normalState.getChain();
    }

    @Override
    public List<int[]> refundChain() {

        return refundState.getChain();
    }

    @Override
    public List<int[]> returnChain() {

        return returnState.getChain();
    }

    @Override
    public List<int[]> exchangeChain() {

        return exchangeState.getChain();
    }

    @Override
    public int getNormalMerchantOrderState(int state) {

        OrderState orderState = normalState.get(state);
        AssertUtil.assertNotNull(orderState, "订单状态未定义" + state);
        return orderState.getMerchantOrderState();
    }

    @Override
    public int getNormalPersonalOrderState(int state) {

        OrderState orderState = normalState.get(state);
        AssertUtil.assertNotNull(orderState, "订单状态未定义" + state);
        return orderState.getPersonalOrderState();
    }

    @Override
    public int getRefundMerchantOrderState(int state) {

        OrderState orderState = refundState.get(state);
        AssertUtil.assertNotNull(orderState, "退款单状态未定义" + state);
        return orderState.getMerchantOrderState();
    }

    @Override
    public int getRefundPersonalOrderState(int state) {

        OrderState orderState = refundState.get(state);
        AssertUtil.assertNotNull(orderState, "退款单状态未定义" + state);
        return orderState.getPersonalOrderState();
    }

    @Override
    public int getReturnMerchantOrderState(int state) {

        OrderState orderState = returnState.get(state);
        AssertUtil.assertNotNull(orderState, "退货单状态未定义" + state);
        return orderState.getMerchantOrderState();
    }

    @Override
    public int getReturnPersonalOrderState(int state) {

        OrderState orderState = returnState.get(state);
        AssertUtil.assertNotNull(orderState, "退货单状态未定义" + state);
        return orderState.getPersonalOrderState();
    }

    @Override
    public int getExchangeMerchantOrderState(int state) {

        OrderState orderState = exchangeState.get(state);
        AssertUtil.assertNotNull(orderState, "换货单状态未定义" + state);
        return orderState.getMerchantOrderState();
    }

    @Override
    public int getExchangePersonalOrderState(int state) {

        OrderState orderState = exchangeState.get(state);
        AssertUtil.assertNotNull(orderState, "换货单状态未定义" + state);
        return orderState.getPersonalOrderState();
    }

    @Override
    public String getNormalPersonalOrderStateDesc(int state) {

        OrderState orderState = normalState.get(state);
        AssertUtil.assertNotNull(orderState, "订单状态未定义" + state);
        return orderState.getPersonalOrderStateDesc();
    }

    @Override
    public String getRefundPersonalOrderStateDesc(int state) {

        OrderState orderState = refundState.get(state);
        AssertUtil.assertNotNull(orderState, "退款单状态未定义" + state);
        return orderState.getPersonalOrderStateDesc();
    }

    @Override
    public String getReturnPersonalOrderStateDesc(int state) {

        OrderState orderState = returnState.get(state);
        AssertUtil.assertNotNull(orderState, "退款单状态未定义" + state);
        return orderState.getPersonalOrderStateDesc();
    }

    @Override
    public String getExchangePersonalOrderStateDesc(int state) {

        OrderState orderState = exchangeState.get(state);
        AssertUtil.assertNotNull(orderState, "换货单状态未定义" + state);
        return orderState.getPersonalOrderStateDesc();
    }

    @Override
    public int getNormalInitOrderState() {

        OrderState orderState = normalState.getInit();
        AssertUtil.assertNotNull(orderState, "订单初始化状态尚未定义.");
        return orderState.getOrderState();
    }

    @Override
    public int getRefundInitOrderState() {

        OrderState orderState = refundState.getInit();
        AssertUtil.assertNotNull(orderState, "退款单初始化状态尚未定义.");
        return orderState.getOrderState();
    }

    @Override
    public int getReturnInitOrderState() {

        OrderState orderState = returnState.getInit();
        AssertUtil.assertNotNull(orderState, "退款单初始化状态尚未定义.");
        return orderState.getOrderState();
    }

    @Override
    public int getExchangeInitOrderState() {

        OrderState orderState = exchangeState.getInit();
        AssertUtil.assertNotNull(orderState, "换货单初始化状态尚未定义.");
        return orderState.getOrderState();
    }

    @Override
    public boolean isNormalFinish(int state) {

        OrderState orderState = normalState.get(state);
        AssertUtil.assertNotNull(orderState, "订单状态未定义" + state);
        return orderState.isFinish();
    }

    @Override
    public boolean isRefundFinish(int state) {

        OrderState orderState = refundState.get(state);
        AssertUtil.assertNotNull(orderState, "退款单状态未定义" + state);
        return orderState.isFinish();
    }

    @Override
    public boolean isReturnFinish(int state) {

        OrderState orderState = returnState.get(state);
        AssertUtil.assertNotNull(orderState, "退货单状态未定义" + state);
        return orderState.isFinish();
    }

    @Override
    public boolean isExchangeFinish(int state) {

        OrderState orderState = exchangeState.get(state);
        AssertUtil.assertNotNull(orderState, "换货单状态未定义" + state);
        return orderState.isFinish();
    }

    @Override
    public boolean canExchangeInNormal(int state) {

        OrderState orderState = normalState.get(state);
        AssertUtil.assertNotNull(orderState, "订单初始化状态尚未定义.");
        return orderState.isCanExchange();
    }

    @Override
    public boolean canRefundInNormal(int state) {

        OrderState orderState = normalState.get(state);
        AssertUtil.assertNotNull(orderState, "订单初始化状态尚未定义.");
        return orderState.isCanRefund();
    }

    @Override
    public boolean canReturnInNormal(int state) {

        OrderState orderState = normalState.get(state);
        AssertUtil.assertNotNull(orderState, "订单初始化状态尚未定义.");
        return orderState.isCanReturn();
    }
}
