package com.qcloud.component.goods.web.controller.admin;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.goods.OutdatedCommoditycenterClient;
import com.qcloud.component.goods.model.AttributeDefinition;
import com.qcloud.component.goods.model.ClassifyAttribute;
import com.qcloud.component.goods.model.key.TypeEnum;
import com.qcloud.component.goods.service.AttributeDefinitionService;
import com.qcloud.component.goods.service.ClassifyAttributeService;
import com.qcloud.component.goods.web.handler.AttributeDefinitionHandler;
import com.qcloud.component.goods.web.handler.ClassifyAttributeHandler;
import com.qcloud.component.goods.web.vo.admin.AdminAttributeDefinitionVO;
import com.qcloud.component.goods.web.vo.admin.AdminClassifyAttributeVO;
import com.qcloud.component.publicdata.ClassifyType;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminClassifyAttributeController.DIR)
public class AdminClassifyAttributeController {

    public static final String            DIR = "admin/classifyAttribute";

    @Autowired
    private ClassifyAttributeService      classifyAttributeService;

    @Autowired
    private ClassifyAttributeHandler      classifyAttributeHandler;

    @Autowired
    private PublicdataClient              publicdataClient;

    @Autowired
    private OutdatedCommoditycenterClient outdatedCommoditycenterClient;

    @Autowired
    private AttributeDefinitionService    attributeDefinitionService;

    @Autowired
    private AttributeDefinitionHandler    attributeDefinitionHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list() {

        // List<Classify> list = publicdataClient.listClassify(ClassifyType.SPECIFICATIONS);
        // List<ClassifyAttribute> caList = classifyAttributeService.listAll();
        // List<AdminClassifyAttributeVO> voList = classifyAttributeHandler.toVOList4Admin(list, caList);
        ModelAndView model = new ModelAndView("/admin/goods-ClassifyAttribute-list");
        // model.addObject("result", Common.listToTree(voList, "classifyId", "parentId", "children", -1));
        model.addObject("classType", ClassifyType.SPECIFICATIONS.getKey());
        List<QClassify> qclassify = publicdataClient.listClassifyForTree(ClassifyType.SPECIFICATIONS);
        model.addObject("qclassify", qclassify);
        return model;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView toSetAttributes(Long classifyId) {

        AssertUtil.assertNotNull(classifyId, "必须指定分类.");
        Classify classify = publicdataClient.getClassify(classifyId);
        AssertUtil.assertNotNull(classify, "指定分类不存在." + classifyId);
        List<Classify> list = publicdataClient.listParentClassifyIncludeMe(classifyId);
        List<AdminClassifyAttributeVO> voList = new ArrayList<AdminClassifyAttributeVO>();
        for (Classify c : list) {
            List<ClassifyAttribute> cl = classifyAttributeService.listByClassify(c.getId());
            if (CollectionUtils.isNotEmpty(cl)) {
                if (c.getId() == classifyId) {
                    voList.addAll(classifyAttributeHandler.toVOList4Admin(cl));
                } else {
                    voList.addAll(classifyAttributeHandler.toVOList4Admin(list, cl, 1));
                }
            }
        }
        ModelAndView model = new ModelAndView("/admin/goods-ClassifyAttribute-setAttributes");
        model.addObject("result", voList);
        model.addObject("classify", classify);
        return model;
    }

    @RequestMapping
    public ModelAndView addClassifyAttributes(ClassifyAttribute classifyAttribute) {

        AssertUtil.assertTrue(classifyAttribute.getClassifyId() > 0, "分类ID错误");
        Classify classify = publicdataClient.getClassify(classifyAttribute.getClassifyId());
        AssertUtil.assertNotNull(classify, "指定分类不存在." + classify);
        classifyAttributeService.add(classifyAttribute);
        AceAjaxView view = new AceAjaxView();
        view.setMessage("添加成功");
        return view;
    }

    @RequestMapping
    public ModelAndView toAddClassifyAttributes(Long classifyId) {

        AssertUtil.assertTrue(classifyId > 0, "分类ID错误");
        Classify classify = publicdataClient.getClassify(classifyId);
        AssertUtil.assertNotNull(classify, "指定分类不存在." + classifyId);
        ModelAndView view = new ModelAndView("/admin/goods-ClassifyAttribute-addClassifyAttributes");
        view.addObject("classify", classify);
        return view;
    }

    @RequestMapping
    public ModelAndView editClassifyAttributes(ClassifyAttribute classifyAttribute) {

        AssertUtil.assertTrue(classifyAttribute.getClassifyId() > 0, "分类ID错误");
        Classify classify = publicdataClient.getClassify(classifyAttribute.getClassifyId());
        AssertUtil.assertNotNull(classify, "指定分类不存在." + classify);
        classifyAttributeService.update(classifyAttribute);
        AceAjaxView view = new AceAjaxView();
        view.setMessage("添加成功");
        return view;
    }

    @RequestMapping
    public ModelAndView toEditClassifyAttributes(Long id) {

        AssertUtil.assertNotNull(id, "id不能为空");
        ClassifyAttribute attribute = classifyAttributeService.get(id);
        AssertUtil.assertNotNull(attribute, "分类属性不存在");
        Classify classify = publicdataClient.getClassify(attribute.getClassifyId());
        AssertUtil.assertNotNull(classify, "指定分类不存在." + attribute.getAttributeId());
        ModelAndView view = new ModelAndView("/admin/goods-ClassifyAttribute-editClassifyAttributes");
        view.addObject("classify", classify);
        view.addObject("attribute", attribute);
        return view;
    }

    @RequestMapping
    public AceAjaxView setAttribute(Long classifyId, Long attributeId, Integer value) {

        AssertUtil.assertNotNull(classifyId, "ClassifyId不能为空.");
        AssertUtil.assertNotNull(attributeId, "AttributeId不能为空.");
        AssertUtil.assertNotNull(value, "Value开关不能为空.");
        ClassifyAttribute ca = classifyAttributeService.get(classifyId, attributeId);
        if (value == 1) {
            if (ca == null) {
                Classify classify = publicdataClient.getClassify(classifyId);
                AssertUtil.assertNotNull(classify, "分类不存在." + classifyId);
                AttributeDefinition attributeDefinition = attributeDefinitionService.get(attributeId);
                AssertUtil.assertNotNull(attributeDefinition, "属性不存在." + attributeId);
                ClassifyAttribute classifyAttribute = new ClassifyAttribute();
                classifyAttribute.setClassifyId(classifyId);
                classifyAttribute.setAttributeId(attributeId);
                classifyAttributeService.add(classifyAttribute);
            }
        } else {
            if (ca != null) {
                classifyAttributeService.delete(ca.getId());
            }
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("设置成功.");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/goods-ClassifyAttribute-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(ClassifyAttribute classifyAttribute) {

        classifyAttributeService.add(classifyAttribute);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        ClassifyAttribute classifyAttribute = classifyAttributeService.get(id);
        AdminClassifyAttributeVO adminClassifyAttributeVO = classifyAttributeHandler.toVO4Admin(classifyAttribute);
        ModelAndView model = new ModelAndView("/admin/goods-ClassifyAttribute-edit");
        model.addObject("classifyAttribute", adminClassifyAttributeVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(ClassifyAttribute classifyAttribute) {

        classifyAttributeService.update(classifyAttribute);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        classifyAttributeService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView classifyList() {

        List<QClassify> qlist = publicdataClient.listClassifyForTree(ClassifyType.MERCHANDISE,true);
        ModelAndView model = new ModelAndView("/admin/goods-ClassifyAttribute-classifyList");
        model.addObject("classType", ClassifyType.MERCHANDISE.getKey());
        model.addObject("qlist", qlist);
        return model;
    }

    @RequestMapping
    public ModelAndView toAddClassifyAttr(Long classifyId) {

        ModelAndView model = new ModelAndView("/admin/goods-ClassifyAttribute-addAttr");
        model.addObject("classifyId", classifyId);
        return model;
    }

    @RequestMapping
    public AceAjaxView addClassifyAttr(AttributeDefinition attributeDefinition, ClassifyAttribute classifyAttribute) {

        attributeDefinition.setType(String.valueOf(TypeEnum.AttrType.attr.getKey()));
        Long attrId = attributeDefinitionService.add(attributeDefinition);
        classifyAttribute.setAttributeId(attrId);
        classifyAttributeService.add(classifyAttribute);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEditClassifyAttr(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        ClassifyAttribute classifyAttribute = classifyAttributeService.get(id);
        AssertUtil.assertNotNull(classifyAttribute, "分类属性不存在");
        AdminClassifyAttributeVO attribute = classifyAttributeHandler.toVO4Admin(classifyAttribute);
        AttributeDefinition definition = attributeDefinitionService.get(attribute.getAttributeId());
        AssertUtil.assertNotNull(definition, "属性定义不存在");
        AdminAttributeDefinitionVO vo = attributeDefinitionHandler.toVO4Admin(definition);
        ModelAndView model = new ModelAndView("/admin/goods-ClassifyAttribute-editAttr");
        model.addObject("attribute", attribute);
        model.addObject("result", vo);
        return model;
    }

    @RequestMapping
    public AceAjaxView editClassifyAttr(AttributeDefinition attributeDefinition, Long classifyId, Long attributeId, int sort) {

        AssertUtil.assertNotNull(attributeId, "id不能为空");
        ClassifyAttribute classifyAttribute = classifyAttributeService.get(attributeId);
        AssertUtil.assertNotNull(classifyAttribute, "分类属性不存在");
        attributeDefinition.setType(String.valueOf(TypeEnum.AttrType.attr.getKey()));
        attributeDefinitionService.update(attributeDefinition);
        classifyAttribute.setSort(sort);
        classifyAttributeService.update(classifyAttribute);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    // 删除商品分类
    @RequestMapping
    public AceAjaxView deleteMallClassify(Long id) {

        AceAjaxView aceAjaxView = new AceAjaxView();
        AssertUtil.assertNotNull(id, "id不能为空");
        Classify classify = publicdataClient.getClassify(id);
        AssertUtil.assertNotNull(classify, "指定分类不存在");
        List<Classify> classifies = publicdataClient.listClassify(classify.getType(), true);
        List<Classify> listChildrenIncludeMe = new ArrayList<Classify>();
        for (Classify c : classifies) {
            if (c.getBsid().startsWith(classify.getBsid())) {
                listChildrenIncludeMe.add(c);
            }
        }
        if (listChildrenIncludeMe.size() > 1) {
            aceAjaxView.setStatus(0);
            aceAjaxView.setMessage("删除失败,该分类包含" + (listChildrenIncludeMe.size() - 1) + "个子类");
        } else {
            int count = 0;
            for (Classify c : listChildrenIncludeMe) {
                count += outdatedCommoditycenterClient.count4DeleteClassify(c.getId());
            }
            if (count > 0) {
                aceAjaxView.setStatus(0);
                aceAjaxView.setMessage("删除失败,该分类或其子类关联了" + count + "件商品");
            } else {
                publicdataClient.delete(id);
                aceAjaxView.setMessage("删除成功");
            }
        }
        return aceAjaxView;
    }
}
