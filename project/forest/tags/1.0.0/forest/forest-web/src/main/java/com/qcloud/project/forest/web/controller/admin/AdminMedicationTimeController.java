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
import com.qcloud.project.forest.model.MedicationTime;
import com.qcloud.project.forest.service.MedicationTimeService;
import com.qcloud.project.forest.web.handler.MedicationTimeHandler;
import com.qcloud.project.forest.model.query.MedicationTimeQuery;
import com.qcloud.project.forest.web.vo.admin.AdminMedicationTimeVO;
		
@Controller
@RequestMapping(value = "/" + AdminMedicationTimeController.DIR)
public class AdminMedicationTimeController {
	
	public static final String DIR = "admin/medicationTime";
	
	@Autowired
	private MedicationTimeService medicationTimeService;
	@Autowired
	private MedicationTimeHandler medicationTimeHandler;
	
	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, MedicationTimeQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<MedicationTime> page = medicationTimeService.page(query, start, PAGE_SIZE);
		List<AdminMedicationTimeVO> list = medicationTimeHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/forest-MedicationTime-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());		
		pagingView.addObject("result", list);
	
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/forest-MedicationTime-add");
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(MedicationTime medicationTime) {
		medicationTimeService.add(medicationTime);
		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		MedicationTime medicationTime=medicationTimeService.get(id);
		AdminMedicationTimeVO adminMedicationTimeVO=medicationTimeHandler.toVO4Admin(medicationTime);
		ModelAndView model = new ModelAndView("/admin/forest-MedicationTime-edit");
		model.addObject("medicationTime", adminMedicationTimeVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(MedicationTime medicationTime) {
		medicationTimeService.update(medicationTime);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		medicationTimeService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
}
