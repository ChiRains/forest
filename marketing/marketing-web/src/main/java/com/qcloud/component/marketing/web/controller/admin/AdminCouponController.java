package com.qcloud.component.marketing.web.controller.admin;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.admin.AdminClient;
import com.qcloud.component.admin.QAdmin;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.marketing.exception.MarketingException;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.model.key.TypeEnum.CouponType;
import com.qcloud.component.marketing.model.key.TypeEnum.Enable;
import com.qcloud.component.marketing.model.key.TypeEnum.PlatformCoupon;
import com.qcloud.component.marketing.model.query.CouponQuery;
import com.qcloud.component.marketing.service.CouponItemsService;
import com.qcloud.component.marketing.service.CouponService;
import com.qcloud.component.marketing.web.handler.CouponHandler;
import com.qcloud.component.marketing.web.vo.admin.AdminCouponVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminCouponController.DIR)
public class AdminCouponController {

    public static final String DIR = "admin/coupon";

    @Autowired
    private CouponService      couponService;

    @Autowired
    private CouponHandler      couponHandler;

    @Autowired
    private FileSDKClient      fileSDKClient;

    @Autowired
    private PublicdataClient   publicdataClient;

    @Autowired
    private CouponItemsService couponItemsService;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, CouponQuery query) {

        QAdmin admin = PageParameterUtil.getParameterValues(request, AdminClient.ADMIN_LOGIN_PARAMETER_KEY);
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        query.setMerchantId((long) PlatformCoupon.PLATFORM.getKey());
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<Coupon> page = couponService.page(query, start, PAGE_SIZE);
        List<AdminCouponVO> list = couponHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/marketing-Coupon-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    // 平台发布优惠券
    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/marketing-Coupon-add");
        String fileSize = publicdataClient.getImageInformationByCode("youhuiquanhuodong");
        model.addObject("fileSize", fileSize);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(Coupon coupon) {

        AssertUtil.assertTrue(!coupon.getStartDate().before(DateUtil.str2Date(DateUtil.getDate(new Date()) + " 00:00:00", "yyyy-MM-dd")), "优惠券开始领取时间不能早于当前时间.");
        AssertUtil.assertTrue(!coupon.getStartDate().after(coupon.getEndDate()), "优惠券开始领取时间不能晚于截止领取时间.");
        AssertUtil.assertTrue(!coupon.getValidDate().after(coupon.getInvalidDate()), "优惠券开始使用时间不能晚于截止使用时间.");
        AssertUtil.assertTrue(!coupon.getInvalidDate().before(coupon.getEndDate()), "优惠券截止使用时间不能早于截止领取时间.");
        coupon.setMerchantId((long) PlatformCoupon.PLATFORM.getKey());
        coupon.setEnable(Enable.ENABLE.getKey());
        couponService.add(coupon);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Coupon coupon = couponService.get(id);
        AdminCouponVO adminCouponVO = couponHandler.toVO4Admin(coupon);
        ModelAndView model = new ModelAndView("/admin/marketing-Coupon-edit");
        model.addObject("coupon", adminCouponVO);
        String fileSize = publicdataClient.getImageInformationByCode("youhuiquanhuodong");
        model.addObject("fileSize", fileSize);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(Coupon coupon) {

        AssertUtil.assertNotNull(coupon.getId(), "ID不能为空");
        Coupon oldCoupon = couponService.get(coupon.getId());
        AssertUtil.assertNotNull(oldCoupon, "优惠券活动不存在");
        AssertUtil.assertTrue(!oldCoupon.getStartDate().after(coupon.getEndDate()), "优惠券开始领取时间不能晚于截止领取时间.");
        AssertUtil.assertTrue(!coupon.getValidDate().after(coupon.getInvalidDate()), "优惠券开始使用时间不能晚于截止使用时间.");
        AssertUtil.assertTrue(!coupon.getInvalidDate().before(coupon.getEndDate()), "优惠券截止使用时间不能早于截止领取时间.");
        coupon.setStartDate(oldCoupon.getStartDate());
        coupon.setMerchantId(oldCoupon.getMerchantId());
        coupon.setImage(fileSDKClient.uidToUrl(coupon.getImage()));
        coupon.setEnable(oldCoupon.getEnable());
        couponService.update(coupon);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Coupon coupon = couponService.get(id);
        AssertUtil.assertNotNull(coupon, "优惠券不存在.");
        if (coupon.getValidDate().after(new Date())) {
            throw new MarketingException("优惠券正在使用." + id);
        }
        boolean flag = couponService.delete(id);
        AssertUtil.assertTrue(flag, "删除失败.");
        List<CouponItems> list = couponItemsService.listByCouponId(id);
        for (CouponItems couponItems : list) {
            couponItemsService.delete(couponItems.getId());
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView showList(Integer pageNum, CouponQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<Coupon> page = couponService.page(query, start, PAGE_SIZE);
        List<AdminCouponVO> list = couponHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/marketing-Coupon-showList", DIR + "/showList", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    // 商家发布优惠券
    @RequestMapping
    @NoReferer
    public ModelAndView list4Merchant(HttpServletRequest request, Integer pageNum, CouponQuery query) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        query.setMerchantId(merchant.getId());
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<Coupon> page = couponService.page(query, start, PAGE_SIZE);
        List<AdminCouponVO> list = couponHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/marketing-Coupon-list4Merchant", DIR + "/list4Merchant", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("couponType", CouponType.values());
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd4Merchant() {

        ModelAndView model = new ModelAndView("/admin/marketing-Coupon-add4Merchant");
        String fileSize = publicdataClient.getImageInformationByCode("youhuiquanhuodong");
        model.addObject("fileSize", fileSize);
        model.addObject("couponType", CouponType.values());
        return model;
    }

    @RequestMapping
    public AceAjaxView add4Merchant(HttpServletRequest request, Coupon coupon) {

        AssertUtil.assertTrue(!coupon.getStartDate().before(DateUtil.str2Date(DateUtil.getDate(new Date()) + " 00:00:00", "yyyy-MM-dd")), "优惠券开始领取时间不能早于当前时间.");
        AssertUtil.assertTrue(!coupon.getStartDate().after(coupon.getEndDate()), "优惠券开始领取时间不能晚于截止领取时间.");
        AssertUtil.assertTrue(!coupon.getValidDate().after(coupon.getInvalidDate()), "优惠券开始使用时间不能晚于截止使用时间.");
        AssertUtil.assertTrue(!coupon.getInvalidDate().before(coupon.getEndDate()), "优惠券截止使用时间不能早于截止领取时间.");
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        coupon.setMerchantId(merchant.getId());
        coupon.setEnable(Enable.ENABLE.getKey());
        couponService.add(coupon);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list4Merchant");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit4Merchant(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Coupon coupon = couponService.get(id);
        AdminCouponVO adminCouponVO = couponHandler.toVO4Admin(coupon);
        ModelAndView model = new ModelAndView("/admin/marketing-Coupon-edit4Merchant");
        model.addObject("coupon", adminCouponVO);
        String fileSize = publicdataClient.getImageInformationByCode("youhuiquanhuodong");
        model.addObject("fileSize", fileSize);
        model.addObject("couponType", CouponType.values());
        return model;
    }

    @RequestMapping
    public AceAjaxView edit4Merchant(HttpServletRequest request, Coupon coupon) {

        AssertUtil.assertNotNull(coupon.getId(), "ID不能为空");
        Coupon oldCoupon = couponService.get(coupon.getId());
        AssertUtil.assertNotNull(oldCoupon, "优惠券活动不存在");
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        AssertUtil.assertTrue(oldCoupon.getMerchantId() == merchant.getId(), "不能修改不属于自己的优惠券");
        AssertUtil.assertTrue(!oldCoupon.getStartDate().after(coupon.getEndDate()), "优惠券开始领取时间不能晚于截止领取时间.");
        AssertUtil.assertTrue(!coupon.getValidDate().after(coupon.getInvalidDate()), "优惠券开始使用时间不能晚于截止使用时间.");
        AssertUtil.assertTrue(!coupon.getInvalidDate().before(coupon.getEndDate()), "优惠券截止使用时间不能早于截止领取时间.");
        //
        coupon.setStartDate(oldCoupon.getStartDate());
        coupon.setMerchantId(oldCoupon.getMerchantId());
        coupon.setImage(fileSDKClient.uidToUrl(coupon.getImage()));
        coupon.setEnable(oldCoupon.getEnable());
        couponService.update(coupon);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list4Merchant");
        return aceAjaxView;
    }
}
