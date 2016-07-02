package com.qcloud.component.brokerage.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class UserDistributionGradeUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/userDistributionGrade/list.do");
        list.add("/admin/userDistributionGrade/toAdd.do");
        list.add("/admin/userDistributionGrade/toEdit.do");
        list.add("/admin/userDistributionGrade/add.do");
        list.add("/admin/userDistributionGrade/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/userDistributionGrade/list.do");
        list.add("/admin/userDistributionGrade/toAdd.do");
        list.add("/admin/userDistributionGrade/toEdit.do");
        list.add("/admin/userDistributionGrade/add.do");
        list.add("/admin/userDistributionGrade/edit.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> list = stringToList("/admin/distributionGrade/list.do");
        //
        map.put("/admin/userDistributionGrade/list.do", list);
        map.put("/admin/userDistributionGrade/toAdd.do", list);
        map.put("/admin/userDistributionGrade/toEdit.do", list);
        map.put("/admin/userDistributionGrade/add.do", list);
        map.put("/admin/userDistributionGrade/edit.do", list);
        return map;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/userDistributionGrade/getMyDistributionGrade.do");
        list.add("/userDistributionGrade/toUpgrade.do");
        list.add("/userDistributionGrade/prepareAlipayPay4Upgrade.do");
        //
        list.add("/app/userDistributionGrade/getMyDistributionGrade.do");
        list.add("/app/userDistributionGrade/toUpgrade.do");
        list.add("/app/userDistributionGrade/prepareAlipayPay4Upgrade.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/userDistributionGrade/getMyDistributionGrade.do");
        list.add("/app/userDistributionGrade/toUpgrade.do");
        list.add("/app/userDistributionGrade/prepareAlipayPay4Upgrade.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/userDistributionGrade/alipayPaied.do");
        list.add("/userDistributionGrade/getHtmlRule.do");
        return list;
    }
}
