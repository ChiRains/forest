package com.qcloud.component.goods.web.controller.admin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.goods.model.AttributeDefinition;
import com.qcloud.component.goods.model.key.TypeEnum;
import com.qcloud.component.goods.model.query.AttributeDefinitionQuery;
import com.qcloud.component.goods.service.AttributeDefinitionService;
import com.qcloud.component.goods.service.EnumerationService;
import com.qcloud.component.goods.web.handler.AttributeDefinitionHandler;
import com.qcloud.component.goods.web.handler.EnumerationHandler;
import com.qcloud.component.goods.web.vo.admin.AdminAttributeDefinitionVO;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
@Controller
@RequestMapping(value = "/" + AdminAttributeDefinitionController.DIR)
public class AdminAttributeDefinitionController {
    public static final String         DIR = "admin/attributeDefinition";
    @Autowired
    private AttributeDefinitionService attributeDefinitionService;
    @Autowired
    private AttributeDefinitionHandler attributeDefinitionHandler;
    @Autowired
    private EnumerationService         enumerationService;
    @Autowired
    private PublicdataClient         publicdataClient;   
    @Autowired
    private EnumerationHandler enumerationHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, AttributeDefinitionQuery query) {
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        query.setType(String.valueOf(TypeEnum.AttrType.attr.getKey()));
        Page<AttributeDefinition> page = attributeDefinitionService.page(query, start, PAGE_SIZE);
        List<AdminAttributeDefinitionVO> list = attributeDefinitionHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/goods-AttributeDefinition-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {
        ModelAndView model = new ModelAndView("/admin/goods-AttributeDefinition-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(AttributeDefinition attributeDefinition) {
        
        attributeDefinition.setType(String.valueOf(TypeEnum.AttrType.attr.getKey()));
        attributeDefinitionService.add(attributeDefinition);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {
        AssertUtil.assertNotNull(id, "ID不能为空");
        AttributeDefinition attributeDefinition = attributeDefinitionService.get(id);
        AdminAttributeDefinitionVO adminAttributeDefinitionVO = attributeDefinitionHandler.toVO4Admin(attributeDefinition);
        ModelAndView model = new ModelAndView("/admin/goods-AttributeDefinition-edit");
        model.addObject("attributeDefinition", adminAttributeDefinitionVO);
        
        List<String> nameList = enumerationService.listNames();
        List<KeyValueVO> voList = publicdataClient.exchageStr(nameList, attributeDefinition.getEnumeration(), "selected");
        model.addObject("enumerationList", voList);        
  
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(AttributeDefinition attributeDefinition) {
        attributeDefinition.setType(String.valueOf(TypeEnum.AttrType.attr.getKey()));
        attributeDefinitionService.update(attributeDefinition);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {
        AssertUtil.assertNotNull(id, "ID不能为空");
        attributeDefinitionService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
    
    
    
    @RequestMapping
    public AcePagingView specList(Integer pageNum, AttributeDefinitionQuery query){
        
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        query.setType(String.valueOf(TypeEnum.AttrType.spec.getKey()));
        Page<AttributeDefinition> page = attributeDefinitionService.page(query, start, PAGE_SIZE);
        List<AdminAttributeDefinitionVO> list = attributeDefinitionHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/goods-AttributeDefinition-speclist", DIR + "/specList", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }
    
    @RequestMapping
    public ModelAndView toAddSpec(){
        
        ModelAndView model = new ModelAndView("/admin/goods-AttributeDefinition-addspec");
        
        return model;
    }
    
    
    //新版添加规格
    @RequestMapping
    public AceAjaxView addSpec(AttributeDefinition attributeDefinition){
        
        attributeDefinition.setType(String.valueOf(TypeEnum.AttrType.spec.getKey()));
        attributeDefinition.setValueType("-1");
        attributeDefinition.setValue(attributeDefinition.getValue().replace("×", ","));
        //添加规格,规格的枚举值
        attributeDefinition.setEnumeration(attributeDefinition.getName());
        attributeDefinitionService.add(attributeDefinition);
        
        
        AceAjaxView model = new AceAjaxView();
        model.setMessage("保存成功");
        return model;
    }
    
    @RequestMapping
    public ModelAndView toEditSpec(Long id){
        
        AssertUtil.assertNotNull(id, "id不能为空");
        AttributeDefinition attributeDefinition=attributeDefinitionService.get(id);
        ModelAndView model = new ModelAndView("/admin/goods-AttributeDefinition-editspec");
        model.addObject("result",attributeDefinition);
        return model;
    }
    
    @RequestMapping
    public AceAjaxView editSpec(AttributeDefinition attributeDefinition){
        
        attributeDefinition.setType(String.valueOf(TypeEnum.AttrType.spec.getKey()));
        attributeDefinition.setValueType("-1");
        attributeDefinition.setEnumeration(attributeDefinition.getName());
        attributeDefinitionService.update(attributeDefinition);
        AceAjaxView model = new AceAjaxView();
        model.setMessage("修改成功");
        return model;
    }
    
    @RequestMapping
    public AcePagingView selectAttribute(Integer pageNum,AttributeDefinitionQuery query){
        
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        //query.setType(String.valueOf(TypeEnum.AttrType.attr.getKey()));
        Page<AttributeDefinition> page = attributeDefinitionService.list4Select(query, start, PAGE_SIZE);
        List<AdminAttributeDefinitionVO> list = attributeDefinitionHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/goods-AttributeDefinition-list4Select", 
                DIR + "/selectAttribute?name="+StringUtil.nullToEmpty(query.getName())+"&type="+query.getType(), pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        return pagingView;
    }
    
    
}
