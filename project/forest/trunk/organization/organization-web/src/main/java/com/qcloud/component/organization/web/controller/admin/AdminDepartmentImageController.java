package com.qcloud.component.organization.web.controller.admin;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.organization.OrganizationClient;
import com.qcloud.component.organization.QClerk;
import com.qcloud.component.organization.model.Department;
import com.qcloud.component.organization.model.DepartmentImage;
import com.qcloud.component.organization.model.query.DepartmentImageQuery;
import com.qcloud.component.organization.service.DepartmentImageService;
import com.qcloud.component.organization.service.DepartmentService;
import com.qcloud.component.organization.web.handler.DepartmentImageHandler;
import com.qcloud.component.organization.web.vo.admin.AdminDepartmentImageVO;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminDepartmentImageController.DIR)
public class AdminDepartmentImageController {

    public static final String     DIR = "admin/departmentImage";

    @Autowired
    private DepartmentImageService departmentImageService;

    @Autowired
    private DepartmentImageHandler departmentImageHandler;

    @Autowired
    private DepartmentService      departmentService;

    @Autowired
    private OrganizationClient     organizationClient;

    @Autowired
    private FileSDKClient          fileSDKClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, DepartmentImageQuery query) {

        QClerk clerk = PageParameterUtil.getParameterValues(request, organizationClient.CLERK_LOGIN_PARAMETER_KEY);
        Department department = departmentService.get(clerk.getDepartmentId());
        List<DepartmentImage> departmentImages = departmentImageService.listByDepartmentId(department.getId());
        List<AdminDepartmentImageVO> adminDepartmentImageVOs = departmentImageHandler.toVOList4Admin(departmentImages);
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        ModelAndView pagingView = new ModelAndView("/admin/organization-DepartmentImage-list");
        pagingView.addObject("result", adminDepartmentImageVOs);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/organization-DepartmentImage-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(HttpServletRequest request, DepartmentImage departmentImage) {

        QClerk clerk = PageParameterUtil.getParameterValues(request, organizationClient.CLERK_LOGIN_PARAMETER_KEY);
        Department department = departmentService.get(clerk.getDepartmentId());
        departmentImage.setDepartmentId(department.getId());
        departmentImage.setImage(fileSDKClient.uidToUrl(departmentImage.getImage()));
        departmentImageService.add(departmentImage);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        DepartmentImage departmentImage = departmentImageService.get(id);
        AdminDepartmentImageVO adminDepartmentImageVO = departmentImageHandler.toVO4Admin(departmentImage);
        ModelAndView model = new ModelAndView("/admin/organization-DepartmentImage-edit");
        model.addObject("departmentImage", adminDepartmentImageVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(DepartmentImage departmentImage, String queryStr) {

        DepartmentImage departmentImage2 = departmentImageService.get(departmentImage.getId());
        if (departmentImage.getImage() != null && !departmentImage.getImage().equals(departmentImage2.getImage())) {
            departmentImage.setImage(fileSDKClient.uidToUrl(departmentImage.getImage()));
        }
        departmentImageService.update(departmentImage);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        departmentImageService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
