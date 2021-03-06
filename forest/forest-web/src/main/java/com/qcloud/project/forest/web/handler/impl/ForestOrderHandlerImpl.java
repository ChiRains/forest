package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.service.CouponItemsService;
import com.qcloud.component.my.DeliveryModeType;
import com.qcloud.component.my.InvoiceType;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.QMyCoupon;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.orderform.PaymentModeType;
import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.QOrderItem;
import com.qcloud.component.orderform.QOrderItemDetail;
import com.qcloud.component.orderform.engine.AfterSaleSelecterService;
import com.qcloud.component.orderform.model.OrderDiscount;
import com.qcloud.component.orderform.service.OrderDiscountService;
import com.qcloud.component.orderform.web.vo.personal.OrderItemVO;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.project.forest.model.ForestOrder;
import com.qcloud.project.forest.model.GiftCoupon;
import com.qcloud.project.forest.model.key.TypeEnum.ForestOrderState;
import com.qcloud.project.forest.service.GiftCouponService;
import com.qcloud.project.forest.web.handler.ForestOrderHandler;
import com.qcloud.project.forest.web.vo.CombinationItemVO;
import com.qcloud.project.forest.web.vo.ForestOrderVO;
import com.qcloud.project.forest.web.vo.admin.AdminForestOrderVO;

@Component
public class ForestOrderHandlerImpl implements ForestOrderHandler {

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Autowired
    private GiftCouponService     giftCouponService;

    @Autowired
    private SellercenterClient    sellercenterClient;

    @Autowired
    private CouponItemsService    couponItemsService;

    @Autowired
    private FileSDKClient         fileSDKClient;

    @Autowired
    private OrderDiscountService  orderDiscountService;

    @Autowired
    private MyClient              myClient;

    @Override
    public List<ForestOrderVO> toVOList(List<ForestOrder> list) {

        List<ForestOrderVO> voList = new ArrayList<ForestOrderVO>();
        for (ForestOrder forestOrder : list) {
            voList.add(toVO(forestOrder));
        }
        return voList;
    }

    @Override
    public ForestOrderVO toVO(ForestOrder forestOrder) {

        String json = Json.toJson(forestOrder);
        return Json.toObject(json, ForestOrderVO.class, true);
    }

    @Override
    public List<AdminForestOrderVO> toVOList4Admin(List<ForestOrder> list) {

        List<AdminForestOrderVO> voList = new ArrayList<AdminForestOrderVO>();
        for (ForestOrder adminForestOrder : list) {
            voList.add(toVO4Admin(adminForestOrder));
        }
        return voList;
    }

    @Override
    public AdminForestOrderVO toVO4Admin(ForestOrder forestOrder) {

        String json = Json.toJson(forestOrder);
        return Json.toObject(json, AdminForestOrderVO.class, true);
    }

    @Override
    public ForestOrderVO toOrderVO(ForestOrder forestOrder, QOrder order) {

        String json = Json.toJson(forestOrder);
        ForestOrderVO orderVO = Json.toObject(json, ForestOrderVO.class, true);
        orderVO.setExplain(order.getMerchantOrderList().get(0).getExplain());
        List<QMerchantOrder> merchantOrderList = order.getMerchantOrderList();
        int merchandiseNumber = 0;
        List<OrderDiscount> discountList = new ArrayList<OrderDiscount>();
        for (QMerchantOrder qMerchantOrder : merchantOrderList) {
            List<CombinationItemVO> combinationItemList = new ArrayList<CombinationItemVO>();
            for (QOrderItem qOrderItem : qMerchantOrder.getOrderItemList()) {
                QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(qOrderItem.getUnifiedMerchandiseId());
                if (unifiedMerchandise.getType().equals(UnifiedMerchandiseType.COMBINATION)) {// 自由搭配
                    //
                    CombinationItemVO combinationItemVO = toOrderItemVOList(qOrderItem, unifiedMerchandise);
                    combinationItemVO.setOrderItemId(qOrderItem.getId());
                    combinationItemList.add(combinationItemVO);
                } else {// 单品
                    orderVO.getOrderItemList().add(toOrderItemVO(qOrderItem, unifiedMerchandise));
                }
                merchandiseNumber++;
                orderVO.getImageList().add(fileSDKClient.getFileServerUrl() + unifiedMerchandise.getImage());
            }
            discountList = orderDiscountService.listBySubOrder(qMerchantOrder.getId());
            orderVO.setCombinationItemList(combinationItemList);
            orderVO.setExplain(qMerchantOrder.getExplain());
            orderVO.setRemind(qMerchantOrder.canRemind());
        }
        orderVO.setEvaluation(order.canEvaluation());
        //
        if (!discountList.isEmpty()) {
            QMyCoupon myCoupon = myClient.getMyCoupon(order.getUserId(), discountList.get(0).getDiscountId());
            CouponItems couponItems = couponItemsService.get(myCoupon.getCouponItemId());
            if (couponItems == null) {
                couponItems = new CouponItems();
                couponItems.setId(-1l);
                couponItems.setName("没有使用任何优惠券");
            }
            orderVO.setCouponItems(couponItems);
        } else {
            CouponItems couponItems = new CouponItems();
            couponItems.setId(-1l);
            couponItems.setName("没有使用任何优惠券");
            orderVO.setCouponItems(couponItems);
        }
        //
        GiftCoupon giftCoupon = giftCouponService.get(forestOrder.getGiftCouponId());
        if (giftCoupon == null) {
            giftCoupon = new GiftCoupon();
            giftCoupon.setName("没有使用任何赠品券");
            giftCoupon.setId(-1l);
        }
        orderVO.setGiftCoupon(giftCoupon);
        //
        if (StringUtils.isNotEmpty(orderVO.getProve())) {
            orderVO.setProve(fileSDKClient.getFileServerUrl() + orderVO.getProve());
        } else {
            orderVO.setProve("");
        }
        orderVO.setInvoiceType(order.getInvoiceType());
        orderVO.setPaymentMode(order.getPaymentMode());
        orderVO.setCounpon(order.getCoupon());
        orderVO.setCash(order.getCash());
        orderVO.setPreferential(order.getPreferential());
        orderVO.setSum(order.getSum());
        //
        QMerchant merchant = sellercenterClient.getMerchant(forestOrder.getMerchantId());
        orderVO.setMerchantName(merchant.getName());
        orderVO.setMerchantImage(fileSDKClient.getFileServerUrl() + merchant.getImage());
        //
        orderVO.setConsignee(order.getConsignee());
        orderVO.setMobile(order.getMobile());
        orderVO.setAddress(order.getAddress());
        //
        orderVO.setState(order.getState());
        orderVO.setDeliveryModeStr(DeliveryModeType.get(order.getMerchantOrderList().get(0).getDeliveryMode()).getName());
        orderVO.setPaymentModeStr(PaymentModeType.get(order.getPaymentMode()).getName());
        orderVO.setStateStr(ForestOrderState.get(order.getState()));
        orderVO.setInvoiceTypeStr(InvoiceType.get(order.getInvoiceType()).getName());
        orderVO.setMerchandiseNumber(merchandiseNumber);
        orderVO.setOrderDateStr(DateUtil.date2String(order.getOrderDate(), "yyyy-MM-dd HH:mm:ss"));
        orderVO.setDeliveryDateStr(order.getMerchantOrderList().get(0).getDeliveryTimeStr());
        orderVO.setPayDateStr(DateUtil.date2String(forestOrder.getPayDate(), "yyyy-MM-dd HH:mm:ss"));
        //
        long payConfig = 1 * 60 * 60 * 1000;// 配置
        long payLimit = payConfig + order.getOrderDate().getTime();// 支付限制时间
        long residualTime = payLimit - new Date().getTime();// 剩余支付时间
        long hours = residualTime / 1000 / 60 / 60;
        long minutes = residualTime / 1000 / 60 % 60;
        long seconds = residualTime / 1000 % 60;
        orderVO.setResidualHour((int) hours);
        orderVO.setResidualMinute((int) minutes);
        orderVO.setResidualSecond((int) seconds);
        orderVO.setResidualTime(residualTime);
        return orderVO;
    }

    private CombinationItemVO toOrderItemVOList(QOrderItem qOrderItem, QUnifiedMerchandise unifiedMerchandise) {

        CombinationItemVO combinationItemVO = new CombinationItemVO();
        combinationItemVO.setDiscount(qOrderItem.getDiscount());
        combinationItemVO.setPrice(qOrderItem.getPrice());
        combinationItemVO.setImage(fileSDKClient.getFileServerUrl() + qOrderItem.getImage());
        combinationItemVO.setName(qOrderItem.getName());
        combinationItemVO.setUnifiedMerchandiseId(qOrderItem.getUnifiedMerchandiseId());
        combinationItemVO.setAfterSale(qOrderItem.isAfterSale());
        combinationItemVO.setEvaluation(qOrderItem.isEvaluation());
        combinationItemVO.setNumber(qOrderItem.getNumber());
        //
        List<OrderItemVO> orderItemList = new ArrayList<OrderItemVO>();
        List<QOrderItemDetail> orderItemDetailList = qOrderItem.getOrderItemDetailList();
        // 从orderItemDetails里面取数据
        for (QOrderItemDetail qOrderItemDetail : orderItemDetailList) {
            OrderItemVO itemVO = new OrderItemVO();
            itemVO.setOrderItemId(qOrderItem.getId());
            itemVO.setOrderItemDetailId(qOrderItemDetail.getId());
            itemVO.setName(qOrderItemDetail.getName());
            itemVO.setImage(fileSDKClient.getFileServerUrl() + qOrderItemDetail.getImage());
            itemVO.setSpecifications(qOrderItemDetail.getSpecifications());
            itemVO.setDiscount(qOrderItemDetail.getDiscount());
            itemVO.setNumber(qOrderItemDetail.getNumber());
            itemVO.setUnifiedMerchandiseId(qOrderItemDetail.getUnifiedMerchandiseId());
            for (QUnifiedMerchandise merchandise : unifiedMerchandise.getList()) {
                if (merchandise.getId().longValue() == qOrderItemDetail.getUnifiedMerchandiseId()) {
                    itemVO.setPrice(merchandise.getPrice());
                    itemVO.setUnit(merchandise.getUnit());
                    itemVO.setCode(merchandise.getCode());
                    break;
                }
            }
            orderItemList.add(itemVO);
            combinationItemVO.setDesc(StringUtil.nullToEmpty(combinationItemVO.getDesc()) + "+" + itemVO.getName());
        }
        // for (QUnifiedMerchandise merchandise : unifiedMerchandise.getList()) {
        // OrderItemVO itemVO = new OrderItemVO();
        // itemVO.setName(merchandise.getName());
        // itemVO.setImage(merchandise.getImage());
        // itemVO.setSpecifications(merchandise.getSpecifications());
        // itemVO.setDiscount(merchandise.getDiscount());
        // itemVO.setNumber(merchandise.getNumber());
        // itemVO.setUnifiedMerchandiseId(merchandise.getId());
        // itemVO.setPrice(merchandise.getPrice());
        // orderItemList.add(itemVO);
        // combinationItemVO.setDesc(combinationItemVO.getDesc() + "+" + itemVO.getName());
        // }
        combinationItemVO.setDesc(combinationItemVO.getDesc().substring(1, combinationItemVO.getDesc().length() - 1));
        combinationItemVO.setOrderItemList(orderItemList);
        return combinationItemVO;
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
        orderItemVO.setCode(unifiedMerchandise.getCode());
        orderItemVO.setOrderItemId(qOrderItem.getId());
        orderItemVO.setAfterSale(qOrderItem.isAfterSale());
        orderItemVO.setEvaluation(qOrderItem.isEvaluation());
        orderItemVO.setUnit(unifiedMerchandise.getUnit());
        orderItemVO.setOrderItemDetailId(qOrderItem.getOrderItemDetailList().get(0).getId());
        return orderItemVO;
    }
}
