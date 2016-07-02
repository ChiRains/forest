package com.qcloud.project.forest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.dao.MedicationRemindersThemeDao;
import com.qcloud.project.forest.model.MedicationRemindersTheme;
import com.qcloud.project.forest.service.MedicationRemindersThemeService;
import com.qcloud.project.forest.model.query.MedicationRemindersThemeQuery;
		
@Service
public class MedicationRemindersThemeServiceImpl implements MedicationRemindersThemeService{
	
	@Autowired
	private MedicationRemindersThemeDao medicationRemindersThemeDao;
	
	@Autowired
	private AutoIdGenerator autoIdGenerator;
	
	private static final String ID_KEY = "forest_medication_reminders_theme";

	@Override
	public boolean add(MedicationRemindersTheme medicationRemindersTheme) {
		long id = autoIdGenerator.get(ID_KEY);
		medicationRemindersTheme.setId(id);
		
		return medicationRemindersThemeDao.add(medicationRemindersTheme);
	}

	@Override
	public MedicationRemindersTheme get(Long id) {	
		return medicationRemindersThemeDao.get(id);		
	}

	@Override
	public boolean delete(Long id) {
		return medicationRemindersThemeDao.delete(id);
	}
	
	@Override
	public boolean update(MedicationRemindersTheme medicationRemindersTheme) {
		return medicationRemindersThemeDao.update(medicationRemindersTheme);
	}
			
	public List<MedicationRemindersTheme> listByUserId(Long userId){
		return medicationRemindersThemeDao.listByUserId(userId);
	}
	
	@Override
	public Page<MedicationRemindersTheme> page(MedicationRemindersThemeQuery query, int start, int count) {
		return medicationRemindersThemeDao.page(query, start, count);
	}
	
	public List<MedicationRemindersTheme> listAll(){
		return medicationRemindersThemeDao.listAll();
	}
}

