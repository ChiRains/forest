package com.qcloud.component.my.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.autoid.UniqueCodeGenerator;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.QUnifiedMerchandise.MerchandiseType;
import com.qcloud.component.my.AfterSaleType;
import com.qcloud.component.my.InvoiceType;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.MyOrderStateType;
import com.qcloud.component.my.NeedInvoiceType;
import com.qcloud.component.my.QMyConsignee;
import com.qcloud.component.my.QMyCoupon;
import com.qcloud.component.my.QMyDelivery;
import com.qcloud.component.my.QMyInvoice;
import com.qcloud.component.my.entity.MyConsigneeEntity;
import com.qcloud.component.my.entity.MyCouponEntity;
import com.qcloud.component.my.entity.MyDeliveryEntity;
import com.qcloud.component.my.entity.MyInvoiceEntity;
import com.qcloud.component.my.entity.MyToEvaluationEntity;
import com.qcloud.component.my.model.Consignee;
import com.qcloud.component.my.model.DeliveryMode;
import com.qcloud.component.my.model.InvoiceMode;
import com.qcloud.component.my.model.MyAfterSale;
import com.qcloud.component.my.model.MyCollectionStatistics;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.model.MyEvaluation;
import com.qcloud.component.my.model.MyOrderForm;
import com.qcloud.component.my.model.MyShoppingCart;
import com.qcloud.component.my.model.MyToEvaluation;
import com.qcloud.component.my.model.SearchHistory;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.model.key.TypeEnum.ConsigneeType;
import com.qcloud.component.my.model.key.TypeEnum.CouponStateType;
import com.qcloud.component.my.service.ConsigneeService;
import com.qcloud.component.my.service.DeliveryModeService;
import com.qcloud.component.my.service.InvoiceModeService;
import com.qcloud.component.my.service.MyAfterSaleService;
import com.qcloud.component.my.service.MyCollectionService;
import com.qcloud.component.my.service.MyCollectionStatisticsService;
import com.qcloud.component.my.service.MyCouponService;
import com.qcloud.component.my.service.MyEvaluationService;
import com.qcloud.component.my.service.MyOrderFormService;
import com.qcloud.component.my.service.MyShoppingCartService;
import com.qcloud.component.my.service.MyToEvaluationService;
import com.qcloud.component.my.service.SearchHistoryService;
import com.qcloud.component.orderform.OrderformClient;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.template.core.util.string.StringUtils;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;

@Component
public class MyClientImpl implements MyClient {

    @Autowired
    private MyOrderFormService            myOrderFormService;

    @Autowired
    private MyShoppingCartService         myShoppingCartService;

    @Autowired
    private ConsigneeService              consigneeService;

    @Autowired
    private InvoiceModeService            invoiceModeService;

    @Autowired
    private DeliveryModeService           deliveryModeService;

    @Autowired
    private MyCouponService               myCouponService;

    @Autowired
    private MyEvaluationService           myEvaluationService;

    @Autowired
    private MyCollectionService           myCollectionService;

    @Autowired
    private UniqueCodeGenerator           uniqueCodeGenerator;

    @Autowired
    private CommoditycenterClient         commoditycenterClient;

    @Autowired
    private OrderformClient               orderformClient;

    @Autowired
    private MyToEvaluationService         myToEvaluationService;

    @Autowired
    private MyCollectionStatisticsService myCollectionStatisticsService;

    @Autowired
    private SearchHistoryService          searchHistoryService;

    @Override
    public boolean addMyOrderForm(long userId, int state, Date time, long orderId) {

        MyOrderForm myOrderForm = new MyOrderForm();
        myOrderForm.setOrderId(orderId);
        myOrderForm.setUserId(userId);
        myOrderForm.setTime(time);
        myOrderForm.setSubOrderId(-1L);
        myOrderForm.setState(state);
        return myOrderFormService.add(myOrderForm);
    }

    @Override
    public boolean updateMyOrderFormState(long userId, long orderId, int state) {

        MyOrderForm myOrderForm = myOrderFormService.getByOrder(userId, orderId, -1L);
        AssertUtil.assertNotNull(myOrderForm, "用户订单不存在." + orderId);
        myOrderForm.setState(state);
        return myOrderFormService.update(myOrderForm);
    }

    @Override
    public boolean addMyOrderForm(long userId, int state, Date time, long orderId, long... subOrderIds) {

        for (long subOrderId : subOrderIds) {
            MyOrderForm myOrderForm = new MyOrderForm();
            myOrderForm.setOrderId(orderId);
            myOrderForm.setUserId(userId);
            myOrderForm.setTime(time);
            myOrderForm.setSubOrderId(subOrderId);
            myOrderForm.setState(state);
            return myOrderFormService.add(myOrderForm);
        }
        return true;
    }

    @Override
    public boolean updateMyOrderFormState(long userId, long orderId, long subOrderId, int state) {

        MyOrderForm myOrderForm = myOrderFormService.getByOrder(userId, orderId, subOrderId);
        AssertUtil.assertNotNull(myOrderForm, "用户订单不存在." + orderId);
        myOrderForm.setState(state);
        return myOrderFormService.update(myOrderForm);
    }

    @Override
    public boolean removeMyShoppingCartMerchandise(Long unifiedMerchandiseId, Long userId) {

        MyShoppingCart myShoppingCart = myShoppingCartService.getByUnifiedMerchandise(unifiedMerchandiseId, userId);
        if (myShoppingCart == null) {
            return false;
        }
        return myShoppingCartService.delete(myShoppingCart.getId(), userId);
    }

    @Override
    public QMyConsignee getDefaultConsignee(long userId) {

        Consignee consignee = consigneeService.getDefault(userId);
        if (consignee == null) {
            consignee = new Consignee();
            consignee.setAcquiesce(ConsigneeType.DEFAULT.getKey());
            consignee.setAddress("");
            consignee.setCity("");
            consignee.setDistrict("");
            consignee.setEmail("");
            consignee.setMobile("");
            consignee.setName("");
            consignee.setProvince("");
            consignee.setZipCode("");
            consignee.setUserId(userId);
            consignee.setId(-1L);
            return new MyConsigneeEntity(consignee);
        }
        MyConsigneeEntity myConsigneeEntity = new MyConsigneeEntity(consignee);
        return myConsigneeEntity;
    }

    @Override
    public QMyConsignee getConsignee(long consigneeId) {

        Consignee consignee = consigneeService.get(consigneeId);
        if (consignee == null) {
            return null;
        }
        MyConsigneeEntity myConsigneeEntity = new MyConsigneeEntity(consignee);
        return myConsigneeEntity;
    }

    @Override
    public QMyInvoice getDefaultInvoice(long userId) {

        InvoiceMode invoiceMode = invoiceModeService.getByUser(userId);
        if (invoiceMode == null) {
            return null;
        }
        MyInvoiceEntity myInvoiceEntity = new MyInvoiceEntity(invoiceMode);
        return myInvoiceEntity;
    }

    @Override
    public QMyInvoice getInvoice(long id) {

        InvoiceMode invoiceMode = invoiceModeService.get(id);
        if (invoiceMode == null) {
            return null;
        }
        MyInvoiceEntity myInvoiceEntity = new MyInvoiceEntity(invoiceMode);
        return myInvoiceEntity;
    }

    @Override
    public boolean setDefaultInvoice(long userId) {

        InvoiceMode invoiceMode = invoiceModeService.getByUser(userId);
        if (invoiceMode == null) {
            return false;
        }
        invoiceMode.setUserId(userId);
        invoiceMode.setType(InvoiceType.COMMON.getKey());
        invoiceMode.setMode(NeedInvoiceType.NO.getKey());
        invoiceMode.setHead("");
        invoiceMode.setContent("");
        return invoiceModeService.update(invoiceMode);
    }

    @Override
    public QMyDelivery getDefaultDelivery(long userId) {

        DeliveryMode deliveryMode = deliveryModeService.getByUser(userId);
        if (deliveryMode == null) {
            return null;
        }
        MyDeliveryEntity myDeliveryEntity = new MyDeliveryEntity(deliveryMode);
        return myDeliveryEntity;
    }

    @Override
    public QMyDelivery getDelivery(long id) {

        DeliveryMode deliveryMode = deliveryModeService.get(id);
        if (deliveryMode == null) {
            return null;
        }
        MyDeliveryEntity myDeliveryEntity = new MyDeliveryEntity(deliveryMode);
        return myDeliveryEntity;
    }

    @Override
    public Long extractCoupon(long userId, long couponId, long couponItemId, Date validDate, double limitPrice, String name, double price, long merchantId) {

        MyCoupon myCoupon = new MyCoupon();
        myCoupon.setCouponId(couponId);
        myCoupon.setCouponItemId(couponItemId);
        myCoupon.setValidDate(validDate);
        myCoupon.setUserId(userId);
        myCoupon.setPrice(price);
        myCoupon.setLimitPrice(limitPrice);
        myCoupon.setName(name);
        myCoupon.setOrderId(-1l);
        myCoupon.setOrderDate(null);
        myCoupon.setState(CouponStateType.NOTUSE.getKey());
        myCoupon.setCode(uniqueCodeGenerator.generate("personalcenter-my-coupon-code", new HashMap<String, String>()));
        myCoupon.setMerchantId(merchantId);
        myCouponService.add(myCoupon);
        return myCoupon.getId();
    }

    @Override
    public List<QMyCoupon> listExtractCouponByUser(long userId, long couponId) {

        List<MyCoupon> list = myCouponService.listByUserAndCoupon(userId, couponId);
        List<QMyCoupon> entityList = new ArrayList<QMyCoupon>();
        for (MyCoupon myCoupon : list) {
            MyCouponEntity qMyCoupon = new MyCouponEntity(myCoupon);
            entityList.add(qMyCoupon);
        }
        return entityList;
    }

    @Override
    public QMyCoupon getMyCoupon(long userId, long myCouponId) {

        MyCoupon myCoupon = myCouponService.get(myCouponId);
        if (myCoupon == null) {
            return null;
        }
        MyCouponEntity qMyCoupon = new MyCouponEntity(myCoupon);
        return qMyCoupon;
    }

    @Override
    public boolean useMyCoupon(long userId, long myCouponId, Long orderId, Date orderDate) {

        MyCoupon myCoupon = myCouponService.get(myCouponId);
        AssertUtil.assertNotNull(myCoupon, "优惠劵不存在." + myCouponId);
        AssertUtil.assertTrue(myCoupon.getState() == CouponStateType.NOTUSE.getKey(), "优惠劵不是未使用状态." + myCouponId);
        AssertUtil.assertTrue(myCoupon.getValidDate().after(new Date()), "优惠劵不是未使用状态." + myCouponId);
        myCoupon.setState(CouponStateType.USE.getKey());
        myCoupon.setOrderId(orderId);
        myCoupon.setOrderDate(orderDate);
        return myCouponService.update(myCoupon);
    }

    @Override
    public boolean addMyEvaluation(long evaluationId, long userId, long merchandiseId, long toEvaluationId) {

        MyToEvaluationEntity myToEvaluation = getMyToEvaluation(toEvaluationId);
        if (myToEvaluation == null) {
            return false;
        }
        MyEvaluation myEvaluation = new MyEvaluation();
        myEvaluation.setEvaluationId(evaluationId);
        myEvaluation.setOrderItemDetailId(myToEvaluation.getOrderItemDetailId());
        myEvaluation.setOrderTime(myToEvaluation.getOrderDate());
        myEvaluation.setUserId(userId);
        myEvaluation.setMerchandiseId(merchandiseId);
        myEvaluationService.add(myEvaluation);
        myToEvaluationService.delete(toEvaluationId);
        boolean result = canEvaluate(userId, myToEvaluation.getSubOrderId());
        if (!result) {
            updateMyOrderFormState(userId, myToEvaluation.getOrderId(), myToEvaluation.getSubOrderId(), MyOrderStateType.EVALUATED.getKey());
        }
        return true;
    }

    @Override
    public boolean canEvaluate(long userId, long subOrderId) {

        int number = myToEvaluationService.countByUserAndOrder(userId, subOrderId);
        return number > 0;
    }

    @Override
    public boolean isEvaluated(long userId, long toEvaluationId) {

        MyToEvaluation myToEvaluation = myToEvaluationService.get(toEvaluationId);
        return myToEvaluation != null && myToEvaluation.getUserId() == userId;
    }

    @Override
    public int countMerchandiseCollectionNumber(long unifiedMerchandiseId) {

        return myCollectionService.count(unifiedMerchandiseId, CollectionType.MERCHANDISE);
    }

//    @Override
//    public KeyValueVO getMyOrderFormState(long userId, long orderId, long subOrderId, int state) {
//
//        MyOrderForm myOrderForm = myOrderFormService.getByOrder(userId, orderId, subOrderId);
//        String value = orderformClient.getNormalPersonalOrderStateDesc(state);
//        // String value = MyOrderStateType.getNameByState(myOrderForm.getState());
//        KeyValueVO vo = new KeyValueVO();
//        vo.setKey(String.valueOf(myOrderForm.getState()));
//        vo.setValue(value);
//        return vo;
//    }

    @Override
    public List<QMyCoupon> listMyCouponCanUse(long userId, Long merchantId, Double sum) {

        List<MyCoupon> list = myCouponService.listCanUseByUser(userId, merchantId, sum, 0, Integer.MAX_VALUE);
        List<QMyCoupon> entityList = new ArrayList<QMyCoupon>();
        for (MyCoupon myCoupon : list) {
            MyCouponEntity myCouponEntity = new MyCouponEntity(myCoupon);
            entityList.add(myCouponEntity);
        }
        return entityList;
    }

    @Override
    public boolean cancleUseMyCoupon(long userId, long myCouponId) {

        MyCoupon myCoupon = myCouponService.get(myCouponId);
        myCoupon.setState(CouponStateType.NOTUSE.getKey());
        return myCouponService.update(myCoupon);
    }

    @Override
    public QMyCoupon getMyCoupon(String code) {

        MyCoupon myCoupon = myCouponService.getByCode(code);
        return new MyCouponEntity(myCoupon);
    }

    @Override
    public int countMerchantCollectionNumber(long merchantId) {

        return myCollectionService.count(merchantId, CollectionType.MERCHANT);
    }

    @Override
    public boolean addMyToEvaluation(long userId, long unifiedMerchandiseId, double discount, long subOrderId, long orderItemId, Date orderDate, String orderNumber) {

        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        if (MerchandiseType.SINGLE.equals(unifiedMerchandise.getType())) {
            QMerchantOrder merchantOrder = orderformClient.getMerchantOrder(subOrderId, orderDate);
            long orderItemDetailId = -1;
            List<QOrderItem> list = merchantOrder.getOrderItemList();
            for (QOrderItem qOrderItem : list) {
                if (qOrderItem.getId() == orderItemId) {
                    orderItemDetailId = qOrderItem.getOrderItemDetailList().get(0).getId();
                }
            }
            MyToEvaluation myToEvaluation = new MyToEvaluation();
            myToEvaluation.setDiscount(discount);
            myToEvaluation.setImage(unifiedMerchandise.getImage());
            myToEvaluation.setMerchandiseId(unifiedMerchandise.getList().get(0).getMerchandiseId());
            myToEvaluation.setMerchantId(unifiedMerchandise.getMerchantId());
            myToEvaluation.setName(unifiedMerchandise.getName());
            myToEvaluation.setOrderDate(orderDate);
            myToEvaluation.setOrderId(merchantOrder.getOrder().getId());
            myToEvaluation.setSubOrderId(subOrderId);
            myToEvaluation.setOrderItemId(orderItemId);
            myToEvaluation.setOrderItemDetailId(orderItemDetailId);
            myToEvaluation.setOrderNumber(orderNumber);
            myToEvaluation.setSignDate(new Date());
            myToEvaluation.setUnifiedMerchandiseId(unifiedMerchandiseId);
            myToEvaluation.setUserId(userId);
            return myToEvaluationService.add(myToEvaluation);
        }
        return false;
    }

    @Override
    public MyToEvaluationEntity getMyToEvaluation(long toEvaluationId) {

        MyToEvaluation myToEvaluation = myToEvaluationService.get(toEvaluationId);
        if (myToEvaluation == null) {
            return null;
        }
        return new MyToEvaluationEntity(myToEvaluation);
    }

    @Autowired
    MyAfterSaleService myAfterSaleService;

    @Override
    public boolean addMyAfterSale(long userId, long afterSaleId, long orderId, long subOrderId, AfterSaleType afterSaleType) {

        MyAfterSale myAfterSale = new MyAfterSale();
        myAfterSale.setAfterSaleId(afterSaleId);
        myAfterSale.setType(afterSaleType.getKey());
        myAfterSale.setUserId(userId);
        myAfterSale.setOrderId(orderId);
        myAfterSale.setSubOrderId(subOrderId);
        return myAfterSaleService.add(myAfterSale);
    }

    @Override
    public int statCollection(long userId, int type) {

        int collectNumber = 0;
        List<MyCollectionStatistics> merchandiseList = myCollectionStatisticsService.listByUserAndType(userId, type);
        for (MyCollectionStatistics myCollectionStatistics : merchandiseList) {
            collectNumber += myCollectionStatistics.getNumber();
        }
        return collectNumber;
    }

    @Override
    public boolean addMySearchHistory(long userId, int type, String keywords) {

        keywords = StringUtil.nullToEmpty(keywords).trim();
        if (StringUtils.isEmpty(keywords)) {
            return false;
        }
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setKeywords(keywords);
        searchHistory.setType(type);
        searchHistory.setUserId(userId);
        return searchHistoryService.add(searchHistory);
    }

    @Override
    public List<String> listMySearchHistory(long userId, int type, int number) {

        return searchHistoryService.list(userId, type, number);
    }

    @Override
    public boolean clearMySearchHistory(long userId, int type) {

        return searchHistoryService.clear(userId, type);
    }

    @Override
    public int getMyShoppingCartMerchandiseNumber(long userId, long unifiedMerchandiseId) {

        MyShoppingCart myShoppingCart = myShoppingCartService.getByUnifiedMerchandise(unifiedMerchandiseId, userId);
        return myShoppingCart == null ? 0 : myShoppingCart.getNumber();
    }

    @Override
    public boolean addMyShoppingCart(long userId, long unifiedMerchandiseId, int number) {

        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "获取商品信息失败.");
        MyShoppingCart myShoppingCart = myShoppingCartService.getByUnifiedMerchandise(unifiedMerchandiseId, userId);
        if (myShoppingCart == null) {
            myShoppingCart = new MyShoppingCart();
            myShoppingCart.setTime(new Date());
            myShoppingCart.setNumber(number);
            myShoppingCart.setUnifiedMerchandiseId(unifiedMerchandiseId);
            myShoppingCart.setMerchantId(unifiedMerchandise.getMerchantId());
            myShoppingCart.setMerchantClassifyId(unifiedMerchandise.getList().get(0).getMerchantClassifyId());
            myShoppingCart.setUserId(userId);
            myShoppingCartService.add(myShoppingCart);
        } else {
            myShoppingCart.setTime(new Date());
            // 数量累计
            myShoppingCart.setNumber(number + myShoppingCart.getNumber());
            myShoppingCartService.update(myShoppingCart);
        }
        return false;
    }
}
