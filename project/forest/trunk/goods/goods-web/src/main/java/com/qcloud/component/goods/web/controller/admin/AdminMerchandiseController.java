package com.qcloud.component.goods.web.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.goods.exception.CommoditycenterException;
import com.qcloud.component.goods.model.AttributeDefinition;
import com.qcloud.component.goods.model.ClassifyAttribute;
import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.model.Enumeration;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.MerchandiseAttribute;
import com.qcloud.component.goods.model.MerchandiseImage;
import com.qcloud.component.goods.model.MerchandiseItem;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum;
import com.qcloud.component.goods.model.key.TypeEnum.BrandType;
import com.qcloud.component.goods.model.key.TypeEnum.MerchandiseStateType;
import com.qcloud.component.goods.model.key.TypeEnum.UnifiedMerchandiseType;
import com.qcloud.component.goods.model.query.MerchandiseQuery;
import com.qcloud.component.goods.service.AttributeDefinitionService;
import com.qcloud.component.goods.service.ClassifyAttributeService;
import com.qcloud.component.goods.service.ClassifySpecificationsService;
import com.qcloud.component.goods.service.EnumerationService;
import com.qcloud.component.goods.service.MerchandiseAttributeService;
import com.qcloud.component.goods.service.MerchandiseImageService;
import com.qcloud.component.goods.service.MerchandiseItemService;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.service.MerchandiseSpecificationsRelationService;
import com.qcloud.component.goods.service.MerchandiseSpecificationsService;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.goods.web.form.MerchandiseAttributeForm;
import com.qcloud.component.goods.web.form.MerchandiseAttributeItemForm;
import com.qcloud.component.goods.web.form.MerchandiseSpecificationRelationForm;
import com.qcloud.component.goods.web.form.MerchandiseSpecificationsForm;
import com.qcloud.component.goods.web.form.MsForm;
import com.qcloud.component.goods.web.form.RelationForm;
import com.qcloud.component.goods.web.handler.MerchandiseHandler;
import com.qcloud.component.goods.web.handler.MerchandiseSpecificationsHandler;
import com.qcloud.component.goods.web.vo.admin.AdminAttributeVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseSpecificationsVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseVO;
import com.qcloud.component.publicdata.ClassifyType;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.publicdata.util.ClassifyUtils;
import com.qcloud.component.sellercenter.OutdatedSellercenterClient;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.component.sellercenter.model.key.TypeEnum.CommodityAuditingType;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMerchandiseController.DIR)
public class AdminMerchandiseController {

    //
    public static final String                       DIR = "admin/merchandise";

    @Autowired
    private MerchandiseService                       merchandiseService;

    @Autowired
    private MerchandiseHandler                       merchandiseHandler;

    // @Autowired
    // private AdminFilterService adminFilterService;
    //
    // @Autowired
    // private TokenClient tokenClient;
    @Autowired
    private OutdatedSellercenterClient               outdatedSellercenterClient;

    @Autowired
    private SellercenterClient                       sellercenterClient;

    @Autowired
    private PublicdataClient                         publicdataClient;

    @Autowired
    private ClassifySpecificationsService            classifySpecificationsService;

    @Autowired
    private AttributeDefinitionService               attributeDefinitionService;

    @Autowired
    private EnumerationService                       enumerationService;

    @Autowired
    private ClassifyAttributeService                 classifyAttributeService;

    @Autowired
    private MerchandiseAttributeService              merchandiseAttributeService;

    @Autowired
    private MerchandiseSpecificationsService         merchandiseSpecificationsService;

    @Autowired
    private MerchandiseImageService                  merchandiseImageService;

    @Autowired
    private MerchandiseItemService                   merchandiseItemService;

    @Autowired
    private UnifiedMerchandiseService                unifiedMerchandiseService;

    @Autowired
    private MerchandiseSpecificationsHandler         merchandiseSpecificationsHandler;

    @Autowired
    private MerchandiseSpecificationsRelationService merchandiseSpecificationsRelationService;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, MerchandiseQuery query) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        query.setMerchantId(merchant.getId());
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<Merchandise> page = merchandiseService.page(query, start, PAGE_SIZE);
        List<AdminMerchandiseVO> list = merchandiseHandler.toVOList4Admin(page.getData());
        String param = "name=" + StringUtil.nullToEmpty(query.getName()) + "&merchantClassifyId=" + query.getMerchantClassifyId() + "&code=" + StringUtil.nullToEmpty(query.getCode()) + "&specClassifyId=" + (query.getSpecClassifyId() == null ? -1L : query.getSpecClassifyId());
        AcePagingView pagingView = new AcePagingView("/admin/goods-Merchandise-list", DIR + "/list?" + param, pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        // pagingView.addObject("merchantList", merchantKVList);
        pagingView.addObject("query", query);
        List<Classify> mallClassifyList = publicdataClient.listClassify(query.getMerchantId());
        List<KeyValueVO> mallCVOList = ClassifyUtils.exchangeObj(mallClassifyList, -1, "");
        pagingView.addObject("merchantClassifyList", mallCVOList);
        // 获取类目列表
        List<Classify> specClassifyList = publicdataClient.listClassify(ClassifyType.SPECIFICATIONS.getKey());
        List<KeyValueVO> specCVOList = ClassifyUtils.exchangeObj(specClassifyList, -1, "");
        pagingView.addObject("specClassifyId", specCVOList);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd(HttpServletRequest request) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        ModelAndView model = new ModelAndView("/admin/goods-Merchandise-add");
        Long merchantId = merchant.getId();
        model.addObject("merchantId", merchantId);
        model.addObject("merchantIsCertified", merchant.getIsCertified());
        model.addObject("merchantIsExternalUrl", merchant.getIsExternalUrl());
        model.addObject("merchantIsNoReason", merchant.getIsNoReason());
        model.addObject("merchantIsSpecialService", merchant.getIsSpecialService());
        model.addObject("merchantIsIncludePost", merchant.getIsIncludePost());
        // 获取商城商品分类列表, 判断该商家是否有限制商品分类
        boolean limitClassify = true;
        // 商城分类
        Xml cXml = XmlFactory.get("commoditycenter_mall_classify_attribute");
        if (cXml != null) {
            List<XmlItem> items = cXml.getItemList();
            for (XmlItem xmlItem : items) {
                limitClassify = Boolean.parseBoolean(xmlItem.getAttrMap().get("value"));
            }
        }
        List<KeyValueVO> mallCVOList = null;
        if (limitClassify) {
            List<Classify> classifyList = outdatedSellercenterClient.listMerchantMerchandiseClassify(merchantId);
            mallCVOList = ClassifyUtils.exchangeObj(classifyList, -1, "");
        } else {
            List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.MERCHANDISE.getKey(),true);
            mallCVOList = ClassifyUtils.exchangeObj(classifyList, -1, "");
        }
        model.addObject("mallClassifyList", mallCVOList);
        // 获取类目列表
        boolean limitSpec = true;
        // 商城分类
        Xml sXml = XmlFactory.get("commoditycenter_specifications_attribute");
        if (sXml != null) {
            List<XmlItem> items = sXml.getItemList();
            for (XmlItem xmlItem : items) {
                limitSpec = Boolean.parseBoolean(xmlItem.getAttrMap().get("value"));
            }
        }
        List<KeyValueVO> specCVOList = null;
        if (limitSpec) {
            List<Classify> specList = outdatedSellercenterClient.listMerchantSpecClassify(merchantId);
            specCVOList = ClassifyUtils.exchangeObj(specList, -1, "");
        } else {
            List<Classify> specList = publicdataClient.listClassify(ClassifyType.SPECIFICATIONS.getKey(),true);
            specCVOList = ClassifyUtils.exchangeObj(specList, -1, "");
        }
        model.addObject("specClassifyId", specCVOList);
        // 获取商家商品分类列表
        List<Classify> merchantClassifyList = publicdataClient.listClassify(merchantId);
        List<KeyValueVO> merchantCVOList = ClassifyUtils.exchangeObj(merchantClassifyList, -1, "");
        model.addObject("merchantClassifyList", merchantCVOList);
        String fileSize = publicdataClient.getImageInformationByCode("shangpinsuoluetu");
        model.addObject("fileSize", fileSize);
        // 品牌
        boolean hasBrand = false;
        Xml xml = XmlFactory.get("commoditycenter_brand_attribute");
        if (xml != null) {
            List<XmlItem> items = xml.getItemList();
            for (XmlItem xmlItem : items) {
                hasBrand = Boolean.parseBoolean(xmlItem.getAttrMap().get("value"));
            }
        }
        model.addObject("hasBrand", hasBrand);
        List<QClassify> brandList = publicdataClient.listClassifyForTree((long) BrandType.Brand.getKey());
        model.addObject("brandList", brandList);
        return model;
    }

    @Transactional
    @RequestMapping
    public AceAjaxView add(Merchandise merchandise) {

        // 自家商品分类
        if (merchandise.getMerchantClassifyId() <= 0) {
            throw new CommoditycenterException("请选择自家商品分类!");
        }
        // 不需要审核的,状态为直接上架
        long merchantId = merchandise.getMerchantId();
        QMerchant merchant = sellercenterClient.getMerchant(merchantId);
        AssertUtil.assertNotNull(merchant, "商品所在商家不存在." + merchantId);
        if (exist(merchandise)) {
            AssertUtil.assertTrue(false, "商品名称重复.");
        }
        if (CommodityAuditingType.UNNEED.getKey() == merchant.getCommodityAuditing().getKey()) {
            merchandise.setState(MerchandiseStateType.ONLINE.getKey());
        } else {
            merchandise.setState(MerchandiseStateType.NEW.getKey());
        }
        if (merchandise.getIsCertified() != EnableType.ENABLE.getKey()) {
            merchandise.setIsCertified(EnableType.DISABLE.getKey());
            merchandise.setCertified("");
        }
        if (merchandise.getIsExternalUrl() != EnableType.ENABLE.getKey()) {
            merchandise.setIsExternalUrl(EnableType.DISABLE.getKey());
            merchandise.setExternalUrl("");
        }
        if (merchandise.getIsNoReason() != EnableType.ENABLE.getKey()) {
            merchandise.setIsNoReason(EnableType.DISABLE.getKey());
            merchandise.setNoReason("");
        }
        if (merchandise.getIsSpecialService() != EnableType.ENABLE.getKey()) {
            merchandise.setIsSpecialService(EnableType.DISABLE.getKey());
            merchandise.setSpecialService("");
        }
        merchandiseService.add(merchandise);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list?merchantId=" + merchandise.getMerchantId());
        return aceAjaxView;
    }

    private boolean exist(Merchandise newMerchandise) {

        List<Merchandise> list = merchandiseService.getMerchandiseList(newMerchandise.getMerchantId());
        for (Merchandise merchandise : list) {
            if (newMerchandise.getId() == 0) {
                if (merchandise.getName().equals(newMerchandise.getName())) {
                    return true;
                }
            } else {
                if (merchandise.getName().equals(newMerchandise.getName()) && merchandise.getId() != newMerchandise.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    @RequestMapping
    public ModelAndView toEdit(HttpServletRequest request, Long id, int pageNum) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        Merchandise merchandise = merchandiseService.get(id);
        AdminMerchandiseVO adminMerchandiseVO = merchandiseHandler.toVO4Admin(merchandise);
        ModelAndView model = new ModelAndView("/admin/goods-Merchandise-edit");
        model.addObject("merchandise", adminMerchandiseVO);
        model.addObject("merchantIsCertified", merchant.getIsCertified());
        model.addObject("merchantIsExternalUrl", merchant.getIsExternalUrl());
        model.addObject("merchantIsNoReason", merchant.getIsNoReason());
        model.addObject("merchantIsSpecialService", merchant.getIsSpecialService());
        model.addObject("merchantIsIncludePost", merchant.getIsIncludePost());
        // 获取商城商品分类列表, 判断该商家是否有限制商品分类
        boolean limitClassify = true;
        // 商城分类
        Xml cXml = XmlFactory.get("commoditycenter_mall_classify_attribute");
        if (cXml != null) {
            List<XmlItem> items = cXml.getItemList();
            for (XmlItem xmlItem : items) {
                limitClassify = Boolean.parseBoolean(xmlItem.getAttrMap().get("value"));
            }
        }
        List<KeyValueVO> mallCVOList = null;
        if (limitClassify) {
            List<Classify> classifyList = outdatedSellercenterClient.listMerchantMerchandiseClassify(merchant.getId());
            mallCVOList = ClassifyUtils.exchangeObj(classifyList, merchandise.getMallClassifyId(), "selected");
        } else {
            List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.MERCHANDISE);
            mallCVOList = ClassifyUtils.exchangeObj(classifyList, merchandise.getMallClassifyId(), "selected");
        }
        // 获取类目列表
        model.addObject("mallClassifyList", mallCVOList);
        List<Classify> merchantClassifyList = publicdataClient.listClassify(merchant.getId());
        // 获取商家商品分类列表
        List<KeyValueVO> merchantCVOList = ClassifyUtils.exchangeObj(merchantClassifyList, merchandise.getMerchantClassifyId(), "selected");
        model.addObject("merchantClassifyList", merchantCVOList);
        model.addObject("pageNum", pageNum);
        String fileSize = publicdataClient.getImageInformationByCode("shangpinsuoluetu");
        model.addObject("fileSize", fileSize);
        // 品牌
        boolean hasBrand = false;
        Xml xml = XmlFactory.get("commoditycenter_brand_attribute");
        if (xml != null) {
            List<XmlItem> items = xml.getItemList();
            for (XmlItem xmlItem : items) {
                hasBrand = Boolean.parseBoolean(xmlItem.getAttrMap().get("value"));
            }
        }
        model.addObject("hasBrand", hasBrand);
        List<QClassify> brandList = publicdataClient.listClassifyForTree((long) BrandType.Brand.getKey());
        model.addObject("brandList", brandList);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(Merchandise merchandise, int pageNum) {

        if (exist(merchandise)) {
            AssertUtil.assertTrue(false, "商品名称重复.");
        }
        // 自家商品分类
        if (merchandise.getMerchantClassifyId() <= 0) {
            throw new CommoditycenterException("请选择自家商品分类!");
        }
        if (merchandise.getIsCertified() != EnableType.ENABLE.getKey()) {
            merchandise.setIsCertified(EnableType.DISABLE.getKey());
            merchandise.setCertified("");
        }
        if (merchandise.getIsExternalUrl() != EnableType.ENABLE.getKey()) {
            merchandise.setIsExternalUrl(EnableType.DISABLE.getKey());
            merchandise.setExternalUrl("");
        }
        if (merchandise.getIsNoReason() != EnableType.ENABLE.getKey()) {
            merchandise.setIsNoReason(EnableType.DISABLE.getKey());
            merchandise.setNoReason("");
        }
        if (merchandise.getIsSpecialService() != EnableType.ENABLE.getKey()) {
            merchandise.setIsSpecialService(EnableType.DISABLE.getKey());
            merchandise.setSpecialService("");
        }
        merchandiseService.update(merchandise);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?merchantId=" + merchandise.getMerchantId() + "&pageNum=" + pageNum);
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEditAttributes(Long id) {

        ModelAndView model = new ModelAndView("/admin/goods-Merchandise-toEditAttributes");
        Merchandise merchandise = merchandiseService.get(id);
        AssertUtil.assertNotNull(merchandise, "商品不存在");
        AdminMerchandiseVO adminMerchandiseVO = merchandiseHandler.toVO4Admin(merchandise);
        model.addObject("merchandise", adminMerchandiseVO);
        if (merchandise.getSpecClassifyId() == -1 || merchandise.getSpecClassifyId() == 0) {
            AssertUtil.assertTrue(false, "商品所在的规格分类：无规格--没有相应的属性.");
        }
        List<Classify> list = publicdataClient.listParentClassifyIncludeMe(merchandise.getSpecClassifyId());
        List<ClassifyAttribute> allCAList = new ArrayList<ClassifyAttribute>();
        for (Classify classify : list) {
            List<ClassifyAttribute> caList = classifyAttributeService.listByClassify(classify.getId());
            if (CollectionUtils.isNotEmpty(caList)) {
                allCAList.addAll(caList);
            }
        }
        // 获取该商品的属性列表
        List<MerchandiseAttribute> maList = merchandiseAttributeService.listByMerchandise(id);
        List<AdminAttributeVO> attributeVOList = new ArrayList<AdminAttributeVO>();
        for (ClassifyAttribute classifyAttribute : allCAList) {
            AttributeDefinition attributeDefinition = attributeDefinitionService.get(classifyAttribute.getAttributeId());
            if (attributeDefinition != null) {
                AdminAttributeVO adminAttributeVO = new AdminAttributeVO();
                adminAttributeVO.setId(attributeDefinition.getId());
                adminAttributeVO.setName(attributeDefinition.getName());
                String value = "";
                for (MerchandiseAttribute ma : maList) {
                    if (ma.getAttributeId() == attributeDefinition.getId()) {
                        value = ma.getValue();
                    }
                }
                adminAttributeVO.setValue(value);
                // 枚举值
                adminAttributeVO.setType(Integer.parseInt(attributeDefinition.getValueType()));
                if (attributeDefinition.getValue() != null) {
                    String valueStr[] = attributeDefinition.getValue().split(",");
                    List<String> valueList = new ArrayList<String>();
                    for (int i = 0; i < valueStr.length; i++) {
                        valueList.add(valueStr[i]);
                    }
                    String message = "checked";
                    if (Integer.parseInt(attributeDefinition.getValueType()) == TypeEnum.AttrValueType.SELECT.getKey()) {
                        message = "selected";
                    }
                    List<KeyValueVO> kvList = publicdataClient.exchageStr(valueList, value, message);
                    adminAttributeVO.setList(kvList);
                }
                attributeVOList.add(adminAttributeVO);
            }
        }
        model.addObject("result", attributeVOList);
        model.addObject("merchandiseId", id);
        return model;
    }

    @RequestMapping
    public AceAjaxView editAttributes(MerchandiseAttributeForm form) {

        Merchandise merchandise = merchandiseService.get(form.getMerchandiseId());
        AssertUtil.assertNotNull(merchandise, "商品不存在");
        List<MerchandiseAttributeItemForm> list = form.getList();
        List<MerchandiseAttribute> maList = new ArrayList<MerchandiseAttribute>();
        for (MerchandiseAttributeItemForm merchandiseAttributeItemForm : list) {
            MerchandiseAttribute ma = new MerchandiseAttribute();
            ma.setMerchandiseId(form.getMerchandiseId());
            ma.setAttributeId(merchandiseAttributeItemForm.getKey());
            ma.setValue(merchandiseAttributeItemForm.getValue());
            maList.add(ma);
        }
        merchandiseAttributeService.set(form.getMerchandiseId(), maList);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("设置成功");
        aceAjaxView.setUrl(DIR + "/list?merchantId=" + merchandise.getMerchantId());
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        merchandiseService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView createSpecs(Long id, List<MerchandiseSpecificationsRelation> merchandiseSpecificationsRelations, List<MerchandiseSpecificationsRelation> extraRelation) {

        AssertUtil.assertTrue(id > 0, "ID无效");
        Merchandise merchandise = merchandiseService.get(id);
        AssertUtil.assertNotNull(merchandise, "商品无效");
        this.createSpecs(merchandise, merchandiseSpecificationsRelations, extraRelation);
        AceAjaxView aceAjaxView = new AceAjaxView();
        return aceAjaxView;
    }


    private List<MerchandiseSpecifications> createSpecs(Merchandise merchandise, List<MerchandiseSpecificationsRelation> newRelation, List<MerchandiseSpecificationsRelation> extraRelation) {// 生成商品规格

        // 商品没有选择规格类目
        if (merchandise.getSpecClassifyId() == -1) {
            List<MerchandiseSpecifications> merchandiseSpecificationses = new ArrayList<MerchandiseSpecifications>();
            MerchandiseSpecifications ms = merchandiseSpecificationsService.get(merchandise.getId(), -1, "", -1, "", -1, "");
            if (ms != null) {
                merchandiseSpecificationses.add(ms);
                return merchandiseSpecificationses;
            }
            ms = new MerchandiseSpecifications(0, merchandise.getId(), -1, "", -1, "", -1, "", 0);
            merchandiseSpecificationsService.add(ms);
            UnifiedMerchandise unifiedMerchandise = new UnifiedMerchandise();
            unifiedMerchandise.setMerchantId(merchandise.getMerchantId());
            unifiedMerchandise.setType(UnifiedMerchandiseType.SINGLE.getKey());
            unifiedMerchandiseService.add(unifiedMerchandise);
            MerchandiseItem merchandiseItem = new MerchandiseItem();
            merchandiseItem.setBrandId(merchandise.getBrandId());
            merchandiseItem.setKeywords(merchandise.getKeywords());
            merchandiseItem.setMerchandiseId(merchandise.getId());
            merchandiseItem.setMallClassifyId(merchandise.getMallClassifyId());
            merchandiseItem.setMerchandiseSpecificationsId(ms.getId());
            merchandiseItem.setMerchantClassifyId(merchandise.getMerchantClassifyId());
            merchandiseItem.setMerchantId(merchandise.getMerchantId());
            merchandiseItem.setName(merchandise.getName());
            merchandiseItem.setStock(0);
            merchandiseItem.setPurchase(0.0);
            merchandiseItem.setDiscount(0.0);
            merchandiseItem.setPrice(0.0);
            merchandiseItem.setState(merchandise.getState());
            merchandiseItem.setUnifiedMerchandiseId(unifiedMerchandise.getId());
            merchandiseItemService.add(merchandiseItem);
            return merchandiseSpecificationses;
        } else {
            List<MerchandiseSpecifications> merchandiseSpecificationses = new ArrayList<MerchandiseSpecifications>();
            HashMap<String, Object> where = new HashMap<String, Object>();
            where.put("classifyId", merchandise.getSpecClassifyId());
            List<ClassifySpecifications> classifySpecificationses = classifySpecificationsService.list(where);
            List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
            for (ClassifySpecifications classifySpecifications : classifySpecificationses) {
                AttributeDefinition attributeDefinition = attributeDefinitionService.get(classifySpecifications.getAttributeId());
                if (attributeDefinition != null) {
                    attributeDefinitions.add(attributeDefinition);
                }
            }
            if (attributeDefinitions.size() > 0) {
                // 在这里禁用默认规格
                MerchandiseSpecifications ms = merchandiseSpecificationsService.get(merchandise.getId(), -1, "", -1, "", -1, "");
                if (ms != null) {
                    MerchandiseItem merchandiseItem = merchandiseItemService.getBySpecifications(merchandise.getId(), ms.getId());
                    AssertUtil.assertNotNull(merchandiseItem, "找不到单一商品" + merchandise.getId() + " " + ms.getId());
                    merchandiseItem.setState(MerchandiseStateType.INIT.getKey());
                    merchandiseItemService.update(merchandiseItem);
                }
                LinkedHashMap<Long, String[]> attrs = new LinkedHashMap<Long, String[]>();
                for (AttributeDefinition attributeDefinition : attributeDefinitions) {
                    List<Enumeration> enumerations = enumerationService.listByName(attributeDefinition.getEnumeration());
                    List<String> strings = new ArrayList<String>();
                    for (int i = 0; i < enumerations.size(); i++) {
                        String str = enumerations.get(i).getValue().trim();
                        List<MerchandiseSpecificationsRelation> relations = merchandiseSpecificationsRelationService.listByMap(merchandise.getId(), attributeDefinition.getId());
                        for (MerchandiseSpecificationsRelation ra : relations) {
                            if (ra.getValue().equals(enumerations.get(i).getValue()) && ra.getType() == 2) {
                                str = ra.getAlias().trim();
                            }
                        }
                        strings.add(str);
                    }
                    for (MerchandiseSpecificationsRelation ra : extraRelation) {
                        if (ra.getAttributeId() == attributeDefinition.getId()) {
                            strings.add(ra.getAlias().trim());
                        }
                    }
                    String[] stringss = new String[strings.size()];
                    for (int i = 0; i < strings.size(); i++) {
                        stringss[i] = strings.get(i);
                    }
                    attrs.put(attributeDefinition.getId(), stringss);
                }
                long attr0 = attributeDefinitions.get(0).getId();
                long attr1 = attributeDefinitions.size() > 1 ? attributeDefinitions.get(1).getId() : -1L;
                long attr2 = attributeDefinitions.size() > 2 ? attributeDefinitions.get(2).getId() : -1L;
                for (String string0 : attrs.get(attr0)) {// 生成
                    if (attrs.containsKey(attr1)) {
                        for (String string1 : attrs.get(attr1)) {
                            if (attrs.containsKey(attr2)) {
                                for (String string2 : attrs.get(attr2)) {
                                    merchandiseSpecificationses.add(new MerchandiseSpecifications(0, merchandise.getId(), attr0, string0, attr1, string1, attr2, string2, 0));
                                }
                            } else {
                                merchandiseSpecificationses.add(new MerchandiseSpecifications(0, merchandise.getId(), attr0, string0, attr1, string1, -1, "", 0));
                            }
                        }
                    } else {
                        merchandiseSpecificationses.add(new MerchandiseSpecifications(0, merchandise.getId(), attr0, string0, -1, "", -1, "", 0));
                    }
                }
                merchandiseSpecificationsRelationService.deleteByMerchandiseId(merchandise.getId());
                for (MerchandiseSpecificationsRelation relation : newRelation) {
                    merchandiseSpecificationsRelationService.add(relation);
                }
                for (MerchandiseSpecificationsRelation relation : extraRelation) {
                    merchandiseSpecificationsRelationService.add(relation);
                }
                for (MerchandiseSpecifications merchandiseSpecifications : merchandiseSpecificationses) {
                    MerchandiseSpecifications dms = merchandiseSpecificationsService.get(merchandise.getId(), merchandiseSpecifications.getAttributeId0(), merchandiseSpecifications.getValue0(), merchandiseSpecifications.getAttributeId1(), merchandiseSpecifications.getValue1(), merchandiseSpecifications.getAttributeId2(), merchandiseSpecifications.getValue2());
                    List<MerchandiseSpecificationsRelation> relations = merchandiseSpecificationsRelationService.listByMap(merchandise.getId(), merchandiseSpecifications.getAttributeId0());
                    for (MerchandiseSpecificationsRelation ra : relations) {
                        if (ra.getOldAlias().equals(merchandiseSpecifications.getValue0()) && ra.getType() == 2) {
                            merchandiseSpecifications.setValue0(ra.getAlias());
                        }
                    }
                    relations = merchandiseSpecificationsRelationService.listByMap(merchandise.getId(), merchandiseSpecifications.getAttributeId1());
                    for (MerchandiseSpecificationsRelation ra : relations) {
                        if (ra.getValue().equals(merchandiseSpecifications.getValue1()) && ra.getType() == 2) {
                            merchandiseSpecifications.setValue1(ra.getAlias());
                        }
                    }
                    //
                    if (dms != null) {
                        dms.setAttributeId0(merchandiseSpecifications.getAttributeId0());
                        dms.setAttributeId1(merchandiseSpecifications.getAttributeId1());
                        dms.setAttributeId2(merchandiseSpecifications.getAttributeId2());
                        dms.setValue0(merchandiseSpecifications.getValue0());
                        dms.setValue1(merchandiseSpecifications.getValue1());
                        dms.setValue2(merchandiseSpecifications.getValue2());
                        merchandiseSpecificationsService.update(dms);
                        continue;
                    }
                    merchandiseSpecificationsService.add(merchandiseSpecifications);
                    UnifiedMerchandise unifiedMerchandise = new UnifiedMerchandise();
                    unifiedMerchandise.setMerchantId(merchandise.getMerchantId());
                    unifiedMerchandise.setType(UnifiedMerchandiseType.SINGLE.getKey());
                    unifiedMerchandiseService.add(unifiedMerchandise);
                    MerchandiseItem merchandiseItem = new MerchandiseItem();
                    merchandiseItem.setBrandId(merchandise.getBrandId());
                    merchandiseItem.setKeywords(merchandise.getKeywords());
                    merchandiseItem.setMerchandiseId(merchandise.getId());
                    merchandiseItem.setMerchandiseSpecificationsId(merchandiseSpecifications.getId());
                    merchandiseItem.setMallClassifyId(merchandise.getMallClassifyId());
                    merchandiseItem.setMerchantClassifyId(merchandise.getMerchantClassifyId());
                    merchandiseItem.setMerchantId(merchandise.getMerchantId());
                    merchandiseItem.setName(merchandise.getName());
                    // 待初始化
                    merchandiseItem.setState(merchandise.getState());
                    merchandiseItem.setStock(0);
                    merchandiseItem.setPurchase(0.0);
                    merchandiseItem.setDiscount(0.0);
                    merchandiseItem.setPrice(0.0);
                    merchandiseItem.setUnifiedMerchandiseId(unifiedMerchandise.getId());
                    merchandiseItemService.add(merchandiseItem);
                }
                // ///////////////////////////////////////
                // 获取生成的merchandiseItems,修改状态
                Map<String, String> strMap = new HashMap<String, String>();
                for (int i = 0; i < classifySpecificationses.size(); i++) {
                    String str = "";
                    List<MerchandiseSpecificationsRelation> rlist1 = merchandiseSpecificationsRelationService.listByMap(merchandise.getId(), classifySpecificationses.get(i).getAttributeId());
                    for (MerchandiseSpecificationsRelation r : rlist1) {
                        if (r.getType() == 2 && r.getIsCheck() == 1) {
                            str += r.getAlias() + ",";
                        } else if (r.getType() == 1) {
                            str += r.getValue() + ",";
                        }
                    }
                    strMap.put("str" + (i + 1), str);
                }
                where.clear();
                where.put("merchandiseId", merchandise.getId());
                where.put("order", "attributeId0 asc");
                List<MerchandiseSpecifications> mspecList = merchandiseSpecificationsService.list(where);
                List<AdminMerchandiseSpecificationsVO> voList = merchandiseSpecificationsHandler.toVOList4Admin(mspecList);
                List<AdminMerchandiseSpecificationsVO> mspecVoList = getListWithSpec(strMap, voList, classifySpecificationses.size());
                if (mspecVoList != null) {
                    for (AdminMerchandiseSpecificationsVO vo : mspecVoList) {
                        MerchandiseItem mdi = merchandiseItemService.getBySpecifications(vo.getMerchandiseId(), vo.getId());
                        vo.setDiscount(mdi.getDiscount());
                        vo.setPurchase(mdi.getPurchase());
                        vo.setPrice(mdi.getPrice());
                        mdi.setState(merchandise.getState());
                        mdi.setBrandId(merchandise.getBrandId());
                        merchandiseItemService.update(mdi);
                    }
                }
                // ///////////////////////////////////////
            } else {
                MerchandiseSpecifications ms = merchandiseSpecificationsService.get(merchandise.getId(), -1, "", -1, "", -1, "");
                if (ms != null) {
                    merchandiseSpecificationses.add(ms);
                    return merchandiseSpecificationses;
                }
                ms = new MerchandiseSpecifications(0, merchandise.getId(), -1, "", -1, "", -1, "", 0);
                merchandiseSpecificationsService.add(ms);
                UnifiedMerchandise unifiedMerchandise = new UnifiedMerchandise();
                unifiedMerchandise.setMerchantId(merchandise.getMerchantId());
                unifiedMerchandise.setType(UnifiedMerchandiseType.SINGLE.getKey());
                unifiedMerchandiseService.add(unifiedMerchandise);
                MerchandiseItem merchandiseItem = new MerchandiseItem();
                merchandiseItem.setBrandId(merchandise.getBrandId());
                merchandiseItem.setKeywords(merchandise.getKeywords());
                merchandiseItem.setMerchandiseId(merchandise.getId());
                merchandiseItem.setMallClassifyId(merchandise.getMallClassifyId());
                merchandiseItem.setMerchandiseSpecificationsId(ms.getId());
                merchandiseItem.setMerchantClassifyId(merchandise.getMerchantClassifyId());
                merchandiseItem.setMerchantId(merchandise.getMerchantId());
                merchandiseItem.setName(merchandise.getName());
                merchandiseItem.setStock(0);
                merchandiseItem.setPurchase(0.0);
                merchandiseItem.setDiscount(0.0);
                merchandiseItem.setPrice(0.0);
                merchandiseItem.setState(merchandise.getState());
                merchandiseItem.setUnifiedMerchandiseId(unifiedMerchandise.getId());
                merchandiseItemService.add(merchandiseItem);
                return merchandiseSpecificationses;
            }
            return merchandiseSpecificationses;
        }
    }

    // 获取商家未审核的商品列表
    @RequestMapping
    public ModelAndView list4UnAudit(HttpServletRequest request, Long merchantId, Integer pageNum) {

        if (merchantId == null) {
            merchantId = 0L;
        }
        // String tokenId = adminFilterService.getTokenId(request);
        // AssertUtil.assertNotEmpty(tokenId, "获取用户登录信息失败.");
        // String idStr = tokenClient.get(tokenId);
        // AssertUtil.assertNotEmpty(idStr, "获取用户标识失败.");
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<Merchandise> unAudit = merchandiseService.list4MerchandiseState(MerchandiseStateType.AUDITING.getKey(), merchantId, start, PAGE_SIZE);
        List<AdminMerchandiseVO> list = merchandiseHandler.toVOList4Admin(unAudit.getData());
        AcePagingView pagingView = new AcePagingView("/admin/goods-unAudit-list", DIR + "/list", pageNum, PAGE_SIZE, unAudit.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("merchantId", merchantId);
        pagingView.addObject("merchantName", request.getParameter("merchantName"));
        return pagingView;
    }

    // 修改商品的状态 ： INIT(1, "待初始化"), NEW(2, "新增"), AUDITING(3, "待审核"), ONLINE(4, "上线"), OFFLINE(5, "下线");
    @RequestMapping
    public AceAjaxView online(Long id) {

        merchandiseService.online(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("操作成功");
        aceAjaxView.setUrl("/admin/goods-unAudit-list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView offline(Long id) {

        merchandiseService.offline(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("操作成功");
        aceAjaxView.setUrl("/admin/goods-unAudit-list");
        return aceAjaxView;
    }

    // 拒绝审核
    @RequestMapping
    public AceAjaxView notAudited(Long id) {

        merchandiseService.toNew(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("操作成功");
        aceAjaxView.setUrl("/admin/goods-unAudit-list");
        return aceAjaxView;
    }

    // 批量审核
    @RequestMapping
    public AceAjaxView onlineSelected(String ids) throws InterruptedException {

        String id[] = ids.split(",");
        for (int i = 0; i < id.length; i++) {
            merchandiseService.online(Long.parseLong(id[i].trim()));
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("操作成功");
        aceAjaxView.setUrl("/admin/goods-unAudit-list");
        return aceAjaxView;
    }

    // 申请审核
    @RequestMapping
    public AceAjaxView toAuditid(Long id) {

        merchandiseService.auditing(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("操作成功");
        aceAjaxView.setUrl("/admin/goods-unAudit-list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEditSpec(Long id) {

        AssertUtil.assertTrue(id > 0, "ID无效");
        Merchandise merchandise = merchandiseService.get(id);
        AssertUtil.assertNotNull(merchandise, "找不到商品");
        ModelAndView modelAndView = new ModelAndView("/admin/goods-Merchandise-toEditSpec");
        // 查询该商品规格列表
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("merchandiseId", merchandise.getId());
        where.put("order", "attributeId0 asc");
        List<MerchandiseSpecifications> list = merchandiseSpecificationsService.list(where);
        where.clear();
        // 查询该类目的规格列表
        where.put("classifyId", merchandise.getSpecClassifyId());
        List<ClassifySpecifications> classifySpecificationses = classifySpecificationsService.list(where);
        List<AdminAttributeVO> adminAttributeVO = new ArrayList<AdminAttributeVO>();
        // 类目没有选择规格 ,获取默认规格
        if (classifySpecificationses.size() == 0) {
            if (list.size() == 0) {
                modelAndView.addObject("isDefault", "");
                modelAndView.addObject("defaultSpec", new AdminMerchandiseSpecificationsVO());
            } else if (list.size() == 1 && list.get(0).getAttributeId0() == -1 && list.get(0).getAttributeId1() == -1 && list.get(0).getAttributeId2() == -1) {
                AdminMerchandiseSpecificationsVO vo = merchandiseSpecificationsHandler.toVO4Admin(list.get(0));
                MerchandiseItem mdi = merchandiseItemService.getBySpecifications(vo.getMerchandiseId(), vo.getId());
                if (mdi.getState() == merchandise.getState()) {
                    modelAndView.addObject("isDefault", "checked");
                }
                vo.setDiscount(mdi.getDiscount());
                vo.setPurchase(mdi.getPurchase());
                vo.setPrice(mdi.getPrice());
                vo.setStock(mdi.getStock());
                modelAndView.addObject("defaultSpec", vo);
            }
        } else {
            for (ClassifySpecifications cs : classifySpecificationses) {
                AttributeDefinition definition = attributeDefinitionService.get(cs.getAttributeId());
                AdminAttributeVO attrVo = new AdminAttributeVO();
                attrVo.setName(definition.getName());
                attrVo.setId(definition.getId());
                if (definition.getValue() != null) {
                    List<MerchandiseSpecificationsRelation> realList = merchandiseSpecificationsRelationService.listByMap(merchandise.getId(), definition.getId());
                    String attrStr[] = definition.getValue().split(",");
                    List<RelationForm> relationForms = new ArrayList<RelationForm>();
                    List<MerchandiseSpecificationsRelation> extraRealList = new ArrayList<MerchandiseSpecificationsRelation>();
                    for (MerchandiseSpecificationsRelation real : realList) {
                        int count = 0;
                        for (int i = 0; i < attrStr.length; i++) {
                            if (!attrStr[i].equals(real.getValue())) {
                                count++;
                            }
                        }
                        if (count == attrStr.length) {
                            extraRealList.add(real);
                        }
                    }
                    for (int i = 0; i < attrStr.length; i++) {
                        RelationForm form = new RelationForm();
                        form.setAttributeId(definition.getId());
                        form.setAlias(attrStr[i]);
                        form.setValue(attrStr[i]);
                        for (MerchandiseSpecificationsRelation real : realList) {
                            if (real.getValue().equals(attrStr[i]) && real.getType() == 2) {
                                form.setAlias(real.getAlias());
                                form.setIsCheck(real.getIsCheck());
                            }
                        }
                        form.setType(1);
                        relationForms.add(form);
                    }
                    // 额外规格
                    for (MerchandiseSpecificationsRelation real : extraRealList) {
                        RelationForm form = new RelationForm();
                        form.setAttributeId(definition.getId());
                        form.setAlias(real.getAlias());
                        form.setValue(real.getValue());
                        form.setIsCheck(real.getIsCheck());
                        form.setType(2);
                        relationForms.add(form);
                    }
                    attrVo.setRelationForms(relationForms);
                }
                adminAttributeVO.add(attrVo);
            }
        }
        List<Enumeration> enumerations = null;
        List<MerchandiseImage> merchandiseImages = merchandiseImageService.listByMerchandise(merchandise.getId());
        HashMap<String, String> images = new HashMap<String, String>();
        String defaultImages = "";
        for (MerchandiseImage merchandiseImage : merchandiseImages) {
            if (merchandiseImage.getAttributeId() == -1) {
                defaultImages = merchandiseImage.getImage();
            } else {
                images.put(merchandiseImage.getValue(), merchandiseImage.getImage());
            }
        }
        //
        Long imagesAtt = 0L;
        for (ClassifySpecifications classifySpecifications : classifySpecificationses) {
            AttributeDefinition attributeDefinition = attributeDefinitionService.get(classifySpecifications.getAttributeId());
            if (attributeDefinition != null) {
                if (classifySpecifications.getUploadImage() != TypeEnum.ClassifySpecificationsUploadImageType.NO.getKey()) {
                    enumerations = enumerationService.listByName(attributeDefinition.getEnumeration());
                    imagesAtt = classifySpecifications.getAttributeId();
                }
            }
        }
        Map<String, String> strMap = new HashMap<String, String>();
        for (int i = 0; i < classifySpecificationses.size(); i++) {
            String str = "";
            List<MerchandiseSpecificationsRelation> rlist1 = merchandiseSpecificationsRelationService.listByMap(merchandise.getId(), classifySpecificationses.get(i).getAttributeId());
            for (MerchandiseSpecificationsRelation r : rlist1) {
                if (r.getType() == 2 && r.getIsCheck() == 1) {
                    str += r.getAlias() + ",";
                } else if (r.getType() == 1) {
                    str += r.getValue() + ",";
                }
            }
            strMap.put("str" + (i + 1), str);
        }
        where.clear();
        where.put("merchandiseId", merchandise.getId());
        where.put("order", "attributeId0 asc");
        List<MerchandiseSpecifications> mspecList = merchandiseSpecificationsService.list(where);
        List<AdminMerchandiseSpecificationsVO> voList = merchandiseSpecificationsHandler.toVOList4Admin(mspecList);
        List<AdminMerchandiseSpecificationsVO> mspecVoList = getListWithSpec(strMap, voList, classifySpecificationses.size());
        if (mspecVoList != null) {
            for (AdminMerchandiseSpecificationsVO vo : mspecVoList) {
                MerchandiseItem mdi = merchandiseItemService.getBySpecifications(vo.getMerchandiseId(), vo.getId());
                vo.setDiscount(mdi.getDiscount());
                vo.setPurchase(mdi.getPurchase());
                vo.setPrice(mdi.getPrice());
                vo.setStock(mdi.getStock());
            }
        }
        modelAndView.addObject("mspecVoList", mspecVoList);
        modelAndView.addObject("enumerations", enumerations);
        modelAndView.addObject("imagesAtt", imagesAtt);
        modelAndView.addObject("images", images);
        modelAndView.addObject("merchandise", merchandise);
        modelAndView.addObject("defaultImages", defaultImages);
        modelAndView.addObject("attrList", adminAttributeVO);
        return modelAndView;
    }

    @RequestMapping
    public ModelAndView editSpec(MerchandiseSpecificationsForm merchandiseSpecificationsForm, Long id, Long imagesAtt) {

        AssertUtil.assertTrue(id > 0, "ID无效");
        // 客户需求，后面规格不输入，默认第一张图片
        Merchandise merchandise = merchandiseService.get(id);
        AssertUtil.assertNotNull(merchandise, "找不到商品" + id);
        HashMap<Long, String> images = merchandiseSpecificationsForm.getImages();
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("merchandiseId", merchandise.getId());
        List<MerchandiseSpecifications> list = merchandiseSpecificationsService.list(where);
        List<MsForm> forms = merchandiseSpecificationsForm.getList();
        AssertUtil.assertNotNull(forms, "规格列表不能为空");
        // 规格商品状态
        for (MerchandiseSpecifications merchandiseSpecifications : list) {
            MerchandiseItem merchandiseItem = merchandiseItemService.getBySpecifications(id, merchandiseSpecifications.getId());
            AssertUtil.assertNotNull(merchandiseItem, "找不到单一商品" + id + " " + merchandiseSpecifications.getId());
            for (MsForm msForm : forms) {
                if (msForm.getId() == merchandiseSpecifications.getId()) {
                    merchandiseSpecifications.setState(msForm.getState());
                    merchandiseSpecificationsService.update(merchandiseSpecifications);
                    if (msForm.getState() == 1) {
                        merchandiseItem.setDiscount(msForm.getDiscount());
                        merchandiseItem.setPurchase(msForm.getPurchase());
                        merchandiseItem.setPrice(msForm.getPrice());
                        merchandiseItem.setState(merchandise.getState());
                    } else {
                        merchandiseItem.setState(MerchandiseStateType.INIT.getKey());
                    }
                    merchandiseItemService.update(merchandiseItem);
                }
            }
        }
        List<MerchandiseImage> newImages = new ArrayList<MerchandiseImage>();
        String image = "";
        if (images.containsKey(-1L)) {// 默认规格的图片
            image = images.get(-1L);
            MerchandiseImage merchandiseImage = new MerchandiseImage();
            merchandiseImage.setMerchandiseId(merchandise.getId());
            merchandiseImage.setImage(images.get(-1L));
            merchandiseImage.setValue("-1");
            merchandiseImage.setAttributeId(-1L);
            newImages.add(merchandiseImage);
            images.remove(-1L);
        }
        if (imagesAtt > 0) {
            for (Map.Entry<Long, String> entry : images.entrySet()) {
                Enumeration enumeration = enumerationService.get(entry.getKey());
                if (enumeration != null) {
                    MerchandiseImage merchandiseImage = new MerchandiseImage();
                    merchandiseImage.setMerchandiseId(merchandise.getId());
                    merchandiseImage.setAttributeId(imagesAtt);
                    merchandiseImage.setValue(enumeration.getValue());
                    // 规格不上传默认为商品图片
                    if (StringUtils.isEmpty(entry.getValue())) {
                        merchandiseImage.setImage(image);
                    } else {
                        merchandiseImage.setImage(entry.getValue());
                    }
                    newImages.add(merchandiseImage);
                }
            }
        }
        merchandiseImageService.setMerchandiseImages(merchandise.getId(), newImages);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView getRelation(Long merchandiseId) {

        AssertUtil.assertNotNull(merchandiseId > 0, "ID不能为空");
        List<MerchandiseSpecificationsRelation> relations = merchandiseSpecificationsRelationService.listByMerchandiseId(merchandiseId);
        for (MerchandiseSpecificationsRelation relation : relations) {
            relation.setValue(relation.getValue().trim());
        }
        AceAjaxView view = new AceAjaxView();
        view.addObject("relations", relations);
        return view;
    }

    // 筛选成规格格式
    public static List<AdminMerchandiseSpecificationsVO> getListWithSpec(Map<String, String> strMap, List<AdminMerchandiseSpecificationsVO> list, int csSize) {

        String str1 = strMap.get("str1");
        String str2 = strMap.get("str2");
        if (csSize == 2) {
            if (str1 == null || str2 == null) {
                return null;
            }
        } else if (csSize == 1) {
            str2 = "";
        } else {
            return null;
        }
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        // 规格组合
        String specValue1[] = str1.split(",");
        String specValue2[] = str2.split(",");
        for (int i = 0; i < specValue1.length; i++) {
            for (int j = 0; j < specValue2.length; j++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("attribute0", specValue1[i].trim());
                map.put("attribute1", specValue2[j].trim());
                mapList.add(map);
            }
        }
        //
        List<AdminMerchandiseSpecificationsVO> temp = new ArrayList<AdminMerchandiseSpecificationsVO>();
        for (Map<String, Object> map : mapList) {
            for (int i = 0; i < list.size(); i++) {
                String value0 = (String) map.get("attribute0");
                String value1 = (String) map.get("attribute1");
                AdminMerchandiseSpecificationsVO spec = list.get(i);
                if (spec.getValue0().equals(value0) && spec.getValue1().equals(value1)) {
                    temp.add(spec);
                }
            }
        }
        return temp;
    }

    // 编辑关联关系
    @RequestMapping
    public AceAjaxView editRelation(Long id, MerchandiseSpecificationRelationForm form, MerchandiseSpecificationRelationForm form1) {

        AceAjaxView view = new AceAjaxView();
        AssertUtil.assertTrue(id > 0, "ID无效");
        Merchandise merchandise = merchandiseService.get(id);
        AssertUtil.assertNotNull(merchandise, "找不到商品");
        HashMap<String, Object> where = new HashMap<String, Object>();
        where.put("merchandiseId", merchandise.getId());
        where.put("order", "attributeId0 asc");
        List<MerchandiseItem> merchandiseItems = merchandiseItemService.listByMerchandise(merchandise.getId());
        for (MerchandiseItem item : merchandiseItems) {
            item.setState(TypeEnum.MerchandiseStateType.INIT.getKey());
            merchandiseItemService.update(item);
        }
        //
        List<MerchandiseSpecificationsRelation> newRelation = new ArrayList<MerchandiseSpecificationsRelation>();
        List<RelationForm> relationForms = form.getRelationForms();
        for (RelationForm relationForm : relationForms) {
            if (relationForm.getAttributeId() != null && relationForm.getIsCheck() != 0) {
                MerchandiseSpecificationsRelation relation = returnRelation(merchandise.getId(), relationForm.getAttributeId(), relationForm.getValue(), relationForm.getAlias());
                if (relation == null) {
                    relation = new MerchandiseSpecificationsRelation();
                }
                if (relationForm.getValue().equals(relationForm.getAlias()) && relation.getType() == 1) {
                    relation.setType(1);
                    relation.setOldAlias(relationForm.getValue());
                } else {
                    if (relation.getOldAlias() == null) {
                        relation.setOldAlias(relationForm.getValue());
                    }
                    relation.setAlias(relationForm.getAlias());
                    relation.setType(2);
                }
                relation.setIsCheck(relationForm.getIsCheck());
                relation.setAttributeId(relationForm.getAttributeId());
                relation.setMerchandiseId(merchandise.getId());
                relation.setValue(relationForm.getValue());
                newRelation.add(relation);
            } else if (relationForm.getAttributeId() != null && relationForm.getAttributeId() == -1 && relationForm.getValue() != null && relationForm.getValue().equals("1")) {
                List<MerchandiseSpecifications> specList = merchandiseSpecificationsService.list(where);
                List<AdminMerchandiseSpecificationsVO> tempList = merchandiseSpecificationsHandler.toVOList4Admin(specList);
                for (AdminMerchandiseSpecificationsVO vo : tempList) {
                    MerchandiseItem mdi = merchandiseItemService.getBySpecifications(vo.getMerchandiseId(), vo.getId());
                    vo.setValue0("默认规格");
                    vo.setDiscount(mdi.getDiscount());
                    vo.setPurchase(mdi.getPurchase());
                    vo.setPrice(mdi.getPrice());
                    mdi.setState(merchandise.getState());
                    merchandiseItemService.update(mdi);
                }
            } else if (relationForm.getAttributeId() != null && relationForm.getAttributeId() == -1 && relationForm.getValue() == null) {
                List<MerchandiseSpecifications> specList = merchandiseSpecificationsService.list(where);
                List<AdminMerchandiseSpecificationsVO> tempList = merchandiseSpecificationsHandler.toVOList4Admin(specList);
                for (AdminMerchandiseSpecificationsVO vo : tempList) {
                    MerchandiseItem mdi = merchandiseItemService.getBySpecifications(vo.getMerchandiseId(), vo.getId());
                    mdi.setState(MerchandiseStateType.INIT.getKey());
                    merchandiseItemService.update(mdi);
                }
            }
        }
        // extraForms 新增加的规格
        List<RelationForm> extraForms = form1.getExtraForms();
        List<MerchandiseSpecificationsRelation> extraRelation = new ArrayList<MerchandiseSpecificationsRelation>();
        if (extraForms != null) {
            for (RelationForm relationForm : extraForms) {
                if (relationForm.getAlias() == null || relationForm.getAlias().equals("")) {
                    continue;
                }
                MerchandiseSpecificationsRelation relation = new MerchandiseSpecificationsRelation();
                relation.setOldAlias(relationForm.getValue());
                relation.setAlias(relationForm.getAlias());
                relation.setType(2);
                relation.setAttributeId(relationForm.getAttributeId());
                relation.setMerchandiseId(merchandise.getId());
                relation.setValue(relationForm.getValue());
                relation.setIsCheck(relationForm.getIsCheck());
                extraRelation.add(relation);
            }
        }
        this.createSpecs(id, newRelation, extraRelation);
        view.setMessage("修改成功");
        view.setUrl(DIR + "/toEditSpec?id=" + id);
        return view;
    }

    private MerchandiseSpecificationsRelation returnRelation(Long merchandiseId, Long attributeId, String value, String alias) {

        MerchandiseSpecificationsRelation relation = null;
        List<MerchandiseSpecificationsRelation> relations = merchandiseSpecificationsRelationService.listByMap(merchandiseId, attributeId);
        for (MerchandiseSpecificationsRelation r : relations) {
            if (value.equals(r.getValue())) {
                if (alias != null && r.getType() == 2 && r.getAlias().equals(alias)) {
                } else {
                    r.setOldAlias(r.getAlias());
                }
                relation = r;
            }
        }
        return relation;
    }

    @RequestMapping
    public ModelAndView list4Admin(Integer pageNum, MerchandiseQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        List<Merchandise> list = merchandiseService.list4Admin(query, start, PAGE_SIZE);
        int count = merchandiseService.count4Admin(query);
        List<AdminMerchandiseVO> voList = merchandiseHandler.toVOList4Admin(list);
        //
        String param = "name=" + StringUtil.nullToEmpty(query.getName()) + "&mallClassifyId=" + query.getMallClassifyId() + "&merchantClassifyId=" + query.getMerchantClassifyId() + "&code=" + StringUtil.nullToEmpty(query.getCode()) + "&specClassifyId=" + (query.getSpecClassifyId() == null ? -1L : query.getSpecClassifyId());
        AcePagingView pagingView = new AcePagingView("/admin/goods-Merchandise-list4Admin", DIR + "/list4Admin?" + param, pageNum, PAGE_SIZE, count);
        pagingView.addObject("result", voList);
        pagingView.addObject("query", query);
        List<Classify> mallClassifyList = publicdataClient.listClassify(ClassifyType.MERCHANDISE);
        List<KeyValueVO> mallCVOList = ClassifyUtils.exchangeObj(mallClassifyList, -1, "");
        pagingView.addObject("mallClassifyList", mallCVOList);
        // 获取类目列表
        List<Classify> specClassifyList = publicdataClient.listClassify(ClassifyType.SPECIFICATIONS.getKey());
        List<KeyValueVO> specCVOList = ClassifyUtils.exchangeObj(specClassifyList, -1, "");
        pagingView.addObject("specClassifyId", specCVOList);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView details4Admin(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Merchandise merchandise = merchandiseService.get(id);
        AdminMerchandiseVO vo = merchandiseHandler.toVO4Admin(merchandise);
        // 获取商城商品分类列表, 判断该商家是否有限制商品分类
        List<KeyValueVO> mallCVOList = null;
        List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.MERCHANDISE);
        mallCVOList = ClassifyUtils.exchangeObj(classifyList, merchandise.getMallClassifyId(), "selected");
        // 获取类目列表
        List<Classify> specClassifyList = publicdataClient.listClassify(ClassifyType.SPECIFICATIONS.getKey());
        List<KeyValueVO> specCVOList = ClassifyUtils.exchangeObj(specClassifyList, -1, "");
        ModelAndView view = new ModelAndView("/admin/goods-Merchandise-details4Admin");
        view.addObject("specClassifyId", specCVOList);
        view.addObject("merchandise", vo);
        view.addObject("mallClassifyList", mallCVOList);
        return view;
    }
}
