package com.qcloud.component.goods.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.goods.model.CombinationMerchandiseItem;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.MerchandiseAttribute;
import com.qcloud.component.goods.model.MerchandiseBrowsingHistory;
import com.qcloud.component.goods.model.MerchandiseEvaluation;
import com.qcloud.component.goods.model.MerchandiseImage;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.model.MerchandiseVipDiscount;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.MerchandiseIsIncludePost;
import com.qcloud.component.goods.model.key.TypeEnum.MerchandiseStateType;
import com.qcloud.component.goods.model.key.TypeEnum.OrderType;
import com.qcloud.component.goods.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.goods.model.key.TypeEnum.QueryType;
import com.qcloud.component.goods.model.key.TypeEnum.StarLevelType;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
import com.qcloud.component.goods.service.CombinationMerchandiseItemService;
import com.qcloud.component.goods.service.MerchandiseAttributeService;
import com.qcloud.component.goods.service.MerchandiseBrowsingHistoryService;
import com.qcloud.component.goods.service.MerchandiseEvaluationService;
import com.qcloud.component.goods.service.MerchandiseImageService;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.service.MerchandiseSpecificationsRelationService;
import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.goods.service.MerchandiseVipDiscountService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.form.MerchandiseForm;
import com.qcloud.component.goods.web.form.SpecificationsForm;
import com.qcloud.component.goods.web.form.SpecificationsFormList;
import com.qcloud.component.goods.web.handler.ClassifySpecificationsHandler;
import com.qcloud.component.goods.web.handler.CombinationMerchandiseHandler;
import com.qcloud.component.goods.web.handler.KV;
import com.qcloud.component.goods.web.handler.MerchandiseAttributeHandler;
import com.qcloud.component.goods.web.handler.MerchandiseEvaluationHandler;
import com.qcloud.component.goods.web.handler.MerchandiseHandler;
import com.qcloud.component.goods.web.vo.AttributeSpecificationsVO;
import com.qcloud.component.goods.web.vo.CombinationMerchandiseVO;
import com.qcloud.component.goods.web.vo.MerchandiseAttributeVO;
import com.qcloud.component.goods.web.vo.MerchandiseEvaluationVO;
import com.qcloud.component.goods.web.vo.MerchandiseExtVO;
import com.qcloud.component.goods.web.vo.MerchandiseVO;
import com.qcloud.component.goods.web.vo.MerchantMerchandiseVO;
import com.qcloud.component.goods.web.vo.SexpressMerchandiseVO;
import com.qcloud.component.goods.web.vo.SimpleMerchandiseVO;
import com.qcloud.component.my.MyClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.publicdata.IntKeyValue;
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
    private UnifiedMerchandiseService                unifiedMerchandiseService;

    @Autowired
    private MerchandiseHandler                       merchandiseHandler;

    @Autowired
    private FileSDKClient                            fileSDKClient;

    @Autowired
    private MerchandiseImageService                  merchandiseImageService;

    @Autowired
    private CombinationMerchandiseHandler            combinationMerchandiseHandler;

    @Autowired
    private CombinationMerchandiseItemService        combinationMerchandiseItemService;

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
    @PiratesApp
    @RequestMapping
    public FrontPagingView query(HttpServletRequest request, PPage pPage, MerchandiseForm form) {

        return query(request, pPage, form, QueryItemType.M);
    }

    @PiratesApp
    @RequestMapping
    public FrontPagingView queryBySpecifications(HttpServletRequest request, PPage pPage, MerchandiseForm form) {

        return query(request, pPage, form, QueryItemType.S);
    }

    //
    private FrontPagingView query(HttpServletRequest request, PPage pPage, MerchandiseForm form, QueryItemType type) {

        UnifiedMerchandiseQuery query = new UnifiedMerchandiseQuery();
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
        query.setMaxDiscount(form.getMaxDiscount());
        query.setMinDiscount(form.getMinDiscount());
        Page<UnifiedMerchandise> page = unifiedMerchandiseService.page(query, pPage.getPageStart(), pPage.getPageSize().intValue());
        List<UnifiedMerchandise> list = page.getData();
        List<MerchandiseVO> voList = new ArrayList<MerchandiseVO>();
        for (UnifiedMerchandise unifiedMerchandise : list) {
            Merchandise merchandise = merchandiseService.get(unifiedMerchandise.getMerchandiseId());
            MerchandiseVO merchandiseVO = merchandiseHandler.toVO(merchandise);
            merchandiseVO.setStock(unifiedMerchandise.getStock());
            merchandiseVO.setPrice(unifiedMerchandise.getPrice());
            merchandiseVO.setDiscount(unifiedMerchandise.getDiscount());
            merchandiseVO.setSalesVolume(unifiedMerchandise.getSalesVolume() + unifiedMerchandise.getVirtualSalesVolume());
            if (QueryItemType.M.equals(type)) {
                merchandiseVO.setSalesVolume(merchandiseService.getSalesVolume(merchandise.getId()) + unifiedMerchandise.getVirtualSalesVolume());
            }
            if (StringUtils.isNotEmpty(merchandise.getImage())) {
                merchandiseVO.setImage(fileSDKClient.getFileServerUrl() + merchandise.getImage());
            }
            merchandiseVO.setUnifiedMerchandiseId(unifiedMerchandise.getId());
            merchandiseVO.setSpecifications("");
            if (!QueryItemType.M.equals(type)) {
                List<MerchandiseSpecifications> merchandiseSpecificationses = merchandiseSpecificationsService.listByUnifiedMerchandise(unifiedMerchandise.getId());
                StringBuffer sb = new StringBuffer();
                for (MerchandiseSpecifications merchandiseSpecifications : merchandiseSpecificationses) {
                    sb.append(merchandiseSpecifications.getValue()).append(" ");
                }
                merchandiseVO.setSpecifications(sb.toString());
            }
            long sum = unifiedMerchandise.getGoodEvaluation() + unifiedMerchandise.getMiddleEvaluation() + unifiedMerchandise.getLowEvaluation();
            int goodEvaluationRate = 0;
            if (sum != 0) {
                goodEvaluationRate = new Double(unifiedMerchandise.getGoodEvaluation() * 100 / sum).intValue();
            }
            merchandiseVO.setGoodEvaluationRate(goodEvaluationRate);
            // vip 价格
            double minVip = merchandiseVipDiscountService.statMin(unifiedMerchandise.getId());
            double maxVip = merchandiseVipDiscountService.statMax(unifiedMerchandise.getId());
            minVip = minVip > unifiedMerchandise.getDiscount() || new Double(minVip).longValue() <= 0 ? unifiedMerchandise.getDiscount() : minVip;
            maxVip = maxVip > unifiedMerchandise.getDiscount() || new Double(maxVip).longValue() <= 0 ? unifiedMerchandise.getDiscount() : maxVip;
            merchandiseVO.setMinVipDiscount(minVip);
            merchandiseVO.setMaxVipDiscount(maxVip);
            merchandiseVO.setVipDiscount(0);
            if (user != null) {
                MerchandiseVipDiscount merchandiseVipDiscount = merchandiseVipDiscountService.get(user.getId(), unifiedMerchandise.getId());
                // 目前只管价格,暂时不处理折扣
                merchandiseVO.setVipDiscount(merchandiseVipDiscount == null ? 0 : merchandiseVipDiscount.getPrice());
            }
            // 加入购物车的数量
            if (user != null) {
                int number = myClient.getMyShoppingCartMerchandiseNumber(user.getId(), unifiedMerchandise.getId());
                merchandiseVO.setNumber(number);
            } else {
                merchandiseVO.setNumber(0);
            }
            //
            QMerchant merchant = sellercenterClient.getMerchant(merchandiseVO.getMerchantId());
            merchandiseVO.setMerchantName(merchant.getName());
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
    @PiratesApp
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
    @PiratesApp
    @RequestMapping
    public FrontAjaxView getBySpecifications(HttpServletRequest request, Long merchandiseId, SpecificationsFormList specificationsList) {

        UnifiedMerchandise merchandise = getMerchandiseItemBySpecifications(merchandiseId, specificationsList);
        return getByUnifiedMerchandise(request, merchandise.getId());
    }

    // 依据商品规格,获取商品详情
    @RequestMapping
    public FrontAjaxView getPriceAndStockBySpecifications(Long merchandiseId, SpecificationsFormList specificationsList) {

        UnifiedMerchandise unifiedMerchandise = getMerchandiseItemBySpecifications(merchandiseId, specificationsList);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取商品价格与库存成功.");
        view.addObject("price", unifiedMerchandise.getPrice());
        view.addObject("discount", unifiedMerchandise.getDiscount());
        view.addObject("stock", unifiedMerchandise.getStock());
        return view;
    }

    private UnifiedMerchandise getMerchandiseItemBySpecifications(Long merchandiseId, SpecificationsFormList specificationsList) {

        List<MerchandiseSpecifications> list = merchandiseSpecificationsService.listByMerchandise(merchandiseId);
        List<SpecificationsForm> sl = specificationsList.getSpecificationsList();
        IntKeyValue[] intKeyValues = new IntKeyValue[sl.size()];
        for (int index = 0; index < intKeyValues.length; index++) {
            SpecificationsForm specificationsForm = sl.get(index);
            IntKeyValue kv = new KV(specificationsForm.getAttributeId(), specificationsForm.getValue());
        }
        List<MerchandiseSpecifications> es = merchandiseSpecificationsService.getSpecifications(list, intKeyValues);
        long unifiedMerchandiseId = es.get(0).getUnifiedMerchandiseId();
        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "找不到指定的规格商品merchandiseId." + merchandiseId);
        return unifiedMerchandise;
    }

    // 依据单一商品ID,获取商品详情
    @PiratesApp
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
        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(unifiedMerchandiseId);
        AssertUtil.assertNotNull(unifiedMerchandise, "统一商品不存在." + unifiedMerchandiseId);
        AssertUtil.assertTrue(!UnifiedMerchandiseType.COMBINATION.equals(unifiedMerchandise.getType()), "组合商品数据不在这个接口提供." + unifiedMerchandiseId);
        Merchandise merchandise = merchandiseService.get(unifiedMerchandise.getMerchandiseId());
        AssertUtil.assertNotNull(merchandise, "商品不存在." + unifiedMerchandise.getMerchandiseId());
        MerchandiseVO merchandiseVO = merchandiseHandler.toVO(merchandise);
        merchandiseVO.setPrice(unifiedMerchandise.getPrice());
        merchandiseVO.setDiscount(unifiedMerchandise.getDiscount());
        merchandiseVO.setSalesVolume(unifiedMerchandise.getSalesVolume());
        merchandiseVO.setStock(unifiedMerchandise.getStock());
        //
        long sum = unifiedMerchandise.getGoodEvaluation() + unifiedMerchandise.getMiddleEvaluation() + unifiedMerchandise.getLowEvaluation();
        int goodEvaluationRate = 0;
        if (sum != 0) {
            goodEvaluationRate = new Double(unifiedMerchandise.getGoodEvaluation() * 100 / sum).intValue();
        }
        merchandiseVO.setGoodEvaluationRate(goodEvaluationRate);
        merchandiseVO.setEvaluationNumber(sum);
        //
        if (StringUtils.isNotEmpty(merchandise.getImage())) {
            merchandiseVO.setImage(fileSDKClient.getFileServerUrl() + merchandise.getImage());
        }
        merchandiseVO.setUnifiedMerchandiseId(unifiedMerchandise.getId());
        // vip 价格
        double minVip = merchandiseVipDiscountService.statMin(unifiedMerchandise.getId());
        double maxVip = merchandiseVipDiscountService.statMax(unifiedMerchandise.getId());
        minVip = minVip > unifiedMerchandise.getDiscount() || new Double(minVip).longValue() <= 0 ? unifiedMerchandise.getDiscount() : minVip;
        maxVip = maxVip > unifiedMerchandise.getDiscount() || new Double(maxVip).longValue() <= 0 ? unifiedMerchandise.getDiscount() : maxVip;
        merchandiseVO.setMinVipDiscount(minVip);
        merchandiseVO.setMaxVipDiscount(maxVip);
        merchandiseVO.setVipDiscount(0);
        if (user != null) {
            MerchandiseVipDiscount merchandiseVipDiscount = merchandiseVipDiscountService.get(user.getId(), unifiedMerchandise.getId());
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
        merchandiseExtVO.setStock(unifiedMerchandise.getStock());
        merchandiseExtVO.setEnable(MerchandiseStateType.ONLINE.getKey() == unifiedMerchandise.getState());
        merchandiseExtVO.setGoodEvaluationNumber(unifiedMerchandise.getGoodEvaluation());
        merchandiseExtVO.setMiddleEvaluationNumber(unifiedMerchandise.getMiddleEvaluation());
        merchandiseExtVO.setLowEvaluationNumber(unifiedMerchandise.getLowEvaluation());
        // 规格
        List<MerchandiseSpecifications> merchandiseSpecificationses = merchandiseSpecificationsService.listByUnifiedMerchandise(unifiedMerchandise.getId());
        // TODO 先用着
        int i = 0;
        List<String> imageStrList = new ArrayList<String>();
        String specificationsStr = "";
        for (MerchandiseSpecifications merchandiseSpecifications : merchandiseSpecificationses) {
            if (i == 0) {
                merchandiseExtVO.setAttributeId0(merchandiseSpecifications.getAttributeId());
                merchandiseExtVO.setValue0(merchandiseSpecifications.getValue());
            } else if (i == 1) {
                merchandiseExtVO.setAttributeId1(merchandiseSpecifications.getAttributeId());
                merchandiseExtVO.setValue1(merchandiseSpecifications.getValue());
            } else if (i == 2) {
                merchandiseExtVO.setAttributeId2(merchandiseSpecifications.getAttributeId());
                merchandiseExtVO.setValue2(merchandiseSpecifications.getValue());
            }
            i++;
            //
            specificationsStr = merchandiseSpecifications.getValue() + " ";
            if (merchandiseSpecifications.getAttributeId() > 0) {
                List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), merchandiseSpecifications.getAttributeId(), merchandiseSpecifications.getValue());
                for (MerchandiseImage merchandiseImage : imageList) {
                    if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
                        String[] strs = merchandiseImage.getImage().split(",");
                        for (int index = 0; index < strs.length; index++) {
                            imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
                        }
                    }
                }
            }
        }
        merchandiseVO.setSpecifications(specificationsStr);
        merchandiseExtVO.setImageList(imageStrList);
        // 为空则取默认图
        if (CollectionUtils.isEmpty(merchandiseExtVO.getImageList())) {
            List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), -1L, "-1");
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
        Page<MerchandiseEvaluation> page = merchandiseEvaluationService.page(merchandise.getId(), StarLevelType.ALL, 0, 1);
        MerchandiseEvaluationVO merchandiseEvaluationVO = null;
        if (CollectionUtils.isNotEmpty(page.getData())) {
            List<MerchandiseEvaluationVO> voList = merchandiseEvaluationHandler.toVOList(page.getData());
            merchandiseEvaluationVO = voList.get(0);
        }
        MerchantMerchandiseVO merchantMerchandiseVO = new MerchantMerchandiseVO();
        int collectNumber = myClient.countMerchantCollectionNumber(merchandise.getMerchantId());
        merchantMerchandiseVO.setCollectNumber(collectNumber);
        int merchandiseNumber = merchandiseService.countMerchantOnlineNumber(merchandise.getMerchantId());
        merchantMerchandiseVO.setMerchandiseNumber(merchandiseNumber);
        merchantMerchandiseVO.setMerchantId(merchandise.getMerchantId());
        QMerchant merchant = sellercenterClient.getMerchant(merchandise.getMerchantId());
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
        // 组合商品 TODO
        List<CombinationMerchandiseVO> comVOList = new ArrayList<CombinationMerchandiseVO>();
        Set<Long> combinationMerchandiseIdList = new HashSet<Long>();
        List<CombinationMerchandiseItem> combinationItemList = combinationMerchandiseItemService.listByMerchandiseItem(unifiedMerchandise.getId(), 0, Integer.MAX_VALUE);
        for (CombinationMerchandiseItem combinationMerchandiseItem : combinationItemList) {
            if (!combinationMerchandiseIdList.contains(combinationMerchandiseItem.getCombinationUnifiedMerchandiseId())) {
                UnifiedMerchandise combinationMerchandise = unifiedMerchandiseService.get(combinationMerchandiseItem.getCombinationUnifiedMerchandiseId());
                CombinationMerchandiseVO combinationMerchandiseVO = combinationMerchandiseHandler.toVO(combinationMerchandise);
                combinationMerchandiseVO.setType(combinationMerchandiseItem.getType());
                combinationMerchandiseIdList.add(combinationMerchandiseItem.getCombinationUnifiedMerchandiseId());
                comVOList.add(combinationMerchandiseVO);
            }
        }
        double topVoucher = 0.0;
        for (CombinationMerchandiseVO combination : comVOList) {
            if (combination.getSurplus() > topVoucher) {
                topVoucher = combination.getSurplus();
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("combinationList", comVOList);
        //
        view.addObject("attrMap", attrMap);
        view.addObject("attrList", voList);
        //
        view.setMessage("获取商品详情成功.");
        view.addObject("merchandise", merchandiseVO);
        view.addObject("merchandiseExt", merchandiseExtVO);
        view.addObject("merchantExt", merchantMerchandiseVO);
        view.addObject("sexpressList", sexpressVOList);
        view.addObject("evaluation", merchandiseEvaluationVO == null ? new HashMap<String, String>() : merchandiseEvaluationVO);
        view.addObject("topVoucher", topVoucher);// 最高优惠
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getDetailByUnifiedMerchandise(Long unifiedMerchandiseId) {

        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(unifiedMerchandiseId);
        if (UnifiedMerchandiseType.SINGLE.getKey() == unifiedMerchandise.getType()) {
            return getDetailByMerchandise(unifiedMerchandise.getMerchandiseId());
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取商品图文详情失败.");
        view.addObject("detail", "");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getDetailByMerchandise(Long merchandiseId) {

        Merchandise merchandise = merchandiseService.get(merchandiseId);
        AssertUtil.assertNotNull(merchandise, "商品不存在." + merchandiseId);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取商品图文详情成功.");
        view.addObject("detail", merchandise.getDetails());
        return view;
    }

    @PiratesApp
    @RequestMapping
    public HtmlView getHtmlDetailByUnifiedMerchandise(Long unifiedMerchandiseId) {

        UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(unifiedMerchandiseId);
        if (UnifiedMerchandiseType.COMBINATION.getKey() != unifiedMerchandise.getType()) {
            return getHtmlDetailByMerchandise(unifiedMerchandise.getMerchandiseId());
        }
        HtmlView view = new HtmlView("");
        return view;
    }

    @PiratesApp
    @RequestMapping
    public HtmlView getHtmlDetailByMerchandise(Long merchandiseId) {

        Merchandise merchandise = merchandiseService.get(merchandiseId);
        AssertUtil.assertNotNull(merchandise, "商品不存在." + merchandiseId);
        HtmlView view = new HtmlView("<style>img{width:100%;} </style>" + merchandise.getDetails());
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView guess(Integer number) {

        return guess4Merchant(-1L, number);
    }

    @PiratesApp
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

    @PiratesApp
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
    public FrontAjaxView listMySearchAndTags(HttpServletRequest request, Integer number) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        number = number == null || number <= 0 || number > 100 ? 20 : number;
        List<String> list = myClient.listMySearchHistory(user.getId(), CommoditycenterClient.SEARCH_TYPE, number);
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        for (String string : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", string);
            List<String> labels = new ArrayList<String>();
            labels.add("大保健");
            labels.add("保健用品");
            labels.add("肥皂");
            map.put("labels", labels);
            listMap.add(map);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的商品搜索历史成功.");
        view.addObject("list", listMap);
        return view;
    }
}
