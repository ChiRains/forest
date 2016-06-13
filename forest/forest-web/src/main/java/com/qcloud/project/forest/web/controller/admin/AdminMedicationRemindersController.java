package com.qcloud.project.forest.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.MedicationReminders;
import com.qcloud.project.forest.service.MedicationRemindersService;
import com.qcloud.project.forest.web.handler.MedicationRemindersHandler;
import com.qcloud.project.forest.model.query.MedicationRemindersQuery;
import com.qcloud.project.forest.web.vo.admin.AdminMedicationRemindersVO;
		
@Controller
@RequestMapping(value = "/" + AdminMedicationRemindersController.DIR)
public class AdminMedicationRemindersController {
	
	public static final String DIR = "admin/medicationReminders";
	
	@Autowired
	private MedicationRemindersService medicationRemindersService;
	@Autowired
	private MedicationRemindersHandler medicationRemindersHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(HttpServletRequest request, PPage pPage, MedicationRemindersQuery query) {
	    
		Page<MedicationReminders> page = medicationRemindersService.page(query, pPage.getPageStart(), pPage.getPageSize());
		List<AdminMedicationRemindersVO> list = medicationRemindersHandler.toVOList4Admin(page.getData());
		
	    String pageQueryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String)PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));   
		AcePagingView pagingView = new AcePagingView("/admin/forest-MedicationReminders-list", DIR
				+ "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());		
		pagingView.addObject("result", list);
		pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
		
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/forest-MedicationReminders-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MedicationReminders medicationReminders) {
		medicationRemindersService.add(medicationReminders);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id, String queryStr) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MedicationReminders medicationReminders=medicationRemindersService.get(id);
		AdminMedicationRemindersVO adminMedicationRemindersVO=medicationRemindersHandler.toVO4Admin(medicationReminders);
		ModelAndView model = new ModelAndView("/admin/forest-MedicationReminders-edit");
		model.addObject("medicationReminders", adminMedicationRemindersVO);
		model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MedicationReminders medicationReminders, String queryStr) {
		medicationRemindersService.update(medicationReminders);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		medicationRemindersService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
