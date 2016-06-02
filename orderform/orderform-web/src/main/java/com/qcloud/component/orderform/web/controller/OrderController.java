package com.qcloud.component.orderform.web.controller;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.QMyConsignee;
import com.qcloud.component.my.QMyCoupon;
import com.qcloud.component.my.QMyDelivery;
import com.qcloud.component.my.QMyInvoice;
import com.qcloud.component.orderform.OrderContext;
import com.qcloud.component.orderform.OrderContext.OrderDelivery;
import com.qcloud.component.orderform.OrderMyConsignee;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.orderform.engine.OrderService;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.entity.OrderItemEntity;
import com.qcloud.component.orderform.service.CollectOrderService;
import com.qcloud.component.orderform.web.form.Explain;
import com.qcloud.component.orderform.web.form.MerchandiseDetail;
import com.qcloud.component.orderform.web.form.MultiMerchantOrderForm;
import com.qcloud.component.orderform.web.form.OrderCoupon;
import com.qcloud.component.orderform.web.form.OrderExpress;
import com.qcloud.component.orderform.web.form.OrderForm;
import com.qcloud.component.orderform.web.form.PrepareOrderForm;
import com.qcloud.component.orderform.web.handler.OrderHandler;
import com.qcloud.component.orderform.web.util.OrderStateType;
import com.qcloud.component.orderform.web.vo.OrderCouponVO;
import com.qcloud.component.orderform.web.vo.OrderExpressVO;
import com.qcloud.component.orderform.web.vo.merchant.MerchantOrderVO;
import com.qcloud.component.orderform.web.vo.personal.OrderCVO;
import com.qcloud.component.orderform.web.vo.personal.OrderMVO;
import com.qcloud.component.orderform.web.vo.personal.OrderVO;
import com.qcloud.component.orderform.web.vo.pre.PreClassifyVO;
import com.qcloud.component.orderform.web.vo.pre.PreMerchantVO;
import com.qcloud.component.orderform.web.vo.pre.PreOrderCVO;
import com.qcloud.component.orderform.web.vo.pre.PreOrderItemVO;
import com.qcloud.component.orderform.web.vo.pre.PreOrderMVO;
import com.qcloud.component.orderform.web.vo.pre.PreOrderVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QGrade;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.piratesship.web.form.ListForm;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
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

@Controller
@RequestMapping(value = OrderController.DIR)
public class OrderController {

    // URI
    public static final String    DIR = "/orderForm";

    @Autowired
    private OrderformClient       orderformClient;

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Autowired
    private PublicdataClient      publicdataClient;

    @Autowired
    private SellercenterClient    sellercenterClient;

    @Autowired
    private OrderService          orderService;

    @Autowired
    private OrderHandler          orderHandler;

    @Autowired
    private MyClient              myClient;

    @Autowired
    private FileSDKClient         fileSDKClient;

    @Autowired
    private PayClient             payClient;

    @Autowired
    private CollectOrderService   collectOrderService;

    @Value("${pirates.mall.topClassify}")
    private String                topClassify;

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

    //
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
            voList.add(vo);
        }
        return voList;
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView prepareOrder4MerchantByOrder(HttpServletRequest request, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QOrder order = orderformClient.getOrder(orderId, orderDate);
        AssertUtil.assertNotNull(order, "订单不存在" + orderId);
        return prepareOrder4Merchant(request, orderToForm(order));
    }

    // 一分钟一个账号允许下一个订单
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView prepareOrder4Merchant(HttpServletRequest request, PrepareOrderForm orderForm) {

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
        for (MerchandiseDetail detail : merchandiseList) {
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(detail.getUnifiedMerchandiseId());
            AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在" + detail.getUnifiedMerchandiseId());
            QMerchant merchant = sellercenterClient.getMerchant(unifiedMerchandise.getMerchantId());
            merchantSet.add(merchant);
            merchandiseMap.put(unifiedMerchandise, detail.getNumber());
        }
        context.setMerchandiseMap(merchandiseMap);
        context.setMerchantList(new ArrayList<QMerchant>(merchantSet));
        // 计算邮费 begin
        // TODO
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
                preMerchantVO.getPreOrderItemList().add(preOrderItemVO);
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
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取下单数据成功");
        view.addObject("preOrder", preOrderMVO);
        view.addObject("discount", context.getDiscount());
        // 会员折扣信息
        QGrade grade = user.getGrade();
        view.addObject("discountMessage", "会员尊享:" + grade.getDiscount() * 100 / 1000 + "折优惠");
        return view;
    }

    // 一分钟一个账号允许下一个订单
    // @RequestMapping
    // @NoReferer
    // public FrontAjaxView prepareOrder4Classify1(HttpServletRequest request, PrepareOrderForm orderForm) {
    //
    // QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
    // int discount = 100;
    // QGrade grade = user.getGrade();
    // if (grade != null) {
    // discount = grade.getDiscount();
    // }
    // AssertUtil.assertNotEmpty(orderForm.getMerchandiseList(), "下订单,商品列表不能为空.");
    // List<MerchandiseDetail> merchandiseList = orderForm.getMerchandiseList();
    // PreOrderCVO preOrderCVO = new PreOrderCVO();
    // Map<Long, PreClassifyVO> map = new HashMap<Long, PreClassifyVO>();
    // List<PreClassifyVO> preClassifyList = new ArrayList<PreClassifyVO>();
    // for (MerchandiseDetail detail : merchandiseList) {
    // QUnifiedMerchandise um = commoditycenterClient.getUnifiedMerchandise(detail.getUnifiedMerchandiseId());
    // AssertUtil.assertNotNull(um, "商品不存在" + detail.getUnifiedMerchandiseId());
    // PreOrderItemVO preOrderItemVO = new PreOrderItemVO();
    // preOrderItemVO.setDiscount(um.getDiscount() * discount / 100);
    // preOrderItemVO.setUnifiedMerchandiseId(um.getId());
    // preOrderItemVO.setImage(fileSDKClient.getFileServerUrl() + um.getList().get(0).getImage());
    // preOrderItemVO.setPrice(um.getPrice());
    // preOrderItemVO.setName(um.getList().get(0).getName());
    // preOrderItemVO.setNumber(detail.getNumber());
    // preOrderItemVO.setSpecifications(um.getList().get(0).getSpecifications());
    // long merchantClassifyId = um.getList().get(0).getMerchantClassifyId();
    // PreClassifyVO preClassifyVO = map.get(um.getList().get(0).getMerchantClassifyId());
    // if (preClassifyVO == null) {
    // preClassifyVO = new PreClassifyVO();
    // Classify classify = publicdataClient.getClassify(merchantClassifyId);
    // preClassifyVO.setClassifyName(classify.getName());
    // preClassifyVO.setImage(fileSDKClient.getFileServerUrl() + StringUtil.nullToEmpty(classify.getImage()));
    // preClassifyVO.setClassifyId(classify.getId());
    // map.put(preClassifyVO.getClassifyId(), preClassifyVO);
    // preClassifyList.add(preClassifyVO);
    // }
    // preOrderItemVO.setSum(preOrderItemVO.getDiscount() * preOrderItemVO.getNumber());
    // preClassifyVO.getPreOrderItemList().add(preOrderItemVO);
    // }
    // preOrderCVO.setPreClassifyList(preClassifyList);
    // FrontAjaxView view = new FrontAjaxView();
    // view.setMessage("获取下单数据成功");
    // view.addObject("preOrder", preOrderCVO);
    // view.addObject("discount", discount);
    // return view;
    // }
    // 一分钟一个账号允许下一个订单
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView prepareOrder4ClassifyByOrder(HttpServletRequest request, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QOrder order = orderformClient.getOrder(orderId, orderDate);
        AssertUtil.assertNotNull(order, "订单不存在" + orderId);
        return prepareOrder4Classify(request, orderToForm(order));
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView prepareOrder4Classify(HttpServletRequest request, PrepareOrderForm orderForm) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        OrderContext context = new OrderContext();
        context.setUser(user);
        QMyConsignee myConsignee = myClient.getDefaultConsignee(user.getId());
        context.setConsignee(myConsignee);
        Set<QMerchant> merchantSet = new HashSet<QMerchant>();
        AssertUtil.assertNotEmpty(orderForm.getMerchandiseList(), "下订单,商品列表不能为空.");
        List<MerchandiseDetail> merchandiseList = orderForm.getMerchandiseList();
        Map<QUnifiedMerchandise, Integer> merchandiseMap = new HashMap<QUnifiedMerchandise, Integer>();
        for (MerchandiseDetail detail : merchandiseList) {
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(detail.getUnifiedMerchandiseId());
            AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在" + detail.getUnifiedMerchandiseId());
            QMerchant merchant = sellercenterClient.getMerchant(unifiedMerchandise.getMerchantId());
            merchantSet.add(merchant);
            merchandiseMap.put(unifiedMerchandise, detail.getNumber());
        }
        context.setMerchandiseMap(merchandiseMap);
        context.setMerchantList(new ArrayList<QMerchant>(merchantSet));
        AssertUtil.assertTrue(merchantSet.size() == 1, "只能下一个商家商品的订单.");
        // 计算邮费
        Map<QMerchant, List<OrderExpressVO>> expressMap = calculatePreparePostage(context, orderForm.getExpressCode());
        // 计算邮费
        OrderEntity orderEntity = orderService.prepareOrderNormal(context);
        PreOrderCVO preOrderCVO = new PreOrderCVO();
        List<MerchantOrderEntity> merchantOrderList = orderEntity.getEntityList();
        List<PreClassifyVO> preClassifyList = new ArrayList<PreClassifyVO>();
        Map<Long, PreClassifyVO> map = new HashMap<Long, PreClassifyVO>();
        for (MerchantOrderEntity merchantOrderEntity : merchantOrderList) {
            QMerchant merchant = sellercenterClient.getMerchant(merchantOrderEntity.getMerchantId());
            preOrderCVO.setPostage(merchantOrderEntity.getPostage());
            List<OrderItemEntity> itemList = merchantOrderEntity.getEntityList();
            for (OrderItemEntity orderItemEntity : itemList) {
                //
                PreOrderItemVO preOrderItemVO = new PreOrderItemVO();
                preOrderItemVO.setDiscount(orderItemEntity.getDiscount());
                preOrderItemVO.setUnifiedMerchandiseId(orderItemEntity.getUnifiedMerchandiseId());
                preOrderItemVO.setImage(fileSDKClient.getFileServerUrl() + orderItemEntity.getImage());
                preOrderItemVO.setPrice(orderItemEntity.getPrice());
                preOrderItemVO.setName(orderItemEntity.getName());
                preOrderItemVO.setNumber(orderItemEntity.getNumber());
                QUnifiedMerchandise um = null;
                for (QUnifiedMerchandise unifiedMerchandise : merchandiseMap.keySet()) {
                    if (unifiedMerchandise.getId() == orderItemEntity.getUnifiedMerchandiseId()) {
                        um = unifiedMerchandise;
                        break;
                    }
                }
                AssertUtil.assertNotNull(um, "商品不存在." + orderItemEntity.getUnifiedMerchandiseId());
                preOrderItemVO.setSpecifications(StringUtil.nullToEmpty(um.getSpecifications()));
                preOrderItemVO.setUnit(um.getUnit());
                preOrderItemVO.setSum(orderItemEntity.getSum());
                //
                long merchantClassifyId = um.getList().get(0).getMerchantClassifyId();
                Classify classify = null;
                if (Boolean.valueOf(topClassify)) {
                    classify = publicdataClient.getTopClassify(merchantClassifyId);
                    AssertUtil.assertNotNull(classify, "一级分类不存在." + merchantClassifyId);
                } else {
                    classify = publicdataClient.getClassify(merchantClassifyId);
                    AssertUtil.assertNotNull(classify, "分类不存在." + merchantClassifyId);
                }
                PreClassifyVO preClassifyVO = map.get(classify.getId());
                if (preClassifyVO == null) {
                    preClassifyVO = new PreClassifyVO();
                    preClassifyVO.setClassifyName(classify.getName());
                    preClassifyVO.setImage(fileSDKClient.getFileServerUrl() + StringUtil.nullToEmpty(classify.getImage()));
                    preClassifyVO.setClassifyId(classify.getId());
                    map.put(classify.getId(), preClassifyVO);
                    preClassifyList.add(preClassifyVO);
                }
                preOrderItemVO.setSum(preOrderItemVO.getDiscount() * preOrderItemVO.getNumber());
                preClassifyVO.getPreOrderItemList().add(preOrderItemVO);
            }
            // 物流
            preOrderCVO.setExpressList(expressMap.get(merchant));
            if (CollectionUtils.isNotEmpty(preOrderCVO.getExpressList())) {
                for (OrderExpressVO orderExpress : preOrderCVO.getExpressList()) {
                    if (orderExpress.getExpressCode().equals(merchantOrderEntity.getSubOrder().getExpressCode())) {
                        preOrderCVO.setExpress(orderExpress);
                        break;
                    }
                }
            }
            // 优惠劵
            List<OrderCouponVO> couponList = calculateUserCoupon(user.getId(), merchantOrderEntity.getMerchantId(), merchantOrderEntity.getCash() - merchantOrderEntity.getPostage());
            preOrderCVO.setCouponList(couponList);
        }
        preOrderCVO.setPreClassifyList(preClassifyList);
        preOrderCVO.setSum(NumberUtil.scale(orderEntity.getSum(), 2));
        preOrderCVO.setCash(NumberUtil.scale(orderEntity.getCash(), 2));
        preOrderCVO.setPostage(NumberUtil.scale(orderEntity.getPostage(), 2));
        preOrderCVO.setPreferential(NumberUtil.scale(orderEntity.getPreferential(), 2));
        preOrderCVO.setTotalNumber(orderEntity.getNumber());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取下单数据成功");
        view.addObject("preOrder", preOrderCVO);
        view.addObject("discount", context.getDiscount());
        return view;
    }

    // // 一分钟一个账号允许下一个订单
    // @RequestMapping
    // @NoReferer
    // public FrontAjaxView prepareOrder1(HttpServletRequest request, PrepareOrderForm orderForm) {
    //
    // QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
    // int discount = 100;
    // QGrade grade = user.getGrade();
    // if (grade != null) {
    // discount = grade.getDiscount();
    // }
    // AssertUtil.assertNotEmpty(orderForm.getMerchandiseList(), "下订单,商品列表不能为空.");
    // List<MerchandiseDetail> merchandiseList = orderForm.getMerchandiseList();
    // PreOrderVO preOrderVO = new PreOrderVO();
    // List<PreOrderItemVO> preOrderItemList = new ArrayList<PreOrderItemVO>();
    // for (MerchandiseDetail detail : merchandiseList) {
    // QUnifiedMerchandise um = commoditycenterClient.getUnifiedMerchandise(detail.getUnifiedMerchandiseId());
    // AssertUtil.assertNotNull(um, "商品不存在" + detail.getUnifiedMerchandiseId());
    // PreOrderItemVO preOrderItemVO = new PreOrderItemVO();
    // preOrderItemVO.setDiscount(um.getDiscount() * discount / 100);
    // preOrderItemVO.setUnifiedMerchandiseId(um.getId());
    // preOrderItemVO.setImage(fileSDKClient.getFileServerUrl() + um.getList().get(0).getImage());
    // preOrderItemVO.setPrice(um.getPrice());
    // preOrderItemVO.setName(um.getList().get(0).getName());
    // preOrderItemVO.setNumber(detail.getNumber());
    // preOrderItemVO.setSpecifications(um.getList().get(0).getSpecifications());
    // preOrderItemVO.setSum(preOrderItemVO.getDiscount() * preOrderItemVO.getNumber());
    // preOrderItemList.add(preOrderItemVO);
    // }
    // preOrderVO.setPreOrderItemList(preOrderItemList);
    // FrontAjaxView view = new FrontAjaxView();
    // view.setMessage("获取下单数据成功");
    // view.addObject("preOrder", preOrderVO);
    // view.addObject("discount", discount);
    // return view;
    // }
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView prepareOrder(HttpServletRequest request, PrepareOrderForm orderForm) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        OrderContext context = new OrderContext();
        context.setUser(user);
        QMyConsignee myConsignee = myClient.getDefaultConsignee(user.getId());
        context.setConsignee(myConsignee);
        Set<QMerchant> merchantSet = new HashSet<QMerchant>();
        AssertUtil.assertNotEmpty(orderForm.getMerchandiseList(), "下订单,商品列表不能为空.");
        List<MerchandiseDetail> merchandiseList = orderForm.getMerchandiseList();
        Map<QUnifiedMerchandise, Integer> merchandiseMap = new HashMap<QUnifiedMerchandise, Integer>();
        for (MerchandiseDetail detail : merchandiseList) {
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(detail.getUnifiedMerchandiseId());
            AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在" + detail.getUnifiedMerchandiseId());
            QMerchant merchant = sellercenterClient.getMerchant(unifiedMerchandise.getMerchantId());
            merchantSet.add(merchant);
            merchandiseMap.put(unifiedMerchandise, detail.getNumber());
        }
        context.setMerchandiseMap(merchandiseMap);
        context.setMerchantList(new ArrayList<QMerchant>(merchantSet));
        AssertUtil.assertTrue(merchantSet.size() == 1, "只能下一个商家商品的订单.");
        // 计算邮费
        Map<QMerchant, List<OrderExpressVO>> expressMap = calculatePreparePostage(context, orderForm.getExpressCode());
        // 计算邮费
        OrderEntity orderEntity = orderService.prepareOrderNormal(context);
        PreOrderVO preOrderVO = new PreOrderVO();
        List<MerchantOrderEntity> merchantOrderList = orderEntity.getEntityList();
        List<PreOrderItemVO> preOrderItemList = new ArrayList<PreOrderItemVO>();
        for (MerchantOrderEntity merchantOrderEntity : merchantOrderList) {
            QMerchant merchant = sellercenterClient.getMerchant(merchantOrderEntity.getMerchantId());
            preOrderVO.setPostage(merchantOrderEntity.getPostage());
            List<OrderItemEntity> itemList = merchantOrderEntity.getEntityList();
            for (OrderItemEntity orderItemEntity : itemList) {
                //
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
                preOrderItemList.add(preOrderItemVO);
            }
            // 物流
            preOrderVO.setExpressList(expressMap.get(merchant));
            if (CollectionUtils.isNotEmpty(preOrderVO.getExpressList())) {
                for (OrderExpressVO orderExpress : preOrderVO.getExpressList()) {
                    if (orderExpress.getExpressCode().equals(merchantOrderEntity.getSubOrder().getExpressCode())) {
                        preOrderVO.setExpress(orderExpress);
                        break;
                    }
                }
            }
            // 优惠劵
            List<OrderCouponVO> couponList = calculateUserCoupon(user.getId(), merchantOrderEntity.getMerchantId(), merchantOrderEntity.getCash() - merchantOrderEntity.getPostage());
            preOrderVO.setCouponList(couponList);
        }
        preOrderVO.setPreOrderItemList(preOrderItemList);
        preOrderVO.setSum(NumberUtil.scale(orderEntity.getSum(), 2));
        preOrderVO.setCash(NumberUtil.scale(orderEntity.getCash(), 2));
        preOrderVO.setPostage(NumberUtil.scale(orderEntity.getPostage(), 2));
        preOrderVO.setPreferential(NumberUtil.scale(orderEntity.getPreferential(), 2));
        preOrderVO.setTotalNumber(orderEntity.getNumber());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取下单数据成功");
        view.addObject("preOrder", preOrderVO);
        view.addObject("discount", context.getDiscount());
        return view;
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView prepareOrderByOrder(HttpServletRequest request, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QOrder order = orderformClient.getOrder(orderId, orderDate);
        AssertUtil.assertNotNull(order, "订单不存在" + orderId);
        return prepareOrder(request, orderToForm(order));
    }

    private PrepareOrderForm orderToForm(QOrder order) {

        PrepareOrderForm orderForm = new PrepareOrderForm();
        List<QMerchantOrder> merchantOrderList = order.getMerchantOrderList();
        List<MerchandiseDetail> merchandiseList = new ArrayList<MerchandiseDetail>();
        for (QMerchantOrder qMerchantOrder : merchantOrderList) {
            List<QOrderItem> itemList = qMerchantOrder.getOrderItemList();
            for (QOrderItem qOrderItem : itemList) {
                MerchandiseDetail merchandiseDetail = new MerchandiseDetail();
                merchandiseDetail.setNumber(qOrderItem.getNumber());
                merchandiseDetail.setUnifiedMerchandiseId(qOrderItem.getUnifiedMerchandiseId());
                merchandiseList.add(merchandiseDetail);
            }
        }
        orderForm.setMerchandiseList(merchandiseList);
        return orderForm;
    }

    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // @RequestMapping(value = "/order1.do")
    // @NoReferer
    // public FrontAjaxView order1(HttpServletRequest request, OrderForm orderForm) {
    //
    // AssertUtil.assertNotNull(orderForm.getConsigneeId(), "收货人信息数据不能为空.");
    // AssertUtil.assertNotNull(orderForm.getDeliveryId(), "配送信息数据不能为空.");
    // AssertUtil.assertNotNull(orderForm.getInvoiceId(), "发票信息数据不能为空.");
    // QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
    // QMyConsignee consignee = myClient.getConsignee(orderForm.getConsigneeId());
    // AssertUtil.assertNotNull(consignee, "收货人信息不存在." + orderForm.getConsigneeId());
    // AssertUtil.assertNotEmpty(orderForm.getMerchandiseList(), "下订单,商品列表不能为空.");
    // List<MerchandiseDetail> merchandiseList = orderForm.getMerchandiseList();
    // List<OrderMerchandise> orderMerchandiseList = new ArrayList<OrderMerchandise>();
    // int totalNumber = 0;
    // for (MerchandiseDetail detail : merchandiseList) {
    // totalNumber += detail.getNumber();
    // OrderMerchandise om = new OrderMerchandise();
    // // 这里的判断逻辑需要重新考虑
    // QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(detail.getUnifiedMerchandiseId());
    // AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在" + detail.getUnifiedMerchandiseId());
    // om.setUnifiedMerchandise(unifiedMerchandise);
    // om.setNumber(detail.getNumber());
    // orderMerchandiseList.add(om);
    // }
    // if (totalNumber > 1000) {
    // throw new OrderformException("订单商品数量不能超过1000个");
    // }
    // // 测试数据
    // Order order = new Order();
    // order.setConsignee(consignee);
    // order.setUser(user);
    // order.setMyCouponId(orderForm.getMyCouponId());
    // // 发票
    // if (orderForm.getInvoiceId() == -1) {
    // order.setInvoiceContent("");
    // order.setInvoiceHead("");
    // order.setInvoiceType(InvoiceType.COMMON);
    // order.setNeedInvoiceType(NeedInvoiceType.NO);
    // } else {
    // QMyInvoice invoice = myClient.getInvoice(orderForm.getInvoiceId());
    // AssertUtil.assertNotNull(invoice, "发票信息不存在." + orderForm.getInvoiceId());
    // order.setInvoiceContent(invoice.getContent());
    // order.setInvoiceHead(invoice.getHead());
    // order.setInvoiceType(invoice.getType());
    // order.setNeedInvoiceType(invoice.getMode());
    // }
    // order.setExplain(orderForm.getExplain());
    // // 微信支付
    // order.setMerchandiseList(orderMerchandiseList);
    // //
    // // OrderContext context = new OrderContext();
    // // context.setUser(user);
    // // context.setConsignee(consignee);
    // // context.setDeliveryMap(deliveryMap);
    // // context.setExplainMap(explainMap);
    // // context.setInvoice(invoice);
    // // context.setMerchandiseMap(merchandiseMap);
    // // context.setMyCouponMap(myCouponMap);
    // // OrderEntity orderEntity = orderService.orderNormal(context);
    // // OrderService.OrderResult result = orderService.order(order);
    // FrontAjaxView view = new FrontAjaxView();
    // myClient.setDefaultInvoice(user.getId());
    // view.setMessage("下订单成功");
    // // view.addObject("result", Boolean.TRUE.toString());
    // // view.addObject("orderId", orderEntity.getId());
    // // view.addObject("sum", orderEntity.getSum());
    // // view.addObject("orderNumber", orderEntity.getOrderNumber());
    // // view.addObject("delivery", order.getDeliveryModeType().getName());
    // // view.addObject("orderDate", DateUtil.date2String(orderEntity.getOrderDate(), DateUtil.FORMAT_STRING));
    // return view;
    // }
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView order(HttpServletRequest request, OrderForm orderForm) {

        AssertUtil.assertNotNull(orderForm.getConsigneeId(), "收货人信息数据不能为空.");
        AssertUtil.assertNotNull(orderForm.getDeliveryId(), "配送信息数据不能为空.");
        AssertUtil.assertNotNull(orderForm.getInvoiceId(), "发票信息数据不能为空.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        QMyConsignee consignee = myClient.getConsignee(orderForm.getConsigneeId());
        AssertUtil.assertNotNull(consignee, "收货人信息不存在." + orderForm.getConsigneeId());
        AssertUtil.assertNotEmpty(orderForm.getMerchandiseList(), "下订单,商品列表不能为空.");
        OrderContext orderContext = formToContext(user, orderForm);
        OrderEntity orderEntity = orderService.orderNormal(orderContext);
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
        view.addObject("discountMessage", "会员尊享:" + grade.getDiscount() * 100 / 1000 + "折优惠");
        return view;
    }

    private OrderContext formToContext(QUser user, OrderForm orderForm) {

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
        for (MerchandiseDetail detail : merchandiseList) {
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(detail.getUnifiedMerchandiseId());
            AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在" + detail.getUnifiedMerchandiseId());
            QMerchant merchant = sellercenterClient.getMerchant(unifiedMerchandise.getMerchantId());
            merchantSet.add(merchant);
            merchandiseMap.put(unifiedMerchandise, detail.getNumber());
        }
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
        Map<QMerchant, OrderDelivery> deliveryMap = new HashMap<QMerchant, OrderDelivery>();
        // 配送
        if (orderForm.getDeliveryId() != -1) {
            QMyDelivery delivery = myClient.getDelivery(orderForm.getDeliveryId());
            AssertUtil.assertNotNull(delivery, "配送信息不存在." + orderForm.getDeliveryId());
            OrderDelivery orderDelivery = new OrderDelivery();
            orderDelivery.setDelivery(delivery);
            orderDelivery.setSexpressCode(orderForm.getExpressCode());
            deliveryMap.put(merchant, orderDelivery);
        } else {
            OrderDelivery orderDelivery = new OrderDelivery();
            orderDelivery.setDelivery(null);
            orderDelivery.setSexpressCode(orderForm.getExpressCode());
            deliveryMap.put(merchant, orderDelivery);
        }
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
    @NoReferer
    public FrontAjaxView order4MultiMerchant(HttpServletRequest request, MultiMerchantOrderForm orderForm) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        // 下单
        OrderContext context = formToContext(user, orderForm);
        OrderEntity orderEntity = orderService.orderNormal(context);
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
        view.addObject("discountMessage", "会员尊享:" + grade.getDiscount() * 100 / 1000 + "折优惠");
        return view;
    }

    // 再次购买
    @PiratesApp
    @RequestMapping
    public FrontAjaxView orderAgain(HttpServletRequest request, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QOrder order = orderformClient.getOrder(orderId, orderDate);
        List<QMerchantOrder> merchantOrderList = order.getMerchantOrderList();
        for (QMerchantOrder qMerchantOrder : merchantOrderList) {
            for (QOrderItem orderItem : qMerchantOrder.getOrderItemList()) {
                myClient.addMyShoppingCart(order.getUserId(), orderItem.getUnifiedMerchandiseId(), orderItem.getNumber());
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("再次购买将订单商品加入购物车成功.");
        return view;
    }

    public OrderContext formToContext(QUser user, MultiMerchantOrderForm orderForm) {

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
        for (MerchandiseDetail detail : merchandiseList) {
            QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(detail.getUnifiedMerchandiseId());
            AssertUtil.assertNotNull(unifiedMerchandise, "商品不存在" + detail.getUnifiedMerchandiseId());
            QMerchant merchant = sellercenterClient.getMerchant(unifiedMerchandise.getMerchantId());
            merchantSet.add(merchant);
            merchandiseMap.put(unifiedMerchandise, detail.getNumber());
        }
        context.setMerchandiseMap(merchandiseMap);
        context.setMerchantList(new ArrayList<QMerchant>(merchantSet));
        // 发票
        if (orderForm.getInvoiceId() > 0) {
            QMyInvoice invoice = myClient.getInvoice(orderForm.getInvoiceId());
            AssertUtil.assertNotNull(invoice, "发票信息不存在." + orderForm.getInvoiceId());
            context.setInvoice(invoice);
        }
        // 配送
        Map<QMerchant, OrderDelivery> deliveryMap = new HashMap<QMerchant, OrderDelivery>();
        // 配送先统一
        for (QMerchant merchant : merchantSet) {
            OrderDelivery orderDelivery = new OrderDelivery();
            orderDelivery.setDelivery(null);
            orderDelivery.setSexpressCode(null);
            deliveryMap.put(merchant, orderDelivery);
            if (!CollectionUtils.isEmpty(orderForm.getExpressList())) {
                for (OrderExpress orderExpress : orderForm.getExpressList()) {
                    if (orderExpress.getMerchantId() == merchant.getId()) {
                        orderDelivery.setSexpressCode(orderExpress.getExpressCode());
                        break;
                    }
                }
            }
        }
        context.setDeliveryMap(deliveryMap);
        // 说明
        Map<QMerchant, String> explainMap = new HashMap<QMerchant, String>();
        if (CollectionUtils.isNotEmpty(orderForm.getExplainList())) {
            for (QMerchant merchant : merchantSet) {
                for (Explain explain : orderForm.getExplainList()) {
                    if (merchant.getId() == explain.getMerchantId()) {
                        explainMap.put(merchant, StringUtil.nullToEmpty(explain.getExplain()));
                        break;
                    }
                }
            }
        }
        context.setExplainMap(explainMap);
        // 优惠劵
        List<OrderCoupon> orderCouponList = orderForm.getMyCouponList();
        // Set<Long> orderCouponSet = new HashSet<Long>();
        Map<QMerchant, List<QMyCoupon>> myCouponMap = new HashMap<QMerchant, List<QMyCoupon>>();
        Set<Long> conponSet = new HashSet<Long>();
        for (QMerchant merchant : merchantSet) {
            myCouponMap.put(merchant, new ArrayList<QMyCoupon>());
            if (!CollectionUtils.isEmpty(orderCouponList)) {
                for (OrderCoupon orderCoupon : orderCouponList) {
                    if (orderCoupon != null && merchant.getId() == orderCoupon.getMerchantId()) {
                        QMyCoupon myCoupon = myClient.getMyCoupon(user.getId(), orderCoupon.getMyCouponId());
                        AssertUtil.assertNotNull(myCoupon, "优惠劵不存在." + myCoupon);
                        // AssertUtil.assertNotNull(orderCouponSet.add(orderCoupon.getMyCouponId()), "优惠劵重复");
                        AssertUtil.assertTrue(conponSet.add(orderCoupon.getMyCouponId()), "在订单中优惠劵不能重复使用." + myCoupon.getCode());
                        myCouponMap.get(merchant).add(myCoupon);
                    }
                }
            }
        }
        context.setMyCouponMap(myCouponMap);
        return context;
    }

    // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView exchangeConsumption(HttpServletRequest request, Long unifiedMerchandiseId, Integer number, String name, String mobile, String address) {

        QUnifiedMerchandise merchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(merchandise, "商品不存在" + unifiedMerchandiseId);
        AssertUtil.assertTrue(merchandise.getSence() == 7, "仅支持兑换兑兑劵商品");
        OrderEntity orderEntity = exchange(request, merchandise, number, name, mobile, address);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("下订单成功");
        view.addObject("result", Boolean.TRUE.toString());
        view.addObject("orderId", orderEntity.getId());
        view.addObject("consumption", orderEntity.getConsumption());
        view.addObject("orderNumber", orderEntity.getOrderNumber());
        view.addObject("orderDate", DateUtil.date2String(orderEntity.getOrderDate(), DateUtil.FORMAT_STRING));
        return view;
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView exchangeIntegral(HttpServletRequest request, Long unifiedMerchandiseId, Integer number, String name, String mobile, String address) {

        QUnifiedMerchandise merchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(merchandise, "商品不存在" + unifiedMerchandiseId);
        AssertUtil.assertTrue(merchandise.getSence() == 6, "仅支持兑换积分商品");
        OrderEntity orderEntity = exchange(request, merchandise, number, name, mobile, address);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("下订单成功");
        view.addObject("result", Boolean.TRUE.toString());
        view.addObject("orderId", orderEntity.getId());
        // 花费积分
        view.addObject("integral", orderEntity.getIntegral());
        view.addObject("orderNumber", orderEntity.getOrderNumber());
        view.addObject("orderDate", DateUtil.date2String(orderEntity.getOrderDate(), DateUtil.FORMAT_STRING));
        return view;
    }

    private OrderEntity exchange(HttpServletRequest request, QUnifiedMerchandise merchandise, Integer number, final String name, final String mobile, final String address) {

        AssertUtil.assertNotEmpty(name, "收货人姓名不能为空.");
        AssertUtil.assertNotEmpty(mobile, "收货人手机不能为空.");
        AssertUtil.assertNotEmpty(address, "收货人地址不能为空.");
        number = number == null || number <= 0 ? 1 : number;
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        QMyConsignee consignee = OrderMyConsignee.get(name, mobile, address);
        return orderService.orderExchange(user, consignee, merchandise, number, OrderStateType.NORMAL_PAID.getKey());
    }

    @RequestMapping
    @NoReferer
    public FrontAjaxView order4groupbuys(HttpServletRequest request, Long seckillMerchandiseId) {

        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("秒杀成功.");
        return view;
    }

    // ///////////////////////////////////////////////////////////////////////////////////////////////////
    @PiratesApp
    @RequestMapping
    public FrontAjaxView get(HttpServletRequest request, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QOrder order = orderformClient.getOrder(orderId, orderDate);
        OrderVO orderVO = orderHandler.toVO(order);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取订单成功");
        view.addObject("order", orderVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView get4Classify(HttpServletRequest request, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QOrder order = orderformClient.getOrder(orderId, orderDate);
        OrderCVO orderVO = orderHandler.toVO4Classify(order);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取订单成功");
        view.addObject("order", orderVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView get4Merchant(HttpServletRequest request, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(orderId, "订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QOrder order = orderformClient.getOrder(orderId, orderDate);
        OrderMVO orderVO = orderHandler.toVO4Merchant(order);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取订单成功");
        view.addObject("order", orderVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getMerchantOrder(HttpServletRequest request, Long subOrderId, Date orderDate) {

        AssertUtil.assertNotNull(subOrderId, "子订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(subOrderId, orderDate);
        OrderVO orderVO = orderHandler.toVO(merchantOrder);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取子订单成功");
        view.addObject("order", orderVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getMerchantOrder4Classify(HttpServletRequest request, Long subOrderId, Date orderDate) {

        AssertUtil.assertNotNull(subOrderId, "子订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(subOrderId, orderDate);
        OrderCVO orderVO = orderHandler.toVO4Classify(merchantOrder);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取子订单成功");
        view.addObject("order", orderVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getMerchantOrder4Merchant(HttpServletRequest request, Long subOrderId, Date orderDate) {

        AssertUtil.assertNotNull(subOrderId, "子订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(subOrderId, orderDate);
        OrderMVO orderVO = orderHandler.toVO4Merchant(merchantOrder);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取子订单成功");
        view.addObject("order", orderVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getSubOrder(HttpServletRequest request, Long subOrderId, Date orderDate) {

        AssertUtil.assertNotNull(subOrderId, "子订单ID不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空.");
        QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(subOrderId, orderDate);
        MerchantOrderVO merchantOrderVO = orderHandler.toMerchantVO(merchantOrder);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取订单成功");
        view.addObject("order", merchantOrderVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView changeConsignee(HttpServletRequest request, Long consigneeId, Long orderId, Date orderDate) {

        AssertUtil.assertNotNull(consigneeId, "收货人信息数据不能为空.");
        AssertUtil.assertNotNull(orderId, "订单标识不能为空.");
        AssertUtil.assertNotNull(orderDate, "订单日期不能为空");
        QMyConsignee consignee = myClient.getConsignee(consigneeId);
        AssertUtil.assertNotNull(consignee, "收货人信息不存在." + consigneeId);
        collectOrderService.updateConsignee(orderId, orderDate, consignee);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改订单地址成功.");
        return view;
    }
}
