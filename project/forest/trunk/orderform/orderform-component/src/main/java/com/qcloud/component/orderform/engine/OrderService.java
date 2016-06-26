package com.qcloud.component.orderform.engine;

import java.util.Date;
import java.util.Map;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.my.QMyConsignee;
import com.qcloud.component.orderform.OrderContext;
import com.qcloud.component.orderform.entity.MerchantOrderEntity;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.personalcenter.QUser;

public interface OrderService {

    /**
     * 
     * @param context
     * @return
     */
    OrderEntity prepareOrderNormal(OrderContext context);

    /**
     * 正常下单
     * 
     * @param context
     */
    OrderEntity orderNormal(OrderContext context);

    /**
     *秒杀
     * 
     * @param context
     */
    OrderEntity orderSeckill(QUser user, QUnifiedMerchandise merchandise);

    /**
     * 兑换
     * 
     * @param context
     */
    OrderEntity orderExchange(QUser user, QMyConsignee consignee, QUnifiedMerchandise merchandise, int number, int state);

    // 团购
    OrderEntity orderGroupbuys(OrderContext context, String orderNumber, Date orderDate, int paymentMode, double cash, int state);

    // 修改订单价格
    boolean changeOrderCash(double sum);

    //
    boolean modifyMerchantOrder(MerchantOrderEntity merchantOrderEntity, Map<QUnifiedMerchandise, Integer> addMerchandiseMap, Map<QUnifiedMerchandise, Integer> deleteMerchandiseMap);
}
