package com.qcloud.component.commoditycenter.web.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.component.commoditycenter.model.AttributeDefinition;
import com.qcloud.component.commoditycenter.model.Enumeration;
import com.qcloud.component.commoditycenter.service.AttributeDefinitionService;
import com.qcloud.component.commoditycenter.service.EnumerationService;
import com.qcloud.component.commoditycenter.web.handler.EnumerationHandler;
import com.qcloud.component.commoditycenter.model.key.TypeEnum;
import com.qcloud.component.commoditycenter.model.query.EnumerationQuery;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminEnumerationVO;
@Controller
@RequestMapping(value = "/" + AdminEnumerationController.DIR)
public class AdminEnumerationController {
    public static final String DIR = "admin/enumeration";
    @Autowired
    private EnumerationService enumerationService;
    @Autowired
    private EnumerationHandler enumerationHandler;
    @Autowired
    private AttributeDefinitionService attributeDefinitionService;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, EnumerationQuery query) {
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<Enumeration> page = enumerationService.page(query, start, PAGE_SIZE);
        List<AdminEnumerationVO> list = enumerationHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/commoditycenter-Enumeration-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {
        ModelAndView model = new ModelAndView("/admin/commoditycenter-Enumeration-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(Enumeration enumeration) {
        enumerationService.add(enumeration);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(String name) {
        AssertUtil.assertNotNull(name, "名称不能为空");
        boolean exist = enumerationService.existByName(name);
        AssertUtil.assertTrue(exist, "枚举不存在");
        AdminEnumerationVO adminEnumerationVO = enumerationHandler.toVO4Admin(name);
        ModelAndView model = new ModelAndView("/admin/commoditycenter-Enumeration-edit");
        model.addObject("enumeration", adminEnumerationVO);
        return model;
    }
    @RequestMapping
    public AceAjaxView edit(Enumeration enumeration) {
        enumerationService.update(enumeration);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {
        AssertUtil.assertNotNull(id, "ID不能为空");
        enumerationService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
    
    
}
