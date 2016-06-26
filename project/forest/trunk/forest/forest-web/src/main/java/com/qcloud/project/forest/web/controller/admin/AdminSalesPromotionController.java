package com.qcloud.project.forest.web.controller.admin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.admin.AdminClient;
import com.qcloud.component.admin.QAdmin;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.OutdatedCommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.marketing.exception.MarketingException;
import com.qcloud.component.marketing.model.MerchandiseCustomClassification;
import com.qcloud.component.marketing.model.query.MerchandiseCustomClassificationQuery;
import com.qcloud.component.marketing.service.MerchandiseCustomClassificationService;
import com.qcloud.component.marketing.web.form.MerchandiseCustomClassificationForm;
import com.qcloud.component.marketing.web.form.MultipleSortForm;
import com.qcloud.component.marketing.web.handler.MerchandiseCustomClassificationHandler;
import com.qcloud.component.marketing.web.vo.admin.AdminMerchandiseCustomClassificationVO;
import com.qcloud.component.publicdata.ClassifyType;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.publicdata.service.ClassifyService;
import com.qcloud.component.publicdata.util.ClassifyUtils;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.key.TypeEnum;

@Controller
@RequestMapping(value = "/" + AdminSalesPromotionController.DIR)
public class AdminSalesPromotionController {

    public static final String                     DIR = "admin/salesPromotion";

    @Autowired
    private PublicdataClient                       publicdataClient;

    @Autowired
    private MerchandiseCustomClassificationService merchandiseCustomClassificationService;

    @Autowired
    private MerchandiseCustomClassificationHandler merchandiseCustomClassificationHandler;

    @Autowired
    private OutdatedCommoditycenterClient          outdatedCommoditycenterClient;

    @Autowired
    private CommoditycenterClient                  commoditycenterClient;

    @Autowired
    private FileSDKClient                          fileSDKClient;

    @Autowired
    private ClassifyService                        classifyService;

    /**
     * 展示活动商品的类别
     * @param pPage
     * @return
     */
    @RequestMapping
    public ModelAndView listClassify(PPage pPage) {

        List<Classify> classifies = publicdataClient.listClassify(Long.valueOf(TypeEnum.ClassifyType.SALESPROMOTION.getKey()));
        pPage.setPageSize(10);
        int i = (pPage.getPageNum() - 1) * pPage.getPageSize() + pPage.getPageSize();
        if (i > classifies.size()) {
            i = classifies.size();
        }
        classifies.subList((pPage.getPageNum() - 1) * pPage.getPageSize(), i);
        AcePagingView pagingView = new AcePagingView("/admin/forest-salesPromotion-classifyList", DIR + "/list", pPage.getPageNum(), pPage.getPageSize(), classifies.size());
        pagingView.addObject("result", classifies);
        return pagingView;
    }

    /**
     * 跳转到添加类别界面
     * @return
     */
    @RequestMapping
    public ModelAndView toAddSalesPromotionClassify() {

        ModelAndView modelAndView = new ModelAndView("/admin/forest-salesPromotion-classifyAdd");
        modelAndView.addObject("typeId", Long.valueOf(TypeEnum.ClassifyType.SALESPROMOTION.getKey()));
        return modelAndView;
    }

    /**
     * 提交添加类别
     * @param classify
     * @return
     */
    @RequestMapping
    public AceAjaxView addSalesPromotionClassify(Classify classify) {

        String url = fileSDKClient.uidToUrl(classify.getImage());
        classify.setImage(url);
        publicdataClient.addClassify(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/listClassify");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEditSalesPromotionClassify(Long classifyId, String queryStr) {

        AssertUtil.assertNotNull(classifyId, "ID不能为空");
        Classify classify = classifyService.get(classifyId);
        String uid = null;
        if (classify.getImage() != null) {
            uid = fileSDKClient.urlToUid(classify.getImage());
        }
        ModelAndView model = new ModelAndView("/admin/forest-salesPromotion-classifyEdit");
        model.addObject("classify", classify);
        model.addObject("uid", uid);
        return model;
    }

    @RequestMapping
    public AceAjaxView editSalesPromotionClassify(Classify classify, String queryStr) {

        Classify classify2 = classifyService.get(classify.getId());
        if (classify2.getImage() != null) {
            if (!classify2.getImage().equals(fileSDKClient.getFileServerUrl() + classify2.getImage())) {
                String url = fileSDKClient.uidToUrl(classify.getImage());
                classify.setImage(url);
            }
        }
        classifyService.update(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/listClassify?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, MerchandiseCustomClassificationQuery query) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        int pageSize = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, pageSize);
        query.setMerchantId(merchant.getId());
        // 根据名称模糊搜索
        if (!StringUtils.isEmpty(query.getName())) {
            start = 0;
            pageNum = 1;
            pageSize = Integer.MAX_VALUE;
        }
        Page<MerchandiseCustomClassification> page = merchandiseCustomClassificationService.page(query, start, pageSize);
        List<AdminMerchandiseCustomClassificationVO> list = merchandiseCustomClassificationHandler.toVOList4Admin(page.getData());
        List<Classify> cList = publicdataClient.listClassify(ClassifyType.MERCHANTCUSTOM);
        Map<Long, String> classifyMap = new LinkedHashMap<Long, String>();
        for (Classify classify : cList) {
            classifyMap.put(classify.getId(), ClassifyUtils.calculationPath(cList, classify));
        }
        // 根据名称模糊搜索
        if (!StringUtils.isEmpty(query.getName())) {
            Iterator<AdminMerchandiseCustomClassificationVO> it = list.iterator();
            while (it.hasNext()) {
                AdminMerchandiseCustomClassificationVO vo = it.next();
                if (!vo.getName().contains(query.getName())) {
                    it.remove();
                }
            }
            page.setCount(list.size());
        }
        String param = "classifyId=" + query.getClassifyId();
        AcePagingView pagingView = new AcePagingView("/admin/forest-SalesPromotion-MerchandiseCustomClassification-list", DIR + "/list?" + param, pageNum, pageSize, page.getCount());
        pagingView.addObject("classifyMap", classifyMap);
        pagingView.addObject("query", query);
        pagingView.addObject("result", list);
        return pagingView;
    }

    /**
     * 自定义商城列表
     * @param request
     * @param pageNum
     * @param query
     * @return
     */
    @RequestMapping
    @NoReferer
    public ModelAndView mallList(HttpServletRequest request, Integer pageNum, MerchandiseCustomClassificationQuery query) {

        long merchantId = Long.valueOf(1);
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        query.setMerchantId(merchantId);
        Page<MerchandiseCustomClassification> page = merchandiseCustomClassificationService.page(query, start, PAGE_SIZE);
        List<AdminMerchandiseCustomClassificationVO> list = merchandiseCustomClassificationHandler.toVOList4Admin(page.getData());
        List<Classify> cList = publicdataClient.listClassify((long) com.qcloud.project.forest.model.key.TypeEnum.ClassifyType.SALESPROMOTION.getKey());
        Map<Long, String> classifyMap = new LinkedHashMap<Long, String>();
        for (Classify classify : cList) {
            classifyMap.put(classify.getId(), ClassifyUtils.calculationPath(cList, classify));
        }
        String param = "classifyId=" + query.getClassifyId();
        AcePagingView pagingView = new AcePagingView("/admin/forest-SalesPromotion-MallCustomClassification-list", DIR + "/mallList?" + param, pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("classifyMap", classifyMap);
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        return pagingView;
    }

    /**
     * 跳转到新增商品页面
     * @param query
     * @return
     */
    @RequestMapping
    public ModelAndView toAddClassifyForMerchantCustom(HttpServletRequest request, MerchandiseCustomClassificationQuery query) {

        ModelAndView modelAndView = new ModelAndView("/admin/forest-SalesPromotion-MerchandiseCustomClassification-add");
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        Map<String, Object> map = new HashMap<String, Object>();
        if (query.getClassifyId() > 0) {
            map.put("merchantId", merchant.getId());
            map.put("customClassifyId", query.getClassifyId());
            List<MerchandiseCustomClassification> mcList = merchandiseCustomClassificationService.listAll(map);
            List<AdminMerchandiseCustomClassificationVO> voList = merchandiseCustomClassificationHandler.toVOList4Admin(mcList);
            modelAndView.addObject("voList", voList);
        }
        List<Classify> cList = publicdataClient.listClassify((long) com.qcloud.project.forest.model.key.TypeEnum.ClassifyType.SALESPROMOTION.getKey());
        Map<Long, String> classifyMap = new LinkedHashMap<Long, String>();
        for (Classify classify : cList) {
            classifyMap.put(classify.getId(), ClassifyUtils.calculationPath(cList, classify));
        }
        modelAndView.addObject("classifyMap", classifyMap);
        modelAndView.addObject("query", query);
        return modelAndView;
    }

    /**
     * 提交新增商品
     * @param merchandiseCustomClassificationForm
     * @param request
     * @return
     */
    @RequestMapping
    public AceAjaxView addClassifyForMerchantCustom(MerchandiseCustomClassificationForm merchandiseCustomClassificationForm, HttpServletRequest request) {

        AssertUtil.greatZero(merchandiseCustomClassificationForm.getClassifyId(), "请选择分类.");
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        AceAjaxView aceAjaxView = new AceAjaxView();
        MerchandiseCustomClassification mc = new MerchandiseCustomClassification();
        long classifyId = merchandiseCustomClassificationForm.getClassifyId();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("merchantId", merchant.getId());
        map.put("customClassifyId", classifyId);
        merchandiseCustomClassificationService.delete(map);
        mc.setMerchantId(merchant.getId());
        mc.setCustomClassifyId(classifyId);
        List<Long> unifiedMerchandiseIds = merchandiseCustomClassificationForm.getUnifiedMerchandiseIds();
        List<Integer> orderNums = merchandiseCustomClassificationForm.getOrderNums();
        for (int i = 0; i < unifiedMerchandiseIds.size(); i++) {
            mc.setUnifiedMerchandiseId(unifiedMerchandiseIds.get(i));
            mc.setOrderNum(orderNums.get(i));
            merchandiseCustomClassificationService.add(mc);
        }
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/toAddClassifyForMerchantCustom?classifyId=" + classifyId);
        return aceAjaxView;
    }

    /**
     * 跳转到商城自定义新增页面
     * @param query
     * @return
     */
    @RequestMapping
    public ModelAndView toAddClassifyForMallCustom(HttpServletRequest request, MerchandiseCustomClassificationQuery query) {

        // 默认商城为1
        Long merchantId = Long.valueOf(1);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("merchantId", merchantId);
        map.put("customClassifyId", query.getClassifyId());
        List<MerchandiseCustomClassification> mcList = merchandiseCustomClassificationService.listAll(map);
        List<AdminMerchandiseCustomClassificationVO> voList = merchandiseCustomClassificationHandler.toVOList4Admin(mcList);
        List<Classify> cList = publicdataClient.listClassify((long) com.qcloud.project.forest.model.key.TypeEnum.ClassifyType.SALESPROMOTION.getKey());
        Map<Long, String> classifyMap = new LinkedHashMap<Long, String>();
        for (Classify classify : cList) {
            classifyMap.put(classify.getId(), ClassifyUtils.calculationPath(cList, classify));
        }
        ModelAndView modelAndView = new ModelAndView("/admin/forest-SalesPromotion-MallCustomClassification-add");
        modelAndView.addObject("classifyMap", classifyMap);
        modelAndView.addObject("query", query);
        modelAndView.addObject("voList", voList);
        return modelAndView;
    }

    /**
     * 商城自定义添加
     * @param merchandiseCustomClassificationForm
     * @param request
     * @return
     */
    @Transactional
    @RequestMapping
    public AceAjaxView addClassifyForMallCustom(MerchandiseCustomClassificationForm merchandiseCustomClassificationForm, HttpServletRequest request) {

        // 默认商城为1
        long merchantId = Long.valueOf(1);
        AceAjaxView aceAjaxView = new AceAjaxView();
        MerchandiseCustomClassification mc = new MerchandiseCustomClassification();
        long classifyId = merchandiseCustomClassificationForm.getClassifyId();
        // 先删除然后再添加
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("merchantId", merchantId);
        map.put("customClassifyId", classifyId);
        merchandiseCustomClassificationService.delete(map);
        mc.setMerchantId(merchantId);
        mc.setCustomClassifyId(classifyId);
        List<Long> unifiedMerchandiseIds = merchandiseCustomClassificationForm.getUnifiedMerchandiseIds();
        List<Integer> orderNums = merchandiseCustomClassificationForm.getOrderNums();
        QUnifiedMerchandise qUnifiedMerchandise = null;
        for (int i = 0; i < unifiedMerchandiseIds.size(); i++) {
            qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseIds.get(i));
            mc.setUnifiedMerchandiseId(unifiedMerchandiseIds.get(i));
            mc.setOrderNum(orderNums.get(i));
            mc.setName(qUnifiedMerchandise.getName());
            merchandiseCustomClassificationService.add(mc);
        }
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/toAddClassifyForMallCustom?classifyId=" + classifyId);
        return aceAjaxView;
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @RequestMapping
    public AceAjaxView deleteMall(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MerchandiseCustomClassification m = merchandiseCustomClassificationService.get(id);
        if (m.getMerchantId() != Long.valueOf(1)) {
            throw new MarketingException("不能删除不属于您的商品!");
        }
        merchandiseCustomClassificationService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/mallList");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView customMallList(Long merchantId, Long classifyId, String name, Integer pageNum, HttpServletRequest request) {

        AssertUtil.greatZero(merchantId, "商家Id不能为空");
        AssertUtil.greatZero(classifyId, "分类Id不能为空");
        MerchandiseCustomClassificationQuery query = new MerchandiseCustomClassificationQuery();
        query.setClassifyId(classifyId);
        query.setName(StringUtil.nullToEmpty(name));
        query.setMerchantId(merchantId);
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<MerchandiseCustomClassification> merchandiseCustomClassification = merchandiseCustomClassificationService.page(query, start, PAGE_SIZE);
        List<AdminMerchandiseCustomClassificationVO> voList = merchandiseCustomClassificationHandler.toVOList4Admin(merchandiseCustomClassification.getData());
        AcePagingView view = new AcePagingView("/admin/forest-SalesPromotion-CustomMall-list", DIR + "/customMallList?merchantId=" + merchantId + "&classifyId=" + classifyId + "&name=" + StringUtil.nullToEmpty(query.getName()), pageNum, PAGE_SIZE, merchandiseCustomClassification.getCount());
        view.addObject("list", voList);
        view.addObject("classifyId", classifyId);
        view.addObject("merchantId", merchantId);
        view.addObject("name", name);
        return view;
    }

    @RequestMapping
    public ModelAndView toAddCustomMall(Long merchantId, Long classifyId) {

        ModelAndView view = new ModelAndView();
        view.setViewName("/admin/forest-SalesPromotion-CustomMall-add");
        // 自定义分类全部列表
        List<Classify> cList = publicdataClient.listClassify(ClassifyType.MALLCUSTOM);
        cList.addAll(publicdataClient.listClassify(ClassifyType.MERCHANTCUSTOM));
        view.addObject("classify", cList);
        view.addObject("classifyId", classifyId);
        view.addObject("merchantId", merchantId);
        return view;
    }

    @RequestMapping
    public AceAjaxView addCustomMall(MerchandiseCustomClassificationForm classificationForm, Long merchantId, Long classifyId) {

        List<Long> unifiedMerchandiseIds = classificationForm.getUnifiedMerchandiseIds();
        List<Integer> orderNums = classificationForm.getOrderNums();
        for (int i = 0; i < unifiedMerchandiseIds.size(); i++) {
            // 获取merchandise：取到sysCode 和 name
            QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseIds.get(i));
            Long merchandiseId = qUnifiedMerchandise.getList().get(0).getMerchandiseId();
            Merchandise merchandise = outdatedCommoditycenterClient.getMerchandise(merchandiseId);
            MerchandiseCustomClassification mc = new MerchandiseCustomClassification();
            mc.setCustomClassifyId(classifyId);
            mc.setMerchantId(merchantId);
            mc.setUnifiedMerchandiseId(unifiedMerchandiseIds.get(i));
            mc.setOrderNum(orderNums.get(i));
            mc.setName(merchandise.getName());
            mc.setSysCode(merchandise.getSysCode());
            merchandiseCustomClassificationService.add(mc);
        }
        AceAjaxView view = new AceAjaxView();
        view.setMessage("添加成功");
        view.setUrl(DIR + "/customMallList?merchantId=" + merchantId + "&classifyId=" + classifyId);
        return view;
    }

    @RequestMapping
    public AceAjaxView delete(Long id, HttpServletRequest request) {

        MerchandiseCustomClassification m = merchandiseCustomClassificationService.get(id);
        if (m.getMerchantId() == 1) {// 管理员可删除
            QAdmin admin = PageParameterUtil.getParameterValues(request, AdminClient.ADMIN_LOGIN_PARAMETER_KEY);
            if (admin == null) {
                throw new MarketingException("不能删除不属于您的商品!");
            }
        } else {// 商家可删除
            QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
            AssertUtil.assertNotNull(id, "ID不能为空");
            if (m.getMerchantId() != merchant.getId()) {
                throw new MarketingException("不能删除不属于您的商品!");
            }
        }
        merchandiseCustomClassificationService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView multipleSort(MultipleSortForm form, Long merchantId, Long classifyId) {

        List<MerchandiseCustomClassification> customList = form.getCustomList();
        for (MerchandiseCustomClassification temp : customList) {
            MerchandiseCustomClassification custom = merchandiseCustomClassificationService.get(temp.getId());
            custom.setOrderNum(temp.getOrderNum());
            merchandiseCustomClassificationService.update(custom);
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("修改成功");
        aceAjaxView.setUrl(DIR + "/customMallList?merchantId=" + merchantId + "&classifyId=" + classifyId);
        return aceAjaxView;
    }
}
