package com.qcloud.project.forest.web.controller.admin;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.Modular;
import com.qcloud.project.forest.model.query.ModularQuery;
import com.qcloud.project.forest.service.ModularService;
import com.qcloud.project.forest.web.handler.ModularHandler;
import com.qcloud.project.forest.web.vo.admin.AdminModularVO;

@Controller
@RequestMapping(value = "/" + AdminModularController.DIR)
public class AdminModularController {

    public static final String DIR = "admin/modular";

    @Autowired
    private ModularService     modularService;

    @Autowired
    private ModularHandler     modularHandler;

    /**
     * 展示模块列表
     * @param request
     * @param pPage
     * @param query
     * @return
     */
    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, ModularQuery query) {

        Page<Modular> page = modularService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminModularVO> list = modularHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/forest-Modular-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        return pagingView;
    }

    /**
     * 跳转到添加的页面
     * @return
     */
    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/forest-Modular-add");
        return model;
    }

    /**
     * 添加模块
     * @param modular
     * @return
     */
    @RequestMapping
    public AceAjaxView add(Modular modular) {

        modularService.add(modular);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    /**
     * 跳转到编辑页面
     * @param id
     * @param queryStr
     * @return
     */
    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Modular modular = modularService.get(id);
        AdminModularVO adminModularVO = modularHandler.toVO4Admin(modular);
        ModelAndView model = new ModelAndView("/admin/forest-Modular-edit");
        model.addObject("modular", adminModularVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    /**
     * 编辑模块
     * @param modular
     * @param queryStr
     * @return
     */
    @RequestMapping
    public AceAjaxView edit(Modular modular, String queryStr) {

        modularService.update(modular);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    /**
     * 启用模块
     * @param id
     * @param enable
     * @return
     */
    @RequestMapping
    public AceAjaxView enable(Long id, int enable) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Modular modular = modularService.get(id);
        modular.setEnable(enable);
        modularService.update(modular);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    /**
     * 验证
     * @param code
     * @return
     */
    @RequestMapping
    public AceAjaxView validate(String code) {

        Boolean exist = false;
        Modular modular = modularService.getByCode(code);
        if (modular != null) {
            exist = true;
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.addObject("exist", exist);
        return aceAjaxView;
    }
}
