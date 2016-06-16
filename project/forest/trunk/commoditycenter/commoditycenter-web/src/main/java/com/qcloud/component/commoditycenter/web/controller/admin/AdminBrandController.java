package com.qcloud.component.commoditycenter.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.BrandType;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.publicdata.web.handler.ClassifyHandler;
import com.qcloud.component.publicdata.web.vo.admin.AdminClassifyVO;
import com.qcloud.pirates.mvc.AceAjaxView;

@Controller
@RequestMapping(value = "/" + AdminBrandController.DIR)
public class AdminBrandController {

    public static final String DIR = "admin/brand";

    @Autowired
    private PublicdataClient   publicdataClient;

    @Autowired
    private ClassifyHandler    classifyHandler;

    @RequestMapping
    public ModelAndView list() {

        List<QClassify> classifyList = publicdataClient.listClassifyForTree((long) BrandType.Brand.getKey(), true);
        ModelAndView view = new ModelAndView("/admin/commoditycenter-Brand-list");
        view.addObject("list", classifyList);
        view.addObject("type", BrandType.Brand.getKey());
        return view;
    }

    @RequestMapping
    public AceAjaxView add(Classify classify) {

        publicdataClient.addClassify(classify);
        AceAjaxView view = new AceAjaxView();
        view.setMessage("添加品牌成功.");
        return view;
    }

    @RequestMapping
    public AceAjaxView edit(Classify classify) {

        publicdataClient.update(classify);
        AceAjaxView view = new AceAjaxView();
        view.setMessage("修改品牌成功.");
        return view;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView view = new ModelAndView("/admin/commoditycenter-Brand-add");
        view.addObject("type", BrandType.Brand.getKey());
        return view;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        Classify classify = publicdataClient.getClassify(id);
        AdminClassifyVO vo = classifyHandler.toVO4Admin(classify);
        ModelAndView view = new ModelAndView("/admin/commoditycenter-Brand-edit");
        view.addObject("classify", vo);
        return view;
    }
}
