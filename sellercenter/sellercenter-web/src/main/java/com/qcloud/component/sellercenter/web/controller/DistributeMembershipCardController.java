package com.qcloud.component.sellercenter.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.admin.AdminClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicservice.SmsClient;
import com.qcloud.component.publicservice.VerificationCodeClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.exception.SellerCenterException;
import com.qcloud.component.sellercenter.model.DistributeMembershipCard;
import com.qcloud.component.sellercenter.model.key.TypeEnum.DistributeMembershipCardStateType;
import com.qcloud.component.sellercenter.service.DistributeMembershipCardService;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = DistributeMembershipCardController.DIR)
public class DistributeMembershipCardController {

    public static final String              DIR                                       = "/distributeMembershipCard";

    // @Autowired
    // private StoreMemberService storeMemberService;
    //
    // @Autowired
    // private OutdatedSellercenterClient outdatedSellercenterClient;
    // @Autowired
    // private UserFilterService userFilterService;
    //
    // @Autowired
    // private TokenClient tokenClient;
    @Autowired
    private PersonalcenterClient            personalcenterClient;

    @Autowired
    private DistributeMembershipCardService distributeMembershipCardService;

    @Autowired
    private SellercenterClient              sellercenterClient;

    // @Autowired
    // private StoreService storeService;
    @Autowired
    private SmsClient                       smsClient;

    public static final String              MERCHANT_DISTRIBUTE_CARD_SMS_TEMPLATE_KEY = "merchant-distribute-ready-card-sms-template";

    @Autowired
    private VerificationCodeClient          verificationCodeClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getToSendCard(HttpServletRequest request, String cardNumber) {

        // long memberId = getMemberId(request);
        AssertUtil.assertTrue(check(request, cardNumber), "获取卡信息失败");
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        //
        frontAjaxView.addObject("cardNumber", cardNumber);
        frontAjaxView.addObject("time", DateUtil.date2String(new Date(), "yyyy-MM-dd"));
        frontAjaxView.addObject("type", "");
        frontAjaxView.addObject("company", "");
        Xml xml = XmlFactory.get("sellercenter-distribute-membership-card-message");
        if (xml != null) {
            List<XmlItem> itemList = xml.getItemList();
            if (itemList != null && !itemList.isEmpty()) {
                XmlItem xmlItem = itemList.get(0);
                String typeStr = xmlItem.getAttrMap().get("type");
                String companyStr = xmlItem.getAttrMap().get("company");
                frontAjaxView.addObject("type", StringUtil.nullToEmpty(typeStr));
                frontAjaxView.addObject("company", StringUtil.nullToEmpty(companyStr));
            }
        }
        //
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView sendSmsMsg4DistributeCard(HttpServletRequest request, String mobile) {

        //
        AssertUtil.assertNotEmpty(mobile, "手机号不能为空.");
        QUser user = personalcenterClient.getUser(mobile);
        AssertUtil.assertTrue(user == null, "手机号已经使用." + mobile);
        String code = verificationCodeClient.create(mobile, 30);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        boolean result = smsClient.send(MERCHANT_DISTRIBUTE_CARD_SMS_TEMPLATE_KEY, mobile, map);
        if (result) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("发送短信成功");
            return view;
        } else {
            throw new SellerCenterException("发送短信失败." + mobile);
        }
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView distributeCard(HttpServletRequest request, String cardNumber, String mobile, String name, Integer sex, String code) {

        AssertUtil.assertNotEmpty(code, "短信验证码不能为空.");
        // 验证码是否有效
        boolean verification = verificationCodeClient.verification(mobile, code);
        AssertUtil.assertTrue(verification, "验证码不正确,请重新获取.");
        AssertUtil.assertNotEmpty(mobile, "会员手机号不能为空.");
        AssertUtil.assertNotEmpty(name, "姓名不能为空.");
        // long memberId = getMemberId(request);
        Long memberId = PageParameterUtil.getParameterValues(request, AdminClient.ADMIN_TOKEN_LOGIN_PARAMETER_KEY);
        AssertUtil.assertTrue(check(request, cardNumber), "发卡失败");
        distributeMembershipCardService.distribute(memberId, cardNumber, mobile, name, sex);
        verificationCodeClient.remove(mobile, code);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("发卡成功");
        return frontAjaxView;
    }

    private boolean check(HttpServletRequest request, String cardNumber) {

        AssertUtil.assertNotEmpty(cardNumber, "待发卡会员卡号不能为空.");
        // Long storeId = getStoreId(memberId);
        // Long merchantId = -1L;
        // if (storeId > 0) {
        // Store store = storeService.get(storeId);
        // merchantId = store.getMerchantId();
        // }
        // AssertUtil.assertTrue(merchantId > 0, "您尚未属于一个门店.");
        QStore store = PageParameterUtil.getParameterValues(request, SellercenterClient.APP_STORE_LOGIN_PARAMETER_KEY);
        boolean exist = personalcenterClient.existAndUselessMembershipCard(cardNumber);
        AssertUtil.assertTrue(exist, "会员卡不存在或者已经使用." + cardNumber);
        DistributeMembershipCard distributeMembershipCard = distributeMembershipCardService.getByCardNumber(cardNumber);
        AssertUtil.assertNotNull(distributeMembershipCard, "会员卡在商家尚未登记." + cardNumber);
        QMerchant merchant = sellercenterClient.getMerchant(distributeMembershipCard.getMerchantId());
        AssertUtil.assertNotNull(merchant, "商家不存在." + distributeMembershipCard.getMerchantId());
        AssertUtil.assertTrue(merchant.getId() == distributeMembershipCard.getMerchantId(), "你不属于会员卡发卡所在的商家." + merchant.getName());
        AssertUtil.assertTrue(distributeMembershipCard.getState() == DistributeMembershipCardStateType.SEND.getKey(), "会员卡(" + cardNumber + ")不是待发卡状态.");
        return true;
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
