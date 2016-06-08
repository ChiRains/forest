package com.qcloud.project.forest.web.controller.admin;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.GiftCoupon;
import com.qcloud.project.forest.model.query.GiftCouponQuery;
import com.qcloud.project.forest.service.GiftCouponService;
import com.qcloud.project.forest.web.handler.GiftCouponHandler;
import com.qcloud.project.forest.web.vo.admin.AdminGiftCouponVO;

@Controller
@RequestMapping(value = "/" + AdminGiftCouponController.DIR)
public class AdminGiftCouponController {

    public static final String DIR = "admin/giftCoupon";

    @Autowired
    private GiftCouponService  giftCouponService;

    @Autowired
    private GiftCouponHandler  giftCouponHandler;

    @Autowired
    private FileSDKClient      fileSDKClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, GiftCouponQuery query) {

        Page<GiftCoupon> page = giftCouponService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminGiftCouponVO> list = giftCouponHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/forest-GiftCoupon-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/forest-GiftCoupon-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(GiftCoupon giftCoupon) {

        String url = fileSDKClient.uidToUrl(giftCoupon.getImage());
        giftCoupon.setImage(url);
        giftCouponService.add(giftCoupon);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        GiftCoupon giftCoupon = giftCouponService.get(id);
        AdminGiftCouponVO adminGiftCouponVO = giftCouponHandler.toVO4Admin(giftCoupon);
        ModelAndView model = new ModelAndView("/admin/forest-GiftCoupon-edit");
        model.addObject("giftCoupon", adminGiftCouponVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(GiftCoupon giftCoupon, String queryStr) {

        GiftCoupon giftCoupon2 = giftCouponService.get(giftCoupon.getId());
        if (!giftCoupon.getImage().equals(fileSDKClient.getFileServerUrl() + giftCoupon2.getImage())) {
            String url = fileSDKClient.uidToUrl(giftCoupon.getImage());
            giftCoupon.setImage(url);
        }
        giftCouponService.update(giftCoupon);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        giftCouponService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView enable(Long id, int enable) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        GiftCoupon giftCoupon = giftCouponService.get(id);
        giftCoupon.setEnable(enable);
        giftCouponService.update(giftCoupon);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("启用成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
