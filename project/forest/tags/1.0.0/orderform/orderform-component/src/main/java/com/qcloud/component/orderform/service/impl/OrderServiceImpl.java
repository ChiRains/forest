//package com.qcloud.component.orderform.service.impl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import com.qcloud.component.commoditycenter.CommoditycenterClient;
//import com.qcloud.component.commoditycenter.QMerchandiseItem;
//import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
//import com.qcloud.component.distribution.DistributionClient;
//import com.qcloud.component.marketing.MarketingClient;
//import com.qcloud.component.orderform.entity.AfterSaleOrder;
//import com.qcloud.component.orderform.entity.MerchantOrderEntity;
//import com.qcloud.component.orderform.entity.Order;
//import com.qcloud.component.orderform.entity.OrderEntity;
//import com.qcloud.component.orderform.entity.OrderItemDetailEntity;
//import com.qcloud.component.orderform.entity.OrderItemEntity;
//import com.qcloud.component.orderform.entity.OrderMerchandise;
//import com.qcloud.component.orderform.model.CollectOrder;
//import com.qcloud.component.orderform.model.OrderItem;
//import com.qcloud.component.orderform.model.OrderItemDetail;
//import com.qcloud.component.orderform.model.SubOrder;
//import com.qcloud.component.orderform.model.key.TypeEnum.OrderStateType;
//import com.qcloud.component.orderform.model.key.TypeEnum.PaymentModeType;
//import com.qcloud.component.orderform.service.AfterSaleSelecterService;
//import com.qcloud.component.orderform.service.CollectOrderService;
//import com.qcloud.component.orderform.service.OrderItemDetailService;
//import com.qcloud.component.orderform.service.OrderItemService;
//import com.qcloud.component.orderform.service.OrderNumberService;
//import com.qcloud.component.orderform.service.OrderSelecterService;
//import com.qcloud.component.orderform.service.OrderService;
//import com.qcloud.component.orderform.service.SubOrderService;
//import com.qcloud.component.personalcenter.PersonalcenterClient;
//import com.qcloud.component.personalcenter.QGrade;
//import com.qcloud.component.personalcenter.QMyConsignee;
//import com.qcloud.component.personalcenter.QMyCoupon;
//import com.qcloud.component.personalcenter.QUser;
//import com.qcloud.component.publicservice.PayClient;
//import com.qcloud.component.sellercenter.QStore;
//import com.qcloud.component.sellercenter.SellercenterClient;
//import com.qcloud.pirates.util.AssertUtil;
//import com.qcloud.pirates.util.StringUtil;
//
//@Service
//public class OrderServiceImpl implements OrderService {
//
//    @Autowired
//    private CollectOrderService      collectOrderService;
//
//    @Autowired
//    private SubOrderService          subOrderService;
//
//    @Autowired
//    private OrderItemService         orderItemService;
//
//    @Autowired
//    private OrderItemDetailService   orderItemDetailService;
//
//    @Autowired
//    private CommoditycenterClient    commoditycenterClient;
//
//    @Autowired
//    private SellercenterClient       sellercenterClient;
//
//    @Autowired
//    private PersonalcenterClient     personalcenterClient;
//
//    @Autowired
//    private PayClient                payClient;
//
//    @Autowired
//    private OrderNumberService       orderNumberService;
//
//    @Autowired
//    private DistributionClient       distributionClient;
//
//    @Autowired
//    private OrderSelecterService     orderSelecterService;
//
//    @Autowired
//    private AfterSaleSelecterService afterSaleSelecterService;
//
//    @Autowired
//    private MarketingClient          marketingClient;
//
////    @Transactional
////    @Override
////    public OrderResult order(Order order) {
////
////        QUser user = order.getUser();
////        AssertUtil.assertNotNull(user, "下订单用户不能为空.");
////        QMyConsignee consignee = order.getConsignee();
////        AssertUtil.assertNotNull(consignee, "下订单用户不能为空.");
////        List<OrderMerchandise> merchandiseList = order.getMerchandiseList();
////        AssertUtil.assertNotEmpty(merchandiseList, "商品列表不能为空.");
////        PaymentModeType paymentModeType = order.getPaymentModeType();
////        AssertUtil.assertNotNull(paymentModeType, "支付方式不能为空.");
////        // List<String> promotionTokenList = order.getPromotionTokenList();
////        // 促销处理,还没想好,好蛋疼
////        return orderCollect(order);
////    }
//
////    // 按商家进行分单
////    private List<List<OrderMerchandise>> spilt(Order order) {
////
////        Map<Long, List<OrderMerchandise>> map = new HashMap<Long, List<OrderMerchandise>>();
////        List<OrderMerchandise> merchandiseList = order.getMerchandiseList();
////        for (OrderMerchandise orderMerchandise : merchandiseList) {
////            Long merchantId = orderMerchandise.getUnifiedMerchandise().getMerchantId();
////            List<OrderMerchandise> list = map.get(merchantId);
////            if (list == null) {
////                list = new ArrayList<OrderMerchandise>();
////                map.put(merchantId, list);
////            }
////            list.add(orderMerchandise);
////        }
////        return new ArrayList<List<OrderMerchandise>>(map.values());
////    }
//
////    // 处理生产总单
////    private OrderResult orderCollect(Order order) {
////
////        QUser user = order.getUser();
////        QGrade grade = user.getGrade();
////        int discount = 100;
////        if (grade != null) {
////            discount = grade.getDiscount();
////        }
////        QMyConsignee consignee = order.getConsignee();
////        List<OrderMerchandise> merchandiseList = order.getMerchandiseList();
////        PaymentModeType paymentModeType = order.getPaymentModeType();
////        double sum = calculateSum(discount, merchandiseList);
////        //
////        String explain = order.getExplain();
////        String deliveryTimeStr = order.getDeliveryTimeStr();
////        String pickupAddressStr = order.getPickupAddressStr();
////        final Date orderDate = new Date();
////        //
////        final CollectOrder collectOrder = new CollectOrder();
////        collectOrder.setSum(sum);
////        collectOrder.setCash(sum);
////        // 优惠劵
////        boolean userCoupon = false;
////        Long couponItemId = -1L;
////        if (order.getMyCouponId() != null && order.getMyCouponId() > 0) {
////            QMyCoupon myCoupon = personalcenterClient.my().getMyCoupon(user.getId(), order.getMyCouponId());
////            AssertUtil.assertNotNull(myCoupon, "优惠劵不存在." + order.getMyCouponId());
////            AssertUtil.assertTrue(myCoupon.getExtractDate().before(new Date()), "优惠劵已经过期");
////            AssertUtil.assertTrue(myCoupon.getLimitPrice() <= sum, "订单金额尚未达到使用优惠劵金额");
////            double cash = sum - myCoupon.getPrice();
////            cash = cash <= 0 ? 0 : cash;
////            //
////            collectOrder.setCash(cash);
//////            collectOrder.setCoupon(myCoupon.getPrice());
//////            collectOrder.setMyCouponId(myCoupon.getId());
////            couponItemId = myCoupon.getCouponItemId();
////            userCoupon = true;
////        } else {
//////            collectOrder.setCoupon(0.0);
//////            collectOrder.setMyCouponId(-1L);
////        }
////        collectOrder.setUserId(user.getId());
////        collectOrder.setTime(orderDate);
////        collectOrder.setOrderNumber(orderNumberService.generate());
////        collectOrder.setEmail(consignee.getEmail());
////        collectOrder.setConsignee(consignee.getName());
////        collectOrder.setMobile(consignee.getMobile());
////        collectOrder.setAddress(StringUtil.nullToEmpty(consignee.getProvince()) + StringUtil.nullToEmpty(consignee.getCity()) + StringUtil.nullToEmpty(consignee.getDistrict()) + consignee.getAddress());
////        collectOrder.setExplain(explain);
////        collectOrder.setDeliveryTimeStr(deliveryTimeStr);
////        collectOrder.setPaymentMode(paymentModeType.getKey());
////        collectOrder.setState(OrderStateType.NORMAL_TOPAY.getKey());
////        collectOrder.setPickupAddressStr(pickupAddressStr);
////        collectOrder.setNeedInvoice(order.getNeedInvoiceType().getKey());
////        collectOrder.setInvoiceType(order.getInvoiceType().getKey());
////        collectOrder.setInvoiceHead(order.getInvoiceHead());
////        collectOrder.setInvoiceContent(order.getInvoiceContent());
////        collectOrder.setDeliveryMode(order.getDeliveryModeType().getKey());
////        boolean result = collectOrderService.add(collectOrder);
////        AssertUtil.assertTrue(result, "下总单失败.");
////        if (userCoupon) {
////            personalcenterClient.my().useMyCoupon(user.getId(), order.getMyCouponId(), collectOrder.getId(), orderDate);
////            marketingClient.useCoupon(couponItemId);
////        }
////        List<List<OrderMerchandise>> list = spilt(order);
////        for (List<OrderMerchandise> omList : list) {
////            orderSub(omList, consignee, collectOrder.getId(), order.getStoreId(), user.getId(), orderDate, discount);
////        }
////        removeFromShoppingCart(order);
////        return new OrderResult() {
////
////            @Override
////            public boolean isSuccess() {
////
////                return true;
////            }
////
////            @Override
////            public String getOrderNumber() {
////
////                return collectOrder.getOrderNumber();
////            }
////
////            @Override
////            public Long getOrderId() {
////
////                return collectOrder.getId();
////            }
////
////            @Override
////            public double getSum() {
////
////                return collectOrder.getSum();
////            }
////
////            @Override
////            public Date getDate() {
////
////                return orderDate;
////            }
////        };
////    }
//
////    // 处理子单
////    private void orderSub(List<OrderMerchandise> orderMerchandiseList, QMyConsignee consignee, Long collectId, Long storeId, long userId, Date orderDate, int discount) {
////
////        Long merchantId = orderMerchandiseList.get(0).getUnifiedMerchandise().getMerchantId();
////        if (storeId == -1) {
////            storeId = calculateStore(merchantId, consignee);
////        }
////        double sum = calculateSum(discount, orderMerchandiseList);
////        SubOrder subOrder = new SubOrder();
////        subOrder.setMerchantId(merchantId);
////        subOrder.setStoreId(storeId);
////        subOrder.setSum(sum);
////        subOrder.setState(OrderStateType.NORMAL_TOPAY.getKey());
////        subOrder.setOrderNumber(orderNumberService.generate());
////        subOrder.setOrderId(collectId);
////        boolean result = subOrderService.add(subOrder, orderDate);
////        AssertUtil.assertTrue(result, "下子单失败." + merchantId);
////        for (OrderMerchandise orderMerchandise : orderMerchandiseList) {
////            orderItem(orderMerchandise, collectId, subOrder.getId(), orderDate, discount);
////        }
////    }
//
////    private Long calculateStore(Long merchantId, QMyConsignee consignee) {
////
////        List<QStore> storeList = sellercenterClient.listStoreByMerchant(merchantId);
////        AssertUtil.assertNotEmpty(storeList, "商家门店不能为空." + merchantId);
////        // 暂时处理获取总店
////        Long storeId = storeList.get(0).getId();
////        for (QStore qStore : storeList) {
////            if (qStore.isRoot()) {
////                storeId = qStore.getId();
////                break;
////            }
////        }
////        // TODO : 区域化,推送订单到门店算法
////        return storeId;
////    }
//
////    private void removeFromShoppingCart(Order order) {
////
////        List<OrderMerchandise> merchandiseList = order.getMerchandiseList();
////        for (OrderMerchandise orderMerchandise : merchandiseList) {
////            QUnifiedMerchandise unifiedMerchandise = orderMerchandise.getUnifiedMerchandise();
////            personalcenterClient.my().removeMyShoppingCartMerchandise(unifiedMerchandise.getId(), order.getUser().getId());
////        }
////    }
//
////    private double calculateSum(int discount, List<OrderMerchandise> merchandiseList) {
////
////        double sum = 0.0;
////        for (OrderMerchandise orderMerchandise : merchandiseList) {
////            sum += calculateSum(discount, orderMerchandise);
////        }
////        return sum;
////    }
////
////    private double calculateSum(int discount, OrderMerchandise orderMerchandise) {
////
////        QUnifiedMerchandise unifiedMerchandise = orderMerchandise.getUnifiedMerchandise();
////        double sum = unifiedMerchandise.getDiscount() * orderMerchandise.getNumber() * discount / 100;
////        return sum;
////    }
//
////    private void orderItem(OrderMerchandise orderMerchandise, Long collectId, Long subId, Date orderDate, int discount) {
////
////        QUnifiedMerchandise unifiedMerchandise = orderMerchandise.getUnifiedMerchandise();
////        OrderItem orderItem = new OrderItem();
////        orderItem.setMerchantId(unifiedMerchandise.getMerchantId());
////        orderItem.setNumber(orderMerchandise.getNumber());
////        orderItem.setOrderId(collectId);
////        orderItem.setSence(unifiedMerchandise.getSence());
////        orderItem.setDiscount(unifiedMerchandise.getDiscount() * discount / 100);
////        orderItem.setPurchase(unifiedMerchandise.getPurchase());
////        orderItem.setSubOrderId(subId);
////        orderItem.setName(unifiedMerchandise.getName());
////        orderItem.setImage(unifiedMerchandise.getImage());
////        orderItem.setPrice(unifiedMerchandise.getPrice());
////        orderItem.setSum(calculateSum(discount, orderMerchandise));
////        orderItem.setUnifiedMerchandiseId(unifiedMerchandise.getId());
////        orderItem.setState(OrderStateType.NORMAL_TOPAY.getKey());
////        // 在这里减库存
////        boolean result = commoditycenterClient.lockOnlineStock(unifiedMerchandise.getId(), 0 - orderMerchandise.getNumber());
////        AssertUtil.assertTrue(result, "锁定库存失败." + orderMerchandise.getUnifiedMerchandise().getId() + " " + unifiedMerchandise.getStock() + " " + orderItem.getNumber());
////        orderItem.setSnapshot("");
////        result = orderItemService.add(orderItem, orderDate);
////        AssertUtil.assertTrue(result, "记录订单商品失败." + orderMerchandise.getUnifiedMerchandise().getId());
////        List<QMerchandiseItem> list = unifiedMerchandise.getList();
////        for (QMerchandiseItem merchandiseItem : list) {
////            OrderItemDetail orderItemDetail = new OrderItemDetail();
////            orderItemDetail.setImage(merchandiseItem.getImage());
////            orderItemDetail.setLogisticsNumber("");
////            orderItemDetail.setMerchandiseItemId(merchandiseItem.getId());
////            orderItemDetail.setMerchantId(merchandiseItem.getMerchantId());
////            orderItemDetail.setName(merchandiseItem.getName());
////            orderItemDetail.setOrderId(collectId);
////            orderItemDetail.setOrderItemId(orderItem.getId());
////            orderItemDetail.setSubOrderId(subId);
////            orderItemDetail.setState(OrderStateType.NORMAL_TOPAY.getKey());
////            orderItemDetail.setUnifiedMerchandiseId(unifiedMerchandise.getId());
////            orderItemDetail.setSpecifications(merchandiseItem.getSpecifications());
////            orderItemDetail.setNumber(orderItem.getNumber() * merchandiseItem.getNumber());
////            orderItemDetail.setCode(merchandiseItem.getCode());
////            result = orderItemDetailService.add(orderItemDetail, orderDate);
////            AssertUtil.assertTrue(result, "记录订单商品明细失败." + merchandiseItem.getName() + "(" + merchandiseItem.getId() + ")");
////        }
////    }
//
//    // //
//    // private String generateOrderNumber(long userId) {
//    //
//    // int a1 = new Random().nextInt(26);
//    // int a2 = new Random().nextInt(26);
//    // int a3 = new Random().nextInt(26);
//    // int a4 = new Random().nextInt(26);
//    // int e = new Random().nextInt(100);
//    // char c1 = UPPERCHAR[a1];
//    // char c2 = UPPERCHAR[a2];
//    // char c3 = UPPERCHAR[a3];
//    // char c4 = UPPERCHAR[a4];
//    // String timeStr = DateUtil.date2String(new Date(), "MMddHHmmss");
//    // return String.valueOf(c1) + String.valueOf(c2) + String.valueOf(c3) + String.valueOf(c4) + timeStr + StringUtils.leftPad(String.valueOf(e), 2, "0");
//    // }
//    //
//    // private static final char[] UPPERCHAR = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
//    // public static void main(String[] args) {
//    //
//    // Set<String> hashSet = new HashSet<String>();
//    // for (int i = 0; i < 100; i++) {
//    // String orderNum = new OrderServiceImpl().generateOrderNumber(1111L);
//    // if (!hashSet.add(orderNum)) {
//    // throw new RuntimeException(" xx " + orderNum);
//    // }
//    // System.out.println(orderNum);
//    // }
//    // }
////    @Transactional
////    public boolean exchangeOrderState(Long orderId, Date orderDate, int state, Long authentication) {
////
////        OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
////        AssertUtil.assertNotNull(orderEntity, "订单不存在." + orderId);
////        final int currentState = orderEntity.getState();
////        boolean ok = check(currentState, state);
////        AssertUtil.assertTrue(ok, "订单状态跳转有误." + currentState + "==》" + state);
////        updateState(orderEntity, orderDate, state);
////        return true;
////    }
////
////    private boolean updateState(OrderEntity orderEntity, Date orderDate, int state) {
////
////        List<MerchantOrderEntity> list = orderEntity.getEntityList();
////        for (MerchantOrderEntity merchantOrder : list) {
////            updateState(merchantOrder, orderDate, state);
////        }
////        updateOrderState(orderEntity.getId(), orderDate, state);
////        orderEntity.setState(state);
////        return true;
////    }
////
////    private boolean updateOrderState(Long orderId, Date orderDate, int state) {
////
////        return collectOrderService.updateState(orderId, orderDate, state);
////    }
////
////    @Transactional
////    @Override
////    public boolean exchangeSubOrderState(Long subOrderId, Date orderDate, int state, Long authentication) {
////
////        SubOrder subOrder = subOrderService.get(subOrderId, orderDate);
////        AssertUtil.assertNotNull(subOrder, "子订单不存在." + subOrderId);
////        OrderEntity orderEntity = orderSelecterService.getOrder(subOrder.getOrderId(), orderDate);
////        MerchantOrderEntity merchantOrderEntity = orderEntity.getMerchantOrder(subOrderId);
////        final int currentState = merchantOrderEntity.getState();
////        boolean ok = check(currentState, state);
////        AssertUtil.assertTrue(ok, "子订单状态跳转有误." + currentState + "==》" + state);
////        updateState(merchantOrderEntity, orderDate, state);
////        updateParent(merchantOrderEntity);
////        return true;
////    }
////
////    private boolean updateParent(MerchantOrderEntity merchantOrderEntity) {
////
////        int state = merchantOrderEntity.getState();
////        OrderEntity orderEntity = merchantOrderEntity.getOrder();
////        List<MerchantOrderEntity> subOrderList = orderEntity.getEntityList();
////        boolean update = true;
////        for (MerchantOrderEntity merchantOrder : subOrderList) {
////            if (merchantOrder.getState() < state) {
////                update = false;
////                break;
////            }
////        }
////        if (update) {
////            updateOrderState(orderEntity.getId(), orderEntity.getOrderDate(), state);
////            orderEntity.setState(state);
////        }
////        return true;
////    }
////
////    private boolean updateState(MerchantOrderEntity merchantOrderEntity, Date orderDate, int state) {
////
////        List<OrderItemEntity> orderItemList = merchantOrderEntity.getEntityList();
////        for (OrderItemEntity orderItem : orderItemList) {
////            updateState(orderItem, orderDate, state);
////        }
////        updateSubOrderState(merchantOrderEntity.getId(), orderDate, state);
////        merchantOrderEntity.setState(state);
////        return true;
////    }
////
////    private boolean updateSubOrderState(Long subOrderId, Date orderDate, int state) {
////
////        return subOrderService.updateState(subOrderId, orderDate, state);
////    }
////
////    @Transactional
////    @Override
////    public boolean exchangeOrderItemState(Long orderItemId, Date orderDate, int state, Long authentication) {
////
////        OrderItem orderItem = orderItemService.get(orderItemId, orderDate);
////        AssertUtil.assertNotNull(orderItem, "订单项不存在." + orderItemId);
////        OrderEntity orderEntity = orderSelecterService.getOrder(orderItem.getOrderId(), orderDate);
////        OrderItemEntity orderItemEntity = orderEntity.getMerchantOrder(orderItem.getSubOrderId()).getOrderItem(orderItemId);
////        final int currentState = orderItemEntity.getState();
////        boolean ok = check(currentState, state);
////        AssertUtil.assertTrue(ok, "订单项状态跳转有误." + currentState + "==》" + state);
////        updateState(orderItemEntity, orderDate, state);
////        updateParent(orderItemEntity);
////        return true;
////    }
////
////    private boolean updateParent(OrderItemEntity orderItemEntity) {
////
////        int state = orderItemEntity.getState();
////        MerchantOrderEntity merchantOrderEntity = orderItemEntity.getMerchantOrder();
////        List<OrderItemEntity> orderItemList = merchantOrderEntity.getEntityList();
////        boolean update = true;
////        for (OrderItemEntity orderItem : orderItemList) {
////            if (orderItem.getState() < state) {
////                update = false;
////                break;
////            }
////        }
////        if (update) {
////            updateSubOrderState(merchantOrderEntity.getId(), merchantOrderEntity.getOrder().getOrderDate(), state);
////            merchantOrderEntity.setState(state);
////            updateParent(merchantOrderEntity);
////        }
////        return true;
////    }
////
////    private boolean updateState(OrderItemEntity orderItemEntity, Date orderDate, int state) {
////
////        List<OrderItemDetailEntity> itemDetailList = orderItemEntity.getEntityList();
////        for (OrderItemDetailEntity orderItemDetail : itemDetailList) {
////            updateState(orderItemDetail, orderDate, state);
////        }
////        updateOrderItemState(orderItemEntity.getId(), orderDate, state);
////        orderItemEntity.setState(state);
////        return true;
////    }
////
////    private boolean updateOrderItemState(Long orderItemId, Date orderDate, int state) {
////
////        OrderItem orderItem = orderItemService.get(orderItemId, orderDate);
////        SubOrder subOrder = subOrderService.get(orderItem.getSubOrderId(), orderDate);
////        return orderItemService.updateState(orderItemId, subOrder.getStoreId(), orderDate, state);
////    }
////
////    @Transactional
////    @Override
////    public boolean exchangeOrderItemDetailState(Long orderItemDetailId, Date orderDate, int state, Long authentication) {
////
////        OrderItemDetail orderItemDetail = orderItemDetailService.get(orderItemDetailId, orderDate);
////        AssertUtil.assertNotNull(orderItemDetail, "订单明细不存在." + orderItemDetailId);
////        OrderEntity orderEntity = orderSelecterService.getOrder(orderItemDetail.getOrderId(), orderDate);
////        OrderItemDetailEntity orderItemDetailEntity = orderEntity.getMerchantOrder(orderItemDetail.getSubOrderId()).getOrderItem(orderItemDetail.getOrderItemId()).getOrderItemDetail(orderItemDetail.getId());
////        final int currentState = orderItemDetailEntity.getState();
////        boolean ok = check(currentState, state);
////        AssertUtil.assertTrue(ok, "订单明细状态跳转有误." + currentState + "==》" + state);
////        updateState(orderItemDetailEntity, orderDate, state);
////        updateParent(orderItemDetailEntity);
////        return true;
////    }
////
////    private boolean updateParent(OrderItemDetailEntity detail) {
////
////        int state = detail.getState();
////        OrderItemEntity orderItemEntity = detail.getOrderItem();
////        List<OrderItemDetailEntity> detailList = orderItemEntity.getEntityList();
////        boolean update = true;
////        for (OrderItemDetailEntity orderItemDetail : detailList) {
////            if (orderItemDetail.getState() < state) {
////                update = false;
////                break;
////            }
////        }
////        if (update) {
////            updateOrderItemState(orderItemEntity.getId(), orderItemEntity.getMerchantOrder().getOrder().getOrderDate(), state);
////            orderItemEntity.setState(state);
////            updateParent(orderItemEntity);
////        }
////        return true;
////    }
////
////    private boolean updateState(OrderItemDetailEntity orderItemDetailEntity, Date orderDate, int state) {
////
////        Long id = orderItemDetailEntity.getId();
////        orderItemDetailService.updateState(id, orderDate, state);
////        orderItemDetailEntity.setState(state);
////        return true;
////    }
////
////    private boolean check(int currentState, int newState) {
////
////        for (int[] chain : normalStateChainList) {
////            for (int index = 0; index < chain.length; index++) {
////                if (currentState == chain[index] && index + 1 < chain.length && chain[index + 1] == newState) {
////                    return true;
////                }
////            }
////        }
////        return false;
////    }
////
////    private static final List<int[]> normalStateChainList = new ArrayList<int[]>();
////    static {
////        int[] invalidOrderChain = new int[] { OrderStateType.NORMAL_TOPAY.getKey(), OrderStateType.NORMAL_INVALID.getKey()};
////        int[] cancelOrderChain = new int[] { OrderStateType.NORMAL_TOPAY.getKey(), OrderStateType.NORMAL_CANCEL_ORDER.getKey()};
////        int[] cancelPaidChain = new int[] { OrderStateType.NORMAL_TOPAY.getKey(), OrderStateType.NORMAL_PAID.getKey(), OrderStateType.NORMAL_CANCEL_PAID.getKey()};
////        int[] cancelConfirmChain = new int[] { OrderStateType.NORMAL_TOPAY.getKey(), OrderStateType.NORMAL_PAID.getKey(), OrderStateType.NORMAL_CONFIRM_ORDER.getKey(), OrderStateType.NORMAL_CANCEL_PAID.getKey()};
////        int[] failChain = new int[] { OrderStateType.NORMAL_TOPAY.getKey(), OrderStateType.NORMAL_PAID.getKey(), OrderStateType.NORMAL_CONFIRM_ORDER.getKey(), OrderStateType.NORMAL_SHIPPED.getKey(), OrderStateType.NORMAL_SIGN.getKey(), OrderStateType.NORMAL_TRADE_FAIL.getKey()};
////        int[] normalChain = new int[] { OrderStateType.NORMAL_TOPAY.getKey(), OrderStateType.NORMAL_PAID.getKey(), OrderStateType.NORMAL_CONFIRM_ORDER.getKey(), OrderStateType.NORMAL_SHIPPED.getKey(), OrderStateType.NORMAL_SIGN.getKey(), OrderStateType.NORMAL_TRADE_SUCCESS.getKey()};
////        normalStateChainList.add(normalChain);
////        normalStateChainList.add(failChain);
////        normalStateChainList.add(invalidOrderChain);
////        normalStateChainList.add(cancelOrderChain);
////        normalStateChainList.add(cancelConfirmChain);
////        normalStateChainList.add(cancelPaidChain);
////    }
//
////    @Override
////    public boolean invalid() {
////
////        AutoChanger invalid = new AutoChanger() {
////
////            @Override
////            public OrderStateType before(Long orderId, Date orderDate) {
////
////                return OrderStateType.NORMAL_INVALID;
////            }
////
////            @Override
////            public boolean done(Long orderId, Date orderDate, OrderStateType target) {
////
////                return true;
////            }
////        };
////        int limitOrderTimeMinutes = payClient.getPayMinutes();
////        Date[] dates = collectOrderService.getDatesByLatelyMinutes(limitOrderTimeMinutes);
////        if (dates.length == 1) {
////            autoChange(dates[0], dates[0], OrderStateType.NORMAL_TOPAY, invalid);
////        } else if (dates.length == 2) {
////            autoChange(dates[0], dates[1], OrderStateType.NORMAL_TOPAY, invalid);
////            autoChange(dates[0], dates[0], OrderStateType.NORMAL_TOPAY, invalid);
////        }
////        return true;
////    }
////
////    @Override
////    public boolean sign() {
////
////        // TODO 自动签收时间
////        int day = 1;
////        int latelyMinutes = day * 24 * 60;
////        //
////        Date[] dates = collectOrderService.getDatesByLatelyMinutes(latelyMinutes);
////        AutoChanger sign = new AutoChanger() {
////
////            @Override
////            public OrderStateType before(Long orderId, Date orderDate) {
////
////                return OrderStateType.NORMAL_SIGN;
////            }
////
////            @Override
////            public boolean done(Long orderId, Date orderDate, OrderStateType target) {
////
////                return true;
////            }
////        };
////        if (dates.length == 1) {
////            autoChange(dates[0], dates[0], OrderStateType.NORMAL_SHIPPED, sign);
////        } else if (dates.length == 2) {
////            autoChange(dates[0], dates[1], OrderStateType.NORMAL_SHIPPED, sign);
////            autoChange(dates[0], dates[0], OrderStateType.NORMAL_SHIPPED, sign);
////        }
////        return true;
////    }
////
////    @Override
////    public boolean trade() {
////
////        // TODO 交易成功时间
////        int day = 1;
////        int latelyMinutes = day * 24 * 60;
////        //
////        Date[] dates = collectOrderService.getDatesByLatelyMinutes(latelyMinutes);
////        AutoChanger trade = new AutoChanger() {
////
////            @Override
////            public OrderStateType before(Long orderId, Date orderDate) {
////
////                OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
////                AssertUtil.assertNotNull(orderEntity, "订单不存在." + orderId);
////                List<AfterSaleOrder> list = afterSaleSelecterService.listAfterSaleOrder(orderEntity);
////                for (AfterSaleOrder afterSaleOrder : list) {
////                    if (OrderStateType.EXCHANGE.getKey() == afterSaleOrder.getState() || OrderStateType.RETURN.getKey() == afterSaleOrder.getState()) {
////                        // 待处理的售后
////                        return null;
////                    } else if (OrderStateType.EXCHANGE_CONFIRM.getKey() <= afterSaleOrder.getState() || OrderStateType.RETURN_CONFIRM.getKey() <= afterSaleOrder.getState()) {
////                        // 存在问题的
////                        return OrderStateType.NORMAL_TRADE_FAIL;
////                    }
////                }
////                // 售后
////                return OrderStateType.NORMAL_TRADE_SUCCESS;
////            }
////
////            @Override
////            public boolean done(Long orderId, Date orderDate, OrderStateType target) {
////
////                if (OrderStateType.NORMAL_TRADE_SUCCESS.equals(target)) {
////                    OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
////                    // 订单 有优惠的不参加分佣
////                    if (orderEntity.getCash() != orderEntity.getSum()) {
////                        return true;
////                    }
////                    List<MerchantOrderEntity> merchantOrderList = orderEntity.getEntityList();
////                    for (MerchantOrderEntity merchantOrder : merchantOrderList) {
////                        double purchaseSum = 0.0;
////                        double discountSum = 0.0;
////                        double priceSum = 0.0;
////                        List<OrderItemEntity> list = merchantOrder.getEntityList();
////                        for (OrderItemEntity item : list) {
////                            // 正常购买单品
////                            if (item.getSence() == -1) {
////                                distributionClient.addMerchandiseDealRecords(orderEntity.getUserId(), item.getUnifiedMerchandiseId(), item.getPurchase(), item.getDiscount(), item.getPrice(), item.getNumber(), orderEntity.getId(), item.getId(), orderEntity.getOrderDate());
////                                purchaseSum += item.getPurchase() * item.getNumber();
////                                discountSum += item.getSum();
////                                priceSum += item.getPrice() * item.getNumber();
////                            }
////                        }
////                        if (discountSum > 0) {
////                            distributionClient.addMerchantDealRecords(orderEntity.getUserId(), merchantOrder.getMerchantId(), purchaseSum, discountSum, priceSum, merchantOrder.getId(), orderEntity.getOrderDate());
////                        }
////                    }
////                }
////                return true;
////            }
////        };
////        if (dates.length == 1) {
////            autoChange(dates[0], dates[0], OrderStateType.NORMAL_SIGN, trade);
////        } else if (dates.length == 2) {
////            autoChange(dates[0], dates[1], OrderStateType.NORMAL_SIGN, trade);
////            autoChange(dates[0], dates[0], OrderStateType.NORMAL_SIGN, trade);
////        }
////        return true;
////    }
////
////    private boolean autoChange(Date endDate, Date tableDate, OrderStateType source, AutoChanger changer) {
////
////        int start = 0;
////        int size = 200;
////        int number = -1;
////        do {
////            List<CollectOrder> list = collectOrderService.list4EndDateAndState(endDate, tableDate, source, start, size);
////            for (CollectOrder collectOrder : list) {
////                autoChange(collectOrder.getId(), collectOrder.getTime(), changer);
////            }
////            number = list.size();
////        } while (number == size);
////        return true;
////    }
////
////    @Transactional
////    private boolean autoChange(Long orderId, Date orderDate, AutoChanger changer) {
////
////        OrderStateType target = changer.before(orderId, orderDate);
////        if (target == null) {
////            return false;
////        }
////        exchangeOrderState(orderId, orderDate, target.getKey(), -999L);
////        changer.done(orderId, orderDate, target);
////        return true;
////    }
////    private static interface AutoChanger {
////
////        OrderStateType before(Long orderId, Date orderDate);
////
////        boolean done(Long orderId, Date orderDate, OrderStateType target);
////    }
//}
