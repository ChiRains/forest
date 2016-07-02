package com.qcloud.component.marketing.web.controller.admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.marketing.exception.MarketingException;
import com.qcloud.component.marketing.model.RecentDiscount;
import com.qcloud.component.marketing.model.key.TypeEnum;
import com.qcloud.component.marketing.model.key.TypeEnum.Enable;
import com.qcloud.component.marketing.model.query.RecentDiscountQuery;
import com.qcloud.component.marketing.service.RecentDiscountService;
import com.qcloud.component.marketing.web.handler.RecentDiscountHandler;
import com.qcloud.component.marketing.web.vo.admin.AdminRecentDiscountVO;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminRecentDiscountController.DIR)
public class AdminRecentDiscountController {

    public static final String    DIR = "admin/recentDiscount";

    @Autowired
    private RecentDiscountService recentDiscountService;

    @Autowired
    private RecentDiscountHandler recentDiscountHandler;

    // @Autowired
    // private AdminFilterService adminFilterService;
    //
    // @Autowired
    // private TokenClient tokenClient;
    // @Autowired
    // private OutdatedSellercenterClient outdatedSellercenterClient;
    @Autowired
    private FileSDKClient         fileSDKClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, RecentDiscountQuery query) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        query.setMerchantId(merchant.getId());
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<RecentDiscount> page = recentDiscountService.page(query, start, PAGE_SIZE);
        List<AdminRecentDiscountVO> list = recentDiscountHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/marketing-RecentDiscount-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/marketing-RecentDiscount-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(HttpServletRequest request, RecentDiscount recentDiscount) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        RecentDiscountQuery query = new RecentDiscountQuery();
        query.setMerchantId(merchant.getId());
        List<RecentDiscount> list = recentDiscountService.page(query, 0, Integer.MAX_VALUE).getData();
        for (RecentDiscount discount : list) {
            if (recentDiscount.getStartDate().before(discount.getEndDate()) && discount.getEnable() == Enable.ENABLE.getKey()) {
                throw new MarketingException("同一时间段只能开启一个近期优惠活动.");
            }
        }
        recentDiscount.setMerchantId(merchant.getId());
        recentDiscount.setEnable(TypeEnum.Enable.ENABLE.getKey());
        recentDiscount.setImage(fileSDKClient.uidToUrl(recentDiscount.getImage()));
        recentDiscountService.add(recentDiscount);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        RecentDiscount recentDiscount = recentDiscountService.get(id);
        AdminRecentDiscountVO adminRecentDiscountVO = recentDiscountHandler.toVO4Admin(recentDiscount);
        ModelAndView model = new ModelAndView("/admin/marketing-RecentDiscount-edit");
        model.addObject("recentDiscount", adminRecentDiscountVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(HttpServletRequest request, RecentDiscount recentDiscount) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        AssertUtil.assertTrue(merchant.getId() == recentDiscount.getMerchantId(), "只能修改自己的活动." + recentDiscount.getId());
        RecentDiscountQuery query = new RecentDiscountQuery();
        query.setMerchantId(merchant.getId());
        List<RecentDiscount> list = recentDiscountService.page(query, 0, Integer.MAX_VALUE).getData();
        for (RecentDiscount discount : list) {
            if (recentDiscount.getStartDate().before(discount.getEndDate()) && discount.getEnable() == Enable.ENABLE.getKey() && discount.getId() != recentDiscount.getId()) {
                throw new MarketingException("同一时间段只能开启一个近期优惠活动.");
            }
        }
        recentDiscount.setImage(fileSDKClient.uidToUrl(recentDiscount.getImage()));
        recentDiscountService.update(recentDiscount);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        recentDiscountService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView enable(HttpServletRequest request, Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        RecentDiscount recentDiscount = recentDiscountService.get(id);
        AssertUtil.assertNotNull(id, "活动不存在." + id);
        //
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        AssertUtil.assertTrue(merchant.getId() == recentDiscount.getMerchantId(), "只能修改自己的活动." + recentDiscount.getId());
        RecentDiscountQuery query = new RecentDiscountQuery();
        query.setMerchantId(merchant.getId());
        List<RecentDiscount> list = recentDiscountService.page(query, 0, Integer.MAX_VALUE).getData();
        for (RecentDiscount discount : list) {
            if (recentDiscount.getStartDate().before(discount.getEndDate()) && discount.getEnable() == Enable.ENABLE.getKey() && discount.getId() != recentDiscount.getId()) {
                throw new MarketingException("同一时间段只能开启一个近期优惠活动.");
            }
        }
        //
        recentDiscount.setEnable(TypeEnum.Enable.ENABLE.getKey());
        recentDiscountService.update(recentDiscount);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("修改成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView disable(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        RecentDiscount recentDiscount = recentDiscountService.get(id);
        AssertUtil.assertNotNull(id, "活动不存在." + id);
        recentDiscount.setEnable(TypeEnum.Enable.DISABLE.getKey());
        recentDiscountService.update(recentDiscount);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("修改成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
    // public QMerchant getMerchant(HttpServletRequest request) {
    //
    // String tokenId = adminFilterService.getTokenId(request);
    // AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
    // String idStr = tokenClient.get(tokenId);
    // AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
    // long memberId = Long.parseLong(idStr);
    // List<QMerchant> merchantList = outdatedSellercenterClient.listMerchant(memberId);
    // QMerchant merchant = null;
    // if (!CollectionUtils.isEmpty(merchantList) && merchantList.size() == 1) {
    // merchant = merchantList.get(0);
    // }
    // if (!CollectionUtils.isEmpty(merchantList) && merchantList.size() > 1) {
    // throw new SellerCenterException("商家职员只能在职一个商家");
    // }
    // AssertUtil.assertNotNull(merchant, "您尚未属于一家商家.");
    // return merchant;
    // }
}
