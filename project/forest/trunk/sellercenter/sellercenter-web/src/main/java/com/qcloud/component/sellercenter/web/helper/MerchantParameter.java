package com.qcloud.component.sellercenter.web.helper;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.organization.OrganizationClient;
import com.qcloud.component.organization.QClerk;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PageParameter;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Component
public class MerchantParameter implements PageParameter {

    @Autowired
    private SellercenterClient sellercenterClient;

    @Override
    public String getKey() {

        return SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getValue(HttpServletRequest request) {

        QClerk clerk = PageParameterUtil.getParameterValues(request, OrganizationClient.CLERK_LOGIN_PARAMETER_KEY);
        QMerchant merchant = sellercenterClient.getMerchant(clerk.getDepartmentId());
        AssertUtil.assertNotNull(merchant, "您尚未属于一家商家.");
        return (T) merchant;
    }
}
