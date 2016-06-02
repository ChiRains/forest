package com.qcloud.component.orderform.web.handler;

import com.qcloud.component.orderform.QMerchantOrder;
import com.qcloud.component.orderform.QOrder;
import com.qcloud.component.orderform.web.vo.merchant.MerchantOrderVO;
import com.qcloud.component.orderform.web.vo.personal.OrderCVO;
import com.qcloud.component.orderform.web.vo.personal.OrderMVO;
import com.qcloud.component.orderform.web.vo.personal.OrderVO;

public interface OrderHandler {

    MerchantOrderVO toMerchantVO(QMerchantOrder merchantOrder);

    OrderVO toVO(QOrder order);

    OrderMVO toVO4Merchant(QOrder order);

    OrderCVO toVO4Classify(QOrder order);

    OrderVO toVO(QMerchantOrder merchantOrder);

    OrderMVO toVO4Merchant(QMerchantOrder merchantOrder);

    OrderCVO toVO4Classify(QMerchantOrder merchantOrder);
}
