package com.qcloud.component.my;

import java.util.Date;
import java.util.List;
import com.qcloud.component.publicdata.KeyValueVO;

public interface MyClient {

    boolean addMyOrderForm(long userId, int state, Date time, long orderId);

    boolean addMyOrderForm(long userId, int state, Date time, long orderId, long... subOrderId);

    boolean updateMyOrderFormState(long userId, long orderId, int state);

    boolean updateMyOrderFormState(long userId, long orderId, long subOrderId, int state);

    boolean removeMyShoppingCartMerchandise(Long unifiedMerchandiseId, Long userId);

    QMyConsignee getDefaultConsignee(long userId);

    QMyConsignee getConsignee(long consigneeId);

    QMyInvoice getDefaultInvoice(long userId);

    QMyInvoice getInvoice(long id);

    boolean setDefaultInvoice(long userId);

    QMyDelivery getDefaultDelivery(long userId);

    QMyDelivery getDelivery(long id);

    Long extractCoupon(long userId, long couponId, long couponItemId, Date validDate, Date invalidDate, double limitPrice, String name, double price, long merchantId);

    List<QMyCoupon> listExtractCouponByUser(long userId, long couponId);

    QMyCoupon getMyCoupon(long userId, long myCouponId);

    QMyCoupon getMyCoupon(String code);

    List<QMyCoupon> listMyCouponCanUse(long userId, Long merchantId, Double sum);

    boolean useMyCoupon(long userId, long myCouponId, Long orderId, Date orderDate);

    boolean cancleUseMyCoupon(long userId, long myCouponId);

    QMyToEvaluation getMyToEvaluation(long toEvaluationId);

    List<QMyToEvaluation> listByUserAndOrderId(long userId, long orderId);

    List<QMyToAppendEvaluation> listAppendEvaluation(long userId, long orderId);

    QMyToAppendEvaluation getMyToAppendEvaluation(long appEvaluationId);

    //
    int getMyShoppingCartMerchandiseNumber(long userId, long unifiedMerchandiseId);

    //
    boolean addMyAfterSale(long userId, long afterSaleId, long orderId, long subOrderId, AfterSaleType afterSaleType);

    //
    boolean addMyToEvaluation(long userId, long unifiedMerchandiseId, double discount, long subOrderId, long orderItemId, Date orderDate, String orderNumber);

    //
    boolean addMyEvaluation(long evaluationId, long userId, long merchandiseId, long toEvaluationId);

    // true 可评论
    boolean canEvaluate(long userId, long subOrderId);

    // true 已评论
    boolean isEvaluated(long userId, long toEvaluationId);

    int countMerchandiseCollectionNumber(long unifiedMerchandiseId);

    int countMerchantCollectionNumber(long merchantId);

    // KeyValueVO getMyOrderFormState(long userId, long orderId, long subOrderId, int state);
    int statCollection(long userId, int type);

    boolean addMySearchHistory(long userId, int type, String keywords);

    List<String> listMySearchHistory(long userId, int type, int number);

    boolean clearMySearchHistory(long userId, int type);

    boolean addMyShoppingCart(long userId, long unifiedMerchandiseId, int number);

    boolean deleteAppendEvaluation(long appEvaluationId);
}
