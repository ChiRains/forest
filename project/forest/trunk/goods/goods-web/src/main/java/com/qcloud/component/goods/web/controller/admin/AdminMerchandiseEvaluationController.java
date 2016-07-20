package com.qcloud.component.goods.web.controller.admin;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.goods.exception.CommoditycenterException;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.query.MerchandiseEvaluationQuery;
import com.qcloud.component.goods.service.MerchandiseEvaluationService;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.form.MerchandiseEvaluationDetailForm;
import com.qcloud.component.goods.web.form.MerchandiseEvaluationFormList;
import com.qcloud.component.goods.web.handler.MerchandiseEvaluationHandler;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseEvaluationVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.key.TypeEnum.StatusType;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMerchandiseEvaluationController.DIR)
public class AdminMerchandiseEvaluationController {

    public static final String               DIR = "admin/merchandiseEvaluation";

    @Autowired
    private MerchandiseEvaluationService     merchandiseEvaluationService;

    @Autowired
    private MerchandiseEvaluationHandler     merchandiseEvaluationHandler;

    @Autowired
    private MerchandiseService               merchandiseService;

    @Autowired
    private PersonalcenterClient             personalcenterClient;

    // @Autowired
    // private OutdatedSellercenterClient outdatedSellercenterClient;
    //
    // @Autowired
    // private PublicdataClient publicdataClient;
    //
    // @Autowired
    // private ClassifySpecificationsService classifySpecificationsService;
    //
    // @Autowired
    // private AttributeDefinitionService attributeDefinitionService;
    //
    // @Autowired
    // private MerchandiseSpecificationsRelationService merchandiseSpecificationsRelationService;
    @Autowired
    private SellercenterClient               sellercenterClient;

    @Autowired
    private MerchandiseSpecificationsService merchandiseSpecificationsService;

    @Autowired
    private UnifiedMerchandiseService        unifiedMerchandiseService;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, MerchandiseEvaluationQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<MerchandiseEvaluation> page = merchandiseEvaluationService.page(query, start, PAGE_SIZE);
        List<AdminMerchandiseEvaluationVO> list = merchandiseEvaluationHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/goods-MerchandiseEvaluation-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("statusType", StatusType.values());
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd(Long merchandiseId) {

        ModelAndView model = new ModelAndView("/admin/goods-MerchandiseEvaluation-add");
        List<UnifiedMerchandise> list = unifiedMerchandiseService.listByMerchandise(merchandiseId, UnifiedMerchandiseType.SINGLE.getKey());
        Map<Long, String> map = new LinkedHashMap<Long, String>();
        for (UnifiedMerchandise unifiedMerchandise : list) {
            List<MerchandiseSpecifications> merchandiseSpecificationss = merchandiseSpecificationsService.listByUnifiedMerchandise(unifiedMerchandise.getId());
            StringBuffer sb = new StringBuffer();
            for (MerchandiseSpecifications merchandiseSpecifications : merchandiseSpecificationss) {
                sb.append(merchandiseSpecifications.getValue()).append(" - ");
            }
            map.put(unifiedMerchandise.getId(), sb.toString());
        }
        model.addObject("merchandiseItemMap", map);
        model.addObject("merchandiseId", merchandiseId);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(MerchandiseEvaluationDetailForm merchandiseEvaluationDetailForm) {

        if (merchandiseEvaluationDetailForm.getStar() > 5 || merchandiseEvaluationDetailForm.getStar() <= 0) {
            throw new CommoditycenterException("星级只能输入1-5范围!");
        }
        String account = String.valueOf(merchandiseEvaluationDetailForm.getAccount());
        QUser user = personalcenterClient.getUser(account);
        AssertUtil.assertNotNull(user, "帐号不存在." + account);
        MerchandiseEvaluation merchandiseEvaluation = new MerchandiseEvaluation();
        merchandiseEvaluation.setMerchandiseId(merchandiseEvaluationDetailForm.getMerchandiseId());
        merchandiseEvaluation.setContent(merchandiseEvaluationDetailForm.getContent());
        merchandiseEvaluation.setStar(merchandiseEvaluationDetailForm.getStar() * 10);
        //
        merchandiseEvaluation.setUserId(user.getId());
        merchandiseEvaluation.setStatus(StatusType.PASS.getKey());
        merchandiseEvaluation.setTime(DateUtils.setSeconds(merchandiseEvaluationDetailForm.getTime(), (new Date()).getSeconds()));
        //
        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(merchandiseEvaluationDetailForm.getMerchandiseItemId());
        // MerchandiseItem merchandiseItem = merchandiseItemService.get(merchandiseEvaluationDetailForm.getMerchandiseItemId());
        AssertUtil.assertNotNull(unifiedMerchandise, "统一商品不存在." + merchandiseEvaluationDetailForm.getMerchandiseItemId());
        List<MerchandiseSpecifications> merchandiseSpecificationss = merchandiseSpecificationsService.listByUnifiedMerchandise(unifiedMerchandise.getId());
        StringBuffer sb = new StringBuffer();
        for (MerchandiseSpecifications merchandiseSpecifications : merchandiseSpecificationss) {
            sb.append(merchandiseSpecifications.getValue()).append(" - ");
        }
        //
        merchandiseEvaluation.setSpecifications(sb.toString());
        merchandiseEvaluation.setAnonymous(EnableType.DISABLE.getKey());
        merchandiseEvaluation.setUnifiedMerchandiseId(unifiedMerchandise.getId());
        boolean flag = merchandiseEvaluationService.add(merchandiseEvaluation);
        AssertUtil.assertTrue(flag, "评价失败.");
        // 更新单一商品好中差评
        if (merchandiseEvaluationDetailForm.getStar() > 0 && merchandiseEvaluationDetailForm.getStar() <= 2) {
            unifiedMerchandise.setLowEvaluation(unifiedMerchandise.getLowEvaluation() + 1);
        } else if (merchandiseEvaluationDetailForm.getStar() == 3) {
            unifiedMerchandise.setMiddleEvaluation(unifiedMerchandise.getMiddleEvaluation() + 1);
        } else if (merchandiseEvaluationDetailForm.getStar() > 3) {
            unifiedMerchandise.setGoodEvaluation(unifiedMerchandise.getGoodEvaluation() + 1);
        }
        unifiedMerchandiseService.update(unifiedMerchandise);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list?merchandiseId=" + merchandiseEvaluation.getMerchandiseId());
        return aceAjaxView;
    }

    //
    // @RequestMapping
    // public ModelAndView toEdit(Long id) {
    // AssertUtil.assertNotNull(id, "ID不能为空");
    // ModelAndView model = new ModelAndView("/admin/evaluationcenter-MerchandiseEvaluation-edit");
    // return model;
    // }
    //
    // @RequestMapping
    // public AceAjaxView edit(MerchandiseEvaluation merchandiseEvaluation) {
    // merchandiseEvaluationService.update(merchandiseEvaluation);
    // AceAjaxView aceAjaxView = new AceAjaxView();
    // aceAjaxView.setMessage("编辑成功");
    // aceAjaxView.setUrl(DIR + "/list");
    // return aceAjaxView;
    // }
    //
    @RequestMapping
    public AceAjaxView delete(HttpServletRequest request, Long id, Long merchandiseId) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        // MerchantEvaluation merchantEvaluation = outdatedSellercenterClient.getMerchantEvaluation(id, merchant.getId());
        // if (merchantEvaluation != null) {
        // outdatedSellercenterClient.deleteMerchantEvaluation(id, merchant.getId());
        // }
        sellercenterClient.deleteMerchantEvaluation(id, merchant.getId());
        merchandiseEvaluationService.delete(id, merchandiseId);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    // @Autowired
    // private TokenClient tokenClient;
    //
    // @Autowired
    // private AdminFilterService adminFilterService;
    //
    // @Autowired
    // private SellercenterClient sellercenterClient;
    /**
     * 审核商品评论
     * @param request
     * @param me
     * @return
     */
    @RequestMapping
    public AceAjaxView agree(HttpServletRequest request, MerchandiseEvaluationFormList form) {

        AssertUtil.assertNotNull(form.getEvaluations(), "审核评价列表不能为空.");
        AssertUtil.assertNotNull(form.getMerchandises(), "审核商品评价列表不能为空.");
        AssertUtil.assertTrue(form.getEvaluations().size() == form.getMerchandises().size(), "ID长度不相等");
        for (int index = 0; index < form.getEvaluations().size(); index++) {
            Long id = form.getEvaluations().get(index);
            Long merchandiseId = form.getMerchandises().get(index);
            if (id == null || id == 0 || merchandiseId == null || merchandiseId == 0) {
                continue;
            }
            MerchandiseEvaluation merchandiseEvaluation = merchandiseEvaluationService.get(id, merchandiseId);
            if (merchandiseEvaluation.getStatus() != StatusType.UNDO.getKey()) {
                throw new CommoditycenterException("当前评论已经审核完成." + merchandiseEvaluation.getContent());
            }
            merchandiseEvaluation.setStatus(StatusType.PASS.getKey());
            merchandiseEvaluationService.synUnifiedMerchandiseEvaluation(merchandiseEvaluation);
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("审核完成");
        return aceAjaxView;
    }

    /**
    * 审核商品评论
    * @param request
    * @param me
    * @return
    */
    @RequestMapping
    public AceAjaxView disagree(HttpServletRequest request, MerchandiseEvaluationFormList form) {

        AssertUtil.assertNotNull(form.getEvaluations(), "审核评价列表不能为空.");
        AssertUtil.assertNotNull(form.getMerchandises(), "审核商品评价列表不能为空.");
        AssertUtil.assertTrue(form.getEvaluations().size() == form.getMerchandises().size(), "ID长度不相等");
        for (int index = 0; index < form.getEvaluations().size(); index++) {
            Long id = form.getEvaluations().get(index);
            Long merchandiseId = form.getMerchandises().get(index);
            if (id == null || id == 0 || merchandiseId == null || merchandiseId == 0) {
                continue;
            }
            MerchandiseEvaluation merchandiseEvaluation = merchandiseEvaluationService.get(id, merchandiseId);
            if (merchandiseEvaluation.getStatus() != StatusType.UNDO.getKey()) {
                throw new CommoditycenterException("当前评论已经审核完成." + merchandiseEvaluation.getContent());
            }
            merchandiseEvaluation.setStatus(StatusType.UNPASS.getKey());
            merchandiseEvaluationService.update(merchandiseEvaluation);
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("审核完成");
        return aceAjaxView;
    }

    /**
     * 审核商品评论
     * @param request
     * @param me
     * @return
     */
    @RequestMapping
    public AceAjaxView edit(HttpServletRequest request, MerchandiseEvaluation me) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        // String tokenId = adminFilterService.getTokenId(request);
        // AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
        // String idStr = tokenClient.get(tokenId);
        // AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
        // // 根据登录用户获取对应商家
        // List<QMerchant> merchantMembers = sellercenterClient.listMerchant(Long.valueOf(idStr));
        // add code 判断是否此商品是否属于该商家
        Merchandise merchandise = merchandiseService.get(me.getMerchandiseId());
        if (merchant.getId() != merchandise.getMerchantId()) {
            throw new CommoditycenterException("该商品不属于你!");
        }
        MerchandiseEvaluation merchandiseEvaluation = merchandiseEvaluationService.get(me.getId(), me.getMerchandiseId());
        if (merchandiseEvaluation.getStatus() != StatusType.UNDO.getKey()) {
            throw new CommoditycenterException("当前评论已经审核完成." + merchandiseEvaluation.getContent());
        }
        merchandiseEvaluation.setStatus(me.getStatus());
        merchandiseEvaluationService.synUnifiedMerchandiseEvaluation(merchandiseEvaluation);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl("admin/merchantEvaluation/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toReplyContent(Long evaluationId, Long merchandiseId) {

        AssertUtil.greatZero(evaluationId, "评价id不能为空.");
        AssertUtil.greatZero(merchandiseId, "商品id不能为空.");
        ModelAndView model = new ModelAndView("/admin/goods-MerchandiseEvaluation-replyContent");
        MerchandiseEvaluation merchandiseEvaluation = merchandiseEvaluationService.get(evaluationId, merchandiseId);
        AssertUtil.assertNotNull(merchandiseEvaluation, "商品评价不存在.");
        model.addObject("merchandiseEvaluation", merchandiseEvaluation);
        return model;
    }

    @RequestMapping
    public AceAjaxView replyContent(Long id, Long merchandiseId, String content, String alreadyContent) {

        AssertUtil.greatZero(id, "评价id不能为空.");
        AssertUtil.assertTrue(StringUtils.isEmpty(alreadyContent), "该评论已回复,请勿重复操作.");
        AssertUtil.assertTrue(!StringUtils.isEmpty(content), "内容不能为空.");
        AceAjaxView aceAjaxView = new AceAjaxView();
        MerchandiseEvaluation merchandiseEvaluation = merchandiseEvaluationService.get(id, merchandiseId);
        merchandiseEvaluation.setReplyContent(content);
        merchandiseEvaluationService.update(merchandiseEvaluation);
        aceAjaxView.setMessage("客服回复成功.");
        return aceAjaxView;
    }
}
