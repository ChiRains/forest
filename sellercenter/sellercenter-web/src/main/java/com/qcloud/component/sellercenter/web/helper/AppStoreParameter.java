package com.qcloud.component.sellercenter.web.helper;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.qcloud.component.organization.OrganizationClient;
import com.qcloud.component.organization.QClerk;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PageParameter;
import com.qcloud.pirates.web.page.PageParameterUtil;

public class AppStoreParameter implements PageParameter {

    @Autowired
    private SellercenterClient sellercenterClient;

    @Override
    public String getKey() {

        return SellercenterClient.APP_STORE_LOGIN_PARAMETER_KEY;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getValue(HttpServletRequest request) {

        QClerk clerk = PageParameterUtil.getParameterValues(request, OrganizationClient.CLERK_LOGIN_PARAMETER_KEY);
        QStore store = sellercenterClient.getStore(clerk.getDepartmentId());
        AssertUtil.assertNotNull(store, "门店不存在." + clerk.getDepartmentId());
        return (T) store;
    }
}
