package com.qcloud.component.marketing.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.marketing.exception.MarketingException;
import com.qcloud.component.marketing.model.Coupon;
import com.qcloud.component.marketing.model.CouponItems;
import com.qcloud.component.marketing.model.key.TypeEnum.CouponType;
import com.qcloud.component.marketing.service.CouponItemsService;
import com.qcloud.component.marketing.service.CouponService;
import com.qcloud.component.marketing.web.handler.CouponHandler;
import com.qcloud.component.marketing.web.handler.CouponItemsHandler;
import com.qcloud.component.marketing.web.vo.CouponItemsVO;
import com.qcloud.component.marketing.web.vo.CouponVO;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.QMyCoupon;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.ListUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = CouponController.DIR)
public class CouponController {

    public static final String DIR = "/coupon";

    @Autowired
    private CouponService      couponService;

    @Autowired
    private CouponItemsService couponItemsService;

    @Autowired
    private CouponHandler      couponHandler;

    @Autowired
    private CouponItemsHandler couponItemsHandler;

    @Autowired
    private MyClient           myClient;

    @RequestMapping
    public FrontAjaxView existActivityCoupon() {

        boolean exist = couponService.existActivityCoupon(-1L);
        FrontAjaxView ajaxView = new FrontAjaxView();
        ajaxView.addObject("exist", exist);
        if (exist) {
            Coupon coupon = couponService.getActivityCoupon(-1L);
            AssertUtil.assertNotNull(coupon, "暂时无优惠劵活动");
            CouponVO vo = couponHandler.toVO(coupon);
            ajaxView.addObject("coupon", vo);
        }
        return ajaxView;
    }

    @RequestMapping
    public FrontAjaxView existMerchantActivityCoupon(Long merchantId) {

        // AssertUtil.assertNotNull(merchantId, "商家ID不能为空");
        // AssertUtil.greatZero(merchantId, "商家ID必须大于0" + merchantId);
        merchantId = getMerchantClassify();
        boolean exist = couponService.existActivityCoupon(merchantId);
        FrontAjaxView ajaxView = new FrontAjaxView();
        ajaxView.setMessage("获取优惠劵活动成功.");
        ajaxView.addObject("exist", exist);
        if (exist) {
            Coupon coupon = couponService.getActivityCoupon(merchantId);
            AssertUtil.assertNotNull(coupon, "暂时无优惠劵活动");
            CouponVO vo = couponHandler.toVO(coupon);
            ajaxView.addObject("coupon", vo);
        }
        return ajaxView;
    }

    @RequestMapping
    public FrontAjaxView getActivityCoupon() {

        Coupon coupon = couponService.getActivityCoupon(-1L);
        AssertUtil.assertNotNull(coupon, "暂时无优惠劵活动");
        CouponVO vo = couponHandler.toVO(coupon);
        FrontAjaxView ajaxView = new FrontAjaxView();
        ajaxView.setMessage("获取优惠劵活动成功.");
        ajaxView.addObject("coupon", vo);
        return ajaxView;
    }

    @RequestMapping
    public FrontAjaxView getMerchantActivityCoupon(Long merchantId) {

        // AssertUtil.assertNotNull(merchantId, "商家ID不能为空");
        // AssertUtil.greatZero(merchantId, "商家ID必须大于0" + merchantId);
        merchantId = getMerchantClassify();
        Coupon coupon = couponService.getActivityCoupon(merchantId);
        AssertUtil.assertNotNull(coupon, "暂时无优惠劵活动");
        CouponVO vo = couponHandler.toVO(coupon);
        FrontAjaxView ajaxView = new FrontAjaxView();
        ajaxView.setMessage("获取优惠劵活动成功.");
        ajaxView.addObject("coupon", vo);
        return ajaxView;
    }

    // @RequestMapping
    // public FrontAjaxView listActivityCouponItem(Long couponId) {
    //
    // Coupon coupon = couponService.get(couponId);
    // AssertUtil.assertNotNull(coupon, "暂时无优惠劵活动");
    // List<CouponItems> list = couponItemsService.list4Extract(couponId);
    // List<CouponItemsVO> voList = couponItemsHandler.toVOList(list);
    // FrontAjaxView ajaxView = new FrontAjaxView();
    // ajaxView.setMessage("获取优惠劵活动成功.");
    // ajaxView.addObject("list", voList);
    // return ajaxView;
    // }
    // @PiratesApp
    // @RequestMapping
    // public FrontAjaxView extractCouponItem(HttpServletRequest request, Long couponItemId) {
    //
    // QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
    // CouponItems couponItems = couponItemsService.get(couponItemId);
    // Long myCouponId = couponService.extractCouponItem(user.getId(), couponItems);
    // QMyCoupon myCoupon = myClient.getMyCoupon(user.getId(), myCouponId);
    // if (myCoupon == null) {
    // FrontAjaxView ajaxView = new FrontAjaxView();
    // ajaxView.setMessage("领取失败.");
    // ajaxView.addObject("price", 0);
    // ajaxView.addObject("date", "");
    // ajaxView.addObject("id", myCouponId);
    // return ajaxView;
    // }
    // FrontAjaxView ajaxView = new FrontAjaxView();
    // ajaxView.setMessage("领取成功");
    // ajaxView.addObject("price", myCoupon.getPrice());
    // ajaxView.addObject("date", DateUtil.date2String(myCoupon.getExtractDate(), DateUtil.DATE_FORMAT_STRING));
    // ajaxView.addObject("id", myCouponId);
    // return ajaxView;
    // }
    @PiratesApp
    @RequestMapping
    public FrontAjaxView extractCoupon(HttpServletRequest request, Long couponId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        Coupon coupon = couponService.get(couponId);
        Long myCouponId = couponService.extractCoupon(user.getId(), coupon);
        QMyCoupon myCoupon = myClient.getMyCoupon(user.getId(), myCouponId);
        if (myCoupon == null) {
            FrontAjaxView ajaxView = new FrontAjaxView();
            ajaxView.setMessage("领取失败.");
            ajaxView.addObject("price", 0);
            ajaxView.addObject("date", "");
            ajaxView.addObject("id", myCouponId);
            return ajaxView;
        }
        FrontAjaxView ajaxView = new FrontAjaxView();
        ajaxView.setMessage("领取成功");
        ajaxView.addObject("price", myCoupon.getPrice());
        ajaxView.addObject("date", DateUtil.date2String(myCoupon.getExtractDate(), DateUtil.DATE_FORMAT_STRING));
        ajaxView.addObject("id", myCouponId);
        return ajaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView canExtract(HttpServletRequest request, Long couponId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        Coupon coupon = couponService.get(couponId);
        boolean result = couponService.canExtract(user.getId(), coupon);
        //
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("判断用户是否可用领取优惠劵.");
        view.addObject("result", result);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView judgeExistAndCanExtract(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<Coupon> list = couponService.listActivityCoupon(-1L);
        boolean result = false;
        for (Coupon coupon : list) {
            if (couponService.canExtract(user.getId(), coupon)) {
                result = true;
                break;
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("判断优惠券存在并且可领.");
        view.addObject("result", result);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView judgeMerchantExistAndCanExtract(HttpServletRequest request, Long merchantId) {

        // AssertUtil.assertNotNull(merchantId, "商家ID不能为空");
        // AssertUtil.greatZero(merchantId, "商家ID必须大于0" + merchantId);
        merchantId = getMerchantClassify();
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<Coupon> list = couponService.listActivityCoupon(merchantId);
        boolean result = false;
        for (Coupon coupon : list) {
            if (couponService.canExtract(user.getId(), coupon)) {
                result = true;
                break;
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("判断优惠券存在并且可领.");
        view.addObject("result", result);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listActivityCoupon(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<Coupon> list = couponService.listActivityCoupon(-1L);
        List<CouponItemsVO> voList = new ArrayList<CouponItemsVO>();
        for (Coupon coupon : list) {
            if (couponService.canExtract(user.getId(), coupon)) {
                List<CouponItems> itemList = couponItemsService.list4Extract(coupon.getId());
                if (ListUtil.isNotEmpty(list)) {
                    CouponItemsVO itemVO = couponItemsHandler.toVO(itemList.get(0));
                    itemVO.setStartDateStr(DateUtil.date2String(coupon.getStartDate(), "yyyy-MM-dd"));
                    itemVO.setEndDateStr(DateUtil.date2String(coupon.getEndDate(), "yyyy-MM-dd"));
                    voList.add(couponItemsHandler.toVO(itemList.get(0)));
                }
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("优惠券可领列表.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listMerchantActivityCoupon(HttpServletRequest request, Long merchantId) {

        // AssertUtil.assertNotNull(merchantId, "商家ID不能为空");
        merchantId = getMerchantClassify();
        AssertUtil.greatZero(merchantId, "商家ID必须大于0" + merchantId);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<Coupon> list = couponService.listActivityCoupon(merchantId);
        List<CouponItemsVO> voList = new ArrayList<CouponItemsVO>();
        for (Coupon coupon : list) {
            if (coupon.getType() == CouponType.Normal.getKey()) {// 普通优惠券
                if (couponService.canExtract(user.getId(), coupon)) {
                    List<CouponItems> itemList = couponItemsService.list4Extract(coupon.getId());
                    if (ListUtil.isNotEmpty(list)) {
                        CouponItemsVO itemVO = couponItemsHandler.toVO(itemList.get(0));
                        itemVO.setStartDateStr(DateUtil.date2String(coupon.getStartDate(), "yyyy-MM-dd"));
                        itemVO.setEndDateStr(DateUtil.date2String(coupon.getEndDate(), "yyyy-MM-dd"));
                        voList.add(itemVO);
                    }
                }
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("优惠券可领列表.");
        view.addObject("list", voList);
        return view;
    }

    private Long getMerchantClassify() {

        Xml xml = XmlFactory.get("forest-merchant");
        AssertUtil.assertNotNull(xml, "大参林商家未配置.forest-merchant");
        List<XmlItem> itemList = xml.getItemList();
        for (XmlItem xmlItem : itemList) {
            if (xmlItem.getAttrMap().get("merchant") != null) {
                return Long.valueOf(String.valueOf(xmlItem.getAttrMap().get("merchant")));
            }
        }
        throw new MarketingException("大参林商家未配置.forest-merchant");
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listMerchantActivityIntegralCoupon(HttpServletRequest request, Long merchantId) {

        // TODO
        // AssertUtil.assertNotNull(merchantId, "商家ID不能为空");
        merchantId = getMerchantClassify();
        AssertUtil.greatZero(merchantId, "商家ID必须大于0" + merchantId);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<Coupon> list = couponService.listActivityCoupon(merchantId);
        List<CouponItemsVO> voList = new ArrayList<CouponItemsVO>();
        for (Coupon coupon : list) {
            if (coupon.getType() == CouponType.Integral.getKey()) {// 积分优惠券
                if (couponService.canExtract(user.getId(), coupon)) {
                    List<CouponItems> itemList = couponItemsService.list4Extract(coupon.getId());
                    if (ListUtil.isNotEmpty(list)) {
                        CouponItemsVO itemVO = couponItemsHandler.toIntegralVO(itemList.get(0));
                        itemVO.setStartDateStr(DateUtil.date2String(coupon.getStartDate(), "yyyy-MM-dd"));
                        itemVO.setEndDateStr(DateUtil.date2String(coupon.getEndDate(), "yyyy-MM-dd"));
                        voList.add(itemVO);
                    }
                }
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("积分优惠券可领列表.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView extractIntegralCoupon(HttpServletRequest request) {

        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("兑换优惠券成功.");
        return view;
    }
}
