package com.qcloud.project.forest.web.controller;

import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.project.forest.model.GiftCoupon;
import com.qcloud.project.forest.model.GiftCouponUser;
import com.qcloud.project.forest.model.query.GiftCouponUserQuery;
import com.qcloud.project.forest.service.GiftCouponService;
import com.qcloud.project.forest.service.GiftCouponUserService;
import com.qcloud.project.forest.web.handler.GiftCouponHandler;
import com.qcloud.project.forest.web.handler.GiftCouponUserHandler;
import com.qcloud.project.forest.web.vo.GiftCouponUserVO;
import com.qcloud.project.forest.web.vo.GiftCouponVO;

@Controller
@RequestMapping(value = GiftCouponUserController.DIR)
public class GiftCouponUserController {

    public static final String    DIR = "/giftCouponUser";

    @Autowired
    private GiftCouponUserService giftCouponUserService;

    @Autowired
    private GiftCouponUserHandler giftCouponUserHandler;

    @Autowired
    private GiftCouponService     giftCouponService;

    @Autowired
    private GiftCouponHandler     giftCouponHandler;

    /**
     * 获取当前用户的赠品券列表
     * @param request
     * @param pPage
     * @return
     */
    @RequestMapping
    public FrontPagingView myGiftCoupon(HttpServletRequest request, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        GiftCouponUserQuery giftCouponUserQuery = new GiftCouponUserQuery();
        giftCouponUserQuery.setUserId(user.getId());
        Page<GiftCouponUser> page = giftCouponUserService.page(giftCouponUserQuery, pPage.getPageStart(), pPage.getPageSize());
        List<GiftCouponUserVO> list = giftCouponUserHandler.toVOList(page.getData());
        GiftCoupon giftCoupon = null;
        GiftCouponUserVO giftCouponUserVO = null;
        Iterator<GiftCouponUserVO> iter = list.iterator();
        while (iter.hasNext()) {
            giftCouponUserVO = iter.next();
            giftCoupon = giftCouponService.get(giftCouponUserVO.getId());
            if (giftCoupon.getEnable() == 0) {
                iter.remove();
            }
        }
        FrontPagingView frontPagingView = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        frontPagingView.setList(list);
        return frontPagingView;
    }

    /**
     * 获取赠品券详情
     * @param giftCouponId
     * @return
     */
    @RequestMapping
    public FrontAjaxView get(Long giftCouponId) {

        GiftCoupon giftCoupon = giftCouponService.get(giftCouponId);
        GiftCouponVO giftCouponVO = giftCouponHandler.toVO(giftCoupon);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("result", giftCouponVO);
        return frontAjaxView;
    }
}
