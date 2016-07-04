//package com.qcloud.component.sellercenter.web.controller.app;
//
//import java.util.HashMap;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.admin.exception.AdminException;
//import com.qcloud.component.sellercenter.exception.SellerCenterException;
//import com.qcloud.component.sellercenter.model.Member;
//import com.qcloud.component.sellercenter.model.MerchantMember;
//import com.qcloud.component.sellercenter.model.StoreMember;
//import com.qcloud.component.sellercenter.service.MemberService;
//import com.qcloud.component.sellercenter.service.MerchantMemberService;
//import com.qcloud.component.sellercenter.service.StoreMemberService;
//import com.qcloud.component.token.TokenClient;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.util.AssertUtil;
//import com.qcloud.pirates.web.filter.user.UserFilterService;
//
//@Controller
//@RequestMapping(value = AppMemberController.DIR)
//public class AppMemberController {
//
//    public static final String      DIR = "/app/member";
//
//    @Autowired
//    private MemberService           memberService;
//
//    @Autowired
//    private UserFilterService       userFilterService;
//
//    @Autowired
//    private TokenClient             tokenClient;
//
//    @Autowired
//    protected MerchantMemberService merchantMemberService;
//
//    @Autowired
//    private StoreMemberService      storeMemberService;
//
//    /**
//     * 帐号登录
//     * @param request
//     * @param username
//     * @param pwd
//     * @return
//     */
//    @RequestMapping
//    public FrontAjaxView login(HttpServletRequest request, String username, String pwd) {
//
//        AssertUtil.assertNotEmpty(username, "账号不能为空.");
//        AssertUtil.assertNotEmpty(pwd, "密码不能为空.");
//        String identificationKey = null;
//        Member member = null;
//        if (memberService.isMember(username, pwd)) {
//            member = memberService.getByAccount(username);
//            identificationKey = String.valueOf(member.getId());
//        } else {
//            throw new SellerCenterException("账号或密码有误.");
//        }
//        String tokenId = userFilterService.doLogin(request);
//        boolean ok = tokenClient.reg(tokenId, identificationKey);
//        if (!ok) {
//            throw new AdminException("系统服务出现异常,token添加失败.");
//        }
//        HashMap where = new HashMap();
//        where.put("memberId", member.getId());
//        StoreMember storeMember = storeMemberService.get(where);
//        List<MerchantMember> list = merchantMemberService.listByMember(member.getId());
//        int memberType;
//        if (storeMember == null && CollectionUtils.isEmpty(list)) {
//            throw new SellerCenterException("您尚未属于一个商家或者门店.");
//        } else if (storeMember != null && CollectionUtils.isEmpty(list)) {
//            memberType = 1;
//        } else if (storeMember == null && !CollectionUtils.isEmpty(list)) {
////            memberType = 2;
//            throw new SellerCenterException("商家客户端现仅支持门店职员使用.");
//        } else if (storeMember != null && !CollectionUtils.isEmpty(list)) {
//            memberType = 3;
//        } else {
//            throw new SellerCenterException("系统内部错误.");
//        }
//        FrontAjaxView fontAjaxView = new FrontAjaxView();
//        fontAjaxView.setMessage("登录成功");
//        fontAjaxView.addObject("token", tokenId);
//        fontAjaxView.addObject("memberType", memberType);
//        return fontAjaxView;
//    }
//}
