package com.qcloud.project.forest.web.controller.admin;

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
import com.qcloud.project.forest.model.Medication;
import com.qcloud.project.forest.service.MedicationService;
import com.qcloud.project.forest.web.handler.MedicationHandler;
import com.qcloud.project.forest.model.query.MedicationQuery;
import com.qcloud.project.forest.web.vo.admin.AdminMedicationVO;
		
@Controller
@RequestMapping(value = "/" + AdminMedicationController.DIR)
public class AdminMedicationController {
	
	public static final String DIR = "admin/medication";
	
	@Autowired
	private MedicationService medicationService;
	@Autowired
	private MedicationHandler medicationHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, MedicationQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<Medication> page = medicationService.page(query, start, PAGE_SIZE);
		List<AdminMedicationVO> list = medicationHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/forest-Medication-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/forest-Medication-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(Medication medication) {
		medicationService.add(medication);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		Medication medication=medicationService.get(id);
		AdminMedicationVO adminMedicationVO=medicationHandler.toVO4Admin(medication);
		ModelAndView model = new ModelAndView("/admin/forest-Medication-edit");
		model.addObject("medication", adminMedicationVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(Medication medication) {
		medicationService.update(medication);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		medicationService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
