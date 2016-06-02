package com.qcloud.component.commoditycenter.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.commoditycenter.model.MerchandiseEvaluation;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.StarLevelType;
import com.qcloud.component.commoditycenter.service.MerchandiseEvaluationService;
import com.qcloud.component.commoditycenter.web.form.EvaluationForm;
import com.qcloud.component.commoditycenter.web.form.MerchandiseEvaluationForm;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseEvaluationHandler;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseEvaluationVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = MerchandiseEvaluationController.DIR)
public class MerchandiseEvaluationController {

    public static final String           DIR = "/merchandiseEvaluation";

    @Autowired
    private MerchandiseEvaluationService merchandiseEvaluationService;

    @Autowired
    private MerchandiseEvaluationHandler merchandiseEvaluationHandler;

    /**
    * 商品显示评价
    * @param pageNum
    * @param merchandiseId
    * @return
    */
    @RequestMapping
    @NoReferer
    public FrontPagingView list(Integer pageNum, Integer pageSize, Long merchandiseId, Integer type) {

        type = type == null ? 0 : type;
        StarLevelType t = StarLevelType.get(type);
        AssertUtil.assertNotNull(merchandiseId, "商品ID不能为空.");
        AssertUtil.assertTrue(merchandiseId > 0, "商品ID值不正确." + merchandiseId);
        final int PAGE_SIZE = pageSize == null || pageSize <= 0 ? 10 : pageSize;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<MerchandiseEvaluation> page = merchandiseEvaluationService.page(merchandiseId, t, start, PAGE_SIZE);
        List<MerchandiseEvaluationVO> voList = merchandiseEvaluationHandler.toVOList(page.getData());
        FrontPagingView view = new FrontPagingView(pageNum, PAGE_SIZE, page.getCount());
        view.addObject("list", voList);
        return view;
    }

    /**
     * 评价商品
     * @param vo  
     * @return
     */
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView evaluate(HttpServletRequest request, EvaluationForm evaluationForm) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MerchandiseEvaluationForm> list = evaluationForm.getMerchandiseEvaluations();
        for (MerchandiseEvaluationForm merchandiseEvaluationForm : list) {
            merchandiseEvaluationService.evaluate(merchandiseEvaluationForm.getToEvaluationId(), user.getId(), merchandiseEvaluationForm.getContent(), getRealStar(merchandiseEvaluationForm.getStar()));
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("评价成功!");
        return view;
    }

    private int getRealStar(int star) {

        switch (star) {
        case 1:
            star = star * 10;
            break;
        case 2:
            star = star * 10;
            break;
        case 3:
            star = star * 10;
            break;
        case 4:
            star = star * 10;
            break;
        case 5:
            star = star * 10;
            break;
        default:
            star = 5 * 10;
        }
        return star;
    }
}
