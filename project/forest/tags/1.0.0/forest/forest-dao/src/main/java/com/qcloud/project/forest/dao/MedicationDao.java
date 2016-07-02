package com.qcloud.project.forest.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.project.forest.model.Medication;
import com.qcloud.project.forest.model.query.MedicationQuery;
		
public interface MedicationDao extends ISimpleDao<Medication, Long> {

	public boolean add(Medication medication);	
	
	public Medication get(Long id);

	public boolean delete(Long id);
	
	public boolean update(Medication medication);
	
	public List<Medication> list(List<Long> idList);
	
	public Map<Long, Medication> map(Set<Long> idSet);
	
	public Page<Medication> page(MedicationQuery query, int start, int size);

	public List<Medication> listAll();
	
}
