package com.qcloud.component.organization.web.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.organization.OrganizationClient;
import com.qcloud.component.organization.QClerk;
import com.qcloud.component.organization.model.Department;
import com.qcloud.component.organization.model.DepartmentClerk;
import com.qcloud.component.organization.model.query.DepartmentQuery;
import com.qcloud.component.organization.service.DepartmentClerkService;
import com.qcloud.component.organization.service.DepartmentService;
import com.qcloud.component.organization.web.handler.DepartmentHandler;
import com.qcloud.component.organization.web.vo.admin.AdminDepartmentVO;
import com.qcloud.component.organization.web.vo.admin.AdminPlatformTypeVO;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminDepartmentController.DIR)
public class AdminDepartmentController {

    public static final String     DIR  = "admin/department";

    @Autowired
    private DepartmentService      departmentService;

    @Autowired
    private DepartmentHandler      departmentHandler;

    @Autowired
    private DepartmentClerkService departmentClerkService;

    private final String           code = "organization-platform-type-config";

    @Autowired
    private PublicdataClient       publicdataClient;

    @Autowired
    private FileSDKClient          fileSDKClient;

    @Autowired
    private OrganizationClient     organizationClient;

    /**
     * 
     * @param parentId
     * @return
     */
    public List<AdminPlatformTypeVO> typeList(Long parentId) {

        int key = 0;
        if (parentId == -1) {
            key = -1;
        } else {
            Department department = departmentService.get(parentId);
            key = department.getType();
        }
        List<AdminPlatformTypeVO> list = new ArrayList<AdminPlatformTypeVO>();
        Xml xml = XmlFactory.get(code);
        if (xml == null) {
            //
            return null;
        } else {
            List<XmlItem> xmlItemList = xml.getItemList();
            for (XmlItem xmlItem : xmlItemList) {
                AdminPlatformTypeVO vo = new AdminPlatformTypeVO();
                String type = xmlItem.getAttrMap().get("type");
                String name = xmlItem.getAttrMap().get("name");
                String parent = xmlItem.getAttrMap().get("parent");
                String parents = xmlItem.getAttrMap().get("parents");
                if (StringUtils.isNotEmpty(parent)) {
                    vo.setParent(Integer.parseInt(parent));
                }
                if (StringUtils.isNotEmpty(parents)) {
                    Xml pxml = XmlFactory.get(parents);
                    if (pxml == null) {
                        return null;
                    } else {
                        List<XmlItem> pxmlItemList = pxml.getItemList();
                        for (XmlItem pxmlItem : pxmlItemList) {
                            String pparent = pxmlItem.getAttrMap().get("parent");
                            vo.getParents().add(Integer.parseInt(pparent));
                        }
                    }
                }
                vo.setType(Integer.parseInt(type));
                vo.setName(name);
                if ((StringUtils.isNotEmpty(parent) && Integer.parseInt(parent) == key) || vo.getParents().contains(key)) {
                    list.add(vo);
                }
            }
        }
        return list;
    }

    @RequestMapping
    public AceAjaxView platformTypeList(Long parentId) {

        int key = 0;
        if (parentId == -1) {
            key = -1;
        } else {
            Department department = departmentService.get(parentId);
            key = department.getType();
        }
        List<AdminPlatformTypeVO> list = new ArrayList<AdminPlatformTypeVO>();
        Xml xml = XmlFactory.get(code);
        if (xml == null) {
            //
            return null;
        } else {
            List<XmlItem> xmlItemList = xml.getItemList();
            for (XmlItem xmlItem : xmlItemList) {
                AdminPlatformTypeVO vo = new AdminPlatformTypeVO();
                String type = xmlItem.getAttrMap().get("type");
                String name = xmlItem.getAttrMap().get("name");
                String parent = xmlItem.getAttrMap().get("parent");
                String parents = xmlItem.getAttrMap().get("parents");
                if (StringUtils.isNotEmpty(parent)) {
                    vo.setParent(Integer.parseInt(parent));
                }
                if (StringUtils.isNotEmpty(parents)) {
                    Xml pxml = XmlFactory.get(parents);
                    if (pxml == null) {
                        return null;
                    } else {
                        List<XmlItem> pxmlItemList = pxml.getItemList();
                        for (XmlItem pxmlItem : pxmlItemList) {
                            String pparent = pxmlItem.getAttrMap().get("parent");
                            vo.getParents().add(Integer.parseInt(pparent));
                        }
                    }
                }
                vo.setType(Integer.parseInt(type));
                vo.setName(name);
                if ((StringUtils.isNotEmpty(parent) && Integer.parseInt(parent) == key) || vo.getParents().contains(key)) {
                    list.add(vo);
                }
            }
        }
        AceAjaxView view = new AceAjaxView();
        view.addObject("list", list);
        return view;
    }
    
    @RequestMapping
    @NoReferer
    public ModelAndView superList(PPage pPage, DepartmentQuery query) {

         Page<Department> page = departmentService.page(query, pPage.getPageStart() , pPage.getPageSize());
        List<AdminDepartmentVO> voList = departmentHandler.toVOList4Admin(page.getData());
        String param = "displayName=" + StringUtil.nullToEmpty(query.getDisplayName());
        AcePagingView pagingView = new AcePagingView("/admin/organization-Department-list", DIR + "/list?" + param, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", voList);
        pagingView.addObject("query", query);
        return pagingView;
    }


    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, DepartmentQuery query) {

        QClerk clerk = PageParameterUtil.getParameterValues(request, organizationClient.CLERK_LOGIN_PARAMETER_KEY);
        Department d = departmentService.get(clerk.getDepartmentId());
        List<Department> list = departmentService.listChildrenByParent(query, d.getBsid());
        int total = departmentService.countChildrenByParent(query, d.getBsid());
        // Page<Department> page = departmentService.page(query, pPage.getPageStart() , pPage.getPageSize());
        List<AdminDepartmentVO> voList = departmentHandler.toVOList4Admin(list);
        String param = "displayName=" + StringUtil.nullToEmpty(query.getDisplayName());
        AcePagingView pagingView = new AcePagingView("/admin/organization-Department-list", DIR + "/list?" + param, pPage.getPageNum(), pPage.getPageSize(), total);
        pagingView.addObject("result", voList);
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd(HttpServletRequest request, DepartmentQuery query) {

        QClerk clerk = PageParameterUtil.getParameterValues(request, organizationClient.CLERK_LOGIN_PARAMETER_KEY);
        Department d = departmentService.get(clerk.getDepartmentId());
        List<Department> dlist = departmentService.listChildrenByParent(query, d.getBsid());
        int root = 0;// 是否存在根节点0否1是
        for (Department department : dlist) {
            if (department.getParentId() == -1) {
                root = 1;
            }
        }
        List<AdminPlatformTypeVO> typeList = this.typeList(d.getId());
        ModelAndView model = new ModelAndView("/admin/organization-Department-add");
        // List<Department> departmentList = departmentService.listAll();
        // model.addObject("departmentList", departmentList);
        model.addObject("departmentList", dlist);
        model.addObject("root", root);
        model.addObject("typeList", typeList);
        List<String> provinceList = publicdataClient.listProvince();
        List<KeyValueVO> voList = publicdataClient.exchageStr(provinceList, null, null);
        model.addObject("provinceList", voList);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(Department department) {

        List<AdminPlatformTypeVO> typeList = this.typeList(department.getParentId());
        AssertUtil.assertTrue(typeList.size()>0, "该节点是最低层节点,不能再添加子节点.");
        department.setManager(Long.valueOf(-1));
        department.setRegistTime(new Date());
        if (StringUtils.isNotEmpty(department.getImage())) {
            department.setImage(fileSDKClient.uidToUrl(department.getImage()));
        }
        departmentService.add(department);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Department department = departmentService.get(id);
        AdminDepartmentVO adminDepartmentVO = departmentHandler.toVO4Admin(department);
        ModelAndView model = new ModelAndView("/admin/organization-Department-edit");
        model.addObject("department", adminDepartmentVO);
        List<String> list = publicdataClient.listProvince();
        List<String> cityList = publicdataClient.listCity(adminDepartmentVO.getProvince());
        List<String> districtList = publicdataClient.listDistrict(adminDepartmentVO.getCity());
        model.addObject("provinceList", publicdataClient.exchageStr(list, adminDepartmentVO.getProvince(), "selected"));
        model.addObject("cityList", publicdataClient.exchageStr(cityList, adminDepartmentVO.getCity(), "selected"));
        model.addObject("districtList", publicdataClient.exchageStr(districtList, adminDepartmentVO.getDistrict(), "selected"));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(Department department) {

        Department oldDepartment = departmentService.get(department.getId());
        if (StringUtils.isNotEmpty(department.getImage())) {
            department.setImage(fileSDKClient.uidToUrl(department.getImage()));
        }
        department.setName(department.getName());
        department.setType(oldDepartment.getType());
        department.setBsid(oldDepartment.getBsid());
        department.setCode(oldDepartment.getCode());
        department.setParentId(oldDepartment.getParentId());
        department.setManager(oldDepartment.getManager());
        department.setDisplayName(oldDepartment.getDisplayName());
        department.setRegistTime(oldDepartment.getRegistTime());
        departmentService.update(department);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AceAjaxView aceAjaxView = new AceAjaxView();
        AssertUtil.assertNotNull(id, "ID不能为空");
        Department department = departmentService.get(id);
        AssertUtil.assertNotNull(department, "部门不存在.");
        List<Department> list = departmentService.listAll();
        for (Department temp : list) {
            if (temp.getParentId() == department.getId()) {
                aceAjaxView.setMessage("删除失败,部门:" + department.getName() + " 中存在子部门");
                aceAjaxView.setStatus(0);
                return aceAjaxView;
            }
        }
        List<DepartmentClerk> clerkList = departmentClerkService.listByDepartment(id);
        if (CollectionUtils.isNotEmpty(clerkList)) {
            aceAjaxView.setMessage("删除失败,部门:" + department.getName() + "中存在:" + clerkList.size() + "名员工.");
            aceAjaxView.setStatus(0);
            return aceAjaxView;
        }
        departmentService.delete(id);
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
