package com.qcloud.component.marketing.web.controller.admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.admin.AdminClient;
import com.qcloud.component.admin.QAdmin;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.model.query.CouponItemsQuery;
import com.qcloud.component.marketing.service.CouponItemsService;
import com.qcloud.component.marketing.service.CouponMultipleConfigService;
import com.qcloud.component.marketing.service.CouponService;
import com.qcloud.component.marketing.web.handler.CouponItemsHandler;
import com.qcloud.component.marketing.web.vo.admin.AdminCouponItemsVO;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
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
@RequestMapping(value = "/" + AdminCouponItemsController.DIR)
public class AdminCouponItemsController {

    public static final String          DIR = "admin/couponItems";

    @Autowired
    private CouponService               couponService;

    @Autowired
    private CouponItemsService          couponItemsService;

    @Autowired
    private CouponItemsHandler          couponItemsHandler;

    @Autowired
    private CouponMultipleConfigService couponMultipleConfigService;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, CouponItemsQuery query) {

        PageParameterUtil.getParameterValues(request, AdminClient.ADMIN_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(query.getCouponID(), "couponID不能为空");
        Page<CouponItems> page = couponItemsService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminCouponItemsVO> list = couponItemsHandler.toVOList4Admin(page.getData());
        if (couponMultipleConfigService.isMallMultiple()) {
            AcePagingView pagingView = new AcePagingView("/admin/marketing-CouponItems-list", DIR + "/list?couponID=" + query.getCouponID(), pPage.getPageNum(), pPage.getPageSize(), page.getCount());
            pagingView.addObject("result", list);
            pagingView.addObject("couponID", query.getCouponID());
            return pagingView;
        } else {
            long couponItemId = 0L;
            if (CollectionUtils.isNotEmpty(list)) {
                couponItemId = list.get(0).getId();
            }
            return toGet(couponItemId, query.getCouponID());
        }
    }

    @RequestMapping
    public ModelAndView toGet(Long couponItemId, Long couponID) {

        CouponItems couponItems = couponItemsService.get(couponItemId);
        ModelAndView model = new ModelAndView("/admin/marketing-CouponItems-toGet");
        model.addObject("couponID", couponID);
        model.addObject("couponItems", couponItems);
        return model;
    }

    @RequestMapping
    public AceAjaxView set(CouponItems couponItems) {

        AssertUtil.assertTrue(couponItems.getLimitPrice() > couponItems.getPrice(), "优惠券限额必须大于优惠券面额");
        Coupon coupon = couponService.get(couponItems.getCouponID());
        AssertUtil.assertNotNull(coupon, "优惠劵活动不存在" + couponItems.getCouponID());
        if (couponItems.getId() == 0) {
            couponItemsService.add(couponItems);
        } else {
            couponItemsService.update(couponItems);
        }
        AceAjaxView view = new AceAjaxView();
        view.setMessage("保存成功");
        view.setUrl(DIR + "/list?couponID=" + couponItems.getCouponID());
        return view;
    }

    @RequestMapping
    public ModelAndView toAdd(Long couponID) {

        ModelAndView model = new ModelAndView("/admin/marketing-CouponItems-add");
        model.addObject("couponID", couponID);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(CouponItems couponItems) {

        AssertUtil.assertTrue(couponItems.getLimitPrice() > couponItems.getPrice(), "优惠券限额必须大于优惠券面额");
        Coupon coupon = couponService.get(couponItems.getCouponID());
        AssertUtil.assertNotNull(coupon, "优惠劵活动不存在" + couponItems.getCouponID());
        couponItemsService.add(couponItems);
        couponItems.setType(coupon.getType());
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list?couponID=" + couponItems.getCouponID());
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        CouponItems couponItems = couponItemsService.get(id);
        AdminCouponItemsVO adminCouponItemsVO = couponItemsHandler.toVO4Admin(couponItems);
        ModelAndView model = new ModelAndView("/admin/marketing-CouponItems-edit");
        model.addObject("couponItems", adminCouponItemsVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(CouponItems couponItems) {

        AssertUtil.assertTrue(couponItems.getLimitPrice() > couponItems.getPrice(), "优惠券限额必须大于优惠券面额");
        couponItemsService.update(couponItems);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?couponID=" + couponItems.getCouponID());
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        couponItemsService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    //
    @RequestMapping
    @NoReferer
    public ModelAndView list4Merchant(HttpServletRequest request, Integer pageNum, CouponItemsQuery query) {

        PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(query.getCouponID(), "couponID不能为空");
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<CouponItems> page = couponItemsService.page(query, start, PAGE_SIZE);
        List<AdminCouponItemsVO> list = couponItemsHandler.toVOList4Admin(page.getData());
        if (couponMultipleConfigService.isMerchantMultiple()) {
            AcePagingView pagingView = new AcePagingView("/admin/marketing-CouponItems-list4Merchant", DIR + "/list4Merchant?couponID=" + query.getCouponID(), pageNum, PAGE_SIZE, page.getCount());
            pagingView.addObject("result", list);
            pagingView.addObject("couponID", query.getCouponID());
            return pagingView;
        } else {
            long couponItemId = 0L;
            if (CollectionUtils.isNotEmpty(list)) {
                couponItemId = list.get(0).getId();
            }
            return toGet4Merchant(couponItemId, query.getCouponID());
        }
    }

    @RequestMapping
    public ModelAndView toGet4Merchant(Long couponItemId, Long couponID) {

        CouponItems couponItems = couponItemsService.get(couponItemId);
        ModelAndView model = new ModelAndView("/admin/marketing-CouponItems-toGet4Merchant");
        model.addObject("couponID", couponID);
        model.addObject("couponItems", couponItems);
        return model;
    }

    @RequestMapping
    public AceAjaxView set4Merchant(CouponItems couponItems) {

        AssertUtil.assertTrue(couponItems.getLimitPrice() > couponItems.getPrice(), "优惠券限额必须大于优惠券面额");
        Coupon coupon = couponService.get(couponItems.getCouponID());
        AssertUtil.assertNotNull(coupon, "优惠劵活动不存在" + couponItems.getCouponID());
        if (couponItems.getId() == 0) {
            couponItemsService.add(couponItems);
        } else {
            couponItemsService.update(couponItems);
        }
        AceAjaxView view = new AceAjaxView();
        view.setMessage("保存成功");
        view.setUrl(DIR + "/list4Merchant?couponID=" + couponItems.getCouponID());
        return view;
    }

    @RequestMapping
    public ModelAndView toAdd4Merchant(Long couponID) {

        ModelAndView model = new ModelAndView("/admin/marketing-CouponItems-add4Merchant");
        model.addObject("couponID", couponID);
        return model;
    }

    @RequestMapping
    public AceAjaxView add4Merchant(CouponItems couponItems) {

        AssertUtil.assertTrue(couponItems.getLimitPrice() > couponItems.getPrice(), "优惠券限额必须大于优惠券面额");
        couponItemsService.add(couponItems);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list4Merchant?couponID=" + couponItems.getCouponID());
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit4Merchant(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        CouponItems couponItems = couponItemsService.get(id);
        AdminCouponItemsVO adminCouponItemsVO = couponItemsHandler.toVO4Admin(couponItems);
        ModelAndView model = new ModelAndView("/admin/marketing-CouponItems-edit4Merchant");
        model.addObject("couponItems", adminCouponItemsVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit4Merchant(CouponItems couponItems) {

        AssertUtil.assertTrue(couponItems.getLimitPrice() > couponItems.getPrice(), "优惠券限额必须大于优惠券面额");
        couponItemsService.update(couponItems);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list4Merchant?couponID=" + couponItems.getCouponID());
        return aceAjaxView;
    }
}
