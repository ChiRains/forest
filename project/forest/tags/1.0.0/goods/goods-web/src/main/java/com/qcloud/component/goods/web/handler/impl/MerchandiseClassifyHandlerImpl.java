package com.qcloud.component.goods.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import com.qcloud.component.publicdata.IntKeyValue;
import com.qcloud.component.publicdata.web.handler.ClassifyTypeHandler;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Component("commoditycenter.MerchandiseClassifyHandler")
public class MerchandiseClassifyHandlerImpl implements ClassifyTypeHandler {

//    @Autowired
//    private AdminFilterService         adminFilterService;
//
//    @Autowired
//    private TokenClient                tokenClient;
//
//    @Autowired
//    private OutdatedSellercenterClient outdatedSellercenterClient;

    @Override
    public IntKeyValue[] listType(HttpServletRequest request) {

//        String tokenId = adminFilterService.getTokenId(request);
//        AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
//        String idStr = tokenClient.get(tokenId);
//        AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
//        long memberId = Long.parseLong(idStr);
        
        QMerchant merchant =  PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        
        List<QMerchant> merchantList = new ArrayList<QMerchant>();// outdatedSellercenterClient.listMerchant(memberId);
        merchantList.add(merchant);
        return merchantList.toArray(new IntKeyValue[merchantList.size()]);
    }
}
