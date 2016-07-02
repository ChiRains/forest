package com.qcloud.component.sellercenter.web.controller.admin;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.SexType;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.Member;
import com.qcloud.component.sellercenter.model.MerchantMember;
import com.qcloud.component.sellercenter.model.query.MerchantMemberQuery;
import com.qcloud.component.sellercenter.service.MemberService;
import com.qcloud.component.sellercenter.service.MerchantMemberService;
import com.qcloud.component.sellercenter.web.handler.MemberHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMemberVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMerchantMemberController.DIR)
public class AdminMerchantMemberController {

    public static final String    DIR = "admin/merchantMember";

    @Autowired
    private MemberService         memberService;

    @Autowired
    private MemberHandler         memberHandler;

    @Autowired
    private PublicdataClient      publicdataClient;

    @Autowired
    private MerchantMemberService merchantMemberService;

    // @Autowired
    // private StoreService storeService;
    //
    // @Autowired
    // private StoreMemberService storeMemberService;
    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, Long merchantId) {

        AssertUtil.assertNotNull(merchantId, "您尚未属于一家商家.");
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        MerchantMemberQuery query = new MerchantMemberQuery();
        query.setMerchantId(merchantId);
        Page<MerchantMember> page = merchantMemberService.page(query, start, PAGE_SIZE);
        List<Member> list = new ArrayList<Member>();
        for (MerchantMember mm : page.getData()) {
            list.add(memberService.get(mm.getMemberId()));
        }
        List<AdminMemberVO> voList = memberHandler.toVOList4Admin(list);
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-MerchantMember-list", DIR + "/list?merchantId=" + merchantId, pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", voList);
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView list4Merchant(HttpServletRequest request, Integer pageNum) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(merchant, "您尚未属于一家商家.");
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        MerchantMemberQuery query = new MerchantMemberQuery();
        query.setMerchantId(merchant.getId());
        Page<MerchantMember> page = merchantMemberService.page(query, start, PAGE_SIZE);
        List<Member> list = new ArrayList<Member>();
        for (MerchantMember mm : page.getData()) {
            list.add(memberService.get(mm.getMemberId()));
        }
        List<AdminMemberVO> voList = memberHandler.toVOList4Admin(list);
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-MerchantMember-list", DIR + "/list?merchantId=" + merchant.getId(), pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", voList);
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd(HttpServletRequest request, Long merchantId) {

        AssertUtil.assertTrue(merchantId > 0, "商家ID错误");
        ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantMember-add");
        List<KeyValueVO> sexTypeList = publicdataClient.exchageObj(SexType.values(), -1, "selected");
        model.addObject("sexTypeList", sexTypeList);
        model.addObject("merchantId", merchantId);
        return model;
    }

    @Transactional
    @RequestMapping
    public AceAjaxView add(HttpServletRequest request, Member member, Long merchantId) {

        AssertUtil.assertNotNull(merchantId, "您尚未属于一家商家.");
        // Long storeId = 0L;
        // List<Store> stores = storeService.listByMerchant(merchantId);
        // for (Store store : stores) {
        // if (store.getParentId() == -1) {
        // storeId = store.getId();
        // }
        // }
        // 添加member表和store_member信息
        member.setUserId(0L);
        // boolean result = storeMemberService.add(member, merchantId, storeId);
        boolean result = memberService.add(member, merchantId);
        AssertUtil.assertTrue(result, "添加失败");
        // 添加merchant_member信息
        MerchantMember merchantMember = new MerchantMember();
        merchantMember.setMemberId(member.getId());
        merchantMember.setMerchantId(merchantId);
        result = merchantMemberService.add(merchantMember);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list?merchantId=" + merchantId);
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Member member = memberService.get(id);
        AdminMemberVO adminMemberVO = memberHandler.toVO4Admin(member);
        ModelAndView model = new ModelAndView("/admin/sellercenter-MerchantMember-edit");
        model.addObject("member", adminMemberVO);
        List<KeyValueVO> sexTypeList = publicdataClient.exchageObj(SexType.values(), member.getSex(), "selected");
        model.addObject("sexTypeList", sexTypeList);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(Member member) {

        AssertUtil.assertNotNull(member.getId(), "用户ID错误");
        Member data = memberService.get(member.getId());
        AssertUtil.assertNotNull(data, "找不到用户");
        data.setName(member.getName());
        data.setSex(member.getSex());
        data.setNickname(member.getNickname());
        data.setMobile(member.getMobile());
        data.setQq(member.getQq());
        data.setEnable(member.getEnable());
        memberService.update(data);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        // aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView listAllBySelectForAdmin(MerchantMemberQuery query, PPage pPage) {

        Page<MerchantMember> page = merchantMemberService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<Member> list = new ArrayList<Member>();
        for (MerchantMember mm : page.getData()) {
            list.add(memberService.get(mm.getMemberId()));
        }
        List<AdminMemberVO> voList = memberHandler.toVOList4Admin(list);
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-MerchantMember-listBySelect", DIR + "/list", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", voList);
        pagingView.addObject("query", query);
        return pagingView;
    }
}
