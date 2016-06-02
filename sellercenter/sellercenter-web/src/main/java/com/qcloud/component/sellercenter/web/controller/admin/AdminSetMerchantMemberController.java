package com.qcloud.component.sellercenter.web.controller.admin;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.sellercenter.model.Member;
import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.model.MerchantMember;
import com.qcloud.component.sellercenter.model.query.MemberQuery;
import com.qcloud.component.sellercenter.service.MemberService;
import com.qcloud.component.sellercenter.service.MerchantMemberService;
import com.qcloud.component.sellercenter.service.MerchantService;
import com.qcloud.component.sellercenter.web.handler.MemberHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMember4MerchantVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.util.StringUtil;

@Controller
@RequestMapping(value = "/" + AdminSetMerchantMemberController.DIR)
public class AdminSetMerchantMemberController {

    public static final String    DIR = "admin/setMerchantMember";

    @Autowired
    private MerchantMemberService merchantMemberService;

    @Autowired
    private MemberService         memberService;

    @Autowired
    private MemberHandler         memberHandler;

    @Autowired
    private MerchantService       merchantService;

    @RequestMapping
    public ModelAndView toSetMembers(Integer pageNum, String name, Long merchantId) {

        AssertUtil.assertNotNull(merchantId, "必须指定商家.");
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        int total = 0;
        List<AdminMember4MerchantVO> voList = new ArrayList<AdminMember4MerchantVO>();
        Merchant m = merchantService.get(merchantId);
        AssertUtil.assertNotNull(m, "指定商家不存在." + merchantId);
        if (merchantId != null && merchantId > 0) {
            List<MerchantMember> mmList = merchantMemberService.listByMerchant(merchantId);
            List<Long> keyList = new ArrayList<Long>();
            for (MerchantMember merchantMember : mmList) {
                keyList.add(merchantMember.getMemberId());
            }
            MemberQuery query = new MemberQuery();
            query.setName(name);
            Page<Member> page = memberService.page(query, start, PAGE_SIZE);
            voList = memberHandler.toVOList4Merchant(page.getData(), keyList);
            total = page.getCount();
        }
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-MerchantMember-setMembers", DIR + "/toSetMembers?name=" + StringUtil.nullToEmpty(name) + "&merchantId=" + merchantId, pageNum, PAGE_SIZE, total);
        pagingView.addObject("result", voList);
        pagingView.addObject("merchant", m);
        return pagingView;
    }

    @RequestMapping
    public AceAjaxView setMember(Long memberId, Long merchantId, Integer value) {

        AssertUtil.assertNotNull(memberId, "MemberId不能为空.");
        AssertUtil.assertNotNull(merchantId, "MerchantId不能为空.");
        AssertUtil.assertNotNull(value, "Value开关不能为空.");
        MerchantMember mm = merchantMemberService.get(memberId, merchantId);
        if (value == 1) {
            if (mm == null) {
                Member member = memberService.get(memberId);
                AssertUtil.assertNotNull(member, "职员不存在." + memberId);
                Merchant merchant = merchantService.get(merchantId);
                AssertUtil.assertNotNull(merchant, "商家不存在." + merchantId);
                MerchantMember merchantMember = new MerchantMember();
                merchantMember.setMemberId(memberId);
                merchantMember.setMerchantId(merchantId);
                merchantMemberService.add(merchantMember);
            }
        } else {
            if (mm != null) {
                merchantMemberService.delete(mm.getId());
            }
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("设置成功.");
        return aceAjaxView;
    }
}
