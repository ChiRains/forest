//package com.qcloud.component.mall.web.controller;
//
//import java.util.Date;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.commoditycenter.CommoditycenterClient;
//import com.qcloud.component.distribution.DistributionClient;
//import com.qcloud.component.distribution.engine.DistributionService;
//import com.qcloud.component.distribution.engine.StatisticsEngineService;
//import com.qcloud.component.orderform.engine.OrderSelecterService;
//import com.qcloud.component.orderform.entity.MerchantOrderEntity;
//import com.qcloud.component.orderform.entity.OrderEntity;
//import com.qcloud.component.orderform.entity.OrderItemEntity;
//import com.qcloud.component.orderform.model.CollectOrder;
//import com.qcloud.component.orderform.service.CollectOrderService;
//import com.qcloud.component.orderform.service.OrderItemDetailService;
//import com.qcloud.component.personalcenter.PersonalcenterClient;
//import com.qcloud.component.personalcenter.WealthType;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//
//@Controller
//@RequestMapping(value = TestController.DIR)
//public class TestController {
//
//    public static final String      DIR = "/test";
//
//    @Autowired
//    private CollectOrderService     collectOrderService;
//
//    @Autowired
//    private PersonalcenterClient    personalcenterClient;
//
//    @Autowired
//    private OrderItemDetailService  orderItemDetailService;
//
//    @Autowired
//    private CommoditycenterClient   commoditycenterClient;
//
//    @Autowired
//    private OrderSelecterService    orderSelecterService;
//
//    @Autowired
//    private DistributionClient      distributionClient;
//
//    @Autowired
//    private DistributionService     distributionService;
//
//    @Autowired
//    private StatisticsEngineService statisticsEngineService;
//
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView add(HttpServletRequest request, Long orderId, Date orderDate) {
//
//        // ---------------------------- 测试代码 ------------------------------------
//        CollectOrder collectOrder = collectOrderService.get(orderId, orderDate);
//        // 积分计算
//        personalcenterClient.calculateMyWealth(collectOrder.getUserId(), WealthType.INTEGRAL, collectOrder.getCash(), true, "订单：" + collectOrder.getOrderNumber() + ",现金消费：" + collectOrder.getCash());
//        // 佣金添加
//        OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
//        List<MerchantOrderEntity> merchantOrderList = orderEntity.getEntityList();
//        for (MerchantOrderEntity merchantOrder : merchantOrderList) {
//            double purchaseSum = 0.0;
//            double discountSum = 0.0;
//            double priceSum = 0.0;
//            List<OrderItemEntity> list = merchantOrder.getEntityList();
//            for (OrderItemEntity item : list) {
//                // 正常购买单品
//                // TODO 记录item id 才合理
//                if (item.getSence() == -1) {
//                    distributionClient.addMerchandiseDealRecords(orderEntity.getUserId(), item.getUnifiedMerchandiseId(), item.getPurchase(), item.getDiscount(), item.getPrice(), item.getNumber(), orderEntity.getId(), item.getId(), orderEntity.getOrderDate());
//                    purchaseSum += item.getPurchase() * item.getNumber();
//                    discountSum += item.getSum();
//                    priceSum += item.getPrice() * item.getNumber();
//                }
//            }
//            if (discountSum > 0) {
//                distributionClient.addMerchantDealRecords(orderEntity.getUserId(), merchantOrder.getMerchantId(), purchaseSum, discountSum, priceSum, merchantOrder.getId(), orderEntity.getOrderDate());
//            }
//        }
//        // ---------------------------- 测试代码 ------------------------------------
//        FrontAjaxView view = new FrontAjaxView();
//        return view;
//    }
//
//    @RequestMapping
//    @NoReferer
//    public FrontAjaxView calculate(HttpServletRequest request) {
//
//        FrontAjaxView view = new FrontAjaxView();
//        statisticsEngineService.distribute();
//        return view;
//    }
//}
