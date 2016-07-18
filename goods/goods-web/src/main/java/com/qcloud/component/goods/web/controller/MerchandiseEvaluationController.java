package com.qcloud.component.goods.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.key.TypeEnum.StarLevelType;
import com.qcloud.component.goods.service.MerchandiseEvaluationService;
import com.qcloud.component.goods.web.form.EvaluationForm;
import com.qcloud.component.goods.web.form.MerchandiseEvaluationForm;
import com.qcloud.component.goods.web.handler.MerchandiseEvaluationHandler;
import com.qcloud.component.goods.web.vo.MerchandiseEvaluationVO;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.QMyToEvaluation;
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

    @Autowired
    private MyClient                     myClient;

    @Autowired
    private CommoditycenterClient        commoditycenterClient;

    @Autowired
    private FileSDKClient                fileSDKClient;

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
        view.setList(voList);
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

        AssertUtil.assertTrue(evaluationForm.getMerchandiseEvaluations().size() > 0, "评价不能为空.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MerchandiseEvaluationForm> list = evaluationForm.getMerchandiseEvaluations();
        for (MerchandiseEvaluationForm merchandiseEvaluationForm : list) {
            // 默认好评
            if (StringUtils.isEmpty(merchandiseEvaluationForm.getContent())) {
                merchandiseEvaluationForm.setContent("好评!");
            }
            merchandiseEvaluationService.evaluate(merchandiseEvaluationForm.getToEvaluationId(), user.getId(), merchandiseEvaluationForm.getContent(), getRealStar(merchandiseEvaluationForm.getStar()), merchandiseEvaluationForm.getImages());
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("评价成功!");
        return view;
    }

    /**
     * 去评价商品
     */
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView toEvaluate(HttpServletRequest request, Long orderId) {

        AssertUtil.greatZero(orderId, "订单id不能为空.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<QMyToEvaluation> list = myClient.listByUserAndOrderId(user.getId(), orderId);
        AssertUtil.assertTrue(list.size() > 0, "该订单无待评价.");
        List<Map<String, Object>> voList = new ArrayList<Map<String, Object>>();
        for (QMyToEvaluation qMyToEvaluation : list) {
            Map<String, Object> param = new HashMap<String, Object>();
            QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(qMyToEvaluation.getUnifiedMerchandiseId());
            AssertUtil.assertNotNull(qUnifiedMerchandise, "统一商品不存在." + qMyToEvaluation.getUnifiedMerchandiseId());
            param.put("discount", qUnifiedMerchandise.getDiscount());
            param.put("image", !StringUtils.isEmpty(qUnifiedMerchandise.getImage()) ? fileSDKClient.getFileServerUrl() + qUnifiedMerchandise.getImage() : "");
            param.put("merchandiseId", qUnifiedMerchandise.getMerchandiseId());
            param.put("name", qUnifiedMerchandise.getName());
            param.put("specifications", qUnifiedMerchandise.getSpecifications());
            param.put("toEvaluationId", qMyToEvaluation.getId());
            voList.add(param);
        }
        Map<String, Object> orderNumMap = new HashMap<String, Object>();
        orderNumMap.put("orderNumber", list.get(0).getOrderNumber());
        voList.add(orderNumMap);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("显示待评价列表!");
        view.addObject("list", voList);
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
