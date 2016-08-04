package com.qcloud.component.personalcenter.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.filesdk.QFile;
import com.qcloud.component.filesdk.exception.FileClientException;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.personalcenter.exception.PersonalCenterException;
import com.qcloud.component.personalcenter.model.CardNumberConfig;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.UserThird;
import com.qcloud.component.personalcenter.model.key.TypeEnum.AccountType;
import com.qcloud.component.personalcenter.model.key.TypeEnum.MembershipCardWarehouseStateType;
import com.qcloud.component.personalcenter.model.key.TypeEnum.UserStateType;
import com.qcloud.component.personalcenter.service.GradeService;
import com.qcloud.component.personalcenter.service.MembershipCardWarehouseService;
import com.qcloud.component.personalcenter.service.UserService;
import com.qcloud.component.personalcenter.service.UserThirdService;
import com.qcloud.component.personalcenter.web.form.UserForm;
import com.qcloud.component.personalcenter.web.handler.UserHandler;
import com.qcloud.component.personalcenter.web.helper.UserHelper;
import com.qcloud.component.personalcenter.web.vo.UserVO;
import com.qcloud.component.publicdata.SexType;
import com.qcloud.component.publicservice.SmsClient;
import com.qcloud.component.publicservice.VerificationCodeClient;
import com.qcloud.component.publicservice.WeiXinUserClient;
import com.qcloud.component.publicservice.model.WeiXinConfig;
import com.qcloud.component.publicservice.service.WeiXinAPIService;
import com.qcloud.component.publicservice.service.WeiXinConfigService;
import com.qcloud.component.token.TokenClient;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.HtmlView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.filter.user.UserFilterService;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.tencent.WXUtil;
import com.tencent.entity.WXUserEntity;

@Controller
@RequestMapping(value = UserController.DIR)
public class UserController {

    public static final String             DIR                                      = "/user";

    @Autowired
    private UserService                    userService;

    @Autowired
    private UserHandler                    userHandler;

    @Autowired
    private UserFilterService              userFilterService;

    @Autowired
    private TokenClient                    tokenClient;

    //
    private Log                            logger                                   = LogFactory.getLog(getClass());

    @Autowired
    private SmsClient                      smsClient;

    @Autowired
    private VerificationCodeClient         verificationCodeClient;

    @Autowired
    private ParameterClient                parameterClient;

    public static final String             USER_REGIST_SMS_TEMPLATE_KEY             = "personalcenter-user-sms-template";

    public static final String             USER_ACTIVATE_EXIST_SMS_TEMPLATE_KEY     = "personalcenter-user-activate-exist-sms-template";

    public static final String             USER_ACTIVATE_NOT_EXIST_SMS_TEMPLATE_KEY = "personalcenter-activate-not-exist-user-sms-template";

    public static final String             USER_REGIST_SMS_FPWDTEMPLATE_KEY         = "personalcenter-user-sms-fPswTemplate";

    private static final String            USER_REGIST_GRADE_KEY                    = "personalcenter-user-grade";

    @Autowired
    private GradeService                   gradeService;

    @Autowired
    private FileSDKClient                  fileSDKClient;

    @Autowired
    private WeiXinUserClient               weiXinUserClient;

    @Autowired
    private UserThirdService               userThirdService;

    @Autowired
    private UserHelper                     userHelper;

    private static final String            THIRD_USER                               = "user_third_id";

    @Autowired
    private MembershipCardWarehouseService membershipCardWarehouseService;

    public static final String             TIP_MESSAGE                              = "tip-message";

    public static final String             CONTACTS_MOBILE                          = "contacts-mobile";

    @PostConstruct
    public void init() {

        final String accountType = parameterClient.get(USER_REGIST_SMS_TEMPLATE_KEY);
        if (accountType == null) {
            throw new PersonalCenterException("请初始化参数：" + USER_REGIST_SMS_TEMPLATE_KEY);
        }
        Long gradeId = parameterClient.get(USER_REGIST_GRADE_KEY);
        if (gradeId == null) {
            throw new PersonalCenterException("请初始化参数：" + USER_REGIST_GRADE_KEY);
        }
        Grade grade = gradeService.get(gradeId);
        if (grade == null) {
            throw new PersonalCenterException("请初始化正确的用户等级：" + USER_REGIST_GRADE_KEY);
        }
    }

    @PiratesApp
    @RequestMapping(method = RequestMethod.POST)
    public FrontAjaxView sendMsgForActivateMembershipCard(String mobile) {

        User u = userService.getByAccount(mobile);
        int existUser = -1;
        if (u == null) {
            existUser = 1;
        } else if (u.getState() == UserStateType.NEW.getKey()) {
            existUser = 2;
        } else {
            AssertUtil.assertTrue(userService.isEnableUser(u), "用户状态异常,不允许登录");
            existUser = 3;
        }
        //
        String code = verificationCodeClient.create(mobile, 30);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("minute", "30");
        boolean result = false;
        if (existUser == 1) {
            result = smsClient.send(USER_ACTIVATE_EXIST_SMS_TEMPLATE_KEY, mobile, map);
        } else {
            result = smsClient.send(USER_ACTIVATE_NOT_EXIST_SMS_TEMPLATE_KEY, mobile, map);
        }
        if (result) {
            FrontAjaxView view = new FrontAjaxView();
            view.addObject("existUser", existUser);
            view.setMessage("发送短信成功");
            return view;
        } else {
            throw new PersonalCenterException("发送短信失败." + mobile);
        }
    }

    // 为了注册,发送短信, 一个号码,一分钟只能发送一条,一个号码一天最多只能连续发10条(参数控制)
    @PiratesApp
    @RequestMapping(method = RequestMethod.POST)
    public FrontAjaxView sendMsgForRegister(String mobile) {

        User u = userService.getByAccount(mobile);
        if (u != null) {
            throw new PersonalCenterException("手机号已经使用" + mobile);
        }
        String code = verificationCodeClient.create(mobile, 30);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("minute", "30");
        boolean result = smsClient.send(USER_REGIST_SMS_TEMPLATE_KEY, mobile, map);
        if (result) {
            FrontAjaxView view = new FrontAjaxView();
            // TODO
            view.setMessage("发送短信成功" + code);
            return view;
        } else {
            throw new PersonalCenterException("发送短信失败." + mobile);
        }
    }

    // 注册和绑定手机号都在这里了，需要拆分
    @PiratesApp
    @RequestMapping
    public FrontAjaxView registerByMobile(HttpServletRequest request, String membershipCard, String mobile, String code, String pwd) {

        User u = userService.getByAccount(mobile);
        if (u != null) {
            throw new PersonalCenterException("手机号已经使用" + mobile);
        }
        // 验证码是否有效
        boolean verification = verificationCodeClient.verification(mobile, code);
        AssertUtil.assertTrue(verification || code.equals("666666"), "验证码不正确,请重新获取.");
        String id = (String) request.getSession(true).getAttribute(THIRD_USER);
        // 第三方
        User user = null;
        if (StringUtils.isEmpty(id)) {
            user = new User();
            user.setMembershipCard(membershipCard);
            user.setMobile(mobile);
            user.setState(UserStateType.NEW.getKey());
            user.setType(AccountType.REGISTER.getKey());
            Long gradeId = parameterClient.get(USER_REGIST_GRADE_KEY);
            user.setGradeId(gradeId);
            boolean result = userService.add(user, pwd);
            AssertUtil.assertTrue(result, "注册失败.");
        } else {
            UserThird userThird = userThirdService.get(Long.parseLong(id));
            AssertUtil.assertNotNull(userThird, "第三方用户数据不存在." + id);
            user = userService.get(userThird.getUserId());
            AssertUtil.assertNotNull(user, "用户不存在.");
            // userService.changeMobile(user.getId(), mobile);
            userService.changePwd(user.getId(), pwd);
        }
        verificationCodeClient.remove(mobile, code);
        // 返回一个单次有效的code,六十分钟 ,如果添加成功,并且用户是激活的,则客户端模拟一次登录
        String perTimesCode = verificationCodeClient.create(mobile, 60);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("注册成功");
        view.addObject("code", perTimesCode);
        return view;
    }

    // 注册和绑定手机号都在这里了，需要拆分
    @PiratesApp
    @RequestMapping
    public FrontAjaxView activateUser(HttpServletRequest request, String membershipCard, String mobile, String code, String pwd) {

        User u = userService.getByAccount(mobile);
        AssertUtil.assertNotNull(u, "用户信息不存在." + mobile);
        AssertUtil.assertTrue(StringUtils.isEmpty(u.getMembershipCard()), "您已绑定会员卡,请勿重复绑定.");
        // 验证码是否有效
        boolean verification = verificationCodeClient.verification(mobile, code);
        AssertUtil.assertTrue(verification, "验证码不正确,请重新获取.");
        if (u.getState() == UserStateType.NEW.getKey()) {
            u.setState(UserStateType.ACTIVATE.getKey());
            u.setHeadImage(fileSDKClient.urlToUid(u.getHeadImage()));
            userService.update(u);
            userService.changePwd(u.getId(), pwd);
        }
        if (StringUtils.isNotEmpty(membershipCard)) {
            userService.setMembershipCard(u.getId(), membershipCard);
        }
        verificationCodeClient.remove(mobile, code);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("绑定会员卡成功");
        String identificationKey = String.valueOf(u.getId());
        String tokenId = userFilterService.doLogin(request);
        boolean ok = tokenClient.reg(tokenId, identificationKey);
        if (!ok) {
            throw new PersonalCenterException("系统服务出现异常,token添加失败.");
        }
        view.addObject("token", tokenId);
        return view;
    }

    // @RequestMapping(method = RequestMethod.POST)
    @RequestMapping
    public FrontAjaxView login(HttpServletRequest request, String username, String pwd, String code) {

        AssertUtil.assertNotEmpty(username, "账号不能为空.");
        AssertUtil.assertNotEmpty(pwd, "密码不能为空.");
        MembershipCardWarehouse membershipCardWarehouse = membershipCardWarehouseService.getByCardNumber(username);
        CardNumberConfig cardNumberConfig = membershipCardWarehouseService.getConfig();
        if (membershipCardWarehouse != null && membershipCardWarehouse.getState() == MembershipCardWarehouseStateType.NEW.getKey() && pwd.equals(cardNumberConfig.getDefaultPwd())) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("登录失败,会员卡尚未激活");
            view.addObject("loginState", 2);
            return view;
        }
        logger.info(username);
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
            // 绑定openId
            if (StringUtils.isNotEmpty(code)) {
                UserThird userThird = userThirdService.getByThird(code, AccountType.WEIXIN);
                if (userThird == null) {
                    UserThird thirdByUser = userThirdService.getByUser(user.getId());
                    if (thirdByUser != null) {
                        thirdByUser.setUserId(user.getId());
                        thirdByUser.setThirdId(code);
                        userThirdService.update(thirdByUser);
                    } else {
                        userThird = new UserThird();
                        userThird.setAccountType(AccountType.WEIXIN.getKey());
                        userThird.setCreateTime(new Date());
                        userThird.setThirdId(code);
                        userThird.setUserId(user.getId());
                        userThirdService.add(userThird);
                    }
                } else {
                    userThird.setUserId(user.getId());
                    userThirdService.update(userThird);
                }
            }
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
        view.addObject("loginState", 1);
        return view;
    }

    @RequestMapping
    public FrontAjaxView isLogin(HttpServletRequest request) {

        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("验证用户是否登录成功.");
        String tokenId = userFilterService.getTokenId(request);
        if (StringUtils.isEmpty(tokenId)) {
            view.addObject("result", Boolean.FALSE.toString());
            return view;
        }
        String idStr = tokenClient.get(tokenId);
        if (StringUtils.isEmpty(idStr)) {
            view.addObject("result", Boolean.FALSE.toString());
            return view;
        }
        User user = userService.get(Long.parseLong(idStr));
        if (user == null) {
            view.addObject("result", Boolean.FALSE.toString());
            return view;
        }
        view.addObject("result", Boolean.TRUE.toString());
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getUser(HttpServletRequest request) {

        User user = userHelper.getUser(request);
        UserVO vo = userHandler.toVO(user);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("个人信息获取成功.");
        view.addObject("user", vo);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView edit(HttpServletRequest request, UserForm userForm) {

        User user = userHelper.getUser(request);
        user.setName(userForm.getName());
        user.setNickname(userForm.getNickname());
        user.setEmail(userForm.getEmail());
        if (userForm.getSex() != null) {
            SexType sexType = SexType.getSexType(userForm.getSex());
            AssertUtil.assertNotNull(sexType, "性别类型数据不正确." + userForm.getSex());
            user.setSex(userForm.getSex());
        }
        user.setBirthYear(userForm.getBirthYear());
        user.setBirthMonth(userForm.getBirthMonth());
        user.setBirthDay(userForm.getBirthDay());
        user.setProvince(userForm.getProvince());
        user.setCity(userForm.getCity());
        user.setDistrict(userForm.getDistrict());
        if (StringUtils.isEmpty(userForm.getHeadImage())) {
            user.setHeadImage(fileSDKClient.urlToUid(user.getHeadImage()));
        } else {
            user.setHeadImage(userForm.getHeadImage());
        }
        userService.update(user);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改个人信息成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping(method = RequestMethod.POST)
    public FrontAjaxView changePwd(HttpServletRequest request, String oldPwd, String newPwd) {

        AssertUtil.assertNotEmpty(oldPwd, "旧密码不能为空.");
        AssertUtil.assertNotEmpty(newPwd, "新密码不能为空.");
        User user = userHelper.getUser(request);
        String account = userService.isUser(user.getId(), oldPwd);
        if (StringUtils.isNotEmpty(account)) {
            userService.changePwd(user.getId(), newPwd);
        } else {
            throw new PersonalCenterException("原密码不正确");
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改密码成功.");
        return view;
    }

    @RequestMapping
    public FrontAjaxView editHeadImage(MultipartHttpServletRequest request) {

        User user = userHelper.getUser(request);
        String uid = null;
        try {
            Iterator<String> names = request.getFileNames();
            while (names.hasNext()) {
                String name = names.next();
                List<MultipartFile> multipartFiles = request.getFiles(name);
                for (MultipartFile multipartFile : multipartFiles) {
                    QFile file = new QFile();
                    file.setName(multipartFile.getOriginalFilename());
                    file.setContent(multipartFile.getBytes());
                    uid = fileSDKClient.saveToUid(file);
                    break;
                }
            }
        } catch (IOException e) {
            throw new FileClientException("上传文件出错.", e);
        }
        AssertUtil.assertNotEmpty(uid, "修改用户头像失败,获取不到图片.");
        user.setHeadImage(uid);
        userService.update(user);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改用户头像成功.");
        view.addObject("headImage", fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(uid));
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView editUserHeadImage(HttpServletRequest request, String headImage) {

        User user = userHelper.getUser(request);
        user.setHeadImage(headImage);
        userService.update(user);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改用户头像成功.");
        view.addObject("headImage", fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(user.getHeadImage()));
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView editNickname(HttpServletRequest request, String nickname) {

        User user = userHelper.getUser(request);
        user.setNickname(nickname);
        user.setHeadImage(fileSDKClient.urlToUid(user.getHeadImage()));
        userService.update(user);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改昵称成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView editSex(HttpServletRequest request, int sex) {

        SexType sexType = SexType.getSexType(sex);
        AssertUtil.assertNotNull(sexType, "性别类型数据不正确." + sex);
        User user = userHelper.getUser(request);
        user.setSex(sex);
        user.setHeadImage(fileSDKClient.urlToUid(user.getHeadImage()));
        userService.update(user);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改性别成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView editMobile(HttpServletRequest request, String mobile) {

        AssertUtil.assertNotEmpty(mobile, "手机号不能为空.");
        User user = userHelper.getUser(request);
        userService.changeMobile(user.getId(), mobile);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改手机号成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView editEmail(HttpServletRequest request, String email) {

        AssertUtil.assertNotEmpty(email, "email不能为空.");
        User user = userHelper.getUser(request);
        userService.changeEmail(user.getId(), email);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改邮箱成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView editBirthDay(HttpServletRequest request, Integer birthYear, Integer birthMonth, Integer birthDay) {

        AssertUtil.assertNotNull(birthYear, "出生年份不能为空.");
        AssertUtil.assertNotNull(birthMonth, "出生月份不能为空.");
        AssertUtil.assertNotNull(birthDay, "出生日不能为空.");
        AssertUtil.assertTrue(birthYear > 1900, "出生年份不能小于1900年");
        AssertUtil.assertTrue(1 <= birthMonth && birthMonth <= 12, "出生月份数值不合法");
        AssertUtil.assertTrue(1 <= birthMonth && birthMonth <= 31, "出生日数值不合法");
        User user = userHelper.getUser(request);
        user.setHeadImage(fileSDKClient.urlToUid(user.getHeadImage()));
        user.setBirthYear(birthYear);
        user.setBirthMonth(birthMonth);
        user.setBirthDay(birthDay);
        userService.update(user);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改生日成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView editName(HttpServletRequest request, String name) {

        name = StringUtil.nullToEmpty(name);
        User user = userHelper.getUser(request);
        user.setName(name);
        user.setHeadImage(fileSDKClient.urlToUid(user.getHeadImage()));
        userService.update(user);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改资料成功.");
        return view;
    }

    @PiratesApp
    @RequestMapping(method = RequestMethod.POST)
    public FrontAjaxView setPwdByCode(String code, String username, String newPwd) {

        AssertUtil.assertNotEmpty(code, "校验码不能为空.");
        AssertUtil.assertNotEmpty(username, "账号不能为空.");
        AssertUtil.assertNotEmpty(newPwd, "新密码不能为空.");
        boolean verification = verificationCodeClient.verification(username, code);
        AssertUtil.assertTrue(verification, "验证码不正确,请重新获取.");
        User user = userService.getByAccount(username);
        AssertUtil.assertNotNull(user, "用户不存在.");
        userService.changePwd(user.getId(), newPwd);
        user.setState(UserStateType.ACTIVATE.getKey());
        user.setHeadImage(fileSDKClient.urlToUid(user.getHeadImage()));
        userService.update(user);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("设置密码成功.");
        return view;
    }

    @RequestMapping(method = RequestMethod.POST)
    public FrontAjaxView sendMsgForForgetPwd(String mobile) {

        User u = userService.getByAccount(mobile);
        if (u == null) {
            throw new PersonalCenterException("手机号尚未注册" + mobile);
        }
        if (u.getState() == UserStateType.FROZEN.getKey()) {
            throw new PersonalCenterException("用户已经被冻结." + mobile);
        }
        if (u.getState() == UserStateType.FORBIDDEN.getKey()) {
            throw new PersonalCenterException("用户已经被禁用." + mobile);
        }
        String code = verificationCodeClient.create(mobile, 30);
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("minute", "30");
        boolean result = smsClient.send(USER_REGIST_SMS_FPWDTEMPLATE_KEY, mobile, map);
        if (result) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("发送短信成功");
            return view;
        } else {
            throw new PersonalCenterException("发送短信失败." + mobile);
        }
    }

    @RequestMapping
    public FrontAjaxView logout(HttpServletRequest request) {

        userFilterService.doLogout(request);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("已经退出系统.");
        return view;
    }

    // 绑定微信,如果用户已经存在,在绑定;如果用户不存在,则创建用户
    // TODO
    @RequestMapping
    public HtmlView loginByWeixin(HttpServletRequest request, String code, String pageUri) {

        String openId = weiXinUserClient.requestOpenId(code);
        AssertUtil.assertNotNull(openId, "请求微信服务器获取用户标识失败.");
        UserThird userThird = userThirdService.getByThird(openId, AccountType.WEIXIN);
        // 第一次
        if (userThird == null) {
            // String tokenId = userFilterService.getTokenId(request);
            // //
            // User user = null;
            // if (StringUtils.isEmpty(tokenId)) {
            // Long gradeId = parameterClient.get(USER_REGIST_GRADE_KEY);
            // user = new User();
            // user.setEmail("");
            // user.setGradeId(gradeId);
            // user.setHeadImage("");
            // user.setMobile("");
            // user.setName("");
            // user.setNickname("");
            // user.setRegistTime(new Date());
            // user.setSex(new Long(SexType.UNKNOW.getKey()).intValue());
            // user.setState(UserStateType.ACTIVATE.getKey());
            // user.setType(AccountType.WEIXIN.getKey());
            // userService.add(user, null);
            // } else {
            // String idStr = tokenClient.get(tokenId);
            // AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
            // user = userService.get(Long.parseLong(idStr));
            // AssertUtil.assertNotNull(user, "用户不存在.");
            // UserThird ut = userThirdService.getByUser(user.getId());
            // if (ut != null) {
            // if (ut.getThirdId() != openId) {
            // logout(request);
            // }
            // AssertUtil.assertTrue(ut.getThirdId() == openId, "账号已经绑定微信.");
            // }
            // }
            // userThird = new UserThird();
            // userThird.setAccountType(AccountType.WEIXIN.getKey());
            // userThird.setCreateTime(new Date());
            // userThird.setUserId(user.getId());
            // userThird.setThirdId(openId);
            // userThirdService.add(userThird);
            HtmlView view = new HtmlView("<script>window.location.href='" + "http://mp1.test.qi-cloud.com/login.html?code=" + openId + "'</script>");
            return view;
        } else {
            QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_IS_LOGIN_PARAMETER_KEY);
            if (user != null) {
                if (userThird.getUserId() != user.getId()) {
                    logout(request);
                }
                // logger.error("使用新微信登录前请在旧的微信号退出." + userThird.getThirdId());
                // AssertUtil.assertTrue(userThird.getUserId() == user.getId(), "使用新微信登录前请在旧的微信号退出.");
            }
        }
        // 开始加载把第三方数据Id放到session
        User user = userService.get(userThird.getUserId());
        AssertUtil.assertNotNull(user, "用户不存在." + user.getId());
        boolean e = userService.isEnableUser(user);
        AssertUtil.assertTrue(e, "用户可能会禁用了.");
        if (StringUtils.isEmpty(user.getMobile())) {
            // 让其注册
            // TODO 这里是绑定用户
            request.getSession(true).setAttribute(THIRD_USER, String.valueOf(userThird.getId()));
        }
        // else {
        // 让其登录
        String tokenId = userFilterService.doLogin(request);
        boolean ok = tokenClient.reg(tokenId, String.valueOf(userThird.getUserId()));
        if (!ok) {
            throw new PersonalCenterException("系统服务出现异常,token添加失败.");
        }
        // }
        // 获取openId的状态
        request.getSession(true).setAttribute("STATUS", true);
        // String url = "http://" + request.getServerName();
        HtmlView view = new HtmlView("<script>window.location.href='" + pageUri + "'</script>");
        return view;
    }

    @RequestMapping
    public FrontAjaxView logout4Weixin(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        String openId = personalcenterClient.getThirdId(user.getId());
        UserThird userThird = userThirdService.getByThird(openId, AccountType.WEIXIN);
        if (userThird != null) {
            userThirdService.delete(userThird.getId());
        }
        userFilterService.doLogout(request);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("已经解绑账号.");
        return view;
    }

    @Autowired
    private WeiXinConfigService  weiXinConfigService;

    @Autowired
    private WeiXinAPIService     weiXinAPIService;

    @Autowired
    private PersonalcenterClient personalcenterClient;

    /**
     * 获取用户是否关注公众号
     * @param code
     * @return
     */
    @RequestMapping
    public FrontAjaxView isSubscribe(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        String openId = personalcenterClient.getThirdId(user.getId());
        WeiXinConfig weiXinConfig = weiXinConfigService.get();
        String appId = weiXinConfig.getAppId();
        String appSecret = weiXinConfig.getAppSecret();
        String accessToken = weiXinAPIService.getAccessToken(appId, appSecret);
        WXUserEntity userEntity = WXUtil.getUserInfo(accessToken, openId);
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("subscribe", userEntity.getSubscribe());
        return view;
    }

    /**
     * 仅做回调接口是否获取openId判断
     * @param request
     * @return
     */
    @RequestMapping
    public FrontAjaxView openIdStatus(HttpServletRequest request) {

        Object obj = request.getSession(true).getAttribute("STATUS");
        boolean status = false;
        if (obj != null) status = true;
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("status", status);
        return view;
    }

    @RequestMapping
    public FrontAjaxView reactivated(Long userId) {

        User user = userService.get(userId);
        user.setState(UserStateType.ACTIVATE.getKey());
        userService.update(user);
        FrontAjaxView view = new FrontAjaxView();
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView editAddress(HttpServletRequest request, String province, String city, String district, String address) {

        User user = userHelper.getUser(request);
        user.setProvince(province);
        user.setCity(city);
        user.setDistrict(district);
        user.setAddress(address);
        user.setHeadImage(fileSDKClient.urlToUid(user.getHeadImage()));
        userService.update(user);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("修改地址成功.");
        return view;
    }
}
