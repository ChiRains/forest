package com.qcloud.component.sellercenter.web.helper;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.sellercenter.OutdatedSellercenterClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.exception.SellerCenterException;
import com.qcloud.component.token.TokenClient;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.filter.admin.AdminFilterService;
import com.qcloud.pirates.web.page.PageParameter;

@Component
public class MerchantParameter implements PageParameter {

    @Autowired
    private AdminFilterService         adminFilterService;

    @Autowired
    private TokenClient                tokenClient;

    @Autowired
    private OutdatedSellercenterClient outdatedSellercenterClient;

    @Override
    public String getKey() {

        return SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getValue(HttpServletRequest request) {

        String tokenId = adminFilterService.getTokenId(request);
        AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
        String idStr = tokenClient.get(tokenId);
        AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
        long memberId = Long.parseLong(idStr);
        List<QMerchant> merchantList = outdatedSellercenterClient.listMerchant(memberId);
        QMerchant merchant = null;
        if (!CollectionUtils.isEmpty(merchantList) && merchantList.size() == 1) {
            merchant = merchantList.get(0);
        }
        if (!CollectionUtils.isEmpty(merchantList) && merchantList.size() > 1) {
            throw new SellerCenterException("商家职员只能在职一个商家");
        }
        AssertUtil.assertNotNull(merchant, "您尚未属于一家商家.");
        return (T) merchant;
    }
}
