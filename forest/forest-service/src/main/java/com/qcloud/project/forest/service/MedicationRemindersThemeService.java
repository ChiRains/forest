package com.qcloud.project.forest.service;

import java.util.List;

import com.qcloud.pirates.data.Page;
import com.qcloud.project.forest.model.MedicationRemindersTheme;
import com.qcloud.project.forest.model.query.MedicationRemindersThemeQuery;

public interface MedicationRemindersThemeService {
	
	public boolean add(MedicationRemindersTheme medicationRemindersTheme);	
	
	public MedicationRemindersTheme get(Long id);
	
	public	boolean delete(Long id);
	
	public	boolean update(MedicationRemindersTheme medicationRemindersTheme);

	public List<MedicationRemindersTheme> listByUserId(Long userId);

	public Page<MedicationRemindersTheme> page(MedicationRemindersThemeQuery query, int start, int count);
	
	public List<MedicationRemindersTheme> listAll();
}

