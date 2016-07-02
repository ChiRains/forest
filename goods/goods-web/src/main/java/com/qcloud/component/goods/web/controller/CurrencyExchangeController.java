//package com.qcloud.component.goods.web.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.qcloud.component.goods.CommoditycenterClient;
//import com.qcloud.component.goods.QUnifiedMerchandise;
//import com.qcloud.component.goods.QUnifiedMerchandise.MerchandiseType;
//import com.qcloud.component.goods.model.Merchandise;
//import com.qcloud.component.goods.model.MerchandiseEvaluation;
//import com.qcloud.component.goods.model.MerchandiseImage;
//import com.qcloud.component.goods.model.MerchandiseItem;
//import com.qcloud.component.goods.model.MerchandiseMarketing;
//import com.qcloud.component.goods.model.MerchandiseSpecifications;
//import com.qcloud.component.goods.model.key.TypeEnum.StarLevelType;
//import com.qcloud.component.goods.service.MerchandiseEvaluationService;
//import com.qcloud.component.goods.service.MerchandiseImageService;
//import com.qcloud.component.goods.service.MerchandiseItemService;
//import com.qcloud.component.goods.service.MerchandiseMarketingService;
//import com.qcloud.component.goods.service.MerchandiseService;
//import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
//import com.qcloud.component.goods.web.handler.ExchangeMerchandiseHandler;
//import com.qcloud.component.goods.web.handler.MerchandiseEvaluationHandler;
//import com.qcloud.component.goods.web.vo.ExchangeMerchandiseListVO;
//import com.qcloud.component.goods.web.vo.ExchangeMerchandiseVO;
//import com.qcloud.component.goods.web.vo.MerchandiseEvaluationVO;
//import com.qcloud.component.goods.web.vo.MerchantMerchandiseVO;
//import com.qcloud.component.filesdk.FileSDKClient;
//import com.qcloud.component.my.MyClient;
//import com.qcloud.component.personalcenter.PersonalcenterClient;
//import com.qcloud.component.personalcenter.QUser;
//import com.qcloud.component.publicdata.KeyValueVO;
//import com.qcloud.component.publicdata.PublicdataClient;
//import com.qcloud.component.publicservice.ShareClient;
//import com.qcloud.component.sellercenter.QMerchant;
//import com.qcloud.component.sellercenter.SellercenterClient;
//import com.qcloud.pirates.core.xml.Xml;
//import com.qcloud.pirates.core.xml.XmlFactory;
//import com.qcloud.pirates.core.xml.XmlItem;
//import com.qcloud.pirates.data.Page;
//import com.qcloud.pirates.mvc.FrontAjaxView;
//import com.qcloud.pirates.util.AssertUtil;
//import com.qcloud.pirates.util.StringUtil;
//import com.qcloud.pirates.web.page.PPage;
//import com.qcloud.pirates.web.page.PageParameterUtil;
//
//@Controller
//@RequestMapping(value = CurrencyExchangeController.DIR)
//public class CurrencyExchangeController {
//
//    public static final String       DIR                    = "/currencyExchange";
//
//    @Autowired
//    MerchandiseMarketingService      merchandiseMarketingService;
//
//    @Autowired
//    ExchangeMerchandiseHandler       exchangeMerchandiseHandler;
//
//    @Autowired
//    CommoditycenterClient            commoditycenterClient;
//
//    @Autowired
//    MerchandiseItemService           merchandiseItemService;
//
//    @Autowired
//    MerchandiseService               merchandiseService;
//
//    @Autowired
//    FileSDKClient                    fileSDKClient;
//
//    @Autowired
//    MerchandiseSpecificationsService merchandiseSpecificationsService;
//
//    @Autowired
//    MerchandiseImageService          merchandiseImageService;
//
//    @Autowired
//    MerchandiseEvaluationService     merchandiseEvaluationService;
//
//    @Autowired
//    MerchandiseEvaluationHandler     merchandiseEvaluationHandler;
//
//    @Autowired
//    SellercenterClient               sellercenterClient;
//
//    @Autowired
//    PersonalcenterClient             personalcenterClient;
//
//    @Autowired
//    MyClient                         myClient;
//
//    @Autowired
//    PublicdataClient                 publicdataClient;
//
//    @Autowired
//    private ShareClient              shareClient;
//
//    final static String              XML_MESSAGE_KEY        = "commoditycenter-currencyexchange-message";
//
//    final static String              currencyexchange_range = "commoditycenter-currencyexchange-range";
//
//    @RequestMapping
//    public FrontAjaxView list(HttpServletRequest request, Double currency, String keywords, PPage pPage) {
//
//        currency = currency == null || currency.longValue() <= 0 ? Long.MAX_VALUE : currency;
//        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_IS_LOGIN_PARAMETER_KEY);
//        // 已经登录
//        if (user != null) {
//            myClient.addMySearchHistory(user.getId(), CommoditycenterClient.SEARCH_TYPE, keywords);
//        }
//        publicdataClient.addSearch(CommoditycenterClient.SEARCH_TYPE, keywords);
//        List<MerchandiseMarketing> list = merchandiseMarketingService.list(7, keywords, currency, pPage.getPageStart(), pPage.getPageSize());
//        List<ExchangeMerchandiseListVO> voList = exchangeMerchandiseHandler.toVOList(list);
//        FrontAjaxView view = new FrontAjaxView();
//        view.setMessage("获取兑换商品成功.");
//        view.addObject("list", voList);
//        //
//        List<KeyValueVO> ddList = publicdataClient.listDataDictionaryByType(currencyexchange_range);
//        view.addObject("rangeList", ddList);
//        //
//        String title = "";
//        String message = "";
//        Xml xml = XmlFactory.get(XML_MESSAGE_KEY);
//        if (xml != null) {
//            List<XmlItem> messageList = xml.getItemList();
//            for (XmlItem xmlItem : messageList) {
//                if ("title".equals(xmlItem.getAttrMap().get("name"))) {
//                    title = xmlItem.getText();
//                }
//                if ("message".equals(xmlItem.getAttrMap().get("name"))) {
//                    message = xmlItem.getText();
//                }
//            }
//        }
//        view.addObject("title", title);
//        view.addObject("message", message);
//        return view;
//    }
//
//    @RequestMapping
//    public FrontAjaxView get(Long unifiedMerchandiseId) {
//
//        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(unifiedMerchandiseId);
//        AssertUtil.assertNotNull(unifiedMerchandise, "统一商品不存在." + unifiedMerchandiseId);
//        AssertUtil.assertTrue(MerchandiseType.MARKETING.equals(unifiedMerchandise.getType()), "这个接口仅提供兑兑劵兑换商品数据." + unifiedMerchandiseId);
//        AssertUtil.assertTrue(unifiedMerchandise.getSence() == 7, "这个接口仅提供兑兑劵兑换商品数据." + unifiedMerchandiseId);
//        MerchandiseItem merchandiseItem = merchandiseItemService.get(unifiedMerchandise.getList().get(0).getId());
//        AssertUtil.assertNotNull(merchandiseItem, "单一商品不存在." + unifiedMerchandiseId);
//        Merchandise merchandise = merchandiseService.get(merchandiseItem.getMerchandiseId());
//        AssertUtil.assertNotNull(merchandise, "商品不存在." + merchandiseItem.getMerchandiseId());
//        ExchangeMerchandiseVO exchangeMerchandiseVO = new ExchangeMerchandiseVO();
//        exchangeMerchandiseVO.setDesc(merchandise.getDesc());
//        exchangeMerchandiseVO.setMerchandiseId(merchandise.getId());
//        exchangeMerchandiseVO.setMerchantId(merchandise.getMerchantId());
//        exchangeMerchandiseVO.setName(merchandiseItem.getName());
//        exchangeMerchandiseVO.setStock(unifiedMerchandise.getStock());
//        // exchangeMerchandiseVO.setSysCode(merchandise.getSysCode());
//        exchangeMerchandiseVO.setUnifiedMerchandiseId(unifiedMerchandiseId);
//        exchangeMerchandiseVO.setCurrency(unifiedMerchandise.getDiscount());
//        exchangeMerchandiseVO.setPrice(merchandiseItem.getPrice());
//        if (StringUtils.isNotEmpty(merchandise.getImage())) {
//            exchangeMerchandiseVO.setImage(fileSDKClient.getFileServerUrl() + merchandise.getImage());
//        }
//        long sum = merchandiseItem.getGoodEvaluation() + merchandiseItem.getMiddleEvaluation() + merchandiseItem.getLowEvaluation();
//        exchangeMerchandiseVO.setEvaluationNumber(sum);
//        // 规格
//        MerchandiseSpecifications merchandiseSpecifications = merchandiseSpecificationsService.get(merchandiseItem.getMerchandiseSpecificationsId());
//        if (merchandiseSpecifications != null) {
//            List<String> imageStrList = new ArrayList<String>();
//            if (merchandiseSpecifications.getAttributeId0() > 0) {
//                List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), merchandiseSpecifications.getAttributeId0(), merchandiseSpecifications.getValue0());
//                for (MerchandiseImage merchandiseImage : imageList) {
//                    if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
//                        String[] strs = merchandiseImage.getImage().split(",");
//                        for (int index = 0; index < strs.length; index++) {
//                            imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
//                        }
//                    }
//                }
//            }
//            if (merchandiseSpecifications.getAttributeId1() > 0) {
//                List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), merchandiseSpecifications.getAttributeId1(), merchandiseSpecifications.getValue1());
//                for (MerchandiseImage merchandiseImage : imageList) {
//                    if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
//                        String[] strs = merchandiseImage.getImage().split(",");
//                        for (int index = 0; index < strs.length; index++) {
//                            imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
//                        }
//                    }
//                }
//            }
//            if (merchandiseSpecifications.getAttributeId2() > 0) {
//                List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), merchandiseSpecifications.getAttributeId2(), merchandiseSpecifications.getValue2());
//                for (MerchandiseImage merchandiseImage : imageList) {
//                    if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
//                        String[] strs = merchandiseImage.getImage().split(",");
//                        for (int index = 0; index < strs.length; index++) {
//                            imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
//                        }
//                    }
//                }
//            }
//            exchangeMerchandiseVO.setImageList(imageStrList);
//        }
//        // 为空则取默认图
//        if (CollectionUtils.isEmpty(exchangeMerchandiseVO.getImageList())) {
//            List<MerchandiseImage> imageList = merchandiseImageService.listByMerchandiseAndAttribute(merchandise.getId(), -1L, "-1");
//            List<String> imageStrList = new ArrayList<String>();
//            for (MerchandiseImage merchandiseImage : imageList) {
//                if (StringUtils.isNotEmpty(merchandiseImage.getImage())) {
//                    String[] strs = merchandiseImage.getImage().split(",");
//                    for (int index = 0; index < strs.length; index++) {
//                        imageStrList.add(fileSDKClient.getFileServerUrl() + fileSDKClient.uidToUrl(strs[index]));
//                    }
//                }
//            }
//            exchangeMerchandiseVO.setImageList(imageStrList);
//        }
//        exchangeMerchandiseVO.setShareUrl(shareClient.getShareDomain() + "/currencyMerchandiseShare.html?unifiedMerchandiseId=" + unifiedMerchandiseId);
//        //
//        Page<MerchandiseEvaluation> page = merchandiseEvaluationService.page(merchandiseItem.getMerchandiseId(), StarLevelType.ALL, 0, 1);
//        MerchandiseEvaluationVO merchandiseEvaluationVO = null;
//        if (CollectionUtils.isNotEmpty(page.getData())) {
//            List<MerchandiseEvaluationVO> voList = merchandiseEvaluationHandler.toVOList(page.getData());
//            merchandiseEvaluationVO = voList.get(0);
//        }
//        MerchantMerchandiseVO merchantMerchandiseVO = new MerchantMerchandiseVO();
//        int collectNumber = myClient.countMerchandiseCollectionNumber(unifiedMerchandiseId);
//        merchantMerchandiseVO.setCollectNumber(collectNumber);
//        int merchandiseNumber = merchandiseService.countMerchantOnlineNumber(merchandiseItem.getMerchantId());
//        merchantMerchandiseVO.setMerchandiseNumber(merchandiseNumber);
//        merchantMerchandiseVO.setMerchantId(merchandiseItem.getMerchantId());
//        QMerchant merchant = sellercenterClient.getMerchant(merchandiseItem.getMerchantId());
//        merchantMerchandiseVO.setMerchantName(merchant.getName());
//        if (StringUtils.isEmpty(merchant.getProvince())) {
//            merchantMerchandiseVO.setDeliveryPlace(StringUtil.nullToEmpty(merchant.getCity()));
//        } else {
//            merchantMerchandiseVO.setDeliveryPlace(merchant.getProvince() + " " + StringUtil.nullToEmpty(merchant.getCity()));
//        }
//        FrontAjaxView view = new FrontAjaxView();
//        view.setMessage("获取兑换商品成功.");
//        view.addObject("merchandise", exchangeMerchandiseVO);
//        view.addObject("evaluation", merchandiseEvaluationVO == null ? new HashMap<String, String>() : merchandiseEvaluationVO);
//        return view;
//    }
//}
