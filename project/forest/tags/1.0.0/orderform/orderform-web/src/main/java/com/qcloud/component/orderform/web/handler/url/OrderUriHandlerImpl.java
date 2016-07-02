package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class OrderUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/orderForm/prepareOrder4Merchant.do");
        list.add("/orderForm/prepareOrder4Classify.do");
        list.add("/orderForm/prepareOrder.do");
        list.add("/orderForm/prepareOrder4MerchantByOrder.do");
        list.add("/orderForm/prepareOrder4ClassifyByOrder.do");
        list.add("/orderForm/prepareOrderByOrder.do");
        list.add("/orderForm/order.do");
        list.add("/orderForm/order4MultiMerchant.do");
        list.add("/orderForm/orderAgain.do");
        list.add("/orderForm/exchangeConsumption.do");
        list.add("/orderForm/exchangeIntegral.do");
        list.add("/orderForm/get.do");
        list.add("/orderForm/get4Classify.do");
        list.add("/orderForm/get4Merchant.do");
        list.add("/orderForm/getMerchantOrder.do");
        list.add("/orderForm/getMerchantOrder4Classify.do");
        list.add("/orderForm/getMerchantOrder4Merchant.do");
        list.add("/orderForm/changeConsignee.do");
        //
        list.add("/app/orderForm/prepareOrder4Merchant.do");
        list.add("/app/orderForm/prepareOrder4Classify.do");
        list.add("/app/orderForm/prepareOrder.do");
        list.add("/app/orderForm/order.do");
        list.add("/app/orderForm/order4MultiMerchant.do");
        list.add("/app/orderForm/orderAgain.do");
        list.add("/app/orderForm/get.do");
        list.add("/app/orderForm/get4Classify.do");
        list.add("/app/orderForm/get4Merchant.do");
        list.add("/app/orderForm/getMerchantOrder.do");
        list.add("/app/orderForm/getMerchantOrder4Classify.do");
        list.add("/app/orderForm/getMerchantOrder4Merchant.do");
        list.add("/app/orderForm/getSubOrder.do");
        list.add("/app/orderForm/orderByMultiMerchant.do");
        list.add("/app/orderForm/exchangeConsumption.do");
        list.add("/app/orderForm/exchangeIntegral.do");
        list.add("/app/orderForm/changeConsignee.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/orderForm/prepareOrder4Merchant.do");
        list.add("/app/orderForm/prepareOrder4Classify.do");
        list.add("/app/orderForm/prepareOrder.do");
        list.add("/app/orderForm/order.do");
        list.add("/app/orderForm/order4MultiMerchant.do");
        list.add("/app/orderForm/orderAgain.do");
        list.add("/app/orderForm/get.do");
        list.add("/app/orderForm/get4Classify.do");
        list.add("/app/orderForm/get4Merchant.do");
        list.add("/app/orderForm/getMerchantOrder.do");
        list.add("/app/orderForm/getMerchantOrder4Classify.do");
        list.add("/app/orderForm/getMerchantOrder4Merchant.do");
        list.add("/app/orderForm/getSubOrder.do");
        list.add("/app/orderForm/exchangeConsumption.do");
        list.add("/app/orderForm/exchangeIntegral.do");
        list.add("/app/orderForm/changeConsignee.do");
        return list;
    }
}
