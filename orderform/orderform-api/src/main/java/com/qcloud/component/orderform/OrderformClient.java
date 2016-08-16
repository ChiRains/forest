package com.qcloud.component.orderform;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.my.AfterSaleType;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.data.Page;

public interface OrderformClient {

    QOrder orderNormal(OrderContext context);

    QOrder orderSeckill(QUser user, QUnifiedMerchandise merchandise);

    QOrder orderGroupbuys(OrderContext context, String orderNumber, Date orderDate, int paymentMode, double cash, int state);

    QOrder getOrder(String orderNumber);

    QOrder getOrder(Long orderId, Date orderDate);

    Page<QOrder> pageOrders(Date starttime, Date endtime, int state, int page, int pagesize);

    QMerchantOrder getMerchantOrder(String orderNumber);

    QMerchantOrder getMerchantOrder(Long subOrderId, Date orderDate);

    int getNormalMerchantOrderState(int state);

    int getNormalPersonalOrderState(int state);

    String getNormalPersonalOrderStateDesc(int state);

    String generateOrderNumber();

    boolean exchangeOrderState(Long orderId, Date orderDate, int state, Long authentication);

    boolean exchangeSubOrderState(Long subOrderId, Date orderDate, int state, Long authentication);

    QAfterSaleOrder getAfterSaleOrder(long afterSaleId, AfterSaleType type);

    List<QAfterSaleOrder> listByOrder(Long orderId, Date orderDate);

    List<QAfterSaleOrder> listByMerchantOrder(Long subOrderId, Date orderDate);

    List<QAfterSaleOrder> listByOrder(String orderNumber);

    List<QAfterSaleOrder> listByMerchantOrder(String orderNumber);

    boolean modifyMerchantOrder(Long subOrderId, Date orderDate, Map<QUnifiedMerchandise, Integer> addMerchandiseMap, Map<QUnifiedMerchandise, Integer> deleteMerchandiseMap);
}