package com.qcloud.component.sellercenter.web.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.qcloud.component.publicdata.util.TreeUtils;
import com.qcloud.component.publicdata.util.TreeUtils.TreeModel;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.Member;
import com.qcloud.component.sellercenter.model.Store;
import com.qcloud.component.sellercenter.model.StoreMember;
import com.qcloud.component.sellercenter.model.query.StoreMemberQuery;
import com.qcloud.component.sellercenter.service.MemberService;
import com.qcloud.component.sellercenter.service.StoreMemberService;
import com.qcloud.component.sellercenter.service.StoreService;
import com.qcloud.component.sellercenter.web.handler.MemberHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMemberVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminStoreMemberController.DIR)
public class AdminStoreMemberController {

    public static final String    DIR                   = "admin/storeMember";

    @Autowired
    private MemberService         memberService;

    @Autowired
    private MemberHandler         memberHandler;

    @Autowired
    private PublicdataClient      publicdataClient;

//    @Autowired
//    private PersonalcenterClient  personalcenterClient;
//
//    private static final String   USER_REGIST_GRADE_KEY = "personalcenter-user-grade";

    // @Autowired
    // private AdminFilterService adminFilterService;
    //
    // @Autowired
    // private TokenClient tokenClient;
    //
    // @Autowired
    // private SellercenterClient sellercenterClient;
//    @Autowired
//    private ParameterClient       parameterClient;

    @Autowired
    private StoreMemberService    storeMemberService;

    @Autowired
    private StoreService          storeService;

//    @Autowired
//    private MerchantMemberService merchantMemberService;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, StoreMemberQuery query) {

        AssertUtil.assertTrue(query.getStoreId() > 0, "门店信息错误");
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        query.setMerchantId(merchant.getId());
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<StoreMember> page = storeMemberService.page(query, start, PAGE_SIZE);
        List<Member> list = new ArrayList<Member>();
        for (StoreMember sm : page.getData()) {
            list.add(memberService.get(sm.getMemberId()));
        }
        List<AdminMemberVO> voList = memberHandler.toVOList4Admin(list);
        List<Store> storeList = storeService.listByMerchant(merchant.getId());
        List<TreeModel> treeModelList = new ArrayList<TreeModel>();
        for (Store store : storeList) {
            treeModelList.add(store);
        }
        List<KeyValueVO> storeKVList = TreeUtils.exchangeObj(treeModelList, query.getStoreId(), "selected");
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-StoreMember-list", DIR + "/list?storeId=" + query.getStoreId(), pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", voList);
        pagingView.addObject("query", query);
        pagingView.addObject("storeKVList", storeKVList);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd(HttpServletRequest request, Long storeId) {

        AssertUtil.assertTrue(storeId > 0, "门店ID错误");
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        List<Store> storeList = storeService.listByMerchant(merchant.getId());
        List<TreeModel> treeModelList = new ArrayList<TreeModel>();
        for (Store store : storeList) {
            treeModelList.add(store);
        }
        List<KeyValueVO> storeKVList = TreeUtils.exchangeObj(treeModelList, -1L, "selected");
        ModelAndView model = new ModelAndView("/admin/sellercenter-StoreMember-add");
        List<KeyValueVO> sexTypeList = publicdataClient.exchageObj(SexType.values(), -1, "selected");
        model.addObject("sexTypeList", sexTypeList);
        model.addObject("storeKVList", storeKVList);
        model.addObject("storeId", storeId);
        return model;
    }

    @Transactional
    @RequestMapping
    public AceAjaxView add(HttpServletRequest request, Member member, Long storeId) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        // 添加member表和store_member信息
        member.setUserId(null);
        boolean result = storeMemberService.add(member, merchant.getId(), storeId);
        AssertUtil.assertTrue(result, "添加失败");
        // 添加merchant_member信息
        // MerchantMember merchantMember = new MerchantMember();
        // merchantMember.setMemberId(member.getId());
        // merchantMember.setMerchantId(merchant.getId());
        // result = merchantMemberService.add(merchantMember);
        AssertUtil.assertTrue(result, "添加失败");
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(HttpServletRequest request, Long id) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        Member member = this.getStoreUserMemer(id, merchant.getId());
        ModelAndView model = new ModelAndView("/admin/sellercenter-StoreMember-edit");
        List<KeyValueVO> sexTypeList = publicdataClient.exchageObj(SexType.values(), -1, "selected");
        model.addObject("sexTypeList", sexTypeList);
        model.addObject("member", member);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(HttpServletRequest request, Member member) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        Member data = this.getStoreUserMemer(member.getId(), merchant.getId());
        data.setName(member.getName());
        data.setSex(member.getSex());
        data.setNickname(member.getNickname());
        data.setMobile(member.getMobile());
        data.setQq(member.getQq());
        data.setEnable(member.getEnable());
        AceAjaxView aceAjaxView = new AceAjaxView();
        if (memberService.update(data)) {
            aceAjaxView.setMessage("修改成功");
        } else {
            aceAjaxView.setStatus(0);
            aceAjaxView.setMessage("修改失败");
        }
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView resetPwd(HttpServletRequest request, Long id) {

        AssertUtil.assertTrue(id > 0, "ID无效");
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        Member data = this.getStoreUserMemer(id, merchant.getId());
        AceAjaxView aceAjaxView = new AceAjaxView();
        data.setPassword(memberService.getEncodeDefaultPwd());
        if (memberService.update(data)) {
            aceAjaxView.setMessage("重置成功");
        } else {
            aceAjaxView.setMessage("重置失败");
            aceAjaxView.setStatus(0);
        }
        return aceAjaxView;
    }

    private Member getStoreUserMemer(Long userId, Long merchantId) {

        AssertUtil.assertTrue(userId > 0, "ID无效");
        Member member = memberService.get(userId);
        AssertUtil.assertNotNull(member, "用户信息获取失败");
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("memberId", member.getId());
        where.put("merchantId", merchantId);
        AssertUtil.assertNotNull(storeMemberService.get(where), "用户门店信息获取失败");
        return member;
    }
}
