package com.qcloud.component.orderform.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.AfterSaleType;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QAfterSaleOrder;
import com.qcloud.component.orderform.engine.AfterSaleSelecterService;
import com.qcloud.component.orderform.engine.AfterSaleService;
import com.qcloud.component.orderform.engine.OrderSelecterService;
import com.qcloud.component.orderform.entity.AfterSaleDetail;
import com.qcloud.component.orderform.entity.AfterSaleItem;
import com.qcloud.component.orderform.entity.AfterSaleOrder;
import com.qcloud.component.orderform.entity.ExchangeAfterSaleOrder;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.OrderItemDetailEntity;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.entity.ReturnAfterSaleOrder;
import com.qcloud.component.orderform.exception.OrderformException;
import com.qcloud.component.orderform.model.ExchangeOrder;
import com.qcloud.component.orderform.model.ReturnOrder;
import com.qcloud.component.orderform.service.ExchangeOrderService;
import com.qcloud.component.orderform.service.ReturnOrderService;
import com.qcloud.component.orderform.web.form.AfterSaleForm;
import com.qcloud.component.orderform.web.handler.AfterSaleHandler;
import com.qcloud.component.orderform.web.util.AfterSaleCheckUtils;
import com.qcloud.component.orderform.web.util.AfterSaleDetailUtils;
import com.qcloud.component.orderform.web.util.AfterSaleItemUtils;
import com.qcloud.component.orderform.web.util.ExchangeOrderStateType;
import com.qcloud.component.orderform.web.util.ReturnOrderStateType;
import com.qcloud.component.orderform.web.vo.AfterSaleInfoDetailsVO;
import com.qcloud.component.orderform.web.vo.AfterSaleInformationVO;
import com.qcloud.component.orderform.web.vo.AfterSaleOrderItemVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;

@Controller
@RequestMapping(value = AfterSaleController.DIR)
public class AfterSaleController {

    public static final String       DIR = "/afterSale";

    @Autowired
    private OrderSelecterService     orderSelecterService;

    @Autowired
    private AfterSaleSelecterService afterSaleSelecterService;

    @Autowired
    private AfterSaleService         afterSaleService;

    @Autowired
    private ExchangeOrderService     exchangeOrderService;

    @Autowired
    private ReturnOrderService       returnOrderService;

    @Autowired
    private AfterSaleHandler         afterSaleHandler;

    @Autowired
    private OrderformClient          orderformClient;

    // 重新申请退货
    @PiratesApp
    @RequestMapping
    public FrontAjaxView reApplyReturnOrder(HttpServletRequest request, Long returnId) {

        ReturnOrder returnOrder = returnOrderService.get(returnId);
        AssertUtil.assertNotNull(returnOrder, "退货单不存在." + returnId);
        OrderEntity orderEntity = orderSelecterService.getOrder(returnOrder.getOrderId(), returnOrder.getOrderDate());
        AssertUtil.assertNotNull(orderEntity, "订单不存在." + returnOrder.getOrderId() + " " + DateUtil.date2String(returnOrder.getOrderDate()));
        // check
        List<AfterSaleOrder> existList = afterSaleSelecterService.listAfterSaleOrder(orderEntity);
        MerchantOrderEntity merchantOrder = orderEntity.getMerchantOrder(returnOrder.getSubOrderId());
        AssertUtil.assertTrue(merchantOrder.canReturn(), "签收后才可以退货." + returnOrder.getOrderId());
        ReturnAfterSaleOrder returnAfterSaleOrder = afterSaleSelecterService.getReturnOrder(merchantOrder, returnId);
        Long result = AfterSaleCheckUtils.checkItem(returnAfterSaleOrder, existList);
        AssertUtil.assertTrue(result == -1L, "订单项已经在售后处理,请勿重复提交." + result);
        //
        returnOrder.setState(ReturnOrderStateType.RETURN_AGAIN.getKey());
        returnOrderService.update(returnOrder);
        afterSaleService.reApplyReturnOrder(returnAfterSaleOrder);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("重新申请退货成功");
        return view;
    }

    // 重新申请换货
    @PiratesApp
    @RequestMapping
    public FrontAjaxView reApplyExchangeOrder(HttpServletRequest request, Long exchangeId) {

        ExchangeOrder exchangeOrder = exchangeOrderService.get(exchangeId);
        AssertUtil.assertNotNull(exchangeOrder, "换货单不存在." + exchangeId);
        OrderEntity orderEntity = orderSelecterService.getOrder(exchangeOrder.getOrderId(), exchangeOrder.getOrderDate());
        AssertUtil.assertNotNull(orderEntity, "订单不存在." + exchangeOrder.getOrderId() + " " + DateUtil.date2String(exchangeOrder.getOrderDate()));
        // check
        List<AfterSaleOrder> existList = afterSaleSelecterService.listAfterSaleOrder(orderEntity);
        MerchantOrderEntity merchantOrder = orderEntity.getMerchantOrder(exchangeOrder.getSubOrderId());
        AssertUtil.assertTrue(merchantOrder.canExchange(), "签收后才可以换货." + exchangeOrder.getOrderId());
        //
        ExchangeAfterSaleOrder exchangeAfterSaleOrder = afterSaleSelecterService.getExchangeOrder(merchantOrder, exchangeId);
        Long result = AfterSaleCheckUtils.checkDetail(exchangeAfterSaleOrder, existList);
        AssertUtil.assertTrue(result == -1L, "订单项已经在售后处理,请勿重复提交." + result);
        //
        exchangeOrder.setState(ExchangeOrderStateType.EXCHANGE_AGAIN.getKey());
        exchangeOrderService.update(exchangeOrder);
        afterSaleService.reApplyExchangeOrder(exchangeAfterSaleOrder);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("重新申请换货成功");
        return view;
    }

    // 退款涉及到库存,只能按子订单退
    @PiratesApp
    @RequestMapping
    public FrontAjaxView refundOrder(HttpServletRequest request, AfterSaleForm afterSaleForm) {

        AssertUtil.assertNotNull(afterSaleForm.getOrderId(), "订单ID不能为空.");
        AssertUtil.assertNotNull(afterSaleForm.getOrderDate(), "订单日期不能为空.");
        OrderEntity orderEntity = orderSelecterService.getOrder(afterSaleForm.getOrderId(), afterSaleForm.getOrderDate());
        AssertUtil.assertNotNull(orderEntity, "订单不存在." + afterSaleForm.getOrderId() + " " + DateUtil.date2String(afterSaleForm.getOrderDate()));
        AssertUtil.assertTrue(orderEntity.canRefund(), "付款或者商家确认方可以退款." + afterSaleForm.getOrderId());
        //
        List<AfterSaleItem> list = AfterSaleItemUtils.toAfterSales(orderEntity, afterSaleForm, afterSaleForm.getExplain(), afterSaleForm.getReason());
        AssertUtil.assertNotEmpty(list, "退款清单不能为空.");
        // check
        List<AfterSaleOrder> existList = afterSaleSelecterService.listAfterSaleOrder(orderEntity);
        Long result = AfterSaleCheckUtils.checkItem(list, existList);
        AssertUtil.assertTrue(result == -1L, "订单项已经在售后处理,请勿重复提交." + result);
        //
        switch (afterSaleForm.getType()) {
        case 1: // 全退了
            List<OrderItemEntity> itemList = new ArrayList<OrderItemEntity>();
            for (AfterSaleItem afterSale : list) {
                itemList.add(afterSale.getOrderItem());
            }
            afterSaleService.applyRefund(orderEntity, itemList, afterSaleForm.getExplain(), afterSaleForm.getReason(), afterSaleForm.getAfterSaleSum());
            break;
        case 2: // 按商家订单退
            List<OrderItemEntity> subList = new ArrayList<OrderItemEntity>();
            for (AfterSaleItem afterSale : list) {
                subList.add(afterSale.getOrderItem());
            }
            afterSaleService.applyRefund(orderEntity, subList, afterSaleForm.getExplain(), afterSaleForm.getReason(), afterSaleForm.getAfterSaleSum());
            break;
        default:
            throw new OrderformException("退款表单类型不正确" + afterSaleForm.getType());
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("申请退款成功");
        return view;
    }

    /**
     * 买家退货
     * @param orderStateForm
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView returnOrder(HttpServletRequest request, AfterSaleForm afterSaleForm) {

        AssertUtil.assertNotNull(afterSaleForm.getOrderId(), "订单ID不能为空.");
        AssertUtil.assertNotNull(afterSaleForm.getOrderDate(), "订单日期不能为空.");
        OrderEntity orderEntity = orderSelecterService.getOrder(afterSaleForm.getOrderId(), afterSaleForm.getOrderDate());
        AssertUtil.assertNotNull(orderEntity, "订单不存在." + afterSaleForm.getOrderId() + " " + DateUtil.date2String(afterSaleForm.getOrderDate()));
        //
        List<AfterSaleItem> list = AfterSaleItemUtils.toAfterSales(orderEntity, afterSaleForm, afterSaleForm.getExplain(), afterSaleForm.getReason());
        AssertUtil.assertNotEmpty(list, "退货清单不能为空.");
        // check
        List<AfterSaleOrder> existList = afterSaleSelecterService.listAfterSaleOrder(orderEntity);
        Long result = AfterSaleCheckUtils.checkItem(list, existList);
        AssertUtil.assertTrue(result == -1L, "订单项已经在售后处理,请勿重复提交." + result);
        Long can = AfterSaleCheckUtils.checkItem(orderEntity, list, AfterSaleType.RETURN);
        AssertUtil.assertTrue(can == -1L, "签收后才可以退货." + can);
        //
        switch (afterSaleForm.getType()) {
        case 1: // 全退了
            List<OrderItemEntity> itemList = new ArrayList<OrderItemEntity>();
            for (AfterSaleItem afterSale : list) {
                itemList.add(afterSale.getOrderItem());
            }
            afterSaleService.applyReturn(orderEntity, itemList, afterSaleForm.getExplain(), afterSaleForm.getReason());
            break;
        case 2: // 按商家订单退
            List<OrderItemEntity> subList = new ArrayList<OrderItemEntity>();
            for (AfterSaleItem afterSale : list) {
                subList.add(afterSale.getOrderItem());
            }
            afterSaleService.applyReturn(orderEntity, subList, afterSaleForm.getExplain(), afterSaleForm.getReason());
            break;
        case 3: // 按物品明细退
            afterSaleService.applyReturn(orderEntity, list);
            break;
        default:
            throw new OrderformException("退货表单类型不正确" + afterSaleForm.getType());
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("申请退货成功");
        return view;
    }

    /**
     * 换货
     * @param orderStateForm
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView exchangeOrder(HttpServletRequest request, AfterSaleForm afterSaleForm) {

        AssertUtil.assertNotNull(afterSaleForm.getOrderId(), "订单ID不能为空.");
        AssertUtil.assertNotNull(afterSaleForm.getOrderDate(), "订单日期不能为空.");
        OrderEntity orderEntity = orderSelecterService.getOrder(afterSaleForm.getOrderId(), afterSaleForm.getOrderDate());
        AssertUtil.assertNotNull(orderEntity, "订单不存在." + afterSaleForm.getOrderId() + " " + DateUtil.date2String(afterSaleForm.getOrderDate()));
        //
        List<AfterSaleDetail> list = AfterSaleDetailUtils.toAfterSales(orderEntity, afterSaleForm, afterSaleForm.getExplain(), afterSaleForm.getReason());
        AssertUtil.assertNotEmpty(list, "换货清单不能为空.");
        // check
        List<AfterSaleOrder> existList = afterSaleSelecterService.listAfterSaleOrder(orderEntity);
        Long result = AfterSaleCheckUtils.checkDetail(list, existList);
        AssertUtil.assertTrue(result == -1L, "订单项已经在售后处理,请勿重复提交." + result);
        Long can = AfterSaleCheckUtils.checkDetail(orderEntity, list, AfterSaleType.EXCHANGE);
        AssertUtil.assertTrue(can == -1L, "签收后才可以换货." + can);
        //
        switch (afterSaleForm.getType()) {
        case 1: // 全退了
            List<OrderItemDetailEntity> allDetailList = new ArrayList<OrderItemDetailEntity>();
            for (AfterSaleDetail afterSale : list) {
                allDetailList.add(afterSale.getOrderItemDetail());
            }
            afterSaleService.applyExchange(orderEntity, allDetailList, afterSaleForm.getExplain(), afterSaleForm.getReason());
            break;
        case 2: // 按商家订单退
            List<OrderItemDetailEntity> subList = new ArrayList<OrderItemDetailEntity>();
            for (AfterSaleDetail afterSale : list) {
                subList.add(afterSale.getOrderItemDetail());
            }
            afterSaleService.applyExchange(orderEntity, subList, afterSaleForm.getExplain(), afterSaleForm.getReason());
            break;
        case 3: // 按物品明细退
            afterSaleService.applyExchange(orderEntity, list);
            break;
        default:
            throw new OrderformException("换货表单类型不正确" + afterSaleForm.getType());
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("申请换货成功");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listByOrder(HttpServletRequest request, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "定单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
        AssertUtil.assertNotNull(orderId, "订单不存在.");
        List<MerchantOrderEntity> list = orderEntity.getEntityList();
        List<AfterSaleOrderItemVO> voList = new ArrayList<AfterSaleOrderItemVO>();
        for (MerchantOrderEntity merchantOrderEntity : list) {
            List<AfterSaleOrderItemVO> itemList = afterSaleHandler.toVOList(merchantOrderEntity);
            voList.addAll(itemList);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取待售后商品成功.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listBySubOrder(HttpServletRequest request, Long subOrderId, Date orderDate) {

        AssertUtil.assertNotNull(subOrderId, "子单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        MerchantOrderEntity merchantOrderEntity = orderSelecterService.getMerchantOrder(subOrderId, orderDate);
        AssertUtil.assertNotNull(subOrderId, "子订单不存在.");
        List<AfterSaleOrderItemVO> voList = afterSaleHandler.toVOList(merchantOrderEntity);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取待售后商品成功.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listAfterSaleInformation(HttpServletRequest request, Long afterSaleId, Integer type) {

        List<AfterSaleInformationVO> voList = new ArrayList<AfterSaleInformationVO>();
        AfterSaleInformationVO vo1 = new AfterSaleInformationVO();
        vo1.setUser(true);
        vo1.setTitle("申请退款");
        vo1.setContent("您申请退款，订单号:6556416556556415646415646556465");
        vo1.setResult(false);
        vo1.setMerchantName("");
        vo1.setTimeStr(DateUtil.date2String(DateUtil.addDate(new Date(), -4)));
        voList.add(vo1);
        //
        AfterSaleInformationVO vo2 = new AfterSaleInformationVO();
        vo2.setUser(false);
        vo2.setResult(false);
        vo2.setTitle("同意退款");
        vo2.setContent("商户同意了您的退款申请");
        vo2.setTimeStr(DateUtil.date2String(DateUtil.addDate(new Date(), -3)));
        vo2.setMerchantName("富贵鸟专卖店");
        voList.add(vo2);
        //
        AfterSaleInformationVO vo3 = new AfterSaleInformationVO();
        vo3.setUser(false);
        vo3.setResult(false);
        vo3.setTitle("退款");
        vo3.setContent("商户给您的支付账户退款652元");
        vo3.setTimeStr(DateUtil.date2String(DateUtil.addDate(new Date(), -2)));
        vo3.setMerchantName("富贵鸟专卖店");
        voList.add(vo3);
        //
        AfterSaleInformationVO vo4 = new AfterSaleInformationVO();
        vo4.setUser(true);
        vo4.setResult(true);
        vo4.setTitle("退款成功");
        vo4.setContent("您确认了商家退款");
        vo4.setTimeStr(DateUtil.date2String(DateUtil.addDate(new Date(), -2)));
        vo4.setMerchantName("");
        voList.add(vo4);
        //
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的售后状态列表成功.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getAfterSaleInfo(HttpServletRequest request, Long afterSaleId, Integer type) {

        AfterSaleType afterSaleType = AfterSaleType.get(type);
        QAfterSaleOrder afterSaleOrder = orderformClient.getAfterSaleOrder(afterSaleId, afterSaleType);
        AfterSaleInfoDetailsVO afterSaleInfo = afterSaleHandler.toDetailsVO(afterSaleOrder);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取售后订单详情成功.");
        view.addObject("afterSale", afterSaleInfo);
        return view;
    }
}
