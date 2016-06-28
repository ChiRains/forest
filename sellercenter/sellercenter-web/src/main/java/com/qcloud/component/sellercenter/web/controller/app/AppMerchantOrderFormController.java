package com.qcloud.component.sellercenter.web.controller.app;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.MerchantOrderForm;
import com.qcloud.component.sellercenter.model.key.TypeEnum.MerchantOrderStateType;
import com.qcloud.component.sellercenter.service.MerchantOrderFormService;
import com.qcloud.component.sellercenter.web.handler.MerchantOrderFormHandler;
import com.qcloud.component.sellercenter.web.vo.MerchantOrderFormVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = AppMerchantOrderFormController.DIR)
public class AppMerchantOrderFormController {

    public static final String       DIR = "/app/merchantOrderForm";

    @Autowired
    private MerchantOrderFormService merchantOrderFormService;

    @Autowired
    private MerchantOrderFormHandler merchantOrderFormHandler;

    // @Autowired
    // private StoreMemberService storeMemberService;
    // @Autowired
    // private OutdatedSellercenterClient outdatedSellercenterClient;
    // @Autowired
    // private UserFilterService userFilterService;
    //
    // @Autowired
    // private TokenClient tokenClient;
    // @Autowired
    // private StoreService storeService;
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, Integer stateType, Integer pageSize, Integer pageNum) {

        final int PAGE_SIZE = pageSize == null || pageSize <= 0 ? 10 : pageSize;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        // long memberId = getMemberId(request);
        // Long merchantId = getMerchantId(memberId);
        // Long storeId = merchantId > 0 ? -1 : getStoreId(memberId);
        // Long storeId = getStoreId(memberId);
        // AssertUtil.assertTrue(storeId > 0, "您尚未属于一个门店.");
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.APP_STORE_LOGIN_PARAMETER_KEY);
        // Long merchantId = -1L;
        // if (storeId > 0) {
        // Store store = storeService.get(storeId);
        // merchantId = store.getMerchantId();
        // }
        int state = 0;
        stateType = stateType == null ? 0 : stateType;
        switch (stateType) {
        case 1:
            state = MerchantOrderStateType.TOPAY.getKey();
            break;
        case 2:
            state = MerchantOrderStateType.TO_PACKING.getKey();
            break;
        case 3:
            state = MerchantOrderStateType.SHIP.getKey();
            break;
        default:
            break;
        }
        List<MerchantOrderForm> list = merchantOrderFormService.list4Store(store.getMerchantId(), store.getId(), state, start, PAGE_SIZE);
        List<MerchantOrderFormVO> voList = merchantOrderFormHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询商家订单成功.");
        view.addObject("list", voList);
        return view;
    }
    // private long getMemberId(HttpServletRequest request) {
    //
    // String tokenId = userFilterService.getTokenId(request);
    // AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
    // String idStr = tokenClient.get(tokenId);
    // AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
    // long memberId = Long.parseLong(idStr);
    // return memberId;
    // }
    //
    // private long getStoreId(Long memberId) {
    //
    // long storeId = -1;
    // HashMap where = new HashMap();
    // where.put("memberId", memberId);
    // StoreMember storeMember = storeMemberService.get(where);
    // if (storeMember != null) {
    // storeId = storeMember.getStoreId();
    // }
    // return storeId;
    // }
    //
    // public Long getMerchantId(Long memberId) {
    //
    // List<QMerchant> merchantList = outdatedSellercenterClient.listMerchant(memberId);
    // long merchantId = -1;
    // if (!CollectionUtils.isEmpty(merchantList) && merchantList.size() == 1) {
    // merchantId = merchantList.get(0).getId();
    // }
    // return merchantId;
    // }
}
