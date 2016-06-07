package com.qcloud.component.sellercenter.web.controller.admin;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.QStore;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.exception.SellerCenterException;
import com.qcloud.component.sellercenter.model.DistributeMembershipCard;
import com.qcloud.component.sellercenter.model.DistributeMembershipCardStat;
import com.qcloud.component.sellercenter.model.Merchant;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardQuery;
import com.qcloud.component.sellercenter.model.query.DistributeMembershipCardStatQuery;
import com.qcloud.component.sellercenter.service.DistributeMembershipCardService;
import com.qcloud.component.sellercenter.service.MerchantService;
import com.qcloud.component.sellercenter.web.handler.DistributeMembershipCardHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminDistributeMembershipCardVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminDistributeMembershipCardController.DIR)
public class AdminDistributeMembershipCardController {

    public static final String              DIR = "admin/distributeMembershipCard";

    @Autowired
    private DistributeMembershipCardService distributeMembershipCardService;

    @Autowired
    private DistributeMembershipCardHandler distributeMembershipCardHandler;

    @Autowired
    private MerchantService                 merchantService;

    @Autowired
    private PersonalcenterClient            personalcenterClient;

    @Autowired
    private SellercenterClient              sellercenterClient;

    @RequestMapping
    @NoReferer
    public ModelAndView stat(Integer pageNum, DistributeMembershipCardStatQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<DistributeMembershipCardStat> page = distributeMembershipCardService.statPage(query, start, PAGE_SIZE);
        List<DistributeMembershipCardStat> list = page.getData();
        for (DistributeMembershipCardStat distributeMembershipCardStat : list) {
            int sendedNum = distributeMembershipCardService.countMerchantSended(distributeMembershipCardStat.getMerchantId());
            distributeMembershipCardStat.setSendedNum(sendedNum);
        }
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-DistributeMembershipCard-stat", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView listInMerchant(Integer pageNum, Long merchantId) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<DistributeMembershipCard> page = distributeMembershipCardService.page(merchantId, start, PAGE_SIZE);
        List<AdminDistributeMembershipCardVO> list = distributeMembershipCardHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-DistributeMembershipCard-listInMerchant", DIR + "/listInMerchant?merchantId=" + merchantId, pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, DistributeMembershipCardQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<DistributeMembershipCard> page = distributeMembershipCardService.page(query, start, PAGE_SIZE);
        List<AdminDistributeMembershipCardVO> list = distributeMembershipCardHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-DistributeMembershipCard-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toInitDistribute() {

        ModelAndView model = new ModelAndView("/admin/sellercenter-DistributeMembershipCard-initDistribute");
        return model;
    }

    @RequestMapping
    public ModelAndView initDistribute(String merchantCode, String startNumberStr, String endNumberStr) {

        Merchant merchant = merchantService.getByCode(merchantCode);
        AssertUtil.assertNotNull(merchant, "商家不存在." + merchantCode);
        AssertUtil.assertNotNull(startNumberStr, "开始号段不能为空");
        AssertUtil.assertNotNull(endNumberStr, "结束号段不能为空");
        AssertUtil.assertTrue(startNumberStr.length() == endNumberStr.length(), "卡号号段必须长度相等.");
        Long start = Long.valueOf(startNumberStr);
        Long end = Long.valueOf(endNumberStr);
        int length = startNumberStr.length();
        AssertUtil.assertTrue(start <= end, "开始号段必须小于结束号段");
        for (Long index = start; index <= end; index++) {
            String cardNumber = StringUtils.leftPad(String.valueOf(index), length, "0");
            boolean exist = personalcenterClient.existAndUselessMembershipCard(cardNumber);
            AssertUtil.assertTrue(exist, "会员卡不存在或者已经使用." + cardNumber);
            DistributeMembershipCard distributeMembershipCard = distributeMembershipCardService.getByCardNumber(cardNumber);
            if (distributeMembershipCard != null) {
                throw new SellerCenterException("会员卡号(" + cardNumber + ")已经派给商家." + getMerchantName(distributeMembershipCard.getMerchantId()));
            }
        }
        distributeMembershipCardService.initDistribute(merchant.getId(), startNumberStr, endNumberStr);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("给商家派卡成功");
        aceAjaxView.setUrl(DIR + "/stat");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/sellercenter-DistributeMembershipCard-add");
        return model;
    }

    private String getMerchantName(Long merchantId) {

        QMerchant merchant = sellercenterClient.getMerchant(merchantId);
        if (merchant == null) {
            return "";
        }
        return merchant.getName();
    }

    @RequestMapping
    public AceAjaxView add(DistributeMembershipCard distributeMembershipCard) {

        distributeMembershipCardService.add(distributeMembershipCard);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        DistributeMembershipCard distributeMembershipCard = distributeMembershipCardService.get(id);
        AdminDistributeMembershipCardVO adminDistributeMembershipCardVO = distributeMembershipCardHandler.toVO4Admin(distributeMembershipCard);
        ModelAndView model = new ModelAndView("/admin/sellercenter-DistributeMembershipCard-edit");
        model.addObject("distributeMembershipCard", adminDistributeMembershipCardVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(DistributeMembershipCard distributeMembershipCard) {

        distributeMembershipCardService.update(distributeMembershipCard);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        distributeMembershipCardService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
