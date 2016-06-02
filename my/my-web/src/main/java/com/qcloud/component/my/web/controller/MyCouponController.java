package com.qcloud.component.my.web.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.model.MyCoupon;
import com.qcloud.component.my.model.key.TypeEnum.CouponStateType;
import com.qcloud.component.my.service.MyCouponService;
import com.qcloud.component.my.web.handler.MyCouponHandler;
import com.qcloud.component.my.web.vo.MyCouponVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;

@Controller
@RequestMapping(value = MyCouponController.DIR)
public class MyCouponController {

    public static final String   DIR = "/myCoupon";

    @Autowired
    private MyCouponService      myCouponService;

    @Autowired
    private MyCouponHandler      myCouponHandler;

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @RequestMapping
    public FrontPagingView page(HttpServletRequest request, Integer type, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyCoupon> list = myCouponService.listByUser(user.getId(), type, pPage.getPageStart(), pPage.getPageSize());
        List<MyCouponVO> voList = myCouponHandler.toVOList(list);
        int number = myCouponService.countByUser(user.getId(), type);
        FrontPagingView view = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), number);
        view.setMessage("获取我的优惠劵成功.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView list(HttpServletRequest request, Integer type, PPage pPage) {

        type = type == null ? 0 : type;
        // 0所有 ,1未使用 ,2已使用 , 3已过期
        AssertUtil.assertTrue(type == 0 || type == 1 || type == 2 || type == 3, "查询我的优惠劵类型取值范围不对." + type);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyCoupon> list = myCouponService.listByUser(user.getId(), type, pPage.getPageStart(), pPage.getPageSize());
        List<MyCouponVO> voList = myCouponHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的优惠劵成功.");
        view.addObject("list", voList);
        return view;
    }

    @RequestMapping
    public FrontAjaxView count(HttpServletRequest request, Integer type) {

        type = type == null ? 0 : type;
        // 0所有 ,1未使用 ,2已使用 , 3已过期
        AssertUtil.assertTrue(type == 0 || type == 1 || type == 2 || type == 3, "查询我的优惠劵类型取值范围不对." + type);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        int number = myCouponService.countByUser(user.getId(), type);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的优惠劵成功.");
        view.addObject("count", number);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listCanUse(HttpServletRequest request, Long merchantId, PPage pPage, Double sum) {

        AssertUtil.assertNotNull(sum, "订单金额不能为空");
        AssertUtil.assertNotNull(sum < 0, "订单金额不能小于零");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MyCoupon> list = myCouponService.listCanUseByUser(user.getId(), merchantId, sum, pPage.getPageStart(), pPage.getPageSize());
        List<MyCouponVO> voList = myCouponHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的可用优惠劵成功.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getByCode(String cardNumber, String code) {

        AssertUtil.assertNotEmpty(code, "电子优惠劵码不能为空.");
        AssertUtil.assertNotNull(cardNumber, "会员卡号不能为空.");
        QUser user = personalcenterClient.getUser(cardNumber);
        AssertUtil.assertNotNull(user, "会员卡尚未发给用户." + cardNumber);
        MyCoupon myCoupon = myCouponService.getByCode(code);
        AssertUtil.assertNotNull(myCoupon, "电子优惠券不存在." + code);
        AssertUtil.assertTrue(user.getId() == myCoupon.getUserId(), "优惠劵不属于指定会员");
        AssertUtil.assertTrue(myCoupon.getState() == CouponStateType.NOTUSE.getKey(), "优惠劵已经使用." + code);
        AssertUtil.assertTrue(myCoupon.getValidDate().after(new Date()), "优惠劵已过期.");
        MyCouponVO myCouponVO = myCouponHandler.toVO(myCoupon);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取优惠劵成功.");
        view.addObject("coupon", myCouponVO);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView delete(HttpServletRequest request, Long id) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MyCoupon myCoupon = myCouponService.get(id);
        AssertUtil.assertNotNull(myCoupon, "优惠劵不存在" + id);
        AssertUtil.assertTrue(user.getId() == myCoupon.getUserId(), "您不能删除别人的优惠劵.");
        myCoupon.setState(CouponStateType.DELETE.getKey());
        myCouponService.update(myCoupon);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("删除成功.");
        return view;
    }
}
