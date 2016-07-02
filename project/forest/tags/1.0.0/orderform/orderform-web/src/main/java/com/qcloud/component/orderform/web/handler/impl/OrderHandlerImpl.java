package com.qcloud.component.orderform.web.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.orderform.QOrderItemDetail;
import com.qcloud.component.orderform.service.CollectOrderService;
import com.qcloud.component.orderform.service.OrderConfigService;
import com.qcloud.component.orderform.service.SubOrderService;
import com.qcloud.component.orderform.web.handler.OrderHandler;
import com.qcloud.component.orderform.web.vo.merchant.MerchantOrderVO;
import com.qcloud.component.orderform.web.vo.personal.AbstractOrderVO;
import com.qcloud.component.orderform.web.vo.personal.OrderCVO;
import com.qcloud.component.orderform.web.vo.personal.OrderClassifyVO;
import com.qcloud.component.orderform.web.vo.personal.OrderDeliveryVO;
import com.qcloud.component.orderform.web.vo.personal.OrderExpressVO;
import com.qcloud.component.orderform.web.vo.personal.OrderItemVO;
import com.qcloud.component.orderform.web.vo.personal.OrderMVO;
import com.qcloud.component.orderform.web.vo.personal.OrderMerchantVO;
import com.qcloud.component.orderform.web.vo.personal.OrderVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.key.TypeEnum.MerchantOrderStateType;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.StringUtil;

@Component
public class OrderHandlerImpl implements OrderHandler {

    @Autowired
    private FileSDKClient         fileSDKClient;

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Autowired
    private SellercenterClient    sellercenterClient;

    @Autowired
    private PublicdataClient      publicdataClient;

    @Autowired
    private SubOrderService       subOrderService;

    @Autowired
    private CollectOrderService   collectOrderService;

    @Autowired
    private OrderConfigService    orderConfigService;

    @Value("${pirates.mall.topClassify}")
    private String                topClassify;

    @Override
    public OrderVO toVO(QOrder order) {

        OrderVO orderVO = new OrderVO();
        setOrderVOAttr(order, orderVO, -1L, true);
        orderVO.setExplain(order.getMerchantOrderList().get(0).getExplain());
        orderVO.setSubOrderId(order.getMerchantOrderList().get(0).getId());
        //
        orderVO.setExpressName(order.getMerchantOrderList().get(0).getExpressName());
        orderVO.setExpressNumber(order.getMerchantOrderList().get(0).getExpressNumber());
        //
        List<QMerchantOrder> merchantOrderList = order.getMerchantOrderList();
        for (QMerchantOrder qMerchantOrder : merchantOrderList) {
            for (QOrderItem qOrderItem : qMerchantOrder.getOrderItemList()) {
                QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(qOrderItem.getUnifiedMerchandiseId());
                orderVO.getOrderItemList().add(toOrderItemVO(qOrderItem, unifiedMerchandise));
            }
        }
        // orderVO.setAfterSaleList(afterSaleHandler.toVOList(order.getAfterSaleOrderList()));
        return orderVO;
    }

    @Override
    public OrderMVO toVO4Merchant(QOrder order) {

        OrderMVO orderVO = new OrderMVO();
        setOrderVOAttr(order, orderVO, -1L, false);
        //
        orderVO.setExpressName(order.getMerchantOrderList().get(0).getExpressName());
        orderVO.setExpressNumber(order.getMerchantOrderList().get(0).getExpressNumber());
        //
        List<QMerchantOrder> merchantOrderList = order.getMerchantOrderList();
        for (QMerchantOrder qMerchantOrder : merchantOrderList) {
            OrderMerchantVO orderMerchantVO = new OrderMerchantVO();
            QMerchant merchant = sellercenterClient.getMerchant(qMerchantOrder.getMerchantId());
            //
            orderMerchantVO.setSubOrderId(qMerchantOrder.getId());
            orderMerchantVO.setMerchantName(merchant == null ? "" : merchant.getName());
            orderMerchantVO.setMerchantImage(fileSDKClient.getFileServerUrl() + StringUtil.nullToEmpty(merchant.getImage()));
            orderMerchantVO.setMerchantId(merchant.getId());
            orderMerchantVO.setNumber(qMerchantOrder.getNumber());
            orderMerchantVO.setExplain(qMerchantOrder.getExplain());
            orderMerchantVO.setPostage(qMerchantOrder.getPostage());
            orderMerchantVO.setCash(qMerchantOrder.getCash());
            orderMerchantVO.setCoupon(qMerchantOrder.getCoupon());
            orderMerchantVO.setIntegral(qMerchantOrder.getIntegral());
            orderMerchantVO.setConsumption(qMerchantOrder.getConsumption());
            int state = collectOrderService.getUserOrderFormState(qMerchantOrder.getState());
            orderMerchantVO.setPersonalOrderState(state);
            OrderExpressVO orderExpressVO = new OrderExpressVO();
            orderExpressVO.setExpressName(sellercenterClient.getExpressName(qMerchantOrder.getExpressCode()));
            orderExpressVO.setExpressNumber(qMerchantOrder.getExpressNumber());
            orderMerchantVO.setOrderExpressVO(orderExpressVO);
            for (QOrderItem qOrderItem : qMerchantOrder.getOrderItemList()) {
                QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(qOrderItem.getUnifiedMerchandiseId());
                orderMerchantVO.getOrderItemList().add(toOrderItemVO(qOrderItem, unifiedMerchandise));
            }
            orderMerchantVO.setSum(qMerchantOrder.getSum());
            orderVO.getMerchantList().add(orderMerchantVO);
        }
        // orderVO.setAfterSaleList(afterSaleHandler.toVOList(order.getAfterSaleOrderList()));
        return orderVO;
    }

    @Override
    public OrderCVO toVO4Classify(QOrder order) {

        Map<Long, OrderClassifyVO> map = new HashMap<Long, OrderClassifyVO>();
        OrderCVO orderVO = new OrderCVO();
        setOrderVOAttr(order, orderVO, -1L, true);
        orderVO.setExplain(order.getMerchantOrderList().get(0).getExplain());
        orderVO.setSubOrderId(order.getMerchantOrderList().get(0).getId());
        //
        orderVO.setExpressName(order.getMerchantOrderList().get(0).getExpressName());
        orderVO.setExpressNumber(order.getMerchantOrderList().get(0).getExpressNumber());
        //
        List<QMerchantOrder> merchantOrderList = order.getMerchantOrderList();
        for (QMerchantOrder qMerchantOrder : merchantOrderList) {
            for (QOrderItem qOrderItem : qMerchantOrder.getOrderItemList()) {
                QOrderItemDetail detail = qOrderItem.getOrderItemDetailList().get(0);
                Long unifiedMerchandiseId = detail.getUnifiedMerchandiseId();
                QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
                Long merchantClassifyId = unifiedMerchandise.getList().get(0).getMerchantClassifyId();
                Classify classify = null;
                if (Boolean.valueOf(topClassify)) {
                    classify = publicdataClient.getTopClassify(merchantClassifyId);
                    AssertUtil.assertNotNull(classify, "一级分类不存在." + merchantClassifyId);
                } else {
                    classify = publicdataClient.getClassify(merchantClassifyId);
                    AssertUtil.assertNotNull(classify, "分类不存在." + merchantClassifyId);
                }
                OrderClassifyVO classifyVO = map.get(classify.getId());
                if (classifyVO == null) {
                    classifyVO = new OrderClassifyVO();
                    classifyVO.setClassifyId(classify.getId());
                    classifyVO.setClassifyName(classify.getName());
                    map.put(classify.getId(), classifyVO);
                    classifyVO.setImage(fileSDKClient.getFileServerUrl() + classify.getImage());
                    orderVO.getClassifyList().add(classifyVO);
                }
                classifyVO = map.get(classify.getId());
                classifyVO.getOrderItemList().add(toOrderItemVO(qOrderItem, unifiedMerchandise));
            }
        }
        // orderVO.setAfterSaleList(afterSaleHandler.toVOList(order.getAfterSaleOrderList()));
        return orderVO;
    }

    private OrderItemVO toOrderItemVO(QOrderItem qOrderItem, QUnifiedMerchandise unifiedMerchandise) {

        OrderItemVO orderItemVO = new OrderItemVO();
        orderItemVO.setDiscount(qOrderItem.getDiscount());
        orderItemVO.setPrice(qOrderItem.getPrice());
        orderItemVO.setImage(fileSDKClient.getFileServerUrl() + qOrderItem.getImage());
        orderItemVO.setName(qOrderItem.getName());
        orderItemVO.setNumber(qOrderItem.getNumber());
        orderItemVO.setSpecifications(qOrderItem.getSpecifications());
        orderItemVO.setUnifiedMerchandiseId(qOrderItem.getUnifiedMerchandiseId());
        orderItemVO.setCode(unifiedMerchandise.getList().get(0).getCode());
        orderItemVO.setOrderItemId(qOrderItem.getId());
        orderItemVO.setAfterSale(qOrderItem.isAfterSale());
        orderItemVO.setEvaluation(qOrderItem.isEvaluation());
        orderItemVO.setUnit(unifiedMerchandise.getUnit());
        return orderItemVO;
    }

    private void setOrderVOAttr(QOrder order, AbstractOrderVO vo, long subOrderId, boolean delivery) {

        // KeyValueVO kv = myClient.getMyOrderFormState(order.getUserId(), order.getId(), subOrderId);
        vo.setAddress(order.getAddress());
        vo.setConsignee(order.getConsignee());
        vo.setId(order.getId());
        vo.setMobile(order.getMobile());
        vo.setSum(order.getSum());
        vo.setOrderDate(order.getOrderDate());
        vo.setOrderNumber(order.getOrderNumber());
        vo.setOrderDateStr(DateUtil.date2String(order.getOrderDate()));
        vo.setStateStr(orderConfigService.getNormalPersonalOrderStateDesc(order.getState()));
        vo.setCash(order.getCash());
        vo.setIntegral(order.getIntegral());
        vo.setConsumption(order.getConsumption());
        vo.setPersonalOrderState(orderConfigService.getNormalPersonalOrderState(order.getState()));
        vo.setInvoice(order.getInvoice());
        vo.setNumber(order.getNumber());
        vo.setState(orderConfigService.getNormalPersonalOrderState(order.getState()));
        vo.setPostage(order.getPostage());
        vo.setAfterSale(order.canApplyAfterSale());
        vo.setEvaluation(order.canEvaluation());
        vo.setSurplus(order.getPreferential());
        vo.setCoupon(order.getCoupon());
        vo.setPaymentMode(order.getPaymentMode());
        vo.setSubOrderId(subOrderId);
        vo.setChangeStateTime(DateUtil.date2String(order.getLastUpdateTime()));
        OrderDeliveryVO orderDeliveryVO = null;
        if (delivery) {
            orderDeliveryVO = new OrderDeliveryVO();
            QMerchantOrder qMerchantOrder = order.getMerchantOrderList().get(0);
            orderDeliveryVO.setDeliveryMode(qMerchantOrder.getDeliveryMode());
            orderDeliveryVO.setDeliveryTimeStr(qMerchantOrder.getDeliveryTimeStr());
            orderDeliveryVO.setPickupAddressStr(qMerchantOrder.getPickupAddressStr());
            orderDeliveryVO.setExpressName(sellercenterClient.getExpressName(qMerchantOrder.getExpressCode()));
            orderDeliveryVO.setExpressNumber(qMerchantOrder.getExpressNumber());
        }
        vo.setOrderDeliveryVO(orderDeliveryVO);
    }

    @Override
    public MerchantOrderVO toMerchantVO(QMerchantOrder merchantOrder) {

        QOrder order = merchantOrder.getOrder();
        MerchantOrderVO vo = new MerchantOrderVO();
        vo.setAddress(order.getAddress());
        vo.setConsignee(order.getConsignee());
        vo.setExplain(merchantOrder.getExplain());
        vo.setMobile(order.getMobile());
        vo.setOrderDateStr(DateUtil.date2String(order.getOrderDate(), DateUtil.DATE_FORMAT_STRING));
        vo.setOrderId(order.getId());
        vo.setOrderNumber(order.getOrderNumber());
        vo.setMerchantOrderNumber(merchantOrder.getOrderNumber());
        int state = subOrderService.getMerchantOrderFormState(merchantOrder.getState());
        MerchantOrderStateType type = MerchantOrderStateType.get(state);
        vo.setStateStr(type == null ? "" : type.getName());
        vo.setSum(merchantOrder.getSum());
        vo.setSurplus(merchantOrder.getSum() - merchantOrder.getSum());
        vo.setCash(merchantOrder.getSum());
        List<com.qcloud.component.orderform.web.vo.merchant.OrderItemVO> orderItemList = new ArrayList<com.qcloud.component.orderform.web.vo.merchant.OrderItemVO>();
        for (QOrderItem orderItem : merchantOrder.getOrderItemList()) {
            com.qcloud.component.orderform.web.vo.merchant.OrderItemVO orderItemVO = new com.qcloud.component.orderform.web.vo.merchant.OrderItemVO();
            orderItemVO.setDiscount(orderItem.getDiscount());
            orderItemVO.setImage(orderItem.getImage());
            orderItemVO.setName(orderItem.getName());
            orderItemVO.setNumber(orderItem.getNumber());
            // 商品的规格,如果是套餐则写套餐
            orderItemVO.setSpecifications(orderItem.getOrderItemDetailList().size() > 1 ? "组合套餐 " : orderItem.getOrderItemDetailList().get(0).getSpecifications());
            orderItemList.add(orderItemVO);
        }
        vo.setOrderItemList(orderItemList);
        return vo;
    }

    @Override
    public OrderVO toVO(QMerchantOrder merchantOrder) {

        OrderVO orderVO = new OrderVO();
        setOrderVOAttr(merchantOrder.getOrder(), orderVO, merchantOrder.getId(), true);
        orderVO.setExplain(merchantOrder.getExplain());
        //
        orderVO.setExpressName(merchantOrder.getExpressName());
        orderVO.setExpressNumber(merchantOrder.getExpressNumber());
        //
        for (QOrderItem qOrderItem : merchantOrder.getOrderItemList()) {
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(qOrderItem.getUnifiedMerchandiseId());
            orderVO.getOrderItemList().add(toOrderItemVO(qOrderItem, unifiedMerchandise));
        }
        // orderVO.setAfterSaleList(afterSaleHandler.toVOList(merchantOrder.getAfterSaleOrderList()));
        return orderVO;
    }

    @Override
    public OrderMVO toVO4Merchant(QMerchantOrder merchantOrder) {

        OrderMVO orderVO = new OrderMVO();
        setOrderVOAttr(merchantOrder.getOrder(), orderVO, merchantOrder.getId(), false);
        //
        orderVO.setExpressName(merchantOrder.getExpressName());
        orderVO.setExpressNumber(merchantOrder.getExpressNumber());
        //
        OrderMerchantVO orderMerchantVO = new OrderMerchantVO();
        QMerchant merchant = sellercenterClient.getMerchant(merchantOrder.getMerchantId());
        //
        orderMerchantVO.setSubOrderId(merchantOrder.getId());
        orderMerchantVO.setMerchantName(merchant == null ? "" : merchant.getName());
        orderMerchantVO.setMerchantImage(fileSDKClient.getFileServerUrl() + StringUtil.nullToEmpty(merchant.getImage()));
        orderMerchantVO.setMerchantId(merchant.getId());
        orderMerchantVO.setNumber(merchantOrder.getNumber());
        orderMerchantVO.setExplain(merchantOrder.getExplain());
        orderMerchantVO.setPostage(merchantOrder.getPostage());
        orderMerchantVO.setCash(merchantOrder.getCash());
        orderMerchantVO.setCoupon(merchantOrder.getCoupon());
        orderMerchantVO.setIntegral(merchantOrder.getIntegral());
        orderMerchantVO.setConsumption(merchantOrder.getConsumption());
        int state = collectOrderService.getUserOrderFormState(merchantOrder.getState());
        orderMerchantVO.setPersonalOrderState(state);
        OrderExpressVO orderExpressVO = new OrderExpressVO();
        orderExpressVO.setExpressName(sellercenterClient.getExpressName(merchantOrder.getExpressCode()));
        orderExpressVO.setExpressNumber(merchantOrder.getExpressNumber());
        orderMerchantVO.setOrderExpressVO(orderExpressVO);
        for (QOrderItem qOrderItem : merchantOrder.getOrderItemList()) {
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(qOrderItem.getUnifiedMerchandiseId());
            orderMerchantVO.getOrderItemList().add(toOrderItemVO(qOrderItem, unifiedMerchandise));
        }
        orderMerchantVO.setSum(merchantOrder.getSum());
        orderVO.getMerchantList().add(orderMerchantVO);
        // orderVO.setAfterSaleList(afterSaleHandler.toVOList(merchantOrder.getAfterSaleOrderList()));
        return orderVO;
    }

    @Override
    public OrderCVO toVO4Classify(QMerchantOrder merchantOrder) {

        Map<Long, OrderClassifyVO> map = new HashMap<Long, OrderClassifyVO>();
        OrderCVO orderVO = new OrderCVO();
        setOrderVOAttr(merchantOrder.getOrder(), orderVO, merchantOrder.getId(), true);
        orderVO.setExplain(merchantOrder.getExplain());
        //
        orderVO.setExpressName(merchantOrder.getExpressName());
        orderVO.setExpressNumber(merchantOrder.getExpressNumber());
        //
        for (QOrderItem qOrderItem : merchantOrder.getOrderItemList()) {
            QOrderItemDetail detail = qOrderItem.getOrderItemDetailList().get(0);
            Long unifiedMerchandiseId = detail.getUnifiedMerchandiseId();
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
            Long merchantClassifyId = unifiedMerchandise.getList().get(0).getMerchantClassifyId();
            Classify classify = publicdataClient.getClassify(merchantClassifyId);
            AssertUtil.assertNotNull(classify, "商品分类不存在." + merchantClassifyId);
            OrderClassifyVO classifyVO = map.get(merchantClassifyId);
            if (classifyVO == null) {
                classifyVO = new OrderClassifyVO();
                classifyVO.setClassifyId(classify.getId());
                classifyVO.setClassifyName(classify.getName());
                map.put(merchantClassifyId, classifyVO);
                classifyVO.setImage(fileSDKClient.getFileServerUrl() + classify.getImage());
                orderVO.getClassifyList().add(classifyVO);
            }
            classifyVO = map.get(merchantClassifyId);
            classifyVO.getOrderItemList().add(toOrderItemVO(qOrderItem, unifiedMerchandise));
        }
        // orderVO.setAfterSaleList(afterSaleHandler.toVOList(merchantOrder.getAfterSaleOrderList()));
        return orderVO;
    }
}
