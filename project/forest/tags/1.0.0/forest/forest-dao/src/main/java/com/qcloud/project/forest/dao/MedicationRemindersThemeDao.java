package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.MedicationRemindersTheme;
import com.qcloud.project.forest.model.query.MedicationRemindersThemeQuery;
		
public interface MedicationRemindersThemeDao extends ISimpleDao<MedicationRemindersTheme, Long> {

	public boolean add(MedicationRemindersTheme medicationRemindersTheme);	
	
	public MedicationRemindersTheme get(Long id);

	public boolean delete(Long id);
	
	public boolean update(MedicationRemindersTheme medicationRemindersTheme);
	
	public List<MedicationRemindersTheme> list(List<Long> idList);
	
	public Map<Long, MedicationRemindersTheme> map(Set<Long> idSet);
	
	public Page<MedicationRemindersTheme> page(MedicationRemindersThemeQuery query, int start, int size);

	public List<MedicationRemindersTheme> listAll();
	
	public List<MedicationRemindersTheme> listByUserId(Long userId);

}
