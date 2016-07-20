package com.qcloud.component.sellercenter.web.controller.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.MerchantEvaluation;
import com.qcloud.component.sellercenter.model.key.TypeEnum.StatusType;
import com.qcloud.component.sellercenter.model.query.MerchantEvaluationQuery;
import com.qcloud.component.sellercenter.service.MerchantEvaluationService;
import com.qcloud.component.sellercenter.web.handler.MerchantEvaluationHandler;
import com.qcloud.component.sellercenter.web.vo.admin.AdminMerchantEvaluationVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMerchantEvaluationController.DIR)
public class AdminMerchantEvaluationController {

    public static final String        DIR = "admin/merchantEvaluation";

    @Autowired
    private MerchantEvaluationService merchantEvaluationService;

    @Autowired
    private MerchantEvaluationHandler merchantEvaluationHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, MerchantEvaluationQuery query) {

        final int PAGE_SIZE = 10;
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        query.setMerchantId(merchant.getId());
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<MerchantEvaluation> page = merchantEvaluationService.page(query, start, PAGE_SIZE);
        List<AdminMerchantEvaluationVO> voList = new ArrayList<AdminMerchantEvaluationVO>();
        // 获取商品档案id,评价表id集合,obj[0]=评价表id, obj[1]=商品档案id
        for (MerchantEvaluation merchantEvaluation : page.getData()) {
            voList.add(merchantEvaluationHandler.toVO4Admin(merchantEvaluation));
        }
        String time = null;
        if (DateUtil.date2String(query.getTime(), DateUtil.DATE_FORMAT_STRING) != null) {
            time = "time=" + DateUtil.date2String(query.getTime(), DateUtil.DATE_FORMAT_STRING);
        }
        if (query.getStatus() != 0) {
            Iterator<AdminMerchantEvaluationVO> iter = voList.iterator();
            while (iter.hasNext()) {
                AdminMerchantEvaluationVO s = iter.next();
                if (s.getStatus() != query.getStatus()) {
                    iter.remove();
                }
            }
        }
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-MerchantEvaluation-list", DIR + "/list?" + time, pageNum, PAGE_SIZE, voList.size());
        pagingView.addObject("result", voList);
        pagingView.addObject("query", query);
        pagingView.addObject("statusType", StatusType.values());
        return pagingView;
    }
    // @RequestMapping
    // public ModelAndView toAdd() {
    //
    // ModelAndView model = new ModelAndView("/admin/evaluationcenter-MerchantEvaluation-add");
    // return model;
    // }
    //
    // @RequestMapping
    // public AceAjaxView add(MerchantEvaluation merchantEvaluation) {
    //
    // merchantEvaluationService.add(merchantEvaluation);
    // AceAjaxView aceAjaxView = new AceAjaxView();
    // aceAjaxView.setMessage("添加成功");
    // aceAjaxView.setUrl(DIR + "/list");
    // return aceAjaxView;
    // }
    //
    // @RequestMapping
    // public ModelAndView toEdit(Long id) {
    //
    // AssertUtil.assertNotNull(id, "ID不能为空");
    // MerchantEvaluation merchantEvaluation = merchantEvaluationService.get(id);
    // AdminMerchantEvaluationVO adminMerchantEvaluationVO = merchantEvaluationHandler.toVO4Admin(merchantEvaluation);
    // ModelAndView model = new ModelAndView("/admin/evaluationcenter-MerchantEvaluation-edit");
    // model.addObject("merchantEvaluation", adminMerchantEvaluationVO);
    // return model;
    // }
    // /**
    // * 审核商品评论
    // * @param request
    // * @param me
    // * @return
    // */
    // @RequestMapping
    // public AceAjaxView edit(HttpServletRequest request, MerchandiseEvaluation me) {
    //
    // String tokenId = adminFilterService.getTokenId(request);
    // AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
    // String idStr = tokenClient.get(tokenId);
    // AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
    // // 根据登录用户获取对应商家
    // List<QMerchant> merchantMembers = sellercenterClient.listMerchant(Long.valueOf(idStr));
    // // add code 判断是否此商品是否属于该商家
    // Merchandise merchandise = merchandiseService.get(me.getMerchandiseId());
    // if (!merchantMembers.contains(merchandise.getMerchantId())) {
    // throw new EvaluationcenterException("该商品不属于你!");
    // }
    // MerchandiseEvaluation merchandiseEvaluation = merchandiseEvaluationService.get(me.getId(), me.getMerchandiseId());
    // merchandiseEvaluation.setStatus(me.getStatus());
    // merchandiseEvaluationService.update(merchandiseEvaluation);
    // AceAjaxView aceAjaxView = new AceAjaxView();
    // aceAjaxView.setMessage("编辑成功");
    // aceAjaxView.setUrl(DIR + "/list");
    // return aceAjaxView;
    // }
    // @RequestMapping
    // public AceAjaxView delete(Long id) {
    //
    // AssertUtil.assertNotNull(id, "ID不能为空");
    // merchantEvaluationService.delete(id);
    // AceAjaxView aceAjaxView = new AceAjaxView();
    // aceAjaxView.setMessage("删除成功");
    // aceAjaxView.setUrl(DIR + "/list");
    // return aceAjaxView;
    // }
}
