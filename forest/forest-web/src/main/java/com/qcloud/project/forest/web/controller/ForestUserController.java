package com.qcloud.project.forest.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.goods.service.MerchandiseBrowsingHistoryService;
import com.qcloud.component.my.service.MyCollectionService;
import com.qcloud.component.my.service.MyCouponService;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.exception.PersonalCenterException;
import com.qcloud.component.personalcenter.model.CardNumberConfig;
import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.key.TypeEnum.MembershipCardWarehouseStateType;
import com.qcloud.component.personalcenter.model.key.TypeEnum.UserStateType;
import com.qcloud.component.personalcenter.service.MembershipCardWarehouseService;
import com.qcloud.component.personalcenter.service.UserService;
import com.qcloud.component.personalcenter.web.controller.UserController;
import com.qcloud.component.publicservice.SmsClient;
import com.qcloud.component.publicservice.VerificationCodeClient;
import com.qcloud.component.token.TokenClient;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.filter.user.UserFilterService;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = ForestUserController.DIR)
public class ForestUserController {

    public static final String                DIR                          = "/forestUser";

    @Autowired
    private MembershipCardWarehouseService    membershipCardWarehouseService;

    @Autowired
    private VerificationCodeClient            verificationCodeClient;

    @Autowired
    private UserFilterService                 userFilterService;

    @Autowired
    private ParameterClient                   parameterClient;

    @Autowired
    private UserController                    userController;

    @Autowired
    private UserService                       userService;

    @Autowired
    private TokenClient                       tokenClient;

    @Autowired
    private SmsClient                         smsClient;

    @Autowired
    private MyCollectionService               myCollectionService;

    @Autowired
    private MerchandiseBrowsingHistoryService merchandiseBrowsingHistoryService;

    @Autowired
    private PersonalcenterClient              personalcenterClient;

    @Autowired
    private MyCouponService                   myCouponService;

    public static final String                USER_SAFETY_SMS_TEMPLATE_KEY = "personalcenter-user-safety-sms-template";

    public static final String                CONTACTS_MOBILE              = "contacts-mobile";

    public static final String                TIP_MESSAGE                  = "tip-message";

    @PiratesApp
    @RequestMapping
    public FrontAjaxView sendSafetyCode(String mobile) {

        User u = userService.getByAccount(mobile);
        if (u == null) {
            throw new PersonalCenterException("会员不存在." + mobile);
        }
        String code = verificationCodeClient.create(mobile, 30);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("minute", "30");
        boolean result = smsClient.send(USER_SAFETY_SMS_TEMPLATE_KEY, mobile, map);
        if (result) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("发送短信成功");
            return view;
        } else {
            throw new PersonalCenterException("发送短信失败." + mobile);
        }
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView safetyLogin(HttpServletRequest request, String username, String pwd, String code) {

        AssertUtil.assertNotEmpty(username, "账号不能为空.");
        AssertUtil.assertNotEmpty(pwd, "密码不能为空.");
        boolean verification = verificationCodeClient.verification(username, code);
        AssertUtil.assertTrue(verification, "验证码不正确,请重新获取.");
        MembershipCardWarehouse membershipCardWarehouse = membershipCardWarehouseService.getByCardNumber(username);
        CardNumberConfig cardNumberConfig = membershipCardWarehouseService.getConfig();
        if (membershipCardWarehouse != null && membershipCardWarehouse.getState() == MembershipCardWarehouseStateType.NEW.getKey() && pwd.equals(cardNumberConfig.getDefaultPwd())) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("登录失败,会员卡尚未激活");
            view.addObject("loginState", 2);
            return view;
        }
        String identificationKey = null;
        if (userService.isUser(username, pwd)) {
            User user = userService.getByAccount(username);
            if (user.getState() == UserStateType.NEW.getKey()) {
                FrontAjaxView view = new FrontAjaxView();
                view.setMessage("登录失败,账号尚未激活");
                view.addObject("loginState", 3);
                return view;
            }
            if (user.getState() == UserStateType.FROZEN.getKey()) {
                FrontAjaxView view = new FrontAjaxView();
                String tip_message = parameterClient.get(TIP_MESSAGE);
                String contacts_mobile = parameterClient.get(CONTACTS_MOBILE);
                view.addObject("loginState", 4);
                view.setMessage("登录失败,账号已被冻结");
                view.addObject("contactMobile", "13800138000");
                if (StringUtils.isNotEmpty(tip_message)) {
                    view.addObject("tipMessage", tip_message);
                }
                if (StringUtils.isNotEmpty(contacts_mobile)) {
                    view.addObject("contactMobile", contacts_mobile);
                }
                return view;
            }
            identificationKey = String.valueOf(user.getId());
        } else {
            throw new PersonalCenterException("账号或密码有误.");
        }
        String tokenId = userFilterService.doLogin(request);
        boolean ok = tokenClient.reg(tokenId, identificationKey);
        if (!ok) {
            throw new PersonalCenterException("系统服务出现异常,token添加失败.");
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("登录成功");
        view.addObject("token", tokenId);
        return view;
    }

    @RequestMapping
    public FrontAjaxView editUser(HttpServletRequest request, String headImage, String nickname, String email) {

        PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        userController.editUserHeadImage(request, headImage);
        userController.editNickname(request, nickname);
        userController.editEmail(request, email);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改个人资料成功");
        return view;
    }

    /**
     * 修改绑定手机号 获取验证码（原手机号）
     * @param mobile 
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView getCodeForChangeBingdingMobileByOldNumber(HttpServletRequest request, String mobile) {

        User u = userService.getByAccount(mobile);
        if (u == null) {
            throw new PersonalCenterException("会员不存在." + mobile);
        }
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AssertUtil.assertTrue(user.getMobile().equals(mobile), "您所填的电话号码不属于当前 用户");
        String code = verificationCodeClient.create(mobile, 30);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("minute", "30");
        boolean result = smsClient.send(USER_SAFETY_SMS_TEMPLATE_KEY, mobile, map);
        if (result) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("发送短信成功");
            return view;
        } else {
            throw new PersonalCenterException("发送短信失败." + mobile);
        }
    }

    /**
     * 判断验证码是否正确（原手机号）
     * @param request
     * @param mobile
     * @param code
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView verifyCodeForChangeBingdingMobileByOldNumber(HttpServletRequest request, String mobile, String code) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AssertUtil.assertTrue(user.getMobile().equals(mobile), "您所填的电话号码不属于当前 用户");
        boolean verification = verificationCodeClient.verification(mobile, code);
        // 上线时此处代码应该删掉
        // ---------------------------------
        if (code.equals("666666")) {
            verification = true;
        }
        // ---------------------------------
        AssertUtil.assertTrue(verification, "验证码不正确,请重新获取.");
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("验证成功");
        frontAjaxView.addObject("isVerify", 1);
        return frontAjaxView;
    }

    /**
     * 修改绑定手机号 获取验证码（新手机号）
     * @param mobile 
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView getCodeForChangeBingdingMobileByNewNumber(String mobile) {

        String code = verificationCodeClient.create(mobile, 30);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("minute", "30");
        boolean result = smsClient.send(USER_SAFETY_SMS_TEMPLATE_KEY, mobile, map);
        if (result) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("发送短信成功");
            return view;
        } else {
            throw new PersonalCenterException("发送短信失败." + mobile);
        }
    }

    /**
     * 提交修改的新手机号和验证码（新手机号）
     * @param request
     * @param mobile
     * @param code
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView editBingdingMobile(HttpServletRequest request, String mobile, String code, String oldMobile, String oldCode) {

        // 原手机号码验证
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AssertUtil.assertTrue(user.getMobile().equals(oldMobile), "您输入的原手机号不属于当前用户");
        boolean oldVerification = verificationCodeClient.verification(oldMobile, oldCode);
        // 上线时此处代码应该删掉
        // ---------------------------------
        if (oldCode.equals("666666")) {
            oldVerification = true;
        }
        // ---------------------------------
        AssertUtil.assertTrue(oldVerification, "原手机号验证码不正确,请重新获取.");
        // 新手机号码验证
        boolean verification = verificationCodeClient.verification(mobile, code);
        // 上线时此处代码应该删掉
        // ---------------------------------
        if (code.equals("666666")) {
            verification = true;
        }
        // ---------------------------------
        AssertUtil.assertTrue(verification, "验证码不正确,请重新获取.");
        return userController.editMobile(request, mobile);
    }

    /**
     * 我的收藏数、历史足迹数、优惠券数、积分数等
     * @param request
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView extraInfo(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_IS_LOGIN_PARAMETER_KEY);
        // 收藏数量
        int collectionNum = myCollectionService.countByUserId(user.getId());
        // 历史足迹数量
        int historyNums = merchandiseBrowsingHistoryService.countByUser(user.getId());
        // 优惠券数量
        int couponNum = myCouponService.countByUser(user.getId(), 1);
        // 可用积分
        long integralNum = personalcenterClient.getMyWealth(user.getId()).getIntegral();
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("collectNums", collectionNum);// 收藏数量，包括商品和门店
        view.addObject("historyNums", historyNums);// 历史足迹数量
        view.addObject("couponNums", couponNum);// 优惠券数量
        view.addObject("integralNums", integralNum);// 可用积分
        return view;
    }
}
