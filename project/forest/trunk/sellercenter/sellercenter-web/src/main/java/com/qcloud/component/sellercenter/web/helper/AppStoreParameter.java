package com.qcloud.component.sellercenter.web.helper;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.StoreMember;
import com.qcloud.component.sellercenter.service.StoreMemberService;
import com.qcloud.component.token.TokenClient;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.filter.user.UserFilterService;
import com.qcloud.pirates.web.page.PageParameter;

public class AppStoreParameter implements PageParameter {

    @Autowired
    private UserFilterService  userFilterService;

    @Autowired
    private TokenClient        tokenClient;

    @Autowired
    private StoreMemberService storeMemberService;

    @Autowired
    private SellercenterClient sellercenterClient;

    @Override
    public String getKey() {

        return SellercenterClient.APP_STORE_LOGIN_PARAMETER_KEY;
    }

    @Override
    public <T> T getValue(HttpServletRequest request) {

        String tokenId = userFilterService.getTokenId(request);
        AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
        String idStr = tokenClient.get(tokenId);
        AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
        long memberId = Long.parseLong(idStr);
        long storeId = 0;
        HashMap where = new HashMap();
        where.put("memberId", memberId);
        StoreMember storeMember = storeMemberService.get(where);
        if (storeMember != null) {
            storeId = storeMember.getStoreId();
        } else {
            AssertUtil.assertTrue(false, "当前登录用户不属于门店!");
        }
        QStore store = sellercenterClient.getStore(storeId);
        AssertUtil.assertNotNull(store, "门店不存在." + storeId);
        return (T) store;
    }
}
