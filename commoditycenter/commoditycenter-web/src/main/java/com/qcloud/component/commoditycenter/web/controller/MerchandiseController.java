package com.qcloud.component.commoditycenter.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise.MerchandiseType;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.model.MerchandiseAttribute;
import com.qcloud.component.commoditycenter.model.MerchandiseBrowsingHistory;
import com.qcloud.component.commoditycenter.model.MerchandiseEvaluation;
import com.qcloud.component.commoditycenter.model.MerchandiseImage;
import com.qcloud.component.commoditycenter.model.MerchandiseItem;
import com.qcloud.component.commoditycenter.model.MerchandiseSpecifications;
import com.qcloud.component.commoditycenter.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.commoditycenter.model.MerchandiseVipDiscount;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.MerchandiseIsIncludePost;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.MerchandiseStateType;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.OrderType;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.QueryType;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.StarLevelType;
import com.qcloud.component.commoditycenter.model.query.MerchandiseItemQuery;
import com.qcloud.component.commoditycenter.service.MerchandiseAttributeService;
import com.qcloud.component.commoditycenter.service.MerchandiseBrowsingHistoryService;
import com.qcloud.component.commoditycenter.service.MerchandiseEvaluationService;
import com.qcloud.component.commoditycenter.service.MerchandiseImageService;
import com.qcloud.component.commoditycenter.service.MerchandiseItemService;
import com.qcloud.component.commoditycenter.service.MerchandiseService;
import com.qcloud.component.commoditycenter.service.MerchandiseSpecificationsRelationService;
import com.qcloud.component.commoditycenter.service.MerchandiseSpecificationsService;
import com.qcloud.component.commoditycenter.service.MerchandiseVipDiscountService;
import com.qcloud.component.commoditycenter.web.form.MerchandiseForm;
import com.qcloud.component.commoditycenter.web.form.SpecificationsForm;
import com.qcloud.component.commoditycenter.web.form.SpecificationsFormList;
import com.qcloud.component.commoditycenter.web.handler.ClassifySpecificationsHandler;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseAttributeHandler;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseEvaluationHandler;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseHandler;
import com.qcloud.component.commoditycenter.web.vo.AttributeSpecificationsVO;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseAttributeVO;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseEvaluationVO;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseExtVO;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseVO;
import com.qcloud.component.commoditycenter.web.vo.MerchantMerchandiseVO;
import com.qcloud.component.commoditycenter.web.vo.SexpressMerchandiseVO;
import com.qcloud.component.commoditycenter.web.vo.SimpleMerchandiseVO;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicservice.ShareClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.mvc.HtmlView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;

@Controller
@RequestMapping(value = MerchandiseController.DIR)
public class MerchandiseController {

    public static final String                       DIR = "/merchandise";

    @Autowired
    private MerchandiseService                       merchandiseService;

    @Autowired
    private CommoditycenterClient                    commoditycenterClient;

    @Autowired
    private MerchandiseItemService                   merchandiseItemService;

    @Autowired
    private MerchandiseHandler                       merchandiseHandler;

    @Autowired
    private FileSDKClient                            fileSDKClient;

    @Autowired
    private MerchandiseImageService                  merchandiseImageService;

    @Autowired
    private MerchandiseSpecificationsRelationService merchandiseSpecificationsRelationService;

    @Autowired
    private MerchandiseSpecificationsService         merchandiseSpecificationsService;

    // @Autowired
    // private ClassifySpecificationsService classifySpecificationsService;
    @Autowired
    private ClassifySpecificationsHandler            classifySpecificationsHandler;

    @Autowired
    private SellercenterClient                       sellercenterClient;

    @Autowired
    private MerchandiseBrowsingHistoryService        merchandiseBrowsingHistoryService;

    @Autowired
    private MyClient                                 myClient;

    @Autowired
    private MerchandiseEvaluationService             merchandiseEvaluationService;

    @Autowired
    private MerchandiseEvaluationHandler             merchandiseEvaluationHandler;

    @Autowired
    private PublicdataClient                         publicdataClient;

    @Autowired
    private MerchandiseVipDiscountService            merchandiseVipDiscountService;

    @Autowired
    private ShareClient                              shareClient;

    @Autowired
    private MerchandiseAttributeService              merchandiseAttributeService;

    @Autowired
    private MerchandiseAttributeHandler              merchandiseAttributeHandler;

    // @Autowired
    // private EvaluationcenterClient evaluationcenterClient;
    // 查询商品,上搜索引擎后再转移这个功能,返回商品档案,还不能直接购买
    @RequestMapping
    public FrontPagingView query(HttpServletRequest request, PPage pPage, MerchandiseForm form) {

        return query(request, pPage, form, QueryItemType.M);
    }

    @RequestMapping
    public FrontPagingView queryBySpecifications(HttpServletRequest request, PPage pPage, MerchandiseForm form) {

        return query(request, pPage, form, QueryItemType.S);
    }

    //
    private FrontPagingView query(HttpServletRequest request, PPage pPage, MerchandiseForm form, QueryItemType type) {

        MerchandiseItemQuery query = new MerchandiseItemQuery();
        query.setName(form.getKeywords());
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_IS_LOGIN_PARAMETER_KEY);
        // 已经登录
        if (user != null) {
            myClient.addMySearchHistory(user.getId(), CommoditycenterClient.SEARCH_TYPE, form.getKeywords());
        }
        publicdataClient.addSearch(CommoditycenterClient.SEARCH_TYPE, form.getKeywords());
        query.setMerchantId(form.getMerchantId());
        query.setMerchantClassifyId(form.getMerchantClassifyId());
        query.setMallClassifyId(form.getMallClassifyId());
        query.setQueryType(QueryType.get(form.getQueryType()));
        query.setOrderType(OrderType.get(form.getOrderType()));
        query.setQueryItemType(type);
        query.setBrandId(form.getBrandId());
        Page<MerchandiseItem> page = merchandiseItemService.query(query, pPage.getPageStart(), pPage.getPageSize());
        List<MerchandiseItem> list = page.getData();
        List<MerchandiseVO> voList = new ArrayList<MerchandiseVO>();
        for (MerchandiseItem merchandiseItem : list) {
            Merchandise merchandise = merchandiseService.get(merchandiseItem.getMerchandiseId());
            MerchandiseVO merchandiseVO = merchandiseHandler.toVO(merchandise);
            merchandiseVO.setPrice(merchandiseItem.getPrice());
            merchandiseVO.setDiscount(merchandiseItem.getDiscount());
            merchandiseVO.setSalesVolume(merchandiseItem.getSalesVolume());
            if (QueryItemType.M.equals(type)) {
                merchandiseVO.setSalesVolume(merchandiseService.getSalesVolume(merchandise.getId()));
            }
            if (StringUtils.isNotEmpty(merchandise.getImage())) {
                merchandiseVO.setImage(fileSDKClient.getFileServerUrl() + merchandise.getImage());
            }
            merchandiseVO.setUnifiedMerchandiseId(merchandiseItem.getUnifiedMerchandiseId());
            merchandiseVO.setSpecifications("");
            if (!QueryItemType.M.equals(type)) {
                // 规格
                MerchandiseSpecifications merchandiseSpecifications = merchandiseSpecificationsService.get(merchandiseItem.getMerchandiseSpecificationsId());
                if (merchandiseSpecifications != null) {
                    String specificationsStr = merchandiseSpecifications.getValue0() + " " + merchandiseSpecifications.getValue1() + " " + merchandiseSpecifications.getValue2();
                    merchandiseVO.setSpecifications(specificationsStr);
                }
            }
            long sum = merchandiseItem.getGoodEvaluation() + merchandiseItem.getMiddleEvaluation() + merchandiseItem.getLowEvaluation();
            int goodEvaluationRate = 0;
            if (sum != 0) {
                goodEvaluationRate = new Double(merchandiseItem.getGoodEvaluation() * 100 / sum).intValue();
            }
            merchandiseVO.setGoodEvaluationRate(goodEvaluationRate);
            // vip 价格
            double minVip = merchandiseVipDiscountService.statMin(merchandiseItem.getId());
            double maxVip = merchandiseVipDiscountService.statMax(merchandiseItem.getId());
            minVip = minVip > merchandiseItem.getDiscount() || new Double(minVip).longValue() <= 0 ? merchandiseItem.getDiscount() : minVip;
            maxVip = maxVip > merchandiseItem.getDiscount() || new Double(maxVip).longValue() <= 0 ? merchandiseItem.getDiscount() : maxVip;
            merchandiseVO.setMinVipDiscount(minVip);
            merchandiseVO.setMaxVipDiscount(maxVip);
            merchandiseVO.setVipDiscount(0);
            if (user != null) {
                MerchandiseVipDiscount merchandiseVipDiscount = merchandiseVipDiscountService.get(user.getId(), merchandiseItem.getId());
                // 目前只管价格,暂时不处理折扣
                merchandiseVO.setVipDiscount(merchandiseVipDiscount == null ? 0 : merchandiseVipDiscount.getPrice());
            }
            //
            if (user != null) {
                int number = myClient.getMyShoppingCartMerchandiseNumber(user.getId(), merchandiseItem.getUnifiedMerchandiseId());
                merchandiseVO.setNumber(number);
            } else {
                merchandiseVO.setNumber(0);
            }
            //
            voList.add(merchandiseVO);
        }
        FrontPagingView view = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        view.setList(voList);
        view.setMessage("查询商品成功.");
        return view;
    }

    // 依据商品档案ID,获取商品详情
    // @RequestMapping
    // public FrontAjaxView getByMerchandise(Long merchandiseId) {
    //
    // Merchandise merchandise = merchandiseService.get(merchandiseId);
    // AssertUtil.assertNotNull(merchandise, "商品不存在." + merchandiseId);
    // List<MerchandiseItem> list = merchandiseItemService.listByMerchandise(merchandiseId);
    // List<QUnifiedMerchandise> umList = new ArrayList<QUnifiedMerchandise>();
    // for (MerchandiseItem merchandiseItem : list) {
    // QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseItem.getUnifiedMerchandiseId());
    // umList.add(unifiedMerchandise);
    // }
    // MerchandiseVO merchandiseVO = merchandiseHandler.toVO(merchandise);
    // FrontAjaxView view = new FrontAjaxView();
    // view.setMessage("获取商品成功.");
    // view.addObject("merchandise", merchandiseVO);
    // view.addObject("unifiedMerchandiseList", umList);
    // return view;
    // }
    // 依据商品档案ID,获取商品详情
    @RequestMapping
    public FrontAjaxView getByMerchandise(HttpServletRequest request, Long merchandiseId) {

        Merchandise merchandise = merchandiseService.get(merchandiseId);
        AssertUtil.assertNotNull(merchandise, "商品不存在." + merchandiseId);
        List<MerchandiseSpecificationsRelation> list = merchandiseSpecificationsRelationService.listByMerchandiseId(merchandiseId);
        List<AttributeSpecificationsVO> attributeSpecificationsVOList = classifySpecificationsHandler.toAttributeSpecificationsVOList(list);
        // List<ClassifySpecifications> csList = classifySpecificationsService.listByClassify(merchandise.getSpecClassifyId());
        // List<AttributeSpecificationsVO> attributeSpecificationsVOList = classifySpecificationsHandler.toAttributeSpecificationsVOList(csList);
        ArrayList<SpecificationsForm> specificationsList = new ArrayList<SpecificationsForm>();
        for (AttributeSpecificationsVO attributeSpecificationsVO : attributeSpecificationsVOList) {
            SpecificationsForm sf = new SpecificationsForm();
            sf.setAttributeId(attributeSpecificationsVO.getId());
            sf.setValue(attributeSpecificationsVO.getSpecificationsList().get(0));
            specificationsList.add(sf);
        }
        SpecificationsFormList specificationsFormList = new SpecificationsFormList();
        specificationsFormList.setSpecificationsList(specificationsList);
        return getBySpecifications(request, merchandiseId, specificationsFormList);
    }

    // 依据商品规格,获取商品详情
    @RequestMapping
    public FrontAjaxView getBySpecifications(HttpServletRequest request, Long merchandiseId, SpecificationsFormList specificationsList) {

        MerchandiseItem merchandiseItem = getMerchandiseItemBySpecifications(merchandiseId, specificationsList);
        return getByUnifiedMerchandise(request, merchandiseItem.getUnifiedMerchandiseId());
    }

    // 依据商品规格,获取商品详情
    @RequestMapping
    public FrontAjaxView getPriceAndStockBySpecifications(Long merchandiseId, SpecificationsFormList specificationsList) {

        MerchandiseItem merchandiseItem = getMerchandiseItemBySpecifications(merchandiseId, specificationsList);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取商品价格与库存成功.");
        view.addObject("price", merchandiseItem.getPrice());
        view.addObject("discount", merchandiseItem.getDiscount());
        view.addObject("stock", merchandiseItem.getStock());
        return view;
    }

    private MerchandiseItem getMerchandiseItemBySpecifications(Long merchandiseId, SpecificationsFormList specificationsList) {

        List<SpecificationsForm> list = specificationsList.getSpecificationsList();
        list = list == null ? new ArrayList<SpecificationsForm>() : list;
        long attributeId0 = -1;
        String value0 = "";
        if (specificationsList != null && list.size() > 0) {
            SpecificationsForm sf0 = list.get(0);
            attributeId0 = sf0.getAttributeId();
            value0 = sf0.getValue();
        }
        long attributeId1 = -1;
        String value1 = "";
        if (specificationsList != null && list.size() > 1) {
            SpecificationsForm sf1 = list.get(1);
            attributeId1 = sf1.getAttributeId();
            value1 = sf1.getValue();
        }
        long attributeId2 = -1;
        String value2 = "";
        if (specificationsList != null && list.size() > 2) {
            SpecificationsForm sf2 = list.get(2);
            attributeId2 = sf2.getAttributeId();
            value2 = sf2.getValue();
        }
        MerchandiseSpecifications merchandiseSpecifications = merchandiseSpecificationsService.get(merchandiseId, attributeId0, value0, attributeId1, value1, attributeId2, value2);
        MerchandiseItem merchandiseItem = null;
        if (merchandiseSpecifications != null) {
            merchandiseItem = merchandiseItemService.getBySpecifications(merchandiseId, merchandiseSpecifications.getId());
            AssertUtil.assertNotNull(merchandiseItem, "找不到指定的规格商品Specifications." + merchandiseSpecifications.getId());
        } else {
            merchandiseItem = merchandiseItemService.getBySpecifications(merchandiseId, -1L);
            AssertUtil.assertNotNull(merchandiseItem, "找不到指定的规格商品merchandiseId." + merchandiseId);
        }
        return merchandiseItem;
    }

    // 依据单一商品ID,获取商品详情
    @RequestMapping
    public FrontAjaxView getByUnifiedMerchandise(HttpServletRequest request, Long unifiedMerchandiseId) {

        Integer clientType = PageParameterUtil.getParameterValues(request, PiratesParameterKey.CLIENT_TYPE);
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_IS_LOGIN_PARAMETER_KEY);
        // 已经登录
        if (user != null && clientType > 0) {
            MerchandiseBrowsingHistory merchandiseBrowsingHistory = new MerchandiseBrowsingHistory();
            merchandiseBrowsingHistory.setBrowsingTime(new Date());
            merchandiseBrowsingHistory.setClientType(clientType);
            merchandiseBrowsingHistory.setUnifiedMerchandiseId(unifiedMerchandiseId);
            merchandiseBrowsingHistory.setUserId(user.getId());
            merchandiseBrowsingHistoryService.add(merchandiseBrowsingHistory);
        }
        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "统一商品不存在." + unifiedMerchandiseId);
        AssertUtil.assertTrue(!MerchandiseType.COMBINATION.equals(unifiedMerchandise.getType()), "组合商品数据不在这个接口提供." + unifiedMerchandiseId);
        MerchandiseItem merchandiseItem = merchandiseItemService.get(unifiedMerchandise.getList().get(0).getId());
        AssertUtil.assertNotNull(merchandiseItem, "单一商品不存在." + unifiedMerchandiseId);
        Merchandise merchandise = merchandiseService.get(merchandiseItem.getMerchandiseId());
        AssertUtil.assertNotNull(merchandise, "商品不存在." + merchandiseItem.getMerchandiseId());
        MerchandiseVO merchandiseVO = merchandiseHandler.toVO(merchandise);
        merchandiseVO.setPrice(merchandiseItem.getPrice());
        merchandiseVO.setDiscount(merchandiseItem.getDiscount());
        merchandiseVO.setSalesVolume(merchandiseItem.getSalesVolume());
        //
        long sum = merchandiseItem.getGoodEvaluation() + merchandiseItem.getMiddleEvaluation() + merchandiseItem.getLowEvaluation();
        int goodEvaluationRate = 0;
        if (sum != 0) {
            goodEvaluationRate = new Double(merchandiseItem.getGoodEvaluation() * 100 / sum).intValue();
        }
        merchandiseVO.setGoodEvaluationRate(goodEvaluationRate);
        merchandiseVO.setEvaluationNumber(sum);
        //
        if (StringUtils.isNotEmpty(merchandise.getImage())) {
            merchandiseVO.setImage(fileSDKClient.getFileServerUrl() + merchandise.getImage());
        }
        merchandiseVO.setUnifiedMerchandiseId(merchandiseItem.getUnifiedMerchandiseId());
        // vip 价格
        double minVip = merchandiseVipDiscountService.statMin(merchandiseItem.getId());
        double maxVip = merchandiseVipDiscountService.statMax(merchandiseItem.getId());
        minVip = minVip > merchandiseItem.getDiscount() || new Double(minVip).longValue() <= 0 ? merchandiseItem.getDiscount() : minVip;
        maxVip = maxVip > merchandiseItem.getDiscount() || new Double(maxVip).longValue() <= 0 ? merchandiseItem.getDiscount() : maxVip;
        merchandiseVO.setMinVipDiscount(minVip);
        merchandiseVO.setMaxVipDiscount(maxVip);
        merchandiseVO.setVipDiscount(0);
        if (user != null) {
            MerchandiseVipDiscount merchandiseVipDiscount = merchandiseVipDiscountService.get(user.getId(), merchandiseItem.getId());
            merchandiseVO.setVipDiscount(merchandiseVipDiscount == null ? 0 : merchandiseVipDiscount.getPrice());
        }
        //
        merchandiseVO.setSpecifications("");
        MerchandiseExtVO merchandiseExtVO = new MerchandiseExtVO();
        //
        merchandiseExtVO.setCertified(EnableType.ENABLE.getKey() == merchandise.getIsCertified());
        merchandiseExtVO.setCertifiedDesc(merchandise.getCertified());
        merchandiseExtVO.setSpecialService(EnableType.ENABLE.getKey() == merchandise.getIsSpecialService());
        merchandiseExtVO.setSpecialServiceDesc(merchandise.getSpecialService());
        merchandiseExtVO.setNoReason(EnableType.ENABLE.getKey() == merchandise.getIsNoReason());
        merchandiseExtVO.setNoReasonDesc(merchandise.getNoReason());
        //
        // TODO 邮费计算,判断商家是否包邮
        merchandiseExtVO.setFreeShipping(merchandise.getIsIncludePost() == MerchandiseIsIncludePost.YES.getKey());
        //
        merchandiseExtVO.setStock(merchandiseItem.getStock());
        merchandiseExtVO.setEnable(MerchandiseStateType.ONLINE.getKey() == merchandiseItem.getState());
        merchandiseExtVO.setGoodEvaluationNumber(merchandiseItem.getGoodEvaluation());
        merchandiseExtVO.setMiddleEvaluationNumber(merchandiseItem.getMiddleEvaluation());
        merchandiseExtVO.setLowEvaluationNumber(merchandiseItem.getLowEvaluation());
        // 规格
        MerchandiseSpecifications merchandiseSpecifications = merchandiseSpecificationsService.get(merchandiseItem.getMerchandiseSpecificationsId());
        if (merchandiseSpecifications != null) {
            String specificationsStr = "";
            List<String> imageStrList = new ArrayList<String>();
            if (merchandiseSpecifications.getAttributeId0() > 0) {
                merchandiseExtVO.setAttributeId0(merchandiseSpecifications.getAttributeId0());
                merchandiseExtVO.setValue0(merchandiseSpecifications.getValue0());
                specificationsStr = merchandiseSpecifications.getValue0();
                List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), merchandiseSpecifications.getAttributeId0(), merchandiseSpecifications.getValue0());
                for (MerchandiseImage merchandiseImage : imageList) {
                    if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
                        String[] strs = merchandiseImage.getImage().split(",");
                        for (int index = 0; index < strs.length; index++) {
                            imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
                        }
                    }
                }
            }
            if (merchandiseSpecifications.getAttributeId1() > 0) {
                merchandiseExtVO.setAttributeId1(merchandiseSpecifications.getAttributeId1());
                merchandiseExtVO.setValue1(merchandiseSpecifications.getValue1());
                specificationsStr += " " + merchandiseSpecifications.getValue1();
                List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), merchandiseSpecifications.getAttributeId1(), merchandiseSpecifications.getValue1());
                for (MerchandiseImage merchandiseImage : imageList) {
                    if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
                        String[] strs = merchandiseImage.getImage().split(",");
                        for (int index = 0; index < strs.length; index++) {
                            imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
                        }
                    }
                }
            }
            if (merchandiseSpecifications.getAttributeId2() > 0) {
                merchandiseExtVO.setAttributeId2(merchandiseSpecifications.getAttributeId2());
                merchandiseExtVO.setValue2(merchandiseSpecifications.getValue2());
                specificationsStr += " " + merchandiseSpecifications.getValue2();
                List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), merchandiseSpecifications.getAttributeId2(), merchandiseSpecifications.getValue2());
                for (MerchandiseImage merchandiseImage : imageList) {
                    if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
                        String[] strs = merchandiseImage.getImage().split(",");
                        for (int index = 0; index < strs.length; index++) {
                            imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
                        }
                    }
                }
            }
            merchandiseVO.setSpecifications(specificationsStr);
            merchandiseExtVO.setImageList(imageStrList);
        }
        // 为空则取默认图
        if (CollectionUtils.isEmpty(merchandiseExtVO.getImageList())) {
            List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), -1L, "-1");
            List<String> imageStrList = new ArrayList<String>();
            for (MerchandiseImage merchandiseImage : imageList) {
                if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
                    String[] strs = merchandiseImage.getImage().split(",");
                    for (int index = 0; index < strs.length; index++) {
                        imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
                    }
                }
            }
            merchandiseExtVO.setImageList(imageStrList);
        }
        merchandiseExtVO.setShareUrl(shareClient.getShareDomain() + "/merchandiseShare.html?unifiedMerchandiseId=" + unifiedMerchandiseId);
        //
        Page<MerchandiseEvaluation> page = merchandiseEvaluationService.page(merchandiseItem.getMerchandiseId(), StarLevelType.ALL, 0, 1);
        MerchandiseEvaluationVO merchandiseEvaluationVO = null;
        if (CollectionUtils.isNotEmpty(page.getData())) {
            List<MerchandiseEvaluationVO> voList = merchandiseEvaluationHandler.toVOList(page.getData());
            merchandiseEvaluationVO = voList.get(0);
        }
        MerchantMerchandiseVO merchantMerchandiseVO = new MerchantMerchandiseVO();
        int collectNumber = myClient.countMerchantCollectionNumber(merchandiseItem.getMerchantId());
        // int collectNumber = personalcenterClient.my().countMerchandiseCollectionNumber(unifiedMerchandiseId);
        merchantMerchandiseVO.setCollectNumber(collectNumber);
        int merchandiseNumber = merchandiseService.countMerchantOnlineNumber(merchandiseItem.getMerchantId());
        merchantMerchandiseVO.setMerchandiseNumber(merchandiseNumber);
        merchantMerchandiseVO.setMerchantId(merchandiseItem.getMerchantId());
        QMerchant merchant = sellercenterClient.getMerchant(merchandiseItem.getMerchantId());
        merchantMerchandiseVO.setMerchantName(merchant.getName());
        if (StringUtils.isEmpty(merchant.getProvince())) {
            merchantMerchandiseVO.setDeliveryPlace(StringUtil.nullToEmpty(merchant.getCity()));
        } else {
            merchantMerchandiseVO.setDeliveryPlace(merchant.getProvince() + " " + StringUtil.nullToEmpty(merchant.getCity()));
        }
        merchantMerchandiseVO.setMerchantImage(fileSDKClient.getFileServerUrl() + merchant.getImage());
        //
        List<KeyValueVO> sexpressList = sellercenterClient.listExpress(merchant);
        List<SexpressMerchandiseVO> sexpressVOList = new ArrayList<SexpressMerchandiseVO>();
        for (KeyValueVO keyValueVO : sexpressList) {
            SexpressMerchandiseVO sexpressMerchandiseVO = new SexpressMerchandiseVO();
            sexpressMerchandiseVO.setSexpressCode(keyValueVO.getKey());
            sexpressMerchandiseVO.setSexpressName(keyValueVO.getValue());
            double postage = sellercenterClient.calculatePostage(sexpressMerchandiseVO.getSexpressCode(), merchant.getId(), merchandise.getWeight(), "");
            sexpressMerchandiseVO.setPostage(postage);
            sexpressVOList.add(sexpressMerchandiseVO);
        }
        List<MerchandiseAttribute> list = merchandiseAttributeService.listByMerchandise(merchandise.getId());
        List<MerchandiseAttributeVO> voList = merchandiseAttributeHandler.toVOList(list);
        Map<String, MerchandiseAttributeVO> attrMap = new HashMap<String, MerchandiseAttributeVO>();
        for (MerchandiseAttributeVO merchandiseAttributeVO : voList) {
            attrMap.put(merchandiseAttributeVO.getCode(), merchandiseAttributeVO);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("attrMap", attrMap);
        view.addObject("attrList", voList);
        //
        view.setMessage("获取商品详情成功.");
        view.addObject("merchandise", merchandiseVO);
        view.addObject("merchandiseExt", merchandiseExtVO);
        view.addObject("merchantExt", merchantMerchandiseVO);
        view.addObject("sexpressList", sexpressVOList);
        view.addObject("evaluation", merchandiseEvaluationVO == null ? new HashMap<String, String>() : merchandiseEvaluationVO);
        return view;
    }

    @RequestMapping
    public FrontAjaxView getDetailByUnifiedMerchandise(Long unifiedMerchandiseId) {

        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        if (MerchandiseType.SINGLE.equals(unifiedMerchandise.getType())) {
            Long merchantItemId = unifiedMerchandise.getList().get(0).getId();
            MerchandiseItem merchandiseItem = merchandiseItemService.get(merchantItemId);
            return getDetailByMerchandise(merchandiseItem.getMerchandiseId());
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取商品图文详情失败.");
        view.addObject("detail", "");
        return view;
    }

    @RequestMapping
    public FrontAjaxView getDetailByMerchandise(Long merchandiseId) {

        Merchandise merchandise = merchandiseService.get(merchandiseId);
        AssertUtil.assertNotNull(merchandise, "商品不存在." + merchandiseId);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取商品图文详情成功.");
        view.addObject("detail", merchandise.getDetails());
        return view;
    }

    @RequestMapping
    public HtmlView getHtmlDetailByUnifiedMerchandise(Long unifiedMerchandiseId) {

        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        if (MerchandiseType.SINGLE.equals(unifiedMerchandise.getType()) || MerchandiseType.MARKETING.equals(unifiedMerchandise.getType())) {
            Long merchantItemId = unifiedMerchandise.getList().get(0).getId();
            MerchandiseItem merchandiseItem = merchandiseItemService.get(merchantItemId);
            return getHtmlDetailByMerchandise(merchandiseItem.getMerchandiseId());
        }
        HtmlView view = new HtmlView("");
        return view;
    }

    @RequestMapping
    public HtmlView getHtmlDetailByMerchandise(Long merchandiseId) {

        Merchandise merchandise = merchandiseService.get(merchandiseId);
        AssertUtil.assertNotNull(merchandise, "商品不存在." + merchandiseId);
        HtmlView view = new HtmlView("<style>img{width:100%;} </style>" + merchandise.getDetails());
        return view;
    }

    @RequestMapping
    public FrontAjaxView guess(Integer number) {

        return guess4Merchant(-1L, number);
    }

    @RequestMapping
    public FrontAjaxView guess4Merchant(Long merchantId, Integer number) {

        number = number == null || number <= 0 || number > 20 ? 8 : number;
        List<QUnifiedMerchandise> list = commoditycenterClient.randomUnifiedMerchandise(merchantId, number);
        List<SimpleMerchandiseVO> voList = merchandiseHandler.toSimpleVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取猜你喜欢成功.");
        view.addObject("list", voList);
        return view;
    }

    @RequestMapping
    public FrontAjaxView listHotSearch(Integer number) {

        number = number == null || number <= 0 || number > 100 ? 20 : number;
        List<String> list = publicdataClient.listHotSearch(CommoditycenterClient.SEARCH_TYPE, number);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取热搜商品关键字成功.");
        view.addObject("list", list);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listMySearch(HttpServletRequest request, Integer number) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        number = number == null || number <= 0 || number > 100 ? 20 : number;
        List<String> list = myClient.listMySearchHistory(user.getId(), CommoditycenterClient.SEARCH_TYPE, number);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的商品搜索历史成功.");
        view.addObject("list", list);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView clearMySearch(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        myClient.clearMySearchHistory(user.getId(), CommoditycenterClient.SEARCH_TYPE);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("清空搜索历史成功.");
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listShopRecommend(Long unifiedMerchandiseId, Integer number) {

        number = number == null || number <= 0 || number > 20 ? 8 : number;
        QUnifiedMerchandise qUnifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
        AssertUtil.assertNotNull(qUnifiedMerchandise, "统一商品不存在.");
        List<QUnifiedMerchandise> list = commoditycenterClient.randomUnifiedMerchandise(qUnifiedMerchandise.getMerchantId(), number);
        List<SimpleMerchandiseVO> voList = merchandiseHandler.toSimpleVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取店铺推荐成功.");
        view.addObject("list", voList);
        return view;
    }
}
