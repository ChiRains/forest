package com.qcloud.component.orderform.engine.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QMerchandiseItem;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.marketing.MarketingClient;
import com.qcloud.component.my.DeliveryModeType;
import com.qcloud.component.my.InvoiceType;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.NeedInvoiceType;
import com.qcloud.component.my.QMyConsignee;
import com.qcloud.component.my.QMyCoupon;
import com.qcloud.component.my.QMyInvoice;
import com.qcloud.component.orderform.OrderContext;
import com.qcloud.component.orderform.OrderContext.OrderDelivery;
import com.qcloud.component.orderform.OrderMyConsignee;
import com.qcloud.component.orderform.PaymentModeType;
import com.qcloud.component.orderform.engine.OrderService;
import com.qcloud.component.orderform.engine.OrderStateService;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.OrderItemDetailEntity;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.exception.OrderformException;
import com.qcloud.component.orderform.model.CollectOrder;
import com.qcloud.component.orderform.model.OrderDiscount;
import com.qcloud.component.orderform.model.OrderItem;
import com.qcloud.component.orderform.model.OrderItemDetail;
import com.qcloud.component.orderform.model.SubOrder;
import com.qcloud.component.orderform.model.key.TypeEnum.DiscountType;
import com.qcloud.component.orderform.service.CollectOrderService;
import com.qcloud.component.orderform.service.OrderConfigService;
import com.qcloud.component.orderform.service.OrderDiscountService;
import com.qcloud.component.orderform.service.OrderItemDetailService;
import com.qcloud.component.orderform.service.OrderItemService;
import com.qcloud.component.orderform.service.OrderNumberService;
import com.qcloud.component.orderform.service.SubOrderService;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QGrade;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.WealthType;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderNumberService     orderNumberService;

    @Autowired
    private SellercenterClient     sellercenterClient;

    @Autowired
    private CollectOrderService    collectOrderService;

    @Autowired
    private SubOrderService        subOrderService;

    @Autowired
    private OrderItemService       orderItemService;

    @Autowired
    private OrderItemDetailService orderItemDetailService;

    @Autowired
    private OrderStateService      orderStateService;

    @Autowired
    private PersonalcenterClient   personalcenterClient;

    @Autowired
    private MyClient               myClient;

    @Autowired
    private OrderDiscountService   orderDiscountService;

    @Autowired
    private MarketingClient        marketingClient;

    @Autowired
    private OrderConfigService     orderConfigService;

    @Autowired
    private CommoditycenterClient  commoditycenterClient;

    @Override
    public OrderEntity prepareOrderNormal(OrderContext context) {

        // 1 准备订单数据
        OrderEntity orderEntity = initOrder(context);
        // 2 算钱
        int discount = calculateDiscount(context.getUser());
        context.setDiscount(discount);
        calculate(orderEntity, context);
        if (orderEntity.getNumber() > 1000) {
            throw new OrderformException("订单商品数量不能超过1000个");
        }
        //
        return orderEntity;
    }

    @Transactional
    @Override
    public OrderEntity orderNormal(OrderContext context) {

        // 1 准备订单数据
        OrderEntity orderEntity = initOrder(context);
        // 2 算钱
        int discount = calculateDiscount(context.getUser());
        context.setDiscount(discount);
        calculate(orderEntity, context);
        if (orderEntity.getNumber() > 1000) {
            throw new OrderformException("订单商品数量不能超过1000个");
        }
        // 3 下单
        order(orderEntity);
        // 4
        removeFromShoppingCart(orderEntity);
        //
        return orderEntity;
    }

    @Transactional
    @Override
    public OrderEntity orderExchange(QUser user, QMyConsignee consignee, QUnifiedMerchandise merchandise, int number, int state) {

        // 上下文
        OrderContext context = new OrderContext();
        context.setUser(user);
        context.setConsignee(consignee);
        context.setDiscount(100);
        context.setDeliveryMap(new HashMap<QMerchant, OrderDelivery>());
        context.setExplainMap(new HashMap<QMerchant, String>());
        context.setInvoice(null);
        context.setMyCouponMap(new HashMap<QMerchant, List<QMyCoupon>>());
        Map<QUnifiedMerchandise, Integer> merchandiseMap = new HashMap<QUnifiedMerchandise, Integer>();
        merchandiseMap.put(merchandise, number);
        context.setMerchandiseMap(merchandiseMap);
        QMerchant merchant = sellercenterClient.getMerchant(merchandise.getMerchantId());
        List<QMerchant> merchantList = new ArrayList<QMerchant>();
        merchantList.add(merchant);
        context.setMerchantList(merchantList);
        // 初始化订单
        OrderEntity orderEntity = initOrder(context);
        orderEntity.getCollectOrder().setAfterSale(EnableType.DISABLE.getKey());
        orderEntity.getCollectOrder().setEvaluation(EnableType.DISABLE.getKey());
        // 计算消费币、或积分
        // 6 积分 7兑兑劵
        MerchantOrderEntity merchantOrderEntity = orderEntity.getEntityList().get(0);
        OrderItemEntity orderItemEntity = merchantOrderEntity.getEntityList().get(0);
        if (merchandise.getSence() == 6) {
            double discount = merchandise.getDiscount();
            int integral = new Double(discount).intValue();
            orderItemEntity.getOrderItem().setIntegral(integral);
            merchantOrderEntity.getSubOrder().setIntegral(integral);
            orderEntity.getCollectOrder().setIntegral(integral);
            orderEntity.getCollectOrder().setPaymentMode(PaymentModeType.INTEGRAL.getKey());
        } else if (merchandise.getSence() == 7) {
            double consumption = merchandise.getDiscount();
            orderItemEntity.getOrderItem().setConsumption(consumption);
            merchantOrderEntity.getSubOrder().setConsumption(consumption);
            orderEntity.getCollectOrder().setConsumption(consumption);
            orderEntity.getCollectOrder().setPaymentMode(PaymentModeType.CONSUMPTION_CURRENCY.getKey());
        } else {
            throw new OrderformException("退换仅支持积分兑换或兑兑券兑换." + merchandise.getSence());
        }
        //
        // 下订单
        order(orderEntity);
        // 无支付密码之前,这里直接到已支付状态
        if (merchandise.getSence() == 6) {
            personalcenterClient.calculateMyWealth(user.getId(), WealthType.INTEGRAL, 0 - orderEntity.getIntegral(), false, "积分商城兑换：" + merchandise.getName() + " " + orderEntity.getIntegral());
        } else if (merchandise.getSence() == 7) {
            personalcenterClient.calculateMyWealth(user.getId(), WealthType.COMSUMPTION_CURRENCY, 0 - orderEntity.getConsumption(), false, "商城兑兑劵兑换：" + merchandise.getName() + " " + orderEntity.getConsumption());
        }
        //
        orderStateService.exchangeOrderState(orderEntity.getId(), orderEntity.getOrderDate(), state, -1L);
        //
        return orderEntity;
    }

    @Transactional
    @Override
    public OrderEntity orderSeckill(QUser user, QUnifiedMerchandise merchandise) {

        OrderContext context = new OrderContext();
        context.setUser(user);
        context.setConsignee(OrderMyConsignee.get());
        context.setDiscount(100);
        context.setDeliveryMap(new HashMap<QMerchant, OrderDelivery>());
        context.setExplainMap(new HashMap<QMerchant, String>());
        context.setInvoice(null);
        context.setMyCouponMap(new HashMap<QMerchant, List<QMyCoupon>>());
        Map<QUnifiedMerchandise, Integer> merchandiseMap = new HashMap<QUnifiedMerchandise, Integer>();
        merchandiseMap.put(merchandise, 1);
        context.setMerchandiseMap(merchandiseMap);
        QMerchant merchant = sellercenterClient.getMerchant(merchandise.getMerchantId());
        List<QMerchant> merchantList = new ArrayList<QMerchant>();
        merchantList.add(merchant);
        context.setMerchantList(merchantList);
        // 初始化订单
        OrderEntity orderEntity = initOrder(context);
        MerchantOrderEntity merchantOrderEntity = orderEntity.getEntityList().get(0);
        OrderItemEntity orderItemEntity = merchantOrderEntity.getEntityList().get(0);
        orderItemEntity.getOrderItem().setSum(merchandise.getDiscount());
        orderItemEntity.getOrderItem().setCash(merchandise.getDiscount());
        orderItemEntity.getOrderItem().setPreferential(0);
        merchantOrderEntity.getSubOrder().setSum(merchandise.getDiscount());
        merchantOrderEntity.getSubOrder().setCash(merchandise.getDiscount());
        merchantOrderEntity.getSubOrder().setPreferential(0);
        orderEntity.getCollectOrder().setSum(merchandise.getDiscount());
        orderEntity.getCollectOrder().setCash(merchandise.getDiscount());
        orderEntity.getCollectOrder().setPreferential(0);
        // 下订单
        order(orderEntity);
        return orderEntity;
    }

    @Transactional
    @Override
    public OrderEntity orderGroupbuys(OrderContext context, String orderNumber, Date orderDate, int paymentMode, double cash, int state) {

        AssertUtil.assertTrue(context.getMerchandiseMap().size() == 1, "团购一个订单只能成交一个商品.");
        context.setDiscount(100);
        context.setDeliveryMap(new HashMap<QMerchant, OrderDelivery>());
        context.setExplainMap(new HashMap<QMerchant, String>());
        context.setInvoice(null);
        context.setMyCouponMap(new HashMap<QMerchant, List<QMyCoupon>>());
        OrderEntity orderEntity = initOrder(context);
        orderEntity.getCollectOrder().setCash(cash);
        orderEntity.getCollectOrder().setSum(cash);
        OrderItemEntity orderItemEntity = orderEntity.getEntityList().get(0).getEntityList().get(0);
        OrderItem orderItem = orderItemEntity.getOrderItem();
        orderItem.setCash(cash);
        orderItem.setSum(cash);
        orderItem.setPreferential(orderItem.getDiscount() * orderItem.getNumber() - cash);
        orderEntity.getCollectOrder().setPreferential(orderItem.getDiscount() * orderItem.getNumber() - cash);
        // 下订单
        order(orderEntity);
        //
        orderStateService.exchangeOrderState(orderEntity.getId(), orderEntity.getOrderDate(), state, -1L);
        return orderEntity;
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    private void removeFromShoppingCart(OrderEntity orderEntity) {

        List<MerchantOrderEntity> merchantOrderList = orderEntity.getEntityList();
        for (MerchantOrderEntity merchantOrderEntity : merchantOrderList) {
            List<OrderItemEntity> itemList = merchantOrderEntity.getEntityList();
            for (OrderItemEntity orderItemEntity : itemList) {
                myClient.removeMyShoppingCartMerchandise(orderItemEntity.getUnifiedMerchandiseId(), orderEntity.getUserId());
            }
        }
    }

    //
    private int calculateDiscount(QUser user) {

        QGrade grade = user.getGrade();
        int discount = 100;
        if (grade != null) {
            discount = grade.getDiscount();
        }
        return discount;
    }

    private void calculate(OrderEntity orderEntity, OrderContext context) {

        int discount = context.getDiscount();
        orderEntity.getCollectOrder().setDiscount(discount);
        // 计算正常折扣价
        List<MerchantOrderEntity> subOrderList = orderEntity.getEntityList();
        for (MerchantOrderEntity merchantOrderEntity : subOrderList) {
            List<OrderItemEntity> orderItemList = merchantOrderEntity.getEntityList();
            for (OrderItemEntity orderItemEntity : orderItemList) {
                orderItemEntity.getOrderItem().setDiscount(orderItemEntity.getDiscount() * discount / 100);
                // VIP价格
//                Double vipDiscount = commoditycenterClient.getVipDiscount(context.getUser().getId(), orderItemEntity.getOrderItem().getUnifiedMerchandiseId());
//                if (vipDiscount != null && new Double(vipDiscount * 100).longValue() > 0 && vipDiscount < orderItemEntity.getOrderItem().getDiscount()) {
//                    orderItemEntity.getOrderItem().setDiscount(vipDiscount);
//                }
                double sum = orderItemEntity.getOrderItem().getDiscount() * orderItemEntity.getNumber();
                orderItemEntity.getOrderItem().setSum(sum);
                orderItemEntity.getOrderItem().setCash(sum);
                orderItemEntity.getOrderItem().setPreferential(orderItemEntity.getPrice() * orderItemEntity.getNumber() - sum);
                //
                merchantOrderEntity.getSubOrder().setSum(merchantOrderEntity.getSum() + orderItemEntity.getSum());
                merchantOrderEntity.getSubOrder().setCash(merchantOrderEntity.getSubOrder().getCash() + orderItemEntity.getOrderItem().getCash());
                merchantOrderEntity.getSubOrder().setPreferential(merchantOrderEntity.getSubOrder().getPreferential() + orderItemEntity.getOrderItem().getPreferential());
            }
            orderEntity.getCollectOrder().setSum(orderEntity.getSum() + merchantOrderEntity.getSum());
            orderEntity.getCollectOrder().setCash(orderEntity.getCash() + merchantOrderEntity.getSubOrder().getCash());
            orderEntity.getCollectOrder().setPreferential(orderEntity.getPreferential() + merchantOrderEntity.getSubOrder().getPreferential());
        }
        // 计算 优惠劵----倒数第二
        Map<QMerchant, List<QMyCoupon>> map = context.getMyCouponMap();
        for (QMerchant merchant : map.keySet()) {
            List<QMyCoupon> list = map.get(merchant);
            MerchantOrderEntity merchantOrderEntity = orderEntity.getMerchantOrderByMerchant(merchant.getId());
            AssertUtil.assertNotNull(merchantOrderEntity, "商家子订单不存在" + merchant.getId());
            double lessCash = merchantOrderEntity.getCash();
            for (QMyCoupon qMyCoupon : list) {
                double limitPrice = qMyCoupon.getLimitPrice();
                // 优惠面值
                // TODO 优惠劵计算可能有问题
                double price = qMyCoupon.getPrice();
                if (limitPrice < lessCash && new Date().before(qMyCoupon.getValidDate())) {
                    lessCash = lessCash - limitPrice;
                    double currentCash = merchantOrderEntity.getSubOrder().getCash();
                    if (currentCash < price) {
                        price = currentCash;
                    }
                    // 优惠记录
                    List<OrderDiscount> discountList = merchantOrderEntity.getDiscountList();
                    OrderDiscount orderDiscount = new OrderDiscount();
                    orderDiscount.setDiscountId(qMyCoupon.getId());
                    orderDiscount.setType(DiscountType.COUPON.getKey());
                    orderDiscount.setPrice(price);
                    discountList.add(orderDiscount);
                    // 商家
                    merchantOrderEntity.getSubOrder().setCash(merchantOrderEntity.getSubOrder().getCash() - price);
                    merchantOrderEntity.getSubOrder().setPreferential(merchantOrderEntity.getSubOrder().getPreferential() + price);
                    merchantOrderEntity.getSubOrder().setCoupon(merchantOrderEntity.getSubOrder().getCoupon() + price);
                    // 订单
                    orderEntity.getCollectOrder().setCash(orderEntity.getCash() - price);
                    orderEntity.getCollectOrder().setPreferential(orderEntity.getPreferential() + price);
                    orderEntity.getCollectOrder().setCoupon(orderEntity.getCoupon() + price);
                }
            }
        }
        // 计算邮费
        for (MerchantOrderEntity merchantOrderEntity : subOrderList) {
            double weight = 0.0;
            List<OrderItemEntity> itemList = merchantOrderEntity.getEntityList();
            for (OrderItemEntity orderItem : itemList) {
                List<OrderItemDetailEntity> detailList = orderItem.getEntityList();
                for (OrderItemDetailEntity orderItemDetailEntity : detailList) {
                    weight += orderItemDetailEntity.getOrderItemDetail().getWeight();
                }
            }
            OrderDelivery orderDelivery = context.getDeliveryMap().get(context.getMerchant(merchantOrderEntity.getMerchantId()));
            // 物流邮费
            if (orderDelivery != null && StringUtils.isNotEmpty(orderDelivery.getSexpressCode()) && (orderDelivery.getDelivery() == null || DeliveryModeType.LOGISTICS.equals(orderDelivery.getDelivery().getType()))) {
                String expressCode = orderDelivery.getSexpressCode();
                double postage = sellercenterClient.calculatePostage(expressCode, merchantOrderEntity.getMerchantId(), weight, context.getConsignee().getCity());
                if (new Double(postage).longValue() > 0) {
                    merchantOrderEntity.getSubOrder().setPostage(postage);
                    merchantOrderEntity.getSubOrder().setCash(merchantOrderEntity.getCash() + postage);
                    merchantOrderEntity.getSubOrder().setSum(merchantOrderEntity.getSum() + postage);
                    //
                    orderEntity.getCollectOrder().setPostage(orderEntity.getCollectOrder().getPostage() + postage);
                    orderEntity.getCollectOrder().setCash(orderEntity.getCash() + postage);
                    orderEntity.getCollectOrder().setSum(orderEntity.getSum() + postage);
                    merchantOrderEntity.getSubOrder().setExpressCode(expressCode);
                    merchantOrderEntity.getSubOrder().setExpressName(sellercenterClient.getExpressName(expressCode));
                }
            } else {
                QMerchant merchant = context.getMerchant(merchantOrderEntity.getMerchantId());
                List<KeyValueVO> expressList = sellercenterClient.listExpress(merchant);
                AssertUtil.assertTrue(CollectionUtils.isEmpty(expressList), "购买商品商家[" + merchant.getName() + "]不包邮,请选择快递公司");
            }
        }
    }

    private void order(OrderEntity orderEntity) {

        synchronized (String.valueOf(orderEntity.getUserId())) {
            orderEntity.getCollectOrder().setOrderNumber(orderNumberService.generate());
            collectOrderService.add(orderEntity);
            for (MerchantOrderEntity merchantOrderEntity : orderEntity.getEntityList()) {
                merchantOrderEntity.getSubOrder().setOrderId(orderEntity.getId());
                merchantOrderEntity.getSubOrder().setOrderNumber(orderNumberService.generate());
                subOrderService.add(merchantOrderEntity, orderEntity.getOrderDate());
                for (OrderItemEntity orderItemEntity : merchantOrderEntity.getEntityList()) {
                    orderItemEntity.getOrderItem().setOrderId(orderEntity.getId());
                    orderItemEntity.getOrderItem().setSubOrderId(merchantOrderEntity.getId());
                    orderItemService.add(orderItemEntity, orderEntity.getOrderDate());
                    //
                    for (OrderItemDetailEntity orderItemDetailEntity : orderItemEntity.getEntityList()) {
                        orderItemDetailEntity.getOrderItemDetail().setOrderId(orderEntity.getId());
                        orderItemDetailEntity.getOrderItemDetail().setSubOrderId(merchantOrderEntity.getId());
                        orderItemDetailEntity.getOrderItemDetail().setOrderItemId(orderItemEntity.getId());
                        orderItemDetailService.add(orderItemDetailEntity, orderEntity.getOrderDate());
                    }
                }
                List<OrderDiscount> discountList = merchantOrderEntity.getDiscountList();
                for (OrderDiscount orderDiscount : discountList) {
                    orderDiscount.setOrderId(orderEntity.getId());
                    orderDiscount.setOrderDate(orderEntity.getOrderDate());
                    orderDiscount.setSubOrderId(merchantOrderEntity.getId());
                    orderDiscountService.add(orderDiscount);
                    if (orderDiscount.getType() == DiscountType.COUPON.getKey()) {
                        // 子单ID
                        QMyCoupon coupon = myClient.getMyCoupon(orderEntity.getUserId(), orderDiscount.getDiscountId());
                        myClient.useMyCoupon(orderEntity.getId(), orderDiscount.getDiscountId(), merchantOrderEntity.getId(), orderEntity.getOrderDate());
                        marketingClient.useCoupon(coupon.getCouponItemId());
                    }
                }
            }
        }
    }

    private OrderEntity initOrder(OrderContext context) {

        CollectOrder collectOrder = new CollectOrder();
        QUser user = context.getUser();
        AssertUtil.assertNotNull(user, "下订单用户不能为空.");
        QMyConsignee consignee = context.getConsignee();
        AssertUtil.assertNotNull(consignee, "收货人地址不能为空.");
        AssertUtil.assertNotEmpty(context.getMerchantList(), "卖家列表不能为空.");
        QMyInvoice invoice = context.getInvoice();
        // 发票
        if (invoice == null) {
            collectOrder.setNeedInvoice(NeedInvoiceType.NO.getKey());
            collectOrder.setInvoiceType(InvoiceType.COMMON.getKey());
            collectOrder.setInvoiceHead("");
            collectOrder.setInvoiceContent("");
        } else {
            collectOrder.setNeedInvoice(invoice.getMode().getKey());
            collectOrder.setInvoiceType(invoice.getType().getKey());
            collectOrder.setInvoiceHead(invoice.getHead());
            collectOrder.setInvoiceContent(invoice.getContent());
        }
        collectOrder.setId(-1L);
        collectOrder.setSum(0.0);
        collectOrder.setCash(0.0);
        collectOrder.setDiscount(100);
        collectOrder.setConsumption(0.0);
        collectOrder.setIntegral(0);
        collectOrder.setPostage(0.0);
        collectOrder.setCoupon(0.0);
        collectOrder.setUserId(user.getId());
        collectOrder.setTime(new Date());
        collectOrder.setOrderNumber("");
        collectOrder.setEmail(consignee.getEmail());
        collectOrder.setConsignee(consignee.getName());
        collectOrder.setMobile(consignee.getMobile());
        collectOrder.setAddress(StringUtil.nullToEmpty(consignee.getProvince()) + StringUtil.nullToEmpty(consignee.getCity()) + StringUtil.nullToEmpty(consignee.getDistrict()) + consignee.getAddress());
        collectOrder.setState(orderConfigService.getNormalInitOrderState());
        collectOrder.setEvaluation(EnableType.ENABLE.getKey());
        collectOrder.setAfterSale(EnableType.ENABLE.getKey());
        OrderEntity orderEntity = new OrderEntity(collectOrder);
        initOrder(orderEntity, context);
        return orderEntity;
    }

    private void initOrder(OrderEntity orderEntity, OrderContext context) {

        Map<Long, List<QUnifiedMerchandise>> map = new HashMap<Long, List<QUnifiedMerchandise>>();
        Set<QUnifiedMerchandise> merchandiseSet = context.getMerchandiseMap().keySet();
        for (QUnifiedMerchandise qUnifiedMerchandise : merchandiseSet) {
            List<QUnifiedMerchandise> list = map.get(qUnifiedMerchandise.getMerchantId());
            if (list == null) {
                list = new ArrayList<QUnifiedMerchandise>();
                map.put(qUnifiedMerchandise.getMerchantId(), list);
            }
            list.add(qUnifiedMerchandise);
        }
        List<MerchantOrderEntity> merchantOrderList = new ArrayList<MerchantOrderEntity>();
        for (Long merchantId : map.keySet()) {
            QMerchant merchant = sellercenterClient.getMerchant(merchantId);
            MerchantOrderEntity merchantOrderEntity = initOrder(orderEntity, merchant, map.get(merchantId), context);
            merchantOrderList.add(merchantOrderEntity);
        }
        orderEntity.setMerchantOrderList(merchantOrderList);
    }

    private MerchantOrderEntity initOrder(OrderEntity orderEntity, QMerchant merchant, List<QUnifiedMerchandise> merchandiseList, OrderContext context) {

        SubOrder subOrder = new SubOrder();
        subOrder.setId(-1L);
        subOrder.setPostage(0.0);
        subOrder.setCoupon(0.0);
        subOrder.setSum(0);
        subOrder.setCash(0);
        subOrder.setConsumption(0);
        subOrder.setExpressCode("");
        subOrder.setIntegral(0);
        subOrder.setPreferential(0);
        subOrder.setMerchantId(merchant.getId());
        subOrder.setExplain(context.getExplainMap().get(merchant));
        subOrder.setStoreId(-1L);
        subOrder.setState(orderConfigService.getNormalInitOrderState());
        subOrder.setOrderNumber("");
        //
        subOrder.setExpressCode(null);
        subOrder.setExpressName(null);
        subOrder.setExpressNumber(null);
        //
        OrderDelivery delivery = context.getDeliveryMap().get(merchant);
        // 配送
        if (delivery == null || delivery.getDelivery() == null) {
            subOrder.setDeliveryMode(DeliveryModeType.LOGISTICS.getKey());
            subOrder.setDeliveryTimeStr("");
            subOrder.setPickupAddressStr("");
        } else {
            subOrder.setDeliveryMode(delivery.getDelivery().getType().getKey());
            subOrder.setDeliveryTimeStr(delivery.getDelivery().getTimeStr());
            if (DeliveryModeType.PICKUP.equals(delivery.getDelivery().getType())) {
                long storeId = delivery.getDelivery().getStoreId();
                QStore store = sellercenterClient.getStore(storeId);
                if (store != null) {
                    subOrder.setPickupAddressStr(store.getAddress());
                    subOrder.setStoreId(store.getId());
                }
            }
        }
        if (subOrder.getStoreId() <= 0) {
            subOrder.setStoreId(calculateStore(merchant.getId(), context.getConsignee()));
        }
        MerchantOrderEntity merchantOrderEntity = new MerchantOrderEntity(orderEntity, subOrder);
        List<OrderItemEntity> orderItemList = new ArrayList<OrderItemEntity>();
        for (QUnifiedMerchandise qUnifiedMerchandise : merchandiseList) {
            OrderItemEntity orderItemEntity = initOrder(orderEntity, merchantOrderEntity, qUnifiedMerchandise, context.getMerchandiseMap().get(qUnifiedMerchandise), context);
            orderItemList.add(orderItemEntity);
        }
        merchantOrderEntity.setOrderItemList(orderItemList);
        return merchantOrderEntity;
    }

    private OrderItemEntity initOrder(OrderEntity orderEntity, MerchantOrderEntity merchantOrderEntity, QUnifiedMerchandise merchandise, int number, OrderContext context) {

        OrderItem orderItem = new OrderItem();
        orderItem.setId(-1L);
        orderItem.setSum(0);
        orderItem.setCash(0);
        orderItem.setConsumption(0);
        orderItem.setIntegral(0);
        orderItem.setPreferential(0);
        orderItem.setPreferentialStr("");
        //
        orderItem.setPurchase(merchandise.getPurchase());
        orderItem.setDiscount(merchandise.getDiscount());
        orderItem.setPrice(merchandise.getPrice());
        orderItem.setName(merchandise.getName());
        orderItem.setImage(merchandise.getImage());
        orderItem.setSence(merchandise.getSence());
        orderItem.setSnapshot("");
        orderItem.setState(orderConfigService.getNormalInitOrderState());
        //
        orderItem.setUnifiedMerchandiseId(merchandise.getId());
        orderItem.setNumber(number);
        //
        orderItem.setEvaluation(EnableType.ENABLE.getKey());
        orderItem.setAfterSale(EnableType.ENABLE.getKey());
        OrderItemEntity orderItemEntity = new OrderItemEntity(orderEntity, merchantOrderEntity, orderItem);
        List<QMerchandiseItem> itemList = merchandise.getList();
        List<OrderItemDetailEntity> list = new ArrayList<OrderItemDetailEntity>();
        for (QMerchandiseItem merchandiseItem : itemList) {
            OrderItemDetailEntity orderItemDetail = initOrder(orderEntity, merchantOrderEntity, orderItemEntity, merchandiseItem, number, context);
            list.add(orderItemDetail);
        }
        orderItemEntity.setOrderItemDetailList(list);
        return orderItemEntity;
    }

    private OrderItemDetailEntity initOrder(OrderEntity orderEntity, MerchantOrderEntity merchantOrderEntity, OrderItemEntity orderItemEntity, QMerchandiseItem merchandiseItem, int number, OrderContext context) {

        OrderItemDetail orderItemDetail = new OrderItemDetail();
        // 都是-1
        orderItemDetail.setId(-1L);
        orderItemDetail.setOrderId(orderEntity.getId());
        orderItemDetail.setSubOrderId(merchantOrderEntity.getId());
        orderItemDetail.setOrderItemId(orderItemEntity.getId());
        //
        orderItemDetail.setMerchandiseItemId(merchandiseItem.getId());
        orderItemDetail.setCode(merchandiseItem.getCode());
        orderItemDetail.setName(merchandiseItem.getName());
        orderItemDetail.setImage(merchandiseItem.getImage());
        orderItemDetail.setMerchantId(merchandiseItem.getMerchantId());
        orderItemDetail.setUnifiedMerchandiseId(merchandiseItem.getUnifiedMerchandiseId());
        orderItemDetail.setSpecifications(merchandiseItem.getSpecifications());
        orderItemDetail.setNumber(number);
        //
        orderItemDetail.setState(orderConfigService.getNormalInitOrderState());
        //
        OrderItemDetailEntity orderItemDetailEntity = new OrderItemDetailEntity(orderEntity, merchantOrderEntity, orderItemEntity, orderItemDetail);
        return orderItemDetailEntity;
    }

    private Long calculateStore(Long merchantId, QMyConsignee consignee) {

        List<QStore> storeList = sellercenterClient.listStoreByMerchant(merchantId);
        AssertUtil.assertNotEmpty(storeList, "商家门店不能为空." + merchantId);
        // 暂时处理获取总店
        Long storeId = storeList.get(0).getId();
        for (QStore qStore : storeList) {
            if (qStore.isRoot()) {
                storeId = qStore.getId();
                break;
            }
        }
        // TODO : 区域化,推送订单到门店算法
        return storeId;
    }

    @Override
    public boolean changeOrderCash(double sum) {

        throw new NotImplementedException();
    }

    @Transactional
    @Override
    public boolean modifyMerchantOrder(MerchantOrderEntity merchantOrderEntity, Map<QUnifiedMerchandise, Integer> addMerchandiseMap, Map<QUnifiedMerchandise, Integer> deleteMerchandiseMap) {

        QUser user = personalcenterClient.getUser(merchantOrderEntity.getOrder().getUserId());
        QMyConsignee myConsignee = OrderMyConsignee.get(merchantOrderEntity.getOrder().getConsignee(), merchantOrderEntity.getOrder().getMobile(), merchantOrderEntity.getOrder().getAddress());
        QMerchant merchant = sellercenterClient.getMerchant(merchantOrderEntity.getMerchantId());
        List<QMerchant> merchantList = new ArrayList<QMerchant>();
        merchantList.add(merchant);
        //
        List<OrderItemEntity> itemList = merchantOrderEntity.getEntityList();
        if (CollectionUtils.isNotEmpty(addMerchandiseMap.values())) {
            OrderContext addContext = new OrderContext();
            addContext.setUser(user);
            addContext.setConsignee(myConsignee);
            addContext.setDiscount(merchantOrderEntity.getOrder().getCollectOrder().getDiscount());
            addContext.setDeliveryMap(new HashMap<QMerchant, OrderDelivery>());
            addContext.setExplainMap(new HashMap<QMerchant, String>());
            addContext.setInvoice(null);
            addContext.setMyCouponMap(new HashMap<QMerchant, List<QMyCoupon>>());
            addContext.setMerchandiseMap(addMerchandiseMap);
            addContext.setMerchantList(merchantList);
            // 初始化订单
            OrderEntity addOrderEntity = initOrder(addContext);
            // 2 算钱
            calculate(merchantOrderEntity, addOrderEntity, addContext);
            if (addOrderEntity.getNumber() > 1000) {
                throw new OrderformException("订单商品数量不能超过1000个");
            }
            MerchantOrderEntity addMerchantOrderEntity = addOrderEntity.getEntityList().get(0);
            List<OrderItemEntity> addItemList = addMerchantOrderEntity.getEntityList();
            for (OrderItemEntity addOrderItemEntity : addItemList) {
                boolean add = true;
                for (OrderItemEntity orderItemEntity : itemList) {
                    if (orderItemEntity.getUnifiedMerchandiseId() == addOrderItemEntity.getUnifiedMerchandiseId()) {
                        add = false;
                        orderItemEntity.getOrderItem().setNumber(orderItemEntity.getNumber() + addOrderItemEntity.getNumber());
                        orderItemEntity.getOrderItem().setSum(orderItemEntity.getSum() + addOrderItemEntity.getSum());
                        orderItemEntity.getOrderItem().setCash(orderItemEntity.getCash() + addOrderItemEntity.getCash());
                        orderItemEntity.getOrderItem().setPreferential(orderItemEntity.getPreferential() + addOrderItemEntity.getPreferential());
                        break;
                    }
                }
                if (add) {
                    addOrderItemEntity.setOrder(merchantOrderEntity.getOrder());
                    addOrderItemEntity.setMerchantOrder(merchantOrderEntity);
                    merchantOrderEntity.getEntityList().add(addOrderItemEntity);
                }
            }
            //
            merchantOrderEntity.getSubOrder().setSum(merchantOrderEntity.getSum() + addMerchantOrderEntity.getSum());
            merchantOrderEntity.getSubOrder().setCash(merchantOrderEntity.getCash() + addMerchantOrderEntity.getCash());
            merchantOrderEntity.getSubOrder().setPreferential(merchantOrderEntity.getPreferential() + addMerchantOrderEntity.getPreferential());
            //
            merchantOrderEntity.getOrder().getCollectOrder().setSum(merchantOrderEntity.getOrder().getSum() + addMerchantOrderEntity.getSum());
            merchantOrderEntity.getOrder().getCollectOrder().setCash(merchantOrderEntity.getOrder().getCash() + addMerchantOrderEntity.getCash());
            merchantOrderEntity.getOrder().getCollectOrder().setPreferential(merchantOrderEntity.getOrder().getCash() + addMerchantOrderEntity.getPreferential());
        }
        if (CollectionUtils.isNotEmpty(deleteMerchandiseMap.values())) {
            //
            OrderContext deleteCntext = new OrderContext();
            deleteCntext.setUser(user);
            deleteCntext.setConsignee(myConsignee);
            deleteCntext.setDiscount(merchantOrderEntity.getOrder().getCollectOrder().getDiscount());
            deleteCntext.setDeliveryMap(new HashMap<QMerchant, OrderDelivery>());
            deleteCntext.setExplainMap(new HashMap<QMerchant, String>());
            deleteCntext.setInvoice(null);
            deleteCntext.setMyCouponMap(new HashMap<QMerchant, List<QMyCoupon>>());
            deleteCntext.setMerchandiseMap(deleteMerchandiseMap);
            deleteCntext.setMerchantList(merchantList);
            // 初始化订单
            OrderEntity deleteOrderEntity = initOrder(deleteCntext);
            calculate(merchantOrderEntity, deleteOrderEntity, deleteCntext);
            if (deleteOrderEntity.getNumber() > 1000) {
                throw new OrderformException("订单商品数量不能超过1000个");
            }
            //
            MerchantOrderEntity deleteMerchantOrderEntity = deleteOrderEntity.getEntityList().get(0);
            List<OrderItemEntity> deleteItemList = deleteMerchantOrderEntity.getEntityList();
            for (OrderItemEntity deleteOrderItemEntity : deleteItemList) {
                boolean delete = false;
                for (OrderItemEntity orderItemEntity : itemList) {
                    if (orderItemEntity.getUnifiedMerchandiseId() == deleteOrderItemEntity.getUnifiedMerchandiseId()) {
                        delete = true;
                        AssertUtil.assertTrue(orderItemEntity.getNumber() - deleteOrderItemEntity.getNumber() >= 0, "商品不够删除." + orderItemEntity.getName());
                        orderItemEntity.getOrderItem().setNumber(orderItemEntity.getNumber() - deleteOrderItemEntity.getNumber());
                        orderItemEntity.getOrderItem().setSum(orderItemEntity.getSum() - deleteOrderItemEntity.getSum());
                        orderItemEntity.getOrderItem().setCash(orderItemEntity.getCash() - deleteOrderItemEntity.getCash());
                        orderItemEntity.getOrderItem().setPreferential(orderItemEntity.getPreferential() - deleteOrderItemEntity.getPreferential());
                        break;
                    }
                }
                AssertUtil.assertTrue(delete, "减少或删除商品不在订单里" + deleteOrderItemEntity.getName());
            }
            //
            merchantOrderEntity.getSubOrder().setSum(merchantOrderEntity.getSum() - deleteMerchantOrderEntity.getSum());
            merchantOrderEntity.getSubOrder().setCash(merchantOrderEntity.getCash() - deleteMerchantOrderEntity.getCash());
            merchantOrderEntity.getSubOrder().setPreferential(merchantOrderEntity.getPreferential() - deleteMerchantOrderEntity.getPreferential());
            AssertUtil.assertTrue(new Double(merchantOrderEntity.getCash() * 100).longValue() > 0, "修改订单后支付现金不允许小于零.");
            //
            merchantOrderEntity.getOrder().getCollectOrder().setSum(merchantOrderEntity.getOrder().getSum() - deleteMerchantOrderEntity.getSum());
            merchantOrderEntity.getOrder().getCollectOrder().setCash(merchantOrderEntity.getOrder().getCash() - deleteMerchantOrderEntity.getCash());
            merchantOrderEntity.getOrder().getCollectOrder().setPreferential(merchantOrderEntity.getOrder().getCash() - deleteMerchantOrderEntity.getPreferential());
        }
        AssertUtil.assertTrue(new Double(merchantOrderEntity.getOrder().getCash() * 100).longValue() > 0, "修改订单后支付现金不允许小于零.");
        //
        synchronized (String.valueOf(merchantOrderEntity.getOrder().getUserId())) {
            for (OrderItemEntity orderItemEntity : merchantOrderEntity.getEntityList()) {
                if (orderItemEntity.getId() > 0) {
                    orderItemService.update(orderItemEntity.getOrderItem(), merchantOrderEntity.getOrder().getOrderDate());
                    for (OrderItemDetailEntity qOrderItemDetail : orderItemEntity.getEntityList()) {
                        //
                        qOrderItemDetail.getOrderItemDetail().setNumber(orderItemEntity.getNumber());
                        orderItemDetailService.update(qOrderItemDetail.getOrderItemDetail(), merchantOrderEntity.getOrder().getOrderDate());
                    }
                } else {
                    orderItemEntity.getOrderItem().setOrderId(merchantOrderEntity.getOrder().getId());
                    orderItemEntity.getOrderItem().setSubOrderId(merchantOrderEntity.getId());
                    orderItemService.add(orderItemEntity, merchantOrderEntity.getOrder().getOrderDate());
                    //
                    for (OrderItemDetailEntity orderItemDetailEntity : orderItemEntity.getEntityList()) {
                        orderItemDetailEntity.getOrderItemDetail().setOrderId(merchantOrderEntity.getOrder().getId());
                        orderItemDetailEntity.getOrderItemDetail().setSubOrderId(merchantOrderEntity.getId());
                        orderItemDetailEntity.getOrderItemDetail().setOrderItemId(orderItemEntity.getId());
                        orderItemDetailService.add(orderItemDetailEntity, merchantOrderEntity.getOrder().getOrderDate());
                    }
                }
            }
            subOrderService.update(merchantOrderEntity.getSubOrder(), merchantOrderEntity.getOrder().getOrderDate());
            collectOrderService.update(merchantOrderEntity.getOrder().getCollectOrder());
            return true;
        }
    }

    private void calculate(MerchantOrderEntity originalMerchantOrderEntity, OrderEntity orderEntity, OrderContext context) {

        int discount = context.getDiscount();
        orderEntity.getCollectOrder().setDiscount(discount);
        // 计算正常折扣价
        List<MerchantOrderEntity> subOrderList = orderEntity.getEntityList();
        for (MerchantOrderEntity merchantOrderEntity : subOrderList) {
            List<OrderItemEntity> orderItemList = merchantOrderEntity.getEntityList();
            for (OrderItemEntity orderItemEntity : orderItemList) {
                orderItemEntity.getOrderItem().setDiscount(orderItemEntity.getDiscount() * discount / 100);
                // VIP价格
                Double vipDiscount = commoditycenterClient.getVipDiscount(context.getUser().getId(), orderItemEntity.getOrderItem().getUnifiedMerchandiseId());
                if (vipDiscount != null && new Double(vipDiscount * 100).longValue() > 0 && vipDiscount < orderItemEntity.getOrderItem().getDiscount()) {
                    orderItemEntity.getOrderItem().setDiscount(vipDiscount);
                }
                double originalDiscount = calculateItemDiscount(orderItemEntity, originalMerchantOrderEntity);
                if (originalDiscount > 0) {
                    orderItemEntity.getOrderItem().setDiscount(originalDiscount);
                }
                double sum = orderItemEntity.getOrderItem().getDiscount() * orderItemEntity.getNumber();
                orderItemEntity.getOrderItem().setSum(sum);
                orderItemEntity.getOrderItem().setCash(sum);
                orderItemEntity.getOrderItem().setPreferential(orderItemEntity.getPrice() * orderItemEntity.getNumber() - sum);
                //
                merchantOrderEntity.getSubOrder().setSum(merchantOrderEntity.getSum() + orderItemEntity.getSum());
                merchantOrderEntity.getSubOrder().setCash(merchantOrderEntity.getSubOrder().getCash() + orderItemEntity.getOrderItem().getCash());
                merchantOrderEntity.getSubOrder().setPreferential(merchantOrderEntity.getSubOrder().getPreferential() + orderItemEntity.getOrderItem().getPreferential());
            }
            orderEntity.getCollectOrder().setSum(orderEntity.getSum() + merchantOrderEntity.getSum());
            orderEntity.getCollectOrder().setCash(orderEntity.getCash() + merchantOrderEntity.getSubOrder().getCash());
            orderEntity.getCollectOrder().setPreferential(orderEntity.getPreferential() + merchantOrderEntity.getSubOrder().getPreferential());
        }
    }

    private double calculateItemDiscount(OrderItemEntity orderItemEntity, MerchantOrderEntity originalMerchantOrderEntity) {

        for (OrderItemEntity o : originalMerchantOrderEntity.getEntityList()) {
            if (orderItemEntity.getUnifiedMerchandiseId() == o.getUnifiedMerchandiseId()) {
                return o.getDiscount();
            }
        }
        return -1;
    }
}
