package com.qcloud.component.orderform.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class AfterSaleStateUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/afterSaleState/refuseRefund.do");
        list.add("/afterSaleState/payRefund.do");
        //
        list.add("/afterSaleState/confirmReturn.do");
        list.add("/afterSaleState/refuseReturn.do");
        list.add("/afterSaleState/signReturn.do");
        list.add("/afterSaleState/payReturn.do");
        //
        list.add("/afterSaleState/confirmExchange.do");
        list.add("/afterSaleState/refuseExchange.do");
        list.add("/afterSaleState/signExchange.do");
        list.add("/afterSaleState/shippedAgainExchange.do");
        //
        list.add("/app/afterSaleState/refuseRefund.do");
        list.add("/app/afterSaleState/payRefund.do");
        //
        list.add("/app/afterSaleState/confirmReturn.do");
        list.add("/app/afterSaleState/refuseReturn.do");
        list.add("/app/afterSaleState/signReturn.do");
        list.add("/app/afterSaleState/payReturn.do");
        //
        list.add("/app/afterSaleState/confirmExchange.do");
        list.add("/app/afterSaleState/refuseExchange.do");
        list.add("/app/afterSaleState/signExchange.do");
        list.add("/app/afterSaleState/shippedAgainExchange.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/afterSaleState/confirmRefund.do");
        list.add("/afterSaleState/confirmPayRefund.do");
        list.add("/afterSaleState/cancelRefund.do");
        //
        list.add("/afterSaleState/shippedReturn.do");
        list.add("/afterSaleState/confirmPayReturn.do");
        //
        list.add("/afterSaleState/shippedExchange.do");
        list.add("/afterSaleState/signAgainExchange.do");
        //
        list.add("/afterSale/listAfterSaleInformation.do");
        //
        list.add("/app/afterSaleState/confirmRefund.do");
        list.add("/app/afterSaleState/confirmPayRefund.do");
        list.add("/app/afterSaleState/cancelRefund.do");
        //
        list.add("/app/afterSaleState/shippedReturn.do");
        list.add("/app/afterSaleState/confirmPayReturn.do");
        //
        list.add("/app/afterSaleState/shippedExchange.do");
        list.add("/app/afterSaleState/signAgainExchange.do");
        //
        list.add("/app/afterSale/listAfterSaleInformation.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/afterSaleState/confirmRefund.do");
        list.add("/afterSaleState/confirmPayRefund.do");
        list.add("/afterSaleState/refuseRefund.do");
        list.add("/afterSaleState/payRefund.do");
        list.add("/afterSaleState/cancelRefund.do");
        list.add("/app/afterSaleState/confirmRefund.do");
        list.add("/app/afterSaleState/confirmPayRefund.do");
        list.add("/app/afterSaleState/refuseRefund.do");
        list.add("/app/afterSaleState/payRefund.do");
        list.add("/app/afterSaleState/cancelRefund.do");
        //
        list.add("/afterSaleState/confirmReturn.do");
        list.add("/afterSaleState/refuseReturn.do");
        list.add("/afterSaleState/signReturn.do");
        list.add("/afterSaleState/payReturn.do");
        //
        list.add("/afterSaleState/confirmExchange.do");
        list.add("/afterSaleState/refuseExchange.do");
        list.add("/afterSaleState/signExchange.do");
        list.add("/afterSaleState/shippedAgainExchange.do");
        //
        list.add("/app/afterSaleState/confirmReturn.do");
        list.add("/app/afterSaleState/refuseReturn.do");
        list.add("/app/afterSaleState/signReturn.do");
        list.add("/app/afterSaleState/payReturn.do");
        //
        list.add("/app/afterSaleState/confirmExchange.do");
        list.add("/app/afterSaleState/refuseExchange.do");
        list.add("/app/afterSaleState/signExchange.do");
        list.add("/app/afterSaleState/shippedAgainExchange.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/afterSaleState/confirmRefund.do");
        list.add("/app/afterSaleState/confirmPayRefund.do");
        list.add("/app/afterSaleState/refuseRefund.do");
        list.add("/app/afterSaleState/payRefund.do");
        list.add("/app/afterSaleState/cancelRefund.do");
        //
        list.add("/app/afterSaleState/confirmReturn.do");
        list.add("/app/afterSaleState/refuseReturn.do");
        list.add("/app/afterSaleState/shippedReturn.do");
        list.add("/app/afterSaleState/signReturn.do");
        list.add("/app/afterSaleState/payReturn.do");
        list.add("/app/afterSaleState/confirmPayReturn.do");
        //
        list.add("/app/afterSaleState/confirmExchange.do");
        list.add("/app/afterSaleState/refuseExchange.do");
        list.add("/app/afterSaleState/shippedExchange.do");
        list.add("/app/afterSaleState/signExchange.do");
        list.add("/app/afterSaleState/shippedAgainExchange.do");
        list.add("/app/afterSaleState/signAgainExchange.do");
        list.add("/app/afterSale/listAfterSaleInformation.do");
        //
        return list;
    }
}
