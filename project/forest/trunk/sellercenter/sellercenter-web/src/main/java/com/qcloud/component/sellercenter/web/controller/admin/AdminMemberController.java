//package com.qcloud.component.sellercenter.web.controller.admin;
//
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//import com.qcloud.component.admin.AdminClient;
//import com.qcloud.component.publicdata.KeyValueVO;
//import com.qcloud.component.publicdata.PublicdataClient;
//import com.qcloud.component.publicdata.SexType;
//import com.qcloud.component.sellercenter.exception.SellerCenterException;
//import com.qcloud.component.sellercenter.model.Member;
//import com.qcloud.component.sellercenter.model.query.MemberQuery;
//import com.qcloud.component.sellercenter.service.MemberService;
//import com.qcloud.component.sellercenter.web.handler.MemberHandler;
//import com.qcloud.component.sellercenter.web.vo.admin.AdminMemberVO;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.mvc.AceAjaxView;
//import com.qcloud.pirates.mvc.AcePagingView;
//import com.qcloud.pirates.util.AssertUtil;
//import com.qcloud.pirates.util.NumberUtil;
//import com.qcloud.pirates.util.RequestUtil;
//import com.qcloud.pirates.util.StringUtil;
//import com.qcloud.pirates.web.page.PageParameterUtil;
//import com.qcloud.pirates.web.security.annotation.NoReferer;
//
//@Controller
//@RequestMapping(value = "/" + AdminMemberController.DIR)
//public class AdminMemberController {
//
//    public static final String DIR = "admin/member";
//
//    @Autowired
//    private MemberService      memberService;
//
//    @Autowired
//    private MemberHandler      memberHandler;
//
//    @Autowired
//    private PublicdataClient   publicdataClient;
//
//    // @Autowired
//    // private AdminFilterService adminFilterService;
//    //
//    // @Autowired
//    // private TokenClient tokenClient;
//    @RequestMapping
//    @NoReferer
//    public ModelAndView list(Integer pageNum, MemberQuery query) {
//
//        final int PAGE_SIZE = 10;
//        pageNum = RequestUtil.getPageid(pageNum);
//        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
//        Page<Member> page = memberService.page(query, start, PAGE_SIZE);
//        List<AdminMemberVO> list = memberHandler.toVOList4Admin(page.getData());
//        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-Member-list", DIR + "/list?name=" + StringUtil.nullToEmpty(query.getName()), pageNum, PAGE_SIZE, page.getCount());
//        pagingView.addObject("result", list);
//        pagingView.addObject("query", query);
//        return pagingView;
//    }
//
//    @RequestMapping
//    public ModelAndView toAdd() {
//
//        ModelAndView model = new ModelAndView("/admin/sellercenter-Member-add");
//        List<KeyValueVO> sexTypeList = publicdataClient.exchageObj(SexType.values(), -1, "selected");
//        model.addObject("sexTypeList", sexTypeList);
//        return model;
//    }
//
//    // @RequestMapping
//    // public AceAjaxView add(Member member) {
//    //
//    // memberService.add(member);
//    // AceAjaxView aceAjaxView = new AceAjaxView();
//    // aceAjaxView.setMessage("添加成功");
//    // aceAjaxView.setUrl(DIR + "/list");
//    // return aceAjaxView;
//    // }
//    @RequestMapping
//    public ModelAndView toEdit(Long id) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        Member member = memberService.get(id);
//        AdminMemberVO adminMemberVO = memberHandler.toVO4Admin(member);
//        ModelAndView model = new ModelAndView("/admin/sellercenter-Member-edit");
//        model.addObject("member", adminMemberVO);
//        List<KeyValueVO> sexTypeList = publicdataClient.exchageObj(SexType.values(), member.getSex(), "selected");
//        model.addObject("sexTypeList", sexTypeList);
//        return model;
//    }
//
//    @RequestMapping
//    public AceAjaxView edit(Member member) {
//
//        memberService.update(member);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("编辑成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//
//    @RequestMapping
//    public AceAjaxView delete(Long id) {
//
//        AssertUtil.assertNotNull(id, "ID不能为空");
//        memberService.delete(id);
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        aceAjaxView.setMessage("删除成功");
//        aceAjaxView.setUrl(DIR + "/list");
//        return aceAjaxView;
//    }
//
//    @RequestMapping
//    public AceAjaxView resetPwd(HttpServletRequest request, Long id) {
//
//        // TODO 待处理用户权限验证
//        AssertUtil.assertTrue(id > 0, "ID无效");
//        Member data = memberService.get(id);
//        AssertUtil.assertNotNull(data, "ID无效");
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        data.setPassword(memberService.getEncodeDefaultPwd());
//        if (memberService.update(data)) {
//            aceAjaxView.setMessage("重置成功");
//        } else {
//            aceAjaxView.setMessage("重置失败");
//            aceAjaxView.setStatus(0);
//        }
//        return aceAjaxView;
//    }
//
//    @RequestMapping
//    public ModelAndView toEditPwd(HttpServletRequest request) {
//
//        Long memberId = PageParameterUtil.getParameterValues(request, AdminClient.ADMIN_TOKEN_LOGIN_PARAMETER_KEY);
//        Member member = memberService.get(memberId);
//        ModelAndView model = new ModelAndView("/admin/sellercenter-Member-editPwd");
//        model.addObject("member", member);
//        return model;
//    }
//
//    @RequestMapping
//    public AceAjaxView editPwd(HttpServletRequest request, String pwd1, String pwd2) {
//
//        Long memberId = PageParameterUtil.getParameterValues(request, AdminClient.ADMIN_TOKEN_LOGIN_PARAMETER_KEY);
//        Member member = memberService.get(memberId);
//        AssertUtil.assertNotNull(member, "此用户不存在!");
//        AceAjaxView aceAjaxView = new AceAjaxView();
//        if (!memberService.getEncodePsw(pwd1).equals(member.getPassword())) {
//            throw new SellerCenterException("原密码不正确!");
//        }
//        member.setPassword(memberService.getEncodePsw(pwd2));
//        if (memberService.update(member)) {
//            aceAjaxView.setMessage("修改成功");
//        } else {
//            aceAjaxView.setMessage("修改失败");
//            aceAjaxView.setStatus(0);
//        }
//        return aceAjaxView;
//    }
//    
//    /**
//     * 成员列表 - 弹框
//     * @param pageNum
//     * @param query
//     * @return
//     */
// 	@RequestMapping
//	public ModelAndView selectMember(Integer pageNum, MemberQuery query) {
//		final int PAGE_SIZE = 10;
//		pageNum = RequestUtil.getPageid(pageNum);
//		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
//		Page<Member> page = memberService.page(query, start, PAGE_SIZE);
//		List<AdminMemberVO> list = memberHandler.toVOList4Admin(page.getData());
//		AcePagingView pagingView = new AcePagingView("/admin/sellercenter-Member-selectMember", DIR + "/list?keyword=" + StringUtil.nullToEmpty(query.getKeyword()), pageNum, PAGE_SIZE, page.getCount());
//		pagingView.addObject("result", list);
//		pagingView.addObject("query", query);
//		return pagingView;
//	}
//}
