package com.qcloud.component.brokerage.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.query.DistributionGradeQuery;
		
public interface DistributionGradeDao extends ISimpleDao<DistributionGrade, Long> {

	public boolean add(DistributionGrade distributionGrade);	
	
	public DistributionGrade get(Long id);

	DistributionGrade getDefault();
	
	public boolean delete(Long id);
	
	public boolean update(DistributionGrade distributionGrade);
	
	public List<DistributionGrade> list(List<Long> idList);
	
	public Map<Long, DistributionGrade> map(Set<Long> idSet);
	
	public Page<DistributionGrade> page(DistributionGradeQuery query, int start, int size);

	public List<DistributionGrade> listAll();
}
