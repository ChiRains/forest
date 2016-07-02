package com.qcloud.component.commoditycenter.web.controller.admin;

import com.qcloud.component.commoditycenter.model.AttributeDefinition;
import com.qcloud.component.commoditycenter.model.ClassifySpecifications;
import com.qcloud.component.commoditycenter.model.key.TypeEnum;
import com.qcloud.component.commoditycenter.service.AttributeDefinitionService;
import com.qcloud.component.commoditycenter.service.ClassifySpecificationsService;
import com.qcloud.component.commoditycenter.web.handler.AttributeDefinitionHandler;
import com.qcloud.component.commoditycenter.web.handler.ClassifySpecificationsHandler;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminAttributeDefinitionVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminClassifySpecificationsVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/" + AdminClassifySpecificationsController.DIR)
public class AdminClassifySpecificationsController {
    public static final String DIR = "admin/classifySpecifications";
    @Autowired
    private ClassifySpecificationsService classifySpecificationsService;
    @Autowired
    private ClassifySpecificationsHandler classifySpecificationsHandler;
    @Autowired
    private PublicdataClient publicdataClient;
    @Autowired
    private AttributeDefinitionService attributeDefinitionService;
    @Autowired
    private AttributeDefinitionHandler attributeDefinitionHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Long classifyId) {
        AssertUtil.assertTrue(classifyId > 0, "分类ID错误");
        HashMap where = new HashMap();
        where.put("classifyId", classifyId);
        List<ClassifySpecifications> list = classifySpecificationsService.list(where);
        List<AdminClassifySpecificationsVO> voList = classifySpecificationsHandler.toDetailVOList4Admin(list);
        ModelAndView model = new ModelAndView("/admin/commoditycenter-ClassifySpecifications-list");
        model.addObject("result", voList);
        model.addObject("classifyId", classifyId);
        return model;
    }

    @RequestMapping
    public ModelAndView toAdd(Long classifyId) {
        AssertUtil.assertTrue(classifyId > 0, "分类ID错误");
        Classify classify=publicdataClient.getClassify(classifyId);
        AssertUtil.assertNotNull(classify, "分类不存在");
        ModelAndView model = new ModelAndView("/admin/commoditycenter-ClassifySpecifications-add");
        model.addObject("classify",classify);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(ClassifySpecifications classifySpecifications) {
        AssertUtil.assertNotNull(classifySpecifications.getAttributeId(), "请选择属性");
        classifySpecificationsService.add(classifySpecifications);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
//        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {
        AssertUtil.assertNotNull(id, "ID不能为空");
        ClassifySpecifications classifySpecifications = classifySpecificationsService.get(id);
        AdminClassifySpecificationsVO vo = classifySpecificationsHandler.toVO4Admin(classifySpecifications);
        Classify classify=publicdataClient.getClassify(classifySpecifications.getClassifyId());
        AssertUtil.assertNotNull(classify, "分类不存在");
        ModelAndView model = new ModelAndView("/admin/commoditycenter-ClassifySpecifications-edit");
        model.addObject("classifySpecifications", vo);
        model.addObject("classify",classify);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(ClassifySpecifications classifySpecifications) {
        classifySpecificationsService.update(classifySpecifications);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
//        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {
        AssertUtil.assertNotNull(id, "ID不能为空");
        classifySpecificationsService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
    
    //10-13 类目列表整合属性/规格添加
    /*****************************************************************************************/
    @RequestMapping
    public ModelAndView toAddClassifySpec(Long classifyId){
        
        ModelAndView model = new ModelAndView("/admin/commoditycenter-ClassifySpecifications-addSpec");
        model.addObject("classifyId",classifyId);
        return model;
        
    }
    
    @RequestMapping 
    public ModelAndView addClassifySpec(AttributeDefinition attributeDefinition ,ClassifySpecifications specifications){
        
        AssertUtil.assertNotNull(specifications.getClassifyId(), "分类id不能为空");
        Classify classify=publicdataClient.getClassify(specifications.getClassifyId());
        AssertUtil.assertNotNull(classify, "分类不存在");
        
        attributeDefinition.setType(String.valueOf(TypeEnum.AttrType.spec.getKey()));
        attributeDefinition.setValueType("-1");
        attributeDefinition.setValue(attributeDefinition.getValue().replace("×", ","));
        Long attrId=attributeDefinitionService.add(attributeDefinition);
        
        specifications.setAttributeId(attrId);
        classifySpecificationsService.add(specifications);
        AceAjaxView model = new AceAjaxView();
        model.setMessage("添加成功");
        return model;
    }
    
    
    @RequestMapping
    public ModelAndView toEditClassifySpec(Long id){
        AssertUtil.assertNotNull(id, "id不能为空");
        
        ClassifySpecifications specifications=classifySpecificationsService.get(id);
        AssertUtil.assertNotNull(specifications, "分类规格不存在");
        AttributeDefinition attributeDefinition=attributeDefinitionService.get(specifications.getAttributeId());
        AdminAttributeDefinitionVO vo=attributeDefinitionHandler.toVO4Admin(attributeDefinition);
        
        ModelAndView model = new ModelAndView("/admin/commoditycenter-ClassifySpecifications-editSpec");
        model.addObject("result",vo);
        model.addObject("specifications",specifications);
        return model;
        
    }
    
    @RequestMapping 
    public ModelAndView editClassifySpec(AttributeDefinition attributeDefinition ,Long classifyId,Long specificationId,int sort,int uploadImage){
        
        AssertUtil.assertNotNull(specificationId, "id不能为空");
        ClassifySpecifications specifications=classifySpecificationsService.get(specificationId);
        AssertUtil.assertNotNull(specifications, "分类规格不存在");
        
        attributeDefinition.setType(String.valueOf(TypeEnum.AttrType.spec.getKey()));
        attributeDefinition.setValueType("-1");
        attributeDefinition.setValue(attributeDefinition.getValue().replace("×", ","));
        attributeDefinitionService.update(attributeDefinition);
        specifications.setSort(sort);
        specifications.setUploadImage(uploadImage);
        classifySpecificationsService.update(specifications);
        AceAjaxView model = new AceAjaxView();
        model.setMessage("修改成功");
        return model;
    }
}
