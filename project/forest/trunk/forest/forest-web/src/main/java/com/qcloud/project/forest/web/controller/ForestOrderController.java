package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.goods.exception.CommoditycenterException;
import com.qcloud.component.marketing.MarketingClient;
import com.qcloud.component.marketing.QCoupon;
import com.qcloud.component.my.DeliveryModeType;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.QMyConsignee;
import com.qcloud.component.my.QMyCoupon;
import com.qcloud.component.my.QMyDelivery;
import com.qcloud.component.my.QMyInvoice;
import com.qcloud.component.my.model.DeliveryMode;
import com.qcloud.component.my.model.MyAfterSale;
import com.qcloud.component.my.service.DeliveryModeService;
import com.qcloud.component.my.service.MyAfterSaleService;
import com.qcloud.component.my.web.form.DeliveryForm;
import com.qcloud.component.orderform.OrderContext;
import com.qcloud.component.orderform.OrderContext.OrderDelivery;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.engine.OrderService;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.OrderItemDetailEntity;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.web.form.MerchandiseDetail;
import com.qcloud.component.orderform.web.form.OrderForm;
import com.qcloud.component.orderform.web.form.PrepareOrderForm;
import com.qcloud.component.orderform.web.vo.OrderCouponVO;
import com.qcloud.component.orderform.web.vo.OrderExpressVO;
import com.qcloud.component.orderform.web.vo.personal.OrderItemVO;
import com.qcloud.component.orderform.web.vo.pre.PreMerchantVO;
import com.qcloud.component.orderform.web.vo.pre.PreOrderCombinationVO;
import com.qcloud.component.orderform.web.vo.pre.PreOrderItemVO;
import com.qcloud.component.orderform.web.vo.pre.PreOrderMVO;
import com.qcloud.component.organization.OrganizationClient;
import com.qcloud.component.organization.QDepartment;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QGrade;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.piratesship.web.form.ListForm;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicservice.PayClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.key.TypeEnum.MerchantIsIncludePost;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.ExpressQueryVO;
import com.qcloud.project.forest.model.ForestOrder;
import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.service.ExpressQueryService;
import com.qcloud.project.forest.service.ForestOrderService;
import com.qcloud.project.forest.service.GiftCouponUserService;
import com.qcloud.project.forest.web.handler.ForestOrderHandler;
import com.qcloud.project.forest.web.vo.ForestOrderVO;
import com.qcloud.project.forest.web.vo.OrderGiftCouponVO;

@Controller
@RequestMapping(value = ForestOrderController.DIR)
public class ForestOrderController {

    public static final String    DIR = "/forestOrder";

    @Autowired
    private ForestOrderService    forestOrderService;

    @Autowired
    private ForestOrderHandler    forestOrderHandler;

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Autowired
    private SellercenterClient    sellercenterClient;

    @Autowired
    private OrderService          orderService;

    @Autowired
    private MyClient              myClient;

    @Autowired
    private PayClient             payClient;

    @Autowired
    private FileSDKClient         fileSDKClient;

    @Autowired
    private GiftCouponUserService giftCouponUserService;

    @Autowired
    private OrderformClient       orderformClient;

    @Autowired
    private DeliveryModeService   deliveryModeService;

    @Autowired
    private OrganizationClient    organizationClient;

    @Autowired
    private ExpressQueryService   expressQueryService;

    @Autowired
    private MyAfterSaleService    myAfterSaleService;

    @Autowired
    private MarketingClient       marketingClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView prepareOrder(HttpServletRequest request, PrepareOrderForm orderForm) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        OrderContext context = new OrderContext();
        context.setUser(user);
        QMyConsignee myConsignee = myClient.getDefaultConsignee(user.getId());
        context.setConsignee(myConsignee);
        Set<QMerchant> merchantSet = new HashSet<QMerchant>();
        AssertUtil.assertNotEmpty(orderForm.getMerchandiseList(), "下订单,商品列表不能为空.");
        List<MerchandiseDetail> merchandiseList = orderForm.getMerchandiseList();
        //
        Map<QUnifiedMerchandise, Integer> merchandiseMap = new HashMap<QUnifiedMerchandise, Integer>();
        Set<Long> freeMerchandiseSet = new HashSet<Long>();
        // TODO
        Map<QUnifiedMerchandise, Integer> freeCombinationMap = new HashMap<QUnifiedMerchandise, Integer>();
        Map<QUnifiedMerchandise, Map<QUnifiedMerchandise, Integer>> freeMerchandiseListMap = new HashMap<QUnifiedMerchandise, Map<QUnifiedMerchandise, Integer>>();
        for (MerchandiseDetail merchandiseDetail : merchandiseList) {
            if (merchandiseDetail.getCombinationMerchandiseId() == -1) {// 单品和固定搭配
                QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseDetail.getUnifiedMerchandiseId());
                AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在." + merchandiseDetail.getUnifiedMerchandiseId());
                QMerchant merchant = sellercenterClient.getMerchant(unifiedMerchandise.getMerchantId());
                merchantSet.add(merchant);
                merchandiseMap.put(unifiedMerchandise, merchandiseDetail.getNumber());
            } else {// 自由组合
                QUnifiedMerchandise freeCombinationMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseDetail.getCombinationMerchandiseId());
                AssertUtil.assertNotNull(freeCombinationMerchandise, "自由搭配组合不存在." + merchandiseDetail.getCombinationMerchandiseId());
                freeMerchandiseSet.add(freeCombinationMerchandise.getId());
                QMerchant merchant = sellercenterClient.getMerchant(freeCombinationMerchandise.getMerchantId());
                merchantSet.add(merchant);
            }
        }
        for (Long freeCombinationMerchandiseId : freeMerchandiseSet) {
            QUnifiedMerchandise freeCombinationMerchandise = commoditycenterClient.getUnifiedMerchandise(freeCombinationMerchandiseId);
            int matchNumber = 0;// 判断
            int combinationNumber = 0;
            Map<QUnifiedMerchandise, Integer> freeMerchandiseList = new HashMap<QUnifiedMerchandise, Integer>();
            for (MerchandiseDetail merchandiseDetail : merchandiseList) {
                for (QUnifiedMerchandise merchandiseItems : freeCombinationMerchandise.getList()) {
                    if (merchandiseDetail.getCombinationMerchandiseId().longValue() == freeCombinationMerchandiseId.longValue() && merchandiseDetail.getUnifiedMerchandiseId().longValue() == merchandiseItems.getId().longValue()) {
                        freeMerchandiseList.put(merchandiseItems, merchandiseDetail.getNumber());
                        combinationNumber = merchandiseDetail.getCombinationMerchandiseNumber();
                        matchNumber++;
                    }
                }
            }
            if (matchNumber == freeCombinationMerchandise.getList().size()) {// 自由搭配商品全买了
                merchandiseMap.put(freeCombinationMerchandise, combinationNumber);
            } else {
                freeCombinationMap.put(freeCombinationMerchandise, combinationNumber);
                freeMerchandiseListMap.put(freeCombinationMerchandise, freeMerchandiseList);
            }
        }
        context.setMerchandiseMap(merchandiseMap);
        context.setFreeCombinationMap(freeCombinationMap);// 自由搭配商品集合：id + number
        context.setFreeMerchandiseListMap(freeMerchandiseListMap);// 自由搭配商品的子商品集合 combination : unifiedMerchandiseId + number
        context.setMerchantList(new ArrayList<QMerchant>(merchantSet));
        judgePrepareMerchandiseStock(context);// 判断
        // AssertUtil.assertTrue(false, "测试用抛异常----");
        // 计算邮费 begin TODO
        Map<QMerchant, List<OrderExpressVO>> expressMap = calculatePreparePostage(context, orderForm.getExpressCode());
        // 计算邮费 end
        OrderEntity orderEntity = orderService.prepareOrderNormal(context);
        PreOrderMVO preOrderMVO = new PreOrderMVO();
        List<PreMerchantVO> preMerchantList = new ArrayList<PreMerchantVO>();
        List<MerchantOrderEntity> merchantOrderList = orderEntity.getEntityList();
        for (MerchantOrderEntity merchantOrderEntity : merchantOrderList) {
            PreMerchantVO preMerchantVO = new PreMerchantVO();
            QMerchant merchant = sellercenterClient.getMerchant(merchantOrderEntity.getMerchantId());
            preMerchantVO.setMerchantName(merchant == null ? "" : merchant.getName());
            preMerchantVO.setImage(fileSDKClient.getFileServerUrl() + StringUtil.nullToEmpty(merchant.getImage()));
            preMerchantVO.setMerchantId(merchant.getId());
            preMerchantVO.setNumber(merchantOrderEntity.getNumber());
            preMerchantVO.setSum(merchantOrderEntity.getSum());
            preMerchantVO.setCash(merchantOrderEntity.getCash());
            preMerchantVO.setPostage(merchantOrderEntity.getPostage());
            preMerchantList.add(preMerchantVO);
            List<OrderItemEntity> itemList = merchantOrderEntity.getEntityList();
            for (OrderItemEntity orderItemEntity : itemList) {
                //
                QUnifiedMerchandise merchandise = commoditycenterClient.getUnifiedMerchandise(orderItemEntity.getUnifiedMerchandiseId());
                // 单品
                if (merchandise.getType().equals(UnifiedMerchandiseType.SINGLE)) {
                    PreOrderItemVO preOrderItemVO = new PreOrderItemVO();
                    preOrderItemVO.setDiscount(orderItemEntity.getDiscount());
                    preOrderItemVO.setUnifiedMerchandiseId(orderItemEntity.getUnifiedMerchandiseId());
                    preOrderItemVO.setImage(fileSDKClient.getFileServerUrl() + orderItemEntity.getImage());
                    preOrderItemVO.setPrice(orderItemEntity.getPrice());
                    preOrderItemVO.setName(orderItemEntity.getName());
                    preOrderItemVO.setNumber(orderItemEntity.getNumber());
                    for (QUnifiedMerchandise unifiedMerchandise : merchandiseMap.keySet()) {
                        if (unifiedMerchandise.getId() == orderItemEntity.getUnifiedMerchandiseId()) {
                            preOrderItemVO.setSpecifications(StringUtil.nullToEmpty(unifiedMerchandise.getSpecifications()));
                            preOrderItemVO.setUnit(unifiedMerchandise.getUnit());
                            break;
                        }
                    }
                    preOrderItemVO.setSum(orderItemEntity.getSum());
                    preMerchantVO.getImageList().add(fileSDKClient.getFileServerUrl() + orderItemEntity.getImage());
                    preMerchantVO.getPreOrderItemList().add(preOrderItemVO);
                } else if (merchandise.getType().equals(UnifiedMerchandiseType.COMBINATION)) {// 组合
                    PreOrderCombinationVO preOrderCombinationVO = new PreOrderCombinationVO();
                    preOrderCombinationVO.setDiscount(orderItemEntity.getSum());
                    preOrderCombinationVO.setUnifiedMerchandiseId(orderItemEntity.getUnifiedMerchandiseId());
                    preOrderCombinationVO.setImage(fileSDKClient.getFileServerUrl() + orderItemEntity.getImage());
                    preOrderCombinationVO.setPrice(orderItemEntity.getSum());
                    preOrderCombinationVO.setName(orderItemEntity.getName());
                    preOrderCombinationVO.setNumber(orderItemEntity.getNumber());
                    //
                    List<OrderItemDetailEntity> orderItemDetailsList = orderItemEntity.getEntityList();
                    for (OrderItemDetailEntity orderItemDetailEntity : orderItemDetailsList) {
                        QUnifiedMerchandise merchandiseItem = commoditycenterClient.getUnifiedMerchandise(orderItemDetailEntity.getUnifiedMerchandiseId());
                        // orderItemDetails拿过来
                        OrderItemVO orderItem = new OrderItemVO();
                        orderItem.setCode(orderItemDetailEntity.getCode());
                        orderItem.setName(orderItemDetailEntity.getName());
                        orderItem.setDiscount(orderItemDetailEntity.getDiscount());
                        orderItem.setImage(fileSDKClient.getFileServerUrl() + orderItemDetailEntity.getImage());
                        orderItem.setUnifiedMerchandiseId(orderItemDetailEntity.getUnifiedMerchandiseId());
                        orderItem.setSpecifications(orderItemDetailEntity.getSpecifications());
                        orderItem.setAfterSale(false);
                        orderItem.setNumber(orderItemDetailEntity.getNumber());
                        orderItem.setEvaluation(false);
                        // 统一商品拿过来
                        orderItem.setPrice(merchandiseItem.getPrice());
                        orderItem.setUnit(merchandiseItem.getUnit());
                        preOrderCombinationVO.getOrderItemList().add(orderItem);
                    }
                    // for (QUnifiedMerchandise merchandiseItem : merchandise.getList()) {
                    // OrderItemVO orderItem = new OrderItemVO();
                    // orderItem.setCode(merchandiseItem.getCode());
                    // orderItem.setName(merchandiseItem.getName());
                    // orderItem.setDiscount(merchandiseItem.getDiscount());
                    // orderItem.setPrice(merchandiseItem.getPrice());
                    // orderItem.setImage(fileSDKClient.getFileServerUrl() + merchandiseItem.getImage());
                    // orderItem.setUnifiedMerchandiseId(merchandiseItem.getId());
                    // orderItem.setSpecifications(merchandiseItem.getSpecifications());
                    // orderItem.setAfterSale(false);
                    // orderItem.setNumber(merchandiseItem.getNumber());
                    // orderItem.setEvaluation(false);
                    // orderItem.setUnit(merchandiseItem.getUnit());
                    // preOrderCombinationVO.getOrderItemList().add(orderItem);
                    // }
                    //
                    preMerchantVO.getCombinationItemList().add(preOrderCombinationVO);
                }
            }
            // 物流
            preMerchantVO.setExpressList(expressMap.get(merchant));
            if (CollectionUtils.isNotEmpty(preMerchantVO.getExpressList())) {
                for (OrderExpressVO orderExpress : preMerchantVO.getExpressList()) {
                    if (orderExpress.getExpressCode().equals(merchantOrderEntity.getSubOrder().getExpressCode())) {
                        preMerchantVO.setExpress(orderExpress);
                        break;
                    }
                }
            }
            // 优惠劵
            List<OrderCouponVO> couponList = calculateUserCoupon(user.getId(), merchantOrderEntity.getMerchantId(), merchantOrderEntity.getCash() - merchantOrderEntity.getPostage());
            preMerchantVO.setCouponList(couponList);
        }
        preOrderMVO.setPreMerchantList(preMerchantList);
        preOrderMVO.setSum(NumberUtil.scale(orderEntity.getSum(), 2));
        preOrderMVO.setCash(NumberUtil.scale(orderEntity.getCash(), 2));
        preOrderMVO.setPostage(NumberUtil.scale(orderEntity.getPostage(), 2));
        preOrderMVO.setPreferential(NumberUtil.scale(orderEntity.getPreferential(), 2));
        preOrderMVO.setTotalNumber(orderEntity.getNumber());
        // 赠品券
        List<OrderGiftCouponVO> giftCouponList = calculateGiftCoupon(user.getId());
        // List<OrderGiftCouponVO> giftCouponList = new ArrayList<OrderGiftCouponVO>();
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取下单数据成功");
        view.addObject("preOrder", preOrderMVO);
        view.addObject("discount", context.getDiscount());
        view.addObject("giftCouponList", giftCouponList);
        // 会员折扣信息
        QGrade grade = user.getGrade();
        view.addObject("discountMessage", "会员尊享:" + grade.getDiscount() * 100 / 1000 + "折优惠");
        return view;
    }

    // 判断预下单商品库存
    public void judgePrepareMerchandiseStock(OrderContext context) {

        Map<QUnifiedMerchandise, Integer> merchandiseMap = context.getMerchandiseMap();
        for (Map.Entry<QUnifiedMerchandise, Integer> entry : merchandiseMap.entrySet()) {
            AssertUtil.assertTrue(entry.getKey().getStock() >= entry.getValue(), "抱歉,订单商品:" + entry.getKey().getName() + "数量库存只剩下" + entry.getKey().getStock() + "了哦.");
        }
        Map<QUnifiedMerchandise, Integer> freeMerchandiseMap = context.getFreeCombinationMap();
        for (Map.Entry<QUnifiedMerchandise, Integer> entry : freeMerchandiseMap.entrySet()) {
            AssertUtil.assertTrue(entry.getKey().getStock() >= entry.getValue(), "抱歉,订单商品:" + entry.getKey().getName() + "数量库存只剩下" + entry.getKey().getStock() + "了哦.");
        }
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView order(HttpServletRequest request, OrderForm orderForm, Long giftCouponUser, String prove, DeliveryForm deliveryForm) {

        AssertUtil.assertNotNull(orderForm.getConsigneeId(), "收货人信息数据不能为空.");
        AssertUtil.assertNotNull(orderForm.getInvoiceId(), "发票信息数据不能为空.");
        AssertUtil.assertNotEmpty(deliveryForm.getTime(), "配送时间/自提时间不能为空.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        QMyConsignee consignee = myClient.getConsignee(orderForm.getConsigneeId());
        AssertUtil.assertNotNull(consignee, "收货人信息不存在." + orderForm.getConsigneeId());
        AssertUtil.assertNotEmpty(orderForm.getMerchandiseList(), "下订单,商品列表不能为空.");
        // 组织数据
        OrderContext orderContext = formToContext(user, orderForm, deliveryForm);
        // 下单
        QOrder orderEntity = forestOrderService.order(orderContext, giftCouponUser, user, prove);
        FrontAjaxView view = new FrontAjaxView();
        myClient.setDefaultInvoice(user.getId());
        view.setMessage("下订单成功");
        view.addObject("result", Boolean.TRUE.toString());
        view.addObject("orderId", orderEntity.getId());
        view.addObject("sum", orderEntity.getSum());
        view.addObject("cash", orderEntity.getCash());
        view.addObject("orderNumber", orderEntity.getOrderNumber());
        //
        int limitOrderTimeMinutes = payClient.getPayMinutes();
        view.addObject("payMinutes", limitOrderTimeMinutes);
        // 支付时间请录入小时的整数
        view.addObject("payHourDesc", "请您在" + new Double(limitOrderTimeMinutes / 60).intValue() + "小时内支付!");
        //
        view.addObject("orderDate", DateUtil.date2String(orderEntity.getOrderDate(), DateUtil.FORMAT_STRING));
        // 会员折扣信息
        QGrade grade = user.getGrade();
        view.addObject("discountMessage", "会员尊享:" + NumberUtil.scale(grade.getDiscount() * 100 / 1000, 2) + "折优惠");
        return view;
    }

    private Map<QMerchant, List<OrderExpressVO>> calculatePreparePostage(OrderContext context, String expressCode) {

        Map<QMerchant, List<OrderExpressVO>> expressMap = new HashMap<QMerchant, List<OrderExpressVO>>();
        Map<QMerchant, OrderDelivery> deliveryMap = new HashMap<QMerchant, OrderContext.OrderDelivery>();
        List<QMerchant> merchantList = context.getMerchantList();
        for (QMerchant qMerchant : merchantList) {
            double weight = 0.0;
            for (QUnifiedMerchandise unifiedMerchandise : context.getMerchandiseMap().keySet()) {
                int number = context.getMerchandiseMap().get(unifiedMerchandise);
                // 商家不包邮 && 商品不包邮 ,则计算邮费
                if (qMerchant.getIsIncludePost() == MerchantIsIncludePost.NO.getKey()) {
                    if (!unifiedMerchandise.getIsIncludePost()) {
                        if (qMerchant.getId() == unifiedMerchandise.getMerchantId()) {
                            weight += unifiedMerchandise.getWeight() * number;
                        }
                    }
                }
            }
            List<KeyValueVO> expressList = sellercenterClient.listExpress(qMerchant);
            List<OrderExpressVO> expressVOList = new ArrayList<OrderExpressVO>();
            OrderExpressVO low = null;
            for (KeyValueVO keyValueVO : expressList) {
                double postage = sellercenterClient.calculatePostage(keyValueVO.getKey(), qMerchant.getId(), weight, context.getConsignee().getCity());
                OrderExpressVO orderExpressVO = new OrderExpressVO();
                orderExpressVO.setExpressCode(keyValueVO.getKey());
                orderExpressVO.setMerchantId(qMerchant.getId());
                orderExpressVO.setName(keyValueVO.getValue());
                orderExpressVO.setPostage(postage);
                expressVOList.add(orderExpressVO);
                if (StringUtils.isEmpty(expressCode)) {
                    if (low == null) {
                        low = orderExpressVO;
                    } else if (low.getPostage() > orderExpressVO.getPostage()) {
                        low = orderExpressVO;
                    }
                } else {
                    if (expressCode.equals(orderExpressVO.getExpressCode())) {
                        low = orderExpressVO;
                    }
                }
            }
            if (low != null) {
                OrderDelivery orderDelivery = new OrderDelivery();
                orderDelivery.setSexpressCode(low.getExpressCode());
                deliveryMap.put(qMerchant, orderDelivery);
            }
            expressMap.put(qMerchant, expressVOList);
        }
        context.setDeliveryMap(deliveryMap);
        return expressMap;
    }

    private List<OrderGiftCouponVO> calculateGiftCoupon(long userId) {

        List<GiftCouponUser> list = giftCouponUserService.listCanUse(userId);
        List<OrderGiftCouponVO> voList = new ArrayList<OrderGiftCouponVO>();
        for (GiftCouponUser coupon : list) {
            OrderGiftCouponVO vo = new OrderGiftCouponVO();
            vo.setGiftCouponId(coupon.getGiftCouponId());
            vo.setId(coupon.getId());
            vo.setImage(fileSDKClient.getFileServerUrl() + coupon.getImage());
            vo.setInValidDateStr(DateUtil.date2String(coupon.getInValidDate(), "yyyy-MM-dd"));
            vo.setName(coupon.getName());
            vo.setValidDateStr(DateUtil.date2String(coupon.getValidDate(), "yyyy-MM-dd"));
            voList.add(vo);
        }
        return voList;
    }

    private List<OrderCouponVO> calculateUserCoupon(long userId, long merchantId, double sum) {

        List<QMyCoupon> list = myClient.listMyCouponCanUse(userId, merchantId, sum);
        List<OrderCouponVO> voList = new ArrayList<OrderCouponVO>();
        for (QMyCoupon qMyCoupon : list) {
            OrderCouponVO vo = new OrderCouponVO();
            vo.setCode(qMyCoupon.getCode());
            vo.setId(qMyCoupon.getId());
            vo.setLimitPrice(qMyCoupon.getLimitPrice());
            vo.setMerchantId(qMyCoupon.getMerchantId());
            vo.setName(qMyCoupon.getName());
            vo.setPrice(qMyCoupon.getPrice());
            vo.setValidDate(qMyCoupon.getValidDate());
            vo.setInvalidDate(qMyCoupon.getInValidDate());
            vo.setValidDateStr(DateUtil.date2String(qMyCoupon.getValidDate(), "yyyy-MM-dd"));
            vo.setInvalidDateStr(DateUtil.date2String(qMyCoupon.getInValidDate(), "yyyy-MM-dd"));
            QCoupon coupon = marketingClient.getCoupon(qMyCoupon.getCouponId());
            vo.setDescription(coupon == null ? "" : coupon.getDescription());
            voList.add(vo);
        }
        return voList;
    }

    private OrderContext formToContext(QUser user, OrderForm orderForm, DeliveryForm deliveryForm) {

        AssertUtil.assertNotNull(orderForm.getConsigneeId(), "收货人信息数据不能为空.");
        AssertUtil.assertNotNull(orderForm.getInvoiceId(), "发票信息数据不能为空.");
        QMyConsignee consignee = myClient.getConsignee(orderForm.getConsigneeId());
        AssertUtil.assertNotNull(consignee, "收货人信息不存在." + orderForm.getConsigneeId());
        AssertUtil.assertNotEmpty(orderForm.getMerchandiseList(), "下订单,商品列表不能为空.");
        //
        OrderContext context = new OrderContext();
        context.setUser(user);
        context.setConsignee(consignee);
        //
        Set<QMerchant> merchantSet = new HashSet<QMerchant>();
        List<MerchandiseDetail> merchandiseList = orderForm.getMerchandiseList();
        Map<QUnifiedMerchandise, Integer> merchandiseMap = new HashMap<QUnifiedMerchandise, Integer>();
        Set<Long> freeMerchandiseSet = new HashSet<Long>();
        // TODO
        Map<QUnifiedMerchandise, Integer> freeCombinationMap = new HashMap<QUnifiedMerchandise, Integer>();
        Map<QUnifiedMerchandise, Map<QUnifiedMerchandise, Integer>> freeMerchandiseListMap = new HashMap<QUnifiedMerchandise, Map<QUnifiedMerchandise, Integer>>();
        for (MerchandiseDetail merchandiseDetail : merchandiseList) {
            if (merchandiseDetail.getCombinationMerchandiseId() == -1) {// 单品和固定搭配
                QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseDetail.getUnifiedMerchandiseId());
                AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在." + merchandiseDetail.getUnifiedMerchandiseId());
                QMerchant merchant = sellercenterClient.getMerchant(unifiedMerchandise.getMerchantId());
                merchantSet.add(merchant);
                merchandiseMap.put(unifiedMerchandise, merchandiseDetail.getNumber());
            } else {// 自由组合
                QUnifiedMerchandise freeCombinationMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseDetail.getCombinationMerchandiseId());
                AssertUtil.assertNotNull(freeCombinationMerchandise, "自由搭配组合不存在." + merchandiseDetail.getCombinationMerchandiseId());
                freeMerchandiseSet.add(freeCombinationMerchandise.getId());
                QMerchant merchant = sellercenterClient.getMerchant(freeCombinationMerchandise.getMerchantId());
                merchantSet.add(merchant);
            }
        }
        for (Long freeCombinationMerchandiseId : freeMerchandiseSet) {
            QUnifiedMerchandise freeCombinationMerchandise = commoditycenterClient.getUnifiedMerchandise(freeCombinationMerchandiseId);
            int matchNumber = 0;// 判断
            int combinationNumber = 0;
            Map<QUnifiedMerchandise, Integer> freeMerchandiseList = new HashMap<QUnifiedMerchandise, Integer>();
            for (MerchandiseDetail merchandiseDetail : merchandiseList) {
                for (QUnifiedMerchandise merchandiseItems : freeCombinationMerchandise.getList()) {
                    if (merchandiseDetail.getCombinationMerchandiseId().longValue() == freeCombinationMerchandiseId.longValue() && merchandiseDetail.getUnifiedMerchandiseId().longValue() == merchandiseItems.getId().longValue()) {
                        freeMerchandiseList.put(merchandiseItems, merchandiseDetail.getNumber());
                        combinationNumber = merchandiseDetail.getCombinationMerchandiseNumber();
                        matchNumber++;
                    }
                }
            }
            if (matchNumber == freeCombinationMerchandise.getList().size()) {// 自由搭配商品全买了
                merchandiseMap.put(freeCombinationMerchandise, combinationNumber);
            } else {
                freeCombinationMap.put(freeCombinationMerchandise, combinationNumber);
                freeMerchandiseListMap.put(freeCombinationMerchandise, freeMerchandiseList);
            }
        }
        context.setFreeCombinationMap(freeCombinationMap);
        context.setFreeMerchandiseListMap(freeMerchandiseListMap);
        AssertUtil.assertTrue(merchantSet.size() == 1, "只能下一个商家商品的订单.");
        context.setMerchandiseMap(merchandiseMap);
        context.setMerchantList(new ArrayList<QMerchant>(merchantSet));
        QMerchant merchant = context.getMerchantList().get(0);
        // 发票
        if (orderForm.getInvoiceId() > 0) {
            QMyInvoice invoice = myClient.getInvoice(orderForm.getInvoiceId());
            AssertUtil.assertNotNull(invoice, "发票信息不存在." + orderForm.getInvoiceId());
            context.setInvoice(invoice);
        }
        // 配送
        // Map<QMerchant, OrderDelivery> deliveryMap = new HashMap<QMerchant, OrderDelivery>();
        // // 配送
        // if (orderForm.getDeliveryId() != -1) {
        // QMyDelivery delivery = myClient.getDelivery(orderForm.getDeliveryId());
        // AssertUtil.assertNotNull(delivery, "配送信息不存在." + orderForm.getDeliveryId());
        // OrderDelivery orderDelivery = new OrderDelivery();
        // orderDelivery.setDelivery(delivery);
        // orderDelivery.setSexpressCode(orderForm.getExpressCode());
        // deliveryMap.put(merchant, orderDelivery);
        // } else {
        // OrderDelivery orderDelivery = new OrderDelivery();
        // orderDelivery.setDelivery(null);
        // orderDelivery.setSexpressCode(orderForm.getExpressCode());
        // deliveryMap.put(merchant, orderDelivery);
        // }
        // context.setDeliveryMap(deliveryMap);
        //
        Map<QMerchant, OrderDelivery> deliveryMap = new HashMap<QMerchant, OrderDelivery>();
        // 配送 送货上门
        DeliveryMode deliveryMode = deliveryModeService.getByUser(user.getId());
        if (deliveryMode == null) {
            deliveryMode = new DeliveryMode();
            deliveryMode.setUserId(user.getId());
            deliveryMode.setTime(deliveryForm.getTime());
            //
            deliveryMode.setType(deliveryForm.getType());
            if (DeliveryModeType.DELIVERY.getKey() == deliveryForm.getType()) {
                deliveryMode.setDesc("送货上门");
                deliveryMode.setStoreId(-1L);
            } else if (DeliveryModeType.PICKUP.getKey() == deliveryForm.getType()) {
                deliveryMode.setDesc("门店自提");
                QDepartment department = organizationClient.getDepartment(deliveryForm.getStoreId());
                AssertUtil.assertNotNull(department, "自提门店不存在.");
                deliveryMode.setStoreId(department.getId());
            } else {
                throw new CommoditycenterException("不支持的配送方式.");
            }
            deliveryModeService.add(deliveryMode);
        } else {
            deliveryMode.setUserId(user.getId());
            deliveryMode.setTime(deliveryForm.getTime());
            //
            deliveryMode.setType(deliveryForm.getType());
            if (DeliveryModeType.DELIVERY.getKey() == deliveryForm.getType()) {
                deliveryMode.setDesc("送货上门");
                deliveryMode.setStoreId(-1L);
            } else if (DeliveryModeType.PICKUP.getKey() == deliveryForm.getType()) {
                deliveryMode.setDesc("门店自提");
                QDepartment department = organizationClient.getDepartment(deliveryForm.getStoreId());
                AssertUtil.assertNotNull(department, "自提门店不存在.");
                deliveryMode.setStoreId(department.getId());
            } else {
                throw new CommoditycenterException("不支持的配送方式.");
            }
            deliveryModeService.update(deliveryMode);
        }
        orderForm.setDeliveryId(deliveryMode.getId());
        QMyDelivery delivery = myClient.getDelivery(orderForm.getDeliveryId());
        AssertUtil.assertNotNull(delivery, "配送信息不存在." + orderForm.getDeliveryId());
        OrderDelivery orderDelivery = new OrderDelivery();
        orderDelivery.setDelivery(delivery);
        orderDelivery.setSexpressCode(orderForm.getExpressCode());
        deliveryMap.put(merchant, orderDelivery);
        context.setDeliveryMap(deliveryMap);
        // 说明
        Map<QMerchant, String> explainMap = new HashMap<QMerchant, String>();
        explainMap.put(merchant, StringUtil.nullToEmpty(orderForm.getExplain()));
        context.setExplainMap(explainMap);
        ListForm listForm = orderForm.getMyCouponList();
        Map<QMerchant, List<QMyCoupon>> myCouponMap = new HashMap<QMerchant, List<QMyCoupon>>();
        myCouponMap.put(merchant, new ArrayList<QMyCoupon>());
        if (listForm != null && CollectionUtils.isNotEmpty(listForm.getLongList())) {
            List<Long> myCouponList = listForm.getLongList();
            Set<Long> conponSet = new HashSet<Long>();
            for (Long myCouponId : myCouponList) {
                QMyCoupon myCoupon = myClient.getMyCoupon(user.getId(), myCouponId);
                AssertUtil.assertNotNull(myCoupon, "优惠劵不存在." + myCoupon);
                AssertUtil.assertTrue(conponSet.add(myCouponId), "在订单中优惠劵不能重复使用." + myCoupon.getCode());
                AssertUtil.assertTrue(myCoupon.getMerchantId() == -1 || myCoupon.getMerchantId() == merchant.getId(), "单中优惠劵只能使用本商家优惠劵或者商城优惠劵." + myCoupon.getCode());
                myCouponMap.get(merchant).add(myCoupon);
            }
        }
        context.setMyCouponMap(myCouponMap);
        return context;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView get(HttpServletRequest request, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QOrder order = orderformClient.getOrder(orderId, orderDate);
        ForestOrder forestOrder = forestOrderService.getByOrder(orderId);
        ForestOrderVO orderVO = forestOrderHandler.toOrderVO(forestOrder, order);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取订单成功");
        view.addObject("order", orderVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getSimple(HttpServletRequest request, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QOrder order = orderformClient.getOrder(orderId, orderDate);
        ForestOrder forestOrder = forestOrderService.getByOrder(orderId);
        ForestOrderVO orderVO = forestOrderHandler.toOrderVO(forestOrder, order);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取订单成功");
        view.addObject("state", orderVO.getState());
        view.addObject("orderNumber", orderVO.getOrderNumber());
        view.addObject("cash", orderVO.getCash());
        view.addObject("paymentModeStr", orderVO.getPaymentModeStr());
        view.addObject("giveIntegral", (int) orderVO.getCash() / 10);// 获得积分
        view.addObject("giveGrowth", (int) orderVO.getCash() / 10);// 获得成长值
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView expressByOrder(HttpServletRequest request, Long orderId, Date orderDate) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(orderId, "订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QOrder order = orderformClient.getOrder(orderId, orderDate);
        List<ExpressQueryVO> volist = expressQueryService.getList(user.getId(), order.getMerchantOrderList().get(0).getExpressCode(), order.getMerchantOrderList().get(0).getExpressNumber());
        if (CollectionUtils.isEmpty(volist)) {
            ExpressQueryVO vo = new ExpressQueryVO();
            vo.setContext("假数据");
            vo.setLocation("假地址");
            vo.setTime(DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
            volist.add(vo);
            ExpressQueryVO vo2 = new ExpressQueryVO();
            vo2.setContext("假数据2");
            vo2.setLocation("假地址2");
            vo2.setTime(DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
            volist.add(vo2);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取订单物流信息成功");
        view.addObject("image", "");
        view.addObject("list", volist);
        view.addObject("stateStr", "正在派送");
        view.addObject("exist", CollectionUtils.isNotEmpty(volist));
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView expressByAfterSale(HttpServletRequest request, Long afterSaleId) {

        // QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MyAfterSale myAfterSale = myAfterSaleService.get(afterSaleId);
        AssertUtil.assertNotNull(myAfterSale, "售后单不存在.");
        if (myAfterSale.getType() == 2) {
        } else {
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取订单物流信息成功");
        // view.addObject("list", volist);
        // view.addObject("exist", CollectionUtils.isNotEmpty(volist));
        return view;
    }
}
