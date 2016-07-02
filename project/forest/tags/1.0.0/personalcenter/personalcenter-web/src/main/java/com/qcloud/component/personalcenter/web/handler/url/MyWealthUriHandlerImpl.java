package com.qcloud.component.personalcenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MyWealthUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/myWealth/integralList.do");
        list.add("/admin/myWealth/commissionList.do");
        list.add("/admin/myWealth/couponbondList.do");
        list.add("/admin/myWealth/toEditIntegral.do");
        list.add("/admin/myWealth/toEditCouponbond.do");
        list.add("/admin/myWealth/editIntegral.do");
        list.add("/admin/myWealth/editCouponbond.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        //
        list.add("/admin/myWealth/integralList.do");
        list.add("/admin/myWealth/commissionList.do");
        list.add("/admin/myWealth/couponbondList.do");
        list.add("/admin/myWealth/toEditIntegral.do");
        list.add("/admin/myWealth/toEditCouponbond.do");
        list.add("/admin/myWealth/editIntegral.do");
        list.add("/admin/myWealth/editCouponbond.do");
        //
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> integralList = stringToList("/admin/myWealth/integralList.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/myWealth/toEditIntegral.do", integralList);
        map.put("/admin/myWealth/editIntegral.do", integralList);
        List<String> couponbondList = stringToList("/admin/myWealth/couponbondList.do");
        map.put("/admin/myWealth/toEditCouponbond.do", couponbondList);
        map.put("/admin/myWealth/editCouponbond.do", couponbondList);
        return map;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/myWealth/listTopWealth.do");
        return list;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/myWealth/listIntegralDetail.do");
        list.add("/myWealth/listCommissionDetail.do");
        list.add("/myWealth/listCurrencyDetail.do");
        list.add("/myWealth/statIntegralTrend.do");
        list.add("/myWealth/statCommissionTrend.do");
        list.add("/myWealth/statCurrencyTrend.do");
        list.add("/myWealth/withdrawCommission.do");
        list.add("/myWealth/listWithdrawCommission.do");
        list.add("/myWealth/listWithdrawedCommission.do");
        list.add("/myWealth/listWithdrawingCommission.do");
        list.add("/myWealth/integralToCurrency.do");
        //
        list.add("/app/myWealth/listIntegralDetail.do");
        list.add("/app/myWealth/listCommissionDetail.do");
        list.add("/app/myWealth/listCurrencyDetail.do");
        list.add("/app/myWealth/statIntegralTrend.do");
        list.add("/app/myWealth/statCommissionTrend.do");
        list.add("/app/myWealth/statCurrencyTrend.do");
        list.add("/app/myWealth/withdrawCommission.do");
        list.add("/app/myWealth/listWithdrawCommission.do");
        list.add("/app/myWealth/listWithdrawedCommission.do");
        list.add("/app/myWealth/listWithdrawingCommission.do");
        list.add("/app/myWealth/integralToCurrency.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/myWealth/listTopWealth.do");
        //
        list.add("/app/myWealth/listIntegralDetail.do");
        list.add("/app/myWealth/listCommissionDetail.do");
        list.add("/app/myWealth/listCurrencyDetail.do");
        //
        list.add("/app/myWealth/statIntegralTrend.do");
        list.add("/app/myWealth/statCommissionTrend.do");
        list.add("/app/myWealth/statCurrencyTrend.do");
        //
        list.add("/app/myWealth/withdrawCommission.do");
        list.add("/app/myWealth/listWithdrawCommission.do");
        list.add("/app/myWealth/integralToCurrency.do");
        return list;
    }
}
