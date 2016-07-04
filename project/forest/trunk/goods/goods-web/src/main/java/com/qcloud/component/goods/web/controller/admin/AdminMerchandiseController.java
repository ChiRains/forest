package com.qcloud.component.goods.web.controller.admin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.goods.UnifiedMerchandiseType;
import com.qcloud.component.goods.exception.CommoditycenterException;
import com.qcloud.component.goods.model.AttributeDefinition;
import com.qcloud.component.goods.model.ClassifyAttribute;
import com.qcloud.component.goods.model.ClassifySpecifications;
import com.qcloud.component.goods.model.Enumeration;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.MerchandiseAttribute;
import com.qcloud.component.goods.model.MerchandiseImage;
import com.qcloud.component.goods.model.MerchandiseSpecifications;
import com.qcloud.component.goods.model.MerchandiseSpecificationsRelation;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum;
import com.qcloud.component.goods.model.key.TypeEnum.BrandType;
import com.qcloud.component.goods.model.key.TypeEnum.MerchandiseStateType;
import com.qcloud.component.goods.model.query.MerchandiseQuery;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
import com.qcloud.component.goods.service.AttributeDefinitionService;
import com.qcloud.component.goods.service.ClassifyAttributeService;
import com.qcloud.component.goods.service.ClassifySpecificationsService;
import com.qcloud.component.goods.service.EnumerationService;
import com.qcloud.component.goods.service.MerchandiseAttributeService;
import com.qcloud.component.goods.service.MerchandiseImageService;
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
import com.qcloud.component.goods.web.handler.KV;
import com.qcloud.component.goods.web.handler.MerchandiseHandler;
import com.qcloud.component.goods.web.handler.MerchandiseSpecificationsHandler;
import com.qcloud.component.goods.web.handler.UnifiedMerchandiseHandler;
import com.qcloud.component.goods.web.vo.UnifiedMerchandiseVO;
import com.qcloud.component.goods.web.vo.admin.AdminAttrListVO;
import com.qcloud.component.goods.web.vo.admin.AdminAttributeVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseSpecificationsVO;
import com.qcloud.component.goods.web.vo.admin.AdminMerchandiseVO;
import com.qcloud.component.goods.web.vo.admin.AdminUnifiedMerchandiseVO;
import com.qcloud.component.publicdata.ClassifyType;
import com.qcloud.component.publicdata.EnableType;
import com.qcloud.component.publicdata.IntKeyValue;
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
import com.qcloud.pirates.web.page.PPage;
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
    private UnifiedMerchandiseService                unifiedMerchandiseService;

    @Autowired
    private UnifiedMerchandiseHandler                unifiedMerchandiseHandler;

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
            List<Classify> classifyList = publicdataClient.listClassify(ClassifyType.MERCHANDISE.getKey(), true);
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
            List<Classify> specList = publicdataClient.listClassify(ClassifyType.SPECIFICATIONS.getKey(), true);
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

    @RequestMapping
    public AceAjaxView listDefaultSpec(Long merchandiseId, Long specClassifyId) {

        // AssertUtil.assertTrue(merchandiseId > 0, "ID无效");
        // Merchandise merchandise = merchandiseService.get(merchandiseId);
        // AssertUtil.assertNotNull(merchandise, "找不到商品");
        // ModelAndView modelAndView = new ModelAndView("/admin/goods-Merchandise-toEditSpec");
        // // 查询该商品规格列表
        HashMap<String, Object> where = new HashMap<String, Object>();
        // where.put("merchandiseId", merchandise.getId());
        // where.put("order", "attributeId asc");
        // List<MerchandiseSpecifications> list = merchandiseSpecificationsService.list(where);
        where.clear();
        // 查询该类目的规格列表
        where.put("classifyId", specClassifyId);
        List<ClassifySpecifications> classifySpecificationses = classifySpecificationsService.list(where);
        List<AdminAttrListVO> attrList = new ArrayList<AdminAttrListVO>();
        // 类目没有选择规格 ,获取默认规格
        for (ClassifySpecifications cs : classifySpecificationses) {
            AttributeDefinition definition = attributeDefinitionService.get(cs.getAttributeId());
            AdminAttrListVO attrVo = new AdminAttrListVO();
            attrVo.setName(definition.getName());
            attrVo.setId(definition.getId());
            String[] values = definition.getValue().split(",");
            attrVo.setValueList(Arrays.asList(values));
            attrList.add(attrVo);
        }
        AceAjaxView view = new AceAjaxView();
        view.addObject("attrList", attrList);
        return view;
    }

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

    private void createSpecs(Merchandise merchandise, List<MerchandiseSpecificationsRelation> newRelation, List<MerchandiseSpecificationsRelation> extraRelation) {// 生成商品规格

        List<MerchandiseSpecifications> list = merchandiseSpecificationsService.listByMerchandise(merchandise.getId());
        // 商品没有选择规格类目
        if (merchandise.getSpecClassifyId() == -1) {
            createDefaultSpecs(merchandise, list);
        } else {
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
                Set<Long> unifiedMerchandiseIds = new HashSet<Long>();
                for (MerchandiseSpecifications merchandiseSpecifications : list) {
                    if (merchandiseSpecifications.getDimensionNumber() != attributeDefinitions.size()) {
                        unifiedMerchandiseIds.add(merchandiseSpecifications.getUnifiedMerchandiseId());
                    }
                }
                disable(merchandise.getId(), new ArrayList<Long>(unifiedMerchandiseIds));
                List<IntKeyValue[]> ls = new ArrayList<IntKeyValue[]>();
                for (final AttributeDefinition attributeDefinition : attributeDefinitions) {
                    List<Enumeration> enumerations = enumerationService.listByName(attributeDefinition.getEnumeration());
                    List<IntKeyValue> strings = new ArrayList<IntKeyValue>();
                    for (int i = 0; i < enumerations.size(); i++) {
                        String str = enumerations.get(i).getValue().trim();
                        List<MerchandiseSpecificationsRelation> relations = merchandiseSpecificationsRelationService.listByMap(merchandise.getId(), attributeDefinition.getId());
                        for (MerchandiseSpecificationsRelation ra : relations) {
                            if (ra.getValue().equals(enumerations.get(i).getValue()) && ra.getType() == 2) {
                                str = ra.getAlias().trim();
                            }
                        }
                        IntKeyValue kv = new KV(attributeDefinition.getId(), str);
                        strings.add(kv);
                    }
                    for (MerchandiseSpecificationsRelation ra : extraRelation) {
                        if (ra.getAttributeId() == attributeDefinition.getId()) {
                            strings.add(new KV(attributeDefinition.getId(), ra.getAlias().trim()));
                        }
                    }
                    IntKeyValue[] stringss = new IntKeyValue[strings.size()];
                    for (int i = 0; i < strings.size(); i++) {
                        stringss[i] = strings.get(i);
                    }
                    ls.add(stringss);
                }
                List<IntKeyValue[]> kvs = calculateSpecifications(ls);
                merchandiseSpecificationsRelationService.deleteByMerchandiseId(merchandise.getId());
                for (MerchandiseSpecificationsRelation relation : newRelation) {
                    merchandiseSpecificationsRelationService.add(relation);
                }
                for (MerchandiseSpecificationsRelation relation : extraRelation) {
                    merchandiseSpecificationsRelationService.add(relation);
                }
                //
                for (IntKeyValue[] intKeyValues : kvs) {
                    List<MerchandiseSpecifications> es = merchandiseSpecificationsService.getSpecifications(list, intKeyValues);
                    if (es != null) {
                        for (MerchandiseSpecifications merchandiseSpecifications : es) {
                            for (IntKeyValue intKeyValue : intKeyValues) {
                                if (intKeyValue.getKey() == merchandiseSpecifications.getAttributeId()) {
                                    merchandiseSpecifications.setValue(intKeyValue.getValue());
                                    merchandiseSpecificationsService.update(merchandiseSpecifications);
                                    break;
                                }
                            }
                        }
                        continue;
                    }
                    UnifiedMerchandise unifiedMerchandise = new UnifiedMerchandise();
                    unifiedMerchandise.setActivityId(-1L);
                    unifiedMerchandise.setBrandId(merchandise.getBrandId());
                    // TODO
                    unifiedMerchandise.setCanUseCoupon(2);
                    unifiedMerchandise.setClickRate(0);
                    // TODO
                    unifiedMerchandise.setCode("");
                    unifiedMerchandise.setDiscount(0.0);
                    unifiedMerchandise.setGoodEvaluation(0L);
                    unifiedMerchandise.setImage(merchandise.getImage());
                    unifiedMerchandise.setName(merchandise.getName());
                    // TODO
                    unifiedMerchandise.setIntegral(0);
                    unifiedMerchandise.setMerchantId(merchandise.getMerchantId());
                    unifiedMerchandise.setKeywords(merchandise.getKeywords());
                    unifiedMerchandise.setLowEvaluation(0);
                    unifiedMerchandise.setMallClassifyId(merchandise.getMallClassifyId());
                    unifiedMerchandise.setMerchandiseId(merchandise.getId());
                    unifiedMerchandise.setMerchantClassifyId(merchandise.getMerchantClassifyId());
                    unifiedMerchandise.setMiddleEvaluation(0);
                    unifiedMerchandise.setRelaUnifiedMerchandiseId(-1L);
                    unifiedMerchandise.setName(merchandise.getName());
                    unifiedMerchandise.setOrder(0);
                    unifiedMerchandise.setPrice(0.0);
                    unifiedMerchandise.setPurchase(0.0);
                    unifiedMerchandise.setRecordTime(new Date());
                    unifiedMerchandise.setSalesVolume(0);
                    // TODO
                    unifiedMerchandise.setVirtualSalesVolume(0);
                    unifiedMerchandise.setState(MerchandiseStateType.INIT.getKey());
                    unifiedMerchandise.setType(UnifiedMerchandiseType.SINGLE.getKey());
                    unifiedMerchandise.setUpdateTime(new Date());
                    unifiedMerchandiseService.add(unifiedMerchandise);
                    unifiedMerchandise.setRelaUnifiedMerchandiseId(unifiedMerchandise.getId());
                    unifiedMerchandiseService.update(unifiedMerchandise);
                    for (IntKeyValue intKeyValue : intKeyValues) {
                        MerchandiseSpecifications merchandiseSpecifications = new MerchandiseSpecifications();
                        merchandiseSpecifications.setAttributeId(intKeyValue.getKey());
                        merchandiseSpecifications.setDimensionNumber(attributeDefinitions.size());
                        merchandiseSpecifications.setMerchandiseId(merchandise.getId());
                        merchandiseSpecifications.setUnifiedMerchandiseId(unifiedMerchandise.getId());
                        merchandiseSpecifications.setValue(intKeyValue.getValue());
                        List<MerchandiseSpecificationsRelation> relations = merchandiseSpecificationsRelationService.listByMap(merchandise.getId(), merchandiseSpecifications.getAttributeId());
                        for (MerchandiseSpecificationsRelation ra : relations) {
                            if (ra.getOldAlias().equals(merchandiseSpecifications.getValue()) && ra.getType() == 2) {
                                merchandiseSpecifications.setValue(ra.getAlias());
                            }
                        }
                        merchandiseSpecificationsService.add(merchandiseSpecifications);
                    }
                }
                List<IntKeyValue[]> cls = new ArrayList<IntKeyValue[]>();
                for (final AttributeDefinition attributeDefinition : attributeDefinitions) {
                    List<MerchandiseSpecificationsRelation> relations = merchandiseSpecificationsRelationService.listByMap(merchandise.getId(), attributeDefinition.getId());
                    List<IntKeyValue> ikvList = new ArrayList<IntKeyValue>();
                    for (MerchandiseSpecificationsRelation merchandiseSpecificationsRelation : relations) {
                        if (merchandiseSpecificationsRelation.getType() == 2 && merchandiseSpecificationsRelation.getIsCheck() == 1) {
                            ikvList.add(new KV(attributeDefinition.getId(), merchandiseSpecificationsRelation.getAlias()));
                        } else if (merchandiseSpecificationsRelation.getType() == 1) {
                            ikvList.add(new KV(attributeDefinition.getId(), merchandiseSpecificationsRelation.getValue()));
                        }
                    }
                    cls.add(ikvList.toArray(new IntKeyValue[ikvList.size()]));
                }
                //
                list = merchandiseSpecificationsService.listByMerchandise(merchandise.getId());
                List<IntKeyValue[]> ckvs = calculateSpecifications(cls);
                for (IntKeyValue[] intKeyValues : ckvs) {
                    List<MerchandiseSpecifications> es = merchandiseSpecificationsService.getSpecifications(list, intKeyValues);
                    if (es != null) {
                        for (MerchandiseSpecifications merchandiseSpecifications : es) {
                            UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(merchandiseSpecifications.getUnifiedMerchandiseId());
                            unifiedMerchandise.setState(merchandise.getState());
                            unifiedMerchandiseService.update(unifiedMerchandise);
                        }
                        continue;
                    }
                }
            } else {
                createDefaultSpecs(merchandise, list);
            }
        }
    }

    private void createDefaultSpecs(Merchandise merchandise, List<MerchandiseSpecifications> list) {

        MerchandiseSpecifications d = null;
        Set<Long> unifiedMerchandiseIds = new HashSet<Long>();
        for (MerchandiseSpecifications merchandiseSpecifications : list) {
            if (merchandiseSpecifications.getDimensionNumber() != 0) {
                unifiedMerchandiseIds.add(merchandiseSpecifications.getUnifiedMerchandiseId());
            } else {
                d = merchandiseSpecifications;
            }
        }
        disable(merchandise.getId(), new ArrayList<Long>(unifiedMerchandiseIds));
        if (d != null) {
            UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(d.getUnifiedMerchandiseId());
            unifiedMerchandise.setState(merchandise.getState());
            unifiedMerchandiseService.update(unifiedMerchandise);
        } else {
            UnifiedMerchandise unifiedMerchandise = new UnifiedMerchandise();
            unifiedMerchandise.setActivityId(-1L);
            unifiedMerchandise.setBrandId(merchandise.getBrandId());
            // TODO
            unifiedMerchandise.setCanUseCoupon(2);
            unifiedMerchandise.setClickRate(0);
            // TODO
            unifiedMerchandise.setCode("");
            unifiedMerchandise.setDiscount(0.0);
            unifiedMerchandise.setGoodEvaluation(0L);
            unifiedMerchandise.setImage(merchandise.getImage());
            unifiedMerchandise.setName(merchandise.getName());
            // TODO
            unifiedMerchandise.setIntegral(0);
            unifiedMerchandise.setMerchantId(merchandise.getMerchantId());
            unifiedMerchandise.setKeywords(merchandise.getKeywords());
            unifiedMerchandise.setLowEvaluation(0);
            unifiedMerchandise.setMallClassifyId(merchandise.getMallClassifyId());
            unifiedMerchandise.setMerchandiseId(merchandise.getId());
            unifiedMerchandise.setMerchantClassifyId(merchandise.getMerchantClassifyId());
            unifiedMerchandise.setMiddleEvaluation(0);
            unifiedMerchandise.setRelaUnifiedMerchandiseId(-1L);
            unifiedMerchandise.setName(merchandise.getName());
            unifiedMerchandise.setOrder(0);
            unifiedMerchandise.setPrice(0.0);
            unifiedMerchandise.setPurchase(0.0);
            unifiedMerchandise.setRecordTime(new Date());
            unifiedMerchandise.setSalesVolume(0);
            // TODO
            unifiedMerchandise.setVirtualSalesVolume(0);
            unifiedMerchandise.setState(merchandise.getState());
            unifiedMerchandise.setType(UnifiedMerchandiseType.SINGLE.getKey());
            unifiedMerchandise.setUpdateTime(new Date());
            unifiedMerchandiseService.add(unifiedMerchandise);
            unifiedMerchandise.setRelaUnifiedMerchandiseId(unifiedMerchandise.getId());
            unifiedMerchandiseService.update(unifiedMerchandise);
            MerchandiseSpecifications ms = new MerchandiseSpecifications();
            ms.setAttributeId(-1L);
            ms.setValue("");
            ms.setDimensionNumber(0);
            ms.setMerchandiseId(merchandise.getId());
            ms.setUnifiedMerchandiseId(unifiedMerchandise.getId());
            merchandiseSpecificationsService.add(ms);
        }
    }

    private void disable(long merchandiseId, List<Long> unifiedMerchandiseIds) {

        List<UnifiedMerchandise> list = unifiedMerchandiseService.listByMerchandise(merchandiseId, UnifiedMerchandiseType.SINGLE.getKey());
        for (UnifiedMerchandise unifiedMerchandise : list) {
            boolean disable = false;
            for (Long id : unifiedMerchandiseIds) {
                if (id == unifiedMerchandise.getId()) {
                    disable = true;
                    break;
                }
            }
            if (disable) {
                unifiedMerchandise.setState(MerchandiseStateType.INIT.getKey());
                unifiedMerchandiseService.update(unifiedMerchandise);
            }
        }
    }

    public static List<IntKeyValue[]> calculateSpecifications(List<IntKeyValue[]> ls) {

        int size = 0;
        for (IntKeyValue[] str : ls) {
            size = size == 0 ? 1 : size;
            size = size * str.length;
        }
        IntKeyValue[][] strs = new IntKeyValue[size][];
        for (int index = 0; index < size; index++) {
            strs[index] = new IntKeyValue[ls.size()];
        }
        int[] indexs = new int[ls.size()];
        for (int i : indexs) {
            indexs[i] = 0;
        }
        for (int index = 0; index < size; index++) {
            IntKeyValue[] ss = strs[index];
            for (int i = 0; i < ss.length; i++) {
                ss[i] = ls.get(i)[indexs[i]];
            }
            indexs[indexs.length - 1] = indexs[indexs.length - 1] + 1;
            increaseIndex(indexs, ls);
        }
        List<IntKeyValue[]> result = new ArrayList<IntKeyValue[]>();
        for (IntKeyValue[] strings : strs) {
            result.add(strings);
        }
        Collections.sort(result, new Comparator<IntKeyValue[]>() {

            @Override
            public int compare(IntKeyValue[] o1, IntKeyValue[] o2) {

                for (int index = 0; index < o1.length; index++) {
                    int r = o1[index].getValue().compareTo(o2[index].getValue());
                    if (r != 0) {
                        return r;
                    }
                }
                return 0;
            }
        });
        return result;
    }

    private static void increaseIndex(int[] indexs, List<IntKeyValue[]> ls) {

        for (int i = indexs.length - 1; i >= 0; i--) {
            if (indexs[i] == ls.get(i).length) {
                indexs[i] = 0;
                if (i > 0) {
                    indexs[i - 1] = indexs[i - 1] + 1;
                }
                increaseIndex(indexs, ls);
            }
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
        where.put("order", "attributeId asc");
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
            } else if (list.size() == 1 && list.get(0).getDimensionNumber() == 0) {
                AdminMerchandiseSpecificationsVO vo = merchandiseSpecificationsHandler.toVO4Admin(list.get(0));
                UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(list.get(0).getUnifiedMerchandiseId());
                if (unifiedMerchandise.getState() == merchandise.getState()) {
                    modelAndView.addObject("isDefault", "checked");
                }
                vo.setDiscount(unifiedMerchandise.getDiscount());
                vo.setPurchase(unifiedMerchandise.getPurchase());
                vo.setPrice(unifiedMerchandise.getPrice());
                vo.setStock(unifiedMerchandise.getStock());
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
        List<UnifiedMerchandise> merchandiseList = unifiedMerchandiseService.listByMerchandise(merchandise.getId(), UnifiedMerchandiseType.SINGLE.getKey(), merchandise.getState());
        List<AdminMerchandiseSpecificationsVO> mspecVoList = new ArrayList<AdminMerchandiseSpecificationsVO>();
        for (UnifiedMerchandise unifiedMerchandise : merchandiseList) {
            AdminMerchandiseSpecificationsVO vo = new AdminMerchandiseSpecificationsVO();
            vo.setId(unifiedMerchandise.getId());
            vo.setPurchase(unifiedMerchandise.getPurchase());
            vo.setDiscount(unifiedMerchandise.getDiscount());
            vo.setPrice(unifiedMerchandise.getPrice());
            vo.setMerchandiseId(unifiedMerchandise.getMerchandiseId());
            vo.setState(unifiedMerchandise.getState());
            vo.setStock(unifiedMerchandise.getStock());
            List<MerchandiseSpecifications> msList = merchandiseSpecificationsService.listByUnifiedMerchandise(unifiedMerchandise.getId());
            StringBuffer sb = new StringBuffer();
            for (MerchandiseSpecifications merchandiseSpecifications : msList) {
                sb.append(merchandiseSpecifications.getValue()).append(" ");
            }
            vo.setValue0(sb.toString());
            mspecVoList.add(vo);
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
            UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(merchandiseSpecifications.getId());
            AssertUtil.assertNotNull(unifiedMerchandise, "找不到统一商品" + id + " " + merchandiseSpecifications.getId());
            for (MsForm msForm : forms) {
                if (msForm.getId() == merchandiseSpecifications.getId()) {
                    unifiedMerchandise.setDiscount(msForm.getDiscount());
                    unifiedMerchandise.setPurchase(msForm.getPurchase());
                    unifiedMerchandise.setPrice(msForm.getPrice());
                    unifiedMerchandise.setState(merchandise.getState());
                    unifiedMerchandiseService.update(unifiedMerchandise);
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
        where.put("order", "attributeId asc");
        List<UnifiedMerchandise> merchandises = unifiedMerchandiseService.listByMerchandise(merchandise.getId(), UnifiedMerchandiseType.SINGLE.getKey());
        for (UnifiedMerchandise unifiedMerchandise : merchandises) {
            unifiedMerchandise.setState(MerchandiseStateType.INIT.getKey());
            unifiedMerchandiseService.update(unifiedMerchandise);
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
                for (MerchandiseSpecifications merchandiseSpecifications : specList) {
                    UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(merchandiseSpecifications.getUnifiedMerchandiseId());
                    unifiedMerchandise.setState(merchandise.getState());
                    unifiedMerchandiseService.update(unifiedMerchandise);
                }
            } else if (relationForm.getAttributeId() != null && relationForm.getAttributeId() == -1 && relationForm.getValue() == null) {
                List<MerchandiseSpecifications> specList = merchandiseSpecificationsService.list(where);
                for (MerchandiseSpecifications merchandiseSpecifications : specList) {
                    UnifiedMerchandise unifiedMerchandise = unifiedMerchandiseService.get(merchandiseSpecifications.getUnifiedMerchandiseId());
                    unifiedMerchandise.setState(MerchandiseStateType.INIT.getKey());
                    unifiedMerchandiseService.update(unifiedMerchandise);
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

    @RequestMapping
    public ModelAndView toEditItemState(UnifiedMerchandiseQuery query) {

        AssertUtil.assertNotNull(query.getMerchandiseId(), "ID不能为空");
        Merchandise merchandise = merchandiseService.get(query.getMerchandiseId());
        AssertUtil.assertNotNull(merchandise, "商品档案不存在.");
        List<UnifiedMerchandise> list = unifiedMerchandiseService.listByMerchandise(merchandise.getId(), UnifiedMerchandiseType.SINGLE.getKey());
        List<AdminUnifiedMerchandiseVO> voList = unifiedMerchandiseHandler.toVOList4Admin(list);
        ModelAndView view = new ModelAndView("/admin/goods-Merchandise-itemState");
        view.addObject("list", voList);
        return view;
    }
}
