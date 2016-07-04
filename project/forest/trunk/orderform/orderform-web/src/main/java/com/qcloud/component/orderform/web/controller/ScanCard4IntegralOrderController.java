package com.qcloud.component.orderform.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.admin.AdminClient;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.QMyCoupon;
import com.qcloud.component.orderform.engine.ScanCard4IntegralOrderService;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QMyWealth;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.piratesship.web.form.ListForm;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = ScanCard4IntegralOrderController.DIR)
public class ScanCard4IntegralOrderController {

    public static final String            DIR = "/scanCard";

    @Autowired
    private PersonalcenterClient          personalcenterClient;

    // @Autowired
    // private UserFilterService userFilterService;
    //
    // @Autowired
    // private TokenClient tokenClient;
    @Autowired
    private SellercenterClient            sellercenterClient;

    // @Autowired
    // private StoreService storeService;
    //
    // @Autowired
    // private StoreMemberService storeMemberService;
    @Autowired
    private ScanCard4IntegralOrderService scanCard4IntegralOrderService;

    @Autowired
    private MyClient                      myClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getMembershipCard(HttpServletRequest request, String cardNumber) {

        AssertUtil.assertNotEmpty(cardNumber, "会员卡号不能为空.");
        // long memberId = getMemberId(request);
        // Long storeId = getStoreId(memberId);
        // Long merchantId = -1L;
        // if (storeId > 0) {
        // Store store = storeService.get(storeId);
        // merchantId = store.getMerchantId();
        // }
        // AssertUtil.assertTrue(merchantId > 0, "您尚未属于一个门店.");
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.APP_STORE_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(store, "您尚未属于一个门店.");
        QUser user = personalcenterClient.getUser(cardNumber);
        AssertUtil.assertNotNull(user, "会员卡尚未发给用户." + cardNumber);
        QMyWealth myWealth = personalcenterClient.getMyWealth(user.getId());
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        //
        frontAjaxView.addObject("name", user.getName());
        frontAjaxView.addObject("integral", myWealth.getIntegral());
        //
        frontAjaxView.addObject("merchantName", cardNumber);
        frontAjaxView.addObject("legalPerson", "名字缺");
        // 扫卡积分比率
        frontAjaxView.addObject("integralRatio", 100);
        frontAjaxView.addObject("merchant", cardNumber);
        // List<QMyCoupon> list = personalcenterClient.my().listMyCouponCanUse(user.getId(), -1L, Double.valueOf(Integer.MAX_VALUE));
        // List<OrderCouponVO> voList = new ArrayList<OrderCouponVO>();
        // for (QMyCoupon qMyCoupon : list) {
        // OrderCouponVO vo = new OrderCouponVO();
        // vo.setCode(qMyCoupon.getCode());
        // vo.setId(qMyCoupon.getId());
        // vo.setLimitPrice(qMyCoupon.getLimitPrice());
        // vo.setMerchantId(qMyCoupon.getMerchantId());
        // vo.setName(qMyCoupon.getName());
        // vo.setPrice(qMyCoupon.getPrice());
        // vo.setValidDate(qMyCoupon.getValidDate());
        // voList.add(vo);
        // }
        // frontAjaxView.addObject("couponList", voList);
        return frontAjaxView;
    }

    // 一分钟一个账号允许下一个订单
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView order4Integral(HttpServletRequest request, String cardNumber, Double sum, ListForm couponList) {

        AssertUtil.assertNotNull(sum, "积分金额不能为空");
        AssertUtil.greatZero(sum.longValue(), "积分金额不能小于0:" + sum);
        AssertUtil.assertNotEmpty(cardNumber, "会员卡号不能为空.");
        // long memberId = getMemberId(request);
        // Long storeId = getStoreId(memberId);
        // Long merchantId = -1L;
        // if (storeId > 0) {
        // Store store = storeService.get(storeId);
        // merchantId = store.getMerchantId();
        // }
        Long memberId = PageParameterUtil.getParameterValues(request, AdminClient.ADMIN_TOKEN_LOGIN_PARAMETER_KEY);
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.APP_STORE_LOGIN_PARAMETER_KEY);
        // AssertUtil.assertTrue(merchantId > 0, "您尚未属于一个商家.");
        // QStore store = sellercenterClient.getStore(storeId);
        // AssertUtil.assertNotNull(store, "门店不存在." + storeId);
        QMerchant merchant = sellercenterClient.getMerchant(store.getMerchant().getId());
        AssertUtil.assertNotNull(merchant, "商家不存在." + store.getMerchant().getId());
        //
        QUser user = personalcenterClient.getUser(cardNumber);
        AssertUtil.assertNotNull(user, "会员卡尚未发给用户." + cardNumber);
        List<QMyCoupon> myCouponList = new ArrayList<QMyCoupon>();
        if (couponList != null && couponList.getStringList() != null && !couponList.getStringList().isEmpty()) {
            List<String> list = couponList.getStringList();
            Set<String> conponSet = new HashSet<String>();
            for (String code : list) {
                QMyCoupon myCoupon = myClient.getMyCoupon(code);
                AssertUtil.assertNotNull(myCoupon, "优惠劵不存在." + myCoupon);
                AssertUtil.assertTrue(myCoupon.getUserId() == user.getId(), "优惠劵不是会员的:" + user.getNickname());
                AssertUtil.assertTrue(conponSet.add(code), "在订单中优惠劵不能重复使用." + myCoupon.getCode());
                myCouponList.add(myCoupon);
            }
        }
        scanCard4IntegralOrderService.order(user, merchant, store, memberId, sum, myCouponList);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        return frontAjaxView;
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
