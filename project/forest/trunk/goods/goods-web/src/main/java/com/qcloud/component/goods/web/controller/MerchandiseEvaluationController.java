package com.qcloud.component.goods.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.StarLevelType;
import com.qcloud.component.goods.service.MerchandiseEvaluationService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.form.EvaluationForm;
import com.qcloud.component.goods.web.form.MerchandiseEvaluationForm;
import com.qcloud.component.goods.web.handler.MerchandiseEvaluationHandler;
import com.qcloud.component.goods.web.vo.MerchandiseEvaluationVO;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.my.QMyToAppendEvaluation;
import com.qcloud.component.my.QMyToEvaluation;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.sellercenter.model.key.TypeEnum.StatusType;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;

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
    private FileSDKClient                fileSDKClient;

    @Autowired
    private UnifiedMerchandiseService    unifiedMerchandiseService;

    /**
    * 商品显示评价
    * @param pageNum
    * @param merchandiseId
    * @return
    */
    @PiratesApp
    @RequestMapping
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
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        long goodEvaluation = 0;
        long middleEvaluation = 0;
        long lowEvaluation = 0;
        for (MerchandiseEvaluationVO vo : voList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("addContent", vo.getAddContent() != null ? vo.getAddContent() : "");
            map.put("content", vo.getContent());
            map.put("headImage", !StringUtils.isEmpty(vo.getHeadImage()) ? fileSDKClient.getFileServerUrl() + vo.getHeadImage() : "");
            List<String> imageEvaluations = new ArrayList<String>();
            if (!StringUtils.isEmpty(vo.getImages())) {
                for (String image : vo.getImages().split(",")) {
                    if (!StringUtils.isEmpty(image)) {
                        imageEvaluations.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(image));
                    }
                }
            }
            map.put("imageEvaluation", imageEvaluations);
            map.put("replyContent", vo.getReplyContent() != null ? vo.getReplyContent() : "");
            map.put("specifications", vo.getSpecifications());
            map.put("star", vo.getStar() / 10);
            map.put("time", vo.getTime());
            map.put("userName", vo.getUserName());
            // 好评、中评、差评
            int star = vo.getStar();
            if (StarLevelType.CP.getKey() >= star) {
                lowEvaluation = lowEvaluation + 1;
            } else if (StarLevelType.ZP.getKey() >= star && star > StarLevelType.CP.getKey()) {
                middleEvaluation = middleEvaluation + 1;
            } else {
                goodEvaluation = goodEvaluation + 1;
            }
            mapList.add(map);
        }
        FrontPagingView view = new FrontPagingView(pageNum, PAGE_SIZE, page.getCount());
        view.addObject("goodEvaluation", goodEvaluation);
        view.addObject("middleEvaluation", middleEvaluation);
        view.addObject("lowEvaluation", lowEvaluation);
        view.setList(mapList);
        view.addObject("merchandiseId", merchandiseId);
        return view;
    }

    /**
     * 评价商品
     * @param vo  
     * @return
     */
    @PiratesApp
    @RequestMapping
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
    public FrontAjaxView toEvaluate(HttpServletRequest request, Long orderId) {

        AssertUtil.greatZero(orderId, "订单id不能为空.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<QMyToEvaluation> list = myClient.listByUserAndOrderId(user.getId(), orderId);
        AssertUtil.assertTrue(list.size() > 0, "该订单不属于你或无待评价.");
        List<Map<String, Object>> voList = new ArrayList<Map<String, Object>>();
        for (QMyToEvaluation qMyToEvaluation : list) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("discount", qMyToEvaluation.getDiscount());
            param.put("image", !StringUtils.isEmpty(qMyToEvaluation.getImage()) ? fileSDKClient.getFileServerUrl() + qMyToEvaluation.getImage() : "");
            param.put("merchandiseId", qMyToEvaluation.getMerchandiseId());
            param.put("name", qMyToEvaluation.getName());
            param.put("specifications", qMyToEvaluation.getSpecifications());
            param.put("toEvaluationId", qMyToEvaluation.getId());
            voList.add(param);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("显示待评价列表!");
        view.addObject("list", voList);
        view.addObject("orderNumber", list.get(0).getOrderNumber());
        return view;
    }

    /**
     * 去追评商品
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView toAdditional(HttpServletRequest request, Long orderId) {

        AssertUtil.greatZero(orderId, "订单id不能为空.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<QMyToAppendEvaluation> list = myClient.listAppendEvaluation(user.getId(), orderId);
        AssertUtil.assertTrue(list.size() > 0, "该订单不属于你或无追评.");
        List<Map<String, Object>> voList = new ArrayList<Map<String, Object>>();
        for (QMyToAppendEvaluation qMyToAppendEvaluation : list) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("appEvaluationId", qMyToAppendEvaluation.getId());
            param.put("discount", qMyToAppendEvaluation.getDiscount());
            param.put("image", !StringUtils.isEmpty(qMyToAppendEvaluation.getImage()) ? fileSDKClient.getFileServerUrl() + qMyToAppendEvaluation.getImage() : "");
            param.put("merchandiseId", qMyToAppendEvaluation.getMerchandiseId());
            param.put("name", qMyToAppendEvaluation.getName());
            param.put("specifications", qMyToAppendEvaluation.getSpecifications());
            voList.add(param);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("显示追评列表!");
        view.addObject("list", voList);
        view.addObject("orderNumber", list.get(0).getOrderNumber());
        return view;
    }

    /**
     * 追加商品评论
     * @param vo  
     * @return
     */
    @PiratesApp
    @RequestMapping
    @Transactional
    public FrontAjaxView additional(HttpServletRequest request, EvaluationForm evaluationForm) {

        AssertUtil.assertTrue(evaluationForm.getMerchandiseEvaluations().size() > 0, "评价不能为空.");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<MerchandiseEvaluationForm> list = evaluationForm.getMerchandiseEvaluations();
        for (MerchandiseEvaluationForm merchandiseEvaluationForm : list) {
            // 默认好评
            if (StringUtils.isEmpty(merchandiseEvaluationForm.getContent())) {
                merchandiseEvaluationForm.setContent("追加好评!");
            }
            // 追加评论id
            long appEvaluationId = merchandiseEvaluationForm.getAppEvaluationId();
            QMyToAppendEvaluation appEvaluation = myClient.getMyToAppendEvaluation(appEvaluationId);
            AssertUtil.assertNotNull(appEvaluation, "待追评id不存在，请检查数据." + appEvaluationId);
            AssertUtil.assertTrue(user.getId() == appEvaluation.getUserId(), "此评价数据不属于你.");
            // 更新追评内容
            MerchandiseEvaluation me = merchandiseEvaluationService.get(appEvaluation.getEvaluationId(), appEvaluation.getMerchandiseId());
            AssertUtil.assertNotNull(me, "商品评价不存在." + appEvaluation.getMerchandiseId());
            me.setAddContent(merchandiseEvaluationForm.getContent());
            merchandiseEvaluationService.update(me);
            myClient.deleteAppendEvaluation(appEvaluationId);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("追加评价成功!");
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
