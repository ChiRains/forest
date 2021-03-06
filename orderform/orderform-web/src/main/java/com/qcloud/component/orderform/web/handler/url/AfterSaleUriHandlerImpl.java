package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class AfterSaleUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/afterSale/listByOrder.do");
        list.add("/afterSale/listBySubOrder.do");
        list.add("/afterSale/returnOrder.do");
        list.add("/afterSale/refundOrder.do");
        list.add("/afterSale/exchangeOrder.do");
        list.add("/afterSale/reApplyReturnOrder.do");
        list.add("/afterSale/reApplyRefundOrder.do");
        list.add("/afterSale/reApplyExchangeOrder.do");
        list.add("/app/afterSale/listByOrder.do");
        list.add("/app/afterSale/listBySubOrder.do");
        list.add("/app/afterSale/returnOrder.do");
        list.add("/app/afterSale/refundOrder.do");
        list.add("/app/afterSale/exchangeOrder.do");
        list.add("/app/afterSale/reApplyReturnOrder.do");
        list.add("/app/afterSale/reApplyRefundOrder.do");
        list.add("/app/afterSale/reApplyExchangeOrder.do");
        //
        list.add("/afterSale/getAfterSaleInfo.do");
        list.add("/afterSale/listRefundReason.do");
        list.add("/afterSale/applyReturnOrder.do");
        list.add("/app/afterSale/getAfterSaleInfo.do");
        list.add("/app/afterSale/listRefundReason.do");
        list.add("/app/afterSale/applyReturnOrder.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/afterSale/listByOrder.do");
        list.add("/app/afterSale/listBySubOrder.do");
        list.add("/app/afterSale/returnOrder.do");
        list.add("/app/afterSale/refundOrder.do");
        list.add("/app/afterSale/exchangeOrder.do");
        list.add("/app/afterSale/reApplyReturnOrder.do");
        list.add("/app/afterSale/reApplyRefundOrder.do");
        list.add("/app/afterSale/reApplyExchangeOrder.do");
        //
        list.add("/app/afterSale/getAfterSaleInfo.do");
        list.add("/app/afterSale/listRefundReason.do");
        list.add("/app/afterSale/applyReturnOrder.do");
        return list;
    }
}
